package io.github.sirmagicman.sayformat.config;

public class MainConfig extends ConfigLoader {
    private static MainConfig mainConfig;

    public MainConfig() {
        super("config.yml");
    }

    public static MainConfig getInstance() {
        if (mainConfig == null) {
            mainConfig = new MainConfig();
        }
        return mainConfig;
    }

    public String getServer() {
        return config.getString("server");
    }

    public String getPlayer() {
        return config.getString("player");
    }

    public static void reload() {
        getInstance().loadFile();
    }
}