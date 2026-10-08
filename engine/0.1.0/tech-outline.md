# Ecosystem simulation: 0.1.0 technical outline

## Status and purpose

This is the agreed gameplay scope, not a description of implemented features.
The [current scaffold](README.md) loads configuration and constructs placeholder
nodes against a pinned Canopy 0.1.0-dev2 snapshot. It does not yet implement
agents, commands, a calendar, resource interactions or a rendered ecosystem.
Migration to the integrated engine is an implementation task; this outline does
not change the dependency pin or configuration schema.

The demo should validate a terminal-ready engine through a small autonomous
world that players influence with commands. Use existing engine systems for
lifecycle, reactive state, terminal input and declarative UI. Keep ecological
rules in the demo unless implementation reveals a reusable engine capability.

## World and resources

Use a bounded, small two-dimensional grid with rabbits and foxes. Grid edges
prevent movement outside the world; movement and proximity queries do not need
a physics backend. Multiple agents may share a cell so crowding does not require
collision resolution or pathfinding.

- Grass patches supply finite food for rabbits and regenerate slowly to a cap.
- Fixed river cells supply water to both species without depletion in this slice.
- Trees supply cover from the start. An agent in cover has a reduced detection
  radius against it; cover does not create impassable terrain or require navigation.
- Foxes obtain food by catching rabbits; there is no separate fox food resource.

Night reduces visibility. Cover applies an additional deterministic detection
modifier. A nearby predator may still detect a rabbit in cover; cover is not
absolute protection. Rain reduces movement frequency using a deterministic
cooldown, rather than fractional coordinates or an extra movement system.

Grid dimensions, starting counts, resource caps, detection radii and cooldowns
are proposed tunable parameters. Initial balancing should keep both species
observable for several days, without promising indefinite population stability.

## Agent decisions and interactions

Each living agent has hunger, thirst and energy. Hunger and thirst increase with
ticks; energy is spent by activity and restored by resting. Zero energy makes an
agent rest rather than die immediately. Death occurs on a successful hunt or
when a tunable starvation/dehydration threshold is reached.

Rabbits first flee a detected nearby fox, then satisfy their most urgent need:
drink at water, eat available grass, or rest to recover energy. Foxes seek water
when thirst is most urgent, hunt detected rabbits when hungry, and rest when
energy is low. Otherwise agents wander one neighboring cell using seeded
randomness. Break equal-distance and equal-priority choices in a stable order.
A thirsty fox is not required to pursue every rabbit it encounters.

Keep hunting simple: after movement, an eligible fox sharing a cell with a live
rabbit catches one rabbit, removes it once, and reduces the fox's hunger. Resolve
competing foxes and prey by stable agent IDs. A fox cannot consume the same prey
again or take multiple prey in one tick. Fleeing moves away from a detected fox
when a legal neighboring step is available; there is no planning or learning.

These needs, thresholds and hunt rules are proposed implementation defaults,
not final balance values. Cover and rain must affect observable outcomes without
introducing complex terrain, probabilistic hit systems or advanced AI.

## Clock and weather

One tick advances one day phase, not one hour. A day consists of nine ticks:

| Tick within day | Phase | Visibility |
| --- | --- | --- |
| 1 | Dawn | Day |
| 2 | Early morning | Day |
| 3 | Late morning | Day |
| 4 | Midday | Day |
| 5 | Afternoon | Day |
| 6 | Dusk | Day |
| 7 | Early night | Night |
| 8 | Midnight | Night |
| 9 | Late night | Night |

After late night, advance to dawn of the next day. Configure the real-time
interval between ticks separately from ecological rates and phase definitions.
Pausing stops simulation ticks while input and rendering continue.

The initial weather scope is clear and rain. Weather changes naturally using a
seeded, configurable schedule. `weather clear` or `weather rain` sets the current
condition at the command boundary, then starts its normal dwell period; natural
changes continue afterwards. No permanent weather lock is implied. `time day`
moves to early morning and `time night` to early night, keeping the current day
number; subsequent ticks resume the normal phase sequence.

The committed scaffold currently accepts `SUNNY`, `SNOWY`, `RAINY` and `STORMY`.
Implementing this outline requires mapping `SUNNY` to clear and `RAINY` to rain,
and defining a clear migration/validation policy for snow and storm. They are
not additional planned simulation conditions.

## Tick ordering and determinism

Use a seeded world-local random source and stable iteration by agent/resource ID.
At a tick boundary:

1. Apply queued state-changing commands in arrival order.
2. Advance the phase, natural weather and grass regeneration.
3. Update needs, choose actions and move living agents in stable order.
4. Resolve drinking, feeding, resting and hunting in a defined stable order.
5. Resolve threshold deaths and remove dead agents exactly once.
6. Publish state and meaningful events to the UI.

A dead agent cannot act later in the same tick. Newly removed agents must not
invalidate iteration. Sequential update order is an explicit rule of this small
demo, rather than a claim that all actions happen simultaneously.

