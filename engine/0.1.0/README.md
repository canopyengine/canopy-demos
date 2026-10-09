# Ecosystem demo scaffold

Targets **Canopy 0.1.0-dev2** with Kotlin2.4.10. Install JDK17 for the compiler tooling
and Gradle host, plus JDK25 for engine/game compilation and execution.

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
new weather model requires a separate code/configuration migration.

## Build and run

First clone the engine and publish the matching namespace-migration revision.
This revision is under review in [engine PR #214](https://github.com/canopyengine/canopy/pull/214);
it is not yet a published Central release. The predecessor `9c1e0f9` still publishes the old namespace and cannot build this
consumer. Until Maven Central publication, local publication remains required:

```sh
git clone https://github.com/canopyengine/canopy.git
cd canopy
git checkout 61122d43706ee9e1e4aa78c84764545be2f46ee9
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

Artifacts use `io.github.canopyengine:engine:0.1.0-dev2` and
`io.github.canopyengine:platforms-terminal:0.1.0-dev2` from `mavenLocal()`.
The matching `io.github.canopyengine.compiler` Gradle plugin is applied and
resolved through `mavenLocal()` in `pluginManagement`. Kotlin package imports
remain `io.canopy.*`; only Maven coordinates and the plugin ID change. Desktop is
currently disabled in the engine; this demo does not require it.

## Quality and CI

Use `./gradlew ktlintFormat` to apply the engine formatting conventions and
`./gradlew ktlintCheck` to check them. CI runs the Gradle build and CodeQL from
this directory after publishing the pinned engine snapshot locally.
Dependency submission runs only on pushes to main with write permission.
