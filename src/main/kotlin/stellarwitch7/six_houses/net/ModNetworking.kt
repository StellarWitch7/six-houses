package stellarwitch7.six_houses.net

import io.wispforest.owo.network.OwoNetChannel
import stellarwitch7.libstellar.registry.owo.net.OwoPacketRegistrar
import stellarwitch7.six_houses.SixHouses

object ModNetworking : OwoPacketRegistrar {
    override val modID: String = SixHouses.modID
    override val name: String = "main"
    override val channel: OwoNetChannel = makeChannel()

    override fun register() {
        registerServerbound(ActivatePrimaryAbilityPacket::class)
    }
}