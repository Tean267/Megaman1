/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ojects;

import Effect.CacheDataLoader;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Tean
 */
public class PhysicalMap  extends GameOject{
    
    public int[][] phys_map;
    private int tileSize; // độ lớn của cạnh hình vuông
    
//    public float posX,posY;//*
    
    public PhysicalMap(float x, float y,GameWorld gameWorld){
        super(x,y,gameWorld);
//        this.posX= x;
//        this.posY= y;
        this.tileSize = 30;
        phys_map = CacheDataLoader.getInstance().getPhysicalMap();
    }
    
    public int getTileSize(){
        return tileSize;
    }
    
    public void draw(Graphics2D g2){
      g2.setColor(Color.GRAY);
      for(int i = 0;i<phys_map.length;i++){
          for(int j =0;j<phys_map[0].length;j++){
              if(phys_map[i][j]!=0) {
                  g2.fillRect((int)getPosX() + j*tileSize, (int)getPosY() +i*tileSize, tileSize, tileSize);
              }
          }
      }
    }
    
    public Rectangle haveCollisionWithLand(Rectangle rect){
        int posX1 = rect.x/tileSize; // tìm index x của rect trong mảng
        posX1 -= 2; // mở rộng phạm vi cho chắc chắn
        int posX2 = (rect.x + rect.width)/tileSize; // tìm index x + 1 của rect trong mảng 
        posX2 += 2;
        
        int posY1 = (rect.y + rect.height)/tileSize;
        
        if(posX1 < 0) posX1 = 0; // nếu x của rect mà = 0 hoăc 1 khi trừ cho 2 sẽ bị âm -> sẽ bị lỗi index nên am thi cho =0
        
        if(posX2 >= phys_map[0].length){
            posX2 = phys_map[0].length -1; 
        }
        
        for(int y = posY1; y< phys_map.length;y++){
            for(int x = posX1; x<=posX2;x++){
                
                if(phys_map[y][x] == 1){ // nếu nó là 1 vật cản thì chúng ta kiểm tra 
                    Rectangle r = new Rectangle((int)getPosX() + x*tileSize, (int)getPosY() + y*tileSize,tileSize,tileSize); 
                    if(rect.intersects(r)){ // Xác định xem rect và r có giao nhau,va cham hay không
                        return r;
                    }
                }
            }
        }
        return null;
    }
    
    public Rectangle haveCollisionWithTop(Rectangle rect){
        
        int posX1 = rect.x/tileSize;
        posX1 -= 2;
        int posX2 = ( rect.x +rect.width)/tileSize;
        posX2 += 2;
        
        int posY = rect.y/tileSize;
        
        if(posX1 < 0) posX1 = 0;
        
        if(posX2 >=phys_map[0].length){
            posX2 = phys_map[0].length -1;
        }
        
        for(int y = posY ;y>=0;y--){
            for(int x = posX1;x<=posX2;x++){
                
                if(phys_map[y][x]==1){
                    Rectangle r = new Rectangle((int) getPosX() + x*tileSize,(int) getPosY() + y*tileSize, tileSize, tileSize);
                    if(rect.intersects(r)){
                        return r;
                    }
                }
            }
        }
        return null;
    }
    
    public Rectangle haveCollisionWithRightWall(Rectangle rect){
        int posY1 = rect.y/tileSize;
        posY1 -=2;
        int posY2 = (rect.y + rect.height)/tileSize;
        posY2+=2;
        
        int posX1 = (rect.x + rect.width)/tileSize;
        int posX2 = posX1 +3;
        if(posX2 >= phys_map[0].length){
            posX2 = phys_map[0].length -1;
        }
        
        if(posY1 < 0) posY1 =0;
        if(posY2 >= phys_map.length) posY2 = phys_map.length -1;
        
        for(int x = posX1; x<= posX2;x++){
            for(int y = posY1;y<= posY2;y++){
                if(phys_map[y][x] ==1){
                    Rectangle r = new Rectangle((int) getPosX()+ x*tileSize,(int) getPosY() +y*tileSize,tileSize,tileSize);
                    if(r.y <rect.y + rect.height -1 && rect.intersects(r)){
                        return r;
                    }
                }
            }
        }
        return null;
   
    }
    
     public Rectangle haveCollisionWithLeftWall(Rectangle rect){
         
         int posY1 = rect.y/tileSize;
         posY1-=2;
         int posY2 = (rect.y + rect.height)/tileSize;
         posY2+=2;
         
         int posX1 = (rect.x +rect.width)/tileSize;
         int posX2 = posX1 -3;
         if(posX2 < 0) posX2 =0;
         
         if(posY1 <0) posY1 =0;
         if(posY2 >= phys_map.length) posY2 = phys_map.length -1;
         
         for(int x = posX1; x>= posX2;x--){
            for(int y =posY1;y<=posY2;y++){
                if(phys_map[y][x]==1){
                    Rectangle r = new Rectangle((int) getPosX()+ x*tileSize,(int) getPosY() +y*tileSize,tileSize,tileSize);
                    if(r.y <rect.y +rect.height -1 &&rect.intersects(r)){
                        return r;
                    }
                }
            }
        }
         return null;
         
     }
    @Override 
    public void Update() {
      
    }
    
    
}
