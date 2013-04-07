package maingame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import zyklon.Enemy;
import zyklon.HighScoreState;
import zyklon.MainMenuState;
import zyklon.PokemonFightState;

public class Main extends StateBasedGame {
  
    public static final int MAINMENUSTATE = 0;
    public static final int HIGHSCORESTATE = 1;
    public static final int GAMESTATE = 2;
    public static final int FIGHTSTATE = 3;
  
    public Main()
    {
        super("Zyklon Pie");
  
        this.addState(new MainMenuState(MAINMENUSTATE));
        this.addState(new HighScoreState(HIGHSCORESTATE));
        this.addState(new GameBaseState(GAMESTATE));
        this.addState(new PokemonFightState(FIGHTSTATE));
        
        PokemonFightState temp = (PokemonFightState) this.getState(FIGHTSTATE);
        
        Enemy en = new Enemy();
        
        en.hp = 100;
        en.maxHp = 100;
        en.name = "Karhu";
        
        temp.setEnemyAndPlayer(new Player(0,0), en);
        
        this.enterState(FIGHTSTATE);
    }
  
    public static void main(String[] args) throws SlickException
    {
         AppGameContainer app = new AppGameContainer(new Main());
  
         app.setDisplayMode(1024, 768, true);
         app.start();
    }
  
    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
  
        this.getState(MAINMENUSTATE).init(gameContainer, this);  
    }
  
}
