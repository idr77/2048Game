# Developer Guidelines

## Compilation and Execution
This project is a Java Swing application.

### Build
To compile the project, run:
```bash
mkdir -p classes
javac -d classes -sourcepath src src/fr/upem/ediall02/game/Game2048.java
```

### Run
To run the game:
```bash
java -cp classes fr.upem.ediall02.game.Game2048
```

## Structure
- `src/fr/upem/ediall02/game`: Main package
  - `controller`: Logic and ActionListeners
  - `model`: Game logic (MVC Model)
  - `view`: Swing components (MVC View)
