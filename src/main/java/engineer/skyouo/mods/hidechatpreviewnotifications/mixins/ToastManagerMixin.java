package engineer.skyouo.mods.hidechatpreviewnotifications.mixins;

import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Arrays;
import java.util.Deque;
import java.util.List;

@Mixin(ToastComponent.class)
public class ToastManagerMixin implements MixinContainer {
    @Redirect(at = @At(value = "INVOKE", target = "Ljava/util/Deque;add(Ljava/lang/Object;)Z"), method = "addToast")
    public boolean working(Deque instance, Object e) {
        assert e instanceof Toast;
        if (e instanceof SystemToast toast) {
            if (toast.getToken().equals(SystemToast.SystemToastIds.CHAT_PREVIEW_WARNING) || toast.getToken().equals(SystemToast.SystemToastIds.UNSECURE_SERVER_WARNING)) return true;
        }
        instance.add(e);
        return true;
    }
    @Override
    public List<String> getSpecifiedVersion() {
        return Arrays.asList("1.19", "1.19.1");
    }
}
