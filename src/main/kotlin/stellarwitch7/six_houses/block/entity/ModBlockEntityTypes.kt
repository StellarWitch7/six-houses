package stellarwitch7.six_houses.block.entity

import net.minecraft.block.entity.BlockEntityType
import stellarwitch7.libstellar.registry.block.entity.BlockEntityTypeRegistrar
import stellarwitch7.six_houses.SixHouses
import stellarwitch7.six_houses.block.ModBlocks

object ModBlockEntityTypes : BlockEntityTypeRegistrar {
    override val modID: String
        get() = SixHouses.modID

    val WILD_ALTAR: BlockEntityType<WildAltarBlockEntity> = register("wild_altar", BlockEntityType.Builder.create(::WildAltarBlockEntity, ModBlocks.WILD_ALTAR).build(null))
}