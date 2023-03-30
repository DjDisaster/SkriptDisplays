package me.djdisaster.skriptdisplays.utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public class BukkitUtil {

    public static int[] toArray(Location loc) {
        return new int[] {loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()};
    }

    public static class LocationUtil {
        public static Location getMin(Location l1, Location l2) {
            int xMin = Math.min(l1.getBlockX(), l2.getBlockX());
            int yMin = Math.min(l1.getBlockY(), l2.getBlockY());
            int zMin = Math.min(l1.getBlockZ(), l2.getBlockZ());
            return new Location(l1.getWorld(), xMin, yMin, zMin);
        }

        public static Location getMax(Location l1, Location l2) {
            int xMax = Math.max(l1.getBlockX(), l2.getBlockX());
            int yMax = Math.max(l1.getBlockY(), l2.getBlockY());
            int zMax = Math.max(l1.getBlockZ(), l2.getBlockZ());
            return new Location(l1.getWorld(), xMax, yMax, zMax);
        }

        public static int getWidth(Location l1, Location l2) {
            return Math.abs(l1.getBlockX() - l2.getBlockX()) + 1;
        }

        public static int getHeight(Location l1, Location l2) {
            return Math.abs(l1.getBlockY() - l2.getBlockY()) + 1;
        }
    }
    static class BlockUtil {
        public static Block[] getBlocks(Location l1, Location l2) {
            if (l1.getWorld() != l2.getWorld()) throw new IllegalArgumentException("non-matching worlds");
            Location min = LocationUtil.getMin(l1, l2);
            Location max = LocationUtil.getMax(l1, l2);

            World world = l1.getWorld();

            Block[] blocks = new Block[LocationUtil.getWidth(l1, l2) * LocationUtil.getHeight(l1, l2) * LocationUtil.getWidth(l1, l2)];

            for (int x = min.getBlockX(); x < max.getBlockX(); x++) {
                for (int y = min.getBlockY(); y < max.getBlockY(); y++) {
                    for (int z = min.getBlockZ(); z < max.getBlockZ(); z++) {

                        int index = (x - min.getBlockX()) + (y - min.getBlockY()) *
                                LocationUtil.getWidth(l1, l2) + (z - min.getBlockZ()) *
                                LocationUtil.getWidth(l1, l2) * LocationUtil.getHeight(l1, l2);

                        blocks[index] = world.getBlockAt(x, y, z);
                    }
                }
            }

            return blocks;
        }
    }
}
