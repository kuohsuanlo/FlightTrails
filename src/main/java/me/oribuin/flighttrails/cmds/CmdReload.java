package me.oribuin.flighttrails.cmds;

import me.oribuin.flighttrails.FlightTrails;
import me.oribuin.flighttrails.persist.ColorU;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CmdReload implements CommandExecutor {
    FlightTrails plugin;

    public CmdReload(FlightTrails instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {

            FileConfiguration config = plugin.getConfig();

            Player player = (Player) sender;

            if (!player.hasPermission("flytrails.reload")) {
                player.sendMessage(ColorU.cl(config.getString("settings.prefix") + " " + config.getString("flight.reload")));
            }

            plugin.saveConfig();
            plugin.reloadConfig();
            plugin.saveConfig();

            player.sendMessage(ColorU.cl(config.getString("prefix") + config.getString("reload")));
        }

        return true;
    }
}