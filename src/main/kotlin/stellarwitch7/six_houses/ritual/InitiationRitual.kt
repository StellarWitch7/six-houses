package stellarwitch7.six_houses.ritual

import net.minecraft.entity.EntityType
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import stellarwitch7.libstellar.registry.codec.CodecType
import stellarwitch7.six_houses.item.ModItems
import stellarwitch7.six_houses.ritual.step.*

class InitiationRitual(pos: BlockPos, steps: ArrayDeque<Step>) : Ritual(pos, steps) {
    override val type: CodecType<Ritual> = initiation

    override fun init(world: ServerWorld): InitiationRitual {
        this.steps = ArrayDeque(listOf(
            ClearCandleStep(BlockPos(3, 0, 0)),
            SleepStep(20),
            ClearCandleStep(BlockPos(2, 0, 2)),
            SleepStep(20),
            ClearCandleStep(BlockPos(0, 0, 3)),
            SleepStep(20),
            ClearCandleStep(BlockPos(-2, 0, 2)),
            SleepStep(20),
            ClearCandleStep(BlockPos(-3, 0, 0)),
            SleepStep(20),
            ClearCandleStep(BlockPos(-2, 0, -2)),
            SleepStep(20),
            ClearCandleStep(BlockPos(0, 0, -3)),
            SleepStep(20),
            ClearCandleStep(BlockPos(2, 0, -2)),
            SleepStep(20),
            ConsumeItemStep(dev.enjarai.trickster.item.ModItems.TOME_OF_TOMFOOLERY, 2.0),
            DropItemStep(ModItems.grimoire.defaultStack, BlockPos(0, 1, 0))
        ))

        return this
    }

    override fun fail(world: ServerWorld) {
        val entity = EntityType.LIGHTNING_BOLT.create(world)

        entity?.let {
            it.setPos(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())
            world.spawnEntity(it)
        }
    }
}