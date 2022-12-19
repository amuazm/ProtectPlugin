package me.amuazm.protectplugin.commands;

import me.amuazm.protectplugin.ProtectPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetWorldSpawnProtectionCommand implements CommandExecutor {
    private final ProtectPlugin plugin = ProtectPlugin.getPlugin(ProtectPlugin.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            int r = plugin.getConfig().getInt("spawn-protection-radius");
            sender.sendMessage(ChatColor.GOLD + "The current spawn protection radius is " + ChatColor.RED + r + ChatColor.GOLD + ".");
        } else if (args.length == 1) {
            int r = Integer.parseInt(args[0]);
            plugin.getConfig().set("spawn-protection-radius", r);
            plugin.saveConfig();
            sender.sendMessage(ChatColor.GOLD + "Set the spawn protection radius to " + ChatColor.RED + r + ChatColor.GOLD + ".");
        } else {
            sender.sendMessage(ChatColor.RED + "Wrong usage, do /setworldspawnprotection <radius>.");
        }

        return true;
    }
}
