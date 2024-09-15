# Attack on Titan: Utopia
**Attack on Titan: Utopia:** is a one-player, endless tower defense game inspired by the popular anime series *Attack on Titan*. In this game, players must defend the Utopia District's walls from waves of titans using various anti-titan weapons. The game challenges players to strategize and manage resources to keep the walls from falling.
## Table of Contents
- [🚀 Game Overview](#-game-overview)
- [🛠 Features](#-features)
- [🏰 Space Setting](#-space-setting)
- [👺 Enemy Characters](#-enemy-characters)
   - [Titan Attributes](#titan-attributes)
   - [Titan Types](#titan-types)
- [⚔️ Friendly Pieces](#-friendly-pieces)
- [🛡️ Game Rules](#-game-rules)
- [🖥️ Technologies and Tools Used](#-technologies-and-tools-used)
- [🎥 Demo Video](#-demo-video)
- [⚙️ Setup and Installation](#-setup-and-installation)
   - [Prerequisites](#prerequisites)
   - [Running the Game](#running-the-game)
- [🤝 Contributing](#-contributing)
## 🚀 Game Overview
The game is set in a fictional scenario where titans have breached Wall Maria and are now attacking the northern border of Wall Rose at the Utopia District. Players take on the role of defenders tasked with preventing the titans from breaching Wall Rose by deploying different types of weapons.
## 🛠 Features
- **Game Modes**: Easy & Hard, offering different starting conditions and challenges
- **Endless Mode**: Test your strategic skills and endurance as Titans keep coming.
- **Dynamic Lanes**: Defend multiple lanes, each with its own danger level.
- **Enemy Titans**: Encounter different Titan types with unique traits affecting gameplay.
- **Weapon Variety**: Use a range of weapons, each with specific advantages and attack actions.
- **AI Integration**: Optimize your gameplay with an AI button for decision-making.
- **Player-selectable guide characters**: for insights during battle phase changes
## 🏰 Space Setting
The battlefield is divided into multiple lanes, each featuring:
- A part of the wall to be defended.
- Deployed weapons.
- Titans advancing toward the wall.
## 👺 Enemy Characters
### Titan Attributes:
- **HP**: Health points of the titan.
- **Damage**: Damage inflicted by the titan on the wall.
- **Height**: Height of the titan (doesn't affect gameplay).
- **Speed**: Movement distance per turn.
- **Resources value**: Resources gained from defeating the titan.
- **Danger level**: How much the titan affects the lane's danger level.
### Titan Types:
- **Pure Titan**: Basic titans with standard attributes.
- **Abnormal Titan**: ⚡ Performs double attacks per turn.
- **Armored Titan**: 🛡️ Takes only 25% of intended damage.
- **Colossal Titan**: 💨 Increases speed after every movement.
## ⚔️ Friendly Pieces
All weapon types behave uniquely while performing their attack actions:
- **Piercing Cannon**: 🎯 Attacks the closest **5 titans** to the wall on the weapon’s lane each turn.
- **Sniper Cannon**: 🔭 Attacks the **first closest titan** to the wall on the weapon’s lane each turn.
- **Volley Spread Cannon**: 🌩️ Attacks **all titans** within the weapon’s **minimum** and **maximum range** on the weapon’s lane each turn.
- **Wall Trap**: 🪤 Attacks only **one titan** that has already reached the wall; if more than one titan has reached, only the **first one** is attacked.
## 🛡️ Game Rules
- **Initial Setup**: The game starts with a score of 0 and initial resources.
- **Objective**: Protect the Utopia District's walls from the titans.
- **No Winning Condition**: The game continues endlessly until all lanes are lost.
- **Turn Actions**: Players choose to buy a weapon or pass their turn, followed by the titans' and weapons' actions.
- **Weapon Purchase**: Players can buy and deploy weapons using gathered resources.
- **Titan Movement**: Titans move closer to the wall each turn based on their speed.
- **Attack Actions**: Titans attack the wall if they reach it, while weapons attack titans in their lanes.
## 🖥️ Technologies and Tools Used
- **Java**: Core programming language for game logic and custom component development.
- **JavaFX**: For user interface design, scene management, layout control, and visual effects.
- **Object-Oriented Programming**: Utilizing OOP principles for clean and maintainable code.
- **MVC Architecture**: Employed to separate the game’s logic (Model), user interface (View), and input handling (Controller), enhancing modularity and maintainability.
- **Game Development Concepts**: Managing game states and dynamic UI updates based on game progress.
- **Animation and Event Handling**: Implementing game dynamics and user interaction through animations and event listeners.
- **Custom Components**: Developing tailored UI elements for game functionality.
## 🎥 Demo Video
Check out our game in action! Watch the full demo video to see all aspects of *Attack on Titan: Utopia* and try out its features.
- [🔗 LinkedIn Post with Video](https://www.linkedin.com/posts/activity-7213468099470098432-5Bdz?utm_source=share&utm_medium=member_desktop)
Feel free to explore the gameplay and get a closer look at what the game offers.
## ⚙️ Setup and Installation
### Prerequisites
- Java Development Kit (JDK) 11 or higher
- JavaFX SDK 22.0.1 or higher
### Running the Game
1. **Set up JavaFX**:
    - Download the JavaFX SDK from [Gluon](https://gluonhq.com/products/javafx/).
    - Extract the SDK and note the path to the `lib` directory.
2. **Compile and Run**:
    - **Add JavaFX** to your IDE's module path.
    - **Use VM Options** to include JavaFX modules:
      ```sh
      --module-path "path/to/javafx-sdk/lib" --add-modules javafx.controls,javafx.fxml,javafx.media
      ```
## 🤝 Contributing
Feel free to fork the repository and submit pull requests. Ensure that your contributions follow the game's design.