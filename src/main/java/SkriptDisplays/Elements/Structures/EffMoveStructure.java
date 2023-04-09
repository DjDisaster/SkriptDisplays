package SkriptDisplays.Elements.Structures;

import SkriptDisplays.PlacedStructure;
import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.Variable;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffMoveStructure extends Effect {
    static {
        Skript.registerEffect(EffMoveStructure.class, "move [placed [display]] structure %placedstructure% to %location% over %number% tick[s]");
    }
    private Expression<PlacedStructure> structure;
    private Expression<Location> location;
    private Expression<Number> time;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        structure = (Expression<PlacedStructure>) expressions[0];
        location = (Expression<Location>) expressions[1];
        time = (Expression<Number>) expressions[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "";//return "Kick player effect with expression player: " + player.toString(event, debug) + " and string expression: " + reason.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        PlacedStructure ps = structure.getSingle(event);
        Location location2 = location.getSingle(event);
        Integer time2 = time.getSingle(event).intValue();

        if (ps == null || location2 == null || time2 == null) return;

        ps.move(location2, time2);

    }
}
