package stellarwitch7.six_houses.ritual

import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.registry.Registry
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import stellarwitch7.libstellar.registry.codec.CodecRegistrar
import stellarwitch7.libstellar.registry.codec.CodecType
import stellarwitch7.libstellar.registry.codec.CodecTypeProvider
import stellarwitch7.six_houses.SixHouses
import stellarwitch7.six_houses.ritual.step.Step

//TODO: this should probably be in libstellar as it may be useful outside of this project
abstract class Ritual(val pos: BlockPos, steps: ArrayDeque<Step>) : CodecTypeProvider<Ritual> {
    var steps = steps
        protected set

    abstract fun init(world: ServerWorld): Ritual

    abstract fun fail(world: ServerWorld)

    fun advance(world: ServerWorld): Boolean {
        return when (steps.removeFirstOrNull()?.apply(world, this, steps)) {
            true -> false
            false -> { fail(world); true }
            null -> true
        }
    }

    companion object : CodecRegistrar<Ritual> {
        override val modID: String = SixHouses.modID
        override val name: String = "ritual"
        override val registry: Registry<CodecType<Ritual>> = makeReg()

        val initiation: CodecType<Ritual> = register("initiation", ::InitiationRitual)

        fun <T : Ritual> register(name: String, constructor: (BlockPos, ArrayDeque<Step>) -> T): CodecType<Ritual> {
            return register(name, RecordCodecBuilder.mapCodec { builder ->
                builder.group(
                    BlockPos.CODEC.fieldOf("pos").forGetter(Ritual::pos),
                    //TODO: move the xmap to libstellar
                    Step.codec.codec().listOf().xmap(::ArrayDeque, ArrayDeque<Step>::toList).fieldOf("steps").forGetter(Ritual::steps)
                ).apply(builder, constructor)
            })
        }
    }
}