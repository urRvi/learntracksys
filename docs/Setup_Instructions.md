# Setup Instructions

## JDK Version Used

This project was built and tested with **OpenJDK 21** (`21.0.10`).

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

```
Hello, World!
```

If that prints correctly, your JDK setup is good and you're ready to
compile LearnTrack itself — see the main `README.md` for exact build
commands.
