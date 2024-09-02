package stellarwitch7.six_houses

import net.fabricmc.api.ClientModInitializer
import stellarwitch7.six_houses.net.ModClientNetworking

object SixHousesClient : ClientModInitializer {
	override fun onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		ModKeyBindings.register()
		ModClientNetworking.register()
	}
}