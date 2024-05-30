package Phase2;

import java.io.*;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.*;

public class MenuFX {
	private Menu file, dis;
	private static Menu loc, mar;
	private MenuItem newFile, save, open, saveas;
	private MenuBar menu;
	private FileChooser fc;
	private VBox vb;
	private Alert alert;
	private static boolean saved;
	
	public MenuFX() throws IOException{
		file = new Menu("File");
		dis = new Menu();
		loc = new Menu();
		loc.setDisable(true);
		mar = new Menu();
		mar.setDisable(true);
		newFile = new MenuItem("New");
		save = new MenuItem("Save");
		save.setDisable(true);
		saveas = new MenuItem("Save As");
		open = new MenuItem("Open");
		menu = new MenuBar();
		menu.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
		fc = new FileChooser();
		saved=true;
		
		file.getItems().addAll(newFile, saveas, save, open);
		menu.getMenus().addAll(file,dis,loc,mar);
		
		alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success Dialog");
        alert.setHeaderText("This is a congratulation!");
		
		vb = new VBox();
		DistrictScreen ds = new DistrictScreen();
		vb.getChildren().addAll(menu, DistrictScreen.getVb());
		dis.setDisable(false);
		DistrictScreen.setDistrict();
		newFile.setOnAction(e->{
			fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV File", "*.csv")); 
			Stage stg = new Stage();
			File file = fc.showSaveDialog(stg);
			if(file!=null) {
				try {
					boolean created = file.createNewFile();
                    if (created) {
                        Driver.setFile(file);
                    }
				}catch(IOException ex) {}
			}
			
			try {
				readFromFile();
				dis.setDisable(false);
				save.setDisable(false);
				DistrictScreen.setDistrict();
				LocationScreen.setDistrict(DistrictScreen.getDistrict());
				mar.setDisable(true);
				vb.getChildren().removeAll(DistrictScreen.getVb());
				vb.getChildren().add(DistrictScreen.getVb());
			} catch(NullPointerException ex) {}
		});
		save.setOnAction(e->{
			writeToFile();
			saved=true;
			alert.setContentText("Saved successfully");
			alert.showAndWait();
		});
		saveas.setOnAction(e->{
			fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV File", "*.csv")); 
			Stage stg = new Stage();
			File file = fc.showSaveDialog(stg);
			if(file!=null) {
				try {
					boolean created = file.createNewFile();
                    if (created) {
                    	Driver.setFile(file);
            			writeToFile();
            			saved=true;
                    	alert.setContentText("Saved successfully");
            			alert.showAndWait();
                    }
				}catch(IOException ex) {}
			}
		});
		open.setOnAction(e->{
			fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV File", "*.csv")); 
			Stage stg = new Stage();
			
			Driver.setFile(fc.showOpenDialog(stg));
			try {
				readFromFile();
				dis.setDisable(false);
				save.setDisable(false);
				DistrictScreen.setDistrict();
				LocationScreen.setDistrict(DistrictScreen.getDistrict());
				MartyrsScreen.setLocation(LocationScreen.getLocation());
				MartyrsScreen.setDate();
				vb.getChildren().removeAll(DistrictScreen.getVb());
				vb.getChildren().add(DistrictScreen.getVb());
			} catch(NullPointerException ex) {}
		});
		dis.setGraphic(new Text("Districts"));
		dis.getGraphic().setOnMouseClicked(e->{
			vb.getChildren().clear();
			vb.getChildren().addAll(menu, DistrictScreen.getVb());
		});
		loc.setGraphic(new Text("Locations"));
		LocationScreen lc = new LocationScreen();
		loc.getGraphic().setOnMouseClicked(e->{
			vb.getChildren().clear();
			vb.getChildren().addAll(menu, LocationScreen.getVb());
		});
		
		mar.setGraphic(new Text("Martyrs"));
		MartyrsScreen mr = new MartyrsScreen();
		mar.getGraphic().setOnMouseClicked(e->{
			vb.getChildren().clear();
			vb.getChildren().addAll(menu, MartyrsScreen.getVb());
		});
		
	}
	public boolean readFromFile() {
		try {
			System.out.println(Driver.getFile().toString());
			Driver.setDistricts(new BinarySearchTree());
			Scanner scan = new Scanner(Driver.getFile());
			District d, dtemp;
			Location l;
			Date date;
			if (scan.hasNext()) scan.nextLine();
			while (scan.hasNext()) {
				String[] data = scan.nextLine().split(",");
				if(data.length==6 && !data[2].isBlank()) {
					dtemp=new District(data[4]);					
					d = (District)Driver.getDistricts().insert(dtemp);
					l = (Location)d.getLocations().insert(new Location(data[3]));
					date = (Date)l.getDates().insert(new Date(data[1]));
					date.getMartyrs().add(new Martyr(data[0], data[5], Integer.parseInt(data[2])));
				}
			}
			scan.close();
			return true;
		} catch (IOException e) {
			System.out.println("Something wrong occured");
			return false;
		}
	}

	// to write the new data to the file
	public static void writeToFile() {
		try {
			PrintWriter pw = new PrintWriter(Driver.getFile());
			pw.println("Name,event,Age,location,District,Gender");
			if(Driver.getDistricts().getRoot()==null) return;
			Queue queueDis = new Queue();
			queueDis.enQueue(Driver.getDistricts().getRoot());
			while(!queueDis.isEmpty()) {
				TNode tempDis = (TNode)queueDis.deQueue();
				if(tempDis.getLeft()!=null) queueDis.enQueue(tempDis.getLeft());
				if(tempDis.getRight()!=null)queueDis.enQueue(tempDis.getRight());
				
				Queue queueLoc = new Queue();
				queueLoc.enQueue(((District)tempDis.getData()).getLocations().getRoot());
				while(!queueLoc.isEmpty()) {
					TNode tempLoc = (TNode)queueLoc.deQueue();
					if(tempLoc.getLeft()!=null) queueLoc.enQueue(tempLoc.getLeft());
					if(tempLoc.getRight()!=null)queueLoc.enQueue(tempLoc.getRight());
					
					Queue queueDate = new Queue();
					queueDate.enQueue(((Location)tempLoc.getData()).getDates().getRoot());
					while(!queueDate.isEmpty()) {
						TNode tempDate = (TNode)queueDate.deQueue();
						if(tempDate.getLeft()!=null) queueDate.enQueue(tempDate.getLeft());
						if(tempDate.getRight()!=null)queueDate.enQueue(tempDate.getRight());
						SNode snode = ((Date)tempDate.getData()).getMartyrs().getFirst();
						while(snode!=null) {
							Martyr m = (Martyr)snode.getKey();
							pw.println(m.getName()+","+tempDate.getData()+","+m.getAge()+","+tempLoc.getData()+","+tempDis.getData()+","+m.getGender());
							snode=snode.getNext();
						}
					}
				}
			}
			pw.close();
		} catch(IOException ex) {
			System.out.println("Something wrong occured");
		}
	}
	
	public VBox getVb() {
		return vb;
	}
	
	public static Menu getLoc() {
		return loc;
	}
	public static Menu getMar() {
		return mar;
	}
	public static boolean isSaved() {
		return saved;
	}
	public static void setSaved(boolean saved) {
		MenuFX.saved = saved;
	}
	
}
