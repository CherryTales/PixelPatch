package com.cherrytales.pixelpatch.mixins;

import com.cherrytales.pixelpatch.blinder.HiddenPlayers;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Render manager mixin to disable player rendering.
 */
@Mixin(RenderManager.class)
public class MixinRenderManager {

    /**
     * Injection into shouldRender() in order to stop players from rendering.
     *
     * @param entityIn the entity to check if the client should render
     * @param camera the camera
     * @param camX the camera's x coordinate
     * @param camY the camera's y coordinate
     * @param camZ the camera's z coordinate
     * @param cir the callback info
     */
    @Inject(method = "shouldRender", at = @At("HEAD"), cancellable = true)
    public void shouldRender(final Entity entityIn, final ICamera camera, final double camX, final double camY,
                             final double camZ, final CallbackInfoReturnable<Boolean> cir) {
        if (entityIn instanceof EntityOtherPlayerMP && HiddenPlayers.checkPlayer(((EntityOtherPlayerMP) entityIn).getGameProfile())) {
            cir.setReturnValue(false);
        }
    }

}
