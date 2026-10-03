package io.github.canopy.demos.ecosystem.world.simulation

import io.canopy.engine.core.nodes.Node
import kotlinx.serialization.Serializable

/** Weather values accepted by the demo configuration. */
@Serializable
enum class WeatherType {
    SUNNY,
    SNOWY,
    RAINY,
    STORMY,
}

/** Placeholder for a future weather simulation; currently has no behavior. */
class Weather : Node<Weather>("weather")
