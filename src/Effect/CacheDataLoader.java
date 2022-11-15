/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Effect;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import javax.imageio.ImageIO;

/**
 *
 * @author Tean
 */
public class CacheDataLoader { // cấu trúc Singleton Design Pattern
    
    private static CacheDataLoader instance; 
    
    private Hashtable<String, FrameImage> frameImages;
    private Hashtable<String, Animation> animations;
    private int[][] phys_map;
    
    private String physmapfile = "data/phys_map.txt";
    private String framefile = "data/frame.txt";
    private String animationfile = "data/animation.txt";
    
    private CacheDataLoader(){
    }
    
    public static CacheDataLoader getInstance(){ 
        
        if(instance == null){
            instance = new CacheDataLoader();
        }
        return instance;
    }
    
    public void LoadData() throws IOException{
        LoadFrame(); // nếu gọi LoadAnimation trc thì sẽ bị lỗi k có file và bị
        LoadAnimation();// ném vào IOException , nên nếu tạo method này người lập trình sẽ k bị nhầm lẫn 
        LoadPhysMap();
    }
    public void LoadFrame() throws IOException{
        
        frameImages = new Hashtable<String,FrameImage>();
        
        FileReader fr = new FileReader(framefile); // để đọc file nhưng chỉ đọc đc theo kí tự k dc theo line
        BufferedReader br = new BufferedReader(fr); // dùng để bocj fr để thao tác đọc tốt hơn nhiều method hơn
        
        String line = null ;
        
        if(br.readLine()==null){ // kiem tra xem file co du lieu khong
            System.out.println("No data");
            throw new IOException();
        }
        else{ // neu file co du lieu thi khai bao lai để đưa con trỏ về đầu file để chuẩn bị lấy n(số frame)
           
            fr = new FileReader(framefile);
            br = new BufferedReader(fr);
            
            while((line = br.readLine()).equals("")); // viết tắt 2 thao tác : gắn và so sánh
            
            int n = Integer.parseInt(line); // số frame
            
            for(int i = 0 ; i < n ; i++){
                
                FrameImage frame = new FrameImage();
                while((line = br.readLine()).equals(""));// bỏ khoảng trống giữa n và tên
                frame.setName(line);// chuyền tên của frame vào 
                
                while((line = br.readLine()).equals(""));
                String[] str = line.split(" "); // cho các phần tử của line vào mảng , chia theo " "
                String path = str[1]; // str[1] luu trữ nguồn của frame
                
                while((line = br.readLine()).equals(""));
                str = line.split(" ");
                int x = Integer.parseInt(str[1]);
                
                while((line = br.readLine()).equals(""));
                str = line.split(" ");
                int y = Integer.parseInt(str[1]);
                
                while((line = br.readLine()).equals(""));
                str = line.split(" ");
                int w = Integer.parseInt(str[1]);
                
                while((line = br.readLine()).equals(""));
                str = line.split(" ");
                int h = Integer.parseInt(str[1]);
                
                BufferedImage imageData = ImageIO.read(new File(path));
                BufferedImage image = imageData.getSubimage(x, y, w, h);
                frame.setImage(image);
                
                instance.frameImages.put(frame.getName(), frame);
            }
        }
        br.close();// khi đọc xong thì đóng lại
    }
    public FrameImage getFrameImage(String name){
        FrameImage frameImage = new FrameImage(instance.frameImages.get(name));
        return frameImage;
    }
    
    public void LoadAnimation() throws IOException{
        
        animations = new Hashtable<String,Animation>();
        
        FileReader fr = new FileReader(animationfile);
        BufferedReader br = new BufferedReader(fr);
        
        String line = null;
        
        if(br.readLine()==null){
            System.out.println("No Data");
            throw new IOException();
        }
        else{
            
            fr = new FileReader(animationfile);
            br = new BufferedReader(fr);
            
            while((line = br.readLine()).equals(""));
            int n = Integer.parseInt(line);
            
            for(int i = 0;i < n; i++){
                Animation animation = new Animation();
                
                while((line = br.readLine()).equals(""));
                animation.setName(line);
                
                while((line = br.readLine()).equals(""));
                String[] str = line.split(" ");
                
                for(int j = 0;j<str.length;j+=2){
                    animation.add(getFrameImage(str[j]),Double.parseDouble(str[j+1]));
                    //frame "getFrameImage(str[j])" là frame str[j], str[j] la
                    //1 frame có dc lưu trong frameImages qua method LoadFrame
  
                }
                instance.animations.put(animation.getName(), animation);
            }
            
        }
        br.close();
    }
    
    public Animation getAnimation(String name){
        
        Animation animation = new Animation(instance.animations.get(name));
        return animation;
    }
    
    public int[][] getPhysicalMap(){
        return instance.phys_map;
    }
    
    public void LoadPhysMap() throws IOException{ // load map vào ma tran phys_map
        FileReader fr = new FileReader(physmapfile);
        BufferedReader br = new BufferedReader(fr);
        
        String line = null;
        
        line = br.readLine();
        int numberOfRows = Integer.parseInt(line);
        line = br.readLine();
        int numberOfColumns = Integer.parseInt(line);
        
        instance.phys_map = new int[numberOfRows][numberOfColumns];
        
        for(int i = 0;i< numberOfRows;i++){
            line = br.readLine();
            String[] str = line.split(" ");
            for(int j = 0;j< numberOfColumns;j++){
                instance.phys_map[i][j] = Integer.parseInt(str[j]);
            }
        }
//        for(int i=0;i<numberOfRows;i++){
//            for(int j=0;j<numberOfColumns;j++){
//                System.out.println(instance.phys_map[i][j]+" ");
//            }
//            System.out.println("");
//        }
        br.close();
    }
    
}
