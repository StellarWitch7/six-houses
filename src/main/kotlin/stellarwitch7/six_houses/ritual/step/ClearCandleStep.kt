package stellarwitch7.six_houses.ritual.step

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.CandleBlock
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.tag.BlockTags
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import stellarwitch7.libstellar.registry.codec.CodecType
import stellarwitch7.six_houses.ritual.Ritual
import stellarwitch7.six_houses.ritual.step.Step.Companion.clearCandle

class ClearCandleStep(private val offset: BlockPos) : Step {
    override val type: CodecType<Step> = clearCandle

    override fun apply(world: ServerWorld, ritual: Ritual, steps: ArrayDeque<Step>): Boolean {
        val pos = ritual.pos.add(offset)
        val state = world.getBlockState(pos)

        if (state.isIn(BlockTags.CANDLES) && state.get(CandleBlock.LIT)) {
            world.setBlockState(pos, state.with(CandleBlock.LIT, false))
            world.spawnParticles(ParticleTypes.SOUL,
                pos.x.toDouble() + 0.5, pos.y.toDouble(), pos.z.toDouble() + 0.5,
                9, 0.2, 0.0, 0.2, 0.0
            )

            return true
        }

        return false
    }

    companion object {
        val codec: MapCodec<ClearCandleStep> = RecordCodecBuilder.mapCodec { builder ->
            builder.group(
                BlockPos.CODEC.fieldOf("offset").forGetter(ClearCandleStep::offset)
            ).apply(builder, ::ClearCandleStep)
        }
    }
}