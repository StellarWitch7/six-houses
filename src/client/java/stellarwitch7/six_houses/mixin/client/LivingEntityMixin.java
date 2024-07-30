package stellarwitch7.six_houses.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import stellarwitch7.six_houses.util.HouseQueriesKt;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @ModifyReturnValue(method = "hasStatusEffect", at = @At("RETURN"))
    private boolean inDarknessIWatch(boolean original, RegistryEntry<StatusEffect> effect) {
        return original || (getWorld().isClient && effect == StatusEffects.BLINDNESS && HouseQueriesKt.isInSpiritView());
    }

    @ModifyReturnValue(method = "getStatusEffect", at = @At("RETURN"))
    private StatusEffectInstance youSeekToUnderstand(StatusEffectInstance original, RegistryEntry<StatusEffect> effect) {
        if (original == null && getWorld().isClient && effect == StatusEffects.BLINDNESS && HouseQueriesKt.isInSpiritView()) {
            return new StatusEffectInstance(effect, 60, 0, false, false);
        }

        return original;
    }
}
