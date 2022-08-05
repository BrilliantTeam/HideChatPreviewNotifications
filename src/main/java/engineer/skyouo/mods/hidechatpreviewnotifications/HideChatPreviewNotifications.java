package engineer.skyouo.mods.hidechatpreviewnotifications;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HideChatPreviewNotifications implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("hide_chat_previews");

    @Override
    public void onInitialize() {
        LOGGER.info("Mod initialized.");
    }
}
