package onboarding.aquarium.terminal.nodes

import io.canopy.engine.core.nodes.Node2D
import io.canopy.engine.ui.UiColor

/**
 * Each fish is a Canopy node in the Aquarium scene. It keeps its own game
 * state; the Aquarium draws that state into a terminal Grid.
 */
class Fish(
    val row: Int,
    private val startingColumn: Int,
    val color: UiColor,
    private val startsMovingRight: Boolean = true
) : Node2D<Fish>() {
    var column = startingColumn
        private set

    var fed = false
        private set

    private var movingRight = startsMovingRight

    val sprite: String
        get() = if (movingRight) "><(((o>" else "<o)))><"

    fun swim(aquariumWidth: Int) {
        if (fed) return

        column += if (movingRight) 1 else -1
        // Leave one cell between the sprite and the aquarium border.
        if (column <= 1) movingRight = true
        if (column + sprite.length >= aquariumWidth - 1) movingRight = false
    }

    // The food reaches this fish when it lands on the fish's row and sprite.
    fun canEatAt(foodColumn: Int, foodRow: Int): Boolean =
        !fed && row == foodRow && foodColumn in column until column + sprite.length

    fun feed() {
        fed = true
    }

    fun reset() {
        column = startingColumn
        movingRight = startsMovingRight
        fed = false
    }
}
