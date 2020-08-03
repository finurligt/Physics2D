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

        Buffer buffer = new Buffer();
        advection(buffer);
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

    private void advection(Buffer buffer) {


        for (int x = 0; x < X_SIZE; x++) {
            for (int y = 0; y < X_SIZE; y++) {
                pushDensity(x, y, buffer);
            }
        }
    }

    private Buffer pushDensity(int sourceX, int sourceY, Buffer buffer) {
        float newX = sourceX + velX[sourceX][sourceY];
        float newY = sourceY + velY[sourceX][sourceY];

        float right,up;

        //edge cases
        if (newX < 1) {
            newX = 1;
            right = 0;
        } else if (newX > X_SIZE - 2) {
            newX = X_SIZE - 2;
            right = 1;
        } else {
            right = newX % 1;
        }

        if (newY < 1) {
            newY = 1;
            up = 0;
        } else if (newY > Y_SIZE - 2) {
            newY = Y_SIZE - 2;
            up = 1;
        } else {
            up = newY % 1;
        }

        int destX, destY;
        float value;


        for (int x = 0; x<2; x++) {
            for (int y = 0; y<2; y++) {
                float horizontalFlow = Math.abs(x - 1 + (right));
                float verticalFlow = Math.abs(y - 1 + (up));
                destX = (int)Math.floor(newX+x);
                destY = (int)Math.floor(newY+y);
                value = (horizontalFlow)*(verticalFlow)*density[sourceX][sourceY];
                buffer.density[destX][destY] += value;
                buffer.velXxDens[destX][destY] += value*velX[sourceX][sourceY];
                buffer.velYxDens[destX][destY] += value*velY[sourceX][sourceY];
            }
        }
        return buffer;
    }
}
