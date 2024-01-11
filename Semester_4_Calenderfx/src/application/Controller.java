package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane ;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Controller extends Main {

	@FXML
	Button cancel_T;
	
	@FXML
	Button Close_btn;
	
	@FXML
	Button Del_Task1;
	
    @FXML
    TextArea Txt_Task_Area;
    
    @FXML
    TextArea Scheduled_Task;
  //textarea for creating tasks
    @FXML
    TextArea show_Task;
	
	@FXML
	AnchorPane Dialogue_Task;
	
	@FXML
	AnchorPane Main_Pane;
	
	@FXML 
	Label movDate;
	
	@FXML
	Label theDate;
	
	@FXML
	GridPane myGrid;
	
	@FXML
    Button Add_Task;
	
	@FXML 
	Button nextbtn;
	
	@FXML
	Button prevbtn;
	
	@FXML
	Button create_T;
static int k=0;
static int y=0;
int ny=0;
static int b=0;
public String Current_Date;
Thread thread;
 
 boolean Flag=false;
 private volatile boolean stop=false;

 

 
 public void LogOUT(ActionEvent e)
 {
	 if(e.getSource().equals(Close_btn))
	 {
		 
		
			
			
		
		 
		 Platform.exit();
		 thread.stop();
	 }
	 
 }
 
 
 public void Alarm_Task()
 {
	 String Status_Date=LocalDate.now().getDayOfMonth()+"-"+Integer.toString(LocalDate.now().plusMonths(k).minusMonths(b).getMonthValue())+"-"+Integer.toString(LocalDate.now().plusYears(y).minusYears(ny).getYear());
	 if(Status_Date.equals(Current_Date))
	 {
			String tsk="";
			File f = new File(Current_Date+".txt");
			try {
				FileReader fr = new FileReader(f);
				 int i;    
		          while((i=fr.read())!=-1)   
		          {
		        	  tsk=tsk+((char)i);  
		          }
		      
				fr.close();
				if(tsk.equals(""))
				{
					Scheduled_Task.setText("");
				}
				else
				{
					 Scheduled_Task.setText("the Current Task for today is \n"+tsk);
				}
				
	
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		// Scheduled_Task.setText("yahoooo me");
	 }
	 else
	 {
		 Scheduled_Task.setText("");
	 }
	 
	// jklj
 }
 
 public void TheTasks (ActionEvent e)
	{
	 
	 if(e.getSource().equals(Del_Task1))
	 {
		
		 
		 int i =0;
			
			for(Node node :myGrid.getChildren()){
				
				
			if(i>6)
			{
				
			
				if(node instanceof StackPane)
				{
					
					StackPane pane =(StackPane)node;
					String Temp=node.getStyle();
					int count=0;
					int Num1 = 0;
					int Num2 = 0;
					for(int j=0;j<Temp.length();j++)
					{
						System.out.print(Temp.charAt(j));
						if(Temp.charAt(j)==':')
						{
							Num1=j;
				//			System.out.println("i got this at"+j);
						}
						if(Temp.charAt(j)==';')
						{
							Num2=j;
				//			System.out.println("i got this at"+j);
							count++;
							if(count==2)
							{
								//count=0;
								break;	
							}
						
						}
					}
					String str1=Temp.substring(Num1+1,Num2 );
					if(str1.equals("orange"))
					{
						
						Label l12= (Label) pane.getChildren().get(0);
						String str2=l12.getText()+"-"+Integer.toString(LocalDate.now().plusMonths(k).minusMonths(b).getMonthValue())+"-"+Integer.toString(LocalDate.now().plusYears(y).minusYears(ny).getYear());
						
						
						
						try {
							PrintWriter pw = new PrintWriter(str2+".txt");
							pw.close();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						Change_paneColour(pane,"white");
					}
							
					//System.out.println("the color is ->"+str1);
					
				
				}
			}
			else
			{
				i++;
			}
		}
			Alarm_Task();
	 }
		
		if(e.getSource().equals(cancel_T))
		{
			Dialogue_Task.setVisible(false);
			create_T.setVisible(false);
			cancel_T.setVisible(false);
			Txt_Task_Area.setVisible(false);
		}
		if(e.getSource().equals(Add_Task))
		{ 	
			Dialogue_Task.setVisible(true);
			create_T.setVisible(true);
			cancel_T.setVisible(true);
			Txt_Task_Area.setVisible(true);
		}
		if(e.getSource().equals(create_T))
		{ 	String str=Txt_Task_Area.getText();
			 int i =0;
				
				for(Node node :myGrid.getChildren()){
					
					
				if(i>6)
				{
					
				
					if(node instanceof StackPane)
					{
						
						StackPane pane =(StackPane)node;
						String Temp=node.getStyle();
						int count=0;
						int Num1 = 0;
						int Num2 = 0;
						for(int j=0;j<Temp.length();j++)
						{
							System.out.print(Temp.charAt(j));
							if(Temp.charAt(j)==':')
							{
								Num1=j;
					//			System.out.println("i got this at"+j);
							}
							if(Temp.charAt(j)==';')
							{
								Num2=j;
					//			System.out.println("i got this at"+j);
								count++;
								if(count==2)
								{
									//count=0;
									break;	
								}
							
							}
						}
						String str1=Temp.substring(Num1+1,Num2 );
						if(str1.equals("orange"))
						{
							Change_paneColour(pane,"red");
							//Label l1=(Label)pane.getChildren().get(0);
						//	theDate.setText(l1.getText()+"/"+Integer.toString(LocalDate.now().plusMonths(k).minusMonths(b).getMonthValue())+"/"+Integer.toString(LocalDate.now().plusYears(y).minusYears(ny).getYear()));
							Label l12= (Label) pane.getChildren().get(0);
							String str2=l12.getText()+"-"+Integer.toString(LocalDate.now().plusMonths(k).minusMonths(b).getMonthValue())+"-"+Integer.toString(LocalDate.now().plusYears(y).minusYears(ny).getYear());
							System.out.println(str2+"hey see this!!!...........");
							File f = new File(str2+".txt");
							try {
								FileWriter fw = new FileWriter(f);
								fw.write(Txt_Task_Area.getText());
								Txt_Task_Area.setText(null);
								fw.close();
					System.out.println("file is successfully created");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							System.out.println(theDate);
						}
								
						//System.out.println("the color is ->"+str1);
						
					
					}
				}
				else
				{
					i++;
				}
			}
			
				Dialogue_Task.setVisible(false);
				create_T.setVisible(false);
				cancel_T.setVisible(false);
				Txt_Task_Area.setVisible(false);
				Alarm_Task();
		}
		
		
		
		
		//.............................
		
		
		
		
		
		
		
		
		}



public void Timenow()
{

	System.out.println("time should show");
	 thread = new Thread ( () ->{
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		while(!stop)
		{
			
			try {
				Thread.sleep(1);
			
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			final String Timenow = sdf.format(new Date());
			Platform.runLater(()->{
				movDate.setText(Timenow);
			});
		}
	});	
	thread.start();
	
	
}


	
	public void initialize() {
		
		
		Timenow();
		
		Add_Task.setVisible(false);
		int dayS=LocalDate.now().getDayOfMonth();
		// myGrid.setGridLinesVisible(true);
	int Year=LocalDate.now().getYear();
	int Month=LocalDate.now().getMonthValue();
	int Days=LocalDate.now().getDayOfMonth();

		theDate.setText(Integer.toString(dayS)+"-"+Integer.toString(LocalDate.now().plusMonths(k).minusMonths(b).getMonthValue())+"-"+Integer.toString(LocalDate.now().plusYears(y).minusYears(ny).getYear()));
		Current_Date=theDate.getText();
		//for setting the name of the days in week
	
		Alarm_Task();
		
		
		for(int i =0;i<=6;i++)
		{
			
	//	myGrid.add(pane, i, j);
			 String str=LocalDate.now().getDayOfWeek().of(i+1).toString();
			Label l1 = new Label(str);
			
			l1.setFont(new Font("Arial",10));
		
			StackPane pane = new StackPane();
		//	pane.setStyle("-fx-border-color:grey;");
			pane.getChildren().add(l1);
		//	pane.setAlignment(l1,Pos.BASELINE_CENTER);
			myGrid.add(pane, i, 0);
			
		//	myGrid.setHalignment(pane.se,HPos.CENTER);
			myGrid.setStyle("-fx-background-color:white;");
		//	myGrid.addEventHandler(null, null);
		//	pane.setOnMouseEntered(e -> pane.setStyle("-fx-background-color:red;"));
			
			
			
		}
		//System.out.println(myGrid.getChildren()+"this is children");
//starting from which day of the week
					int Start_Ind = 0;
					//what is the length of the month
					int End_Ind=LocalDate.now().lengthOfMonth()+1;
				//	int End_Ind=LocalDate.now().plusMonths(2).lengthOfMonth()+1;
					boolean flag=false;
					//starting from which day of the week
	  String Day_first_Name=LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), 1).getDayOfWeek().toString();
				for(int j=0;j<=6;j++)
				{
					//nothing special ,just printing out each day of the week
					 String str=LocalDate.now().getDayOfWeek().of(j+1).toString();
					 if(Day_first_Name.equals(str))
					 {
						 Start_Ind=j;
				 }
			}
			int index=1;
			 for(int r=1;r<=6;r++)
			 {
				 for(int c=0;c<=6;c++)
				 {
					 if(index==End_Ind)
					 {
						 break;
					 }
					 if(c==Start_Ind)
					 {
						 flag=true;
					 }
					 if(flag==true)
					 {
						 Label l2 = new Label(Integer.toString(index++));
						 l2.setFont(new Font("Arial",20));
					//	 l2.setStyle("-fx-border-color:blue;");
						 
						 
						
						//l2.setMaxWidth(20);
						 StackPane pane = new StackPane();
						// pane.setStyle("-fx-border-color:grey;");
							pane.getChildren().add(l2);
							pane.setStyle("-fx-background-color:;-fx-border-color:;-fx-border-width:2;");
							Change_paneColour(pane,"white");
							myGrid.add(pane, c, r);
					
						
						
						
					
						
					 }
				
						
					
				 }
			 }
			
	//	System.out.println(myGrid.getChildren().get(1));
	//	StackPane Spane=(StackPane) myGrid.getChildren().get(1);
	//	System.out.println(Spane.getChildren().get(0));
		//**very important discovery. ;)
			 
			 
			
			 InitGo();
			 Initialize_Filing_Color();
			
			
	}
	
	public void Initialize_Border_Color()
	{
	//	 System.out.println("I AM INSIDE THE FUNCTION!!!!!1");
		 int i =0;
		
		for(Node node :myGrid.getChildren()){
			
			
		if(i>6)
		{
			
		
			if(node instanceof StackPane)
			{
				
				StackPane pane =(StackPane)node;
				String Temp=node.getStyle();
				int count=0;
				int Num1 = 0;
				int Num2 = 0;
				for(int j=0;j<Temp.length();j++)
				{
					System.out.print(Temp.charAt(j));
					if(Temp.charAt(j)==':')
					{
						Num1=j;
			//			System.out.println("i got this at"+j);
					}
					if(Temp.charAt(j)==';')
					{
						Num2=j;
			//			System.out.println("i got this at"+j);
						count++;
						if(count==2)
						{
							//count=0;
							break;	
						}
					
					}
				}
				String str1=Temp.substring(0, Num1+1);
				
				String str2=Temp.substring(Num2);
				
			//	pane.setStyle("-fx-border-color:orange;-fx-border-width:2;");
			//	System.out.println("str1 inside init ->"+str1);
			//	System.out.println("str2 inside init ->"+str2);
				
				pane.setStyle(str1+str2);
			}
		}
		else
		{
			i++;
		}
	}
	}
	
	
	public void Initialize_Filing_Color()
	{
	//	 System.out.println("I AM INSIDE THE FUNCTION!!!!!1");
		 int i =0;
		
		for(Node node :myGrid.getChildren()){
			
			
		if(i>6)
		{
			
		
			if(node instanceof StackPane)
			{
				
				StackPane pane =(StackPane)node;
				Label l12= (Label) pane.getChildren().get(0);
				String str2=l12.getText()+"-"+Integer.toString(LocalDate.now().plusMonths(k).minusMonths(b).getMonthValue())+"-"+Integer.toString(LocalDate.now().plusYears(y).minusYears(ny).getYear());
				
				File f = new File(str2+".txt");
				if(f.length()!=0)
				{
					Change_paneColour(pane,"red");
				}
			}
		}
		else
		{
			i++;
		}
	}
	}
	
	
	
	public void Change_paneColour(StackPane node,String Col)
	{
		String Temp=node.getStyle();
		int Num1 = 0;
		int Num2 = 0;
		for(int i=0;i<Temp.length();i++)
		{
			System.out.print(Temp.charAt(i));
			if(Temp.charAt(i)==':')
			{
				Num1=i;
				System.out.println("i got this at"+i);
			}
			if(Temp.charAt(i)==';')
			{
				Num2=i;
				System.out.println("i got this at"+i);
				break;
			}
		}
		String str1=Temp.substring(0, Num1+1);
		
		String str2=Temp.substring(Num2);
		//System.out.println(str1+"is string1");
		//System.out.println(str2+"is string2");
	//	System.out.println(str1+"blue"+str2);
		node.setStyle(str1+Col+str2);
		
		
	}
	public String Get_paneColour(StackPane node)
	{
		String Col;
		String Temp=node.getStyle();
		int Num1 = 0;
		int Num2 = 0;
		for(int i=0;i<Temp.length();i++)
		{
			System.out.print(Temp.charAt(i));
			if(Temp.charAt(i)==':')
			{
				Num1=i;
				System.out.println("i got this at"+i);
			}
			if(Temp.charAt(i)==';')
			{
				Num2=i;
				System.out.println("i got this at"+i);
				break;
			}
		}
		String str1=Temp.substring(Num1+1, Num2);
		
	//	String str2=Temp.substring(Num2);
		//System.out.println(str1+"is string1");
		//System.out.println(str2+"is string2");
	//	System.out.println(str1+"blue"+str2);
		return str1;
		
	}
	
	public void InitGo()
	{
		 int i =0;
			
			for(Node node :myGrid.getChildren()){
				
				System.out.println(node+"right here");
			if(i>6)
			{
				
			
				if(node instanceof StackPane)
				{
					
					StackPane pane =(StackPane)node;
				
				  
				//	node.setOnMouseEntered(e -> System.out.println("yes Pane") );
				
				//	node.setOnMouseEntered(e -> node.setStyle("-fx-background-color:blue;"));
				//	pane.setOnMouseClicked(e -> node.setStyle("-fx-border-color:orange;"));
					pane.setOnMousePressed(new EventHandler<MouseEvent>() {

						@Override
						public void handle(MouseEvent arg0) {
							Initialize_Border_Color();
							
							
							
							
							String Temp=node.getStyle();
							int count=0;
							int Num1 = 0;
							int Num2 = 0;
							for(int i=0;i<Temp.length();i++)
							{
								System.out.print(Temp.charAt(i));
								if(Temp.charAt(i)==':')
								{
									Num1=i;
									System.out.println("i got this at"+i);
								}
								if(Temp.charAt(i)==';')
								{
									Num2=i;
									System.out.println("i got this at"+i);
									count++;
									if(count==2)
									{
										break;	
									}
								
								}
							}
							String str1=Temp.substring(0, Num1+1);
							
							String str2=Temp.substring(Num2);
							
						//	pane.setStyle("-fx-border-color:orange;-fx-border-width:2;");
							pane.setStyle(str1+"orange"+str2);
							Label l12= (Label) pane.getChildren().get(0);
							String str=l12.getText()+"-"+Integer.toString(LocalDate.now().plusMonths(k).minusMonths(b).getMonthValue())+"-"+Integer.toString(LocalDate.now().plusYears(y).minusYears(ny).getYear());
						//	show_Task.setText(str);
							String tsk="";
							File f = new File(str+".txt");
							try {
								FileReader fr = new FileReader(f);
								 int i;    
						          while((i=fr.read())!=-1)   
						          {
						        	  tsk=tsk+((char)i);  
						          }
						      
								fr.close();
								show_Task.setText(tsk);
					System.out.println("file is successfully readed");
							} catch (IOException e1) {
								
								e1.printStackTrace();
							}
							
							
							//add file loading here.
						//	File f = new File(theDate+".txt");
							
								//FileReader fw = new FileWriter(f);
								//fw.write(Txt_Task_Area.getText());
							//	fw.close();
							
							Add_Task.setVisible(true);
							if(!(Get_paneColour(pane).equals("red")))
							{
								Change_paneColour(pane,"white");
							}
						//	Change_paneColour(pane,"white");
							Label l1= (Label) pane.getChildren().get(0);
							l1.setStyle("-fx-text-fill:black;");
					//		Flag=true;
							System.out.println(pane.getStyle());
						}
						
					});
				
					pane.setOnMouseEntered(new EventHandler<MouseEvent>(){
					
						@Override
						public void handle(MouseEvent arg0) {
						//	Flag=false;
								Label l1= (Label) pane.getChildren().get(0);
								l1.setStyle("-fx-text-fill:white;");
							
							
						
							System.out.println(arg0);
							System.out.println(pane.getStyle());
							if(Get_paneColour(pane).equals("white"))
							{
								Change_paneColour(pane,"blue");
							}
						//	System.out.println("hi there my pane col is ->"+Get_paneColour(pane)); 
							
						
						System.out.println(myGrid.getColumnIndex(pane)+" ,"+myGrid.getRowIndex(pane));
							
						}
					});
					pane.setOnMouseExited(new EventHandler<MouseEvent>(){
						
						@Override
						public void handle(MouseEvent arg0) {
							
								Label l1= (Label) pane.getChildren().get(0);
								l1.setStyle("-fx-text-fill:black;");
								String Temp=node.getStyle();
								int Num1 = 0;
								int Num2 = 0;
								System.out.println(Temp);
								for(int i=0;i<Temp.length();i++)
								{
									System.out.print(Temp.charAt(i));
									if(Temp.charAt(i)==':')
									{
										Num1=i;
										//System.out.println("i got this at"+i);
									}
									if(Temp.charAt(i)==';')
									{
										Num2=i;
										break;
									//	System.out.println("i got this at"+i);
									}
								}
								String str1=Temp.substring(0, Num1+1);
								String str2=Temp.substring(Num2);
							//	System.out.println(str1+"is string1");
						//		System.out.println(str2+"is string2");
						 String Temp1=Temp.substring(Num1+1, Num2);
								if(Temp1.equals("blue"))
								{
									node.setStyle(str1+"white"+str2);
								
								//	pane.setStyle("-fx-background-color:white;");
								}
							
							
					
						System.out.println(myGrid.getColumnIndex(pane)+" ,"+myGrid.getRowIndex(pane));
							
						}
					});
					
				}
				//first if
			}
			else
			{
				if(node instanceof StackPane)
				{
					StackPane pane =(StackPane)node;
					pane.setOnMouseEntered(new EventHandler<MouseEvent>(){
					
						@Override
						public void handle(MouseEvent arg0) {
							
								Label l1= (Label) pane.getChildren().get(0);
								l1.setStyle("-fx-text-fill:white;");
							node.setStyle("-fx-background-color:red;");
							
						}
					});
					pane.setOnMouseExited(new EventHandler<MouseEvent>(){
						
						@Override
						public void handle(MouseEvent arg0) {
							
								Label l1= (Label) pane.getChildren().get(0);
								l1.setStyle("-fx-text-fill:black;");	
							node.setStyle("-fx-background-color:white;");
						}
					});
					
				}
			}
			i++;
			}
	}
	
	public void mybtn (ActionEvent e)
	{
		
		
		if(e.getSource().equals(nextbtn))
		{ 
			if(LocalDate.now().minusMonths(b).plusMonths(k).getMonthValue()==12)
			{
				y=y+1;
			
			}
			k=k+1;
		
			theDate.setText(LocalDate.now().toString());
			
			theDate.setText("1"+"/"+Integer.toString(LocalDate.now().minusMonths(b).plusMonths(k).getMonthValue())+"/"+Integer.toString(LocalDate.now().minusYears(ny).plusYears(y).getYear()));
			myGrid.getChildren().clear();
		//	CreateBorder();
			Alarm_Task();
			for(int i =0;i<=6;i++)
			{
				
				 String str=LocalDate.now().getDayOfWeek().of(i+1).toString();
				Label l1 = new Label(str);
				l1.setFont(new Font("Arial",10));
				
				StackPane pane = new StackPane();
			//	pane.setStyle("-fx-border-color:grey;");
				pane.getChildren().add(l1);
			
				myGrid.add(pane, i, 0);
				myGrid.setStyle("-fx-background-color:white;");
			}
			
		//	myGrid.setStyle("-fx-background-color:palegreen");
			
		//myGrid.setStyle("-fx-background-color:cell-border-color,cell-color;");
			
			int Start_Ind = 0;
			//what is the length of the month
			
		//	int End_Ind=LocalDate.now().minusYears(ny).plusYears(y).minusMonths(b).plusMonths(k).lengthOfMonth()+1;
			int End_Ind=LocalDate.now().minusMonths(b).plusMonths(k).lengthOfMonth()+1;
			System.out.println(End_Ind);
			boolean flag=false;
			//starting from which day of the week
String Day_first_Name=LocalDate.of(LocalDate.now().minusYears(ny).plusYears(y).getYear(), LocalDate.now().minusYears(ny).plusYears(y).minusMonths(b).plusMonths(k).getMonthValue(), 1).getDayOfWeek().toString();
		for(int j=0;j<=6;j++)
		{
			//nothing special ,just printing out each day of the week
			 String str=LocalDate.now().getDayOfWeek().of(j+1).toString();
			 if(Day_first_Name.equals(str))
			 {
				 Start_Ind=j;
		 }
	}
	int index=1;
	 for(int r=1;r<=6;r++)
	 {
		 for(int c=0;c<=6;c++)
		 {
			 if(index==End_Ind)
			 {
				 break;
			 }
			 if(c==Start_Ind)
			 {
				 flag=true;
			 }
			 if(flag==true)
			 {
				 Label l2 = new Label(Integer.toString(index++));
				 l2.setFont(new Font("Arial",20));
				 
				 StackPane pane = new StackPane();
			//	 pane.setStyle("-fx-border-color:grey;");
					pane.getChildren().add(l2);
					pane.setStyle("-fx-background-color:;-fx-border-color:;-fx-border-width:2;");
					Change_paneColour(pane,"white");
					myGrid.add(pane, c, r);
			 }
			
			
		 }
	 }

	 InitGo();
	 Initialize_Filing_Color();
		}
		
		if(e.getSource().equals(prevbtn))
		{
			if(LocalDate.now().plusMonths(k).minusMonths(b).getMonthValue()==1)
			{
				y=y-1;
				//b=-2;
			}
			b=b+1;
		
		
			
			theDate.setText("1"+"/"+Integer.toString(LocalDate.now().plusMonths(k).minusMonths(b).getMonthValue())+"/"+Integer.toString(LocalDate.now().plusYears(y).minusYears(ny).getYear()));
			myGrid.getChildren().clear();
		//	CreateBorder();
			Alarm_Task();
			for(int i =0;i<=6;i++)
			{
				
				 String str=LocalDate.now().getDayOfWeek().of(i+1).toString();
				Label l1 = new Label(str);
				l1.setFont(new Font("Arial",10));
				
			
				
				StackPane pane = new StackPane();
			//	pane.setStyle("-fx-border-color:grey;");
				pane.getChildren().add(l1);
			
				myGrid.add(pane, i, 0);
				myGrid.setStyle("-fx-background-color:white;");
			
			}
			
			
			
			int Start_Ind = 0;
			//what is the length of the month
			
			int End_Ind=LocalDate.now().minusMonths(b).plusMonths(k).lengthOfMonth()+1;
			boolean flag=false;
			//starting from which day of the week
String Day_first_Name=LocalDate.of(LocalDate.now().plusYears(y).minusYears(ny).getYear(), LocalDate.now().plusYears(y).minusYears(ny).plusMonths(k).minusMonths(b).getMonthValue(), 1).getDayOfWeek().toString();
		for(int j=0;j<=6;j++)
		{
			//nothing special ,just printing out each day of the week
			 String str=LocalDate.now().getDayOfWeek().of(j+1).toString();
			 if(Day_first_Name.equals(str))
			 {
				 Start_Ind=j;
		 }
	}
	int index=1;
	 for(int r=1;r<=6;r++)
	 {
		 for(int c=0;c<=6;c++)
		 {
			 if(index==End_Ind)
			 {
				 break;
			 }
			 if(c==Start_Ind)
			 {
				 flag=true;
			 }
			 if(flag==true)
			 {
				 Label l2 = new Label(Integer.toString(index++));
				 l2.setFont(new Font("Arial",20));
				 StackPane pane = new StackPane();
					
							pane.getChildren().add(l2);
							pane.setStyle("-fx-background-color:;-fx-border-color:;-fx-border-width:2;");
							Change_paneColour(pane,"white");
							myGrid.add(pane, c, r);
			 }
			
			
		 }
	 }
	 
	 InitGo();
	 Initialize_Filing_Color();
		}
}
	
	
	






	
	
}


