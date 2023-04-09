package SkriptDisplays.Elements.Structures;

import SkriptDisplays.PlacedStructure;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffStartInterpolation extends Effect {
    static {
        Skript.registerEffect(EffStartInterpolation.class, "set [structure] (interpolation|size) of %object% to %number%[,] [[ ]%-number%,[ ]%-number%] over %number% ticks");
    }
    private Expression<PlacedStructure> structure;
    private Expression<Number> x;
    private Expression<Number> y;
    private Expression<Number> z;
    private Expression<Number> ticks;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        structure = (Expression<PlacedStructure>) expressions[0];
        x = (Expression<Number>) expressions[1];
        y = (Expression<Number>) expressions[2];
        z = (Expression<Number>) expressions[3];
        ticks = (Expression<Number>) expressions[4];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "";//return "Kick player effect with expression player: " + player.toString(event, debug) + " and string expression: " + reason.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        PlacedStructure ps = structure.getSingle(event);
        if (ps == null) return;

        Float size1 = x.getSingle(event).floatValue();
        try {
            Float size2 = y.getSingle(event).floatValue();
            Float size3 = z.getSingle(event).floatValue();
            ps.startInterpolation(size1, size2, size3, ticks.getSingle(event).intValue());
        } catch (NullPointerException e) {
            ps.startInterpolation(size1, size1, size1, ticks.getSingle(event).intValue());
        }
    }
}
