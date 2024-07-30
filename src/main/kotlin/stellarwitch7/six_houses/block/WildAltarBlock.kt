package stellarwitch7.six_houses.block

import com.mojang.serialization.MapCodec
import net.minecraft.block.BlockState
import net.minecraft.block.BlockWithEntity
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.math.BlockPos
import stellarwitch7.six_houses.block.entity.WildAltarBlockEntity

class WildAltarBlock : BlockWithEntity(Settings.create()) {
    override fun getCodec(): MapCodec<out BlockWithEntity> {
        TODO("Not yet implemented")
    }

    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return WildAltarBlockEntity(pos, state)
    }
}