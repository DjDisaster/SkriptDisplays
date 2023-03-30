package me.djdisaster.skriptdisplays;

import me.djdisaster.skriptdisplays.elements.types.TypePlacedStructure;
import me.djdisaster.skriptdisplays.elements.types.TypeStructure;
import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class SkriptDisplays extends JavaPlugin {
    private static SkriptDisplays instance;
    private SkriptAddon addon;

    @Override
    public void onEnable() {

        instance = this;
        addon = Skript.registerAddon(this);
        // Plugin startup logic

        TypeStructure.register();
        TypePlacedStructure.register();
        getLogger().info("SkriptDisplays is now enabled.");
        try {
            //This will register all our syntax for us. Explained below
            addon.loadClasses("me.djdisaster.skriptdisplays", "elements");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static SkriptDisplays getInstance() {
        return instance;
    }

    public SkriptAddon getAddon() {
        return addon;
    }
}
