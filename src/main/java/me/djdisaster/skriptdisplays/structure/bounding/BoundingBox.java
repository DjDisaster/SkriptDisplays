package me.djdisaster.skriptdisplays.structure.bounding;

import org.bukkit.Location;
import org.bukkit.World;

public interface BoundingBox {

    int getMinX();
    int getMinY();
    int getMinZ();

    int getMaxX();
    int getMaxY();
    int getMaxZ();

    int getWidth();
    int getHeight();
    int getLength();

    boolean contains(int x, int y, int z);
}
