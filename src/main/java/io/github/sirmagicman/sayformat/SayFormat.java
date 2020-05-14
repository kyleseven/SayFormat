package io.github.sirmagicman.sayformat;

import co.aikar.commands.PaperCommandManager;
import io.github.sirmagicman.sayformat.commands.MainCommand;
import io.github.sirmagicman.sayformat.config.MainConfig;
import io.github.sirmagicman.sayformat.listeners.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class SayFormat extends JavaPlugin {
    private static SayFormat plugin;

    public static SayFormat getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        PaperCommandManager commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new MainCommand());
        MainConfig.getInstance();
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
