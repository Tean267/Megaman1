
package Useinterface;

import Effect.CacheDataLoader;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;


public class GameFrame  extends JFrame{
    
    public static final int SCREEN_HEIGHT = 600;
    public static final int SCREEN_WIDTH = 1000;
    GamePanel gamePanel;
    
    public GameFrame(){
        try {
            CacheDataLoader.getInstance().LoadData();
        } catch (IOException ex) {
            Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        Toolkit toolkit = this.getToolkit(); //  lấy size hiện tiện của màn hình laptop
        Dimension dimension =  toolkit.getScreenSize();
        this.setBounds((dimension.width-SCREEN_WIDTH)/2 ,(dimension.height-SCREEN_HEIGHT)/2, SCREEN_WIDTH, SCREEN_HEIGHT); // set vi tri diem dau cua jframe va chieu dai chieu rong
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamePanel = new GamePanel();
        add(gamePanel); // them gamePanel vao  gameFrame
        this.addKeyListener(gamePanel);
    }
    public void startGame(){ // method de cho run thread trong gamePanel
        gamePanel.startGame();
    }
    public static void main(String[] args) {
        GameFrame gameframe = new GameFrame();
        gameframe.setVisible(true);
        gameframe.startGame();
    }
}