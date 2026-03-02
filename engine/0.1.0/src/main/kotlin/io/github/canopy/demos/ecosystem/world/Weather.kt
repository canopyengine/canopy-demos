package io.github.canopy.demos.ecosystem.world

import io.canopy.engine.core.nodes.core.Node
import kotlinx.serialization.Serializable

@Serializable
enum class WeatherType {
    SUNNY,
    SNOWY,
    RAINY,
    STORMY,
}

class Weather : Node<Weather>("weather", {})