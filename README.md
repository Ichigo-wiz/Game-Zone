# 🎮 Game-Zone

**Game-Zone** is a Java-based multi-game console application that combines several classic games into a single, interactive terminal experience. It is built with modular Java classes, each encapsulating the logic for a specific game, and includes MySQL database integration to support logging or score tracking.

---

## 📁 Folder Structure

```plaintext
Game-Zone-main/
└── Game-Zone-main/
    ├── README.md
    └── Integration/
        ├── console_chaos (1).sql
        └── Integration/
            └── src/
                └── GameZone/
                    ├── ConsoleChaos/
                    │   └── ConsoleChaos.java
                    ├── CrazyCricket/
                    │   └── CrazeCricket.java
                    ├── Cricket/
                    │   └── DigitalCricket.java
                    ├── Game/
                    │   └── QuizGame.java
                    ├── Hangman/
                    │   └── HangmanGame.java
                    ├── TicTacToe/
                    │   └── TicTacToe.java
                    └── TickingTime/
                        └── TimerGame.java
```

---

## 🕹️ Included Games

| Game             | File Name            | Description                                  |
|------------------|----------------------|----------------------------------------------|
| Quiz Game        | `QuizGame.java`      | A simple quiz-based trivia game.             |
| Hangman          | `HangmanGame.java`   | Word guessing game with limited chances.     |
| Tic Tac Toe      | `TicTacToe.java`     | Classic 2-player board game.                 |
| Timer Game       | `TimerGame.java`     | Reaction-based game based on time.           |
| Console Chaos    | `ConsoleChaos.java`  | Possibly the game launcher or aggregator.    |
| Crazy Cricket    | `CrazeCricket.java`  | A fun twist on traditional cricket.          |
| Digital Cricket  | `DigitalCricket.java`| Another version of cricket gameplay.         |

---

## 🛠️ Getting Started

### 🧰 Requirements

- Java JDK 8 or later
- MySQL server (optional, for `console_chaos (1).sql` database)
- A terminal or IDE to run Java applications

---

### ▶️ Run Instructions

1. Navigate to the main Java source folder:
   ```bash
   cd Integration/Integration/src/GameZone
   ```

2. Compile all `.java` files:
   ```bash
   javac */*.java
   ```

3. Run the main game (e.g., ConsoleChaos launcher):
   ```bash
   java ConsoleChaos.ConsoleChaos
   ```

> ✅ Make sure your terminal is at the correct root directory and your Java classpaths are set if needed.

---

## 🗄️ Database Setup (Optional)

The project includes a SQL file named `console_chaos (1).sql`, which can be used to:
- Store user scores
- Log gameplay data
- Track game statistics

### To import the SQL file:
```sql
SOURCE path/to/console_chaos (1).sql;
```

> ⚠️ JDBC connection setup must be configured inside the Java code to link it with the database.

---

## 📌 Key Concepts Demonstrated

- Object-Oriented Programming (Java)
- Game loops and control flow
- User input/output via console
- Modular design with package separation
- Optional MySQL integration using JDBC

---

## 🔮 Future Enhancements

- Add a graphical user interface (Swing or JavaFX)
- Multiplayer support for certain games
- Real-time leaderboard with database support
- Persistent user profiles and sessions

---



