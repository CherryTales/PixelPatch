package com.cherrytales.pixelpatch.blinder;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores the hidden players.
 */
public final class HiddenPlayers {

    private HiddenPlayers() {
        // Do nothing
    }

    private static final List<String> hiddenPlayersList = new ArrayList<>();

    /**
     * Adds a player to the hidden list.
     *
     * @param otherPlayer the player to hide
     */
    public static void addHiddenPlayer(final String otherPlayer) {
        hiddenPlayersList.add(otherPlayer.toLowerCase());
    }

    /**
     * Removes a player from the hidden list.
     *
     * @param otherPlayer the player to reveal
     */
    public static void removeHiddenPlayer(final String otherPlayer) {
        hiddenPlayersList.remove(otherPlayer.toLowerCase());
    }

    /**
     * Checks if the player is hidden.
     *
     * @param otherPlayer the player to check
     * @return {@code true} if the player should be hidden
     */
    public static boolean checkPlayer(final String otherPlayer) {
        return hiddenPlayersList.contains(otherPlayer.toLowerCase());
    }

    /**
     * Gets the list of hidden players.
     *
     * @return the hidden player list
     */
    public static List<String> hiddenPlayers() {
        return hiddenPlayersList;
    }
}
