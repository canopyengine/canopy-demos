# Pocket Aquarium

A small terminal game for learning how a Canopy app fits together. Move the
marker with the Left and Right arrows, press Space to drop food, and feed all
three fish before six pieces of food run out. Press R to restart or Q to quit.

## Run it

Use JDK 25. From this directory, run:

~~~
java CanopyLaunch.java --current-terminal
~~~

The launcher builds the project and keeps keyboard input attached to the game.
To check the code without playing, use `.\gradlew.bat build` on Windows or
`./gradlew build` on Linux/macOS. Dependency versions are in
`gradle/version.toml`. Gradle searches Maven Local and Maven Central; you can
also point it at a repository with `-PcanopyRepository=file:///path/to/repository`.

## Read the code

1. `src/main/kotlin/onboarding/aquarium/terminal/Main.kt` creates a terminal app,
   chooses the initial scene, and launches it.
2. `src/main/kotlin/onboarding/aquarium/terminal/nodes/Aquarium.kt` is the scene
   root. Canopy calls `onSetup` to create child nodes, `onInput` for keys, and
   `onUpdate` each frame. It draws the current game state into a `Grid`.
3. `src/main/kotlin/onboarding/aquarium/terminal/nodes/Fish.kt` stores each
   fish's position, direction, and fed state. The fish are child nodes of the
   aquarium, so it can find them with `getChildrenOfType<Fish>()`.

The `Column`, `Text`, and `Grid` nodes make up the terminal UI. A `GridSymbol`
provides one character and its color for a grid cell. Try changing the fish
positions or colors in `addFish`, or the food count in `STARTING_FOOD`.
