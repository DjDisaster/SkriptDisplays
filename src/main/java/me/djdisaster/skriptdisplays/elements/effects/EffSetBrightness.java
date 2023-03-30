package me.djdisaster.skriptdisplays.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Display;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffSetBrightness extends Effect {

    static {
        Skript.registerEffect(EffSetBrightness.class, "set display brightness of %entity% to %number%");
    }

    private Expression<Entity> entity;
    private Expression<Number> brightness;

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        entity = (Expression<Entity>) expressions[0];
        brightness = (Expression<Number>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "";
    }

    @Override
    protected void execute(Event event) {
        Entity e = entity.getSingle(event);
        if (e instanceof org.bukkit.entity.BlockDisplay display) {
            int brightness = this.brightness.getSingle(event).intValue();
            display.setBrightness(new Display.Brightness(brightness, 1));
        }
    }
}
