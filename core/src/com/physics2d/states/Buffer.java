package com.physics2d.states;

public class Buffer {
    public float[][] velXxDens,velYxDens,density;

    public Buffer(int xSize, int ySize) {
        velXxDens = new float[xSize][ySize];
        velYxDens = new float[xSize][ySize];
        density = new float[xSize][ySize];
    }
}
