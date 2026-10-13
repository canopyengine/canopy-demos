# Ecosystem demo scaffold

Targets the foundation integration's **Canopy 0.1.0-alpha.2** with Kotlin2.4.10.
Install JDK17 for the compiler tooling
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

First clone the engine and publish the matching foundation integration revision.
It is on a side branch and is not yet merged or published on Maven Central:

```sh
git clone https://github.com/canopyengine/canopy.git
cd canopy
git checkout e776bfc715f105543d1120ac4ea325c7dde776e0
./gradlew publishToMavenLocal
```

From this demo directory (`canopy-demos/engine/0.1.0`):

```sh
./gradlew ktlintCheck assemble
java CanopyLaunch.java
```

On Windows use `gradlew.bat` for Gradle commands; the Java launch command is the
same on Windows, Linux and macOS. This repository has no root Gradle project.
The launcher uses the project's wrapper to build `installDist`, then starts the
game's Java runtime directly in a separate terminal window with native input. Avoid `gradlew run`
for live terminal testing because Gradle owns that process's input.
Every launch opens a new window, and Ctrl+C closes the app and that window.
Colors follow the terminal capabilities. Windows uses its native console, macOS
uses Terminal (allow Automation permission if requested), and Linux needs a
desktop with kitty, alacritty or xterm. Use `java CanopyLaunch.java --current-terminal`
for CI, SSH or testing in the current terminal. The scaffold does not currently
draw a simulation view.
Diagnostics are under `.canopy/logs/` relative to the working directory.

Artifacts use `io.github.canopyengine:engine:0.1.0-alpha.2` and
`io.github.canopyengine:platforms-terminal:0.1.0-alpha.2` from `mavenLocal()`.
The matching `io.github.canopyengine.compiler` Gradle plugin is applied and
resolved through `mavenLocal()` in `pluginManagement`. Kotlin package imports
remain `io.canopy.*`. The scaffold's existing Kotlin sources compile unchanged;
the integration also provides the new UiNode system for future demo work.
Desktop is currently disabled in the engine; this demo does not require it.

If using the engine's `stageCentralPublication` task instead of Maven Local,
select its repository explicitly for both the compiler plugin and libraries:

```sh
java CanopyLaunch.java --gradle-arg "-PcanopyRepository=file:///absolute/path/to/canopy/build/central-repository"
```

On Windows the URI has the form `file:///C:/path/to/canopy/build/central-repository`.
Quote the whole argument when the path contains spaces. To build without
launching, pass the same `-PcanopyRepository=...` argument to Gradle. An explicit
repository takes precedence over Maven Local so stale local artifacts do not
shadow it. Staging or publishing matching artifacts is still required.
`CanopyLaunch.java` is copied from engine commit `e776bfc`; keep it aligned with
the engine launcher when updating the pinned integration revision.

## Quality and CI

Use `./gradlew ktlintFormat` to apply the engine formatting conventions and
`./gradlew ktlintCheck` to check them. CI runs the Gradle build and CodeQL from
this directory after publishing the pinned engine snapshot locally.
Dependency submission runs only on pushes to main with write permission.
