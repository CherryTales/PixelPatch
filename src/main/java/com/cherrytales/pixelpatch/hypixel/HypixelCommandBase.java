package com.cherrytales.pixelpatch.hypixel;

import com.cherrytales.pixelpatch.PixelPatchForge;
import com.cherrytales.pixelpatch.utilities.PixelPatchCommandBase;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Commands which can only be used on Hypixel.
 */
public abstract class HypixelCommandBase extends PixelPatchCommandBase {

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
