package SkriptDisplays.Elements.Structures;

import SkriptDisplays.PlacedStructure;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffSetStructureSize extends Effect {
    static {
        Skript.registerEffect(EffSetStructureSize.class, "set [structure] size of %placedstructure% to %number%");
    }
    private Expression<PlacedStructure> structure;
    private Expression<Number> size;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        structure = (Expression<PlacedStructure>) expressions[0];
        size = (Expression<Number>) expressions[1];
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

        Float size2 = size.getSingle(event).floatValue();
        ps.setSize(size2);
    }
}
