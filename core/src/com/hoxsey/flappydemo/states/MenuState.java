package com.hoxsey.flappydemo.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.hoxsey.flappydemo.FlappyDemo;

import sun.font.TrueTypeFont;

/**
 * Created by Hoxsey on 8/19/2016.
 */
public class MenuState extends State {
    private Texture background;
    private Texture playBtn;
    private Stage stage;
    private Label welcome;
    private Label title;
    private Table table;
    private BitmapFont font;


    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyDemo.WIDTH/2, FlappyDemo.HEIGHT/2);
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
        font = new BitmapFont(Gdx.files.internal("font/fb.fnt"),Gdx.files.internal("font/fb.png"),false);



        stage = new Stage(new ScreenViewport(), gsm.batch);
        title = new Label("FLAPPY\n DEMO", new Label.LabelStyle(font, Color.WHITE));
        title.setFontScale(4);
        welcome = new Label("WELCOME!", new Label.LabelStyle(font, Color.WHITE));
        welcome.setFontScale(5);
        table = new Table();
        tableSetup();

    }

    public void tableSetup()    {
        table.setFillParent(true);
        table.add(title).center().top().padTop(20).expand();
        table.row();
        table.add(welcome).center().bottom().padBottom(200).expand();
        stage.addActor(table);
    }

    @Override
    public void handleInput() {
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
            sb.draw(background, 0, 0);
            sb.draw(playBtn, (cam.position.x)- (playBtn.getWidth()/2), (cam.position.y));
        sb.end();
        stage.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        System.out.println("Menu State Disposed");
    }
}
