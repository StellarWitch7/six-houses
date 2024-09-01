package stellarwitch7.six_houses.block.entity

import stellarwitch7.libstellar.registry.block.entity.BlockEntityTypeRegistrar
import stellarwitch7.six_houses.SixHouses

object ModBlockEntityTypes : BlockEntityTypeRegistrar {
    override val modID: String
        get() = SixHouses.modID
}