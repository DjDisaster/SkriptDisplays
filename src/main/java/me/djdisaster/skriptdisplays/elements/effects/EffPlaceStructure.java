package me.djdisaster.skriptdisplays.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.Variable;
import ch.njol.util.Kleenean;
import me.djdisaster.skriptdisplays.structure.external.VisualStructure;
import me.djdisaster.skriptdisplays.structure.internal.DisplayStructure;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffPlaceStructure extends Effect {
    static {
        Skript.registerEffect(EffPlaceStructure.class, "place [the] structure %displaystructure% at %location% [and [store [it]] in %-object%]");
    }
    private Expression<DisplayStructure> structure;
    private Expression<Location> location;
    private Variable<?> variable;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        structure = (Expression<DisplayStructure>) expressions[0];
        location = (Expression<Location>) expressions[1];
        variable = (Variable<?>) expressions[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "";
    }

    @Override
    protected void execute(Event event) {
        Location loc = location.getSingle(event);
        DisplayStructure s = structure.getSingle(event);
        if (loc == null || s == null) return;

        VisualStructure ps = new VisualStructure(s, loc);
        ps.place();

        if (variable != null) variable.change(event, new Object[]{ps}, Changer.ChangeMode.SET);
    }
}
