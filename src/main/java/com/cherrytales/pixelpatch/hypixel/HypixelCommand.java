package com.cherrytales.pixelpatch.hypixel;

import com.cherrytales.pixelpatch.PixelPatchForge;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Commands which can only be used on Hypixel.
 */
public abstract class HypixelCommand extends CommandBase {

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    /**
     * Gets the usage string for the command.
     *
     * @param sender the command sender
     */
    @Override
    public String getCommandUsage(final ICommandSender sender) {
        return "pixelpatch.commands." + getCommandName() + ".usage";
    }

    /**
     * Lets you create a command.
     *
     * @param sender the command sender
     * @param args the command arguments
     * @throws CommandException command exception
     */
    public abstract void processCommand(final ICommandSender sender, final String[] args) throws CommandException;

    /**
     * Ensures the command sender is on Hypixel before commands are executed.
     *
     * @param sender the command sender
     * @throws CommandException command exception
     */
    public void hypixelCheck(final ICommandSender sender) throws CommandException {
        if (!this.onHypixel(sender)) {
            throw new NotOnHypixelException();
        }
    }

    /**
     * Tests if the command sender is on Hypixel.
     *
     * @param sender the command sender
     * @return true if on Hypixel
     */
    public boolean onHypixel(final ICommandSender sender) {
        if (sender instanceof EntityPlayer && !Minecraft.getMinecraft().isIntegratedServerRunning()) {
            for (final String ip : PixelPatchForge.HYPIXEL_SERVER_IPS) {
                if (Minecraft.getMinecraft().getCurrentServerData().serverIP.toLowerCase().endsWith("." + ip.toLowerCase()) ||
                        Minecraft.getMinecraft().getCurrentServerData().serverIP.equalsIgnoreCase(ip)) {
                    return true;
                }
            }
        }
        return false;
    }
}
