package stellarwitch7.six_houses.spell.trick

import dev.enjarai.trickster.spell.trick.Trick
import dev.enjarai.trickster.spell.trick.Tricks
import net.minecraft.registry.Registry
import stellarwitch7.libstellar.registry.Registrar
import stellarwitch7.six_houses.SixHouses
import stellarwitch7.six_houses.spell.trick.house.bone.SummonZombieTrick

object ModTricks : Registrar<Trick> {
    override val modID: String = SixHouses.modID
    override val registry: Registry<Trick> = Tricks.REGISTRY

    // BONE
    val SUMMON_ZOMBIE: SummonZombieTrick = register("summon_zombie", SummonZombieTrick())
}