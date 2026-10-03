package io.github.canopy.demos.ecosystem.world

import io.canopy.engine.core.flows.Context
import io.canopy.engine.core.managers.manager
import io.canopy.engine.core.nodes.Node
import io.canopy.engine.core.nodes.behavior
import io.canopy.engine.data.assets.AssetsManager
import io.canopy.engine.data.assets.FileSource
import io.canopy.engine.data.parsers.Toml
import io.canopy.engine.logging.logger
import io.github.canopy.demos.ecosystem.world.input.CommandHandler
import io.github.canopy.demos.ecosystem.world.logs.EventLogger
import io.github.canopy.demos.ecosystem.world.narrator.Narrator
import io.github.canopy.demos.ecosystem.world.simulation.Simulation
import io.github.canopy.demos.ecosystem.world.simulation.SimulationData

/** Builds configuration-backed placeholder nodes for the proposed ecosystem simulation. */
class World(name: String, private val seed: String = "", block: World.() -> Unit = {}) :
    Node<World>(name, block = block) {
    val logger = logger("World")

    var data: SimulationData? = null

    override fun nodeInit() {
        behavior(
            onEnterTree = {
                val assetsManager = manager<AssetsManager>()

                val file = assetsManager.loadFile("config.toml", FileSource.Classpath)

                val config = Toml.fromFile<SimulationData>(file)
                data = config

                logger.info(
                    "grass" to config.grass,
                    "trees" to config.trees,
                    "rivers" to config.rivers,
                    "rabbits" to config.rabbits,
                    "foxes" to config.foxes,
                    "weather" to config.weather
                ) { "Loaded config!" }
            }

        )

        Context {
            provide("data") { this@World.data }

            // Planned gameplay roles; currently configuration lookup and placeholder nodes.
            Simulation()
            EventLogger()
            Narrator()
            CommandHandler()
        }
    }
}
