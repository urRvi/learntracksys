# Design Notes

## Why ArrayList instead of array

Arrays in Java are fixed-size — you have to know the capacity up
front, and growing one means allocating a new, bigger array and
copying everything over manually. That's the wrong fit here: the
number of students, courses, and enrollments the app will hold isn't
known when the program starts, and it changes constantly as the user
adds records through the menu.

`ArrayList` handles that growth internally. It also comes with
built-in operations (`add`, `remove`, iteration via streams) that
would otherwise have to be hand-rolled on top of a raw array —
tracking a separate "current size" counter, shifting elements after
a removal, and so on. Since the brief also calls for basic
`try-catch` and clean separation of concerns rather than
low-level data structure work, `ArrayList` was the more appropriate
tool for a repository layer that just needs to store and retrieve
objects.

## Where static members were used and why

`IdGenerator` is the clearest case: `studentIdCounter`,
`courseIdCounter`, and `enrollmentIdCounter` are all `private static
int` fields. IDs need to stay unique and sequential across the
*entire* running application, not per-instance — if `IdGenerator`
had instance fields instead, every service would need to share a
single `IdGenerator` object just to keep counters in sync, which
adds coupling for no benefit. A static counter is the natural fit
for "there's exactly one of these truths for the whole app."

`AppConstants` and `MenuOptions` are `final` classes with only
`static final` fields — they're pure constant holders and never
need to be instantiated at all (both have private constructors to
enforce that).

Everything else — `Student`, `Course`, `Enrollment`, and the
service/repository classes — deliberately uses instance fields,
because each object genuinely represents a distinct real-world
record with its own independent state.

## Where inheritance was used and what it gained

`Student extends Person`. `Person` holds the fields genuinely common
to any person the system might track — id, first/last name, email —
and `Student` adds student-specific state (`batch`, `active`) on
top.

The concrete payoff shows up in `getDisplayName()`. `Person` defines
a baseline implementation (`"First Last"`), and `Student` overrides
it to append the batch (`"First Last (Batch: DTU-2026)"`). Any code
holding a `Person` reference — including hypothetically a future
`Trainer` subclass — automatically gets the right, type-specific
output without needing an `if (obj instanceof Student)` check
anywhere. That's the actual polymorphism win here: behavior
dispatches based on the real runtime type, not on manual branching
in calling code.

`Student`'s constructor also calls `super(id, firstName, lastName,
email)` to let `Person` initialize the fields it owns, instead of
`Student` reaching in and setting them directly — encapsulation is
kept intact even across the inheritance boundary.

## Service layer depending on other services, not repositories

`EnrollmentService` takes `StudentService` and `CourseService` as
constructor dependencies, not `StudentRepository` / `CourseRepository`
directly. That was a deliberate call: it means enrollment logic
always goes through the same "does this student exist" validation
path as everywhere else in the app, instead of duplicating a raw
repository lookup and risking it drifting out of sync with the real
validation rules over time.
