package engineer.skyouo.mods.hidechatpreviewnotifications.mixins;

import net.minecraft.client.multiplayer.ServerData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.List;

@Mixin(ServerData.ChatPreview.class)
public class ChatPreviewMixin implements MixinContainer {
    @Shadow private boolean acknowledged;

    @Shadow private boolean toastShown;

    @Inject(at = @At(value = "TAIL"), method = "<init>")
    private void init(boolean acknowledged, boolean toastShown, CallbackInfo ci) {
        this.acknowledged = true;
        this.toastShown = true;
    }

    @Inject(at = @At(value = "RETURN"), method = "isAcknowledged", cancellable = true)
    private void isAcknowledged(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }

    @Inject(at = @At(value = "RETURN"), method = "showToast", cancellable = true)
    private void showToast(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }


    @Override
    public List<String> getSpecifiedVersion() {
        return Arrays.asList("1.19", "1.19.1");
    }
}
