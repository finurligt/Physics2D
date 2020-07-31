package com.physics2d.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.physics2d.Physics2D;

public class PlayState extends State {
    private final int X_SIZE = 100;
    private final int Y_SIZE = 100;

    private Texture background;
    private float[][] velX;
    private float[][] velY;
    private float[][] density;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("black-background-1600x960-r.jpg");

        velX = new float[X_SIZE][Y_SIZE];
        velY = new float[X_SIZE][Y_SIZE];
        density = new float[X_SIZE][Y_SIZE];
    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(float dt) {
        handleInput();

        float[][] densityAddBuffer = new float[X_SIZE][Y_SIZE];
        float[][] densityRemoveBuffer = new float[X_SIZE][Y_SIZE];
        advection(densityAddBuffer);
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

    private void advection(float[][] densityAddBuffer) {


        for (int x = 0; x < X_SIZE; x++) {
            for (int y = 0; y < X_SIZE; y++) {
                pushDensity(x, y, densityAddBuffer);
            }
        }
    }

    private Buffer pushDensity(int x, int y, Buffer buffer) {
        float newX = x + velX[x][y];
        float newY = y + velY[x][y];

        float right = (newX % 1);
        float up = newY % 1;

        //TODO: loop this shit
        int destX, destY;
        float value;

        //down left
        destX = (int)Math.floor(newX);
        destY = (int)Math.floor(newY);
        value = (1-right)*(1-up)*density[x][y];
        buffer.density[destX][destY] += value;
        buffer.velXxDens[destX][destY] += value*velX[x][y];
        buffer.velYxDens[destX][destY] += value*velY[x][y];

        //down right
        destX = (int)Math.floor(newX+1);
        destY = (int)Math.floor(newY);
        value = (right)*(1-up)*density[x][y];
        buffer.density[destX][destY] += value;
        buffer.velXxDens[destX][destY] += value*velX[x][y];
        buffer.velYxDens[destX][destY] += value*velY[x][y];

        //up left
        destX = (int)Math.floor(newX);
        destY = (int)Math.floor(newY+1);
        value = (1-right)*(up)*density[x][y];
        buffer.density[destX][destY] += value;
        buffer.velXxDens[destX][destY] += value*velX[x][y];
        buffer.velYxDens[destX][destY] += value*velY[x][y];

        //up right
        destX = (int)Math.floor(newX+1);
        destY = (int)Math.floor(newY+1);
        value = (right)*(up)*density[x][y];
        buffer.density[destX][destY] += value;
        buffer.velXxDens[destX][destY] += value*velX[x][y];
        buffer.velYxDens[destX][destY] += value*velY[x][y];
    }
}
