/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zyklon;

import java.io.BufferedWriter;
import java.io.FileWriter;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class WinStage extends BasicGameState {
    
    int stateId = -1;
    
    Image background;
    
    UnicodeFont scoreFont;
    
    private boolean winplayed = false;
    
    Sound win;
    
    public WinStage(int stateId) {
        this.stateId = stateId;
    }

    @Override
    public int getID() {
        return this.stateId;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = new Image("assets/voittokuva.png");
        
        scoreFont = new UnicodeFont("assets/menu.ttf", 60, false, false);
        scoreFont.getEffects().add(new ColorEffect(java.awt.Color.white)); 
        
        win = new Sound("assets/audio/victoryFanfare.ogg");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        background.draw(0, 0);
        
        scoreFont.drawString(200, 240, "You got " + Main.points + " points!");

        if (!winplayed) {
            winplayed = true;
            win.play();
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        scoreFont.loadGlyphs();        
        
        Input input = gc.getInput();
        
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            try {
                FileWriter fstream = new FileWriter("highscores.txt", true);
                BufferedWriter out = new BufferedWriter(fstream);
                out.write("temp;" + Main.points + "\n");

                out.close();
            }
            catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
          
            sbg.enterState(Main.CREDITSSTATE);
        }
    }
    
}
