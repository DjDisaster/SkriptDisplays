    package SkriptDisplays;

    import org.bukkit.Location;

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
            // looping blocks between l and l2
            for (int x = Math.min(l.getBlockX(), l2.getBlockX()); x <= Math.max(l.getBlockX(), l2.getBlockX()); x++) {
                for (int y = Math.min(l.getBlockY(), l2.getBlockY()); y <= Math.max(l.getBlockY(), l2.getBlockY()); y++) {
                    for (int z = Math.min(l.getBlockZ(), l2.getBlockZ()); z <= Math.max(l.getBlockZ(), l2.getBlockZ()); z++) {
                        Location loc = new Location(l.getWorld(), x, y, z);
                        String block = loc.getBlock().getBlockData().getAsString();

                        // if block != air
                        if (block.equals("minecraft:air")) {
                            continue;
                        }

                        int vx = loc.getBlockX() - l.getBlockX();
                        int vy = loc.getBlockY() - l.getBlockY();
                        int vz = loc.getBlockZ() - l.getBlockZ();
                        String key = vx + "," + vy + "," + vz;
                        blocks.put(key, block);
                    }
                }
            }
        }
    }
