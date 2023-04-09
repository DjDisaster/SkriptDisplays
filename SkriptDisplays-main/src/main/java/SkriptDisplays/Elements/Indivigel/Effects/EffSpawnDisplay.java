package SkriptDisplays.Elements.Indivigel.Effects;

import SkriptDisplays.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.Variable;
import ch.njol.skript.sections.EffSecSpawn;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.TextDisplay;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;

public class EffSpawnDisplay extends Effect {
    static {
        Skript.registerEffect(EffSpawnDisplay.class, "[SD|SkriptDisplay[s]] spawn (block|1:item|2:text) display at %location% [and [store [it]] in %-object%]");
    }

    private Expression<Location> location;
    private Variable<?> variable;
    private int type;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        location = (Expression<Location>) expressions[0];
        variable = (Variable<?>) expressions[1];
        type = parser.mark;
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "";//return "Kick player effect with expression player: " + player.toString(event, debug) + " and string expression: " + reason.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        Location loc = location.getSingle(event);
        if (loc == null) return;
        Entity e = null;
        switch (type) {
            case 0 -> {
                e = loc.getWorld().spawn(loc, BlockDisplay.class);
                Main.lastSpawnedBlockDisplay = e;
            }
            case 1 -> {
                e = loc.getWorld().spawn(loc, ItemDisplay.class);
                Main.lastSpawnedItemDisplay = e;
            }
            case 2 -> {
                e = loc.getWorld().spawn(loc, TextDisplay.class);
                Main.lastSpawnedTextDisplay = e;
            }
        }
        Class<?> effSecSpawnClass = EffSecSpawn.class;
        try {
            Field lastSpawnedField = effSecSpawnClass.getDeclaredField("lastSpawned");
            lastSpawnedField.setAccessible(true);
            lastSpawnedField.set(null, e);
        } catch (NoSuchFieldException ex) {
            throw new RuntimeException(ex);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
        if (variable != null) variable.change(event, new Object[]{e}, Changer.ChangeMode.SET);
    }
}
