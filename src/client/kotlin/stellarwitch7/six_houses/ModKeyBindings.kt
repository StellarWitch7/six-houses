package stellarwitch7.six_houses

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBinding
import org.lwjgl.glfw.GLFW
import stellarwitch7.six_houses.net.ActivatePrimaryAbilityPacket
import stellarwitch7.six_houses.net.ModNetworking

object ModKeyBindings {
    val activateAbility: KeyBinding = register(KeyBinding("key.six-houses.activate_ability", GLFW.GLFW_KEY_V, SixHouses.MOD_ID))

    fun register(binding: KeyBinding): KeyBinding {
        return KeyBindingHelper.registerKeyBinding(binding)
    }

    fun register() {
        ClientTickEvents.END_CLIENT_TICK.register { client ->
            val player = client.player

            if (player != null && client.currentScreen == null) {
                if (activateAbility.wasPressed()) {
                    ModNetworking.channel.clientHandle().send(ActivatePrimaryAbilityPacket())
                }
            }
        }
    }
}