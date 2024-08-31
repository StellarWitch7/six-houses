package stellarwitch7.six_houses.house

import net.minecraft.registry.Registry
import net.minecraft.server.network.ServerPlayerEntity
import stellarwitch7.libstellar.registry.codec.CodecRegistrar
import stellarwitch7.libstellar.registry.codec.CodecType
import stellarwitch7.libstellar.registry.codec.CodecTypeProvider
import stellarwitch7.six_houses.SixHouses

interface House : CodecTypeProvider<House> {
    fun tick(player: ServerPlayerEntity)
    fun triggerPrimaryAbility(player: ServerPlayerEntity)

    companion object : CodecRegistrar<House> {
        override val modID: String = SixHouses.modID
        override val name: String = "house_type"
        override val registry: Registry<CodecType<House>> = makeReg()

        val storm: CodecType<House> = register("storm", StormHouse.codec)
        val bone: CodecType<House> = register("bone", BoneHouse.codec)
    }
}