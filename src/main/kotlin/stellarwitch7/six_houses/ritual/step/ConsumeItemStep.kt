package stellarwitch7.six_houses.ritual.step

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.entity.Entity
import net.minecraft.entity.ItemEntity
import net.minecraft.item.Item
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.Registries
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.TypeFilter
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3d
import stellarwitch7.libstellar.registry.codec.CodecType
import stellarwitch7.six_houses.ritual.Ritual
import stellarwitch7.six_houses.ritual.step.Step.Companion.consumeItem

class ConsumeItemStep(private val item: Item, private val radius: Double = 0.0, private var count: Int = 1) : Step {
    override val type: CodecType<Step> = consumeItem

    //TODO: add a way to make consumed items pop back out?
    override fun apply(world: ServerWorld, ritual: Ritual, steps: ArrayDeque<Step>): Boolean {
        if (count < 1)
            return true

        val offset1 = Vec3d(radius, radius, radius)
        val offset2 = offset1.negate()
        val entities = world.getEntitiesByType(TypeFilter.instanceOf(ItemEntity::class.java),
            Box(ritual.pos.toCenterPos().add(offset1), ritual.pos.toCenterPos().add(offset2)),
            { a -> a.stack.isOf(item) })

        for ((i, entity) in entities.withIndex()) {
            if (entity.stack.count > count) {
                entity.stack.decrement(count)
                return true
            } else if (entity.stack.count == count) {
                discard(world, entity)
                return true
            } else {
                count -= entity.stack.count
                entities.removeAt(i)
                discard(world, entity)
            }
        }

        return false
    }

    private fun discard(world: ServerWorld, entity: Entity) {
        entity.discard()
        world.spawnParticles(ParticleTypes.POOF,
            entity.x, entity.y, entity.z,
            1, 0.0, 0.0, 0.0, 0.0
        )
    }

    companion object {
        val codec: MapCodec<ConsumeItemStep> = RecordCodecBuilder.mapCodec { builder ->
            builder.group(
                Registries.ITEM.codec.fieldOf("item").forGetter(ConsumeItemStep::item),
                Codec.DOUBLE.fieldOf("radius").forGetter(ConsumeItemStep::radius),
                Codec.INT.fieldOf("count").forGetter(ConsumeItemStep::count)
            ).apply(builder, ::ConsumeItemStep)
        }
    }
}