package Phase2;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LocationScreen {
	private static VBox vb, vb2, vb3, vb4, vblist, vb5;
	private static ComboBox<Location> cmb;
	private static Button update, delete, insert, next, prev, showList, updatestg;
	private static HBox hb, hb2, hb3, datesHb;
	private static Label mainlb, up_de, inslb, dislb, earlb, latlb, numlb, lblist, enterlb;
	private static TextField instxt, uptxt;
	private Alert alert, alert2;
	private static District district;
	private static Stack stack1, stack2;
	private static Location location;
	private Stage stg1, upstg;
	private static ListView<String> list1;
	
	public LocationScreen() throws IOException {
		stack1 = new Stack();
		stack2 = new Stack();
		
		vb = new VBox(20);
		vb.setPadding(new Insets(10,10,10,10));
		vb.setAlignment(Pos.CENTER);
		vb.setStyle("-fx-background-radius: 10px; -fx-border-color: grey; -fx-border-radius: 10px;");
		mainlb = new Label();
		mainlb.setStyle("-fx-font-size: 30; -fx-font-weight: bold;");
		alert = new Alert(AlertType.WARNING);
		alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
		alert.setTitle("Warning Dialog");
        alert.setHeaderText("This is a warning!");
        alert2 = new Alert(AlertType.ERROR);
		alert2.setTitle("Error Dialog");
        alert2.setHeaderText("An error has occurred!");
		cmb = new ComboBox<>();
		cmb.setValue(new Location("Locations"));
		cmb.setStyle("-fx-pref-width: 180px; -fx-font-color: #7C15D8; -fx-font-size: 18; -fx-font-weight: bold; -fx-border-width: 2.5px; -fx-border-color: #7C15D8; -fx-border-style: hidden hidden solid hidden;");
		enterlb = new Label("Enter the new name");
		enterlb.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
		uptxt = new TextField();
		uptxt.setStyle("-fx-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold;");
		updatestg = new Button("Update");
		updatestg.setStyle("-fx-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold; -fx-background-radius: 10px;");
		upstg = new Stage();
		upstg.setTitle("Update District");
		Image icon = new Image(new FileInputStream("palestine.png"));
		upstg.getIcons().add(icon);
		vb5 = new VBox(15);
		vb5.setPadding(new Insets(25,25,25,25));
		vb5.getChildren().addAll(enterlb, uptxt, updatestg);
		upstg.setScene(new Scene(vb5));
		update = new Button("Update");
		update.setStyle("-fx-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold; -fx-background-radius: 10px;");
		Alert alert3 = new Alert(AlertType.INFORMATION);
        alert3.setTitle("Success Dialog");
        alert3.setHeaderText("This is a congratulation!");
		ButtonType okButton = alert.getButtonTypes().get(0);
		updatestg.setOnAction(e->{
			if(uptxt.getText().isBlank()) {
				alert2.setContentText("Please enter the new name");
				alert2.showAndWait();
			}
			else if(Driver.getDistricts().find(new District(uptxt.getText()))!=null) {
				alert2.setContentText("The name is exist before");
				alert2.showAndWait();
			} else {
				Location loc=cmb.getValue();
				alert.setContentText("Are you sure to update "+loc.getName()+"?!");

		        alert.setOnCloseRequest(event -> {
		        	if (alert.getResult() == okButton) {
		        		district.getLocations().delete(loc);
						loc.setName(uptxt.getText());
						district.getLocations().insert(loc);
						setDistrict(district);
						upstg.close();
						uptxt.setText("");
		        	}
		        });

		        alert.showAndWait();
			}
		});
		update.setOnAction(e->{
			Location d=cmb.getValue();
			if(d!=null && !d.getName().equals("Districts")) {
				upstg.show();
			}
			else {
				alert2.setContentText("Please choose a district.");
				alert2.showAndWait();
			}
		});
		delete = new Button("Delete");
		
		alert = new Alert(AlertType.WARNING);
		alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
		alert.setTitle("Warning Dialog");
        alert.setHeaderText("This is a warning!");
        alert2 = new Alert(AlertType.ERROR);
		alert2.setTitle("Error Dialog");
        alert2.setHeaderText("An error has occurred!");
		delete.setOnAction(e->{
			Location d=cmb.getValue();
			if(d!=null && !d.getName().equals("Locations")) {
		        alert.setContentText("Are you sure to delete "+d.getName()+"?!");

		        alert.setOnCloseRequest(event -> {
		        	if (alert.getResult() == okButton) {
			        	district.getLocations().delete(d);
			        	setDistrict(district);
		        	}
		        });

		        alert.showAndWait();
			}
			else {
				alert2.setContentText("Please Choose a Location.");
				alert2.showAndWait();
			}
		});
		up_de = new Label("Choose a location to update or delete");
		up_de.setStyle("-fx-color: #7C15D8; -fx-font-size: 18; -fx-font-weight: bold;");
		delete.setStyle("-fx-font-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: #7C15D8;");
		insert = new Button("Insert");
		insert.setStyle("-fx-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold; -fx-background-radius: 10px;");
		inslb = new Label("Insert new location");
		inslb.setStyle("-fx-color: #7C15D8; -fx-font-size: 18; -fx-font-weight: bold;");
		instxt = new TextField();
		instxt.setStyle("-fx-color: #7C15D8; -fx-font-size: 18; -fx-font-weight: bold;");
		insert.setOnAction(e->{
			if(instxt.getText().isBlank()) {
				alert2.setContentText("Please fill the blank.");
				alert2.showAndWait();
			}
			else if(district.getLocations().find(new Location(instxt.getText()))!=null) {
				alert2.setContentText("The district is added before.");
				alert2.showAndWait();
			}
			else {
				Location loc= new Location(instxt.getText());
				district.getLocations().insert(loc);
				setDistrict(district);
				location=(Location)((SNode)stack1.peek()).getKey();
				dislb.setText(location.toString());
				earlb.setText("The earliest date is "+location.getDates().getEarliest(location.getDates().getRoot()));
				latlb.setText("The latest date is "+location.getDates().getLatest(location.getDates().getRoot()));
				String[] arr = location.getDates().getMaxDate().split(",");
				numlb.setText("The date has the most number of martyrs is "+arr[0]);
				if(arr.length==1) showList.setDisable(true);
				else {
					list1.setItems(FXCollections.observableArrayList(arr));
					showList.setDisable(false);
				}
				MartyrsScreen.setLocation(location);
				MartyrsScreen.setDate();
				if(stack1.getSize()==1) next.setDisable(true);
				else next.setDisable(false);
				if(stack2.isEmpty()) prev.setDisable(true);
				else prev.setDisable(false);
				alert3.setContentText("Inserted successfully");
				alert3.showAndWait();
				MenuFX.getMar().setDisable(false);
			}
		});
		dislb = new Label("District name");
		dislb.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
		earlb = new Label("The earliest date is ");
		earlb.setStyle("-fx-font-size: 17; -fx-font-weight: bold;");
		latlb = new Label("The Latest date is ");
		latlb.setStyle("-fx-font-size: 17; -fx-font-weight: bold;");
		numlb = new Label("The date has the most number of martyrs is ");
		numlb.setStyle("-fx-font-size: 17; -fx-font-weight: bold;");
		list1 = new ListView<>();
		list1.setStyle("-fx-background-color: #7C15D8; -fx-font-size: 16px; -fx-padding: 5px;");
		list1.setSelectionModel(null);
		lblist = new Label("All dates that has the most number of Martyrs");
		lblist.setStyle("-fx-font-size: 17; -fx-font-weight: bold;");
		vblist = new VBox(10);
		vblist.getChildren().addAll(lblist, list1);
		vblist.setStyle("-fx-padding: 20px;");
		stg1 = new Stage();
		Image icon2 = new Image(new FileInputStream("palestine.png"));
		stg1.getIcons().add(icon2);
		stg1.setScene(new Scene(vblist));
		stg1.setTitle("FREE PALESTINE");
		showList = new Button("Show All");
		showList.setStyle("-fx-font-color: #7C15D8; -fx-font-size: 13; -fx-font-weight: bold; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: #7C15D8;");
		showList.setOnAction(e->stg1.show());
		datesHb = new HBox(10);
		datesHb.getChildren().addAll(numlb, showList);
		datesHb.setAlignment(Pos.CENTER);
		next = new Button("Next");
		next.setStyle("-fx-font-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold; -fx-border-width: 2.6px; -fx-border-color: #7C15D8; -fx-border-style: hidden hidden solid hidden;");
		prev = new Button("Previous");
		hb3 = new HBox(15);
		hb3.getChildren().addAll(prev, next);
		hb3.setAlignment(Pos.CENTER);
		vb3 = new VBox(15);
		vb3.getChildren().addAll(dislb, earlb, latlb, datesHb, hb3);
		vb3.setAlignment(Pos.CENTER);
		next.setOnAction(e->{
			stack2.push(stack1.pop());
			location=(Location)((SNode)stack1.peek()).getKey();
			dislb.setText(location.toString());
			earlb.setText("The earliest date is "+location.getDates().getEarliest(location.getDates().getRoot()));
			latlb.setText("The latest date is "+location.getDates().getLatest(location.getDates().getRoot()));
			String[] arr = location.getDates().getMaxDate().split(",");
			numlb.setText("The date has the most number of martyrs is "+arr[0]);
			cmb.setValue(location);
			if(arr.length==1) showList.setDisable(true);
			else {
				list1.setItems(FXCollections.observableArrayList(arr));
				showList.setDisable(false);
			}
			MartyrsScreen.setLocation(location);
			MartyrsScreen.setDate();
			if(stack1.getSize()==1) next.setDisable(true);
			else next.setDisable(false);
			if(stack2.isEmpty()) prev.setDisable(true);
			else prev.setDisable(false);
		});

		prev.setStyle("-fx-font-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold; -fx-border-width: 2.6px; -fx-border-color: #7C15D8; -fx-border-style: hidden hidden solid hidden;");
		prev.setOnAction(e->{
			stack1.push(stack2.pop());
			location=(Location)((SNode)stack1.peek()).getKey();
			dislb.setText(location.toString());
			earlb.setText("The earliest date is "+location.getDates().getEarliest(location.getDates().getRoot()));
			latlb.setText("The latest date is "+location.getDates().getLatest(location.getDates().getRoot()));
			String[] arr = location.getDates().getMaxDate().split(",");
			numlb.setText("The date has the most number of martyrs is "+arr[0]);
			cmb.setValue(location);
			if(arr.length==1) showList.setDisable(true);
			else {
				list1.setItems(FXCollections.observableArrayList(arr));
				showList.setDisable(false);
			}
			MartyrsScreen.setLocation(location);
			MartyrsScreen.setDate();
			if(stack1.getSize()==1) next.setDisable(true);
			else next.setDisable(false);
			if(stack2.isEmpty()) prev.setDisable(true);
			else prev.setDisable(false);
		});

		vb2 = new VBox(20);
		vb2.setPadding(new Insets(10,10,10,10));
		vb2.setAlignment(Pos.CENTER);
		vb2.setStyle("-fx-background-radius: 10px; -fx-border-color: grey; -fx-border-radius: 10px;");
		vb2.getChildren().addAll(inslb, instxt, insert);
		hb = new HBox(15);
		hb.setAlignment(Pos.CENTER);
		hb.getChildren().addAll(update, delete);
		vb.getChildren().addAll(up_de, cmb, hb);
		hb2 = new HBox(15);
		hb2.setAlignment(Pos.CENTER);
		hb2.getChildren().addAll(vb, vb2);
		vb4 = new VBox(25);
		vb4.getChildren().addAll(mainlb, hb2);
		if(district!=null && district.getLocations().getRoot()!=null) vb4.getChildren().add(vb3);
		vb4.setAlignment(Pos.CENTER);
		VBox.setMargin(mainlb, new Insets(135, 0, 0, 0));
	}
	
	public static VBox getVb() {
		return vb4;
	}
	public static ComboBox<Location> getCombo(){
		return cmb;
	}
	public Label getDislb() {
		return dislb;
	}
	public static void setDistrict(District d) {
		district=d;
		mainlb.setText(d.getName());
		Location loctemp = location, locmb = cmb.getValue();
		stack1.clear();
		stack2.clear();
		cmb.getItems().clear();
		fillStack(d.getLocations().getRoot());
		while(!stack1.isEmpty()  && loctemp!=null && !loctemp.getName().equals(((Location)((SNode)stack1.peek()).getKey()).getName())) {
			stack2.push(stack1.pop());
		}
		if(stack1.isEmpty()) {
			while(!stack2.isEmpty()) stack1.push(stack2.pop());
		}
		getFirstLocation();
	}
	private static void fillStack(TNode node) {
		if(node==null) return;
		Queue q = new Queue();
		q.enQueue(node);
		while(!q.isEmpty()) {
			TNode loc = (TNode)q.deQueue();
			if(loc.getLeft()!=null) q.enQueue(loc.getLeft());
			if(loc.getRight()!=null)q.enQueue(loc.getRight());
			stack2.push((Location)loc.getData());
			cmb.getItems().add((Location)loc.getData());
		}
		while(!stack2.isEmpty()) stack1.push(stack2.pop());
	}
	public static void getFirstLocation() {
		if(stack1.getSize()==1) next.setDisable(true);
		else next.setDisable(false);
		if(stack2.isEmpty()) prev.setDisable(true);
		else prev.setDisable(false);
		if(stack1.peek()!=null) {
			location=(Location)((SNode)stack1.peek()).getKey();
			cmb.setValue(new Location("Locations"));
			instxt.setText("");
			dislb.setText(location.toString());
			earlb.setText("The earliest date is "+location.getDates().getEarliest(location.getDates().getRoot()));
			latlb.setText("The latest date is "+location.getDates().getLatest(location.getDates().getRoot()));
			String[] arr = location.getDates().getMaxDate().split(",");
			numlb.setText("The date has the most number of martyrs is "+arr[0]);
			cmb.setValue(location);
			if(arr.length==1) showList.setDisable(true);
			else {
				list1.setItems(FXCollections.observableArrayList(arr));
				showList.setDisable(false);
			}
			delete.setDisable(false);
			update.setDisable(false);
			cmb.setDisable(false);
			vb4.getChildren().remove(vb3);
			vb4.getChildren().add(vb3);
		}else {
			delete.setDisable(true);
			update.setDisable(true);
			cmb.setDisable(true);
			vb4.getChildren().remove(vb3);
		}
	}
	public static District getDistrict() {
		return district;
	}
	public static Location getLocation() {
		return location;
	}
	
}
