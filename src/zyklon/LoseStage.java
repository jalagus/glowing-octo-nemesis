/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zyklon;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
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
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        background.draw(0, 0);
        
        scoreFont.drawString(200, 270, "You got " + Main.points + " points!");
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        scoreFont.loadGlyphs();
        
        Input input = gc.getInput();
        
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            sbg.enterState(Main.MAINMENUSTATE);
        }
    }
    
}
