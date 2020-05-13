package io.github.sirmagicman.sayformat.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import io.github.sirmagicman.sayformat.config.MainConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("SF|sayformat")
public class MainCommand extends BaseCommand {

    @CatchUnknown
    @Default
    @Subcommand("help")
    @Description("Shows this page")
    public void help(CommandSender sender) {
    sender.sendMessage("§9>>>>>>>> §cSay Format §9<<<<<<<<");
    sender.sendMessage("§chelp  §7-  §cShows this page");
    sender.sendMessage("§csay  §7-  §cSends a message as yourself");
    if (sender.hasPermission("sf.server")) {
        sender.sendMessage("§cserver  §7-  §cSends a message as the server");
    }
    else {
        sender.sendMessage("§3Hey! Sorry, but you don't have permission for this command.");
    }
    if (sender.hasPermission("sf.reload")) {
        sender.sendMessage("§creload  §7-  §cReloads the config");
    }
    else {
        sender.sendMessage("§3Hey! Sorry, but you don't have permission for this command.");
    }
    }

    @Subcommand("reload")
    @Description("Reload the plugin")
    @CommandPermission("sf.reload")
    public void reload(CommandSender sender) {
        MainConfig.reload();
        sender.sendMessage("§2Config reloaded");
    }

    @Subcommand("server")
    @CommandAlias("server")
    @Description("Run 'say' as the server")
    @CommandPermission("sf.server")
    public void server(CommandSender sender, String message) {
        String prefix = MainConfig.getInstance().getServer();
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
    }

    @Subcommand("say")
    @CommandAlias("say")
    @Description("Run 'say' as yourself")
    @CommandPermission("sf.say")
    public void say(CommandSender sender, String message) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String prefix = MainConfig.getInstance().getPlayer();
            prefix = prefix.replaceAll("%PLAYER%", ChatColor.stripColor(player.getDisplayName()));
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
        }
        else {
            server(sender, message);
        }
    }
}


