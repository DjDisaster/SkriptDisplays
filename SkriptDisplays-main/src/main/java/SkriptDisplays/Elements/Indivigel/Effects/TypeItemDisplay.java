package SkriptDisplays.Elements.Indivigel.Effects;

import SkriptDisplays.PlacedStructure;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.ItemDisplay;

public class TypeItemDisplay {
    public static void register() {
        Classes.registerClass(new ClassInfo<>(ItemDisplay.class, "itemdisplay")
                .user("itemdisplay")
                .name("ItemDisplay")
                .description("Represents an entity.")
                .since("1.0")
                .parser(new Parser<ItemDisplay>() {

                    @Override
                    public boolean canParse(ParseContext context) {
                        return false;
                    }

                    @Override
                    public String toString(ItemDisplay o, int flags) {
                       return "[SkriptDisplays ItemDisplay]";
                    }

                    @Override
                    public String toVariableNameString(ItemDisplay o) {
                        return null;
                    }


                })
        );
    }
}