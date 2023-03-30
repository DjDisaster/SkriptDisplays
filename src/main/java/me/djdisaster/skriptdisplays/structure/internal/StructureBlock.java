package me.djdisaster.skriptdisplays.structure.internal;

import me.djdisaster.skriptdisplays.utils.BukkitUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

import java.util.Objects;


/**
 * DisplayBlock is the class which holds data about a given block in a structure
 * <p>
 * X, Y, Z, and its data
 */
public class StructureBlock {
    private final int x, y, z;
    private final BlockData block;

    public StructureBlock(Block block) {
        int[] pos = BukkitUtil.toArray(block.getLocation());
        x = pos[0];
        y = pos[1];
        z = pos[2];

        this.block = block.getBlockData();
    }

    public StructureBlock(Location loc, Material m) {
        int[] pos = BukkitUtil.toArray(loc);
        x = pos[0];
        y = pos[1];
        z = pos[2];

        this.block = m.createBlockData();
    }

    public StructureBlock(BlockData block, int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.block = block;
    }

    public BlockData getBlock() {
        return block;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "DisplayBlock{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", block=" + block.getAsString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StructureBlock that = (StructureBlock) o;
        return x == that.x && y == that.y && z == that.z && Objects.equals(block, that.block);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, block);
    }
}
