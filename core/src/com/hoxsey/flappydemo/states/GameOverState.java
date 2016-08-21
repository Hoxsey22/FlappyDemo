package com.hoxsey.flappydemo.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hoxsey.flappydemo.FlappyDemo;

/**
 * Created by Hoxsey on 8/19/2016.
 */
public class GameOverState extends State {
    private Texture bg;
    private Texture gameover;

    protected GameOverState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        bg = new Texture("bg.png");
        gameover = new Texture("gameover.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
            sb.draw(bg, 0, 0);
            sb.draw(gameover, (cam.position.x)- (gameover.getWidth()/2), (cam.position.y));
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        gameover.dispose();
        System.out.println("Game Over State Disposed");
    }
}
