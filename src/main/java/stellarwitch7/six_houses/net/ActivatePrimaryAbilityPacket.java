package stellarwitch7.six_houses.net;

import io.wispforest.owo.network.ServerAccess;
import org.jetbrains.annotations.NotNull;
import stellarwitch7.libstellar.registry.owo.net.OwoPacketServerbound;
import stellarwitch7.six_houses.cca.entity.ModEntityComponents;

public record ActivatePrimaryAbilityPacket() implements OwoPacketServerbound<ActivatePrimaryAbilityPacket> {
    @Override
    public void handle(@NotNull ServerAccess serverAccess) {
        ModEntityComponents.INSTANCE.getWitch().get(serverAccess.player()).triggerPrimaryAbility();
    }
}
