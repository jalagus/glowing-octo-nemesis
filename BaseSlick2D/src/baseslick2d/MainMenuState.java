package baseslick2d;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuState extends BasicGameState {
  
    Image background;
    Image pi;
    
    UnicodeFont menuFont;
    UnicodeFont headerFont;
    UnicodeFont creditFont;
    
    int activeOption = 0;
    
    int stateID = -1;
    
    int menuPositionX = 150;
    int menuPositionY = 220;
  
    MainMenuState( int stateID ) 
    {
       this.stateID = stateID;
    }
  
    @Override
    public int getID() {
        return stateID;
    }
  
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = new Image("data/bg.png");
        pi = new Image("data/pi.png");
        
        menuFont = new UnicodeFont("data/menu.ttf", 60, false, false);  
        headerFont = new UnicodeFont("data/menu.ttf", 120, false, false);
        creditFont = new UnicodeFont("data/menu.ttf", 30, false, false);
        
        menuFont.getEffects().add(new ColorEffect(java.awt.Color.white));
        headerFont.getEffects().add(new ColorEffect(java.awt.Color.white));
        creditFont.getEffects().add(new ColorEffect(java.awt.Color.white));
    }
  
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        String gameName = "Zyklon";
        String gameBy = "Game by";
        
        String credits = "Glowing Octo Nemesis";
        
        String newGameText = "New Game";
        String exitGameText = "Exit Game";
        String highScoreText = "High Scores";
        
        g.setColor(Color.black);

        background.draw(0,0);
        
        pi.draw(380, 0);
        
        headerFont.drawString(40, 40, gameName);
        
        switch(activeOption) {
            case 0:
                menuFont.drawString(menuPositionX - 40, menuPositionY, ">");
                break;
            case 1:
                menuFont.drawString(menuPositionX - 40, menuPositionY + 60, ">");
                break;
            case 2:
                menuFont.drawString(menuPositionX - 40, menuPositionY + 160, ">");
                break;
        }
        
        menuFont.drawString(menuPositionX, menuPositionY, newGameText);
        menuFont.drawString(menuPositionX, menuPositionY + 60, highScoreText);
        menuFont.drawString(menuPositionX, menuPositionY + 160, exitGameText);

        creditFont.drawString(893, 690, gameBy);
        creditFont.drawString(720, 720, credits);
    }
  
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        menuFont.loadGlyphs();
        headerFont.loadGlyphs();
        creditFont.loadGlyphs();
        
        Input input = gc.getInput();    
        
        if (input.isKeyPressed(Input.KEY_DOWN)) {
            activeOption++;
            if (activeOption > 2) {
                activeOption = 0;
            }
        }
        else if (input.isKeyPressed(Input.KEY_UP)) {
            activeOption--;
            if (activeOption < 0) {
                activeOption = 2;
            }
        }
        else if (input.isKeyPressed(Input.KEY_ENTER)) {
            
            switch (activeOption) {
                case 0:
                    break;
                case 1:
                    sbg.enterState(BaseSlick2D.HIGHSCORESTATE);
                    break;
                case 2:
                    gc.exit();
                    break;
            }
        }
        
    }
  
}