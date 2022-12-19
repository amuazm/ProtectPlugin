package me.amuazm.protectplugin;

import me.amuazm.protectplugin.commands.SetWorldSpawnProtectionCommand;
import me.amuazm.protectplugin.commands.SpawnCommand;
import me.amuazm.protectplugin.listeners.ProtectionListener;
import me.amuazm.protectplugin.listeners.SetWorldSpawnListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class ProtectPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("setworldspawnprotection").setExecutor(new SetWorldSpawnProtectionCommand());
        getServer().getPluginManager().registerEvents(new SetWorldSpawnListener(), this);
        getServer().getPluginManager().registerEvents(new ProtectionListener(), this);
    }
}
