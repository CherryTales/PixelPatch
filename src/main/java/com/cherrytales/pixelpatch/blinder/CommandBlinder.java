package com.cherrytales.pixelpatch.blinder;

import com.cherrytales.pixelpatch.hypixel.HypixelCommand;
import java.util.List;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;

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
                //TODO fix crash
                //final EntityPlayer entityPlayer = getPlayer(sender, args[1]);
                final String entityPlayer = args[1];
                if ("show".equalsIgnoreCase(args[0])) {
                    if (!HiddenPlayers.checkPlayer(entityPlayer)) {
                        final ChatComponentTranslation playerAlreadyShown = new ChatComponentTranslation(
                        "pixelpatch.commands.blinder.alreadyShownPlayer", entityPlayer);
                        playerAlreadyShown.getChatStyle().setColor(EnumChatFormatting.GREEN);
                        sender.addChatMessage(playerAlreadyShown);
                    } else {
                        HiddenPlayers.removeHiddenPlayer(entityPlayer);

                        final ChatComponentTranslation shownPlayerMessage = new ChatComponentTranslation(
                        "pixelpatch.commands.blinder.shownPlayer", entityPlayer);
                        shownPlayerMessage.getChatStyle().setColor(EnumChatFormatting.GREEN);
                        sender.addChatMessage(shownPlayerMessage);
                    }
                } else if ("hide".equalsIgnoreCase(args[0])) {
                    if (HiddenPlayers.checkPlayer(entityPlayer)) {
                        final ChatComponentTranslation playerAlreadyHidden = new ChatComponentTranslation(
                        "pixelpatch.commands.blinder.alreadyHiddenPlayer", entityPlayer);
                        playerAlreadyHidden.getChatStyle().setColor(EnumChatFormatting.GREEN);
                        sender.addChatMessage(playerAlreadyHidden);
                    } else {
                        HiddenPlayers.addHiddenPlayer(entityPlayer);

                        final ChatComponentTranslation hidePlayerMessage = new ChatComponentTranslation(
                        "pixelpatch.commands.blinder.hidePlayer", entityPlayer);
                        hidePlayerMessage.getChatStyle().setColor(EnumChatFormatting.GREEN);
                        sender.addChatMessage(hidePlayerMessage);
                    }
                } else {
                    throw new WrongUsageException("pixelpatch.commands.blinder.usage");
                }
            }
        }
    }

    @Override
    public List<String> addTabCompletionOptions(final ICommandSender sender, final String[] args, final BlockPos pos) {
        if (onHypixel(sender)) {
            if (args.length == 1) {
                return getListOfStringsMatchingLastWord(args, "show", "hide");
            } else if (args.length == 2) {
                //TODO crashes game
                return getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames());
            }
        }
        return null;
    }
}
