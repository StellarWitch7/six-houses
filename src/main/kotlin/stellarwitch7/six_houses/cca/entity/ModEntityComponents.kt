package stellarwitch7.six_houses.cca.entity

import org.ladysnake.cca.api.v3.component.ComponentKey
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy
import stellarwitch7.libstellar.registry.cca.entity.CCAEntityComponentRegistrar
import stellarwitch7.six_houses.SixHouses

object ModEntityComponents : CCAEntityComponentRegistrar() {
    override val modID: String = SixHouses.MOD_ID

    val WITCH: ComponentKey<WitchComponent> = register("witch", WitchComponent::class, ::WitchComponent, RespawnCopyStrategy.ALWAYS_COPY)
}