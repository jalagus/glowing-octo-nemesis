package zyklon;

import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuState extends BasicGameState {

    Image background;
    Image pi;
    Image pieSlice;

    UnicodeFont menuFont;
    UnicodeFont headerFont;
    UnicodeFont creditFont;

    Sound menuChangeSound;
    Sound menuMusic;

    int activeOption = 0;

    int stateID = -1;
    
    public boolean gameRunning = false;

    int menuPositionX = 150;
    int menuPositionY = 220;

    public MainMenuState(int stateID) {
        this.stateID = stateID;
    }

    @Override
    public int getID() {
        return stateID;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = new Image("assets/graphics/taustakuva_mustaharmaa.png");
        pi = new Image("assets/pi.png");
        pieSlice = new Image("assets/graphics/taustakuva_piirakka.png");

        menuFont = new UnicodeFont("assets/menu.ttf", 60, false, false);
        headerFont = new UnicodeFont("assets/menu.ttf", 120, false, false);
        creditFont = new UnicodeFont("assets/menu.ttf", 30, false, false);

        menuFont.getEffects().add(new ColorEffect(java.awt.Color.white));
        headerFont.getEffects().add(new ColorEffect(java.awt.Color.white));
        creditFont.getEffects().add(new ColorEffect(java.awt.Color.white));

        menuChangeSound = new Sound("assets/audio/menuBlop.ogg");
        menuMusic = new Sound("assets/audio/menuMusic.ogg");
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        String gameName = "Zyklon";
        String gameBy = "Game by";

        String credits = "Glowing Octo Nemesis";

        String newGameText;
        if (!gameRunning) {
            newGameText = "New Game";
        }
        else {
            newGameText = "Continue Game";            
        }
        String highScoreText = "High Scores";
        String exitGameText = "Exit Game";

        g.setColor(Color.black);

        background.draw(0, 0);

        pieSlice.draw(380, 240);
        pi.draw(380, 0);

        headerFont.drawString(40, 40, gameName);

        switch (activeOption) {
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
        if (!menuMusic.playing()) {
            menuMusic.loop(1.0f, 0.1f);
        }

        menuFont.loadGlyphs();
        headerFont.loadGlyphs();
        creditFont.loadGlyphs();

        Input input = gc.getInput();

        if (input.isKeyPressed(Input.KEY_DOWN)) {
            menuChangeSound.play();

            activeOption++;
            if (activeOption > 2) {
                activeOption = 0;
            }
        }
        else if (input.isKeyPressed(Input.KEY_UP)) {
            menuChangeSound.play();

            activeOption--;
            if (activeOption < 0) {
                activeOption = 2;
            }
        }
        else if (input.isKeyPressed(Input.KEY_ENTER)) {

            switch (activeOption) {
                case 0:
                    menuMusic.stop();
                    
                    if (!gameRunning) {
                        sbg.enterState(Main.INTROSTATE);
                    }
                    else {
                        sbg.enterState(Main.GAMESTATE);
                    }
                    gameRunning = true;
                    
                    break;
                case 1:
                    sbg.enterState(Main.HIGHSCORESTATE);
                    break;
                case 2:
                    sbg.enterState(Main.CREDITSSTATE);
                    break;
            }
        }

    }

}