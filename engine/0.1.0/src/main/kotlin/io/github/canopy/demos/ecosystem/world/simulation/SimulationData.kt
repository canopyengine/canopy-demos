package io.github.canopy.demos.ecosystem.world.simulation

import kotlinx.serialization.Serializable

@Serializable
class SimulationData(
    // Biome
    var grass: Int = 0,
    var trees: Int = 0,
    var rivers: Int = 0,
    // Animals
    var rabbits: Int = 0,
    var foxes: Int = 0,
    // Weather
    var weather: WeatherType = WeatherType.SUNNY,
)
