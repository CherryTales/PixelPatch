package com.cherrytales.pixelpatch;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * Pixel Patch Forge Main Class.
 */
@Mod(modid = PixelPatchForge.MODID)
public class PixelPatchForge {
    public static final String MODID = "pixelpatch";

    /**
     * Initializes the PixelPatch mod.
     *
     * @param event Initialization event
     */
    @EventHandler
    public void init(final FMLInitializationEvent event) {
        System.out.println("DIRT BLOCK >> " + Blocks.dirt.getUnlocalizedName());
    }
}
