package stellarwitch7.six_houses.house

import com.google.common.base.Supplier
import com.google.common.base.Suppliers
import com.mojang.serialization.MapCodec
import net.minecraft.server.network.ServerPlayerEntity

interface House {
    fun tick(player: ServerPlayerEntity)

    fun type(): HouseType<*>

    companion object {
        val CODEC: Supplier<MapCodec<House>> = Suppliers.memoize { HouseType.REGISTRY.codec.dispatchMap(House::type, HouseType<*>::codec) }
    }
}