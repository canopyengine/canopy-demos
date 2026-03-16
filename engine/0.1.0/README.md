# 0.1.0 Tech Demo - Ecosystem Simulation

A terminal-based ecosystem simulation showcasing semi-autonomous agents, resource dynamics, and emergent behavior, with 
interactive commands that allow users to influence the world in real time.

## Goals for this tech demo

This tech demo aims to test Canopy fundamental systems, such as the Node and Flow Systems, interactive runtimes, real time
input handling and dynamic text with color support.

> [!NOTE]
> If you're looking for the technical outline, you can check it [here](/tech-outline.md)

---

# Ecosystem

The ecosystem is made of three main components:

## Agents

These are ``semi-autonomous`` entities that, via an algorithm, will interact with the rest of the environment with full
autonomy. These are categorized into:

**Deer**

Agents that feed on resources found across the biome, such as grass patches and water sources. 

Their behavior is mostly passive and survival-oriented, focusing on grazing, roaming, resting, and avoiding predators. 

When threatened, deer tend to flee and seek safer areas with cover or proximity to other deer.

**Wolves**
Agents that prey on deer and are aggressive by default. Their behavior is centered around patrolling, tracking prey, 
hunting, resting, and competing for survival. 

Wolves act as the biome’s main predators and may also display pack-oriented behavior when near other wolves.

**Needs and Drives**
Each agent has a group of ``needs`` and ``drives`` which influence how they react and act:

* **reproductive** - does the agent think it should reproduce?
* **hunger** - does the agent need food? - oscillates during the day, and low levels may result in starvation, which can cause the agent to ``die``.
* **thirst** - does the agent need water? - works similar to ``hunger`` and must be filled, or the agent can die dehydration.
* **energy** - how tired is the agent?
* **safety** - is the agent safe?
* **temperature** - do the agents need to cooldown?

Other than **needs**, there are some other major **drives** that have a more short-term influence on agents.

* **fear** - this increases on situations that may result in the agent's ``death``, and promotes actions such as hiding.
* **aggressive** - how much does the agent tend to aggressive actions?
* **social** - should the agent collaborate with other agents?
* **curiosity** - should the agent explore? 

## Environmental Features that Impact Agents
These features have a direct or indirect on agents.

### Physical features

**Food Sources**

* resources that ``deer`` can use to feel their ``hunger`` need
* regenerate over time
* seasonal available
* limited quantity

**Water Sources**

* areas where agents can drink to satisfy ``thirst``
* agent crowding
* seasonal drying

**Trees**

* serve as a temporary shelter for agents
* still not as safe as ``burrows``
* more available than ``burrows``

**Vegetation Cover**

* serve as shelter for ``agents``
* territory ownership
* reproductory location

### Weather and Meteorological Conditions

Short-term environmental factors that affect the whole biome

* seasons (spring/summer/winter)
* rain / thunderstorms
* fog

### Time of Day
Agents may behave differently depending on time: day, dusk, night

---

# Simulation flow

## Milestones

The atomic milestone of the simulation is a ``hour`` - corresponds to a ``tick``. The remaining higher-order milestones are built on top of ``hours``:

* ``Day`` - 9 hours (3 morning, 3 dusk, 3 night)
* ``Week`` - 4 days
* ``Month`` - 4 weeks
* ``Year`` - 9 months(3 spring, 3 summer, 3 winter)

Each simulated ``year`` results in:
* ``1296`` simulated hours.
* ``144`` simulated days.
* ``36`` simulated weeks

> [!NOTE]
> The number of hours per day days per week and months per year are reduced as to make the simulations flow more quickly.

## Simulation summaries

If not prompted by the user, the simulation will run autonomously and log important events at the end of a milestone, with the following behavior:

1. If at least a relevant event happens in a ``day`` - show it in the summary.
2. If nothing happens, keeps simulating. If something relevant is logged before it reaches the next ``simulation`` milestone, log each consecutive milestone as a list.
3. If the next ``milestone`` is reached
   * Something relevant happened - log it with only the high-order ``milestone``
   * Nothing happened - repeat step 2 but using the high-order.

> [!NOTE]
> Events are marked as ``relevant`` by a separate algorithm

Examples:

**Something relevant happened in day 5 - log it**

````md
[Day 5 Summary]
- Daniel(Deer) cried
````

**Nothing relevant happened for 2 days in a row, but then something happened in day 8**

````
[Day 6 Summary] Nothing relevant happened

[Day 7 Summary] Nothing relevant happened

[Day 8 Summary]
- Daniel(Deer) cried again
````

**Nothing happened until the last day of the week**

````
[Week 1 Summary]
- Ricardo(Tree) was struck by a thunderstorm, and fell
````

**Nothing happened for a full week, then something happened on the first day of the next week**

````
[Week 1 Summary] Nothing relevant happened

[Day 8 Summary]
- Something happened
````

> [!IMPORTANT]
> You keep this process going, and bubble to higher-order milestones

---

# User Interactivity

Users may choose to interact with the simulation, such as:

* Changing ``weather``
* ``Fast-forwarding`` / ``Reversing`` simulations
* Special events(plagues, etc...)

This is done via ``console commands`` the user inputs.

## Available Commands

---

# Simulation Configuration
(Users may configure simulations: milestones durations, ignore certain summaries, seeded simulations, etc....)