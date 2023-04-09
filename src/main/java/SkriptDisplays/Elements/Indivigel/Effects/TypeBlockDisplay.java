package SkriptDisplays.Elements.Indivigel.Effects;

import SkriptDisplays.PlacedStructure;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import org.bukkit.entity.BlockDisplay;

public class TypeBlockDisplay {
    public static void register() {
        Classes.registerClass(new ClassInfo<>(BlockDisplay.class, "blockdisplay")
                .user("blockdisplay")
                .name("BlockDisplay")
                .description("Represents an entity.")
                .since("1.0")
                .parser(new Parser<BlockDisplay>() {

                    @Override
                    public boolean canParse(ParseContext context) {
                        return false;
                    }

                    @Override
                    public String toString(BlockDisplay o, int flags) {
                        return "[SkriptDisplays BlockDisplay]";
                    }

                    @Override
                    public String toVariableNameString(BlockDisplay o) {
                        return null;
                    }

                })
        );
    }
}