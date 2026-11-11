# Pocket Aquarium

The [starter](starter/) is an empty project skeleton for the terminal onboarding
game. It contains no gameplay yet; `Main.kt` is intentionally empty.

Open `starter/` as the Gradle project. Dependency versions live in
`starter/gradle/version.toml`. Use JDK 17 for Gradle and install JDK 25 for game
compilation. The Gradle wrapper works on Windows (`gradlew.bat`), Linux and macOS
(`./gradlew`).

Canopy `0.1.0-dev4` currently requires matching locally published engine and
compiler artifacts. Follow the existing [local publication instructions](../../engine/0.1.0/README.md#build-and-run),
using pinned engine revision `d6349011a35695b26fd3e0fa290f19bbc45c4f18` from
`codex/agent/feat/native-reactive-properties` (engine PR232). Alternatively, pass `-PcanopyRepository=file:///path/to/repository`
to select a staged Maven repository.

From `starter/`, run `./gradlew build` to check the skeleton. There is no runnable
entry point until you add `fun main()` to `src/main/kotlin/Main.kt`. After adding it, launch from `starter/` with the same command on Windows, Linux
and macOS:

```sh
java CanopyLaunch.java
```

The included launcher builds through Gradle, then starts the game directly in a
separate terminal window so keyboard input reaches the game. Use
`java CanopyLaunch.java --current-terminal` for SSH or a headless session.
To select isolated artifacts, append
`--gradle-arg "-PcanopyRepository=file:///absolute/path/to/repository"`.
Keep the engine and compiler from the same pinned revision; both currently use
`0.1.0-dev4`. The completed reference game follows the maintainer's Aquarium work.
