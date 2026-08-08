# LearnTrack — Student & Course Management System

A console-based Student & Course Management System built in Core
Java. Admins can manage students, courses, and enrollments through a
menu-driven terminal interface, with all data held in memory for the
lifetime of the program.

This project was built as a fundamentals exercise — no frameworks,
no database, no concurrency. Just plain Java: classes, objects,
constructors, encapsulation, basic inheritance/polymorphism,
`ArrayList`, and `try-catch`.

## Features

**Student Management**
- Add a new student (with or without email — constructor/method overloading)
- View all students
- Search a student by ID
- Deactivate a student (soft delete — sets `active = false`, doesn't remove the record)

**Course Management**
- Add a new course (with or without a description)
- View all courses
- Activate / deactivate a course

**Enrollment Management**
- Enroll a student in a course (blocks enrollment if either is inactive)
- View all enrollments for a given student
- Mark an enrollment as `COMPLETED` or `CANCELLED`

## How to Compile and Run

Requires JDK 17+ (built and tested on JDK 21). See
[`docs/Setup_Instructions.md`](docs/Setup_Instructions.md) if you
need to install one.

From the project root:

```bash
# Compile everything into an `out/` directory
find src -name "*.java" > sources.txt
javac -d out @sources.txt

# Run
java -cp out com.airtribe.learntrack.Main
```

Or, if your shell doesn't support `find`/`@sources.txt` well (e.g.
some Windows setups), compile directly:

```bash
javac -d out src/com/airtribe/learntrack/**/*.java src/com/airtribe/learntrack/*.java
java -cp out com.airtribe.learntrack.Main
```

**Running from an IDE:** import the project, mark `src` as the
source root, and run `com.airtribe.learntrack.Main`.

## Project Structure

```
src/com/airtribe/learntrack/
├── Main.java              # Menu-driven console entry point
├── entity/                # Person, Student, Course, Enrollment
├── repository/            # In-memory ArrayList-backed storage
├── service/                # Business logic + validation
├── exception/              # EntityNotFoundException, InvalidInputException
├── util/                   # IdGenerator, InputValidator
├── constants/               # AppConstants, MenuOptions
└── enums/                    # EnrollmentStatus, CourseStatus
```

See [`docs/Design_Notes.md`](docs/Design_Notes.md) for the reasoning
behind these choices — why `ArrayList` over arrays, where statics
were used, and what the `Person → Student` inheritance actually buys.

## Class Diagram

```mermaid
classDiagram
    class Person {
        -int id
        -String firstName
        -String lastName
        -String email
        +getDisplayName() String
    }

    class Student {
        -String batch
        -boolean active
        +getDisplayName() String
    }

    class Course {
        -int id
        -String courseName
        -String description
        -int durationInWeeks
        -boolean active
    }

    class Enrollment {
        -int id
        -int studentId
        -int courseId
        -LocalDate enrollmentDate
        -EnrollmentStatus status
    }

    class EnrollmentStatus {
        <<enumeration>>
        ACTIVE
        COMPLETED
        CANCELLED
    }

    class StudentRepository {
        -List~Student~ students
        +save(Student)
        +findAll() List~Student~
        +findById(int) Optional~Student~
    }

    class CourseRepository {
        -List~Course~ courses
        +save(Course)
        +findAll() List~Course~
        +findById(int) Optional~Course~
    }

    class EnrollmentRepository {
        -List~Enrollment~ enrollments
        +save(Enrollment)
        +findByStudentId(int) List~Enrollment~
    }

    class StudentService {
        -StudentRepository studentRepository
        +addStudent(...) Student
        +removeStudent(int)
    }

    class CourseService {
        -CourseRepository courseRepository
        +addCourse(...) Course
        +setActive(int, boolean)
    }

    class EnrollmentService {
        -EnrollmentRepository enrollmentRepository
        -StudentService studentService
        -CourseService courseService
        +enroll(...) Enrollment
        +updateStatus(int, EnrollmentStatus) Enrollment
    }

    class Main {
        +main(String[])
    }

    Person <|-- Student : inheritance
    Enrollment --> EnrollmentStatus : has a
    StudentRepository o-- Student : stores
    CourseRepository o-- Course : stores
    EnrollmentRepository o-- Enrollment : stores
    StudentService --> StudentRepository : uses
    CourseService --> CourseRepository : uses
    EnrollmentService --> EnrollmentRepository : uses
    EnrollmentService --> StudentService : uses
    EnrollmentService --> CourseService : uses
    Main --> StudentService : uses
    Main --> CourseService : uses
    Main --> EnrollmentService : uses
```

## Documentation Index

- [`docs/Setup_Instructions.md`](docs/Setup_Instructions.md) — JDK setup and "Hello World" check
- [`docs/JVM_Basics.md`](docs/JVM_Basics.md) — JDK vs JRE vs JVM, bytecode, portability
- [`docs/Design_Notes.md`](docs/Design_Notes.md) — architectural reasoning

## Known Limitations

Everything is in-memory only — closing the program wipes all data.
No persistence layer was in scope for this stage of the project.
