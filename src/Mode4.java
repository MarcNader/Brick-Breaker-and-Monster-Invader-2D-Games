/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;




import java.io.File;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javafx.util.Duration;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Mode4 extends Pane {
   

    int timer, timer2,timer3,timer4;
          int w=1365 , h=767, score,score_2;
          
      int game_end=90;
            MediaPlayer mediaPlayer2;

   private Label scores = new Label();
   private Label scores_2 = new Label();
    private Bullet bul;
    private Bullet bul2;
     private Bullet bul_2;
    private Bullet bul2_2;
    ArrayList<Bullet>bullets= new ArrayList<>();
    ArrayList<Bullet>bullet2= new ArrayList<>();
        ArrayList<Bullet>bullets_2= new ArrayList<>();
    ArrayList<Bullet>bullet2_2= new ArrayList<>();
    private Image background = new Image(new File("space.jpg").toURI().toString());
    private ImageView imv = new ImageView(background);
    public Group root = new Group();
    private Enemy en;
    private FireArm fire;
     private Enemy en_2;
    private FireArm fire_2;
    private AudioClip aud, aud2;
     Line l =new Line(w/2, 0, w/2, h);

     Timeline tl;
     Timeline time_up;
    private boolean [] keys = new boolean[6];
    boolean gmfinish=false;
         Button menu= new Button("Main Menu");
 public Mode4() {
             Media mus2 = new Media(new File("background.mp3").toURI().toString());
        mediaPlayer2 = new MediaPlayer(mus2);
        mediaPlayer2.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer2.play();
     this.setCursor(Cursor.NONE);
   
         l.setStroke(Color.WHITE);
         l.setStrokeWidth(4);
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

        imv.setFitWidth(w);
        imv.setFitHeight(h);
        root.getChildren().add(imv);
        this.getChildren().addAll(root,l);
        
        en= new Enemy(0,w/2-40);
        en_2=new Enemy(w/2,w/2-40);
        
  Text time= new Text("time:"+game_end);
  time.setFill(Color.WHITE);
    time.setFont(new Font(20));
        time.setX(w/2+2);
        time.setY(25);   
        
      scores.setLayoutY(h-60);
      scores.setFont(Font.font(20));
      scores.setText("Score:"+score);
      
        scores_2.setLayoutY(h-60);
        scores_2.setLayoutX(w-90);
      scores_2.setFont(Font.font(20));
      scores_2.setText("Score:"+score_2);
      
         fire= new FireArm(1);
         fire_2= new FireArm(2);
        //*************
        this.getChildren().addAll(en,en_2,fire,fire_2,scores,scores_2,time,txt_1,txt_2);
        
        //************
        
       
        
        this.setOnKeyPressed(e->{
            if(e.getCode()==KeyCode.A){
                keys[0]=true;
            }
            if(e.getCode()==KeyCode.D){
                keys[1]=true;
            }
            if(e.getCode()==KeyCode.W){
                keys[2]=true;
            } 
            if(e.getCode()==KeyCode.LEFT){
                keys[3]=true;
            }
            if(e.getCode()==KeyCode.RIGHT){
                keys[4]=true;
            }
            if(e.getCode()==KeyCode.L){
                keys[5]=true;
            }
        
        });
        this.setOnKeyReleased(e->{
            if(e.getCode()==KeyCode.A){
                keys[0]=false;
            }
            if(e.getCode()==KeyCode.D){
                keys[1]=false;
            }
            if(e.getCode()==KeyCode.W){
                keys[2]=false;
            }
            if(e.getCode()==KeyCode.LEFT){
                keys[3]=false;
            }
            if(e.getCode()==KeyCode.RIGHT){
                keys[4]=false;
            }
            if(e.getCode()==KeyCode.L){
                keys[5]=false;
            }
   
        });
        
//             aud2= new AudioClip(new File("Sounds//Zeriba.mp3").toURI().toString());
//             aud2.setCycleCount(AudioClip.INDEFINITE);
//            aud2.play();
        

        KeyFrame kf = new KeyFrame(Duration.millis(40),
                e->
                {
                        updategame();
                    
                }
        );
        tl = new Timeline(kf);
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
        
 time_up =new Timeline(new KeyFrame(Duration.seconds(1), ev ->{
    
   game_end--;
   time.setText("time:"+game_end);
   if(game_end==0){
    this.setCursor(Cursor.DEFAULT);
    tl.stop();
    gmfinish=true;
this.getChildren().remove(time);

   if(score>score_2){
        Text text = new Text("PLAYER1 WINS!");
        text.setFont(new Font(100));
        text.setX(w/2-250);
        text.setY(h/2-100);    
        text.setFill(Color.GREEN);
        text.setStrokeWidth(3);
        text.setStroke(Color.GOLD); 
        this.getChildren().add(text);
   }
   else if(score<score_2){
       Text text = new Text("PLAYER2 WINS!");
        text.setFont(new Font(100));
        text.setX(w/2-250);
        text.setY(h/2-100);    
        text.setFill(Color.GREEN);
        text.setStrokeWidth(3);
        text.setStroke(Color.GOLD); 
                this.getChildren().add(text);

   }
        else{
                Text text = new Text("Draw");
        text.setFont(new Font(100));
        text.setX(w/2-200);
        text.setY(h/2-100);    
        text.setFill(Color.GREEN);
        text.setStrokeWidth(3);
        text.setStroke(Color.GOLD); 
                this.getChildren().add(text);

                }
       menu.setLayoutX(600);
        menu.setLayoutY(370);
        menu.setPrefSize(180, 20);
        menu.setCursor(Cursor.HAND);
        menu.setStyle("-fx-background-color:red;"+"-fx-border-radius:16;"
                                +"-fx-font-weight:bold;"+"-fx-text-fill:white;"
                                +"-fx-font-size:16;"+"-fx-background-radius:22;");

        Button exit= new Button("Exit");
        exit.setLayoutX(600);
        exit.setLayoutY(440);
        exit.setPrefSize(180, 20);
        exit.setCursor(Cursor.HAND);
        exit.setStyle("-fx-background-color:red;"+"-fx-border-radius:20;"
                                +"-fx-font-weight:bold;"+"-fx-text-fill:white;"
                                +"-fx-font-size:16;"+"-fx-background-radius:22;");
        exit.setOnAction(e2 ->{System.exit(0);});
 this.getChildren().addAll(menu,exit);
   }
                   
}));
time_up.setCycleCount(Timeline.INDEFINITE);
time_up.play();
    
    }
        public void updategame(){
            
            
             if(keys[0]==true&&fire.getX()>0){
               fire.setX(fire.getX()-8);
            }
            if(keys[1]==true && fire.getX()+40<w/2){
               fire.setX(fire.getX()+8);
  
            }
            if(keys[2]==true && timer >50){
       
                bul2= new Bullet(fire.getX()+20, fire.getY(), Color.RED);
                //aud= new AudioClip(new File("Sounds//Laser.mp3").toURI().toString());
               this.getChildren().addAll(bul2);
              // aud.play();
               bullets.add(bul2);
              timer=0;
            }
      
            
            if(keys[3]==true&&fire_2.getX()>w/2){
               fire_2.setX(fire_2.getX()-8);
            }
            if(keys[4]==true && fire_2.getX()+40<w){
               fire_2.setX(fire_2.getX()+8);
  
            }
            if(keys[5]==true && timer3 >50){
       
                bul2_2= new Bullet(fire_2.getX()+20, fire_2.getY(), Color.RED);
                //aud= new AudioClip(new File("Sounds//Laser.mp3").toURI().toString());
               this.getChildren().addAll(bul2_2);
              // aud.play();
               bullets_2.add(bul2_2);
              timer3=0;
            }
           /* if(fire.getBoundsInParent().intersects(bul.getBoundsInParent())){
             Rectangle r1 =new Rectangle(800, 800);
             r1.setOpacity(0.9);  
             Text t1=new Text("Game Over"); t1.setX(w/2-100);t1.setY(h/2);t1.setFill(Color.RED);t1.setFont(Font.font(70));
             Text t2=new Text("Your Score:"+ score); t2.setX(w/2-100);t2.setY((h/2)+100);t2.setFill(Color.RED);t2.setFont(Font.font(70));
             
             this.getChildren().addAll(r1,t1,t2);
             tl.stop();
            }*/
          
             if(timer2>150){
                        bul=new Bullet(en.getX()+25, en.getY()+51, Color.GOLD);
                        bullet2.add(bul);
                     //   bullet2_2.add(bul);
                         this.getChildren().add(bul);

                        

                      timer2=0;
            } 
                  if(timer4>150){
                              bul_2=new Bullet(en_2.getX()+25, en_2.getY()+51, Color.GOLD);
                        bullet2_2.add(bul_2);
                     //   bullet2_2.add(bul);
                         this.getChildren().add(bul_2);
                         timer4=0;
                  }     
      
    
try {
             
             for (Bullet bul : bullet2) {
                    
                  bul.setY(bul.getY()+15);// move yellow balls
                   
              if(fire.getBoundsInParent().intersects(bul.getBoundsInParent())){
               
                  score-=3;
                     scores.setText("Score:"+score);
                     this.getChildren().remove(bul);
                     bullet2.remove(bul);
            
            }
                            /// remove balls  exceeds height limit
                           if(bul.getY()+10> h){
                               bullet2.remove(bul);
                               this.getChildren().remove(bul);
                               
                           }
            
                }
             
       for (Bullet bul_2 : bullet2_2) {
                    
                  bul_2.setY(bul_2.getY()+15);// move yellow balls
                   
              if(fire_2.getBoundsInParent().intersects(bul_2.getBoundsInParent())){
               
                  score_2 -=3;
                     scores_2.setText("Score:"+score_2);
                this.getChildren().remove(bul_2);
                      bullet2_2.remove(bul_2);

            }
                            /// remove balls  exceeds height limit
                           if(bul_2.getY()+10> h){
                               bullet2_2.remove(bul_2);
                               this.getChildren().remove(bul_2);
                               
                           }
            
                }
           
                
  
                   for (Bullet bul2_2 : bullets_2) {
                    
                  bul2_2.setY(bul2_2.getY()-15);
                   
              if(en_2.getBoundsInParent().intersects(bul2_2.getBoundsInParent())){
               
                  score_2+=5;
                     scores_2.setText("Score:"+score_2);
                      this.getChildren().removeAll(en_2,bul2_2);
                    
                    en_2= new Enemy(w/2,w/2-50);
                            
                    this.getChildren().add(en_2);
            
            }
                            /// remove balls  exceeds height limit
                           if(bul2_2.getY()< 0){
                               bullets_2.remove(bul2_2);
                               this.getChildren().remove(bul2_2);
                               
                           }
            
                }

                   
                   for (Bullet bul2 : bullets) {
                    
                  bul2.setY(bul2.getY()-15);
                   
              if(en.getBoundsInParent().intersects(bul2.getBoundsInParent())){
               
                  score+=5;
                     scores.setText("Score:"+score);
                      this.getChildren().removeAll(en,bul2);
                    
                    en= new Enemy(0,w/2-50);
                            
                    this.getChildren().add(en);
            
            }
                            /// remove balls  exceeds height limit
                           if(bul2.getY()< 0){
                               bullets.remove(bul2);
                               this.getChildren().remove(bul2);
                               
                           }
            
                }
         } catch (Exception e) {
            }
       
                           
                        
     // if(bul!=null )
       //        bul.setY(bul.getY()+10);
      
      timer2+=5;
       timer+=5;
       timer3+=5;
       timer4+=5;
        }
        
        
        void stop(){
            time_up.stop();
            tl.stop();
            mediaPlayer2.stop();
        }
       
 }
 


 class Bullet extends Rectangle{

  
  public Bullet(double x, double y,Color c){
      
      setX(x);
      setY(y);
       setWidth(15);
      setHeight(5);
      setArcHeight(10);
      setArcWidth(10);
     setRotate(90);
      setFill(c);
    
  }
  

  }






 class Enemy extends ImageView{ 
     private Image fim;
    public Enemy(double s,double x){
         fim = new Image(new File("monster.png").toURI().toString());
         
         this.setImage(fim);
        this.setX(s+Math.random()*x);
        this.setY(Math.random()*400);
  this.setFitHeight(50);
  this.setFitWidth(50);

    }

    
 }
    
 
    
class FireArm extends ImageView{
private Image fim;
    public FireArm(int sel){
         fim = new Image(new File("player.png").toURI().toString());
         this.setImage(fim);
         if(sel==1){
        this.setX(400);
        this.setY(700);}
         else{
             this.setX(980);
             this.setY(700);
             
         }
  this.setFitHeight(50);
  this.setFitWidth(50);
        
    }
    

    
}

