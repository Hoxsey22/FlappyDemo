package com.hoxsey.flappydemo.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.hoxsey.flappydemo.FlappyDemo;
import com.hoxsey.flappydemo.Hud;
import com.hoxsey.flappydemo.sprites.Bird;
import com.hoxsey.flappydemo.sprites.Tube;

/**
 * Created by Hoxsey on 8/19/2016.
 */
public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -50 ;

    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private Hud hud;
    private Stage stage;
    private Integer highscore;
    private Preferences prefs;
    private boolean isNewHighScore;
    private Sound ding;

    private Array<Tube> tubes;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        //stage = new Stage(new ScreenViewport());
        bird = new Bird(25, 300);

        cam.setToOrtho(false, FlappyDemo.WIDTH/2, FlappyDemo.HEIGHT/2);

        bg = new Texture("bg.png");
        tubes = new Array<Tube>();

        for(int i = 1; i <= TUBE_COUNT; i++)   {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }

        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth/2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth/2) + ground.getWidth(), GROUND_Y_OFFSET);

        hud = new Hud(gsm.batch);

        prefs = Gdx.app.getPreferences("highscore");
        highscore = prefs.getInteger("hs", 0);
        hud.changeHighScore(highscore);

        isNewHighScore = false;

        ding = Gdx.audio.newSound(Gdx.files.internal("mariocoin.mp3"));

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())    {
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;
        for(int i = 0; i < tubes.size; i++)   {
            Tube tube = tubes.get(i);
            if(cam.position.x - (cam.viewportWidth/2) > tube.getPosTopTube().x + tube.getTopTube().getWidth())    {
                tube.reposition(tube.getPosTopTube().x + (Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT);
            }
            if(tube.clearedTheTube(bird.getBounds()))    {
                hud.addPoint();
                ding.play(0.5f);
            }

            if(tube.collides(bird.getBounds()))    {
                checkHighScore();
                gsm.set(new GameOverState(gsm, isNewHighScore));
            }
        }
        if(bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET)    {
            checkHighScore();
            gsm.set(new GameOverState(gsm, isNewHighScore));
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
            sb.draw(bg, cam.position.x - (cam.viewportWidth/2),0);
            sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
            for(Tube tube : tubes)   {
                sb.draw(tube.getTopTube(),tube.getPosTopTube().x, tube.getPosTopTube().y);
                sb.draw(tube.getBottomTube(),tube.getPosBotTube().x, tube.getPosBotTube().y);
            }
            sb.draw(ground, groundPos1.x, groundPos1.y);
            sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();
        hud.stage.draw();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        ground.dispose();
        ding.dispose();
        for(Tube tube : tubes)   {
            tube.dispose();
        }
        System.out.println("Play State Disposed");
    }

    public void updateGround()  {
        if((cam.position.x - cam.viewportWidth/2) > (groundPos1.x + ground.getWidth())) {
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if((cam.position.x - cam.viewportWidth/2) > (groundPos2.x + ground.getWidth())) {
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }

    public void checkHighScore() {
        Integer tempScore = hud.getHighscore();
        if(tempScore > highscore) {
            isNewHighScore = true;
            prefs.putInteger("hs", tempScore);
            prefs.flush();
        }

    }
}
