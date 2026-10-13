# Canopy demos

Examples for the experimental Canopy engine. The committed project in
[engine/0.1.0](engine/0.1.0/README.md) targets the foundation integration's
**0.1.0-alpha.2** artifacts on this side branch.

The ecosystem example currently loads TOML configuration, creates a scene and
passes data through Context. Simulation, commands and narration are placeholders.
The [technical outline](engine/0.1.0/tech-outline.md) defines the planned 0.1.0
slice: rabbits and foxes, cover, nine day phases, naturally evolving clear/rain
weather and a live command overlay with adaptive declarative UI. The scaffold now uses the integrated engine and matching compiler plugin, but
has not implemented this gameplay.

Use JDK17 for compiler tooling/Gradle and JDK25 for engine/game code, with the
project Gradle9.8.0 wrapper. Publish the matching engine
locally before compiling; [project instructions](engine/0.1.0/README.md) include
exact commands. CI builds the demo, runs ktlint, submits dependencies on main
pushes and scans Java/Kotlin with CodeQL. It builds engine snapshot commit
`9b860bda23b0cbc5ad1cb46be45ecac54c32315b` as a reproducible dependency.
This integration is not yet merged or published on Maven Central. The project
includes the bare Java launcher for native terminal input; see its instructions.

Main changes require a PR, one human approval, passing build/CodeQL checks and
squash merging. Follow the [Canopy contribution guidelines](https://github.com/canopyengine/canopy-docs/blob/main/markdown/contributing/contributing.md),
including agent branch, commit and PR provenance.
