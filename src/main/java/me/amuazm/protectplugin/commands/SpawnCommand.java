package me.amuazm.protectplugin.commands;

import me.amuazm.protectplugin.ProtectPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnCommand implements CommandExecutor {
    private final ProtectPlugin plugin = ProtectPlugin.getPlugin(ProtectPlugin.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            String spawnWorld = plugin.getConfig().getString("spawn-world");
            World world = Bukkit.getWorld(spawnWorld);

            final int[] countdown = {3};
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (countdown[0] == 0) {
                        // Code to run after countdown
                        p.teleport(world.getSpawnLocation().add(0.5, 0.0, 0.5));
                        p.sendMessage(ChatColor.GOLD + "Teleporting you to spawn.");

                        this.cancel();
                        return;
                    }

                    // Code to run at the start and in between counts
                    p.sendMessage(ChatColor.GOLD + "Teleporting in " + ChatColor.RED + countdown[0] + ChatColor.GOLD + ".");

                    countdown[0] -= 1;
                }
            }.runTaskTimer(plugin, 0, 20);
        }

        return true;
    }
}
