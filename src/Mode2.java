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
import javafx.application.Application;
import static javafx.application.Application.launch;
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
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
 


public class Mode2  {

    MediaPlayer mediaPlayer2;
    Media mus = new Media(new File("Ball_Bounce.mp3").toURI().toString());
     MediaPlayer mediaplayer =new MediaPlayer(mus);
     
     Media mus2= new Media(new File("Block_Destroy.mp3").toURI().toString());
     MediaPlayer media= new MediaPlayer(mus2);

List<brick>bricks_1 =new ArrayList<>();

List<brick>bricks_2 =new ArrayList<>();

int w=1365;int h=767;
Pane p =new Pane();
int Bat_long=80;
Rectangle Bat_2 =new Rectangle(w/2+w/4,h-80 ,Bat_long, 10);
Rectangle Bat_1 =new Rectangle(w/4,h-80 ,Bat_long, 10);
 int brick_w=100;
 int brick_h=40;
boolean keys[]=new boolean[6];
public int Score;
Button bt1 = new Button("Exit");
Button bt2 = new Button("Main Menu");
Ball b1 =new Ball(1);

 Ball b2 =new Ball(2);
  Image brick_im = new Image("file:brick3.png");
Image brick_smash = new Image( "file:bricksm.png");


boolean gamefinish=false;
class brick  {

    private double x,y;
    private int smash;
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
    
    public brick(double x,double y) {
        
           this.x=x;this.y=y;
           smash =1;
       rect=new Rectangle(brick_w, brick_h);
        rect.setX(x);
        rect.setY(y);

       rect.setFill( new ImagePattern(brick_im));
        
        p.getChildren().add(rect);
        
        
    }
    }


    
    public  Mode2() {


p.getChildren().add(Background());

         Line l =new Line(w/2, 0, w/2, h);
         l.setStroke(Color.BLACK);
         l.setStrokeWidth(4);
         
p.setCursor(Cursor.NONE);


Text txt_1 =new Text("Player 1");
txt_1.setX(0);
txt_1.setY(15);
txt_1.setFont(Font.font(20));
txt_1.setFill(Color.WHITE);

Text txt_2 =new Text("Player 2");
txt_2.setX(w-80);
txt_2.setY(15);
txt_2.setFill(Color.WHITE);
txt_2.setFont(Font.font(20));

Build_bricks();
            


                  
            
        
 Bat_1.setFill(Color.BLACK);
 Bat_1.setArcHeight(30);
 Bat_1.setArcWidth(30);
 Bat_1.setStroke(Color.BLACK.brighter());

 Bat_2.setFill(Color.BLACK);
 Bat_2.setArcHeight(30);
 Bat_2.setArcWidth(30);
 Bat_2.setStroke(Color.BLACK.brighter());
//******************

//***********


 p.getChildren().addAll(Bat_1,Bat_2,b1,b2,l,txt_1,txt_2);
       


p.setOnKeyPressed(e ->{
         
    if(e.getCode()==KeyCode.S){
 if(!gamefinish){
       b1.play();
   b1.play3();
 }
    }
    if(e.getCode()==KeyCode.LEFT)
                keys[1]=true;

    if(e.getCode()==KeyCode.RIGHT)
                keys[2]=true;

    
          if(e.getCode()==KeyCode.L){
              if(!gamefinish){
       b2.play();
   b1.play3();
              }
    }
    if(e.getCode()==KeyCode.A)
                keys[4]=true;

    if(e.getCode()==KeyCode.D)
       keys[5]=true;

        

 });


p.setOnKeyReleased(e ->{
         

    if(e.getCode()==KeyCode.LEFT)
                keys[1]=false;

    if(e.getCode()==KeyCode.RIGHT)
                keys[2]=false;

    

    if(e.getCode()==KeyCode.A)
                keys[4]=false;

    if(e.getCode()==KeyCode.D)
       keys[5]=false;

        

 });



    }
    
    class Ball extends Pane {
        
        
  public final double radius = 5;
   private double dx_1,dy_1;
  private double dx , dy ;
  private Circle circle ;
   private Circle circle_2 ;
  private Timeline animation;
 private Timeline animation2;
  private Timeline animation3;

