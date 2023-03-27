package SkriptDisplays.Elements.Effects;

import SkriptDisplays.PlacedStructure;
import SkriptDisplays.Structure;
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

public class EffPlaceStructure extends Effect {
    static {
        Skript.registerEffect(EffPlaceStructure.class, "place [the] structure %displaystructure% at %location% [and [store [it]] in %-object%]");
    }
    private Expression<Structure> structure;
    private Expression<Location> location;
    private Variable<?> variable;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        structure = (Expression<Structure>) expressions[0];
        location = (Expression<Location>) expressions[1];
        variable = (Variable<?>) expressions[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "";//return "Kick player effect with expression player: " + player.toString(event, debug) + " and string expression: " + reason.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        Location loc = location.getSingle(event);
        Structure s = structure.getSingle(event);
        if (loc == null || s == null) return;

        PlacedStructure ps = new PlacedStructure();
        ps.placeStructure(s, loc);

        if (variable != null) variable.change(event, new Object[]{ps}, Changer.ChangeMode.SET);
    }
}
