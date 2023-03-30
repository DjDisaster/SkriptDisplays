package me.djdisaster.skriptdisplays.elements.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import me.djdisaster.skriptdisplays.structure.external.VisualStructure;

public class TypePlacedStructure {
    public static void register() {
        Classes.registerClass(new ClassInfo<>(VisualStructure.class, "placedstructure")
                .user("placedstructure")
                .name("PlacedStructure")
                .description("Represents a placed structure, will not save through reloads/restarts.")
                .since("1.0")
                .parser(new Parser<VisualStructure>() {

                    @Override
                    public boolean canParse(ParseContext context) {
                        return false;
                    }

                    @Override
                    public String toString(VisualStructure structure, int flags) {
                        return "(yeah why u looking at ma classes man/woman/they/them/other?!?!)";
                    }

                    @Override
                    public String toVariableNameString(VisualStructure structure) {
                        return "hi2";
                    }
                })
        );
    }
}