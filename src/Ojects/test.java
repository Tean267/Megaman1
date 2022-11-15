/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ojects;

import Effect.CacheDataLoader;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tean
 */
public class test {
    
    static int[][] phys_map = CacheDataLoader.getInstance().getPhysicalMap();;
    static int tileSize; // độ lớn của cạnh hình vuông
    
    static float x,y;//*
    
    static int getTileSize(){
        return tileSize;
    }
    
    static void draw(Graphics2D g2){
      g2.setColor(Color.GRAY);
      for(int i = 0;i<phys_map.length;i++){
          for(int j =0;j<phys_map[0].length;j++){
              if(phys_map[i][j]!=0) g2.fillRect((int)x + j*tileSize, (int)y +j*tileSize, tileSize, tileSize);
          }
      }
    }
    static void abcytry(){
         for(int i=0;i<50;i++){
            for(int j=0;j<156;j++){
                System.out.println(phys_map[i][j]+" ");
            }
            System.out.println("");
        }
    }
    public static void main(String[] args) {
        try {
            // abcytry();
            CacheDataLoader.getInstance().LoadData();
        } catch (IOException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        int[][] phys_mapp = CacheDataLoader.getInstance().getPhysicalMap();
//        for(int i =0;i<phys_mapp.length;i++){
//            for(int j=0;j<phys_mapp[0].length;j++){
//                g2.fillRect(20+j*20, 20+i*20, 100, 100);
//            }
//        }
    }
}
