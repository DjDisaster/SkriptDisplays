package SkriptDisplays.Elements.Indivigel.Effects;

import SkriptDisplays.PlacedStructure;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.TextDisplay;

public class TypeTextDisplay {
    public static void register() {
        Classes.registerClass(new ClassInfo<>(TextDisplay.class, "textdisplay")
                .user("textdisplay")
                .name("TextDisplay")
                .description("Represents an entity.")
                .since("1.0")
                .parser(new Parser<TextDisplay>() {

                    @Override
                    public boolean canParse(ParseContext context) {
                        return false;
                    }

                    @Override
                    public String toString(TextDisplay o, int flags) {
                        return "[SkriptDisplays TextDisplay]";
                    }

                    @Override
                    public String toVariableNameString(TextDisplay o) {
                        return null;
                    }


                })
        );
    }
}