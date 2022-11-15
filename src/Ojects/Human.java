/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ojects;

import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Tean
 */
public abstract class Human extends ParticularOject{
    
    private boolean isJuming; // co đang ở trạng thái trên không không
    private boolean isDicking; // có ở trạng thái đang ngồi không
    
    private boolean isLanding; // đang quỳ xuống khi nhảy đáp 
    
    public Human(float x, float y, float width, float height, float mass, float bood, GameWorld gameWorld) {
        super(x, y, width, height, mass, bood, gameWorld);
        setState(ALIVE);
    }
    
    public abstract void run();
    
    public abstract void jump();
    
    public abstract void dick();
    
    public abstract void standUp();
    
    public abstract void stopRun();

    public boolean isIsJuming() {
        return isJuming;
    }

    public void setIsJuming(boolean isJuming) {
        this.isJuming = isJuming;
    }

    public boolean isIsDicking() {
        return isDicking;
    }

    public void setIsDicking(boolean isDicking) {
        this.isDicking = isDicking;
    }

    public boolean isIsLanding() {
        return isLanding;
    }

    public void setIsLanding(boolean isLanding) {
        this.isLanding = isLanding;
    }
    
    

    @Override
    public void attack() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void draw(Graphics2D g2) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void Update(){
        super.Update();
        if(getState() == ALIVE || getState() == NOBEHURT){
            
            if(!isLanding){
                
                setPosX(getPosX() + getSpeedX());
                
                if(getDirection() == LEFT_DIR &&
                        getGameWorld().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap())!=null){
                    Rectangle rectLeftWall = getGameWorld().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap());
                    setPosX(rectLeftWall.x + rectLeftWall.width + getWidth()/2);
                }
                if(getDirection() == RIGHT_DIR &&
                        getGameWorld().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap())!=null){
                    Rectangle rectRightWall = getGameWorld().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap());
                    setPosX(rectRightWall.x - getWidth()/2);
                }
                
                
                Rectangle boundForCollisionWithMapFuture = getBoundForCollisionWithMap();
                boundForCollisionWithMapFuture.y += (getSpeedY()!=0?getSpeedY():2);
                Rectangle rectLand = getGameWorld().physicalMap.haveCollisionWithLand(boundForCollisionWithMapFuture);
                
                Rectangle rectTop = getGameWorld().physicalMap.haveCollisionWithTop(boundForCollisionWithMapFuture);
                if(rectTop != null){
                    setSpeedY(0);
                    setPosY(rectTop.y + getGameWorld().physicalMap.getTileSize() + getHeight()/2);
                }
                else if(rectLand != null){
                    setIsJuming(false);
                    if(getSpeedY() >0) setIsLanding(true);
                    setSpeedY(0);
                    setPosY(rectLand.y - getHeight()/2 -1);
                }
                else{
                    setIsJuming(true);
                    setSpeedY(getSpeedY() + getMass());
                    setPosY(getPosY() + getSpeedY());
                }
                
                 
                
            }
        }
    }
}
