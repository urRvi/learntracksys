# Setup Instructions

## JDK Version Used

This project was built and tested with **OpenJDK 21** (Eclipse Temurin build `21.0.12`).

Check your version with:

```bash
java -version
javac -version
```

Any JDK from version 17 onward will work, since the project uses modern
`switch` expressions (`case X -> ...`). If you're on an older JDK,
those switch statements will need to be rewritten with the classic
`case X: ... break;` syntax.

## Installing the JDK

**Ubuntu/Debian:**
```bash
sudo apt-get update
sudo apt-get install openjdk-21-jdk-headless
```

**Windows/Mac:**
Download from [Adoptium](https://adoptium.net/) (Eclipse Temurin builds)
and follow the installer.

## "Hello World" Verification

Before touching this project, confirm your JDK works with a throwaway file:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

Compile and run it:

```bash
javac HelloWorld.java
java HelloWorld
```

Expected output:

Hello, World!


### Verification Output

Ran the above on this machine with JDK 21 (Temurin 21.0.12) installed via
Adoptium. Output confirmed:

PS> java -version
openjdk version "21.0.12" 2026-07-21 LTS
OpenJDK Runtime Environment Temurin-21.0.12+8 (build 21.0.12+8-LTS)
OpenJDK 64-Bit Server VM Temurin-21.0.12+8 (build 21.0.12+8-LTS, mixed mode, sharing)

PS> javac -version
javac 21.0.12

PS> java HelloWorld
Hello, World!


If that prints correctly, your JDK setup is good and you're ready to
compile LearnTrack itself — see the main `README.md` for exact build
commands.