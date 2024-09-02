package stellarwitch7.six_houses.item

import dev.enjarai.trickster.effects.ModEffects
import net.minecraft.item.ItemUsageContext
import net.minecraft.item.ToolItem
import net.minecraft.item.ToolMaterials
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.ActionResult
import stellarwitch7.six_houses.block.ModBlocks
import stellarwitch7.six_houses.block.entity.WildAltarBlockEntity
import stellarwitch7.six_houses.ritual.InitiationRitual

class SacrificialBladeItem : ToolItem(ToolMaterials.IRON, Settings()) {
    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        val world = context.world
        val pos = context.blockPos
        val state = world.getBlockState(pos)
        val player = context.player

        if (world is ServerWorld && state.isOf(ModBlocks.wildAltar) && player?.hasStatusEffect(ModEffects.MANA_BOOST) == true) {
            val blockEntity = world.getBlockEntity(pos)

            if (blockEntity is WildAltarBlockEntity) {
                //TODO: this should use a stone tablet on the ritual instead to select which ritual
                if (blockEntity.tryStartRitual(InitiationRitual(pos, ArrayDeque()).init(world))) {
                    player.clearStatusEffects()
//                    player.damage(/*TODO*/, Float.MIN_VALUE)
                    player.health = 1F

                    return ActionResult.success(world.isClient())
                }
            }

            return ActionResult.PASS
        } else return super.useOnBlock(context)
    }
}