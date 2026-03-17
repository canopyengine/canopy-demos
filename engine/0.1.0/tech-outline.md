# 🌿 Ecosystem Simulation — Contributor Overview (v0.1.0)

This document defines the **scope and core concepts** of the 0.1.0 tech demo.

The goal is to deliver a **small, believable, and observable ecosystem simulation** that showcases Canopy’s capabilities.

---

## 🎯 Goal

Create a **live, terminal-based ecosystem simulation** where:

* Agents behave autonomously
* The environment influences outcomes
* Events form a readable narrative
* Users can observe and lightly interact

> This is a **tech demo**, not a full simulation system.

---

## 🧩 Core Components

The simulation is built from three systems:

1. **Agents** — entities with behavior
2. **Environment** — world state and resources
3. **Simulation Loop** — time and progression

---

## 🐾 Agents

Agents are **autonomous entities** driven by simple internal state.

### Types

* **Deer** → passive, survival-focused
* **Wolves** → aggressive predators

---

### 🧠 Needs vs Drives

Agents make decisions using two layers:

#### Needs (long-term)

Core survival metrics:

* Hunger
* Thirst
* Energy
* Safety

---

#### Drives (short-term)

Contextual modifiers:

* Fear
* Aggression

---

> Needs define *priorities*.
> Drives influence *immediate reactions*.

---

### 🎯 Behavior Model

Agents use **simple rule-based decision-making**:

* Satisfy the most critical need
* React to nearby threats or opportunities
* Adjust behavior based on environment (time, weather)

No advanced AI, planning, or learning systems are included in 0.1.0.

---

## 🌍 Environment

The environment provides **resources and constraints**.

### Key Elements

* **Food Sources**

    * Regenerate over time
    * Limited availability

* **Water Sources**

    * Shared between agents

* **Cover**

    * Areas that improve safety (e.g. vegetation)

---

## 🌦️ Global Conditions

Global conditions affect both **visibility and behavior**.

### Time of Day

* Morning
* Dusk
* Night

---

### Weather & Seasons

A small set of conditions such as:

* Clear

* Rain

* Storm

* Seasons (e.g. spring, winter) act as simple modifiers

---

> These are **lightweight modifiers**, not complex systems.

---

## ⏱️ Simulation Model

### Time Units

* **Tick = 1 hour**

### Structure

* Day → 9 hours
* Week → 4 days
* Month → 4 weeks
* Year → 9 months

This compressed scale ensures **frequent, observable changes**.

---

## 📡 Event System

The simulation produces **human-readable events**.

### Principles

1. Only meaningful events are shown
2. Repetitive events are grouped
3. Quiet periods are summarized

---

### Example

```text
[Day 2 | Morning]

- A deer is grazing
- A wolf is patrolling nearby
```

---

### Aggregation Example

```text
- 3 deer are grazing
```

---

### Quiet Period Example

```text
[Day 1 → Day 4]

- The days pass quietly across the biome
```

---

> Events should feel like a **live narrative**, not debug logs.

---

## 🎮 Interaction Layer

The simulation is **watch-first**, with optional interaction.

---

### 👀 Watch Mode (Default)

* Simulation runs continuously
* Events stream in real time
* No persistent input UI

---

### ⌨️ Command Prompt

Opened with:

```text
space
```

Example:

```text
[Day 3 | Dusk] > weather storm
```

---

### 🧩 Commands (0.1.0 Scope)

#### Time

```bash
time forward <amount>
pause
resume
```

---

#### Environment

```bash
weather <type>
season <type>
```

---

#### Inspection

```bash
list agents
inspect <id>
status
summary
history
```

---

#### Events (Limited)

```bash
event <type>
```

> Only a small set of predefined events is supported.

---

### ⚡ Interaction Model

* Commands are **temporary overlays**
* Simulation pauses while typing
* Results are **merged into the event stream**

---

## 🖥️ Output Principles

