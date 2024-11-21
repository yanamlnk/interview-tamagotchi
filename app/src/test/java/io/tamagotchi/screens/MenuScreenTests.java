package io.tamagotchi.screens;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ MediaPlayer.class, Media.class })
public class MenuScreenTests extends ApplicationTest {
    @Override
    public void start(Stage stage) throws Exception {
        // Start the MenuScreen application
        new MenuScreen().start(stage);
    }

    @Test
    @DisplayName("Test to verify if \"Start\" button is initially disabled")
    void testInitialState() {
        FxRobot robot = new FxRobot();

        Button startButton = robot.lookup(".button").queryAs(Button.class);
        assertNotNull(startButton, "Start button should exist.");
        assertTrue(startButton.isDisabled(), "Start button should initially be disabled.");
    }

    @Test
    @DisplayName("Test to verify the functionality of pet buttons.")
    void testPetSelection() {
        FxRobot robot = new FxRobot();
        ToggleButton gradleButton = robot.lookup("#gradle").queryAs(ToggleButton.class);
        ToggleButton dockerButton = robot.lookup("#docker").queryAs(ToggleButton.class);
        ToggleButton linuxButton = robot.lookup("#linux").queryAs(ToggleButton.class);

        assertNotNull(gradleButton, "Gradle pet button should exist.");
        assertNotNull(dockerButton, "Docker pet button should exist.");
        assertNotNull(linuxButton, "Linux pet button should exist.");

        robot.clickOn(gradleButton);
        assertTrue(gradleButton.isSelected(), "Gradle button should be selected after clicking.");
        assertFalse(dockerButton.isSelected(), "Docker button should not be selected after clicking on Gradle.");
        assertFalse(linuxButton.isSelected(), "Linux button should not be selected after clicking on Gradle.");

        robot.clickOn(dockerButton);
        assertTrue(dockerButton.isSelected(), "Docker button should be selected after clicking.");
        assertFalse(gradleButton.isSelected(), "Gradle button should not be selected after clicking on Docker.");
        assertFalse(linuxButton.isSelected(), "Linux button should not be selected after clicking on Docker.");

        robot.clickOn(linuxButton);
        assertTrue(linuxButton.isSelected(), "Linux button should be selected after clicking.");
        assertFalse(gradleButton.isSelected(), "Gradle button should not be selected after clicking on Linux.");
        assertFalse(dockerButton.isSelected(), "Docker button should not be selected after clicking on Linux.");

        robot.clickOn(linuxButton);
        assertTrue(linuxButton.isSelected(), "Linux button should remain selected after clicking again.");
    }

    @Test
    @DisplayName("Test to verify the functionality of language buttons.")
    void testLanguageSelection() {
        FxRobot robot = new FxRobot();

        ToggleButton javaButton = robot.lookup("#java").queryAs(ToggleButton.class);
        ToggleButton pythonButton = robot.lookup("#python").queryAs(ToggleButton.class);
        assertNotNull(javaButton, "Java language button should exist.");
        assertNotNull(pythonButton, "Python language button should exist.");

        robot.clickOn(javaButton);
        assertTrue(javaButton.isSelected(), "Java button should be selected after clicking.");
        assertFalse(pythonButton.isSelected(), "Python button should not be selected after clicking on Java.");

        robot.clickOn(pythonButton);
        assertTrue(pythonButton.isSelected(), "Python button should be selected after clicking.");
        assertFalse(javaButton.isSelected(), "Java button should not be selected after clicking on Python.");

        robot.clickOn(pythonButton);
        assertTrue(pythonButton.isSelected(), "Python button should remain selected after clicking again.");
    }

