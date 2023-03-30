package me.djdisaster.skriptdisplays.structure.external;

import me.djdisaster.skriptdisplays.structure.internal.DisplayStructure;
import me.djdisaster.skriptdisplays.structure.internal.StructureMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Transformation;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class StructureEntityMap {
    private StructureMap map;
    private Location loc;
    private StructureEntity[] entities;

    private Transformation transform;

    public StructureEntityMap(StructureMap map, Location loc) {
        this.loc = loc;
        this.map = map;
        entities = new StructureEntity[map.getStructureData().length];
    }

    public void spawn() {
        for (int i = 0; i < entities.length; i++) {
            int x, y, z;

            int width = map.getBoxAB().getWidth();
            int height = map.getBoxAB().getHeight();
            int length = map.getBoxAB().getLength();

            x = i % width;
            y = (i / width) % height;
            z = (i / width / height) % length;

            BlockDisplay entity = (BlockDisplay) loc.getWorld().spawnEntity(loc, EntityType.BLOCK_DISPLAY);
            StructureEntity structureEntity = new StructureEntity(entity, map.getBlock(x, y, z));

            entities[i] = structureEntity;
        }
    }

    public void despawn() {
        for (StructureEntity entity : entities) {
            entity.getEntity().remove();
        }
    }

    public void rescale(float[] scale) {
        for (StructureEntity entity : entities) {
            Location entityLoc = entity.getEntity().getLocation();

            double dx = entityLoc.getX() - loc.getX();
            double dy = entityLoc.getY() - loc.getY();
            double dz = entityLoc.getZ() - loc.getZ();

            double nx = dx * scale[0];
            double ny = dy * scale[1];
            double nz = dz * scale[2];

            entityLoc.setX(loc.getX() + nx);
            entityLoc.setY(loc.getY() + ny);
            entityLoc.setZ(loc.getZ() + nz);

            entity.getEntity().teleport(entityLoc);
        }
        Transformation scaleTransform = new Transformation(
                new Vector3f(scale[0], scale[1], scale[2]),
                transform.getLeftRotation(),
                transform.getTranslation(),
                transform.getRightRotation()
        );

        updateTransform(scaleTransform);

        transform = scaleTransform;
    }

    public void rotateX(float angle) {
        for (StructureEntity entity : entities) {
            Location entityLoc = entity.getEntity().getLocation();

            double dx = entityLoc.getX() - loc.getX();
            double dy = entityLoc.getY() - loc.getY();
            double dz = entityLoc.getZ() - loc.getZ();

            double nx = dx;
            double ny = dy * Math.cos(angle) - dz * Math.sin(angle);
            double nz = dy * Math.sin(angle) + dz * Math.cos(angle);

            entityLoc.setX(loc.getX() + nx);
            entityLoc.setY(loc.getY() + ny);
            entityLoc.setZ(loc.getZ() + nz);

            entity.getEntity().teleport(entityLoc);
        }

        Transformation rotationTransform = new Transformation(
                transform.getScale(),
                transform.getLeftRotation(),
                transform.getTranslation(),
                transform.getRightRotation().mul(new Quaternionf().rotateX(angle))
        );

        updateTransform(rotationTransform);
    }


    public void rotateY(float angle) {
        for (StructureEntity entity : entities) {
            Location entityLoc = entity.getEntity().getLocation();

            double dx = entityLoc.getX() - loc.getX();
            double dy = entityLoc.getY() - loc.getY();
            double dz = entityLoc.getZ() - loc.getZ();

            double nx = dx * Math.cos(angle) + dz * Math.sin(angle);
            double ny = dy;
            double nz = -dx * Math.sin(angle) + dz * Math.cos(angle);

            entityLoc.setX(loc.getX() + nx);
            entityLoc.setY(loc.getY() + ny);
            entityLoc.setZ(loc.getZ() + nz);

            entity.getEntity().teleport(entityLoc);
        }

        Transformation rotationTransform = new Transformation(
                transform.getScale(),
                transform.getLeftRotation(),
                transform.getTranslation(),
                transform.getRightRotation().mul(new Quaternionf().rotateY(angle))
        );

        updateTransform(rotationTransform);
    }

    public void rotateZ(float angle) {
        for (StructureEntity entity : entities) {
            Location entityLoc = entity.getEntity().getLocation();

            double dx = entityLoc.getX() - loc.getX();
            double dy = entityLoc.getY() - loc.getY();
            double dz = entityLoc.getZ() - loc.getZ();

            double nx = dx * Math.cos(angle) - dy * Math.sin(angle);
            double ny = dx * Math.sin(angle) + dy * Math.cos(angle);
            double nz = dz;

            entityLoc.setX(loc.getX() + nx);
            entityLoc.setY(loc.getY() + ny);
            entityLoc.setZ(loc.getZ() + nz);

            entity.getEntity().teleport(entityLoc);
        }

        Transformation rotationTransform = new Transformation(
                transform.getScale(),
                transform.getLeftRotation(),
                transform.getTranslation(),
                transform.getRightRotation().mul(new Quaternionf().rotateZ(angle))
        );

        updateTransform(rotationTransform);
    }

    public void rotate(float[] leftRot, float[] rightRot) {
        Transformation rotationTransform = new Transformation(
                transform.getScale(),
                new Quaternionf(leftRot[0], leftRot[1], leftRot[2], leftRot[3]),
                transform.getTranslation(),
                new Quaternionf(rightRot[0], rightRot[1], rightRot[2], rightRot[3])
        );
        updateTransform(rotationTransform);

        transform = rotationTransform;
    }

    public void translate(float[] translation) {
        Transformation translationTransform = new Transformation(
                transform.getScale(),
                transform.getLeftRotation(),
                new Vector3f(translation[0], translation[1], translation[2]),
                transform.getRightRotation()
        );
        updateTransform(translationTransform);

        transform = translationTransform;
    }

    public void updateTransform() {
        updateTransform(transform);
    }

    public void updateTransform(Transformation transform) {
        for (StructureEntity entity : entities) {
            entity.getEntity().setTransformation(transform);
        }
    }

    public void interpolate(Transformation transform, int duration) {
        for (StructureEntity entity : entities) {
            entity.interpolate(transform, loc, duration);
        }
    }

    public Transformation getTransform() {
        return transform;
    }
}
