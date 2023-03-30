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

public class EffRotateStructure extends Effect {
    static {
        Skript.registerEffect(EffRotateStructure.class, "rotate [display] structure %placedstructure% by %number%");
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
        return "";//return "Kick player effect with expression player: " + player.toString(event, debug) + " and string expression: " + reason.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        VisualStructure ps = structure.getSingle(event);
        if (ps == null) return;
        float angle = Objects.requireNonNull(size.getSingle(event)).floatValue();
        ps.getEntityMap().rotateX(angle);
    }
}
