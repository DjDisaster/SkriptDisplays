package me.djdisaster.skriptdisplays.structure.external;

import me.djdisaster.skriptdisplays.structure.internal.DisplayStructure;
import org.bukkit.Location;

public class VisualStructure {
    private DisplayStructure structure;
    private StructureEntityMap entityMap;

    public VisualStructure(DisplayStructure structure, Location loc) {
        this.structure = structure;
        entityMap = new StructureEntityMap(structure.getStructureData(), loc);
    }

    public void place() {
        entityMap.spawn();
    }

    public void remove() {
        entityMap.despawn();
    }

    public void rescale(float[] scale) {
        entityMap.rescale(scale);
    }

    public void rotate(float[] leftRotation, float[] rightRotation) {
        entityMap.rotate(leftRotation, rightRotation);
    }

    public void translate(float[] translation) {
        entityMap.translate(translation);
    }

    public StructureEntityMap getEntityMap() {
        return entityMap;
    }
}
