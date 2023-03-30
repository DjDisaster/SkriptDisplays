package me.djdisaster.skriptdisplays.structure.external;

import me.djdisaster.skriptdisplays.structure.internal.StructureBlock;
import org.bukkit.Location;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.util.Transformation;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class StructureEntity {
    private BlockDisplay entity;
    private StructureBlock block;

    public StructureEntity(BlockDisplay entity, StructureBlock block) {
        this.entity = entity;
        this.block = block;
    }

    public BlockDisplay getEntity() {
        return entity;
    }

    public void setEntity(BlockDisplay entity) {
        this.entity = entity;
    }

    public void interpolate(Transformation transform, Location loc, int duration) {
        entity.setInterpolationDelay(0);
        entity.setInterpolationDuration(duration);

        Location currentLocation = entity.getLocation();

        double dx = currentLocation.getX() - loc.getX();
        double dy = currentLocation.getY() - loc.getY();
        double dz = currentLocation.getZ() - loc.getZ();

        double nx = dx * transform.getScale().x;
        double ny = dy * transform.getScale().y;
        double nz = dz * transform.getScale().z;

        Vector3f newPos = new Vector3f((float) loc.getX() + (float) nx, (float) loc.getY() + (float) ny, (float) loc.getZ() + (float) nz);

        Quaternionf newRotLeft = new Quaternionf(transform.getLeftRotation());
        Quaternionf newRotRight = new Quaternionf(transform.getRightRotation());

        Vector3f newScale = new Vector3f(transform.getScale());

        Transformation t = entity.getTransformation();

        t.getTranslation().set(newPos);
        t.getLeftRotation().set(newRotLeft);
        t.getRightRotation().set(newRotRight);
        t.getScale().set(newScale);

        entity.setTransformation(t);
    }
}
