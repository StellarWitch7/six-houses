package stellarwitch7.six_houses.block

import stellarwitch7.libstellar.registry.block.BlockRegistrar
import stellarwitch7.six_houses.SixHouses

object ModBlocks : BlockRegistrar {
    override val modID: String
        get() = SixHouses.modID

    val WILD_ALTAR: WildAltarBlock = register("wild_altar_block", WildAltarBlock())
}