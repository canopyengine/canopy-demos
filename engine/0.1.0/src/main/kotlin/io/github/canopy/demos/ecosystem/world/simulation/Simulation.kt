package io.github.canopy.demos.ecosystem.world.simulation

import io.canopy.engine.core.nodes.core.Node
import io.canopy.engine.core.nodes.core.behavior
import io.canopy.engine.core.reactive.lazyResolve
import io.canopy.engine.logging.logger

class Simulation(
    block: Simulation.() -> Unit = {},
) : Node<Simulation>("name", block){

    val data by lazyResolve<SimulationData>("data")
    val logger = logger("Simulation")

    override fun create() {
        behavior(
            onReady = {
                logger.info("data" to data){"Ready"}
            }
        )
    }
}
