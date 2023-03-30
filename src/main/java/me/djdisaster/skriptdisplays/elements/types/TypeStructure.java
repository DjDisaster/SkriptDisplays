package me.djdisaster.skriptdisplays.elements.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.classes.Serializer;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.yggdrasil.Fields;
import me.djdisaster.skriptdisplays.structure.bounding.BoundingBoxAB;
import me.djdisaster.skriptdisplays.structure.internal.DisplayStructure;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.util.HashMap;

public class TypeStructure {
    public static void register() {
        Classes.registerClass(new ClassInfo<>(DisplayStructure.class, "displaystructure")
                .user("displaystructure")
                .name("TestStructure")
                .description("Represents a structure created from two locations.")
                .since("1.0")
                .parser(new Parser<DisplayStructure>() {

                    @Override
                    public boolean canParse(ParseContext context) {
                        return false;
                    }

                    @Override
                    public String toString(DisplayStructure structure, int flags) {
                        return "(Idk why your looking at this, but its a structure type in like Elements.Structure ok? Cool now join our discord https://discord.gg/EHJ2YuC4xs its not clickable because I hate you now.)";
                    }

                    @Override
                    public String toVariableNameString(DisplayStructure structure) {
                        return "hi2";
                    }
                })
                .serializer(new Serializer<DisplayStructure>() {
                    @Override
                    public Fields serialize(DisplayStructure structure) throws NotSerializableException {
                        Fields f = new Fields();
                        f.putObject("map", structure.getStructureData().getAsLegacy());
                        f.putObject("boundingbox", structure.getStructureData().getBoxAB());
                        return f;
                    }

                    @Override
                    public void deserialize(DisplayStructure o, Fields f) throws StreamCorruptedException, NotSerializableException {
                        assert false;
                    }

                    @Override
                    public DisplayStructure deserialize(Fields f) throws StreamCorruptedException, NotSerializableException {
                        return DisplayStructure.fromLegacy(f.getObject("map", HashMap.class), f.getObject("boundingbox", BoundingBoxAB.class));
                    }

                    @Override
                    public boolean mustSyncDeserialization() {
                        return false;
                    }

                    @Override
                    protected boolean canBeInstantiated() {
                        return false;
                    }

                })

        );
    }
}