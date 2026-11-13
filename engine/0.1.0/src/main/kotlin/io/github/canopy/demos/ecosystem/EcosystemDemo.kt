package io.github.canopy.demos.ecosystem

import io.canopy.engine.app.initialScreen
import io.canopy.platforms.terminal.app.terminalApp
import io.github.canopy.demos.ecosystem.world.World

/** Lazily creates the configuration-loading world as the initial screen. Gameplay remains in the World tree. */
fun main() = terminalApp {
    initialScreen<World> { World("Ecosystem Demo") }
}.launch()
