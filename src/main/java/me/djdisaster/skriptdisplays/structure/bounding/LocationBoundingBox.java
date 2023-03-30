package me.djdisaster.skriptdisplays.structure.bounding;

import org.bukkit.Location;
import org.bukkit.World;

public interface LocationBoundingBox extends BoundingBox {

    Location getL1();
    Location getL2();


    Location getMinLocation();
    Location getMaxLocation();

    World getWorld();

    boolean contains(Location l);
}
