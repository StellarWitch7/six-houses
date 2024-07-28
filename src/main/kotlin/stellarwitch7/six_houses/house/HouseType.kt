package stellarwitch7.six_houses.house

import com.mojang.serialization.Lifecycle
import com.mojang.serialization.MapCodec
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.SimpleRegistry
import stellarwitch7.six_houses.SixHouses

data class HouseType<T : House>(val codec: MapCodec<T>) {
    companion object {
        private val REGISTRY_KEY: RegistryKey<Registry<HouseType<*>>> = RegistryKey.ofRegistry(SixHouses.id("house_type"))
        val REGISTRY: Registry<HouseType<*>> = SimpleRegistry(REGISTRY_KEY, Lifecycle.stable())

        val STORM: HouseType<StormHouse> = register("storm", StormHouse.CODEC)

        private fun <T : House> register(name: String, codec: MapCodec<T>): HouseType<T> {
            return Registry.register(REGISTRY, SixHouses.id(name), HouseType(codec))
        }
    }
}
