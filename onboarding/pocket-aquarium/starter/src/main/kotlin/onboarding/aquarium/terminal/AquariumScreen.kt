package onboarding.aquarium.terminal

import io.canopy.engine.app.Screen
import io.canopy.engine.core.nodes.types.empty.EmptyNode
import io.canopy.engine.core.queries.ancestor
import io.canopy.engine.ui.Anchor
import io.canopy.engine.ui.Canvas
import io.canopy.engine.ui.CrossAlign
import io.canopy.engine.ui.Insets
import io.canopy.engine.ui.Place
import io.canopy.engine.ui.Size


class AquariumScreen : Screen() {

    override fun onEnter() {

        EmptyNode("Root") {

            Canvas(name = "Hud") {
                Text(
                    "Score: 0",
                    place = Place.At(
                        Anchor.TopRight,
                        inset = Insets(2.0)
                    )
                )

                Column(
                    place = Place.At(Anchor.Center),
                    size = Size.Content,
                    align = CrossAlign.Center,
                    gap = 1.0
                ) {
                    Text("Paused")
                    Button("Resume", onClick = {
                        // Call your game's resume action.
                        val canvas by ancestor<Canvas<*>>()

                        canvas.toggleVisibility()


                    })
                }
            }


        //Aquarium()
        }.asSceneRoot()

    }

}
