package javaapplication1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Mode3 {

  MediaPlayer mediaPlayer2;
    Media mus = new Media(new File("damaged.mp3").toURI().toString());
    MediaPlayer damaged =new MediaPlayer(mus);  
    Media mus2= new Media(new File("hit.mp3").toURI().toString());
    MediaPlayer hit= new MediaPlayer(mus2);
    Media mus3= new Media(new File("shot.mp3").toURI().toString());
    MediaPlayer shot= new MediaPlayer(mus3);
    Media mus4 = new Media(new File("game_over.mp3").toURI().toString());
    MediaPlayer GameOver =new MediaPlayer(mus4);
    Media mus5 = new Media(new File("win.mp3").toURI().toString());
    MediaPlayer win =new MediaPlayer(mus5);  
    Timeline timer;
    Timeline timeline;
    Pane root = new Pane();
    Circle c = new Circle();
    boolean right = true;
    Text lives;
    Text score;
    Text accuracy;
    int numScore = 0;
    List<ImageView> monsters = new ArrayList<ImageView>();
    List<Circle> monsterShoots = new ArrayList<Circle>();
    List<Circle> playerShoots = new ArrayList<Circle>();
    ImageView player;
    int numLives = 2;
    double numShots=0;
    double numHits=0;
    int timer_bullet=0;      
    int acc=0;  
    boolean gmfinished=false;
    private boolean [] keys = new boolean[3];
     Button menu= new Button("Main Menu");

 
    
    public  Mode3(){
        
    
        
       Image img= new Image("file:space.jpg");
       ImageView im2= new ImageView(img);
       im2.setFitWidth(1400);
        Media mus2 = new Media(new File("background.mp3").toURI().toString());
        mediaPlayer2 = new MediaPlayer(mus2);
        mediaPlayer2.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer2.play();
        accuracy = new Text("Shoot Accuracy: 0%");       
        accuracy.setLayoutX(530);
        accuracy.setLayoutY(30);
        accuracy.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 20));
        accuracy.setFill(Color.BLUE);
        lives = new Text("Lives: 2");
        lives.setLayoutX(20);
        lives.setLayoutY(30);
        lives.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 20));
        lives.setFill(Color.BLUE);
        score = new Text("Score: 0");
        score.setLayoutX(1200);
        score.setLayoutY(30);
        score.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 20));
        score.setFill(Color.BLUE);
        root.setCursor(Cursor.NONE);
        root.getChildren().addAll(im2,lives, score,accuracy);
        c.setLayoutX(0);
       
        //creating player
        player = player();
        root.getChildren().add(player);
       
        //create monsters
       
       try {
            
      
        int w=40;
        for(int i = 0; i < 13; i++){
            monsters.add(monster(w, 80));
            w+=100;
                    
            root.getChildren().add(monsters.get(i));
        }
        w=40;
        for(int i = 0; i < 13; i++){
            monsters.add(monster(w, 180));
            w+=100;
            root.getChildren().add(monsters.get(i + 13));
        }
         w=40;
        for(int i = 0; i < 13; i++){
            monsters.add(monster(w, 280));
            w+=100;
            root.getChildren().add(monsters.get(i + 26));
        }
        w=80;
        for(int i = 0; i < 9; i++) {
            if(w<root.getLayoutY()-90)
            {
                continue;
            }
            monsters.add(monster(w, 380));
            w=w+(int)(Math.random()*120)+70;
            root.getChildren().add(monsters.get(i + 39));
            
          }
          } catch (Exception e) {
          }
        
  
         timer = new Timeline(new KeyFrame(Duration.millis(30), event -> {
           UpdateGame();
        }));
        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();
       
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
            
                int monstershoot = (int)(Math.random()*monsters.size()-1)+0;
                monsterShoots.add(shoot(monsters.get(monstershoot).getLayoutX() + 25, monsters.get(monstershoot).getLayoutY() + 25,Color.RED));
                root.getChildren().add(monsterShoots.get(monsterShoots.size() - 1));               
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
       
     
        
        shot.setOnEndOfMedia(()->{shot.stop();});
        root.setOnKeyPressed(e->{
            if(e.getCode()==KeyCode.LEFT){
                keys[0]=true;
            }
            if(e.getCode()==KeyCode.RIGHT){
                keys[1]=true;
            }
            if(e.getCode()==KeyCode.SPACE){            
                keys[2]=true;               
            }
        
        });
        root.setOnKeyReleased(e->{
            if(e.getCode()==KeyCode.LEFT){
                keys[0]=false;
            }
            if(e.getCode()==KeyCode.RIGHT){
                keys[1]=false;
            }
            if(e.getCode()==KeyCode.SPACE){
                keys[2]=false;
            }
   
        });

    }
   
    public void UpdateGame() {
        
        playerShoot();
        PlayerDestroyed();       
        MonstersMove();
        MonsterDestroyed();
        monstersShoot();
        win();
        lost();
        
        
        if(keys[1]==true&&player.getLayoutX()<root.getWidth()-60)
           player.setLayoutX(player.getLayoutX()+5);            
            
        if(keys[0]==true&&player.getLayoutX()>10)
          player.setLayoutX(player.getLayoutX()-5);  
               
            
            if(keys[2]==true && timer_bullet>150){
       playerShoot(player.getLayoutX());
        shot.play();
        numShots++;
          timer_bullet=0;
            }
        timer_bullet+=10;
    }
   
   
    public void MonstersMove() {
        double movement;
 
        if(right)
            movement= 0.6;
        else
            movement = - 0.6;
       
        if(c.getLayoutX() >= 40) 
            right = false;
                   
        if(c.getLayoutX() <= -20) 
            right = true;           
       
        for(int i = 0; i < monsters.size(); i++) {
            monsters.get(i).setLayoutX(monsters.get(i).getLayoutX() + movement);
        }
        c.setLayoutX(c.getLayoutX() + movement);
    }
   
    public ImageView player() {
        Image him= new Image("file:player.png");
        ImageView img= new ImageView(him);
        img.setLayoutX(225);
        img.setLayoutY(650);
        img.setFitHeight(50);
        img.setFitWidth(50);
        return img;
    }
    public ImageView monster(double x, double y) {
        Image him= new Image("file:monster.png");
        ImageView img= new ImageView(him);
        img.setLayoutX(x);
        img.setLayoutY(y);
        img.setFitHeight(50);
        img.setFitWidth(50);
        return img;
    }
   
    public Circle shoot(double x, double y,Color color) {
        Circle c = new Circle();
        c.setFill(color);
        c.setLayoutX(x);
        c.setLayoutY(y);c.setRadius(3);
        return c;
    }
    public void playerShoot(double x) {
        playerShoots.add(shoot((x + 25), 650,Color.BLUE));
        root.getChildren().add(playerShoots.get(playerShoots.size() - 1));
    }
   
 
