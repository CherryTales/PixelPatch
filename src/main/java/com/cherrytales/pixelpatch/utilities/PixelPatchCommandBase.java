package com.cherrytales.pixelpatch.utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

/**
 * The base for all Pixel Patch commands.
 */
public abstract class PixelPatchCommandBase extends CommandBase {

    /**
     * Lets you create a command.
     * Once the command is executed, the code in this method will run.
     *
     * @param sender the command sender
     * @param args the command arguments
     * @throws CommandException command exception
     */
    public abstract void processCommand(final ICommandSender sender, final String[] args) throws CommandException;

    /**
     * Gets the usage string for the command.
     *
     * @param sender the command sender
     */
    @Override
    public String getCommandUsage(final ICommandSender sender) {
        return "pixelpatch.commands." + getCommandName() + ".usage";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    /**
     * Gets a list of all the players on the server.
     *
     * @param senderName the sender name - excludes the name from the list
     * @return the list of player names
     */
    public List<String> allPlayerNames(final String senderName) {
        final Collection<NetworkPlayerInfo> playerInfoMap = Minecraft.getMinecraft().getNetHandler().getPlayerInfoMap();
        final List<String> playerNames = new ArrayList<>();
        for (final NetworkPlayerInfo playerInfo : playerInfoMap) {
            if (playerInfo != null) {
                // Regex checks that the name is a valid username
                if (playerInfo.getGameProfile().getName().matches("^[a-zA-Z0-9_]{2,16}$") &&
                    !playerInfo.getGameProfile().getName().equalsIgnoreCase(senderName)) {
                    playerNames.add(playerInfo.getGameProfile().getName());
                }
            }
        }
        return playerNames;
    }

    /**
     * Gets a list of all the players on the server.
     *
     * @return the list of player names
     */
    public List<String> allPlayerNames() {
        return this.allPlayerNames(null);
    }

}
