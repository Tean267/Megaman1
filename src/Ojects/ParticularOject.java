/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ojects;

import Effect.Animation;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Tean
 */
public abstract class ParticularOject extends GameOject {
    
    public static final int LEAGUE_TEAM =1; // đồng minh
    public static final int ENEMY_TEAM = 2; // kẻ địch
    
    public static final int LEFT_DIR = 0; // hướng 
    public static final int RIGHT_DIR = 1;
    
    public static final int ALIVE =0; // trạng thái sống
    public static final int BEHURT = 1; // trạng thái bị dính phải tấn công
    public static final int FEY = 2;// trạng thái săp chết
    public static final int DEATH = 3;// trang thai chết
    public static final int NOBEHURT = 4; // trạng thái k bị ảnh hưởng khi mới hồi sinh
    private int state = ALIVE; // xác định xem nhân vật ở trạng thái nào
    
    private float width;
    private float height;
    private float mass;
    private float speedX;
    private float speedY;
    private int blood;
    
    private int damage; // sát thương của đối tượng gây ra
    
    private int direction; // luw trạng thái hướng 
    
    protected Animation behurtForwardAnim , behurtBackAnim;
    
    private int teamType; 
    
    private long startTimeNoBeHurt;
    private long timeForNoBeHurt;

    public ParticularOject(float x , float y, float width, float height,float mass,float bood ,GameWorld gameWorld) {
        super(x,y,gameWorld);
        setWidth(width);
        setHeight(height);
        setMass(mass);
        setBlood(blood);
        
        direction = RIGHT_DIR;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getTeamType() {
        return teamType;
    }

    public void setTeamType(int teamType) {
        this.teamType = teamType;
    }

    public long getStartTimeNoBeHurt() {
        return startTimeNoBeHurt;
    }

    public void setStartTimeNoBeHurt(long startTimeNoBeHurt) {
        this.startTimeNoBeHurt = startTimeNoBeHurt;
    }

    public long getTimeForNoBeHurt() {
        return timeForNoBeHurt;
    }

    public void setTimeForNoBeHurt(long timeForNoBeHurt) {
        this.timeForNoBeHurt = timeForNoBeHurt;
    }
    
    public abstract void attack();
    
    public Rectangle getBoundForCollisionWithMap(){ // ractangle tra ve doi tuong hinh chu nhat
       
       Rectangle bound = new Rectangle();
       bound.x = (int) (getPosX() - getWidth()/2); // để làm cho hình chữ nhật sẽ bao quanh megaman
       bound.y = (int) (getPosY() - getHeight()/2); // lấy tọa độ megaman làm trọng tâm hcn
       bound.width = (int) getWidth();
       bound.height = (int) getHeight();
       return bound;
   }
    
    public void beHurt(int damgeEat){
        setBlood(getBlood() - damgeEat); // trừ máu khi bị gây damgeEat
        state = BEHURT;
        hurtingCallback();
    }
    
    @Override
    public void Update(){
        switch(state){
            case ALIVE:
                /*
                
                */
                break;
                
            case BEHURT:
                if(behurtBackAnim == null){ // không sử dụng animation cho trạng nhân vật bị gây sát thương
                    state = NOBEHURT;
                    startTimeNoBeHurt = System.nanoTime();
                    if(getBlood()==0){
                        state = FEY;
                    }
                }else { //sử dụng animation cho trạng nhân vật bị gây sát thương
                    behurtForwardAnim.Update(System.nanoTime());
                    if(behurtForwardAnim.isLastFrame()){
                        behurtForwardAnim.reset();
                        state = NOBEHURT;
                        if(getBlood() == 0){
                            state = FEY;
                        }
                        startTimeNoBeHurt = System.nanoTime();
                    }
                }
                break;
            
            case FEY:
                
                state = DEATH;
                
                break;
                
            case NOBEHURT:
                System.out.println("state = nobehurt");
                if(System.nanoTime() - startTimeNoBeHurt > timeForNoBeHurt){
                    state = ALIVE;
                }
                break;
                
        }   
    }
    
    public void drawBoundForCollisionWithMap(Graphics2D g2){
        Rectangle rect = getBoundForCollisionWithMap();
        g2.setColor(Color.BLUE);
    }
    
    public void drawBoundForCollisionWithEnemy(Graphics2D g2){
        Rectangle rect = getBoundForCollisionWithEnemy();
        g2.setBackground(Color.RED);
    }
    
    public abstract Rectangle getBoundForCollisionWithEnemy();
    
    public abstract void draw(Graphics2D g2);
    
    public void hurtingCallback(){};
  
    
    
    
}
