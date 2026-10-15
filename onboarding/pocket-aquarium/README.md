# Pocket Aquarium

The [starter](starter/) is an empty project skeleton for the terminal onboarding
game. It contains no gameplay yet; `Main.kt` is intentionally empty.

Open `starter/` as the Gradle project. Dependency versions live in
`starter/gradle/version.toml`. Use JDK 17 for Gradle and install JDK 25 for game
compilation. The Gradle wrapper works on Windows (`gradlew.bat`), Linux and macOS
(`./gradlew`).

Canopy `0.1.0-alpha.2` currently requires matching locally published engine and
compiler artifacts. Follow the existing [local publication instructions](../../engine/0.1.0/README.md#build-and-run),
using the current foundation integration revision instead of the older ecosystem
snapshot. Alternatively, pass `-PcanopyRepository=file:///path/to/repository`
to select a staged Maven repository.

From `starter/`, run `./gradlew build` to check the skeleton. There is no runnable
entry point until you add `fun main()` to `src/main/kotlin/Main.kt`. The terminal
launcher and completed reference game will be added with the playable example.
