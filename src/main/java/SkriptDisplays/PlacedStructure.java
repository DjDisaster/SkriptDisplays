package SkriptDisplays;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Display;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Transformation;
import org.joml.Vector3f;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class PlacedStructure implements Serializable {

    public HashMap<String, Entity> entities = new HashMap<>();
    public Float sizeX = 1F;
    public Float sizeY = 1F;
    public Float sizeZ = 1F;
    public Float rotationX = 0F;
    public Float rotationY = 0F;
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

            vx *= size / this.sizeX;
            vy *= size / this.sizeY;
            vz *= size / this.sizeZ;


            // teleport entity to new location
            e.teleport(new Location(location.getWorld(), location.getX() + vx, location.getY() + vy, location.getZ() + vz));
            BlockDisplay bd = (BlockDisplay) e;

            Transformation t = bd.getTransformation();
            t.getScale().set(size, size, size);
            bd.setTransformation(t);
        }
        this.sizeX = size;
        this.sizeY = size;
        this.sizeZ = size;
    }

    public void rotateX(Float angle) {
        double radians = Math.toRadians(angle - rotationX);
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

        this.rotationX = angle;
    }

    public void rotateY(Float angle) {
        double radians = Math.toRadians(angle - rotationY);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);

        for (Entity e : entities.values()) {
            // Get the vector from the location to the entity
            Location l = e.getLocation();
            double vx = l.getX() - location.getX();
            double vy = l.getY() - location.getY();
            double vz = l.getZ() - location.getZ();

            // Rotate the vector
            double newX = vx * cos + vz * sin;
            double newY = vy;
            double newZ = -vx * sin + vz * cos;

            // multiply by size
            newX *= sizeX;
            newY *= sizeY;
            newZ *= sizeZ;

            // Teleport entity to the new location
            e.teleport(new Location(location.getWorld(), location.getX() + newX, location.getY() + newY, location.getZ() + newZ));

            // Rotate the block display entity
            BlockDisplay bd = (BlockDisplay) e;
            bd.setRotation(0, angle);

        }

        this.rotationY = angle;
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
        this.sizeX = size;
        this.sizeY = size2;
        this.sizeZ = size3;
    }
    public void setBrightness(int brightness) {
        Display.Brightness b = new Display.Brightness(brightness, brightness);
        for (Entity e : entities.values()) {
            BlockDisplay bd = (BlockDisplay) e;
            bd.setBrightness(b);
        }
    }
    public void move(Location l, int duration) {
        for (Entity e : entities.values()) {
            BlockDisplay bd = (BlockDisplay) e;
            bd.setInterpolationDelay(0);
            bd.setInterpolationDuration(duration);
            // offset
            Transformation t = bd.getTransformation();
            t.getTranslation().set(l.getX() - location.getX(), l.getY() - location.getY(), l.getZ() - location.getZ());
            bd.setTransformation(t);

        }
    }
    public void fixPositions() {
        for (Entity e : entities.values()) {
            // Convert any offsets into absolute positions
            BlockDisplay bd = (BlockDisplay) e;
            Transformation t = bd.getTransformation();

            // Get current position and translation
            Location currentLocation = e.getLocation();
            Vector3f translation = t.getTranslation();

            // Calculate new absolute position
            double newX = currentLocation.getX() + translation.x;
            double newY = currentLocation.getY() + translation.y;
            double newZ = currentLocation.getZ() + translation.z;

            // Teleport entity to the new absolute position
            e.teleport(new Location(currentLocation.getWorld(), newX, newY, newZ));

            // Reset translation to (0, 0, 0)
            t.getTranslation().set(0, 0, 0);
            bd.setTransformation(t);
        }
    }
}
