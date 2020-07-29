package com.physics2d.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.physics2d.Physics2D;

public class PlayState extends State {
    private Texture background;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("black-background-1600x960-r.jpg");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, Physics2D.WIDTH, Physics2D.HEIGHT);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
