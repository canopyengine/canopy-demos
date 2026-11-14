# Pocket Aquarium

The [starter](starter/) is an empty project skeleton for the terminal onboarding
game. It contains no gameplay yet; `Main.kt` is intentionally empty.

Open `starter/` as the Gradle project. Dependency versions live in
`starter/gradle/version.toml`. Use JDK 17 for Gradle and install JDK 25 for game
compilation. The Gradle wrapper works on Windows (`gradlew.bat`), Linux and macOS
(`./gradlew`).

Canopy `0.1.0-dev4` currently requires matching locally published engine and
compiler artifacts. Follow the existing [local publication instructions](../../engine/0.1.0/README.md#build-and-run),
using pinned engine revision `01e049dcf5697950efe094ed7b148049f49c352f` from
engine PR [#244](https://github.com/canopyengine/canopy/pull/244), based on
`codex/agent/refactor/foundation-integration`. Alternatively, pass `-PcanopyRepository=file:///path/to/repository`
to select a staged Maven repository.

From `starter/`, run `./gradlew build` to check the skeleton. There is no runnable
entry point until you add `fun main()` to `src/main/kotlin/Main.kt`. After adding it,
reload Gradle in the IDE and run **Tasks → application → canopyRun**. The IDE
creates a reusable run configuration. The Canopy plugin supplies the launcher;
no source file needs to be copied or configured.

From a terminal use `./gradlew canopyRun` on Linux/macOS or
`.\gradlew.bat canopyRun` on Windows. Game arguments use `--args="--seed 42"`.
The task builds your changes and opens the terminal game in its own window.
The window closes on game exit; build/startup errors remain visible in the IDE.
Native Run/Debug beside `main()` retains its existing IDE behavior.

The included `CanopyLaunch.java` remains an alternative for direct terminal/SSH
launching. `java CanopyLaunch.java --current-terminal` uses the caller's terminal.
`canopyRun --current-terminal` is available for automation, but retains Gradle's
console/input limitations. To use isolated source artifacts, pass
`-PcanopyRepository=file:///absolute/path/to/repository` to Gradle.
Keep the engine and compiler from the same pinned revision; both currently use
`0.1.0-dev4`. The completed reference game follows the maintainer's Aquarium work.
