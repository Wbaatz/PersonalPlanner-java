package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;

public class Main extends Application {
	// static Controller c;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Full Calendar Example");
        Parent root = FXMLLoader.load(getClass().getResource("CalenderPage.fxml"));
        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
    //    primaryStage.setWidth(1270);
    //    primaryStage.setHeight(820);
        primaryStage.setWidth(600);
            primaryStage.setHeight(500);
         primaryStage.setOnCloseRequest(event -> event.consume());
        
       primaryStage.show();
       
       //..............................................
        //System.out.println("doing testing");
        //System.out.println(LocalDate.now().getMonth().toString()+" "+LocalDate.now().getYear());
        //System.out.println(LocalDate.now().getDayOfMonth());
        //System.out.println(LocalDate.now().getMonthValue());
        //System.out.println(LocalDate.now().getDayOfWeek());
        //System.out.println(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), 5).getDayOfWeek());
    ////    System.out.println(LocalDate.now().lengthOfMonth());
       // LocalDate ld=LocalDate.of(2023, 2, 5);
      //  System.out.println(ld.getDayOfWeek());
        //System.out.println("show all days");
       
      //  for(int i=1;i<=7;i++)
        //{
        	// System.out.print(LocalDate.now().getDayOfWeek().of(i)+" ");
       // }
        //for(int i=1;i<=LocalDate.now().lengthOfMonth();i++)
        //{
        	
        //}
    }

    public static void main(String[] args) {
    	
        launch(args);
       
        
    }
    
    
    
}