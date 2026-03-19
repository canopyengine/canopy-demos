# 🌿 Ecosystem Simulation — Tech Demo (v0.1.0)

A minimal, terminal-based ecosystem simulation focused on **clarity, autonomy, and observable behavior**.

---

## 🎯 Goal

Create a small simulation where:

* Agents act autonomously
* The environment affects outcomes
* Events form a clear, readable narrative

> This is a **tech demo**, not a full system.

---

## 🧩 Core Systems

The simulation consists of:

1. **Agents** — entities with simple behavior
2. **Environment** — resources and conditions
3. **Simulation Loop** — time progression

---

## 🐾 Agents

### Types

* **Deer**

  * Passive
  * Seek food and water
  * Flee when threatened

* **Wolves**

  * Predators
  * Hunt deer for food

---

### 🧠 Decision Model (Simplified)

Agents use a **priority + override system**:

1. Choose the most critical need:

  * Hunger
  * Thirst
  * Energy

2. Override with **fear** if danger is nearby:

  * Deer flee from wolves
  * Wolves may chase prey

> No planning, learning, or complex AI.

---

### ⚙️ Behavior Rules

* Agents move randomly when idle
* Agents interact only with nearby entities
* Agents lose energy over time
* Agents die when energy reaches zero

---

## 🌍 Environment

### World Model

* Simple **2D grid**
* Agents occupy positions
* Interactions occur within a fixed radius

---

### Resources

* **Food**

  * Limited
  * Regenerates slowly

* **Water**

  * Fixed locations
  * Shared by all agents

* **Cover**

  * Reduces chance of being detected

---

## 🌦️ Conditions (Minimal)

Only two environmental modifiers:

### Time of Day

* Day
* Night

Effect:

* Night reduces visibility

---

### Weather

* Clear
* Rain

Effect:

* Rain reduces movement speed

---

## ⏱️ Simulation Model

* **Tick = 1 hour**

Time structure:

* 1 day = 9 ticks

---

### Tick Flow

Each tick:

1. Update environment
2. Update all agents (sequentially)
3. Resolve interactions (e.g. hunting)
4. Generate events

---

## 📡 Event System (Minimal)

The simulation outputs **readable events**.

---

### Rules

* Only meaningful events are shown
* Identical events may be grouped

---

### Examples

```
[Day 1 | Day]

- A deer is grazing
- A wolf spots a deer
- The deer flees
```

```
- 3 deer are grazing
```

---

### Not Included (0.1.0)

* Quiet period summaries
* Complex narration
* Event history storage

---

## 🎮 Interaction

### Mode

* Simulation runs automatically
* User can pause and enter commands

---

### Commands (Minimal Set)

#### Time

```
pause
resume
```

---

#### Environment

```
weather rain
weather clear
time day
time night
```

---

#### Inspection

```
list agents
inspect <id>
status
```

---

### Behavior

* Simulation pauses while entering commands
* Commands apply immediately
* Output continues in event stream

---

## 💾 Persistence (Minimal)

### Supported

* Start simulation with a **seed**
* Same seed → same initial world

---

### Not Included

* Replay system
* Versioned saves

---

## 🧭 Design Principles

* **Simple over complex**
* **Observable over realistic**
* **Deterministic where possible**
* **Autonomous by default**

---

## 🚧 Out of Scope

* Additional species
* Reproduction
* Advanced AI
* Complex pathfinding
* Inventory or crafting
* Large worlds
* GUI / TUI
* Full persistence

---

## 🧪 Success Criteria

The demo is successful if:

* The simulation runs without input
* Deer flee from wolves
* Wolves hunt deer
* Environment affects behavior (e.g. night, rain)
* Events are easy to read and understand

---

## 🧭 Summary

> A small, deterministic ecosystem simulation that produces a clear, live narrative in the terminal.
