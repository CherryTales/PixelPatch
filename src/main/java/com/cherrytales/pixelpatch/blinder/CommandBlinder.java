package com.cherrytales.pixelpatch.blinder;

import com.cherrytales.pixelpatch.hypixel.HypixelCommand;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * The blinder command.
 */
public class CommandBlinder extends HypixelCommand {

    /**
     * Gets the name of the command.
     */
    @Override
    public String getCommandName() {
        return "blinder";
    }

    /**
     * When the Blinder command is executed, this code executes.
     *
     * @param sender the command sender
     * @param args the command arguments
     */
    @Override
    public void processCommand(final ICommandSender sender, final String[] args) throws CommandException {
        hypixelCheck(sender);
        if (args.length < 2) {
            throw new WrongUsageException("pixelpatch.commands.blinder.usage");
        } else {
            if (!(sender instanceof EntityPlayer)) {
                throw new CommandException("pixelpatch.commands.blinder.playersOnly");
            } else {
                final EntityPlayer commandSenderPlayer = (EntityPlayer) sender;
                final EntityPlayerMP entityPlayer = getPlayer(sender, args[1]);
                if ("show".equalsIgnoreCase(args[0])) {
                } else if ("hide".equalsIgnoreCase(args[0])) {
                } else {
                    throw new WrongUsageException("pixelpatch.commands.blinder.usage");
                }
            }
        }
    }

}
