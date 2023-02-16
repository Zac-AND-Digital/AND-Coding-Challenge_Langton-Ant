package com.and.challenge.constant;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class TextureMap {

    public static final Map<TextureType, Texture> TEXTURE_MAP;

    static {

        TEXTURE_MAP = new HashMap<>();
        TEXTURE_MAP.put(TextureType.GRID_WHITE, new Texture(Gdx.files.internal("Grid_White.PNG")));
        TEXTURE_MAP.put(TextureType.GRID_BLACK, new Texture(Gdx.files.internal("Grid_Black.PNG")));
        TEXTURE_MAP.put(TextureType.ANT_NORTH, new Texture(Gdx.files.internal("Ant_North.PNG")));
        TEXTURE_MAP.put(TextureType.ANT_EAST, new Texture(Gdx.files.internal("Ant_East.PNG")));
        TEXTURE_MAP.put(TextureType.ANT_SOUTH, new Texture(Gdx.files.internal("Ant_South.PNG")));
        TEXTURE_MAP.put(TextureType.ANT_WEST, new Texture(Gdx.files.internal("Ant_West.PNG")));

    }

}
