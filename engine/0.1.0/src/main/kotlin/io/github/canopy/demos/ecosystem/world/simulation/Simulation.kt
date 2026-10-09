package io.github.canopy.demos.ecosystem.world.simulation

import io.canopy.engine.core.flows.fromContext
import io.canopy.engine.core.nodes.Node
import io.canopy.engine.core.nodes.behavior
import io.canopy.engine.logging.logger

/** Resolves shared simulation configuration; gameplay stepping remains to be implemented. */
class Simulation(block: Simulation.() -> Unit = {}) : Node<Simulation>("Simulation", block = block) {

    val data: SimulationData
        get() = fromContext("data")
    val logger = logger("Simulation")

    override fun nodeInit() {
        behavior(
            onReady = {
                logger.info("data" to data) { "Ready" }
            }
        )
    }
}
