package stellarwitch7.six_houses.item

import dev.enjarai.trickster.effects.ModEffects
import net.minecraft.block.Blocks
import net.minecraft.block.CandleBlock
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ItemUsageContext
import net.minecraft.item.ToolItem
import net.minecraft.item.ToolMaterials
import net.minecraft.registry.tag.BlockTags
import net.minecraft.util.ActionResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class SacrificialBladeItem : ToolItem(ToolMaterials.IRON, Settings()) {
    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        val world = context.world
        val pos = context.blockPos
        val state = world.getBlockState(pos)
        val player = context.player

        if (!world.isClient
            && (state.isOf(Blocks.WATER_CAULDRON)
                    && player?.hasStatusEffect(ModEffects.MANA_BOOST) == true)
            && player.offHandStack.isOf(dev.enjarai.trickster.item.ModItems.TOME_OF_TOMFOOLERY)
            && validateSetup(world, pos)
        ) {
            //TODO: use `world.moonPhase` to determine what House to give the player
            player.equipStack(EquipmentSlot.OFFHAND, ModItems.grimoire.defaultStack)
            player.clearStatusEffects()
            //TODO: use a min value float to get head tilt
            player.health = 1F

            return ActionResult.CONSUME
        } else return super.useOnBlock(context)
    }

    private fun validateSetup(world: World, pos: BlockPos): Boolean {
        for (offsetPos in offsets(pos)) {
            val state = world.getBlockState(offsetPos)

            if (!state.isIn(BlockTags.CANDLES) || !state.get(CandleBlock.LIT))
                return false

            world.setBlockState(offsetPos, state.with(CandleBlock.LIT, false))
        }

        return true
    }

    private fun offsets(pos: BlockPos): List<BlockPos> {
        return listOf(pos.east(3),
            pos.east(2).north(2),
            pos.north(3),
            pos.north(2).west(2),
            pos.west(3),
            pos.west(2).south(2),
            pos.south(3),
            pos.south(2).east(2))
    }
}