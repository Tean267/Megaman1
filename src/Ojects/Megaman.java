/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ojects;

import Effect.Animation;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Tean
 */
public class Megaman  {
//    
    private float posX; //vi tri của mega man
    private float posY;
    
    private float width; // chieu rong của mega man
    private float height;// chieu cao
    private float mass;// khối lượng (tính đổ nhảy )

    private float speedX; // vận tốc theo phương x
    private float speedY;
    
    public static int DIR_LEFT;// hướng đi sang trái
    public static int DIR_RIGHT; // hướng đi sang phải
    private int direction;
    GameWorld gameWorld;
//    public static final int RUNSPEED = 3;
//    
//    private Animation runForwardAnim, runBackAnim, runShootingForwarAnim, runShootingBackAnim;
//    private Animation idleForwardAnim, idleBackAnim, idleShootingForwardAnim, idleShootingBackAnim;
//    private Animation dickForwardAnim, dickBackAnim;
//    private Animation flyForwardAnim, flyBackAnim, flyShootingForwardAnim, flyShootingBackAnim;
//    private Animation landingForwardAnim, landingBackAnim;
//    
//    private Animation climWallForward, climWallBack;
//    
//    private long lastShootingTime;
//    private boolean isShooting = false;
//    
//    private AudioClip hurtingSound;
//    private AudioClip shooting1;
//    
    
    public Megaman(float posX, float posY, float width, float height, float mass,GameWorld gameWorld) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.mass = mass;
        this.gameWorld = gameWorld;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }
    
    
    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
    
    public void draw(Graphics2D g2){
        g2.setColor(Color.red);
        g2.fillRect((int) (getPosX() - getWidth()/2), (int) (getPosY() - getHeight()/2), (int)width,(int) height); // method này nhận kiểu int nên phải ép kiểu
    }
    
   public void update(){
//        setPosX(getPosX() + speedX);
//        Rectangle rectLand = gameWorld.physicalMap.haveCollisionWithLand(getBoundForCollisionWithmap());
//        if(rectLand!=null) {
//          setPosY(rectLand.y - getHeight()/2);setSpeedY(-3);
//        } 
//        else{
//            setPosY(getPosY() + speedY);
//            setSpeedY(getSpeedY()+mass);
//        }
          setPosX(getPosX() + speedX);
          Rectangle futureRect = getBoundForCollisionWithMap();
          futureRect.y += getSpeedY();
          Rectangle rectLand = gameWorld.physicalMap.haveCollisionWithLand(futureRect);
          
          if(rectLand!=null) setPosY(rectLand.y - getHeight()/2);
          else{
              setPosY(getPosY() +speedY);
              setSpeedY(getSpeedY() + mass);
          }
    } 
    
   public Rectangle getBoundForCollisionWithMap(){ // ractangle tra ve doi tuong hinh chu nhat
       
       Rectangle bound = new Rectangle();
       bound.x = (int) (getPosX() - getWidth()/2); // để làm cho hình chữ nhật sẽ bao quanh megaman
       bound.y = (int) (getPosY() - getHeight()/2); // lấy tọa độ megaman làm trọng tâm hcn
       bound.width = (int) getWidth();
       bound.height = (int) getHeight();
       return bound;
   }
   
   
}
