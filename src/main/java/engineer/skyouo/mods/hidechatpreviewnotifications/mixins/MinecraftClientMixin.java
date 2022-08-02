package engineer.skyouo.mods.hidechatpreviewnotifications.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.multiplayer.ChatPreviewWarningScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Mixin(Minecraft.class)
public class MinecraftClientMixin implements MixinContainer {

    @Shadow @Nullable
    private ServerData currentServer;

    @Inject(at = @At(value = "HEAD"), method = "setScreen", cancellable = true)
    private void set(Screen screen, CallbackInfo ci) {
        if (screen instanceof ChatPreviewWarningScreen) {
            screen.onClose();
            ServerData serverInfo = this.currentServer;
            Objects.requireNonNull(serverInfo.getChatPreview()).acknowledge();
            ServerList.saveSingleServer(serverInfo);

            ci.cancel();
        }
    }

    @Override
    public List<String> getSpecifiedVersion() {
        return Arrays.asList("1.19", "1.19.1");
    }
}
