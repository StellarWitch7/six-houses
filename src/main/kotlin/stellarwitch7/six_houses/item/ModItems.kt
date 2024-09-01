package stellarwitch7.six_houses.item

import stellarwitch7.libstellar.registry.item.ItemRegistrar
import stellarwitch7.six_houses.SixHouses

object ModItems : ItemRegistrar {
    override val modID: String
        get() = SixHouses.modID

    val grimoire: GrimoireItem = register("grimoire", GrimoireItem())
    val sacrificialBlade: SacrificialBladeItem = register("blade_of_sacrifice", SacrificialBladeItem())
}