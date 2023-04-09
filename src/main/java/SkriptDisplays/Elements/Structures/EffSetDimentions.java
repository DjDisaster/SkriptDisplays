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

public class EffSetDimentions extends Effect {

    static {
        Skript.registerEffect(EffSetDimentions.class, "set (width|1:height) of %entity% to %number%");
    }

    private Expression<Entity> entity;
    private Expression<Number> size;
    private int mark;

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        entity = (Expression<Entity>) expressions[0];
        size = (Expression<Number>) expressions[1];
        mark = parseResult.mark;
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "";
    }

    @Override
    protected void execute(Event event) {
        Entity e = entity.getSingle(event);
        if (e instanceof BlockDisplay display) {
            float size = this.size.getSingle(event).floatValue();
            switch (mark) {
                case 0 -> {
                    display.setDisplayWidth(size);
                }
                case 1 -> {
                    display.setDisplayHeight(size);
                }
            }
        }
    }
}
