
# Tower of Mordoria - Tower Defense Game (libGDX)

Welcome to **Tower of Mordoria**, a simple tower defense game built using [libGDX](https://libgdx.com/). This is the foundation of a 2D tower defense game where enemies animate and move across the screen over a static map background.

---

## 🎮 Features (So Far)

- ✅ Loads a background map image using `SpriteBatch`
- ✅ Displays animated enemy sprites using `Animation<TextureRegion>`
- ✅ Moves an enemy sprite smoothly across the screen
- ✅ Uses `FitViewport` to maintain a consistent world size across devices

---


## 🧱 Requirements

- Java 8+
- Gradle
- libGDX (configured via `gdx-setup.jar`)
- IDE like IntelliJ IDEA or Android Studio

---

## 🚀 How to Run

1. Clone this repository.
2. Open it in your IDE and make sure the Gradle project syncs.
3. Place your map image in `assets/Maps/Map1.png`.
4. Place your enemy spritesheet in `assets/Test/Walk/Vampires1_Walk_full.png`.
5. Run the `desktop/lwjgl3` launcher or the core game screen.

---

## 🗺 Map

- Map image: `Maps/Map1.png`
- Rendered with size `12 x 9` world units using `FitViewport`
- Centered on the screen and scaled to fit aspect ratio

---

## 👾 Enemies

- Uses sprite sheet with 6 columns and 4 rows.
- Only the last row (row 3) is used for the walking animation.
- Enemy sprite is 2x2 world units and moves horizontally.

---

## 🛠 Next Steps

- Add path-following logic for enemies
- Add clickable tiles to place towers
- Define tower range, damage, and shooting behavior
- Add UI for player resources, wave counter, and controls
