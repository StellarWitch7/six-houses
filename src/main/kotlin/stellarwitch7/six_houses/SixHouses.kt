package stellarwitch7.six_houses

import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object SixHouses : ModInitializer {
    val LOGGER: Logger = LoggerFactory.getLogger("six-houses")

	override fun onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("By this oath I pledge myself, to my House as I have my coven, to serve until my blood has hollowed me out")
	}

	fun id(name: String): Identifier {
		return Identifier.of(name)
	}
}