package SkriptDisplays;

import SkriptDisplays.Elements.Types.TypePlacedStructure;
import SkriptDisplays.Elements.Types.TypeStructure;
import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Main extends JavaPlugin {
    Main instance;
    SkriptAddon addon;

    public static Entity lastSpawnedBlockDisplay;
    public static Entity lastSpawnedItemDisplay;
    public static Entity lastSpawnedTextDisplay;
    public static Double id = 0.0;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        addon = Skript.registerAddon(this);
        // Plugin startup logic

        TypeStructure.register();
        TypePlacedStructure.register();
        getLogger().info("SkConvenience is now enabled.");
        try {
            //This will register all our syntax for us. Explained below
            addon.loadClasses("SkriptDisplays", "Elements");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