The same seed, configuration and tick-indexed ordered command sequence must
produce the same simulation state and events. Real-time typing or scheduler
speed alone is not a deterministic input. This is a testability contract, not a
replay or recording feature. Pause/resume and quit are immediate control commands;
while paused, environment commands apply in order without advancing a tick.

## Terminal interaction and minimum UI

The simulation runs automatically. A bottom command overlay captures typed
command input while open, preventing typed keys from triggering gameplay actions.
Simulation continues while typing. Players may opt into pause-on-open; closing
the overlay then restores the prior running/paused state rather than overriding
an explicit pause. `pause` and `resume` remain available regardless of that option.

Use the minimum shared declarative UI for a compact world/status view, bounded
recent events and command-editor focus. Layout and sizes adapt to terminal
resizing, including a readable compact presentation in small terminals. Show day,
phase, weather, running/paused state and rabbit/fox counts. Inspection exposes
agent ID, position, current action and needs. A sophisticated map renderer is not
a prerequisite for understanding the world.

| Command | Behavior |
| --- | --- |
| `pause` / `resume` | Stop or restart ticks explicitly |
| `weather clear` / `weather rain` | Set weather now; natural evolution continues |
| `time day` / `time night` | Set early morning/early night; the clock continues |
| `list agents` | List living agents with stable IDs and species |
| `inspect <id>` | Show one agent's state or a clear missing-ID message |
| `status` | Show environment, population and simulation state |
| `quit` | Exit through normal engine cleanup |

Commands report invalid arguments clearly. Inspection reads current state;
state-changing commands use the boundary described above so updates do not race
agent iteration. No general scripting language is required.

## Events

Emit useful transitions: detection, fleeing, a successful hunt, death, weather
changes and command results. Avoid emitting identical idle activity every tick.
Keep a bounded in-memory recent-event list for the UI; persistent event history,
replay files, quiet-period summaries and a narration framework are out of scope.

For example:

```text
Day 2 · Dusk · Rain · Running
Rabbits: 5 · Foxes: 2
Rabbit #4 retreats into cover.
Fox #7 catches rabbit #2.
```

## Provisional launch and build/deployment targets

These are planning targets, not measured Canopy performance or established Unity/Godot
benchmarks. The game remains a scaffold; validate the targets with the implemented
slice before claiming they are met.

For launch, assume a modern laptop with an SSD, a bundled JVM, local assets and no
startup network request. Measure player process start to the first usable screen,
excluding installation and Gradle compilation. Target **0.5–2 seconds**. The initial
component budget is JVM/class loading 150–600 ms, engine/logging/input 50–200 ms,
world/assets 100–500 ms and at most 17 ms to the first frame at 60 FPS: a theoretical
317–1,317 ms sum, with margin in the launch target.

For build/deployment, assume cached tooling/dependencies, an available CI worker,
150 MB of game content plus an assumed 80 MB bundled runtime, and effective upload
throughput of 10 MB/s. These sizes are an illustrative comparison workload, not a
required size for this terminal demo or a measured Canopy distribution size.

| Stage | Initial budget |
| --- | ---: |
| Build | 60 seconds |
| Tests | 90 seconds |
| Package | 10 seconds |
| Upload: 230 MB / 10 MB/s | 23 seconds |
| Total | 183 seconds (about 3 minutes) |

The deployment budget excludes CI queueing, manual approval, signing and store
processing. Cold builds also download dependencies and initialize tooling. Keep
build/package time separate from tests and upload when comparing engines.

The final comparative target is to match or beat comparable Unity and Godot
build/package times on the same hardware, target platform and representative
workload, with equivalent cache conditions. Project size alone is insufficient:
assets, code, scripting backend and packaging differ. No numerical competitor
baseline has been measured yet, so the provisional 60-second build and 10-second
package budgets are not a claim of parity. A terminal-only demo cannot establish
performance parity for graphical indie games.

## Completion checks

- A seeded world runs autonomously with grass, water and cover from the first slice.
- Rabbits feed, drink, rest and flee; foxes drink, rest and hunt; deaths release nodes.
- Day phases, clear/rain and cover alter the intended behavior observably.
- Identical deterministic inputs reproduce state and event sequences.
- Commands work while ticks continue; explicit pause/resume and optional
  pause-on-open preserve the intended simulation state.
- Status, events and command focus remain usable after terminal resizing.
- Invalid commands and inspection of removed agents are safe; quit cleans up normally.
- A fresh project can build against the chosen matching engine/compiler artifacts.

## Outside this release

Additional species, reproduction, large worlds, complex pathfinding, learning,
inventory/crafting, desktop UI, advanced TUI widgets, physics, audio, durable
saves, replay and a general narration system are outside this slice. A seed is
startup configuration, not persistence. Do not expand the engine just to model
features the initial ecosystem does not need.
