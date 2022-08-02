package engineer.skyouo.mods.hidechatpreviewnotifications.mixins;

import net.minecraft.client.toast.SystemToast;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Arrays;
import java.util.Deque;
import java.util.List;

@Mixin(ToastManager.class)
public class ToastManagerMixin implements MixinContainer {
    @Redirect(at = @At(value = "INVOKE", target = "Ljava/util/Deque;add(Ljava/lang/Object;)Z"), method = "add")
    public boolean working(Deque instance, Object e) {
        assert e instanceof Toast;
        if (e instanceof SystemToast) {
            SystemToast toast = (SystemToast) e;
            if (toast.getType().equals(SystemToast.Type.CHAT_PREVIEW_WARNING) || toast.getType().equals(SystemToast.Type.UNSECURE_SERVER_WARNING)) return true;
        }
        instance.add(e);
        return true;
    }
    @Override
    public List<String> getSpecifiedVersion() {
        return Arrays.asList("1.19", "1.19.1");
    }
}
