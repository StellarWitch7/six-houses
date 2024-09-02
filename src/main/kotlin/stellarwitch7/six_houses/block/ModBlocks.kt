package stellarwitch7.six_houses.block

import net.minecraft.block.AbstractBlock
import stellarwitch7.libstellar.registry.block.BlockRegistrar
import stellarwitch7.six_houses.SixHouses

object ModBlocks : BlockRegistrar {
    override val modID: String
        get() = SixHouses.MOD_ID
    val wildAltar: WildAltarBlock = register("wild_altar_block", WildAltarBlock(AbstractBlock.Settings.create()))
}