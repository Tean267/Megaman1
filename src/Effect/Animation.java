
package Effect;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Tean
 */
public class Animation {
    
    private String name; // để quản lí Animation;
    private boolean isRepeated; // kiểm tra trạng thái , nếu image = bằng false thì k repeate
    private ArrayList<FrameImage> frameImages;
    private int currentFrame; // chỉ frame hiện tại đang đc vẽ trên màn hình 
    private ArrayList<Boolean> ignoreFrames; 
    private ArrayList<Double> delayFrames; // chứa thời gian delay giữa các frame
    private long beginTime;
    private boolean drawRectFrame; // vẽ khung hình của frame, trưe vẽ , false không
    
    public Animation(){
        delayFrames = new ArrayList<Double>();
        beginTime=0;
        currentFrame = 0;
        ignoreFrames = new ArrayList<Boolean>();
        frameImages = new ArrayList<FrameImage>();
        drawRectFrame = false; // mặc định không vẽ
        isRepeated = true; // mặc định xét aniamtion dc repeate 
    }
    
    public Animation(Animation animation){
        beginTime = animation.beginTime;
        currentFrame = animation.currentFrame;
        drawRectFrame = animation.drawRectFrame;
        isRepeated = animation.isRepeated;
        delayFrames = new ArrayList<Double>();
        for(Double d : animation.delayFrames){
            delayFrames.add(d);
        }
        ignoreFrames = new ArrayList<Boolean>();
        for(Boolean b : animation.ignoreFrames){
            ignoreFrames.add(b);
        }
        
        frameImages = new ArrayList<FrameImage>();
        for(FrameImage f : animation.frameImages){
            frameImages.add(new FrameImage(f));
        }
        
    }
   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsRepeated() {
        return isRepeated;
    }

    public void setIsRepeated(boolean isRepeated) {
        this.isRepeated = isRepeated;
    }

    public ArrayList<FrameImage> getFrameImages() {
        return frameImages;
    }

    public void setFrameImages(ArrayList<FrameImage> frameImages) {
        this.frameImages = frameImages;
    }

    public int getCurrentFrame() {
       return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) { 
       if(currentFrame >=0 && currentFrame < frameImages.size()){ // phải kiểm tra currentFrame có hợp lệ không 
            this.currentFrame = currentFrame;
        }
        else this.currentFrame =0;
    }

    public ArrayList<Boolean> getIgnoreFrames() {
        return ignoreFrames;
    }

    public void setIgnoreFrames(ArrayList<Boolean> ignoreFrames) {
        this.ignoreFrames = ignoreFrames;
    }

    public ArrayList<Double> getDelayFrames() {
        return delayFrames;
    }

    public void setDelayFrames(ArrayList<Double> delayFrames) {
        this.delayFrames = delayFrames;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public boolean getDrawReactFrame() {
        return drawRectFrame;
    }

    public void setDrawReactFrame(boolean drawReactFrame) {
        this.drawRectFrame = drawReactFrame;
    }
    
    public boolean isIgnoreFrame(int id){ // id dc trich xuất từ ignoreframe xem nó có bị k dc vẽ ra k 
        return ignoreFrames.get(id);
    }
    
    public void setIgnoreFrame(int id){ 
        if(id >=0 && id < ignoreFrames.size()){// bỏ trường hợp frame có id nhỏ hơn 0 và lơn hơn size
            ignoreFrames.set(id, true);
        }
    }
    
    public void unIgnoreFrame (int id){
        if(id >=0 && id < ignoreFrames.size()){// bỏ trường hợp frame có id nhỏ hơn 0 và lơn hơn size
            ignoreFrames.set(id, false);
        }
    }
    
    public void reset(){ // reset khi thay đổi frame
        currentFrame = 0;
        beginTime =0;
        for(int i =0;i < ignoreFrames.size();i++){
            ignoreFrames.set(i, false);
        }
    }
    
    public void add(FrameImage frameImage, double timeToNextFrame){ //timeToNextFrame : thời gian dến frame tiep theo
        
        ignoreFrames.add(false);
        frameImages.add(frameImage);
        delayFrames.add(new Double (timeToNextFrame));
    }
    
    public BufferedImage getCurrentImage(){ // chả vè image của index = currenFrame
        return frameImages.get(currentFrame).getImage();
    }
    
    public void nextFrame(){
        if(currentFrame >= frameImages.size() - 1){ 
            if(isRepeated) currentFrame = 0; // nếu có lặp lại thì cho =0;
        }
        else currentFrame ++;
        if(ignoreFrames.get(currentFrame)) nextFrame(); // nếu là frame bị bỏ qua thi nextFrame
    }
    
    public void Update(long currentTime){ // currentTime là time của hệ thông mà ta chuyền vào 
        
        if(beginTime ==0 ) beginTime = currentTime;
        else{
            if(currentTime - beginTime > delayFrames.get(currentFrame)){
                nextFrame();
                beginTime = currentTime;
            }
        }
    }
    
    public boolean isLastFrame(){ // kiemtra xem animation này đã chạy xong ch để chuyển trạng thái 
        if(currentFrame == frameImages.size()-1){
            return true;
        }
        else return false;
    }
    
    public void flipAllImage(){ // lật các image 
        for(int i =0 ;i< frameImages.size();i++){
            BufferedImage image = frameImages.get(i).getImage();
            
            AffineTransform tx = AffineTransform.getScaleInstance(-1,1);
            tx.translate(-image.getWidth(),0);
            
            AffineTransformOp op = new AffineTransformOp(tx,AffineTransformOp.TYPE_BILINEAR);
            image = op.filter(image, null);
            
            frameImages.get(i).setImage(image);
        }
    }
    
    public void draw(int x, int y , Graphics2D g2){
        
        BufferedImage image = getCurrentImage();
        
        g2.drawImage(image,x - image.getWidth()/2, y - image.getHeight()/2,null);
        if(drawRectFrame){
            g2.drawRect(x-image.getWidth()/2,x-image.getWidth()/2 , image.getWidth(), image.getHeight());
        }
    }
}
