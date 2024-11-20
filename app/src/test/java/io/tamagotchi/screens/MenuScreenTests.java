package io.tamagotchi.screens;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.*;

public class MenuScreenTests extends ApplicationTest {
    @Override
    public void start(Stage stage) throws Exception {
        // Start the MenuScreen application
        new MenuScreen().start(stage);
    }

    @BeforeEach
    void setUp() {
        // Optional setup if required
    }

    @Test
    void testInitialState() {
        FxRobot robot = new FxRobot();

        // Verify "Start" button is initially disabled
        Button startButton = robot.lookup(".button").queryAs(Button.class);
        assertNotNull(startButton, "Start button should exist.");
        assertTrue(startButton.isDisabled(), "Start button should initially be disabled.");
    }
//
//    @Test
//    void testPetSelection(FxRobot robot) {
//        // Verify pet selection behavior
//        ToggleButton gradleButton = robot.lookup(".toggle-button").lookup("GRADLE").queryAs(ToggleButton.class);
//        assertNotNull(gradleButton, "Gradle pet button should exist.");
//
//        robot.clickOn(gradleButton);
//        assertTrue(gradleButton.isSelected(), "Gradle button should be selected after clicking.");
//    }
//
//    @Test
//    void testLanguageSelection(FxRobot robot) {
//        // Verify language selection behavior
//        ToggleButton javaButton = robot.lookup(".toggle-button").lookup("JAVA").queryAs(ToggleButton.class);
//        assertNotNull(javaButton, "Java language button should exist.");
//
//        robot.clickOn(javaButton);
//        assertTrue(javaButton.isSelected(), "Java button should be selected after clicking.");
//    }
//
//    @Test
//    void testEnableStartButton(FxRobot robot) {
//        // Verify enabling the "Start" button after both selections
//        ToggleButton gradleButton = robot.lookup(".toggle-button").lookup("GRADLE").queryAs(ToggleButton.class);
//        ToggleButton javaButton = robot.lookup(".toggle-button").lookup("JAVA").queryAs(ToggleButton.class);
//        Button startButton = robot.lookup(".button").queryAs(Button.class);
//
//        assertNotNull(gradleButton, "Gradle pet button should exist.");
//        assertNotNull(javaButton, "Java language button should exist.");
//        assertNotNull(startButton, "Start button should exist.");
//
//        robot.clickOn(gradleButton);
//        robot.clickOn(javaButton);
//
//        assertFalse(startButton.isDisabled(), "Start button should be enabled when both selections are made.");
//    }
//
//    @Test
//    void testStartButtonAction(FxRobot robot) {
//        // Verify behavior of clicking the "Start" button
//        ToggleButton gradleButton = robot.lookup(".toggle-button").lookup("GRADLE").queryAs(ToggleButton.class);
//        ToggleButton javaButton = robot.lookup(".toggle-button").lookup("JAVA").queryAs(ToggleButton.class);
//        Button startButton = robot.lookup(".button").queryAs(Button.class);
//
//        robot.clickOn(gradleButton);
//        robot.clickOn(javaButton);
//        robot.clickOn(startButton);
//
//        // Since the start button opens a new screen (MainScreen),
//        // you may want to assert the state transition logic.
//        // Example: Checking if the MainScreen's scene is loaded.
//        // (You'll need to mock or spy MainScreen for deeper testing.)
//        assertFalse(startButton.isDisabled(), "Start button action should trigger after enabling.");
//    }
//
//    @Test
//    void testHoverStyles(FxRobot robot) {
//        // Verify hover effects on buttons
//        ToggleButton gradleButton = robot.lookup(".toggle-button").lookup("GRADLE").queryAs(ToggleButton.class);
//        Button startButton = robot.lookup(".button").queryAs(Button.class);
//
//        assertNotNull(gradleButton, "Gradle pet button should exist.");
//        assertNotNull(startButton, "Start button should exist.");
//
//        // Hover over Gradle button
//        robot.moveTo(gradleButton);
//        // Hover style test (replace with a suitable assertion to verify style if needed)
//
//        // Hover over Start button
//        robot.moveTo(startButton);
//        // Hover style test (replace with a suitable assertion to verify style if needed)
//    }
}
