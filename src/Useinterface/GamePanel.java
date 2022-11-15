/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Useinterface;

import Effect.Animation;
import Effect.CacheDataLoader;
import Effect.FrameImage;
import Ojects.GameWorld;
import Ojects.Megaman;
import Ojects.PhysicalMap;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Tean
 */
public class GamePanel extends JPanel implements Runnable, KeyListener{
    
    private Thread thread;
    private boolean isRunning;
    InputManger inputManger;
    
    FrameImage frame1, frame2, frame3;
//    Animation anim;
    BufferedImage bufImage;
    Graphics2D bufG2D;
    public GameWorld gameWorld;
    // Megaman megaman = new Megaman(300,300,100,100,0.1f);
     PhysicalMap physicalmap = new PhysicalMap(0,0,gameWorld);
    public GamePanel(){
        gameWorld = new GameWorld();
        inputManger = new InputManger(gameWorld);
        bufImage = new BufferedImage(GameFrame.SCREEN_WIDTH,GameFrame.SCREEN_HEIGHT,BufferedImage.TYPE_3BYTE_BGR);
     //   frame1 = CacheDataLoader.getInstance().getFrameImage("idleshoot1");
//        anim = CacheDataLoader.getInstance().getAnimation("flyingup");
        //Megaman megaman = new Megaman(300,400,200,200,0.1f);
    }
    @Override
    public void paint(Graphics g){ // method of JPanel -- tu dong goi khi add JP vao Frame
//        g.setColor(Color.WHITE);
//        g.fillRect(0,0 ,GameFrame.SCREEN_WIDTH,GameFrame.SCREEN_HEIGHT );
         // Graphics2D g2 = (Graphics2D) g;
//      
      //  frame1.draw(g2, 200, 200);
//        anim.draw(100, 130, g2);
            g.drawImage(bufImage, 0, 0, this);
       
    }
    public void UpdateGame(){
       // megaman.update();
       gameWorld.Update();
    }
    public void RenderGame(){ // vẽ lên 1 bức ảnh để vẽ lên pannel
        if(bufImage == null ){
            bufImage = new BufferedImage(GameFrame.SCREEN_WIDTH,GameFrame.SCREEN_HEIGHT,BufferedImage.TYPE_3BYTE_BGR);
            
        }
        if(bufImage !=null){
            bufG2D = (Graphics2D) bufImage.getGraphics();
        }
        if(bufG2D != null){
            bufG2D.setColor(Color.WHITE);
            bufG2D.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
            
            //frame1.draw(bufG2D, 200, 200);
            //megaman.draw(bufG2D);
         //  physicalmap.draw(bufG2D);
       //  bufG2D.setColor(Color.red);
         //   bufG2D.fillRect(40, 50, 100, 100);
           gameWorld.Render(bufG2D);
        }
    }
    public void startGame(){
        if(thread == null){
            isRunning = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    @Override
    public void run() {
       
        long FPS= 80; // số frame trong 1s
        long period = 1000*1000000/FPS; // chu kì (1s = 1ty nano s)
        long beginTime;
        long sleepTime;
        
        beginTime = System.nanoTime(); //System.nanoTime() là lấy time hiên tại của hệ thống máy mình
        while(isRunning){
         
            //update
            //Render
//            anim.Update(System.nanoTime());
           UpdateGame();
           /// repaint(); repaint trc khi rendergame se bi sai
            RenderGame();
            repaint();
            long deltaTime = System.nanoTime() - beginTime; // thời gian thực hiện bằng thời gian hiện tại trừ thời gian ban đầu
            sleepTime = period - deltaTime; // thời gian còn lại sau khi up và draw dc gắn vào thời gian sleep
            
            try {
                if(sleepTime>0){
                     Thread.sleep(sleepTime/1000000); // chia cho 1tr để đổi từ milis sang nanos vì method sleep chỉ nhận mlisi
                }
                else Thread.sleep(period/2000000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            beginTime = System.nanoTime();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { // method của KeyListener
        
    }

    @Override
    public void keyPressed(KeyEvent e) { // method của KeyListener , khi người dùng ấn phím thì sẽ gọi method này
      
        inputManger.processKeyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) { //method của KeyListener, khi người dùng thả phím sẽ gọi phương thức này
      
        inputManger.processKeyReleased(e.getKeyCode());
    }
    
    
}
