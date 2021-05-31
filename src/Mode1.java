package javaapplication1;
import java.io.File;
import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;


public class Mode1  {
    Pane p =new Pane();  // Mode 1 pane
    
    Media mus = new Media(new File("Ball_Bounce.mp3").toURI().toString());
    MediaPlayer mediaplayer =new MediaPlayer(mus); 
    Media mus2= new Media(new File("Block_Destroy.mp3").toURI().toString());
    MediaPlayer media= new MediaPlayer(mus2);
    Media mus3 = new Media(new File("game_over.mp3").toURI().toString());
    MediaPlayer GameOver =new MediaPlayer(mus3);
    Media mus4 = new Media(new File("win.mp3").toURI().toString());
    MediaPlayer win2 =new MediaPlayer(mus4);
Button bt1= new Button("Exit");
Button bt2= new Button("Main Menu");

List<brick>bricks =new ArrayList<>();
int w=1365;int h=767;

Rectangle Bat =new Rectangle(w/2,h-80 ,120, 10);
 int brick_w=100;
 int brick_h=40;
boolean gamefinished= false;
public int Score;
Text txt=new Text(10,h-h/20, "Score: "+Score);
  Image brick_im = new Image("file:brick.png");
  Image brick_im2 = new Image("file:brick2.png");
    Image brick_im3 = new Image("file:brick3.png");
      Image brick_im4 = new Image("file:brick4.png");
      Image brick_im5 = new Image("file:brick5.png");
      Image brick_im6 = new Image("file:brick6.png");
    public final double radius = 5;
  private double x = w/2, y = h-90;
  private double dx = 1, dy = 1;
  private Circle circle = new Circle(x, y, radius);
  private Timeline animation;

class brick  {

    private double x,y;
    private Rectangle rect;

        public void setX(double x) {
            this.x = x;
        }

        public void setY(double y) {
            this.y = y;
        }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

        public Rectangle getRect() {
            return rect;
        }
    
    public brick(double x,double y, Image im) {
        this.x=x;this.y=y;
       rect=new Rectangle(brick_w, brick_h);
        rect.setX(x);
        rect.setY(y);

       rect.setFill( new ImagePattern(im));
        
        p.getChildren().add(rect);
        
        
    }
    }


    
    public Mode1(int level) {
        
p.getChildren().add(Background());

 Image im = new Image("file:bat.png");
 Bat.setFill(new ImagePattern(im));
  
p.setCursor(Cursor.NONE);

txt.setFill(Color.BLACK);
txt.setFont(Font.font(24));
    circle.setFill(Color.RED.darker()); 
    p.getChildren().add(circle); 
    
    animation = new Timeline(
      new KeyFrame(Duration.millis(5), e -> Movement()));
    animation.setCycleCount(Timeline.INDEFINITE);
    
  
        Levels(level);





       

 p.setOnMouseMoved(e ->{
                       if(e.getX()<w-120)
                        Bat.setX(e.getX());
           if(e.getButton()!=MouseButton.PRIMARY&&animation.getStatus()!=Animation.Status.RUNNING
                   &&e.getX()<w-120){
               circle.setCenterX(e.getX()+60);
              
           }
        });
               

         
p.setOnMouseClicked(e ->{
           if (e.getButton()==MouseButton.PRIMARY&&animation.getStatus()!=(Animation.Status.RUNNING)){
               x=Bat.getX()+60;
           animation.play();}
       });  



       
        p.getChildren().addAll(Bat,txt);
        p.requestFocus();


    }
   

        



