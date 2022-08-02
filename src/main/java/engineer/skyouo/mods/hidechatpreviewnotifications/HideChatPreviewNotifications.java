package engineer.skyouo.mods.hidechatpreviewnotifications;

import engineer.skyouo.mods.hidechatpreviewnotifications.mixins.*;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class HideChatPreviewNotifications implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("hide_chat_previews");
    public static Map<String, MixinContainer> mixins = new HashMap<>();

    static {
        mixins.put("engineer.skyouo.mods.hidechatpreviewnotifications.mixins.ChatPreviewMixin", new ChatPreviewMixin());
        mixins.put("engineer.skyouo.mods.hidechatpreviewnotifications.mixins.ClientPlayerNetworkHandlerMixin", new ClientPlayerNetworkHandlerMixin());
        mixins.put("engineer.skyouo.mods.hidechatpreviewnotifications.mixins.ToastManagerMixin", new ToastManagerMixin());

        mixins.put("engineer.skyouo.mods.hidechatpreviewnotifications.mixins.MinecraftClientMixin", new MinecraftClientMixin());
    }

    @Override
    public void onInitialize() {
        LOGGER.info("Mod initialized.");
    }
}
