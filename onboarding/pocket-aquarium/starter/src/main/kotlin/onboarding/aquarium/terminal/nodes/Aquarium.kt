package onboarding.aquarium.terminal.nodes

import io.canopy.engine.core.nodes.Node2D
import io.canopy.engine.graphics.GridSymbol
import io.canopy.engine.input.binds.Key
import io.canopy.engine.input.events.InputEvent
import io.canopy.engine.input.events.InputState
import io.canopy.engine.input.events.KeyInputEvent
import io.canopy.engine.ui.Arrange
import io.canopy.engine.ui.CrossAlign
import io.canopy.engine.ui.GridLines
import io.canopy.engine.ui.LinePattern
import io.canopy.engine.ui.LineStyle
import io.canopy.engine.ui.Size
import io.canopy.engine.ui.UiAppearance
import io.canopy.engine.ui.UiColor
import io.canopy.engine.ui.UiLength
import io.canopy.engine.ui.UiStyle
import io.canopy.engine.ui.components.Column
import io.canopy.engine.ui.components.Grid
import io.canopy.engine.ui.components.Text

/**
 * The root of the game scene. Canopy calls onSetup once, sends input to
 * onInput, and calls onUpdate every frame.
 */
class Aquarium(private val onQuit: () -> Unit) : Node2D<Aquarium>("Aquarium") {
    private companion object {
        const val WIDTH = 58
        const val HEIGHT = 12
        const val STARTING_FOOD = 6
        const val STEP_SECONDS = 0.05f
        const val FISH_MOVE_EVERY_STEPS = 2
        const val START_MESSAGE = "Aim with Left / Right, then press Space to feed a fish."

        val TEXT_COLOR = UiColor.Rgb(244, 229, 206)
        val WATER_COLOR = UiColor.Rgb(105, 220, 186)
        val PLANT_COLOR = UiColor.Rgb(108, 210, 123)
        val PLANT_COLUMNS = listOf(6, 25, 49)
    }

    // The fish are child nodes, so we can ask Canopy for them when needed.
    private val fish get() = getChildrenOfType<Fish>()

    private var aimColumn = WIDTH / 2
    private var foodRemaining = STARTING_FOOD
    private var foodColumn = 0
    // null means that no food is currently falling.
    private var foodRow: Int? = null
    private var timeSinceStep = 0f
    private var stepCount = 0
    private var statusMessage = START_MESSAGE

    // These UI nodes are created in onSetup, so their references start empty.
    private var aquariumGrid: Grid<*>? = null
    private var scoreLabel: Text<*>? = null
    private var statusLabel: Text<*>? = null

    override fun onSetup() {
        addFish()
        buildUi()
        drawGame()
    }

    private fun addFish() {
        addChild(Fish(row = 3, startingColumn = 8, color = UiColor.Rgb(255, 202, 101)))
        addChild(
            Fish(
                row = 6,
                startingColumn = 39,
                color = UiColor.Rgb(255, 136, 134),
                startsMovingRight = false
            )
        )
        addChild(Fish(row = 9, startingColumn = 26, color = UiColor.Rgb(124, 225, 209)))
    }

    private fun buildUi() {
        // The Column becomes a child of Aquarium. Text and Grid become children
        // of the Column, so Canopy can lay out the whole screen.
        // Fill uses the terminal's space; Center places the content inside it.
        Column(
            style = UiStyle(
                width = UiLength.Fill,
                height = UiLength.Fill,
                appearance = UiAppearance(
                    foreground = TEXT_COLOR,
                    background = UiColor.Rgb(4, 15, 30)
                )
            ),
            arrange = Arrange.Center,
            align = CrossAlign.Center
        ) {
            Text("POCKET AQUARIUM").apply {
                style = style.copy(appearance = UiAppearance(bold = true))
            }
            Text("Feed all ${fish.size} fish before your food runs out.")
            scoreLabel = Text("").apply {
                style = style.copy(appearance = UiAppearance(bold = true))
            }
            // Each Grid cell is one terminal character wide and high.
            aquariumGrid = Grid(
                columns = WIDTH,
                rows = HEIGHT,
                cellSize = Size(UiLength.Fixed(1.0), UiLength.Fixed(1.0)),
                lines = GridLines(outer = LineStyle(LinePattern.Single, WATER_COLOR))
            )
            Text("Left / Right  Aim     Space  Drop food     R  Restart     Q  Quit")
            statusLabel = Text("")
        }
    }

    override fun onInput(event: InputEvent) {
        // A key can produce press, repeat, and release events. Handle only
        // the first press so one tap drops just one piece of food.
        if (event !is KeyInputEvent || event.state != InputState.JustPressed) return

        when (event.key) {
            Key.LEFT -> aimColumn = (aimColumn - 1).coerceAtLeast(0)
            Key.RIGHT -> aimColumn = (aimColumn + 1).coerceAtMost(WIDTH - 1)
            Key.SPACE -> dropFood()
            Key.R -> restart()
            Key.Q -> {
                event.consume()
                onQuit()
                return
            }
            else -> return
        }
        // Mark keys we used as handled, then show the new state.
        event.consume()
        drawGame()
    }

