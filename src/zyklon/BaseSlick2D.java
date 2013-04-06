package zyklon;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class BaseSlick2D extends StateBasedGame {
  
    public static final int MAINMENUSTATE = 0;
    public static final int HIGHSCORESTATE = 1;
    public static final int GAMESTATE = 2;
  
    public BaseSlick2D()
    {
        super("SlickBlocks");
  
        this.addState(new MainMenuState(MAINMENUSTATE));
        this.addState(new HighScoreState(HIGHSCORESTATE));
        
        this.enterState(MAINMENUSTATE);
    }
  
    public static void main(String[] args) throws SlickException
    {
         AppGameContainer app = new AppGameContainer(new BaseSlick2D());
  
         app.setDisplayMode(1024, 768, true);
         app.start();
    }
  
    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
  
        this.getState(MAINMENUSTATE).init(gameContainer, this);  
    }
  
}