    @Test
    @DisplayName("Test to verify the enabling of the \"Start\" button.")
    void testEnableStartButton() {
        FxRobot robot = new FxRobot();

        ToggleButton gradleButton = robot.lookup("#gradle").queryAs(ToggleButton.class);
        ToggleButton javaButton = robot.lookup("#java").queryAs(ToggleButton.class);
        Button startButton = robot.lookup(".button").queryAs(Button.class);

        assertNotNull(gradleButton, "Gradle pet button should exist.");
        assertNotNull(javaButton, "Java language button should exist.");
        assertNotNull(startButton, "Start button should exist.");

        robot.clickOn(gradleButton);
        assertTrue(startButton.isDisabled(), "Start button should be disabled when only one selection is made.");

        robot.clickOn(javaButton);
        assertFalse(startButton.isDisabled(), "Start button should be enabled when both selections are made.");
    }

    @Test
    @DisplayName("Test to verify the different styles of buttons.")
    void testButtonStyles() {
        FxRobot robot = new FxRobot();
        ToggleButton gradleButton = robot.lookup("#gradle").queryAs(ToggleButton.class);
        Button startButton = robot.lookup(".button").queryAs(Button.class);
        ToggleButton javaButton = robot.lookup("#java").queryAs(ToggleButton.class);

        assertNotNull(gradleButton, "Gradle pet button should exist.");
        assertNotNull(startButton, "Start button should exist.");
        assertNotNull(javaButton, "Java language button should exist.");

        assertTrue(gradleButton.getStyle().contains("-fx-background-color: transparent;"),
                "Gradle button should have default style applied.");
        assertTrue(startButton.getStyle().contains("-fx-background-color: transparent;"),
                "Start button should have default style applied.");

        robot.moveTo(gradleButton);
        assertTrue(gradleButton.getStyle().contains("-fx-background-color: #C2ECF4;"),
                "Gradle button should have hover style applied.");

        robot.moveTo(javaButton);
        assertTrue(javaButton.getStyle().contains("-fx-background-color: #C2ECF4;"),
                "Java button should have hover style applied.");
        assertTrue(gradleButton.getStyle().contains("-fx-background-color: transparent;"), "Gradle button should not have hover style applied.");

        robot.clickOn(gradleButton);
        assertTrue(gradleButton.getStyle().contains("-fx-background-color: #94CED8;"),
                "Gradle button should have selected style applied.");

        robot.clickOn(javaButton);
        assertTrue(javaButton.getStyle().contains("-fx-background-color: #94CED8;"),
                "Java button should have selected style applied.");

        assertTrue(startButton.getStyle().contains("-fx-background-color: #A0DF95;"),
                "Start button should have selected style applied.");

        robot.moveTo(startButton);
        assertTrue(startButton.getStyle().contains("-fx-background-color: #8AC380;"),
                "Start button should have hover style applied.");
    }

//    @Test
//    @DisplayName("Test to verify the action of the \"Start\" button.")
//    void testStartButtonAction() throws Exception {
//        Media mockMedia = mock(Media.class);
//        MediaPlayer mockMediaPlayer = mock(MediaPlayer.class);
//
//        // Mock behavior of MediaPlayer
//        doNothing().when(mockMediaPlayer).play();
//        doNothing().when(mockMediaPlayer).pause();
//
//        // Mock the creation of a MediaPlayer
//        PowerMockito.whenNew(MediaPlayer.class).withAnyArguments().thenReturn(mockMediaPlayer);
//
//        FxRobot robot = new FxRobot();
//
//        MainScreen mainScreenMock = mock(MainScreen.class);
//        Stage primaryStageMock = mock(Stage.class);
//        doNothing().when(mainScreenMock).start(any(Stage.class));
//
//        doNothing().when(mainScreenMock).playBackgroundMusic();
//
//
//        ToggleButton gradleButton = robot.lookup("#gradle").queryAs(ToggleButton.class);
//        ToggleButton javaButton = robot.lookup("#java").queryAs(ToggleButton.class);
//        Button startButton = robot.lookup(".button").queryAs(Button.class);
//
//        robot.clickOn(gradleButton);
//        robot.clickOn(javaButton);
//        robot.clickOn(startButton);
//
//        assertFalse(startButton.isDisabled(), "Start button action should trigger after enabling.");
//
//        robot.clickOn(startButton);
//
//        verify(mainScreenMock).start(primaryStageMock);
//        verify(mockMediaPlayer, times(0)).play();
//    }
}
