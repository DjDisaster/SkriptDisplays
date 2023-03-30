package me.djdisaster.skriptdisplays.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.djdisaster.skriptdisplays.structure.external.VisualStructure;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class EffSetStructureSize extends Effect {
    static {
        Skript.registerEffect(EffSetStructureSize.class, "set [structure] size of %placedstructure% to %number%");
    }
    private Expression<VisualStructure> structure;
    private Expression<Number> size;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        structure = (Expression<VisualStructure>) expressions[0];
        size = (Expression<Number>) expressions[1];
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
        float size = Objects.requireNonNull(this.size.getSingle(event)).floatValue();
        ps.getEntityMap().rescale(new float[]{size, size, size});
    }
}
