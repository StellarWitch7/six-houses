package stellarwitch7.six_houses.ritual.step

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.entity.ItemEntity
import net.minecraft.item.ItemStack
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import stellarwitch7.libstellar.registry.codec.CodecType
import stellarwitch7.six_houses.ritual.Ritual
import stellarwitch7.six_houses.ritual.step.Step.Companion.dropItem

class DropItemStep(val stack: ItemStack, val offset: BlockPos) : Step {
    override val type: CodecType<Step> = dropItem

    override fun apply(world: ServerWorld, ritual: Ritual, steps: ArrayDeque<Step>): Boolean {
        val offsetPos = ritual.pos.add(offset)
        world.spawnParticles(ParticleTypes.FLASH,
            offsetPos.x.toDouble(), offsetPos.y.toDouble(), offsetPos.z.toDouble(),
            1, 0.0, 0.0, 0.0, 0.0)
        world.spawnEntity(ItemEntity(world,
            offsetPos.x.toDouble(),
            offsetPos.y.toDouble(),
            offsetPos.z.toDouble(),
            stack))
        return true
    }

    companion object {
        val codec: MapCodec<DropItemStep> = RecordCodecBuilder.mapCodec { builder ->
            builder.group(
                ItemStack.CODEC.fieldOf("stack").forGetter(DropItemStep::stack),
                BlockPos.CODEC.fieldOf("offset").forGetter(DropItemStep::offset)
            ).apply(builder, ::DropItemStep)
        }
    }
}