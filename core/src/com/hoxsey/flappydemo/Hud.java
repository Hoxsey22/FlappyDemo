package com.hoxsey.flappydemo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Hoxsey on 8/20/2016.
 */
public class Hud {
    private BitmapFont font;
    public Stage stage;
    private Viewport viewport;

    private Integer score;
    private Integer highscore;
    private Label scoreLabel;
    private Label highscoreLabel;
    private Label highscoreTitleLabel;

    private Table tableCurrentScore;
    private Table tableHighScore;



    public Hud(SpriteBatch sb)    {
        score = 0;
        highscore = 0;
        font = new BitmapFont(Gdx.files.internal("font/fb.fnt"),Gdx.files.internal("font/fb.png"),false);

        stage = new Stage(new ScreenViewport(),sb);

        scoreLabel = new Label(String.format("%03d",score), new Label.LabelStyle(font, Color.WHITE));
        scoreLabel.setFontScale(4);
        highscoreLabel = new Label(String.format("%03d",highscore), new Label.LabelStyle(font, Color.WHITE));
        highscoreTitleLabel = new Label("High Score:", new Label.LabelStyle(font, Color.WHITE));
        highscoreLabel.setFontScale(2);
        highscoreTitleLabel.setFontScale(2);

        tableCurrentScore = new Table();
        tableCurrentScore.setFillParent(true);
        tableCurrentScore.add(scoreLabel).center().top().padTop(10).expand();
        stage.addActor(tableCurrentScore);

        tableHighScore = new Table();
        tableHighScore.setFillParent(true);
        tableHighScore.add(highscoreTitleLabel).center().bottom();
        tableHighScore.add(highscoreLabel).bottom().expandY();
        stage.addActor(tableHighScore);

    }

    public void addPoint()  {
        score++;
        scoreLabel.setText(String.format("%03d", score));
    }

    public void resetScore()    {
        score = 0;
    }
    public Integer getHighscore()   {
        if(score > highscore)
            return score;
        else
            return highscore;
    }

    public void changeHighScore(Integer hs)  {
        highscore = hs;
        highscoreLabel.setText(String.format("%03d", highscore));
    }
}
