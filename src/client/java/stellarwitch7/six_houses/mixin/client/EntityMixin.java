package stellarwitch7.six_houses.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import stellarwitch7.six_houses.util.HouseQueriesKt;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow private Vec3d pos;
    @Shadow private World world;

    @ModifyReturnValue(method = "isGlowing", at = @At("RETURN"))
    private boolean maybeIAm(boolean original) {
        return original || (world.isClient
                && (MinecraftClient.getInstance().player instanceof PlayerEntity player)
                && HouseQueriesKt.isInSpiritView()
                && player.getPos().distanceTo(pos) < 25);
    }
}
