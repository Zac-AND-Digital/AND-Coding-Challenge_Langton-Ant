package com.and.challenge.ant;

import com.and.challenge.constant.CameraResolution;
import com.and.challenge.constant.TextureMap;
import com.and.challenge.constant.TextureType;

import java.awt.*;

public class ModelAnt {

    private enum Direction {

        NORTH,
        EAST,
        SOUTH,
        WEST

    }

    private final Rectangle ant;

    private Direction direction;
    private TextureType antTexture;

    private final int[] tileJustSeen;
    private TextureType tileJustSeenColour;

    private final int moveAmount;

    ModelAnt() {

        this.direction = Direction.NORTH;
        this.moveAmount = TextureMap.TEXTURE_MAP.get(TextureType.ANT_NORTH).getHeight();

        this.ant = new Rectangle();
        this.ant.width = TextureMap.TEXTURE_MAP.get(TextureType.ANT_NORTH).getWidth();
        this.ant.height = TextureMap.TEXTURE_MAP.get(TextureType.ANT_NORTH).getHeight();
        this.ant.x = ant.width * (Math.round((float) (CameraResolution.WIDTH / 2) / (float) ant.width));
        this.ant.y = ant.height * (Math.round((float) (CameraResolution.HEIGHT / 2) / (float) ant.height));

        this.tileJustSeen = new int[] {ant.x / ant.width, ant.y / ant.height};

    }

    Rectangle getAnt() { return ant; }

    TextureType getRotation() { return antTexture; }

    int[] getTileJustSeen() { return tileJustSeen; }

    TextureType getTileJustSeenColour() { return tileJustSeenColour; }

    void render(TextureType gridColourCurrent) {

        this.tileJustSeen[0] = ant.x / ant.width;
        this.tileJustSeen[1] = ant.y / ant.height;

        if (gridColourCurrent == TextureType.GRID_WHITE) {

            this.tileJustSeenColour = TextureType.GRID_BLACK;

            switch (direction) {

                case NORTH:
                    this.direction = Direction.WEST;
                    this.antTexture = TextureType.ANT_WEST;
                    this.ant.x -= moveAmount;
                    break;

                case EAST:
                    this.direction = Direction.NORTH;
                    this.antTexture = TextureType.ANT_SOUTH;
                    this.ant.y -= moveAmount;
                    break;

                case SOUTH:
                    this.direction = Direction.EAST;
                    this.antTexture = TextureType.ANT_EAST;
                    this.ant.x += moveAmount;
                    break;

                case WEST:
                    this.direction = Direction.SOUTH;
                    this.antTexture = TextureType.ANT_NORTH;
                    this.ant.y += moveAmount;
                    break;

            }

        }

        if (gridColourCurrent == TextureType.GRID_BLACK) {

            this.tileJustSeenColour = TextureType.GRID_WHITE;

            switch (direction) {

                case NORTH:
                    this.direction = Direction.EAST;
                    this.antTexture = TextureType.ANT_EAST;
                    this.ant.x += moveAmount;
                    break;

                case EAST:
                    this.direction = Direction.SOUTH;
                    this.antTexture = TextureType.ANT_NORTH;
                    this.ant.y += moveAmount;
                    break;

                case SOUTH:
                    this.direction = Direction.WEST;
                    this.antTexture = TextureType.ANT_EAST;
                    this.ant.x -= moveAmount;
                    break;

                case WEST:
                    this.direction = Direction.NORTH;
                    this.antTexture = TextureType.ANT_SOUTH;
                    this.ant.y -= moveAmount;
                    break;

            }

        }


        System.out.println("Ant Direction -> " + direction.name());
        System.out.println("Ant Facing -> " + antTexture.name());

    }

}
