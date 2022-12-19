package me.amuazm.protectplugin.listeners;

import me.amuazm.protectplugin.ProtectPlugin;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class SetWorldSpawnListener implements Listener {
    private final ProtectPlugin plugin = ProtectPlugin.getPlugin(ProtectPlugin.class);

    @EventHandler
    public void onSetWorldSpawn(PlayerCommandPreprocessEvent e) {
        String s = e.getMessage();
        // Event if /setworldspawn is run
        if (s.startsWith("/setworldspawn")) {
            // Save the world the command was run in e.g. "world" if in overworld
            World world = e.getPlayer().getWorld();
            plugin.getConfig().set("spawn-world", world.getName());
            plugin.saveConfig();

            // Make players spawn in a single location
            world.setGameRule(GameRule.SPAWN_RADIUS, 0);
        }
    }
}
