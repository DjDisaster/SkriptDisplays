package me.djdisaster.skriptdisplays.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.Variable;
import ch.njol.util.Kleenean;
import me.djdisaster.skriptdisplays.structure.external.VisualStructure;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffRemoveStructure extends Effect {
    static {
        Skript.registerEffect(EffRemoveStructure.class, "remove [placed [display]] structure %placedstructure%");
    }
    private Expression<VisualStructure> structure;
    private Variable<?> var;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        structure = (Expression<VisualStructure>) expressions[0];
        var = (Variable<?>) expressions[0];
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
        ps.remove();
        if (var != null) var.change(event, new Object[]{null}, Changer.ChangeMode.SET);
    }
}
