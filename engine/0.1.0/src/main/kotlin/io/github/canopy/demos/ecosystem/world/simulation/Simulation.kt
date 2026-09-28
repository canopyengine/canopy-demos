//package io.github.canopy.demos.ecosystem.world.simulation
//
//import io.canopy.engine.core.flows.lazyFromContext
//import io.canopy.engine.core.nodes.Node
//import io.canopy.engine.core.nodes.behavior
//import io.canopy.engine.logging.logger
//
//class Simulation(
//    block: Simulation.() -> Unit = {},
//) : Node<Simulation>("name", block = block){
//
//    val data by lazyFromContext<SimulationData>("data")
//    val logger = logger("Simulation")
//
//    override fun create() {
//        behavior(
//            onReady = {
//                logger.info("data" to data){"Ready"}
//            }
//        )
//    }
//}
