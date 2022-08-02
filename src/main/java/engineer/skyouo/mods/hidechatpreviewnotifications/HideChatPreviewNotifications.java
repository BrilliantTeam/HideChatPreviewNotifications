package engineer.skyouo.mods.hidechatpreviewnotifications;

import com.mojang.logging.LogUtils;
import engineer.skyouo.mods.hidechatpreviewnotifications.mixins.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HideChatPreviewNotifications.MODID)
public class HideChatPreviewNotifications {

    public static final String MODID = "hide_chat_preview_notifications";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static Map<String, MixinContainer> mixins = new HashMap<>();

    static {
        mixins.put("engineer.skyouo.mods.hidechatpreviewnotifications.mixins.ChatPreviewMixin", new ChatPreviewMixin());
        mixins.put("engineer.skyouo.mods.hidechatpreviewnotifications.mixins.ClientPlayerNetworkHandlerMixin", new ClientPlayerNetworkHandlerMixin());
        mixins.put("engineer.skyouo.mods.hidechatpreviewnotifications.mixins.ToastManagerMixin", new ToastManagerMixin());

        mixins.put("engineer.skyouo.mods.hidechatpreviewnotifications.mixins.MinecraftClientMixin", new MinecraftClientMixin());
    }


    public HideChatPreviewNotifications() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("Loading mod.");
    }
}
