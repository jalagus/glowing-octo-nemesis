/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zyklon;

import maingame.Main;
import maingame.Player;
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


public class PokemonFightState extends BasicGameState {

    Image background;
    Image hud;
    
    Image enemyFight;
    
    UnicodeFont menuFont;
    UnicodeFont statsFont;
    
    Player player;
    Enemy enemy;
    
    int stateId = -1;
    
    private int menuOption = 0;
    
    public PokemonFightState(int stateId) {
        this.stateId = stateId;
    }
    
    public void setEnemyAndPlayer(Player player, Enemy enemy) {
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
        enemyFight = new Image(enemy.fightGraphic);
        
        menuFont = new UnicodeFont("assets/menu.ttf", 30, false, false);  
        statsFont = new UnicodeFont("assets/menu.ttf", 30, false, false);  
        
        menuFont.getEffects().add(new ColorEffect(java.awt.Color.white));         
        statsFont.getEffects().add(new ColorEffect(java.awt.Color.white));        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        background.draw(0,0);
        hud.draw(0, 0);
        enemyFight.draw(0,0);
        
        statsFont.drawString(700, 460, player.name);
        statsFont.drawString(700, 490, "HP");
        statsFont.drawString(700, 520, player.hp + " / " + player.maxHp);
        
        statsFont.drawString(50, 50, enemy.name);
        statsFont.drawString(50, 80, "HP");
        statsFont.drawString(50, 110, enemy.hp + " / " + enemy.maxHp);
        
        // Enemy
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
        menuFont.drawString(190, 700, "Super Attack"); 
        menuFont.drawString(420, 700, "Hyper Attack"); 
        menuFont.drawString(650, 700, "Super Hyper Attack"); 
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        menuFont.loadGlyphs();
        statsFont.loadGlyphs();
        
        Input input = gc.getInput();
        
        if (input.isKeyPressed(Input.KEY_A)) {
            menuOption--;
            
            if(menuOption < 0) {
                menuOption = 3;
            }
        }
        else if (input.isKeyPressed(Input.KEY_D)) {
            menuOption++;
            
            if(menuOption > 3) {
                menuOption = 0;
            }
        }
        else if (input.isKeyPressed(Input.KEY_ENTER)) {
            enemy.hp = enemy.hp - (10 * menuOption);
            player.hp = player.hp - 10;
            
            if (enemy.hp < 1) {
                sbg.enterState(Main.GAMESTATE);
            }
            
            if (player.hp < 1) {
                sbg.enterState(Main.GAMESTATE);
            }
            
        }

        
    }

}
