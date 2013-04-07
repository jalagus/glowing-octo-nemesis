package zyklon;

import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class HighScoreState extends BasicGameState {

    Image background;
    Image table;

    UnicodeFont hsFont;
    UnicodeFont scoreFont;

    int stateId = -1;

    public HighScoreState(int stateId) {
        this.stateId = stateId;
    }

    @Override
    public int getID() {
        return this.stateId;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = new Image("assets/graphics/taustakuva_mustaharmaa.png");
        table = new Image("assets/graphics/taustakuva_highscore.png");

        hsFont = new UnicodeFont("assets/menu.ttf", 80, false, false);
        scoreFont = new UnicodeFont("assets/menu.ttf", 40, false, false);

        hsFont.getEffects().add(new ColorEffect(java.awt.Color.white));
        scoreFont.getEffects().add(new ColorEffect(java.awt.Color.white));
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        background.draw(0, 0);
        table.draw(0, 0);

        hsFont.drawString(20, 20, "High Scores");

        HighScore[] scores = new HighScoreLoader().highscores();

        for (int i = 0; i < scores.length; i++) {
            scoreFont.drawString(100, 100 + i * 40, "#" + (i + 1));
            scoreFont.drawString(170, 100 + i * 40, scores[i].name);
            scoreFont.drawString(340, 100 + i * 40, scores[i].score + " pts");
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        hsFont.loadGlyphs();
        scoreFont.loadGlyphs();

        Input input = gc.getInput();

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            sbg.enterState(Main.MAINMENUSTATE);
        }
    }

}