    override fun onUpdate(delta: Float) {
        if (gameIsOver()) return

        // delta is the time since the last frame. Small, fixed steps keep the
        // fish and food at the same speed on fast and slow terminals.
        timeSinceStep += delta.coerceAtMost(0.2f)
        var gameChanged = false
        while (timeSinceStep >= STEP_SECONDS && !gameIsOver()) {
            timeSinceStep -= STEP_SECONDS
            stepCount++

            if (stepCount % FISH_MOVE_EVERY_STEPS == 0) {
                fish.forEach { it.swim(WIDTH) }
                gameChanged = true
            }
            if (foodRow != null) {
                moveFoodDown()
                gameChanged = true
            }
        }
        if (gameChanged) drawGame()
    }

    private fun moveFoodDown() {
        val nextRow = (foodRow ?: return) + 1
        foodRow = nextRow

        val hungryFish = fish.firstOrNull { it.canEatAt(foodColumn, nextRow) }
        if (hungryFish != null) {
            hungryFish.feed()
            foodRow = null
            val hungryFishLeft = fish.size - fedCount()
            statusMessage = if (hungryFishLeft == 0) "All fish fed! Press R to play again."
                else "Nice shot! $hungryFishLeft hungry fish to go."
        } else if (nextRow >= HEIGHT) {
            foodRow = null
            statusMessage = if (foodRemaining == 0) "Out of food! Press R to try again."
                else "Missed! $foodRemaining food left."
        }
    }

    private fun dropFood() {
        if (gameIsOver() || foodRow != null) return

        foodRemaining--
        foodColumn = aimColumn
        // Row zero holds the aim marker, so food starts just below it.
        foodRow = 1
        statusMessage = "Food is falling..."
    }

    private fun restart() {
        fish.forEach(Fish::reset)
        aimColumn = WIDTH / 2
        foodRemaining = STARTING_FOOD
        foodColumn = 0
        foodRow = null
        timeSinceStep = 0f
        stepCount = 0
        statusMessage = START_MESSAGE
    }

    private fun fedCount() = fish.count { it.fed }

    // The last piece of food may still feed a fish, so wait for it to land.
    private fun gameIsOver() = fedCount() == fish.size || (foodRemaining == 0 && foodRow == null)

    private fun drawGame() {
        val grid = aquariumGrid ?: return
        scoreLabel?.text =
            "FISH FED  ${fedCount()} / ${fish.size}       FOOD  $foodRemaining / $STARTING_FOOD"
        statusLabel?.text = statusMessage

        // Grid cells use [column, row], starting at the top-left corner.
        // Clear the old picture before drawing the current game state.
        grid.fill(' ')
        drawWater(grid)
        drawPlants(grid)
        fish.forEach { drawFish(grid, it) }

        grid[aimColumn, 0] = GridSymbol('+', TEXT_COLOR)
        foodRow?.let { row ->
            grid[foodColumn, row] = GridSymbol('*', TEXT_COLOR)
        }
    }

    private fun drawWater(grid: Grid<*>) {
        for (row in 0 until HEIGHT step 2) {
            for (column in 1 until WIDTH step 3) {
                grid[column, row] = GridSymbol('.', WATER_COLOR)
            }
        }
    }

    private fun drawPlants(grid: Grid<*>) {
        for (column in PLANT_COLUMNS) {
            drawText(grid, column, HEIGHT - 2, "/|\\", PLANT_COLOR)
            drawText(grid, column, HEIGHT - 1, "/|\\", PLANT_COLOR)
        }
    }

    private fun drawFish(grid: Grid<*>, swimmer: Fish) {
        grid[swimmer.column + 3, swimmer.row - 1] = GridSymbol('o', TEXT_COLOR)
        val color = if (swimmer.fed) PLANT_COLOR else swimmer.color
        drawText(grid, swimmer.column, swimmer.row, swimmer.sprite, color)

        if (swimmer.fed) {
            val label = "[fed]"
            val labelColumn = swimmer.column + swimmer.sprite.length + 1
            if (labelColumn + label.length <= WIDTH) {
                drawText(grid, labelColumn, swimmer.row, label, PLANT_COLOR)
            }
        }
    }

    private fun drawText(grid: Grid<*>, column: Int, row: Int, characters: String, color: UiColor) {
        // A GridSymbol pairs one character with its display color.
        for ((offset, character) in characters.withIndex()) {
            val cellColumn = column + offset
            if (cellColumn in 0 until WIDTH) {
                grid[cellColumn, row] = GridSymbol(character, color)
            }
        }
    }
}
