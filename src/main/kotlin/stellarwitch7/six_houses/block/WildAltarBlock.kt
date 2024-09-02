package stellarwitch7.six_houses.block

import com.mojang.serialization.MapCodec
import net.minecraft.block.BlockState
import net.minecraft.block.BlockWithEntity
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import stellarwitch7.six_houses.block.entity.ModBlockEntityTypes
import stellarwitch7.six_houses.block.entity.WildAltarBlockEntity

class WildAltarBlock(settings: Settings) : BlockWithEntity(settings) {
    override fun getCodec(): MapCodec<out BlockWithEntity> {
        return createCodec(::WildAltarBlock)
    }

    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return WildAltarBlockEntity(pos, state)
    }

    override fun <T : BlockEntity> getTicker(world: World, state: BlockState, type: BlockEntityType<T>): BlockEntityTicker<T>? {
        return validateTicker(type, ModBlockEntityTypes.wildAltar, { a, b, c, d -> d.tick(a, b, c) })
    }
}