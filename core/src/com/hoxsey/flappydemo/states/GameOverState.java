package com.hoxsey.flappydemo.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.hoxsey.flappydemo.FlappyDemo;

/**
 * Created by Hoxsey on 8/19/2016.
 */
public class GameOverState extends State {
    private Label hsTitle;
    private Texture bg;
    private Texture gameover;
    private Label highscore;
    private Preferences savedData;
    private Stage gameoverStage;
    private Table table;

    protected GameOverState(GameStateManager gsm, boolean isNewhighscore) {
        super(gsm);
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        bg = new Texture("bg.png");
        gameover = new Texture("gameover.png");
        gameoverStage = new Stage(new ScreenViewport(), gsm.batch);
        savedData = Gdx.app.getPreferences("highscore");

        if(isNewhighscore)
            hsTitle = new Label("NEW HIGH SCORE:", new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        else
            hsTitle = new Label("HIGH SCORE:", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        highscore = new Label(String.format("%03d",savedData.getInteger("hs",0)), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        highscore.setFontScale(3);
        hsTitle.setFontScale(3);

        table = new Table();
        table.setFillParent(true);
        table.add(hsTitle);
        table.row();
        table.add(highscore).center().top().pad(10).expand();

        gameoverStage.addActor(table);

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
        gameoverStage.draw();
    }

    @Override
    public void dispose() {
        bg.dispose();
        gameover.dispose();
        System.out.println("Game Over State Disposed");
    }
}
