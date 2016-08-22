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
    private Label newTitle;
    private Label hsTitle;
    private Texture bg;
    private Label gameover;
    private Label highscore;
    private Preferences savedData;
    private Stage gameoverStage;
    private Table table;
    private BitmapFont font;

    protected GameOverState(GameStateManager gsm, boolean isNewhighscore) {
        super(gsm);

        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        bg = new Texture("bg.png");


        gameoverStage = new Stage(new ScreenViewport(), gsm.batch);

        savedData = Gdx.app.getPreferences("highscore");

        font = new BitmapFont(Gdx.files.internal("font/fb.fnt"),Gdx.files.internal("font/fb.png"),false);

        newTitle = new Label("NEW", new Label.LabelStyle(font, Color.WHITE));
        hsTitle = new Label("HIGH SCORE:", new Label.LabelStyle(font, Color.WHITE));
        gameover = new Label("GAMEOVER", new Label.LabelStyle(font, Color.RED));
        highscore = new Label(String.format("%03d",savedData.getInteger("hs",0)), new Label.LabelStyle(font, Color.WHITE));

        gameover.setFontScale(5);
        highscore.setFontScale(4);
        hsTitle.setFontScale(3);
        newTitle.setFontScale(4);

        table = new Table();
        table.setFillParent(true);
        table.padTop(170);
        if(isNewhighscore)  {
            table.add(newTitle).row();
        }
        table.add(hsTitle).padTop(10);
        table.row();
        table.add(highscore).center().top().expand().padTop(10).row();
        table.add(gameover).center().padBottom(200);

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
        sb.end();
        gameoverStage.draw();
    }

    @Override
    public void dispose() {
        bg.dispose();
        System.out.println("Game Over State Disposed");
    }
}
