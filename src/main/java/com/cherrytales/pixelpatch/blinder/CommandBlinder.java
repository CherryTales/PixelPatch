package com.cherrytales.pixelpatch.blinder;

import com.cherrytales.pixelpatch.hypixel.HypixelCommandBase;
import com.mojang.authlib.GameProfile;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;

/**
 * The blinder command.
 */
public class CommandBlinder extends HypixelCommandBase {

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
                if (Minecraft.getMinecraft().getNetHandler().getPlayerInfo(args[1]) == null) {
                    throw new CommandException("pixelpatch.commands.blinder.cannotFindPlayer", args[1]);
                }
                final GameProfile entityPlayer =
                    Minecraft.getMinecraft().getNetHandler().getPlayerInfo(args[1]).getGameProfile();
                if (entityPlayer == null || entityPlayer.equals(((EntityPlayer) sender).getGameProfile()) ) {
                    throw new CommandException("pixelpatch.commands.blinder.cannotFindPlayer", args[1]);
                } else {
                    if ("show".equalsIgnoreCase(args[0])) {
                        if (!HiddenPlayers.checkPlayer(entityPlayer)) {
                            final ChatComponentTranslation playerAlreadyShown = new ChatComponentTranslation(
                                "pixelpatch.commands.blinder.alreadyShownPlayer", entityPlayer.getName());
                            playerAlreadyShown.getChatStyle().setColor(EnumChatFormatting.RED);
                            sender.addChatMessage(playerAlreadyShown);
                        } else {
                            HiddenPlayers.removeHiddenPlayer(entityPlayer);

                            final ChatComponentTranslation shownPlayerMessage = new ChatComponentTranslation(
                                "pixelpatch.commands.blinder.shownPlayer", entityPlayer.getName());
                            shownPlayerMessage.getChatStyle().setColor(EnumChatFormatting.GREEN);
                            sender.addChatMessage(shownPlayerMessage);
                        }
                    } else if ("hide".equalsIgnoreCase(args[0])) {
                        if (HiddenPlayers.checkPlayer(entityPlayer)) {
                            final ChatComponentTranslation playerAlreadyHidden = new ChatComponentTranslation(
                                "pixelpatch.commands.blinder.alreadyHiddenPlayer", entityPlayer.getName());
                            playerAlreadyHidden.getChatStyle().setColor(EnumChatFormatting.RED);
                            sender.addChatMessage(playerAlreadyHidden);
                        } else {
                            HiddenPlayers.addHiddenPlayer(entityPlayer);

                            final ChatComponentTranslation hidePlayerMessage = new ChatComponentTranslation(
                                "pixelpatch.commands.blinder.hidePlayer", entityPlayer.getName());
                            hidePlayerMessage.getChatStyle().setColor(EnumChatFormatting.GREEN);
                            sender.addChatMessage(hidePlayerMessage);
                        }
                    } else {
                        throw new WrongUsageException("pixelpatch.commands.blinder.usage");
                    }
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
                if ("hide".equalsIgnoreCase(args[0])) {
                    return getListOfStringsMatchingLastWord(args, allPlayerNames(sender.getName()));
                } else if ("show".equalsIgnoreCase(args[0])) {
                    // Outputs the hidden players list
                    final List<String> hiddenPlayerNames = new ArrayList<>();
                    for (final GameProfile hiddenPlayer : HiddenPlayers.hiddenPlayers()) {
                        if (hiddenPlayer.getName().matches("^[a-zA-Z0-9_]{2,16}$")) {
                            hiddenPlayerNames.add(hiddenPlayer.getName());
                        }
                    }
                    return getListOfStringsMatchingLastWord(args, hiddenPlayerNames);
                }
            }
        }
        return null;
    }
}
