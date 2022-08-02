package engineer.skyouo.mods.hidechatpreviewnotifications.mixins;

import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.client.multiplayer.ClientPacketListener;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Collections;
import java.util.List;

@Mixin(ClientPacketListener.class)
public class ClientPlayerNetworkHandlerMixin implements MixinContainer {
    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/toasts/ToastComponent;addToast(Lnet/minecraft/client/gui/components/toasts/Toast;)V"), method = "handleServerData")
    private void addToast(ToastComponent instance, Toast toast) { }

    @Override
    public List<String> getSpecifiedVersion() {
        return Collections.singletonList("1.19.1");
    }
}
