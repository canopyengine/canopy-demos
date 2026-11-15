package onboarding.aquarium.terminal.nodes

import io.canopy.engine.core.nodes.Node2D

class Aquarium : Node2D<Aquarium>("Aquarium") {

    val nFish by computed {
        getChildrenOfType<Fish>().size
    }

    val x = 100
}
