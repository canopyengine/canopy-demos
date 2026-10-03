package io.github.canopy.demos.ecosystem

import io.canopy.engine.app.Screen
import io.canopy.engine.app.screens
import io.canopy.platforms.terminal.app.terminalApp
import io.github.canopy.demos.ecosystem.world.World

/** Installs the configuration-loading scaffold each time the demo screen is entered. */
class EcosystemDemo : Screen() {
    override fun onEnter() {
        World("Ecosystem Demo").asSceneRoot()
    }
}

/** Launches the terminal host; Ctrl+C stops its input and frame loops. */
fun main() = terminalApp {
    screens { start(EcosystemDemo()) }
}.launch()
