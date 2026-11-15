package onboarding.aquarium.terminal

import io.canopy.engine.app.App
import io.canopy.engine.app.AppConfig
import io.canopy.engine.app.screens
import io.canopy.platforms.terminal.app.terminalApp

fun main() {
    val app = terminalApp {
        config(AppConfig(title = "Pocket Aquarium"))

        screens {
            // Note 1 - start<Screen>() is a bit ambiguous,
            // maybe make start<Screen> the only way and whenever on first registration,
            // register it automatically
            start(AquariumScreen())
        }
    }
    app.launch()
}
