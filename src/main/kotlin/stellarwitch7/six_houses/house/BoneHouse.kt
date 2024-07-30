package stellarwitch7.six_houses.house

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.server.network.ServerPlayerEntity
import stellarwitch7.libstellar.registry.codec.CodecType
import stellarwitch7.six_houses.house.House.Companion.bone

class BoneHouse(private var spiritCasts: Int, private var inSpiritView: Boolean) : House {
    override val type: CodecType<House> = bone

    override fun tick(player: ServerPlayerEntity) {
    }

    fun getInSpiritView(): Boolean {
        return inSpiritView
    }

    companion object {
        val codec: MapCodec<BoneHouse> = RecordCodecBuilder.mapCodec { instance -> instance.group(
            Codec.INT.fieldOf("spirit_casts").forGetter(BoneHouse::spiritCasts),
            Codec.BOOL.fieldOf("in_spirit_view").forGetter(BoneHouse::inSpiritView)
        ).apply(instance, ::BoneHouse) }
    }
}