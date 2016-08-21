package com.hoxsey.flappydemo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Hoxsey on 8/20/2016.
 */
public class Hud {
    public Stage stage;
    private Viewport viewport;

    private Integer score;
    private Integer highscore;
    private Label scoreLabel;
    private Label highscoreLabel;

    private Table table;


    public Hud(SpriteBatch sb)    {
        score = 0;
        highscore = 0;

        viewport = new FitViewport(FlappyDemo.WIDTH, FlappyDemo.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport,sb);

        scoreLabel = new Label(String.format("%03d",score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        highscoreLabel = new Label(String.format("%03d",highscore), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table = new Table();
        table.add(scoreLabel).top().center();
        table.add(highscoreLabel).bottom().right();
        stage.addActor(table);
    }
}
