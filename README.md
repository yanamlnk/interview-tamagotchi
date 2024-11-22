# PrepPal
## Tamagotchi pet that will help you to prepare for your technical interview!

## Content
- [Description of the Game](#description-of-the-game)
- [How to Play](#how-to-play)
   - [MENU SCREEN](#menu-screen)
   - [MAIN SCREEN](#main-screen)
   - [QUIZ SCREEN](#quiz-screen)
   - [WIN AND LOSE CONDITIONS](#win-and-lose-conditions)
   - [GAME MECHANICS](#game-mechanics)
  -  [SUPERPOWERS IN DETAILS](#superpowers-in-details)
- [Architecture and Technologies Used](#architecture-and-technologies-used)
- [OOP Principles used](#oop-principles-used)
- [Ways To Improve](#ways-to-improve)
- [Installation](#installation)

> [!TIP]
> To play immediately, download jar file [here]() and run it with `java -jar path/to/file/PrepPal.jar`

### Description of the Game
PrepPal is a Tamagotchi pet that will help you to prepare for your technical interview! You will need to feed, play, and study with your PrepPal to make sure it is ready for the big day.

The game proposes 2 languages to study: 
1. **Java**
2. **Python**

For each language there are 2 activities: 
1. **interview questions** - questions that you could hear during the interview with 4 possible answers to choose from.
2. **coding games** - a code snippets with a missing code and 4 options what to add on a place of missing code.

There are 3 pets to choose from, each one with its perks:
- **Gradly** - an elephant pet inspired by build tool Gradle. He gains more HP from food, so he is perfect for you, if you want to concentrate on coding games instead of interview questions.
- **Docky** - a whale inspired by Docker. He gains more XP from coding games, so he is a great choice if you want to refresh your knowledge and win the game quicker.
- **Nux** - a penguin inspired by Linux. He gets hungry slower, so he is perfect for longer game experience without a fear of losing.

<div style="display: flex; justify-content: center;">
  <img src="app/src/main/resources/gradle.gif" width="300" height="300" alt = "gif with elephant"/>
  <img src="app/src/main/resources/docker.gif" width="300" height="300" alt = "gif with whale"/>
  <img src="app/src/main/resources/linux.gif" width="300" height="300" alt = "gif with penguin"/>
</div> 

📘Game Design Document is available [here](./PrepPal%20-%20Game%20Design%20Document.pdf)📘
### How to Play

### MENU SCREEN
Choose you pet and language you want to practice from the menu screen:
<img width="839" alt="image" src="https://github.com/user-attachments/assets/07df6c20-06a5-41b5-a78e-9c9c1d448eb4">

Once you have chosen, click on **START**. 

### MAIN SCREEN
<img width="996" alt="image" src="https://github.com/user-attachments/assets/5d14a728-4be2-4482-9d05-7696f6dec8af">

On the main screen you will see:
1. **RULES** - button on the top, button to read rules of the game
2. **CUSTOMIZE** - button to open a pop up menu, when you are able to change background color and control the level of the sound
3. On the right, you will see unique message of your pet, the pet itself, along with his name, description of superpower, and a language you have chosen
4. On the left, you will see information on your pet's health and experience points, along with the level and number of coins you have. 
5. *FEED** - button to feed your pet. It will open pop up window with 3 options what to give to your tamagotchi:
   - **Burger** - costs 5 coins, and gives your pet 35 HP
   - **Fish** - costs 3 coins, and gives 23 HP
   - **Potato** - costs 1 coin, and gives 10 HP
6. **CODING GAME** - open a coding game window
7. **INTERVIEW QUESTIONS** - open a interview questions window

### QUIZ SCREEN
<img width="1000" alt="image" src="https://github.com/user-attachments/assets/f195d0bb-0212-40ff-8433-d11d4c8338fb">

On the quiz screen you will see:
- how many question you have on total (usually 10), and on what question you currently are.
- question itself
- 4 possible answers to choose from
- button to submit your answer

After you click on submit, you will see immediately correct and wrong answers.
After the quiz, you will see how much money/XP you have earned, and you will have a button to return to the main screen.

### WIN AND LOSE CONDITIONS
You will win the game if you reach level 10.
<img width="995" alt="image" src="https://github.com/user-attachments/assets/a38ef840-5968-47ba-bbd0-5551d1a2894f">


You will lose the game if your pet's health reaches 0.
<img width="1138" alt="image" src="https://github.com/user-attachments/assets/f4ecaec8-7b5c-4cbd-b75a-c8bd1756cc3b">

### GAME MECHANICS
After interview questions:
- you get 3 coins + 0.5 coins for each correct answer (if number is not integer, it is rounded down)
- you lose 20 HP

After coding game:
- you get 10 XP + 2 XP for each correct answer
- you lose 30 HP

### SUPERPOWERS IN DETAILS
- Docky gets extra 10 XP after coding game.
- Gradly gets extra 10 HP after eating.
- Nux loses 10HP less after coding game or interview question.

### Architecture and Technologies Used
There are 4 main packages in the project:
1. **pet** - to store pet model. 
    - Interface `Tamagotchi` - to store methods that every pet should have
    - Abstract class `Pet` (implements `Tamagotchi`) - to store common methods for all pets
    - 3 classes `Gradle`, `Docker`, `Linux` (extends `Pet`) - to store unique methods for each pet
    - `PetFactory` - to store factory method to create a pet
    - `LevelController` - to store methods to control pet's level
2. **food** - to store food model
    - Abstract class `Food` - to store common methods for all food
    - 3 classes `Burger`, `Fish`, `Potato` (extends `Food`) - to store unique fields for each food
    - `FoodFactory` - to store factory method to create a food
3. **question** - to store question model and controller
    - Class `Question` - to store question model
    - Class `QuestionController` - provides method to read questions from a file
    - Class `Questions` - provides method to generate 10 random questions and store all questions
4. **screens** - to store all screens
    - Class `MenuScreen` - to store menu screen
    - Class `MainScreen` - to store main screen, that takes Pet and language from MenuScreen
    - Class `QuizScreen` - to store quiz screen, has 2 modes: `play` (for coding game) and `work` (for interview questions)
    - Class `EndGameScreen` - takes Pet and language, but also has 2 options: `winner` and `loser`

🕸️UML diagram is available [here](./UML%20diagram.pdf) 🕸️

Technologies used:
- Java 17
- JavaFX - to create GUI
- Gradle as build tool
- JUnit 5 for testing
- Mockito for mocking
- GSON for reading JSON files
- Jacoco for test coverage

Also it is possible to generate docs with Javadoc, and simple CI pipeline is set up with GitHub Actions.

### OOP Principles used:
- Inheritance: project has clear relationships in pet and food packages, where subclasses inherits from abstract classes or interface.
- Abstraction: this project has 1 interface and 2 abstract classes.
- Encapsulation: most fields are private, and can be accessed only through getters and setters. Some specific fields are protected.
- Polymorphism: `Pet` overrides `Tamagotchi` methods, subclasses overrides specific methods of parents to create superpowers. Overloading with method `eat` in `Pet` (argument is either Food or flood).

Design pattern: Factory Method is used to create pets and food.
Custom Exception: `TamagotchiException` is used to handle exceptions in the project.

### Ways To Improve
Easy:
1. Add more languages to study
Medium:
2. Improve quality of questions
3. Add possibility to save progress
Harder:
4. For interview questions, add possibility to write answers by hand, and check them with AI.
5. For coding games, add possibility to write code by hand, and check the functionality of the code with tests or AI.

### Installation
Java version 17+ is required to run the game.
To check Java version: `java -version`

**If you want to check out the code and play the game, follow these steps:**
1. Clone repository with `git clone`
2. Download and install JavaFX from [here](https://openjfx.io/).
3. go to the project folder and run `./gradlew run` to start the game
> [!NOTE]
> If you don't want to install JavaFX, scroll down to `Jar` task.

To test:
1. run `./gradlew test` to run all tests. Results of the tests are logged into console, but you can also find them in `build/reports/tests/test/index.html`
2. if just coverage report is needed, run `./gradlew test jacocoTestReport`. Result you can find in `build/reports/jacoco/test/html/index.html`
> [!NOTE]
> Coverage report is already included into `test` task 

Javadoc:
1. run `./gradlew javadoc` to generate docs. You can find them in `build/docs/javadoc/index.html`

Jar:
1. run `./gradlew jar` to create a jar file. You can find it in `build/libs/PrepPal.jar`
2. run `java -jar build/libs/PrepPal.jar` to start the game (will work if you have JavaFX installed)
3. 

**If you don't want to clone the repository, you can download the jar [here]() and run it as explained in Jar step 2.**