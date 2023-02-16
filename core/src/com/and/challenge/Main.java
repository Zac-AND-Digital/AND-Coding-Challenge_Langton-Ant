package com.and.challenge;

import com.and.challenge.ant.ControllerAnt;
import com.and.challenge.constant.CameraResolution;
import com.and.challenge.constant.TextureMap;
import com.and.challenge.constant.TextureType;
import com.and.challenge.grid.ControllerGrid;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {

	private static final float ANT_PAUSE_TIME_INC_DEC = 0.064f;

	private OrthographicCamera camera;
	private SpriteBatch batch;

	private ControllerGrid controllerGrid;
	private ControllerAnt controllerAnt;

	private float antPauseTime;

	@Override
	public void create () {

		int gWidth = TextureMap.TEXTURE_MAP.get(TextureType.GRID_WHITE).getWidth();
		int gHeight = TextureMap.TEXTURE_MAP.get(TextureType.GRID_WHITE).getHeight();

		System.out.println("Screen Resolution: " + Gdx.graphics.getBackBufferWidth() + "x" + Gdx.graphics.getBackBufferHeight());

		CameraResolution.WIDTH = (int) (gWidth * (Math.floor((double) CameraResolution.WIDTH / (double) gWidth)));
		CameraResolution.HEIGHT = (int) (gHeight * (Math.floor((double) CameraResolution.HEIGHT / (double) gHeight)));

		System.out.println("Camera Resolution -> " + CameraResolution.WIDTH + "x" + CameraResolution.HEIGHT);

		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(true, CameraResolution.WIDTH, CameraResolution.HEIGHT);

		this.batch = new SpriteBatch();

		this.antPauseTime = 0.256f;

		this.controllerGrid = new ControllerGrid(CameraResolution.WIDTH, CameraResolution.HEIGHT);
		this.controllerAnt = new ControllerAnt(antPauseTime);

	}

	@Override
	public void render () {

		ScreenUtils.clear(Color.BLACK);

		this.camera.update();

		this.batch.setProjectionMatrix(camera.combined);

		if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {

			this.antPauseTime += ANT_PAUSE_TIME_INC_DEC;
			if (antPauseTime > 1.024f) this.antPauseTime = 1.024f;
			this.controllerAnt.setAntPauseTime(antPauseTime);

		} else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {

			this.antPauseTime -= ANT_PAUSE_TIME_INC_DEC;
			if (antPauseTime < 0.064f) this.antPauseTime = 0.128f;
			this.controllerAnt.setAntPauseTime(antPauseTime);

		} else if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {

			this.antPauseTime = 0.0f;
			this.controllerAnt.setAntPauseTime(antPauseTime);

		}

		this.batch.begin();

		this.controllerAnt.render(controllerGrid.getGridColourCurrent(), controllerGrid.isResetAnt());

		this.controllerGrid.render(
				batch,
				controllerAnt.getAnt(),
				controllerAnt.getRotation(),
				controllerAnt.getTileJustSeen(),
				controllerAnt.getTileJustSeenColour());

		this.batch.end();

	}
	
	@Override
	public void dispose () {

		this.batch.dispose();
		this.controllerGrid.dispose();

	}

}
