package me.djdisaster.skriptdisplays.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.djdisaster.skriptdisplays.structure.external.VisualStructure;
import org.bukkit.event.Event;
import org.bukkit.util.Transformation;
import org.jetbrains.annotations.Nullable;

public class EffStartInterpolation extends Effect {
    static {
        Skript.registerEffect(EffStartInterpolation.class, "set [structure] (interpolation|size) of %object% to %number%[,] [[ ]%-number%,[ ]%-number%] over %number% ticks");
    }
    private Expression<VisualStructure> structure;
    private Expression<Number> x;
    private Expression<Number> y;
    private Expression<Number> z;
    private Expression<Number> ticks;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        structure = (Expression<VisualStructure>) expressions[0];
        x = (Expression<Number>) expressions[1];
        y = (Expression<Number>) expressions[2];
        z = (Expression<Number>) expressions[3];
        ticks = (Expression<Number>) expressions[4];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "";
    }

    @Override
    protected void execute(Event event) {
        VisualStructure ps = structure.getSingle(event);
        if (ps == null) return;

        float size1 = x.getSingle(event).floatValue();
        float size2 = y.getSingle(event).floatValue();
        float size3 = z.getSingle(event).floatValue();

        Transformation current = ps.getEntityMap().getTransform();
        current.getScale().set(size1, size2, size3);

        ps.getEntityMap().interpolate(current, ticks.getSingle(event).intValue());
    }
}
