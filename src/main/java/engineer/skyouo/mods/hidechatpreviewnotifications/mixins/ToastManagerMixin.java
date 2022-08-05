package engineer.skyouo.mods.hidechatpreviewnotifications.mixins;

import net.minecraft.client.toast.SystemToast;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.List;

@Mixin(ToastManager.class)
public class ToastManagerMixin {
    @Inject(at = @At(value = "HEAD"), method = "add", cancellable = true)
    public void working(Toast toast, CallbackInfo ci) {
        if (toast instanceof SystemToast) {
            SystemToast systemToast = (SystemToast) toast;
            if (systemToast.getType().equals(SystemToast.Type.CHAT_PREVIEW_WARNING) || systemToast.getType().equals(SystemToast.Type.UNSECURE_SERVER_WARNING)) {
                ci.cancel();
            }
        }
    }
}
