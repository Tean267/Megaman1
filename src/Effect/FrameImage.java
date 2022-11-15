/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Effect;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Tean
 */
public class FrameImage {
    
    private String name;
    private BufferedImage image;
    
    public FrameImage(String name, BufferedImage image){
        this.name = name;
        this.image = image;
              
    }
    
    public FrameImage(FrameImage frameImage){ // để copy dữ liệu
        image = new BufferedImage(frameImage.getImageWidth(),frameImage.getImageHeight(),frameImage.getImage().getType());
        // neu đặt là image = frameImage.getImage() thì this.image sẽ trỏ cùng với image của frameImage, sẽ dùng chung 1 image
        
        Graphics g = image.getGraphics(); // tạo bút vẽ riêng cho image
        g.drawImage(frameImage.getImage(), 0, 0, null);
        
    }
    FrameImage(){
        image = null;
        name = null;
    }
    public void draw(Graphics2D g2,int x, int y){
        g2.drawImage(image, x-image.getWidth()/2,y-image.getHeight()/2,null);
    }
    public int getImageWidth(){
        return image.getWidth();
    }
    public int getImageHeight(){
        return image.getHeight();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
    
}
