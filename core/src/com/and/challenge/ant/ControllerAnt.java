package com.and.challenge.ant;

import com.and.challenge.constant.TextureType;
import com.badlogic.gdx.Gdx;

import java.awt.*;

public class ControllerAnt {

    private final ModelAnt modelAnt;

    private float dT;

    public ControllerAnt() {

        this.dT = Float.MAX_VALUE;

        this.modelAnt = new ModelAnt();

    }

    public Rectangle getAnt() { return modelAnt.getAnt(); }

    public TextureType getRotation() { return modelAnt.getRotation(); }

    public int[] getTileJustSeen() { return modelAnt.getTileJustSeen(); }

    public TextureType getTileJustSeenColour() { return modelAnt.getTileJustSeenColour(); }

    public void render(TextureType gridColourCurrent) {

        this.dT += Gdx.graphics.getDeltaTime();

        if (dT >= 0.256f) {

            this.modelAnt.render(gridColourCurrent);
            this.dT = 0.0f;

        }

    }

}
