package io.github.sirmagicman.sayformat.config;

import io.github.sirmagicman.sayformat.SayFormat;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public abstract class ConfigLoader {
    protected SayFormat plugin = SayFormat.getPlugin();
    protected String fileName;
    protected File configFile;
    protected FileConfiguration config;

    public ConfigLoader(String relativePath, String fileName) {
        this.fileName = fileName;
        this.configFile = new File(plugin.getDataFolder(), relativePath + File.separator + fileName);
        loadFile();
    }

    public ConfigLoader(String fileName) {
        this.fileName = fileName;
        this.configFile = new File(plugin.getDataFolder(), fileName);
        loadFile();
    }

    protected void loadFile() {
        if (!configFile.exists()) {
            plugin.getLogger().info("Creating " + fileName + "...");
            plugin.saveResource(fileName, false);
        }
        else {
            plugin.getLogger().info("Loading " + fileName + "...");
        }

        config = YamlConfiguration.loadConfiguration(configFile);
    }
}