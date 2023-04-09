package SkriptDisplays;

import SkriptDisplays.Elements.Indivigel.Effects.TypeBlockDisplay;
import SkriptDisplays.Elements.Indivigel.Effects.TypeItemDisplay;
import SkriptDisplays.Elements.Indivigel.Effects.TypeTextDisplay;
import SkriptDisplays.Elements.Structures.Types.TypePlacedStructure;
import SkriptDisplays.Elements.Structures.Types.TypeStructure;
import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.entity.SimpleEntityData;
import com.google.common.io.ByteArrayDataOutput;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public final class Main extends JavaPlugin {
    Main instance;
    SkriptAddon addon;

    public static Entity lastSpawnedBlockDisplay;
    public static Entity lastSpawnedItemDisplay;
    public static Entity lastSpawnedTextDisplay;
    public static Double id = 0.0;
    @Override
    public void onEnable() {

        instance = this;
        addon = Skript.registerAddon(this);
        // Plugin startup logic
        TypeStructure.register();
        TypePlacedStructure.register();
        TypeBlockDisplay.register();
        TypeItemDisplay.register();
        TypeTextDisplay.register();
        getLogger().info("SkriptDisplays is now enabled.");
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
