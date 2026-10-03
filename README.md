# Canopy demos

Examples for the experimental Canopy engine. The committed project in
[engine/0.1.0](engine/0.1.0/README.md) targets **0.1.0-dev2**.

The ecosystem example currently loads TOML configuration, creates a scene and
passes data through Context. Simulation, commands and narration are placeholders.
The [technical outline](engine/0.1.0/tech-outline.md) describes proposed gameplay.

Use JDK25 and the project Gradle9.8.0 wrapper. Publish the matching engine
locally before compiling; [project instructions](engine/0.1.0/README.md) include
exact commands. CI builds the demo, runs ktlint, submits dependencies on main
pushes and scans Java/Kotlin with CodeQL. It builds engine snapshot commit
`27019353589d8eb36adb04e387ca6749a0d5379f` as a reproducible dependency.

Main changes require a PR, one human approval, passing build/CodeQL checks and
squash merging. Follow the [Canopy contribution guidelines](https://github.com/canopyengine/canopy-docs/blob/main/markdown/contributing/contributing.md),
including agent branch, commit and PR provenance.
