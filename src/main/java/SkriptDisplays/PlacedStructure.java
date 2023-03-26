package SkriptDisplays;

import jdk.javadoc.internal.doclets.toolkit.BaseConfiguration;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Transformation;

import java.io.Serializable;
import java.util.HashMap;

public class PlacedStructure implements Serializable {

    public HashMap<String, Entity> entities = new HashMap<>();
    public Float size = 1F;
    public Float rotation = 0F;
    Location location;
    public void placeStructure(Structure s, Location l) {
        location = l;
        Main.id++;
        HashMap<String, String> blocks = s.getBlocks();
        for (String key : blocks.keySet()) {
            String[] split = key.split(",");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            int z = Integer.parseInt(split[2]);
            Location loc = new Location(l.getWorld(), l.getBlockX() + x, l.getBlockY() + y, l.getBlockZ() + z);
            // spawn block display entity
            BlockDisplay e = (BlockDisplay) loc.getWorld().spawnEntity(loc, EntityType.BLOCK_DISPLAY);
            e.setBlock(Bukkit.createBlockData(blocks.get(key)));
            entities.put(key, e);
        }
    }
    public void remove() {
        for (Entity e : entities.values()) {
            e.remove();
        }
    }
    public void setSize(Float size) {
        System.out.println("Size: " + size);
        for (Entity e : entities.values()) {
            // get vector from l to e
            Location l = e.getLocation();
            double vx = l.getX() - location.getX();
            double vy = l.getY() - location.getY();
            double vz = l.getZ() - location.getZ();

            vx *= size / this.size;
            vy *= size / this.size;
            vz *= size / this.size;


            // teleport entity to new location
            e.teleport(new Location(location.getWorld(), location.getX() + vx, location.getY() + vy, location.getZ() + vz));
            BlockDisplay bd = (BlockDisplay) e;

            Transformation t = bd.getTransformation();
            t.getScale().set(size, size, size);
            bd.setTransformation(t);
        }
        this.size = size;
    }

    public void rotate(Float angle) {
        double radians = Math.toRadians(angle - rotation);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);

        for (Entity e : entities.values()) {
            // Get the vector from the location to the entity
            Location l = e.getLocation();
            double vx = l.getX() - location.getX();
            double vy = l.getY() - location.getY();
            double vz = l.getZ() - location.getZ();

            // Rotate the vector
            double newX = vx * cos - vz * sin;
            double newZ = vx * sin + vz * cos;

            // Teleport entity to the new location
            e.teleport(new Location(location.getWorld(), location.getX() + newX, location.getY() + vy, location.getZ() + newZ));

            // Rotate the block display entity
            BlockDisplay bd = (BlockDisplay) e;
            bd.setRotation(angle, 0);

        }

        this.rotation = angle;
    }
    public void startInterpolation(Float size, Float size2, Float size3, int duration) {
        System.out.println("Size: " + size + ", " + size2 + ", " + size3 + "Duration: " + duration);
        for (Entity e : entities.values()) {
            BlockDisplay bd = (BlockDisplay) e;
            bd.setInterpolationDelay(0);
            bd.setInterpolationDuration(duration);
            // offset
            Transformation t = bd.getTransformation();
            Location l = e.getLocation();
            double vx = l.getX() - location.getX();
            double vy = l.getY() - location.getY();
            double vz = l.getZ() - location.getZ();
            t.getTranslation().set(vx * (size - 1), vy * (size2 - 1), vz * (size3 - 1));
            t.getScale().set(size, size2, size3);
            bd.setTransformation(t);
        }
    }
}
