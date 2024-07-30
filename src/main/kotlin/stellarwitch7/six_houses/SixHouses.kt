package stellarwitch7.six_houses

import stellarwitch7.six_houses.item.ModItems
import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import stellarwitch7.six_houses.block.ModBlocks
import stellarwitch7.six_houses.block.entity.ModBlockEntityTypes

object SixHouses : ModInitializer {
	val MOD_ID: String = "six-houses"
    val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("-- init --")
		LOGGER.info("By this oath I pledge myself, to my House as I have my coven, to serve until my blood has hollowed me out")
		LOGGER.info("-- init --")

		ModItems.register()
		ModBlocks.register()
		ModBlockEntityTypes.register()
	}

	fun id(name: String): Identifier {
		return Identifier.of(MOD_ID, name)
	}
}