The terminal is the **primary interface**.

### Goals

* Readable at a glance
* Narrative-style output
* Minimal noise
* Consistent formatting

---

### Example

```text
[Day 3 | Dusk]

- A wolf spots a deer
- The deer begins to flee
- Rain begins to fall
```

---

Yes — that should be included, but kept **very narrow** for 0.1.0.

You do not want “full save system” scope.
You want **deterministic world persistence**.

That means the goal is:

> load the same world setup again, and optionally restore a simulation snapshot.

For the scope doc, I’d add a section like this:

---

## 💾 Persistence & Seeded Worlds

The simulation supports **basic persistence** to ensure reproducibility and continuity.

This is primarily intended for:

* reloading the same generated world
* continuing a previous simulation
* reproducing interesting scenarios during testing

---

### 🌱 Seeded World Generation

World generation is deterministic.

A world seed controls the initial setup, including:

* resource placement
* agent spawn positions
* initial world conditions

Using the same seed should produce the same starting world configuration.

> Seeds are intended to make simulations reproducible for testing, debugging, and demo sharing.

---

### 🗂️ Save Data

Persistence is split into two levels:

#### World Seed / Initial State

Stores the information required to recreate the initial world:

* seed
* world settings
* initial environment conditions
* initial agent setup

This allows the simulation to restart from the same baseline.

---

#### Simulation Snapshot

Stores the current state of a running simulation, such as:

* current time
* active world conditions
* agent states
* resource states
* recent simulation state needed to resume execution

This allows the simulation to continue from a previously saved point.

---

### 🎯 0.1.0 Scope

Persistence in 0.1.0 is intentionally limited.

Supported goals:

* create a world from a known seed
* save the current simulation state
* load a saved simulation state
* reproduce the same starting world for testing

Not required in 0.1.0:

* version migration between incompatible save formats
* partial save editing
* multiple save slots UI
* replay system
* branching timelines
* full historical reconstruction

---

### 🧱 Design Principles

* **Deterministic by default**
  The same seed should recreate the same initial world

* **Simple and inspectable**
  Save data should be easy to understand and debug

* **Practical over exhaustive**
  Persistence should support demo and development needs, not full production save management

* **Stable enough for iteration**
  Saves should help contributors reproduce bugs and scenarios reliably

---

### 🧪 Use Cases

Examples of intended usage:

* rerun the same world seed to compare behavior changes
* save an interesting scenario and resume it later
* share a seed that produces a notable predator-prey interaction
* preserve simulation state during development and testing

---

### 📌 Notes

Persistence should be designed around the simulation’s core data model, not around terminal output.

Logs and rendered event text are considered **derived output** and do not need to be stored as primary save data.

This keeps it useful without exploding scope.

---

## 🧭 Design Principles

* **Autonomy first**
  The world runs meaningfully without input

* **Emergence over scripting**
  Behavior arises from systems

* **Clarity over complexity**
  Prefer simple, understandable systems

* **Watch-first experience**
  Output is the main product

* **Fast iteration**
  Short cycles, quick feedback

---

### 🚧 Out of Scope (0.1.0)

To keep the demo focused, the following are explicitly excluded:

* Additional species
* Reproduction systems
* Advanced AI / learning
* Complex pathfinding
* Inventory or crafting systems
* Large-scale world simulation
* GUI / TUI interfaces
* Replay systems
* Save migration/version compatibility guarantees
* Time rewind

---

## 🧪 Success Criteria

The demo is successful if:

* The simulation runs autonomously
* Deer and wolves behave distinctly
* Environment changes affect outcomes
* Events form a clear, readable narrative
* Users can influence the simulation meaningfully
* The system remains stable over continuous runtime

---

## 🧭 Summary

This tech demo is:

> A small, reactive ecosystem simulation that showcases Canopy’s ability to model autonomous systems and present them as a clear, live narrative in the terminal.
