# JVM Basics

## JDK vs JRE vs JVM

These three get thrown around interchangeably but they're not the same thing.

**JVM (Java Virtual Machine)** is the actual engine that runs compiled
Java bytecode. It's what makes Java "write once, run anywhere" —
the JVM abstracts away the underlying OS and hardware, so the same
`.class` file runs unmodified on Windows, Linux, or Mac, as long as
each has a JVM installed.

**JRE (Java Runtime Environment)** is the JVM plus the standard
library classes (`java.util`, `java.io`, etc.) needed to actually run
a Java program. If you only need to *run* Java applications, not
write them, the JRE is all you need.

**JDK (Java Development Kit)** is the JRE plus the developer tools —
the compiler (`javac`), debugger, and other build tools. If you're
writing and compiling Java code (which is what this project needs),
you need the JDK, not just the JRE.

So the containment is: **JDK ⊃ JRE ⊃ JVM**. The JDK includes
everything you need both to build and to run a Java program.

## What Is Bytecode

When you compile a `.java` file with `javac`, you don't get machine
code — you get `.class` files full of **bytecode**. Bytecode is a
platform-independent intermediate format: a set of instructions the
JVM knows how to execute, but not something your CPU can run directly.

This is the layer that makes Java portable. `javac` doesn't need to
know or care what OS or CPU the program will eventually run on — it
just needs to produce valid bytecode. The JVM installed on the target
machine is the piece responsible for translating that bytecode into
whatever native instructions the actual hardware needs, at run time
(via interpretation and/or JIT compilation).

## "Write Once, Run Anywhere"

This is Java's original core pitch: you compile your source code
exactly once, and the resulting bytecode can run on any machine that
has a compatible JVM installed, without recompiling.

Contrast this with a language that compiles straight to native
machine code — that binary is tied to a specific OS/CPU combination,
and you'd need to rebuild it separately for each target platform.
Java sidesteps that by adding the JVM as a middle layer: the
bytecode itself never changes, only the JVM implementation
underneath it varies by platform. In practice this project's
`.class` files, once compiled on this machine, would run identically
on a Windows or Mac laptop with JDK 21 installed — no changes needed.
