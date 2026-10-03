package io.github.canopy.demos.ecosystem.world.input

import io.canopy.engine.core.nodes.Node

/** Placeholder for command handling; no input parsing or command dispatch is implemented yet. */
class CommandHandler(block: CommandHandler.() -> Unit = {}) : Node<CommandHandler>("CommandInput", block = block)
