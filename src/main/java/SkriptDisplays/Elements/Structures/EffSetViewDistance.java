package SkriptDisplays.Elements.Structures;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffSetViewDistance extends Effect {

    static {
        Skript.registerEffect(EffSetViewDistance.class, "set view distance of %entity% to %number%");
    }

    private Expression<Entity> entity;
    private Expression<Number> distance;

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        entity = (Expression<Entity>) expressions[0];
        distance = (Expression<Number>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "";
    }

    @Override
    protected void execute(Event event) {
        Entity e = entity.getSingle(event);
        if (e instanceof BlockDisplay display)
            display.setViewRange(distance.getSingle(event).intValue());
    }
}
