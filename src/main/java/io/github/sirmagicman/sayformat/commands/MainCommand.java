package io.github.sirmagicman.sayformat.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import io.github.sirmagicman.sayformat.SayFormat;
import io.github.sirmagicman.sayformat.config.MainConfig;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("SF|SayFormat")
public class MainCommand extends BaseCommand {

    @CatchUnknown
    @Default
    @Subcommand("help|h|?")
    @Description("Shows this page")
    public void help(CommandSender sender) {
        //Title hover chat
        TextComponent title = new TextComponent("§a>>>>>>>> §cSay Icon §a<<<<<<<<");
        ComponentBuilder titleHover = new ComponentBuilder("").append("Spigot URL").color(ChatColor.DARK_AQUA);
        title.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, titleHover.create()));
        title.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/say-format.78764/"));

        //Help hover chat and click
        TextComponent help = new TextComponent("§ahelp  §3-  §9Shows this page");
        ComponentBuilder helpHover = new ComponentBuilder("").append("Click to show the help page").color(ChatColor.DARK_AQUA);
        help.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, helpHover.create()));
        help.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/sf help"));

        //Say hover chat and click
        TextComponent say = new TextComponent("§asay  §3-  §9Sends a message as yourself");
        ComponentBuilder sayHover = new ComponentBuilder("").append("Use command").color(ChatColor.DARK_AQUA);
        say.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, sayHover.create()));
        say.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/say "));

        //Server hover chat and click
        TextComponent server = new TextComponent("§aserver  §3-  §9Sends a message as the server");
        ComponentBuilder serverHover = new ComponentBuilder("").append("Use command").color(ChatColor.DARK_AQUA);
        server.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, serverHover.create()));
        server.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/server "));

        //SudoSay hover chat and click
        TextComponent sudoSay = new TextComponent("§assay  §3-  §9Forces a player to run 'say'");
        ComponentBuilder sudoSayHover = new ComponentBuilder("").append("Use command").color(ChatColor.DARK_AQUA);
        sudoSay.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, sudoSayHover.create()));
        sudoSay.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/ssay "));

        //Reload hover chat and click
        TextComponent reload = new TextComponent("§areload  §3-  §9Reloads the config");
        ComponentBuilder reloadHover = new ComponentBuilder("").append("Run command").color(ChatColor.DARK_AQUA);
        reload.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, reloadHover.create()));
        reload.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/sf reload"));

        //Version hover chat and click
        TextComponent version = new TextComponent("§aversion §3- §9Checks the version of the plugin.");
        ComponentBuilder versionHover = new ComponentBuilder("").append("Run command").color(ChatColor.DARK_AQUA);
        version.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, versionHover.create()));
        version.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/sf version"));

        //Send help page
        sender.spigot().sendMessage(title);
        sender.spigot().sendMessage(help);
        sender.spigot().sendMessage(say);
        if (sender.hasPermission("sf.server")) { //Check for server permission
            sender.spigot().sendMessage(server);
        } else {
            sender.sendMessage("§3Hey! Sorry, but you don't have permission to use this command.");
        }
        if (sender.hasPermission("sf.sudosay")) { //Check for SudoSay permission
            sender.spigot().sendMessage(sudoSay);
        }
        else {
            sender.sendMessage("§3Hey! Sorry, but you don't have permission to use this command.");
        }
        if (sender.hasPermission("sf.reload")) { //Check for reload permission
            sender.spigot().sendMessage(reload);
        } else {
            sender.sendMessage("§3Hey! Sorry, but you don't have permission to use this command.");
        }
        sender.spigot().sendMessage(version);
    }
    //TODO - Add function to run as another player
    //Say command
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
        } else {
            server(sender, message);
        }
    }

    //Server command
    @Subcommand("server")
    @CommandAlias("server|serv")
    @Description("Run 'say' as the server")
    @CommandPermission("sf.server")
    public void server(CommandSender sender, String message) {
        String prefix = MainConfig.getInstance().getServer();
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
    }

    //Sudo Say
    @Subcommand("sudosay")
    @CommandAlias("sudosay|ssay")
    @Description("Force another player to run 'say'")
    @CommandPermission("sf.sudosay")
    public void sudoSay(CommandSender sender, String message) {
        String[] split = message.split(" ");
        String newMessage = null;
        Player target = null;
        for (Player player: Bukkit.getOnlinePlayers()) {
            if (split[0].equalsIgnoreCase(ChatColor.stripColor(player.getName())) || split[0].equalsIgnoreCase(ChatColor.stripColor(player.getDisplayName()))) target = player;
        }
        for (int i = 1; i < split.length; i++) {
            newMessage = split[i] + " ";
        }
        if (target != null) {
            target.performCommand("say " + newMessage);
        } else {
            sender.sendMessage("Player not found");
        }
    }

    //Reload command
    @Subcommand("reload|rel|r")
    @Description("Reload the plugin")
    @CommandPermission("sf.reload")
    public void reload(CommandSender sender) {
        MainConfig.reload();
        sender.sendMessage("§2Config reloaded");
    }

    //Version command
    @Subcommand("version|ver|v")
    @Description("Checks the plugin version")
    public void version(CommandSender sender) {
        sender.sendMessage("§cRunning §3SayFormat v" + SayFormat.getPlugin().getDescription().getVersion());
    }
}


