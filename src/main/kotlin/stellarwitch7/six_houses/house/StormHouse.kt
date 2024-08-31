package stellarwitch7.six_houses.house

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.server.network.ServerPlayerEntity
import stellarwitch7.libstellar.registry.codec.CodecType
import stellarwitch7.six_houses.house.House.Companion.storm

class StormHouse(private var deflectCooldown: Int) : House {
    override val type: CodecType<House> = storm

    override fun tick(player: ServerPlayerEntity) {
        deflectCooldown = if (deflectCooldown < 0) 0 else deflectCooldown - 1
    }

    override fun triggerPrimaryAbility(player: ServerPlayerEntity) {
        TODO("Not yet implemented")
    }

    companion object {
        val codec: MapCodec<StormHouse> = RecordCodecBuilder.mapCodec { instance -> instance.group(
            Codec.INT.fieldOf("deflect_cooldown").forGetter(StormHouse::deflectCooldown)
        ).apply(instance, ::StormHouse) }
    }
}