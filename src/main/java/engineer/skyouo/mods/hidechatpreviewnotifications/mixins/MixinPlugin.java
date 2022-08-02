package engineer.skyouo.mods.hidechatpreviewnotifications.mixins;

import engineer.skyouo.mods.hidechatpreviewnotifications.HideChatPreviewNotifications;
import net.minecraft.MinecraftVersion;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class MixinPlugin implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {
        HideChatPreviewNotifications.LOGGER.info("Loading Mixins...");
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        boolean result = HideChatPreviewNotifications.mixins.get(mixinClassName) != null && HideChatPreviewNotifications.mixins.get(mixinClassName).getSpecifiedVersion().contains(MinecraftVersion.CURRENT.getName());
        if (result) {
            HideChatPreviewNotifications.LOGGER.info(mixinClassName + " is loaded.");
        }
        return result;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
