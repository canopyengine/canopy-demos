package io.github.canopy.demos.ecosystem.world

import io.canopy.engine.core.flow.Context
import io.canopy.engine.core.managers.manager
import io.canopy.engine.core.nodes.Node
import io.canopy.engine.core.nodes.behavior
import io.canopy.engine.data.core.assets.AssetsManager
import io.canopy.engine.data.core.parsers.Toml
import io.canopy.engine.logging.logger
import io.github.canopy.demos.ecosystem.world.input.CommandHandler
import io.github.canopy.demos.ecosystem.world.logs.EventLogger
import io.github.canopy.demos.ecosystem.world.narrator.Narrator
import io.github.canopy.demos.ecosystem.world.simulation.Simulation
import io.github.canopy.demos.ecosystem.world.simulation.SimulationData

class World(
    name: String,
    private val seed: String = "",
    block: World.() -> Unit = {},
) : Node<World>(name, block) {
    val logger = logger("World")

    var data: SimulationData? = null

    override fun create() {
        behavior(
            onEnterTree = {
                val assetsManager = manager<AssetsManager>()

                val file = assetsManager.loadFile("config.toml", AssetsManager.FileSource.Classpath)

                val config = Toml.fromFile<SimulationData>(file)
                data = config

                logger.info(
                    "grass" to config.grass,
                    "trees" to config.trees,
                    "rivers" to config.rivers,
                    "rabbits" to config.rabbits,
                    "foxes" to config.foxes,
                    "weather" to config.weather,
                ) { "Loaded config!" }
            },
        )


        Context {
            provide("data") { this@World.data }

            // 1. Run simulation
            Simulation()
            // 2. Log Events
            EventLogger()
            // 3. Summarizes events
            Narrator()
            // 4. Handle user command
            CommandHandler()
        }
    }
}
