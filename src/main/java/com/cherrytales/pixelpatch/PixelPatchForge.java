package com.cherrytales.pixelpatch;

import com.cherrytales.pixelpatch.blinder.CommandBlinder;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.init.Blocks;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import static com.cherrytales.pixelpatch.PixelPatchForge.MODVERSION;

/**
 * Pixel Patch Forge Main Class.
 */
@Mod(modid = PixelPatchForge.MODID, version = MODVERSION)
public class PixelPatchForge {
    public static final String MODID = "pixelpatch";
    public static final String MODVERSION = "@pixelpatch-version@";
    public static final List<String> HYPIXEL_SERVER_IPS = new ArrayList<>();

    /**
     * Initializes the PixelPatch mod.
     *
     * @param event Initialization event
     */
    @EventHandler
    public void init(final FMLInitializationEvent event) {
        System.out.println("DIRT BLOCK >> " + Blocks.dirt.getUnlocalizedName());
        ClientCommandHandler.instance.registerCommand(new CommandBlinder());
        this.setupIps();
    }

    private void setupIps() {
        HYPIXEL_SERVER_IPS.add(".hypixel.net");
    }
}
