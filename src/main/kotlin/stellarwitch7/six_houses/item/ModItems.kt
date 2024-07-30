package stellarwitch7.six_houses.item

import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import stellarwitch7.libstellar.registry.item.ItemRegistrar
import stellarwitch7.six_houses.SixHouses
import stellarwitch7.six_houses.block.ModBlocks

object ModItems : ItemRegistrar {
    override val modID: String
        get() = SixHouses.MOD_ID

    val WILD_ALTAR: BlockItem = register("wild_altar", BlockItem(ModBlocks.WILD_ALTAR, Item.Settings()))
}