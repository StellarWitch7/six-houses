package stellarwitch7.six_houses.ritual.step

import net.minecraft.registry.Registry
import net.minecraft.server.world.ServerWorld
import stellarwitch7.libstellar.registry.codec.CodecRegistrar
import stellarwitch7.libstellar.registry.codec.CodecType
import stellarwitch7.libstellar.registry.codec.CodecTypeProvider
import stellarwitch7.six_houses.SixHouses
import stellarwitch7.six_houses.ritual.Ritual

interface Step : CodecTypeProvider<Step> {
    fun apply(world: ServerWorld, ritual: Ritual, steps: ArrayDeque<Step>): Boolean

    companion object : CodecRegistrar<Step> {
        override val modID: String = SixHouses.modID
        override val name: String = "step"
        override val registry: Registry<CodecType<Step>> = makeReg()

        val sleep: CodecType<Step> = register("sleep", SleepStep.codec)
        val clearCandle: CodecType<Step> = register("clear_candle", ClearCandleStep.codec)
        val consumeItem: CodecType<Step> = register("consume_item", ConsumeItemStep.codec)
        val dropItem: CodecType<Step> = register("drop_item", DropItemStep.codec)
    }
}