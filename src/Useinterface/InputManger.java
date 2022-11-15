/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Useinterface;

import Ojects.GameWorld;
import Ojects.Megaman;
import java.awt.event.KeyEvent;

/**
 *
 * @author Tean
 */
public class InputManger {
    
    private GamePanel gamePanel;
    private GameWorld gameWorld;
    public InputManger( GameWorld gameWorld){
        this.gameWorld = gameWorld;
    }
            
    public void processKeyPressed(int keyCode){
        switch (keyCode){
            
            case KeyEvent.VK_UP:
                System.out.println("u press UP");
                break;
            case KeyEvent.VK_DOWN:
                 System.out.println("u press DOWN");
                break;
            case KeyEvent.VK_LEFT:
                 gameWorld.megaman.setDirection(Megaman.DIR_LEFT);
                 gameWorld.megaman.setSpeedX(-5);
                break;
            case KeyEvent.VK_RIGHT:
               gameWorld.megaman.setDirection(Megaman.DIR_RIGHT);
                 gameWorld.megaman.setSpeedX(5);
                break;
            case KeyEvent.VK_ENTER:
                 System.out.println("u press ENTER");
                break;
            case KeyEvent.VK_SPACE:
                 gameWorld.megaman.setSpeedY(-3);
                gameWorld.megaman.setPosY(gameWorld.megaman.getPosY()-3);
                break;
            case KeyEvent.VK_A:
                
                break;
        }
    }
    public void processKeyReleased(int keyCode){
        switch (keyCode){
            
            case KeyEvent.VK_UP:
                System.out.println("u released UP");
                break;
            case KeyEvent.VK_DOWN:
                 System.out.println("u released DOWN");
                break;
            case KeyEvent.VK_LEFT:
                gameWorld.megaman.setSpeedX(0);
                break;
            case KeyEvent.VK_RIGHT:
                 gameWorld.megaman.setSpeedX(0);
                break;
            case KeyEvent.VK_ENTER:
                 System.out.println("u released ENTER");
                break;
            case KeyEvent.VK_SPACE:
                
                break;
            case KeyEvent.VK_A:
                
                break;
        }
    }
}
