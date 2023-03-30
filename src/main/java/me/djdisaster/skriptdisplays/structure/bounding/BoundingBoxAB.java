package me.djdisaster.skriptdisplays.structure.bounding;

import me.djdisaster.skriptdisplays.utils.BukkitUtil;
import org.bukkit.Location;
import org.bukkit.World;


import java.util.Locale;

public class BoundingBoxAB implements LocationBoundingBox {
    private Location l1;
    private Location l2;

    private Location min;
    private Location max;

    public BoundingBoxAB(Location l1, Location l2) {
        this.l1 = l1;
        this.l2 = l2;

        this.min = BukkitUtil.LocationUtil.getMin(l1, l2);
        this.max = BukkitUtil.LocationUtil.getMax(l1, l2);
    }

    public void reshape(Location l1, Location l2) {
        this.l1 = l1;
        this.l2 = l2;

        this.min = BukkitUtil.LocationUtil.getMin(l1, l2);
        this.max = BukkitUtil.LocationUtil.getMax(l1, l2);
    }

    public void increase(int size) {
        this.l1.add(-size, -size, -size);
        this.l2.add(size, size, size);

        this.min = BukkitUtil.LocationUtil.getMin(l1, l2);
        this.max = BukkitUtil.LocationUtil.getMax(l1, l2);
    }

    public void decrease(int size) {
        this.l1.add(size, size, size);
        this.l2.add(-size, -size, -size);

        this.min = BukkitUtil.LocationUtil.getMin(l1, l2);
        this.max = BukkitUtil.LocationUtil.getMax(l1, l2);
    }

    public void move(int x, int y, int z) {
        this.l1.add(x, y, z);
        this.l2.add(x, y, z);

        this.min = BukkitUtil.LocationUtil.getMin(l1, l2);
        this.max = BukkitUtil.LocationUtil.getMax(l1, l2);
    }

    public void move(Location l) {
        this.l1.add(l);
        this.l2.add(l);

        this.min = BukkitUtil.LocationUtil.getMin(l1, l2);
        this.max = BukkitUtil.LocationUtil.getMax(l1, l2);
    }


    @Override
    public int getMinX() {
        return min.getBlockX();
    }

    @Override
    public int getMinY() {
        return min.getBlockY();
    }

    @Override
    public int getMinZ() {
        return min.getBlockZ();
    }

    @Override
    public int getMaxX() {
        return max.getBlockX();
    }

    @Override
    public int getMaxY() {
        return max.getBlockY();
    }

    @Override
    public int getMaxZ() {
        return max.getBlockZ();
    }

    @Override
    public int getWidth() {
        return Math.abs(l1.getBlockX() - l2.getBlockX());
    }

    @Override
    public int getHeight() {
        return Math.abs(l1.getBlockY() - l2.getBlockY());
    }

    @Override
    public int getLength() {
        return Math.abs(l1.getBlockZ() - l2.getBlockZ());
    }

    @Override
    public boolean contains(int x, int y, int z) {
        return x >= getMinX() && x <= getMaxX() && y >= getMinY() && y <= getMaxY() && z >= getMinZ() && z <= getMaxZ();
    }

    @Override
    public Location getL1() {
        return l1;
    }

    @Override
    public Location getL2() {
        return l2;
    }

    @Override
    public Location getMinLocation() {
        return min;
    }

    @Override
    public Location getMaxLocation() {
        return min;
    }

    @Override
    public World getWorld() {
        return l1.getWorld();
    }

    @Override
    public boolean contains(Location l) {
        return contains(l.getBlockX(), l.getBlockY(), l.getBlockZ()) && l.getWorld().equals(getWorld());
    }

}
