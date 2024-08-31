package stellarwitch7.six_houses.spell.fragment

import dev.enjarai.trickster.spell.fragment.FragmentType
import net.minecraft.registry.Registry
import stellarwitch7.libstellar.registry.Registrar
import stellarwitch7.six_houses.SixHouses

object ModFragmentTypes : Registrar<FragmentType<*>> {
    override val modID: String = SixHouses.modID
    override val registry: Registry<FragmentType<*>> = FragmentType.REGISTRY
}