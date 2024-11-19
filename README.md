# Flappy Bird (Java Implementation)

A Java-based recreation of the Flappy Bird game, showcasing simple game mechanics, object-oriented programming, and real-time gameplay.

---

## Features

- Dynamic obstacle generation
- Basic 2D graphics rendering
- High-score tracking

---

## Prerequisites

- JDK 11 or later
- A build tool such as Maven or Gradle

---

## Getting Started

### Clone the repository:

```bash
git clone https://github.com/ervardaan/flappy-bird.git
cd flappy-bird
```
### Build and Run
##### Using Maven: 
Compile the project:

```bash
mvn compile
```
##### Run the application:

```bash
mvn exec:java -Dexec.mainClass="com.flappybird.FlappyBird"
```

### Project Structure
- src/main/java/: Contains the main Java source files.
- src/main/resources/: Holds resources like images and configuration files.
- src/test/java/: Includes unit test cases.
- lib/: External JAR dependencies.

### How to Play
- Run the program.
- Use the spacebar to make the bird jump.
- Avoid the obstacles to earn points.

### Contributing
Contributions are welcome! Please read the CONTRIBUTING.md for guidelines.

### License
This project is licensed under the MIT License.

### Acknowledgments
Inspired by the original Flappy Bird by Dong Nguyen.