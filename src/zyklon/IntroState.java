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


public class IntroState extends BasicGameState {

    Image background;

    int stateId = -1;

    private int position = 738;
    
    private int elapsedTime = 0;
    
    public IntroState(int stateId) {
        this.stateId = stateId;
    }

    @Override
    public int getID() {
        return this.stateId;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = new Image("assets/intro_teksti_kuva.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        background.draw(200, position);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        elapsedTime += delta;
        
        if (elapsedTime > 50) {
            position -= 3;
            elapsedTime = 0;
        }
        
        if (position < -768 || gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
            sbg.enterState(Main.GAMESTATE);
        }
    }

}