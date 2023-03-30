package me.djdisaster.skriptdisplays.structure.internal;

import me.djdisaster.skriptdisplays.structure.bounding.BoundingBoxAB;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.BlockDisplay;

import java.util.HashMap;

public class StructureMap {
    private final BoundingBoxAB boxAB;
    private StructureBlock[][][] structureData;

    public StructureMap(BoundingBoxAB boxAB) {
        this.boxAB = boxAB;
        structureData = new StructureBlock[boxAB.getWidth()][boxAB.getHeight()][boxAB.getLength()];
    }

    public StructureBlock[][][] getStructureData() {
        return structureData;
    }

    public void setStructureData(StructureBlock[][][] structureData) {
        this.structureData = structureData;
    }

    public StructureBlock getBlock(int x, int y, int z) {
        return structureData[x][y][z];
    }

    public void setBlock(int x, int y, int z, StructureBlock block) {
        structureData[x][y][z] = block;
    }

    public void setBlock(int x, int y, int z, Block block) {
        structureData[x][y][z] = new StructureBlock(block);
    }

    public BoundingBoxAB getBoxAB() {
        return boxAB;
    }

    public HashMap<String, String> getAsLegacy() {
        HashMap<String, String> map = new HashMap<>();
        for (int x = 0; x < structureData.length; x++) {
            for (int y = 0; y < structureData[x].length; y++) {
                for (int z = 0; z < structureData[x][y].length; z++) {

                    StructureBlock block = structureData[x][y][z];
                    if (block != null) {
                        map.put(x + "," + y + "," + z, block.getBlock().getAsString());
                    }
                }
            }
        }


        return map;
    }

    public static StructureMap fromLegacy(HashMap<String, String> map, BoundingBoxAB boxAB) {
        StructureMap structureMap = new StructureMap(boxAB);
        for (String key : map.keySet()) {
            String[] split = key.split(",");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            int z = Integer.parseInt(split[2]);

            structureMap.setBlock(x, y, z, new StructureBlock(Bukkit.createBlockData(map.get(key)), x, y, z));
        }

        return structureMap;
    }
}
