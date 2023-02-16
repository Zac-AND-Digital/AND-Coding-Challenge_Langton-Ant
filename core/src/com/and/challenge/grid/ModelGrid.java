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

    ModelGrid(int screenWidth, int screenHeight) {

        screenWidth /= (TextureMap.TEXTURE_MAP.get(TextureType.GRID_WHITE).getWidth() * 0.5);
        screenHeight /= (TextureMap.TEXTURE_MAP.get(TextureType.GRID_WHITE).getHeight() * 0.5);

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

    }

    TextureType getGridColourCurrent() { return gridColourCurrent; }

    void render(SpriteBatch batch, Rectangle ant, TextureType rotation, int[] tileJustSeen, TextureType tileJustSeenColour) {

        this.gridTexture[tileJustSeen[0]][tileJustSeen[1]] = TextureMap.TEXTURE_MAP.get(tileJustSeenColour);
        this.gridTextureType[tileJustSeen[0]][tileJustSeen[1]] = tileJustSeenColour;

        int antRowIndex = ant.x / ant.width;
        int antColumnIndex = ant.y / ant.height;

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

    void dispose() {

        for (TextureType textureType : TextureType.values()) TextureMap.TEXTURE_MAP.get(textureType).dispose();

        for (Texture[] row : gridTexture) {
            for (Texture columnTexture : row) {
                columnTexture.dispose();
            }
        }

    }

}