private void PlayerDestroyed() {
    try {
        
    damaged.setOnEndOfMedia(()->{damaged.stop();});
    for(int i = 0; i < monsterShoots.size(); i ++) {
       
            if(        ((monsterShoots.get(i).getLayoutX() > player.getLayoutX())
                    && ((monsterShoots.get(i).getLayoutX() < player.getLayoutX() + 50))
                    && ((monsterShoots.get(i).getLayoutY() > player.getLayoutY())
                    && ((monsterShoots.get(i).getLayoutY() < player.getLayoutY() + 50))))) {
                       root.getChildren().remove(monsterShoots.get(i));
                       monsterShoots.remove(i); 
                       //player.setLayoutX(225);
                       numLives--;
                       
                       damaged.play();
                       lives.setText("Lives: " + String.valueOf(numLives));
       
    }
        }
    } catch (Exception e) {
    }
}

 private void playerShoot() {
        
        try {
            
                      

        if(!playerShoots.isEmpty()) {
            for(int i = 0; i < playerShoots.size(); i++) {
                playerShoots.get(i).setLayoutY(playerShoots.get(i).getLayoutY() - 3);
                
                if(playerShoots.get(i).getLayoutY() <= 0) {
                    root.getChildren().remove(playerShoots.get(i));
                    playerShoots.remove(i);
                 
                }
            }
        }
         } catch (Exception e) {
}
    }
    
private void monstersShoot() {
       
    try {
   
        if(!monsterShoots.isEmpty()) {
            for(int i = 0; i < monsterShoots.size(); i ++) {
                monsterShoots.get(i).setLayoutY(monsterShoots.get(i).getLayoutY() + 3);
                if(monsterShoots.get(i).getLayoutY() <= 0) {
                    root.getChildren().remove(monsterShoots.get(i));
                    monsterShoots.remove(i); 
                }
            }
        }
             
    } catch (Exception e) {
    }
    }

