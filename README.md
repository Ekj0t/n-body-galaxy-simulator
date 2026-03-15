# N-Body Galaxy Simulator 🌌

A real-time **gravitational N-body simulation** written in Java that generates and animates spiral galaxies.  
Stars interact through Newtonian gravity, forming rotating galaxies, tidal arms, and chaotic structures during galaxy collisions.

---

## Demo

![Galaxy Simulation](./demo.gif)

---

## Features

- ⭐ Real-time **N-body gravitational simulation**
- 🌌 Procedurally generated **spiral galaxies**
- 🔭 **1500+ interacting stars**
- 💫 **Galaxy collision simulation**
- ✨ **Brightness variation** for depth
- 🌠 **Motion blur / orbital trails**
- ⚡ Runs smoothly with **O(n²) gravity calculations**

---

## How It Works

Each star is modeled as a body with:

- Position `(x, y)`
- Velocity `(vx, vy)`
- Mass
- Brightness

Every frame:

1. Gravitational forces are calculated between all bodies.
2. Accelerations update star velocities.
3. Stars move according to updated velocities.
4. The scene is rendered with motion blur to show orbital paths.

The simulation approximates the gravitational interaction:

```

F = G * m₁ * m₂ / r²

```

Where:

- **G** – gravitational constant  
- **m₁, m₂** – masses of bodies  
- **r** – distance between bodies  

---

#File Structure

| File | Description |
|-----|-------------|
| `Main` | Starts the application window |
| `SimulationPanel` | Handles simulation loop and rendering |
| `Body` | Represents stars and galactic cores |
| `Vector2D` | Utility class for vector math |

---

## Galaxy Generation

Galaxies are generated procedurally using spiral arm equations:

```

angle = armAngle + radius * spiralFactor

```

This produces logarithmic spiral arms similar to real galaxies.

Two galaxies are initialized with opposite velocities to simulate **galactic collisions**.

---

## Performance

The simulation calculates gravitational interactions between all stars:

```

O(n²) interactions per frame

```

For ~1500 stars:

```

≈ 2.25 million force calculations per frame

```

Despite this, the simulation runs smoothly at interactive frame rates.

---

## Example Behaviors

The simulation can produce:

- Spiral galaxy rotation
- Tidal arms
- Star bridges between galaxies
- Star ejections from gravitational slingshots
- Chaotic star cluster formations

---

## Future Improvements

Possible extensions:

- Barnes–Hut algorithm for **10k+ stars**
- Camera zoom and panning
- Interactive galaxy generation
- Multiple galaxy systems
- Black hole accretion visualization

---

## Technologies Used

- **Java**
- **Java Swing**
- **Java2D Graphics**
