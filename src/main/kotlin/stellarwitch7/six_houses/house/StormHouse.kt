package stellarwitch7.six_houses.house

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.server.network.ServerPlayerEntity

class StormHouse(private var deflectCooldown: Int) : House {
    override fun tick(player: ServerPlayerEntity) {
        deflectCooldown = if (deflectCooldown < 0) 0 else deflectCooldown - 1
    }

    override fun type(): HouseType<*> {
        return HouseType.STORM
    }

    companion object {
        val CODEC: MapCodec<StormHouse> = RecordCodecBuilder.mapCodec { instance -> instance.group(
            Codec.INT.fieldOf("deflect_cooldown").forGetter(StormHouse::deflectCooldown)
        ).apply(instance, ::StormHouse) }
    }
}