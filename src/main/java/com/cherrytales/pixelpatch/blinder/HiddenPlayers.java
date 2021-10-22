package com.cherrytales.pixelpatch.blinder;

import com.mojang.authlib.GameProfile;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores the hidden players.
 */
public final class HiddenPlayers {

    private HiddenPlayers() {
        // Do nothing
    }

    private static final List<GameProfile> hiddenPlayersList = new ArrayList<>();

    /**
     * Adds a player to the hidden list.
     *
     * @param otherPlayer the player to hide
     */
    public static void addHiddenPlayer(final GameProfile otherPlayer) {
        hiddenPlayersList.add(otherPlayer);
    }

    /**
     * Removes a player from the hidden list.
     *
     * @param otherPlayer the player to reveal
     */
    public static void removeHiddenPlayer(final GameProfile otherPlayer) {
        hiddenPlayersList.remove(otherPlayer);
    }

    /**
     * Checks if the player is hidden.
     *
     * @param otherPlayer the player to check
     * @return {@code true} if the player should be hidden
     */
    public static boolean checkPlayer(final GameProfile otherPlayer) {
        return hiddenPlayersList.contains(otherPlayer);
    }

    /**
     * Gets the list of hidden players.
     *
     * @return the hidden player list
     */
    public static List<GameProfile> hiddenPlayers() {
        return hiddenPlayersList;
    }
}
