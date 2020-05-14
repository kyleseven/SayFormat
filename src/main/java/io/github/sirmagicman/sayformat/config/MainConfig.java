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

    //Server format getter
    public String getServer() {
        return config.getString("server");
    }

    //Say format getter
    public String getPlayer() {
        return config.getString("player");
    }

    //Reload function
    public static void reload() {
        getInstance().loadFile();
    }
}