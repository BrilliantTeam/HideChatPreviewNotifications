package engineer.skyouo.mods.hidechatpreviewnotifications.mixins;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Collections;
import java.util.List;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayerNetworkHandlerMixin implements MixinContainer {
    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/toast/ToastManager;add(Lnet/minecraft/client/toast/Toast;)V"), method = "onServerMetadata")
    private void addToast(ToastManager instance, Toast toast) { }

    @Override
    public List<String> getSpecifiedVersion() {
        return Collections.singletonList("1.19.1");
    }
}
