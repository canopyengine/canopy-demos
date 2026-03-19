package io.github.canopy.demos.ecosystem

import io.canopy.backends.terminal.app.terminalApp
import io.canopy.engine.app.screen.Screen
import io.github.canopy.demos.ecosystem.world.World

class EcosystemDemo : Screen() {
    // Set main structure
    override fun setup() {
        World("Ecosystem Demo").asSceneRoot()
    }
}

fun main() =
    terminalApp {
        // Add input system
        sceneManager {
            // +InputSystem()
        }

        // Set screen
        screens { start(EcosystemDemo()) }
    }.launch()
