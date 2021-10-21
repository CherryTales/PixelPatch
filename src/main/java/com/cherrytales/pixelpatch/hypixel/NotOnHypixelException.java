package com.cherrytales.pixelpatch.hypixel;

import net.minecraft.command.CommandException;

/**
 * Exception for if the player is not on Hypixel.
 */
@SuppressWarnings("serial")
public class NotOnHypixelException extends CommandException {

    /**
     * Constructor for the Not on Hypixel Exception.
     */
    public NotOnHypixelException() {
        super("pixelpatch.command.onlyOnHypixel");
    }
}
