package engineer.skyouo.mods.hidechatpreviewnotifications.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.ChatPreviewWarningScreen;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.option.ServerList;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin implements MixinContainer {

    @Shadow @Nullable
    private ServerInfo currentServerEntry;

    @Inject(at = @At(value = "HEAD"), method = "setScreen", cancellable = true)
    private void set(Screen screen, CallbackInfo ci) {
        if (screen instanceof ChatPreviewWarningScreen) {
            screen.close();
            ServerInfo serverInfo = this.currentServerEntry;
            Objects.requireNonNull(serverInfo.getChatPreview()).setAcknowledged();
            ServerList.updateServerListEntry(serverInfo);

            ci.cancel();
        }
    }

    @Override
    public List<String> getSpecifiedVersion() {
        return Arrays.asList("1.19", "1.19.1");
    }
}
