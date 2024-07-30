package stellarwitch7.six_houses.util

import net.minecraft.client.MinecraftClient
import net.minecraft.entity.player.PlayerEntity
import stellarwitch7.six_houses.cca.entity.ModEntityComponents.witch
import stellarwitch7.six_houses.house.BoneHouse

fun isInSpiritView(): Boolean {
    val player = MinecraftClient.getInstance().player
    if (player !is PlayerEntity) return false

    val bone = witch[player].house
    if (bone !is BoneHouse) return false

    return bone.getInSpiritView()
}