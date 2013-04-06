package baseslick2d;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class HighScoreState extends BasicGameState {

    Image background;
    
    UnicodeFont hsFont;
    UnicodeFont scoreFont;
    
    int stateId = -1;
    
    HighScoreState(int stateId) {
        this.stateId = stateId;
    }
    
    @Override
    public int getID() {
        return this.stateId;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = new Image("data/bg.png");
        
        hsFont = new UnicodeFont("data/menu.ttf", 80, false, false);  
        scoreFont = new UnicodeFont("data/menu.ttf", 40, false, false);  
        
        hsFont.getEffects().add(new ColorEffect(java.awt.Color.white));         
        scoreFont.getEffects().add(new ColorEffect(java.awt.Color.white));        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        background.draw(0,0);
        
        hsFont.drawString(20, 20, "High Scores");
        
        for (int i = 1; i < 6; i++) {
            scoreFont.drawString(100, 100 + i * 40, "#" + i);
            scoreFont.drawString(170, 100 + i * 40, "Testi");            
            scoreFont.drawString(400, 100 + i * 40, "1000 pts");            
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        hsFont.loadGlyphs();
        scoreFont.loadGlyphs();
        
        Input input = gc.getInput();
        
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            sbg.enterState(BaseSlick2D.MAINMENUSTATE);
        }
    }

}
