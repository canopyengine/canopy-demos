package io.github.canopy.demos.ecosystem.world

import io.canopy.engine.core.managers.manager
import io.canopy.engine.core.nodes.core.Node
import io.canopy.engine.core.nodes.core.behavior
import io.canopy.engine.data.core.assets.AssetsManager
import io.canopy.engine.data.core.parsers.TomlParser
import io.canopy.engine.logging.api.Logs
import io.canopy.engine.logging.bootstrap.CanopyLogging

class World(
    name: String,
    private val seed : String = "",
    block : World.() -> Unit = {}
) : Node<World>(name, block){

    val logger = Logs.get("World")

    var data : WorldData? = null

    override fun create() {
        behavior(
            onReady = {
                val assetsManager = manager<AssetsManager>()

                val file = assetsManager.loadFile("config.toml", AssetsManager.FileSource.Classpath)

                val config = TomlParser.fromFile<WorldData>(file)
                data = config

                logger.info(
                    "grass" to config.grass,
                    "trees" to config.trees,
                    "rivers" to config.rivers,
                    "rabbits" to config.rabbits,
                    "foxes" to config.foxes,
                    "weather" to config.weather,
                ){"Loaded config!"}
            }
        )
    }

}