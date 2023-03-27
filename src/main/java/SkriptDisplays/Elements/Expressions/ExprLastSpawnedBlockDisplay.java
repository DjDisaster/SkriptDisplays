package SkriptDisplays.Elements.Expressions;
import SkriptDisplays.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("Gets the last spawned block display")
@Description("Returns the last spawned block display")
@Since("1.0")
public class ExprLastSpawnedBlockDisplay extends SimpleExpression<Entity> {
    static {
        Skript.registerExpression(ExprLastSpawnedBlockDisplay.class, Entity.class, ExpressionType.SIMPLE, "last spawned block display");
    }

    @Override
    public Class<? extends Entity> getReturnType() {
        return Entity.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "";//"bungee total of " + (this.server != null ? this.server.toString(event,debug) : " all servers");
    }

    @Override
    @Nullable
    protected Entity[] get(Event e) {
        return new Entity[]{Main.lastSpawnedBlockDisplay};
    }
}