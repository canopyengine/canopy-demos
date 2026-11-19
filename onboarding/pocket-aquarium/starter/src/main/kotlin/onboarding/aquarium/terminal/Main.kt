package onboarding.aquarium.terminal

import io.canopy.engine.app.AppConfig
import io.canopy.engine.app.initialScreen
import io.canopy.platforms.terminal.app.terminalApp
import onboarding.aquarium.terminal.nodes.Aquarium

fun main() {
    val app = terminalApp {
        config(AppConfig(title = "Pocket Aquarium"))

        // Canopy creates this scene when the app starts. The callback lets the
        // aquarium quit without needing to know how the app is launched.
        initialScreen<Aquarium> {
            Aquarium(onQuit = { handle.requestExit() })
        }
    }

    app.launch()
}