  protected void Movement() {
      
      //mediaplayer.setRate(10);
      mediaplayer.setOnEndOfMedia(()->{mediaplayer.stop();});
      media.setOnEndOfMedia(()->{media.stop();});

      try {
          

      for (brick c : bricks) {

        if((y+radius==c.getY()||y-radius==c.getY()+brick_h) && x>=c.getX()&&x<c.getX()+brick_w){
          p.getChildren().remove(c.rect);
              mediaplayer.play();
              dy*=-1;
              bricks.remove(c);
              Score+=5;
	txt.setText("Score: "+Score);
          }
         else if((x+radius==c.getX()||x-radius==c.getX()+brick_w)&&y<=c.getY()+brick_h&&y>c.getY()){
          p.getChildren().remove(c.rect);
              mediaplayer.play();
              dx*=-1;
              bricks.remove(c);
              Score+=5;
	txt.setText("Score: "+Score);
          }
         else if(c.getRect().getBoundsInParent().contains(circle.getBoundsInParent())){
     p.getChildren().remove(c.rect);
              mediaplayer.play();
              bricks.remove(c);
              Score+=5;
              dx*=-1;
              dy*=-1;
	txt.setText("Score: "+Score);
    
}

      }
      GameOver.setOnEndOfMedia(()->{GameOver.stop();});
      win2.setOnEndOfMedia(()->{win2.stop();});
            if (bricks.isEmpty()==true) {
                        win2.play();
                        gamefinished=true;
                         Text win =new Text(w/2-w/4, h/2, "YOU WIN !");
                         win.setFill(Color.GREEN);
			win.setFont(new Font(100));
                        txt.setFill(Color.WHITE);
                       Rectangle r1 =new Rectangle(w,h);
                        r1.setOpacity(0.7);
                        bt1.setLayoutX(w/2-70);
                        bt1.setLayoutY(h-(h/3)+20);
                        bt1.setPrefSize(140, 40);
                        bt1.setCursor(Cursor.HAND);
                        bt1.setOnAction(e->{System.exit(0);});
                        bt1.setStyle("-fx-background-color:red;"+"-fx-border-radius:20;"
                                +"-fx-font-weight:bold;"+"-fx-text-fill:white;"
                                +"-fx-font-size:16;"+"-fx-background-radius:22;");
                        bt2.setLayoutX(w/2-70);
                        bt2.setLayoutY(h-h/3-50);
                        bt2.setPrefSize(140, 40);
                        bt2.setCursor(Cursor.HAND);
                        bt2.setStyle("-fx-background-color:red;"+"-fx-border-radius:20;"
                                +"-fx-font-weight:bold;"+"-fx-text-fill:white;"
                                +"-fx-font-size:16;"+"-fx-background-radius:22;");
                        
                        p.getChildren().addAll(r1,win,bt1,bt2);
                         p.setCursor(Cursor.DEFAULT);
	                circle.setFill(null);
			animation.stop();
		}
            if (y+radius>Bat.getY()+15) {
                        GameOver.play();
                        gamefinished=true;
			 Text lost =new Text(w/2-(w/4)+70, h/2, "Game Over");
			lost.setFill(Color.RED);
			lost.setFont(new Font(100));
                        txt.setFill(Color.WHITE);
                       Rectangle r1 =new Rectangle(w,h);
                        r1.setOpacity(0.7);
                        
                        bt1.setLayoutX(w/2-70);
                        bt1.setLayoutY(h-(h/3)+20);
                        bt1.setPrefSize(140, 40);
                        bt1.setCursor(Cursor.HAND);
                        bt1.setOnAction(e->{System.exit(0);});
                        bt1.setStyle("-fx-background-color:red;"+"-fx-border-radius:20;"
                                +"-fx-font-weight:bold;"+"-fx-text-fill:white;"
                                +"-fx-font-size:16;"+"-fx-background-radius:22;");
                         
                        bt2.setLayoutX(w/2-70);
                        bt2.setLayoutY(h-h/3-50);
                        bt2.setPrefSize(140, 40);
                        bt2.setCursor(Cursor.HAND);
                        bt2.setStyle("-fx-background-color:red;"+"-fx-border-radius:20;"
                                +"-fx-font-weight:bold;"+"-fx-text-fill:white;"
                                +"-fx-font-size:16;"+"-fx-background-radius:22;");
                        
                        p.getChildren().addAll(r1,lost,bt1,bt2);
                        p.setCursor(Cursor.DEFAULT);
                        circle.setFill(null);
			animation.stop();
		}
    if (x < radius || x >w - radius) {
        media.play();
        dx *= -1; 
    }
    if (y < radius || y > h - radius) {
        media.play();
      dy *= -1;
    }
    

   if( Bat.getBoundsInLocal().intersects(circle.getBoundsInLocal()))
  {
            dy *= -1; 
            Random r=new Random();
            dx=r.nextInt(3)-1;
            
  }

      } catch (Exception e) {
      }
      
    x += dx;
    y += dy;
    circle.setCenterX(x);
    circle.setCenterY(y);
  }

    public void increaseSpeed(double rate) {
    animation.setRate(animation.getRate() + rate);
  }      
    

