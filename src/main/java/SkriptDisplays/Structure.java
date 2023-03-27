    package SkriptDisplays;

    import org.bukkit.Location;
    import org.bukkit.Material;
    import org.bukkit.block.Block;

    import java.io.Serializable;
    import java.util.HashMap;

    public class Structure implements Serializable {

        private static final long serialVersionUID = 7L;
        private HashMap<String, String> blocks = new HashMap<>();


        public Structure() {
        }

        public HashMap<String, String> getBlocks() {
            return blocks;
        }
        public void setBlocks(HashMap<String, String> blocks) {
            this.blocks = blocks;
        }


        public Structure(Location l, Location l2) {

            int xMin = Math.min(l.getBlockX(), l2.getBlockX());
            int yMin = Math.min(l.getBlockY(), l2.getBlockY());
            int zMin = Math.min(l.getBlockZ(), l2.getBlockZ());

            int xMax = Math.max(l.getBlockX(), l2.getBlockX());
            int yMax = Math.max(l.getBlockY(), l2.getBlockY());
            int zMax = Math.max(l.getBlockZ(), l2.getBlockZ());

            // looping blocks between l and l2
            for (int x = xMin; x <= xMax; x++) {
                for (int y = yMin; y <= yMax; y++) {
                    for (int z = zMin; z <= zMax; z++) {
                        Location loc = new Location(l.getWorld(), x, y, z);
                        Block block = loc.getBlock();

                        // if block != air
                        if (block.getType() == Material.AIR)
                            continue;

                        int vx = loc.getBlockX() - l.getBlockX();
                        int vy = loc.getBlockY() - l.getBlockY();
                        int vz = loc.getBlockZ() - l.getBlockZ();
                        String key = vx + "," + vy + "," + vz;
                        blocks.put(key, block.getBlockData().getAsString());
                    }
                }
            }
        }
    }