  public Ball(int pick) {

      if(pick==1){
           dx = 1; dy = 1;
    circle = new Circle(Bat_1.getX()+Bat_long/2, h-90, radius,Color.RED);
          this.getChildren().add(circle);
              animation = new Timeline(
      new KeyFrame(Duration.millis(5), e -> {

        
          Movement(1);
             
  }));
    animation.setCycleCount(Timeline.INDEFINITE);
    
      }
      else
      {
          
          dx_1=1;
          dy_1=1;
circle_2 = new Circle(Bat_2.getX()+Bat_long/2, h-90, radius,Color.BLUE);
this.getChildren().add(circle_2);
    animation = new Timeline(
      new KeyFrame(Duration.millis(5), e -> {

        
          Movement(2);
             
  }));
    animation.setCycleCount(Timeline.INDEFINITE);
    
      }
    


 
    
      animation3 = new Timeline(
      new KeyFrame(Duration.millis(5), e -> {
      
      
                    move();
          
                    
  }));
    animation3.setCycleCount(Timeline.INDEFINITE);
  }

  public void play() {
    animation.play();
  }

  public void play2() {
    animation2.play();
  }

  public void play3() {
    animation3.play();
  }



  protected void Movement(int pick) {
      mediaplayer.setOnEndOfMedia(()->{mediaplayer.stop();});
      media.setOnEndOfMedia(()->{media.stop();});
      try {
          
    
     
     if(pick==2){
         


         for (brick c : bricks_2) {

        if((circle_2.getCenterY()+radius==c.getY()||circle_2.getCenterY()-radius==c.getY()+brick_h) && circle_2.getCenterX()>=c.getX()&&circle_2.getCenterX()<c.getX()+brick_w){
        if(c.smash==1){
            c.rect.setFill(new ImagePattern(brick_smash));
             mediaplayer.play();
              dy_1*=-1;
              c.smash--;
        }
        else{
            p.getChildren().remove(c.rect);
           mediaplayer.play();
              dy_1*=-1;
              bricks_2.remove(c);
              Score+=5;
        }
          }
         else if((circle_2.getCenterX()+radius==c.getX()||circle_2.getCenterX()-radius==c.getX()+brick_w)&&circle_2.getCenterY()<=c.getY()+brick_h&&circle_2.getCenterY()>c.getY()){
       if(c.smash==1){
            c.rect.setFill(new ImagePattern(brick_smash));
             mediaplayer.play();
              dx_1*=-1;
              c.smash--;
        }
        else{
            p.getChildren().remove(c.rect);
           mediaplayer.play();
              dx_1*=-1;
              bricks_2.remove(c);
              Score+=5;
        }
         }
         else if(c.getRect().getBoundsInParent().contains(circle_2.getBoundsInParent())){
        if(c.smash==1){
            c.rect.setFill(new ImagePattern(brick_smash));
             mediaplayer.play();
              dy_1*=-1;
              dx_1*=-1;
              c.smash--;
        }
        else{
            p.getChildren().remove(c.rect);
           mediaplayer.play();
              dy_1*=-1;
              dx_1*=-1;
              bricks_2.remove(c);
              Score+=5;
        }
    
}

       
      }
            if (bricks_2.isEmpty()==true) {
                         Text win =new Text(w/2-w/4, h/2, "PLAYER  2 WINS !");
                         Rectangle r1 =new Rectangle(1365,767);
                         r1.setOpacity(0.4);
			win.setFill(Color.RED);
			win.setFont(new Font(100));
                        gamefinish=true;
                        bt1.setLayoutX(w/2-70);
                        bt1.setLayoutY(h-(h/3)+20);
                        bt1.setPrefSize(140, 40);
                        bt1.setCursor(Cursor.HAND);
                        bt1.setOnAction(e->{System.exit(0);});
                        bt1.setStyle("-fx-background-color:red;"+"-fx-border-radius:20;"+"-fx-font-weight:bold;"
            +"-fx-text-fill:white;"+"-fx-font-size:18;"+"-fx-background-radius:22;");
                        bt2.setLayoutX(w/2-70);
                        bt2.setLayoutY(h-h/3-50);
                        bt2.setPrefSize(140, 40);
                        bt2.setCursor(Cursor.HAND);
                        bt2.setStyle("-fx-background-color:red;"+"-fx-border-radius:20;"+"-fx-font-weight:bold;"
            +"-fx-text-fill:white;"+"-fx-font-size:18;"+"-fx-background-radius:22;");
              
                        p.getChildren().addAll(r1,win,bt1,bt2);
                        p.setCursor(Cursor.DEFAULT);
	                circle.setFill(null);
			b1.animation.stop();
                        b2.animation.stop();
		}
            if (circle_2.getCenterY()+radius>Bat_2.getY()+15) {
			animation.stop();
                        circle_2.setCenterX(Bat_2.getX()+Bat_long/2);
                        circle_2.setCenterY(Bat_2.getY()-10);

                        
		}
            
            

    
    
    //*****************************
        if (circle_2.getCenterX() > w-radius || circle_2.getCenterX() <w/2 + radius) {
             media.play();
      dx_1 *= -1; // Change ball move direction
    }
    if (circle_2.getCenterY() < radius ) {
         media.play();
      dy_1 *= -1; // Change ball move direction
    }
    

   

      if( Bat_2.getBoundsInParent().intersects(circle_2.getBoundsInParent()))
  {
Random r=new Random();
            dx_1=r.nextInt(3)-1;
            dy_1 *= -1; 
            
  }

      
    // Adjust ball position
  circle_2.setCenterX(circle_2.getCenterX()+dx_1);
  circle_2.setCenterY(circle_2.getCenterY()+dy_1);
    
     }
      //********************************
      if(pick==1){
          bricks_1.forEach((c) -> {
              if((circle.getCenterY()+radius==c.getY()||circle.getCenterY()-radius==c.getY()+brick_h) &&circle.getCenterX() >=c.getX()&&circle.getCenterX()<c.getX()+brick_w){
                  if(c.smash==1){
                      c.rect.setFill(new ImagePattern(brick_smash));
                      mediaplayer.play();
                      dy*=-1;
                      c.smash--;
                  }
                  else{
                      p.getChildren().remove(c.rect);
                      mediaplayer.play();
                      dy*=-1;
                      bricks_1.remove(c);
                      Score+=5;
                  }
              }
              else if((circle.getCenterX()+radius==c.getX()||circle.getCenterX()-radius==c.getX()+brick_w)&&circle.getCenterY()<=c.getY()+brick_h&&circle.getCenterY()>c.getY()){
                  if(c.smash==1){
                      c.rect.setFill(new ImagePattern(brick_smash));
                      mediaplayer.play();
                      dx*=-1;
                      c.smash--;
                  }
                  else{
                      p.getChildren().remove(c.rect);
                      mediaplayer.play();
                      dx*=-1;
                      bricks_1.remove(c);
                      Score+=5;
                  }
              }
              else if(c.getRect().getBoundsInParent().contains(circle.getBoundsInParent())){
                  if(c.smash==1){
                      c.rect.setFill(new ImagePattern(brick_smash));
                      mediaplayer.play();
                      dy*=-1;
                      dx*=-1;
                      c.smash--;
                  }
                  else{
                      p.getChildren().remove(c.rect);
                      mediaplayer.play();
                      dy*=-1;
                      dx*=-1;
                      bricks_1.remove(c);
                      Score+=5;
                  }
                  
                  
              }        });
            if (bricks_1.isEmpty()==true) {
                         Text win =new Text(w/2-w/4, h/2, "PLAYER 1 WIN !");
			win.setFill(Color.RED);
			win.setFont(new Font(100));
                        Rectangle r1 =new Rectangle(1365,767);
                        r1.setOpacity(0.4);
                        gamefinish=true;
                        bt1.setLayoutX(w/2-70);
                        bt1.setLayoutY(h-(h/3)+20);
                        bt1.setPrefSize(140, 40);
                        bt1.setCursor(Cursor.HAND);
                        bt1.setOnAction(e->{System.exit(0);});
                        bt1.setStyle("-fx-background-color:red;"+"-fx-border-radius:20;"+"-fx-font-weight:bold;"
            +"-fx-text-fill:white;"+"-fx-font-size:20;"+"-fx-background-radius:22;");
                        bt2.setLayoutX(w/2-70);
                        bt2.setLayoutY(h-h/3-50);
                        bt2.setPrefSize(140, 40);
                        bt1.setCursor(Cursor.HAND);
                        bt2.setStyle("-fx-background-color:red;"+"-fx-border-radius:20;"+"-fx-font-weight:bold;"
            +"-fx-text-fill:white;"+"-fx-font-size:20;"+"-fx-background-radius:22;");
                         /*bt2.setOnAction(e->{
                            JavaApplication1 main= new JavaApplication1();
                            mediaPlayer2.stop();
                            s2.close();
                            try {
                                main.start(new Stage());
                            } catch (Exception ex) {
                            }                       
                        });*/
                        p.getChildren().addAll(r1,win,bt1,bt2);
                        p.setCursor(Cursor.DEFAULT);
	                circle.setFill(null);
			b1.animation.stop();
                        b2.animation.stop();
		}
            if (circle.getCenterY()+radius>Bat_1.getY()+15) {
		
                animation.stop();

                        circle.setCenterX(Bat_1.getX()+Bat_long/2);
                        circle.setCenterY(Bat_1.getY()-10);

                        
			
		}
            
             // Check boundaries of pane
    if (circle.getCenterX() < radius || circle.getCenterX()>w/2 - radius) {
         media.play();
      dx *= -1; // Change ball move direction
    }
    if (circle.getCenterY()< radius ) {
         media.play();
      dy *= -1; // Change ball move direction
    }   
    
   if( Bat_1.getBoundsInParent().intersects(circle.getBoundsInParent()))
  {
            dy *= -1; 
            Random r=new Random();
            dx=r.nextInt(3)-1;
  }
            
   
    circle.setCenterX(circle.getCenterX()+dx);
    circle.setCenterY(circle.getCenterY()+dy);
    
     
  }
        
       } catch (Exception e) {
      }


  }
}
void move(){
    try {
        

    if(!bricks_1.isEmpty()||!bricks_2.isEmpty()){

         

    if(keys[4]==true&&Bat_1.getX()>=1)
        Bat_1.setX(Bat_1.getX()-1);
    if(keys[5]==true&&Bat_1.getX()+Bat_long<=w/2-1)
        Bat_1.setX(Bat_1.getX()+1);
    if(b1.animation.getStatus()!=Animation.Status.RUNNING)
        b1.circle.setCenterX(Bat_1.getX()+Bat_long/2);
    

    if(keys[1]==true&&Bat_2.getX()>=w/2+1)
        Bat_2.setX(Bat_2.getX()-1);
    if(keys[2]==true&&Bat_2.getX()+Bat_long<=w-1)
        Bat_2.setX(Bat_2.getX()+1);
    if(b2.animation.getStatus()!=Animation.Status.RUNNING)
        b2.circle_2.setCenterX(Bat_2.getX()+Bat_long/2);
        


}
    } catch (Exception e) {
    }
    
}
 
final void Build_bricks(){
    
  int t=40;
                       for (int i = 0; i < 5; i++) {
                          int s=40;
                            for (int j = 0; j < 5; j++) {
                          
                           
                             brick e=new brick(s, t);
                             bricks_1.add(e);
                                s+=100;
            }
                            int m=w/2+110;
                            for (int j = 0; j < 5; j++) {
                               
                         
                          
                             brick e=new brick(m, t);
                             bricks_2.add(e);
                                m+=100;
            }
            t+=42;
}
}
    //****** METHOD TO CREATE BACKGROUND ANIMATION ******\\
    

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
        
        b1.animation.stop();
        b2.animation.stop();
        b1.animation3.stop();
        b2.animation3.stop();

    }
}