package onboarding.aquarium.terminal.nodes

import io.canopy.engine.core.nodes.Node2D
import io.canopy.engine.core.queries.ancestor

class Fish : Node2D<Fish>("Fish") {
    val aquarium by ancestor<Aquarium>()


}
