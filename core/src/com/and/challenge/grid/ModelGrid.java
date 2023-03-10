package com.and.challenge.grid;

import com.and.challenge.constant.TextureMap;
import com.and.challenge.constant.TextureType;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class ModelGrid {

    private final Texture[][] gridTexture;
    private final Rectangle[][] gridRectangle;
    private final TextureType[][] gridTextureType;

    private TextureType gridColourCurrent;

    private final int maxRow;
    private final int maxColumn;

    private boolean resetAnt;

    ModelGrid(int screenWidth, int screenHeight) {

        screenWidth /= (TextureMap.TEXTURE_MAP.get(TextureType.GRID_WHITE).getWidth() * 0.5);
        screenHeight /= (TextureMap.TEXTURE_MAP.get(TextureType.GRID_WHITE).getHeight() * 0.5);

        this.maxRow = (screenWidth / 2) - 1;
        this.maxColumn = (screenHeight / 2) - 1;

        this.gridTexture = new Texture[screenHeight][screenWidth];
        this.gridRectangle = new Rectangle[screenHeight][screenWidth];
        this.gridTextureType = new TextureType[screenHeight][screenWidth];

        for (int row = 0; row < gridTexture.length; row++) {

            for (int column = 0; column < gridTexture[row].length; column++) {

                this.gridTexture[row][column] = TextureMap.TEXTURE_MAP.get(TextureType.GRID_WHITE);

                this.gridRectangle[row][column] = new Rectangle();
                this.gridRectangle[row][column].width = gridTexture[row][column].getWidth();
                this.gridRectangle[row][column].height = gridTexture[row][column].getHeight();
                this.gridRectangle[row][column].x = row * gridRectangle[row][column].width;
                this.gridRectangle[row][column].y = column * gridRectangle[row][column].height;

                this.gridTextureType[row][column] = TextureType.GRID_WHITE;

            }

        }

        this.gridColourCurrent = TextureType.GRID_WHITE;

        this.resetAnt = false;

    }

    TextureType getGridColourCurrent() { return gridColourCurrent; }

    boolean isResetAnt() { return resetAnt; }

    void render(SpriteBatch batch, Rectangle ant, TextureType rotation, int[] tileJustSeen, TextureType tileJustSeenColour) {

        if (this.resetAnt) this.resetAnt = false;

        this.gridTexture[tileJustSeen[0]][tileJustSeen[1]] = TextureMap.TEXTURE_MAP.get(tileJustSeenColour);
        this.gridTextureType[tileJustSeen[0]][tileJustSeen[1]] = tileJustSeenColour;

        int antRowIndex = ant.x / ant.width;
        int antColumnIndex = ant.y / ant.height;

        if (antRowIndex <= 0 || antRowIndex >= maxRow || antColumnIndex <= 0 || antColumnIndex >= maxColumn) {
            this.resetAnt = true;
        }

        this.gridColourCurrent = gridTextureType[antRowIndex][antColumnIndex];

        draw(batch, ant, rotation);

    }

    private void draw(SpriteBatch batch, Rectangle ant, TextureType rotation) {

        for (int row = 0; row < gridTexture.length; row++) {
            for (int column = 0; column < gridTexture[row].length; column++) {
                batch.draw(gridTexture[row][column], gridRectangle[row][column].x, gridRectangle[row][column].y);
            }
        }

        batch.draw(TextureMap.TEXTURE_MAP.get(rotation), ant.x, ant.y);

    }

    void invert(Rectangle ant) {

        for (int row = 0; row < gridTextureType.length; row++) {

            for (int column = 0; column < gridTextureType[row].length; column++) {

                if (gridTextureType[row][column] == TextureType.GRID_WHITE) {
                    this.gridTextureType[row][column] = TextureType.GRID_BLACK;
                    this.gridTexture[row][column] = TextureMap.TEXTURE_MAP.get(TextureType.GRID_BLACK);
                }
                else {
                    this.gridTextureType[row][column] = TextureType.GRID_WHITE;
                    gridTexture[row][column] = TextureMap.TEXTURE_MAP.get(TextureType.GRID_WHITE);
                }

            }

        }

        int antRowIndex = ant.x / ant.width;
        int antColumnIndex = ant.y / ant.height;

        this.gridColourCurrent = gridTextureType[antRowIndex][antColumnIndex];

    }

    void dispose() {

        for (TextureType textureType : TextureType.values()) TextureMap.TEXTURE_MAP.get(textureType).dispose();

        for (Texture[] row : gridTexture) {
            for (Texture columnTexture : row) {
                columnTexture.dispose();
            }
        }

    }

}
