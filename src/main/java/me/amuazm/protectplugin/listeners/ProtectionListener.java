package me.amuazm.protectplugin.listeners;

import me.amuazm.protectplugin.ProtectPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class ProtectionListener implements Listener {
    private final ProtectPlugin plugin = ProtectPlugin.getPlugin(ProtectPlugin.class);

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getClickedBlock() != null) {
            Location location = e.getClickedBlock().getLocation();
            Location worldSpawn = e.getClickedBlock().getWorld().getSpawnLocation();
            canceller(e, e.getPlayer(), worldSpawn, location);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Location location = e.getBlockPlaced().getLocation();
        Location worldSpawn = e.getBlockPlaced().getWorld().getSpawnLocation();
        canceller(e, e.getPlayer(), worldSpawn, location);
    }

    public void canceller(Cancellable e, Player p, Location worldSpawn, Location location) {
        int spawnRadius = plugin.getConfig().getInt("spawn-protection-radius");

        Vector xz1 = location.toVector().setY(0);
        Vector xz2 = worldSpawn.toVector().setY(0);
        if (xz1.subtract(xz2).length() <= spawnRadius) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + "You cannot build at spawn.");

            // Particles
            double radius = spawnRadius;
            for (double angle = 0; angle < 360; angle += 5) {
                double x = (radius * Math.sin(angle));
                double z = (radius * Math.cos(angle));
                worldSpawn.getWorld().spawnParticle(
                        Particle.VILLAGER_ANGRY,
                        worldSpawn.getX() + x + 0.5,
                        p.getLocation().getY(),
                        worldSpawn.getZ() + z + 0.5,
                        1
                );
            }
        }
    }
}
