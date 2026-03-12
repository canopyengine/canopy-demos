package io.github.canopy.demos.ecosystem.world.input

import io.canopy.engine.core.nodes.Node


class CommandHandler(block : CommandHandler.() -> Unit = {}) : Node<CommandHandler>("CommandInput", block)