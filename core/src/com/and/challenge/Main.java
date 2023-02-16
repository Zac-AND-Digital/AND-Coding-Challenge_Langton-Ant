package com.and.challenge;

import com.and.challenge.ant.ControllerAnt;
import com.and.challenge.constant.CameraResolution;
import com.and.challenge.grid.ControllerGrid;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {

	private OrthographicCamera camera;
	private SpriteBatch batch;

	private ControllerGrid controllerGrid;
	private ControllerAnt controllerAnt;

	@Override
	public void create () {

		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(true, CameraResolution.WIDTH, CameraResolution.HEIGHT);

		this.batch = new SpriteBatch();

		this.controllerGrid = new ControllerGrid(CameraResolution.WIDTH, CameraResolution.HEIGHT);
		this.controllerAnt = new ControllerAnt();

	}

	@Override
	public void render () {

		ScreenUtils.clear(Color.BLACK);

		this.camera.update();

		this.batch.setProjectionMatrix(camera.combined);

		this.batch.begin();

		this.controllerAnt.render(controllerGrid.getGridColourCurrent());

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
