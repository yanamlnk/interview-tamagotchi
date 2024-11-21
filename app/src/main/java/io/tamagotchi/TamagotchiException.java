package io.tamagotchi;

/**
 * Custom exception class for handling Tamagotchi-related errors.
 */
public class TamagotchiException extends Exception {

    /**
     * Constructs a new TamagotchiException with the specified detail message.
     *
     * @param message the detail message
     */
    public TamagotchiException(String message) {
        super(message);
    }
}
