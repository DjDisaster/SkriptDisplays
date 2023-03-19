package SkriptDisplays.Elements.Types;

import SkriptDisplays.PlacedStructure;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;

public class TypePlacedStructure {
    public static void register() {
        Classes.registerClass(new ClassInfo<>(PlacedStructure.class, "placedstructure")
                .user("placedstructure")
                .name("PlacedStructure")
                .description("Represents a placed structure, will not save through reloads/restarts.")
                .since("1.0")
                .parser(new Parser<PlacedStructure>() {

                    @Override
                    public boolean canParse(ParseContext context) {
                        return false;
                    }

                    @Override
                    public String toString(PlacedStructure structure, int flags) {
                        return "(yeah why u looking at ma classes man/woman/they/them/other?!?!)";
                    }

                    @Override
                    public String toVariableNameString(PlacedStructure structure) {
                        return "hi2";
                    }
                })
        );
    }
}