     final void Levels(int level){

                if(level==3)  {     
                           increaseSpeed(0.6);

                      int t=0;
                       for (int i = 0; i < 5; i++) {
                          int s=0;
                            for (int j = 0; j < 13; j++) {
                             brick e=new brick(s, t,brick_im);
                             bricks.add(e);
                                s+=105;
            }
            t+=42;
            
        }
  }
        if(level==2){
            
                       increaseSpeed(0.4);
                      
                       int t=40;
                       for (int i = 0; i < 5; i++) {
                          int s=40;
                            for (int j = 0; j < 5; j++) {
                          
                           
                             brick e=new brick(s, t,brick_im2);
                             bricks.add(e);
                                s+=100;
            }
                            int m=w/2+110;
                            for (int j = 0; j < 5; j++) {
                               
                         
                          
                             brick e=new brick(m, t,brick_im2);
                             bricks.add(e);
                                m+=100;
            }
            t+=42;
                       }
            
        }
        if(level==1){ 
            animation.setRate(animation.getRate());
                       int t=0;
                       for (int i = 0; i < 6; i++) {
                          int s=40;
                            for (int j = 0; j <= i; j++) {
                          
                           
                             brick e=new brick(s, t,brick_im3);
                             bricks.add(e);
                                s+=100;
            }
                            for (int j = 0; j <6-i; j++) {

                                s+=100;
            }
                             for (int j = 0; j <= i; j++) {
                          
                           
                             brick e=new brick(s, t,brick_im3);
                             bricks.add(e);
                                s+=100;
            }

            t+=42;
                       }
            
        }
        if(level==4)  {     
                                   increaseSpeed(0.6);

                      int t=0;
                      int k=11;
                      int s;
                     
                           for (int v = 100; v < 700; v+=100) {
                               
                          s=v;
                           
                            for (int j = 0; j < k; j++) {
                             brick e=new brick(s, t,brick_im5);
                             bricks.add(e);
                                s+=100;
            }
                           
            t+=42;
            k-=2;
                           }
            
                       }
       
        if(level==5)  {     
                                   increaseSpeed(0.6);

                      int t=0;
                      int k=11;
                      int s;
                       for (int i = 0; i < 8; i++){ 
                               
                          s=100;
                 
                            for (int j = 0; j < k; j++) {
                             brick e=new brick(s, t,brick_im6);
                             bricks.add(e);
                                s+=100;
            }
                           
            t+=42;
            k--;
                           
            
                       }
        }
        
        if(level==6){ 
            animation.setRate(animation.getRate()+0.6);
            increaseSpeed(0.2);
                       int t=0;
                       for (int f=20;f<1400;f+=100){  
                     brick r= new brick(f,420,brick_im4);
                     bricks.add(r);
                     }
                     for (int k=10;k<1400;k+=100){  
                     brick r= new brick(k,500,brick_im4);
                     bricks.add(r);
                     }
                       for (int i = 0; i < 6; i++) {
                          int s=40;
                            for (int j = 0; j <= i; j++) {
                          
                           
                             brick e=new brick(s, t,brick_im4);
                             bricks.add(e);
                                s+=100;
            }
                            for (int j = 0; j <6-i; j++) {

                                s+=100;
            }
                             for (int j = 0; j <= i; j++) {
                          
                           
                             brick e=new brick(s, t,brick_im4);
                             bricks.add(e);
                                s+=100;
            }

            t+=42;
                       }
        }
    
        if(level==7)  {     
                    increaseSpeed(0.7);
                    
                    int t=0;
                    for (int f=0;f<1400;f+=100){  
                     brick r= new brick(f,560,brick_im4);
                     bricks.add(r);
                     }
                      for (int f=0;f<1400;f+=120){  
                     brick r= new brick(f,420,brick_im4);
                     bricks.add(r);
                     }
                     for (int k=120;k<1400;k+=240){  
                     brick r= new brick(k,500,brick_im4);
                     bricks.add(r);
                     }
                       for (int i = 0; i < 8; i++) {
                          int s=0;
                            for (int j = 0; j < 14; j++) {
                             brick e=new brick(s, t,brick_im4);
                             bricks.add(e);
                             s+=100;

            }
            t+=42;
            
        }
        }
        
    }
   

     
     
    //****** METHOD TO CREATE BACKGROUND ANIMATION ****** \\
    
    
    Parent Background(){
      
         Group root = new Group();
        Scene scene = new Scene(root, w, h,null);
       
        Group circles = new Group();
        for (int i = 0; i < 30; i++) {
            Circle circle = new Circle(150, Color.web("white", 0.05));
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.web("white", 0.16));
            circle.setStrokeWidth(4);
            circles.getChildren().add(circle);
        }
        Rectangle colors = new Rectangle(scene.getWidth(), scene.getHeight(),
                new LinearGradient(1f, 1f, 0f, 1f, true, CycleMethod.NO_CYCLE, new Stop[]{
                    new Stop(0, Color.web("#f8bd55")),
                    new Stop(0.14, Color.web("#c0fe56")),
                    new Stop(0.28, Color.web("#5dfbc1")),
                    new Stop(0.43, Color.web("#64c2f8")),
                    new Stop(0.57, Color.web("#be4af7")),
                    new Stop(0.71, Color.web("#ed5fc2")),
                    new Stop(0.85, Color.web("#ef504c")),
                    new Stop(1, Color.web("#f2660f")),}));
        Group blendModeGroup =
                new Group(new Group(new Rectangle(scene.getWidth(), scene.getHeight(),
                Color.DARKCYAN.brighter()), circles), colors);
        colors.setBlendMode(BlendMode.OVERLAY);
        root.getChildren().add(blendModeGroup);
        circles.setEffect(new BoxBlur(10, 10, 3));
        Timeline timeline = new Timeline();
        for (Node circle : circles.getChildren()) {
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, // set start position at 0
                    new KeyValue(circle.translateXProperty(), random() * w),
                    new KeyValue(circle.translateYProperty(), random() * h)),
                    new KeyFrame(new Duration(40000), // set end position at 40s
                    new KeyValue(circle.translateXProperty(), random() * w),
                    new KeyValue(circle.translateYProperty(), random() * h)));
        }
        
        // play 40s of animation
        timeline.play();
        return root;
    }
    
    
    void stop(){
        animation.stop();
        

    }

}
