package zyklon;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {

    public static final int MAINMENUSTATE = 0;
    public static final int HIGHSCORESTATE = 1;
    public static final int GAMESTATE = 2;
    public static final int FIGHTSTATE = 3;

    public Main() {
        super("Zyklon π");

        this.addState(new MainMenuState(MAINMENUSTATE));
        this.addState(new HighScoreState(HIGHSCORESTATE));
        this.addState(new GameBaseState(GAMESTATE));
        this.addState(new PokemonFightState(FIGHTSTATE));


        this.enterState(GAMESTATE);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Main());

        app.setDisplayMode(1024, 768, false);
        app.start();
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {

        this.getState(MAINMENUSTATE).init(gameContainer, this);
    }

}
