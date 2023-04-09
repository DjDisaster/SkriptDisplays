package SkriptDisplays.Elements.Structures;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public class EffSetBlockDisplay extends Effect {

    static {
        Skript.registerEffect(EffSetBlockDisplay.class, "set block display of %entity% to %itemtype%");
    }

    private Expression<Entity> entity;
    private Expression<ItemType> item;

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        entity = (Expression<Entity>) expressions[0];
        item = (Expression<ItemType>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "";
    }

    @Override
    protected void execute(Event event) {
        Entity e = entity.getSingle(event);
        ItemStack i = item.getSingle(event).getRandom();
        if (e == null || i == null) return;

        if (e instanceof BlockDisplay display)
            display.setBlock(i.getType().createBlockData());
    }
}
