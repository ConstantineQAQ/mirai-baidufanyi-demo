package org.example;

import jdk.nashorn.internal.objects.Global;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;
import org.example.entity.translate;

public final class Plugin extends JavaPlugin {
    public static final Plugin INSTANCE = new Plugin();

    private Plugin() {
        super(new JvmPluginDescriptionBuilder("org.example.plugin.baidufanyi", "1.0-SNAPSHOT")
                .name("baidufanyi")
                .author("Constantine")
                .build());
    }

    @Override
    public void onEnable() {
        getLogger().info("百度翻译插件已上线");
        GlobalEventChannel.INSTANCE.registerListenerHost(new translate());
    }
}