package SkriptDisplays.Elements.Effects;

import SkriptDisplays.PlacedStructure;
import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.Variable;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffRemoveStructure extends Effect {
    static {
        Skript.registerEffect(EffRemoveStructure.class, "remove [placed] structure %object%");
    }
    private Expression<PlacedStructure> structure;
    private Variable<?> var;
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.structure = (Expression<PlacedStructure>) expressions[0];
        this.var = (Variable<?>) expressions[0];
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
        ps.remove();
        ps = null;
        if (var != null) var.change(event, new Object[]{null}, Changer.ChangeMode.SET);
    }
}
