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
	private float[] defaultCameraPosition;
	private SpriteBatch batch;

	private ControllerGrid controllerGrid;
	private ControllerAnt controllerAnt;

	private float antPauseTime;
	private boolean followAnt;
	private boolean turboMode;


	@Override
	public void create () {

		Gdx.graphics.setVSync(true);

		int gWidth = TextureMap.TEXTURE_MAP.get(TextureType.GRID_WHITE).getWidth();
		int gHeight = TextureMap.TEXTURE_MAP.get(TextureType.GRID_WHITE).getHeight();

		CameraResolution.WIDTH = (int) (gWidth * (Math.floor((double) CameraResolution.WIDTH / (double) gWidth)));
		CameraResolution.HEIGHT = (int) (gHeight * (Math.floor((double) CameraResolution.HEIGHT / (double) gHeight)));

		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(true, CameraResolution.WIDTH, CameraResolution.HEIGHT);
		this.defaultCameraPosition = new float[] {camera.zoom, camera.position.x, camera.position.y};

		this.batch = new SpriteBatch();

		this.antPauseTime = 0.256f;
		this.followAnt = false;
		this.turboMode = false;

		this.controllerGrid = new ControllerGrid(CameraResolution.WIDTH, CameraResolution.HEIGHT);
		this.controllerAnt = new ControllerAnt(antPauseTime);

	}

	@Override
	public void render () {

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();

		if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {

			Gdx.graphics.setVSync(true);
			this.turboMode = false;
			this.antPauseTime += ANT_PAUSE_TIME_INC_DEC;
			if (antPauseTime > 1.024f) this.antPauseTime = 1.024f;
			this.controllerAnt.setAntPauseTime(antPauseTime);

		} else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {

			Gdx.graphics.setVSync(true);
			this.turboMode = false;
			this.antPauseTime -= ANT_PAUSE_TIME_INC_DEC;
			if (antPauseTime < 0.064f) this.antPauseTime = 0.128f;
			this.controllerAnt.setAntPauseTime(antPauseTime);

		} else if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {

			this.turboMode ^= true;

			if (turboMode) {

				this.antPauseTime = 0.0f;
				Gdx.graphics.setVSync(false);
				this.controllerAnt.setAntPauseTime(antPauseTime);

			} else {

				this.antPauseTime = 0.256f;
				Gdx.graphics.setVSync(true);
				this.controllerAnt.setAntPauseTime(antPauseTime);

			}

		} else if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE)) {

			create();
			return;

		} else if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {

			this.followAnt ^= true;

			if (! followAnt) {

				this.camera.zoom = defaultCameraPosition[0];
				this.camera.position.x = defaultCameraPosition[1];
				this.camera.position.y = defaultCameraPosition[2];

			}

		}

		if (followAnt) {

			this.camera.zoom = 0.33f;
			this.camera.position.x = controllerAnt.getAnt().x;
			this.camera.position.y = controllerAnt.getAnt().y;

		}

		ScreenUtils.clear(Color.BLACK);

		this.camera.update();

		this.batch.setProjectionMatrix(camera.combined);

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
