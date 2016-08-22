package com.hoxsey.flappydemo.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Hoxsey on 8/19/2016.
 */
public class Tube {
    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING = 120;
    public static final int TUBE_WIDTH = 52;
    private Vector2 posClear;
    private  Texture clearPoint;
    public Rectangle boundsTop, boundsBot, boundsScore;
    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBotTube;
    private Random rand;
    private boolean tubecleared;

    public Tube(float x)   {
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        clearPoint = new Texture("bird.png");
        rand = new Random();

        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION)+ TUBE_GAP + LOWEST_OPENING);
        posBotTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());

        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBot = new Rectangle(posBotTube.x, posBotTube.y, bottomTube.getWidth(), bottomTube.getHeight());

        boundsScore = new Rectangle(boundsBot.getX()+bottomTube.getWidth()/2,boundsBot.getY()+boundsBot.getHeight(),bottomTube.getWidth()/4,LOWEST_OPENING);

        posClear = new Vector2(boundsScore.x, boundsScore.y);
        tubecleared = false;
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getClearPoint() {
        return clearPoint;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    public Vector2 getPosClear() {
        return posClear;
    }

    public Rectangle getBoundsScore() {
        return boundsScore;
    }

    public void reposition(float x)   {
        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION)+ TUBE_GAP + LOWEST_OPENING);
        posBotTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());
        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBot.setPosition(posBotTube.x, posBotTube.y);
        boundsScore.setPosition(boundsBot.getX()+bottomTube.getWidth()/2,boundsBot.getY()+boundsBot.getHeight());
        tubecleared = false;
    }

    public boolean collides(Rectangle player)   {
        return player.overlaps(boundsTop) ||player.overlaps(boundsBot);
    }

    public boolean clearedTheTube(Rectangle player) {
        if(player.overlaps(boundsScore) && !tubecleared)    {
            tubecleared = true;
            return true;
        }else
            return false;
    }

    public void dispose()   {
        topTube.dispose();
        bottomTube.dispose();
    }
}
