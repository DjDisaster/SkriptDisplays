package SkriptDisplays.Elements.Types;

import SkriptDisplays.Structure;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.classes.Serializer;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.yggdrasil.Fields;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.util.HashMap;

public class TypeStructure {
    public static void register() {
        Classes.registerClass(new ClassInfo<>(Structure.class, "displaystructure")
                .user("displaystructure")
                .name("TestStructure")
                .description("Represents a structure created from two locations.")
                .since("1.0")
                .parser(new Parser<Structure>() {

                    @Override
                    public boolean canParse(ParseContext context) {
                        return false;
                    }

                    @Override
                    public String toString(Structure structure, int flags) {
                        return "(Idk why your looking at this, but its a structure type in like Elements.Structure ok? Cool now join our discord https://discord.gg/EHJ2YuC4xs its not clickable because I hate you now.)";
                    }

                    @Override
                    public String toVariableNameString(Structure structure) {
                        return "hi2";
                    }
                })
                .serializer(new Serializer<Structure>() {
                    // serialize into x,y,z||blockdata==x,y,z||blockdata etc
                    @Override
                    public Fields serialize(Structure structure) throws NotSerializableException {
                        Fields f = new Fields();
                        f.putObject("map", structure.getBlocks());
                        return f;
                    }

                    @Override
                    public void deserialize(Structure o, Fields f) throws StreamCorruptedException, NotSerializableException {
                        assert false;
                    }

                    @Override
                    public Structure deserialize(Fields f) throws StreamCorruptedException, NotSerializableException {
                        HashMap<String, String> blocks = f.getObject("map", HashMap.class);
                        Structure structure = new Structure();
                        structure.setBlocks(blocks);
                        return structure;
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