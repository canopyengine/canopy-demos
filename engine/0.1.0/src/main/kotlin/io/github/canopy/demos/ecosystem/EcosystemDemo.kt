package io.github.canopy.demos.ecosystem

import io.canopy.engine.app.core.screen.CanopyScreen
import io.canopy.engine.app.headless.terminalApp
import io.canopy.engine.core.nodes.core.asSceneRoot
import io.canopy.engine.input.InputSystem
import io.github.canopy.demos.ecosystem.world.Weather
import io.github.canopy.demos.ecosystem.world.World

class EcosystemDemo : CanopyScreen() {

    // Set main structure
    override fun setup() {
        World("Ecosystem Demo"){
            Weather()
        }.asSceneRoot()
    }

}


fun main() = terminalApp {

    // Add input system
    sceneManager {
        //+InputSystem()
    }

    // Set screen
    screens { start(EcosystemDemo()) }
}.launch()


