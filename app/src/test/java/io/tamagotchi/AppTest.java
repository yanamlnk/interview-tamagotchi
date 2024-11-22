package io.tamagotchi;

import io.tamagotchi.screens.MenuScreen;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;

public class AppTest {

    @Test
    public void testMain() {
        try (MockedStatic<MenuScreen> menuScreenMock = mockStatic(io.tamagotchi.screens.MenuScreen.class)) {
            App.main(new String[]{});
            menuScreenMock.verify(() -> io.tamagotchi.screens.MenuScreen.main(new String[]{}));
        }
    }
}
