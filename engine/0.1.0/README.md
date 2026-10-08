# Ecosystem demo scaffold

Targets **Canopy 0.1.0-dev2** with Kotlin2.4.10 and JDK25.

## Implemented

- Terminal application and screen setup.
- A World node loading `src/main/resources/config.toml` with Toml.
- A Context provider exposing SimulationData to a Simulation child.
- Configuration and ready logging.

Configuration contains grass, trees, rivers, rabbits, foxes and weather.
CommandHandler, EventLogger, Narrator and Weather are placeholders. There is no
playable simulation, animal decision loop, calendar, command parser or narrated
terminal view yet. See [the 0.1.0 technical outline](tech-outline.md).

The planned slice uses rabbits and foxes, grass/water/cover, nine day phases,
naturally changing clear/rain weather, and a live command overlay with minimum
declarative UI and adaptive terminal layout. These are planned features, not
capabilities of this pinned scaffold. Its weather configuration still accepts
`SUNNY`, `SNOWY`, `RAINY` and `STORMY`; aligning it to clear/rain and adopting the
integrated engine requires a separate code/configuration migration.

## Build and run

First clone the engine and publish the matching snapshot:

```sh
git clone https://github.com/canopyengine/canopy.git
cd canopy
git checkout 27019353589d8eb36adb04e387ca6749a0d5379f
./gradlew publishToMavenLocal
```

From this demo directory (`canopy-demos/engine/0.1.0`):

```sh
./gradlew ktlintCheck assemble
./gradlew run
```

On Windows use `gradlew.bat`. This repository has no root Gradle project.
The terminal app runs until Ctrl+C; it does not currently draw a simulation view.
Diagnostics are under `.canopy/logs/` relative to the working directory.

Artifacts use `io.canopy:engine:0.1.0-dev2` and
`io.canopy:platforms-terminal:0.1.0-dev2` from `mavenLocal()`. Desktop is
currently disabled in the engine; this demo does not require it.

## Quality and CI

Use `./gradlew ktlintFormat` to apply the engine formatting conventions and
`./gradlew ktlintCheck` to check them. CI runs the Gradle build and CodeQL from
this directory after publishing the pinned engine snapshot locally.
Dependency submission runs only on pushes to main with write permission.
