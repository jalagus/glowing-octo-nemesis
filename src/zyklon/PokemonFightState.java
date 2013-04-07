/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zyklon;

import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;


public class PokemonFightState extends BasicGameState {

    Image background;
    Image hud;
    Image enemyFight;

    UnicodeFont menuFont;
    UnicodeFont statsFont;

    Player player;
    Sound fightMusic;
    GraphicEntity enemy;

    int stateId = -1;

    private int menuOption = 0;

    public PokemonFightState(int stateId) {
        this.stateId = stateId;
    }

    public void setEnemyAndPlayer(Player player, GraphicEntity enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    @Override
    public int getID() {
        return this.stateId;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = new Image("assets/graphics/taustakuva_mustaharmaa.png");
        hud = new Image("assets/graphics/taistelureunat.png");

        fightMusic = new Sound("assets/audio/fightMusic.ogg");

        menuFont = new UnicodeFont("assets/menu.ttf", 30, false, false);
        statsFont = new UnicodeFont("assets/menu.ttf", 30, false, false);

        menuFont.getEffects().add(new ColorEffect(java.awt.Color.white));
        statsFont.getEffects().add(new ColorEffect(java.awt.Color.white));
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        background.draw(0, 0);
        hud.draw(0, 0);
        enemy.fightSprite.draw(0, 0);

        statsFont.drawString(700, 460, player.name);
        statsFont.drawString(700, 490, "HP");
        statsFont.drawString(700, 520, player.hp + " / " + player.maxHp);

        statsFont.drawString(50, 50, enemy.name);
        statsFont.drawString(50, 80, "HP");
        statsFont.drawString(50, 110, enemy.hp + " / " + enemy.maxHp);

        // GraphicEntity
        grphcs.setColor(Color.yellow);
        grphcs.fillRect(100, 85, (int) (150 * ((1.0 * enemy.hp) / enemy.maxHp)), 20);

        // Mummo
        grphcs.setColor(Color.yellow);
        grphcs.fillRect(750, 495, (int) (150 * ((1.0 * player.hp) / player.maxHp)), 20);


        grphcs.setColor(Color.black);
        grphcs.drawRect(100, 85, 150, 20);
        grphcs.drawRect(750, 495, 150, 20);

        switch (menuOption) {
            case 0:
                menuFont.drawString(30, 700, ">");
                break;
            case 1:
                menuFont.drawString(170, 700, ">");
                break;
            case 2:
                menuFont.drawString(400, 700, ">");
                break;
            case 3:
                menuFont.drawString(630, 700, ">");
                break;
        }

        menuFont.drawString(50, 700, "Attack");
        menuFont.drawString(190, 700, "Super Atk " + (player.stamina / 20));
        menuFont.drawString(420, 700, "Hyper Atk " + (player.stamina / 40));
        menuFont.drawString(650, 700, "Super Hyper Atk " + (player.stamina / 60));

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if (!fightMusic.playing()) {
            fightMusic.loop(1.0f, 0.2f);
        }

        menuFont.loadGlyphs();
        statsFont.loadGlyphs();

        Input input = gc.getInput();

        if (input.isKeyPressed(Input.KEY_A)) {
            menuOption--;

            if (menuOption < 0) {
                menuOption = 3;
            }
        }
        else if (input.isKeyPressed(Input.KEY_D)) {
            menuOption++;

            if (menuOption > 3) {
                menuOption = 0;
            }
        }
        else if (input.isKeyPressed(Input.KEY_ENTER)) {
            if ((player.stamina - (20 * menuOption)) >= 0) {

                enemy.hp = enemy.hp - ((10 * menuOption) + 10);
                player.stamina -= (20 * menuOption);

                player.hp = player.hp - 10;

                if (enemy.hp < 1) {
                    enemy.hp = 0;
                    fightMusic.stop();
                    sbg.enterState(Main.GAMESTATE, new EmptyTransition(), new FadeInTransition());
                }

                if (player.hp < 1) {
                    player.hp = 0;
                    fightMusic.stop();
<<<<<<< Updated upstream
                    sbg.enterState(Main.GAMESTATE, new EmptyTransition(), new FadeInTransition());
=======
                    sbg.enterState(Main.LOSESTAGE);
>>>>>>> Stashed changes
                }
            }

        }


    }

}
