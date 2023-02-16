package com.and.challenge.grid;

import com.and.challenge.constant.TextureType;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class ControllerGrid {

    private final ModelGrid modelGrid;

    public ControllerGrid(int screenWidth, int screenHeight) {

        this.modelGrid = new ModelGrid(screenWidth, screenHeight);

    }

    public TextureType getGridColourCurrent() { return modelGrid.getGridColourCurrent(); }

    public void render(SpriteBatch batch, Rectangle ant, TextureType rotation, int[] tileJustSeen, TextureType tileJustSeenColour) {

        this.modelGrid.render(batch, ant, rotation, tileJustSeen, tileJustSeenColour);

    }

    public void dispose() {

        this.modelGrid.dispose();

    }

}
