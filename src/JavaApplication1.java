package javaapplication1;

import java.io.File;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JavaApplication1 extends Application {

    MediaPlayer mediaPlayer2;
public static void main(String[] args) {	launch(args);}
        
    @Override
    public void start(Stage primaryStage) throws Exception {
        Media mus2 = new Media(new File("back.mp3").toURI().toString());
        mediaPlayer2 = new MediaPlayer(mus2);
        mediaPlayer2.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer2.play();
  Pane r=new Pane();
ImageView bk= new ImageView("file:d.jpeg");
    
        Text txt =new Text(50, 767/2,"Destroy !");
        txt.setFill(Color.RED);
        txt.setStroke(Color.WHITE);
        txt.setStrokeWidth(4);
        txt.setFont(Font.font(120));
        txt.setRotate(-35);
                FadeTransition fd=new FadeTransition(Duration.seconds(2), txt);
        fd.setAutoReverse(true);
        fd.setCycleCount(50);
        fd.setFromValue(10);  
        fd.setToValue(0.1);  
        fd.play();
Button n1=new Button("Break It");
n1.setTranslateX(900);
n1.setTranslateY(230);
n1.setPrefSize(220, 30);
n1.setOnMouseEntered(e ->{});
n1.setCursor(Cursor.HAND);


Button mod=new Button("1 vs 1");
mod.setTranslateX(900);
mod.setTranslateY(310);
mod.setPrefSize(220, 30);
mod.setCursor(Cursor.HAND);

Button mode3= new Button("Kill It");
mode3.setTranslateX(900);
mode3.setTranslateY(390);
mode3.setPrefSize(220, 30);
mode3.setCursor(Cursor.HAND);

Button mode4= new Button("1 vs 1");
mode4.setTranslateX(900);
mode4.setTranslateY(470);
mode4.setPrefSize(220, 30);
mode4.setCursor(Cursor.HAND);

Button b1= new Button("Exit");
b1.setTranslateX(945);
b1.setTranslateY(550);
b1.setPrefSize(140, 30);
b1.setCursor(Cursor.HAND);
b1.setOnAction(e->{System.exit(0);});
      
mod.setOnAction(e-> {
    Mode2 n= new Mode2();
    r.getChildren().clear();
    r.getChildren().add(n.p);
    n.p.requestFocus();
   primaryStage.getScene().setOnMouseMoved(es ->{
           if(n.gamefinish==true)
               n.bt2.setOnAction(v -> {
                   r.getChildren().clear();
                   r.getChildren().addAll(bk,txt,n1,mod,mode3,mode4,b1);              
               
               });  
         
      }); 
     primaryStage.getScene().setOnKeyPressed(ev ->{
           
           if(ev.getCode()==KeyCode.Q){
               n.stop();
            r.getChildren().clear();
             r.getChildren().addAll(bk,txt,n1,mod,mode3,mode4,b1);
           }
           });
});

mode3.setOnAction(e-> {
    mediaPlayer2.stop();
    Mode3 m= new Mode3();
    r.getChildren().clear();
    r.getChildren().add(m.root);
    m.root.requestFocus();
    primaryStage.getScene().setOnMouseMoved(e2 ->{
           if(m.gmfinished==true)
               m.menu.setOnAction(v -> {
                   r.getChildren().clear();
               m.stop();
       mediaPlayer2.play();
                   r.getChildren().addAll(bk,txt,n1,mod,mode3,mode4,b1);              
               
               });     
primaryStage.getScene().setOnKeyPressed(ev ->{
           
           if(ev.getCode()==KeyCode.Q){
               m.stop();
            r.getChildren().clear();
             r.getChildren().addAll(bk,txt,n1,mod,mode3,mode4,b1);
             mediaPlayer2.play();
           }
           });
    }); 
});
Button n2=new Button("Level 1");
n2.setTranslateX(900);
n2.setTranslateY(230);
n2.setPrefSize(120, 30);
Button n3=new Button("Level 2");
n3.setTranslateX(900);
n3.setTranslateY(280);
n3.setPrefSize(120, 30);
Button n4=new Button("Level 3");
n4.setTranslateX(900);
n4.setTranslateY(330);
n4.setPrefSize(120, 30);
n4.setCursor(Cursor.HAND);
Button n6=new Button("Level 4");
n6.setTranslateX(900);
n6.setTranslateY(380);
n6.setPrefSize(120, 30);
n6.setCursor(Cursor.HAND);

Button n7=new Button("Level 5");
n7.setTranslateX(900);
n7.setTranslateY(430);
n7.setPrefSize(120, 30);
n7.setCursor(Cursor.HAND);

Button n8=new Button("Level 6");
n8.setTranslateX(900);
n8.setTranslateY(480);
n8.setPrefSize(120, 30);
n8.setCursor(Cursor.HAND);

Button n9=new Button("Level 7");
n9.setTranslateX(900);
n9.setTranslateY(530);
n9.setPrefSize(120, 30);
n9.setCursor(Cursor.HAND);

Button n5=new Button("Back");
n5.setTranslateX(900);
n5.setTranslateY(580);
n5.setPrefSize(120, 30);
n5.setCursor(Cursor.HAND);


n1.setStyle("-fx-background-color:red;"+"-fx-border-radius:20;"+"-fx-font-weight:bold;"
            +"-fx-text-fill:white;"+"-fx-font-size:20;"+"-fx-background-radius:22;");
mode3.setStyle("-fx-background-color:red;"+"-fx-border-radius:20;"+"-fx-font-weight:bold;"
            +"-fx-text-fill:white;"+"-fx-font-size:20;"+"-fx-background-radius:22;");
b1.setStyle("-fx-background-color:red;"+"-fx-border-radius:20;"+"-fx-font-weight:bold;"
            +"-fx-text-fill:white;"+"-fx-font-size:18;"+"-fx-background-radius:22;");
n2.setStyle("-fx-background-color:red;"+"-fx-border-radius:20;"+"-fx-font-weight:bold;"
            +"-fx-text-fill:white;"+"-fx-font-size:20;"+"-fx-background-radius:22;");
n3.setStyle("-fx-background-color:red;"+"-fx-border-radius:20;"+"-fx-font-weight:bold;"
            +"-fx-text-fill:white;"+"-fx-font-size:20;"+"-fx-background-radius:22;");
n4.setStyle("-fx-background-color:red;"+"-fx-border-radius:20;"+"-fx-font-weight:bold;"
            +"-fx-text-fill:white;"+"-fx-font-size:20;"+"-fx-background-radius:22;");
n5.setStyle("-fx-background-color:red;"+"-fx-border-radius:20;"+"-fx-font-weight:bold;"
            +"-fx-text-fill:white;"+"-fx-font-size:20;"+"-fx-background-radius:22;");
n6.setStyle("-fx-background-color:red;"+"-fx-border-radius:20;"+"-fx-font-weight:bold;"
            +"-fx-text-fill:white;"+"-fx-font-size:20;"+"-fx-background-radius:22;");
n7.setStyle("-fx-background-color:red;"+"-fx-border-radius:20;"+"-fx-font-weight:bold;"
            +"-fx-text-fill:white;"+"-fx-font-size:20;"+"-fx-background-radius:22;");
n8.setStyle("-fx-background-color:red;"+"-fx-border-radius:20;"+"-fx-font-weight:bold;"
            +"-fx-text-fill:white;"+"-fx-font-size:20;"+"-fx-background-radius:22;");
n9.setStyle("-fx-background-color:red;"+"-fx-border-radius:20;"+"-fx-font-weight:bold;"
            +"-fx-text-fill:white;"+"-fx-font-size:20;"+"-fx-background-radius:22;");
mod.setStyle("-fx-background-color:red;"+"-fx-border-radius:20;"+"-fx-font-weight:bold;"
            +"-fx-text-fill:white;"+"-fx-font-size:20;"+"-fx-background-radius:22;");
mode4.setStyle("-fx-background-color:red;"+"-fx-border-radius:20;"+"-fx-font-weight:bold;"
            +"-fx-text-fill:white;"+"-fx-font-size:20;"+"-fx-background-radius:22;");
n2.setCursor(Cursor.HAND);
n3.setCursor(Cursor.HAND);

mode4.setOnAction( eve ->{
    
    Mode4 m4 = new Mode4();
    mediaPlayer2.stop();
r.getChildren().clear();
r.getChildren().add(m4);
m4.requestFocus();
primaryStage.getScene().setOnMouseMoved(e2 ->{
           if(m4.gmfinish==true)
               m4.menu.setOnAction(v -> {
                   r.getChildren().clear();
               m4.stop();
mediaPlayer2.play();
                   r.getChildren().addAll(bk,txt,n1,mod,mode3,mode4,b1);              
               
               });     
           }); 
 primaryStage.getScene().setOnKeyPressed(ev ->{
           
           if(ev.getCode()==KeyCode.Q){
               m4.stop();
            r.getChildren().clear();
             r.getChildren().addAll(bk,txt,n1,mod,mode3,mode4,b1);
                mediaPlayer2.play();

           }
           });
});
 
        r.getChildren().addAll(bk,txt,n1,mod,mode3,mode4,b1);

        
 n1.setOnAction(g ->{
            r.getChildren().removeAll(n1,b1,mod,mode3,mode4);

                 r.getChildren().addAll(n2,n3,n4,n5,n6,n7,n8,n9);

   n3.setOnAction(level2 ->{Mode1 f1=new Mode1(2);
       r.getChildren().clear();
       r.getChildren().add(f1.p);
       primaryStage.getScene().setOnMouseMoved(e2 ->{
           if(f1.gamefinished==true)
               f1.bt2.setOnAction(v -> {
                   r.getChildren().clear();
                   r.getChildren().addAll(bk,txt,n1,b1,mod,mode3,mode4);              
               });    
      

       });
          
            primaryStage.getScene().setOnKeyPressed(ev ->{
           
           if(ev.getCode()==KeyCode.Q){
              f1.stop();
            r.getChildren().clear();
             r.getChildren().addAll(bk,txt,n1,mod,mode3,mode4,b1);
             
           }
           });
   });    
   
   
  n2.setOnAction(level1 ->{Mode1 f1=new Mode1(1);
       r.getChildren().clear();
       r.getChildren().add(f1.p);
       primaryStage.getScene().setOnMouseMoved(e2 ->{
           if(f1.gamefinished==true)
               f1.bt2.setOnAction(v -> {
                   r.getChildren().clear();
                   r.getChildren().addAll(bk,txt,n1,b1,mod,mode3,mode4);              
               });                  
       });
           
            primaryStage.getScene().setOnKeyPressed(ev ->{
           
           if(ev.getCode()==KeyCode.Q){
              f1.stop();
            r.getChildren().clear();
             r.getChildren().addAll(bk,txt,n1,mod,mode3,mode4,b1);
             
           }
           });
   });    
       
   n4.setOnAction(level3 ->{Mode1 f1=new Mode1(3);
       r.getChildren().clear();
       r.getChildren().add(f1.p);
       primaryStage.getScene().setOnMouseMoved(e2 ->{
           if(f1.gamefinished==true)
               f1.bt2.setOnAction(v -> {
                     r.getChildren().clear();
                   r.getChildren().addAll(bk,txt,n1,b1,mod,mode3,mode4);              
               });                  
       });
           
            primaryStage.getScene().setOnKeyPressed(ev ->{
           
           if(ev.getCode()==KeyCode.Q){
              f1.stop();
            r.getChildren().clear();
             r.getChildren().addAll(bk,txt,n1,mod,mode3,mode4,b1);
             
           }
           });
   });    
   
   n6.setOnAction(level4 ->{Mode1 f1=new Mode1(4);
       r.getChildren().clear();
       r.getChildren().add(f1.p);
       primaryStage.getScene().setOnMouseMoved(e2 ->{
           if(f1.gamefinished==true)
               f1.bt2.setOnAction(v -> {
                    r.getChildren().clear();
                   r.getChildren().addAll(bk,txt,n1,b1,mod,mode3,mode4);              
               });                  
       });
                primaryStage.getScene().setOnKeyPressed(ev ->{
           
           if(ev.getCode()==KeyCode.Q){
              f1.stop();
            r.getChildren().clear();
             r.getChildren().addAll(bk,txt,n1,mod,mode3,mode4,b1);
             
           }
           });
   });    
   
   n7.setOnAction(level5 ->{Mode1 f1=new Mode1(5);
       r.getChildren().clear();
       r.getChildren().add(f1.p);
       primaryStage.getScene().setOnMouseMoved(e2 ->{
           if(f1.gamefinished==true)
               f1.bt2.setOnAction(v -> {
                   r.getChildren().clear();
                   r.getChildren().addAll(bk,txt,n1,b1,mod,mode3,mode4);              
               });                  
       });
                primaryStage.getScene().setOnKeyPressed(ev ->{
           
           if(ev.getCode()==KeyCode.Q){
              f1.stop();
            r.getChildren().clear();
             r.getChildren().addAll(bk,txt,n1,mod,mode3,mode4,b1);
             
           }
           });
   });    
   
      n8.setOnAction(level5 ->{Mode1 f1=new Mode1(6);
       r.getChildren().clear();
       r.getChildren().add(f1.p);
       primaryStage.getScene().setOnMouseMoved(e2 ->{
           if(f1.gamefinished==true)
               f1.bt2.setOnAction(v -> {
                   r.getChildren().clear();
                   r.getChildren().addAll(bk,txt,n1,b1,mod,mode3,mode4);              
               });                  
       });
              primaryStage.getScene().setOnKeyPressed(ev ->{
           
           if(ev.getCode()==KeyCode.Q){
              f1.stop();
            r.getChildren().clear();
             r.getChildren().addAll(bk,txt,n1,mod,mode3,mode4,b1);
             
           }
           });  
   });  
   
         n9.setOnAction(level5 ->{Mode1 f1=new Mode1(7);
       r.getChildren().clear();
       r.getChildren().add(f1.p);
       primaryStage.getScene().setOnMouseMoved(e2 ->{
           if(f1.gamefinished==true)
               f1.bt2.setOnAction(v -> {
                   r.getChildren().clear();
                   r.getChildren().addAll(bk,txt,n1,b1,mod,mode3,mode4);              
               });                  
       });
               primaryStage.getScene().setOnKeyPressed(ev ->{
           
           if(ev.getCode()==KeyCode.Q){
              f1.stop();
            r.getChildren().clear();
             r.getChildren().addAll(bk,txt,n1,mod,mode3,mode4,b1);
             
           }
           }); 
   });  
   n5.setOnAction(sj->{r.getChildren().removeAll(n2,n3,n4,n5,n6,n7,n8,n9);
            r.getChildren().addAll(n1,b1,mod,mode3,mode4);
   }); 
   
   

});
        
        
        primaryStage.setScene(new Scene(r,1365,767));
        primaryStage.getIcons().add(new Image("file:icon.jpg"));
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Destroy !");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    

}
