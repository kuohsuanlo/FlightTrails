package me.oribuin.flighttrails.cmds;

import me.oribuin.flighttrails.FlightTrails;
import me.oribuin.flighttrails.handlers.FlyHandler;
import me.oribuin.flighttrails.menus.ColorSelector;
import me.oribuin.flighttrails.persist.ColorU;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CmdToggleTrail implements CommandExecutor {

    public FlyHandler flyHandler;
    FlightTrails plugin;

    public CmdToggleTrail(FlyHandler flyHandler, FlightTrails instance) {
        this.flyHandler = flyHandler;
        this.plugin = instance;
    }


    public Particle.DustOptions particleColor(int r, int g, int b) {
        return new Particle.DustOptions(Color.fromRGB(r, g, b), plugin.getConfig().getInt("particle-size"));
    }

    Particle.DustOptions color = null;

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            FileConfiguration config = plugin.getConfig();

            if (!player.hasPermission("flytrails.fly")) {
                player.sendMessage(ColorU.cl(config.getString("prefix") + config.getString("cmd-permission")));
                return true;
            }

            if (!flyHandler.trailIsToggled(player.getUniqueId())) {
                player.sendMessage(ColorU.cl(config.getString("prefix") + config.get("trails-enabled")));
            } else {
                player.sendMessage(ColorU.cl(config.getString("prefix") + config.get("trails-disabled")));
            }


            if (color != null) {
                ColorSelector.dustOptionsMap.put(player.getUniqueId(), color);
            }

            flyHandler.trailToggle(player.getUniqueId());
        }
        return true;
    }
}
