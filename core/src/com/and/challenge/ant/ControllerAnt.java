package com.and.challenge.ant;

import com.and.challenge.constant.TextureType;
import com.badlogic.gdx.Gdx;

import java.awt.*;

public class ControllerAnt {

    private final ModelAnt modelAnt;

    private float dT;

    private float antPauseTime;

    public ControllerAnt(float antPauseTime) {

        this.dT = Float.MAX_VALUE;

        this.modelAnt = new ModelAnt();

        this.antPauseTime = antPauseTime;

    }

    public void setAntPauseTime(float antPauseTime) { this.antPauseTime = antPauseTime; }

    public Rectangle getAnt() { return modelAnt.getAnt(); }

    public TextureType getRotation() { return modelAnt.getRotation(); }

    public int[] getTileJustSeen() { return modelAnt.getTileJustSeen(); }

    public TextureType getTileJustSeenColour() { return modelAnt.getTileJustSeenColour(); }

    public void render(TextureType gridColourCurrent, boolean isResetAnt) {

        this.dT += Gdx.graphics.getDeltaTime();

        if (dT >= antPauseTime) {

            this.modelAnt.render(gridColourCurrent, isResetAnt);
            this.dT = 0.0f;

        }

    }

}
