package stellarwitch7.six_houses.ritual

import net.minecraft.registry.Registry
import stellarwitch7.libstellar.registry.codec.CodecRegistrar
import stellarwitch7.libstellar.registry.codec.CodecType
import stellarwitch7.libstellar.ritual.Ritual
import stellarwitch7.six_houses.SixHouses

object ModRituals : CodecRegistrar<Ritual> {
    override val modID: String = SixHouses.MOD_ID
    override val registry: Registry<CodecType<Ritual>> = Ritual.registry

    val initiation: CodecType<Ritual> = Ritual.register("initiation", ::InitiationRitual)
}