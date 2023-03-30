package me.djdisaster.skriptdisplays.structure.internal;

import me.djdisaster.skriptdisplays.structure.bounding.BoundingBoxAB;
import org.bukkit.Bukkit;

import java.util.HashMap;

public class DisplayStructure {
    private final StructureMap structureData;

    public DisplayStructure(BoundingBoxAB boxAB) {
        this.structureData = new StructureMap(boxAB);
    }

    public DisplayStructure(StructureMap structureData) {
        this.structureData = structureData;
    }

    public StructureMap getStructureData() {
        return structureData;
    }

    public static DisplayStructure fromLegacy(HashMap<String, String> map, BoundingBoxAB boxAB) {
        return new DisplayStructure(StructureMap.fromLegacy(map, boxAB));
    }
}
