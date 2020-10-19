/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timeline.demo;

import com.sun.javafx.perf.PerformanceTracker;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Miguel Ligero
 */
public class TimelineDemo extends Application {
    
    //Declaración de ejes de la bola
    public static double BSX = 1;
    public static double BSY = 1;
    
    @Override
    public void start(Stage primaryStage) {
        
        //Bola
        Circle ball = new Circle(10);
        ball.setTranslateX(50 * 0.5);
        ball.setTranslateY(50 * 0.5);

        //Label FPS
        Label tx = new Label();    
        tx.setTranslateX(20);
        tx.setTranslateY(20);
        
        //Layout
        Group pane = new Group();
        pane.getChildren().addAll(ball,tx);
        Scene scene = new Scene(pane, 300, 250);
        
        //Manejador de Eventos
        EventHandler<ActionEvent> event = e -> {
            
             //PerformanceTracker (FPS)
            PerformanceTracker showfps = PerformanceTracker.getSceneTracker(scene);
            tx.setText("FPS (Timeline) = " + showfps.getInstantFPS());

            
            // Condicional para modificar la dirección de la bola cuando llegue a los extremos
              if(ball.getTranslateX()<0 ||ball.getTranslateX()>scene.getWidth()){
               BSX *= -1;
            }
            if(ball.getTranslateY()<0 || ball.getTranslateY()>scene.getHeight()){
               BSY *= -1;
            }

            ball.setTranslateX(ball.getTranslateX() + BSX);
            ball.setTranslateY(ball.getTranslateY() + BSY);
        };
        
         //Animación
        Timeline anim = new Timeline(new KeyFrame(Duration.millis(400), event));
        anim.setCycleCount(Timeline.INDEFINITE);
        anim.play();

        primaryStage.setTitle("Timeline Demo");
        primaryStage.setScene(scene);
      //primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
