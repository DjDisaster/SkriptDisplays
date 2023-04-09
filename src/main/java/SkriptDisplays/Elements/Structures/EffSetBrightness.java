package SkriptDisplays.Elements.Structures;

import SkriptDisplays.PlacedStructure;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffSetBrightness extends Effect {

    static {
        Skript.registerEffect(EffSetBrightness.class, "set display brightness of %placedstructure% to %number%");
    }

    private Expression<PlacedStructure> structure;
    private Expression<Long> brightness;

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        structure = (Expression<PlacedStructure>) expressions[0];
        brightness = (Expression<Long>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "";
    }

    @Override
    protected void execute(Event event) {
        PlacedStructure s = structure.getSingle(event);
        // get long as int
        // brightness must be 0-15
        int brightness = this.brightness.getSingle(event).intValue();
        if (brightness < 0) brightness = 0;
        if (brightness > 15) brightness = 15;
        s.setBrightness(brightness);
    }
}
