package SkriptDisplays.Elements.Effects;

import SkriptDisplays.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.Variable;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffCreateDisplay extends Effect {
    static {
        Skript.registerEffect(EffCreateDisplay.class, "Create (0¦Block|1¦Item|2¦Text) display at %location% [(and store it|stored) in %-object%]");
    }

    private Expression<Location> location;
    private Variable<?> var;
    private int type;
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.location = (Expression<Location>) expressions[0];
        this.var = (Variable<?>) expressions[1];
        this.type = parser.mark;
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
            case 0:
                e = loc.getWorld().spawn(loc, org.bukkit.entity.BlockDisplay.class);
                Main.lastSpawnedBlockDisplay = e;
                break;
            case 1:
                e = loc.getWorld().spawn(loc, org.bukkit.entity.ItemDisplay.class);
                Main.lastSpawnedItemDisplay = e;
                break;
            case 2:
                e = loc.getWorld().spawn(loc, org.bukkit.entity.TextDisplay.class);
                Main.lastSpawnedTextDisplay = e;
                break;
        }
        if (var == null) return;
        var.change(event, new Object[]{e}, Changer.ChangeMode.SET);
    }
}
