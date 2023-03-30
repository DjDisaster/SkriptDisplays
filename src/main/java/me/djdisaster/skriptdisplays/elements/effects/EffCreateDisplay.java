package me.djdisaster.skriptdisplays.elements.effects;

import me.djdisaster.skriptdisplays.SkriptDisplays;
import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.Variable;
import ch.njol.util.Kleenean;
import me.djdisaster.skriptdisplays.elements.expressions.ExprLastSpawnedBlockDisplay;
import org.bukkit.Location;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.TextDisplay;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EffCreateDisplay extends Effect {
    static {
        Skript.registerEffect(EffCreateDisplay.class, "create (block|1:item|2:text) display at %location% [and [store [it]] in %-object%]");
    }

    private Expression<Location> location;
    private Variable<?> variable;
    private int type;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.ParseResult parser) {
        location = (Expression<Location>) expressions[0];
        variable = (Variable<?>) expressions[1];
        type = parser.mark;
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "";
    }

    @Override
    protected void execute(Event event) {
        Location loc = location.getSingle(event);
        if (loc == null) return;
        Entity e = null;
        switch (type) {
            case 0 -> {
                e = loc.getWorld().spawn(loc, BlockDisplay.class);
                ExprLastSpawnedBlockDisplay.lastSpawnedBlockDisplay = e;
            }
            case 1 -> {
                e = loc.getWorld().spawn(loc, ItemDisplay.class);
                ExprLastSpawnedBlockDisplay.lastSpawnedItemDisplay = e;
            }
            case 2 -> {
                e = loc.getWorld().spawn(loc, TextDisplay.class);
                ExprLastSpawnedBlockDisplay.lastSpawnedTextDisplay = e;
            }
        }
        if (variable != null) variable.change(event, new Object[]{e}, Changer.ChangeMode.SET);
    }
}
