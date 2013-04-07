/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zyklon;

import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Rium
 */
public class LoseStage extends BasicGameState {
    
int stateId = -1;
    
    Image background;
    UnicodeFont scoreFont;
    Sound end;
    
    public LoseStage(int stateId) {
        this.stateId = stateId;
    }

    @Override
    public int getID() {
        return this.stateId;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = new Image("assets/haviokuva.png");
        
        scoreFont = new UnicodeFont("assets/menu.ttf", 60, false, false);
        scoreFont.getEffects().add(new ColorEffect(java.awt.Color.white));
        end = new Sound("assets/end.ogg");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        background.draw(0, 0);
        
        //scoreFont.drawString(200, 270, "You got " + Main.points + " points!");
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if (!end.playing()) end.loop();
        scoreFont.loadGlyphs();
        
        Input input = gc.getInput();
        
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            sbg.enterState(Main.MAINMENUSTATE);
        }
    }
    
}