private void MonsterDestroyed() {
    try {
        
   hit.setOnEndOfMedia(()->{hit.stop();});
    for(int i = 0; i < playerShoots.size(); i ++) {
        for(int j = 0; j < monsters.size(); j++) {
            if(((playerShoots.get(i).getLayoutX() > monsters.get(j).getLayoutX())
                    &&((playerShoots.get(i).getLayoutX() < monsters.get(j).getLayoutX() + 50))
                    &&((playerShoots.get(i).getLayoutY() > monsters.get(j).getLayoutY())
                    &&((playerShoots.get(i).getLayoutY() < monsters.get(j).getLayoutY() + 50))))){
                        root.getChildren().remove(monsters.get(j));
                        monsters.remove(j);
                        root.getChildren().remove(playerShoots.get(i));
                        playerShoots.remove(i);
                        numScore += 50;
                        hit.play();
           
                        score.setText("Score: " + String.valueOf(numScore));                       
                        numHits++;
                        if(numShots==0)                         
                            accuracy.setText("Shoot Accuracy: 0%");
                        else {acc=(int) ((numHits/numShots)*100);
                            accuracy.setText("Shoot Accuracy: " + String.valueOf(acc)+"%");}
        }
    }
        }
     } catch (Exception e) {
    }
}

public void win(){
    win.setOnEndOfMedia(()->{win.stop();});
    if(monsters.isEmpty()) {
        mediaPlayer2.stop();
        win.play();
        gmfinished=true;
        root.setCursor(Cursor.DEFAULT);
       menu.setLayoutX(560);
        menu.setLayoutY(370);
        menu.setPrefSize(180, 20);
        menu.setCursor(Cursor.HAND);
        menu.setStyle("-fx-background-color:red;"+"-fx-border-radius:16;"
                                +"-fx-font-weight:bold;"+"-fx-text-fill:white;"
                                +"-fx-font-size:16;"+"-fx-background-radius:22;");

        Button exit= new Button("Exit");
        exit.setLayoutX(560);
        exit.setLayoutY(440);
        exit.setPrefSize(180, 20);
        exit.setCursor(Cursor.HAND);
        exit.setStyle("-fx-background-color:red;"+"-fx-border-radius:20;"
                                +"-fx-font-weight:bold;"+"-fx-text-fill:white;"
                                +"-fx-font-size:16;"+"-fx-background-radius:22;");
        exit.setOnAction(e2 ->{System.exit(0);});
       // lives.setFill(Color.WHITE);
        //accuracy.setFill(Color.WHITE);
       // score.setFill(Color.WHITE);
        Rectangle r1 =new Rectangle(root.getWidth(),root.getHeight());
        r1.setOpacity(0.4);
        Text text = new Text("YOU WIN!");
        text.setFont(new Font(100));
        text.setX(420);
        text.setY(300);    
        text.setFill(Color.GREEN); 
        root.getChildren().clear();
        root.getChildren().addAll(r1,text,accuracy,lives,score,menu,exit);
        timer.stop();
        timeline.stop();
    }
}
 
public void lost(){
    GameOver.setOnEndOfMedia(()->{GameOver.stop();});
    if(numLives <= 0) {
        mediaPlayer2.stop();
        GameOver.play();
        gmfinished=true;
        root.setCursor(Cursor.DEFAULT);
        menu.setLayoutX(560);
        menu.setLayoutY(370);
        menu.setPrefSize(180, 20);
        menu.setCursor(Cursor.HAND);
        menu.setStyle("-fx-background-color:red;"+"-fx-border-radius:20;"
                                +"-fx-font-weight:bold;"+"-fx-text-fill:white;"
                                +"-fx-font-size:16;"+"-fx-background-radius:22;");

        
        Button exit= new Button("Exit");
        exit.setLayoutX(560);
        exit.setLayoutY(440);
        exit.setPrefSize(180, 20);
        exit.setCursor(Cursor.HAND);
        exit.setStyle("-fx-background-color:red;"+"-fx-border-radius:20;"
                                +"-fx-font-weight:bold;"+"-fx-text-fill:white;"
                                +"-fx-font-size:16;"+"-fx-background-radius:22;");
        exit.setOnAction(e ->{System.exit(0);});
        lives.setFill(Color.RED);
       // accuracy.setFill(Color.WHITE);
       // score.setFill(Color.WHITE);
        Rectangle r1 =new Rectangle(root.getWidth(),root.getHeight());
        r1.setOpacity(0.4);
        Text text = new Text("GAME OVER");
        text.setFont(new Font(100));
        text.setX(350);
        text.setY(300);    
        text.setFill(Color.RED);
        text.setStrokeWidth(3);;        
        root.getChildren().clear();
        root.getChildren().addAll(r1,text,accuracy,lives,score,menu,exit);
        timer.stop();
        timeline.stop();
    }
}

void stop()
{
    timeline.stop();
    timer.stop();
    mediaPlayer2.stop();
    
    
}
 
}