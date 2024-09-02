package stellarwitch7.six_houses.block.entity

import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import stellarwitch7.libstellar.ritual.Ritual

class WildAltarBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(ModBlockEntityTypes.wildAltar, pos, state) {
    private var ritual: Ritual? = null

    fun tick(world: World, pos: BlockPos, state: BlockState) {
        if (world is ServerWorld) {
            if (ritual?.advance(world) == true)
                ritual = null
        }
    }

    fun tryStartRitual(ritual: Ritual): Boolean {
        if (this.ritual == null) {
            this.ritual = ritual
            return true
        } else return false
    }
}