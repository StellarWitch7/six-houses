package stellarwitch7.six_houses.cca

import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import org.ladysnake.cca.api.v3.component.Component
import org.ladysnake.cca.api.v3.component.ComponentKey
import org.ladysnake.cca.api.v3.component.ComponentRegistry
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy
import stellarwitch7.six_houses.SixHouses
import kotlin.reflect.KClass

object ModEntityComponents : EntityComponentInitializer {
    private val registered: ArrayList<(EntityComponentFactoryRegistry) -> Unit> = ArrayList()

    val WITCH: ComponentKey<WitchComponent> = register("witch", WitchComponent::class, ::WitchComponent, RespawnCopyStrategy.ALWAYS_COPY)

    private fun <C : Component> makeKey(name: String, c: KClass<C>): ComponentKey<C> {
        val result = ComponentRegistry.getOrCreate(SixHouses.id(name), c.java)
        return result;
    }

    private fun <C : Component, T : Entity> register(name: String, c: KClass<C>, factory: (Entity) -> C, t: KClass<T>): ComponentKey<C> {
        val result = makeKey(name, c)
        registered.add { registry -> registry.registerFor(t.java, result, factory) }
        return result;
    }

    private fun <C : Component> register(name: String, c: KClass<C>, factory: (PlayerEntity) -> C, copyStrat: RespawnCopyStrategy<Component>): ComponentKey<C> {
        val result = makeKey(name, c)
        registered.add { registry -> registry.registerForPlayers(result, factory, copyStrat) }
        return result;
    }

    override fun registerEntityComponentFactories(registry: EntityComponentFactoryRegistry) {
        for (fn in registered) {
            fn(registry)
        }
    }
}