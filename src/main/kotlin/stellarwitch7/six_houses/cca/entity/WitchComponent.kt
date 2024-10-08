package stellarwitch7.six_houses.cca.entity

import com.mojang.serialization.DataResult
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtOps
import net.minecraft.registry.RegistryWrapper
import net.minecraft.server.network.ServerPlayerEntity
import org.ladysnake.cca.api.v3.component.tick.ServerTickingComponent
import stellarwitch7.six_houses.house.House

class WitchComponent(private val player: PlayerEntity) : ServerTickingComponent {
    var house: House? = null
        private set

    override fun readFromNbt(tag: NbtCompound, registryLookup: RegistryWrapper.WrapperLookup) {
        val result: DataResult<House> = House.codec.parse(NbtOps.INSTANCE, tag.get("house"))

        if (result.hasResultOrPartial())
            house = result.resultOrPartial().orElseThrow()
    }

    override fun writeToNbt(tag: NbtCompound, registryLookup: RegistryWrapper.WrapperLookup) {
        house?.let {
            tag.put("house", House.codec.encodeStart(NbtOps.INSTANCE, it).result().orElseThrow())
        }
    }

    override fun serverTick() {
        house?.tick(player as ServerPlayerEntity)
    }

    fun triggerPrimaryAbility() {
        house?.triggerPrimaryAbility(player as ServerPlayerEntity)
    }

    fun claimHouse(house: House) {
        if (this.house != null)
            this.house = house
    }
}