package Phase2;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DistrictScreen {
	private static VBox vb, vb2, vb3, vb4, vb5;
	private static ComboBox<District> cmb;
	private static Button update, delete, insert, next, prev, updatestg;
	private static HBox hb, hb2, hb3;
	private static Label up_de, inslb, dislb, numlb, enterlb;
	private TextField instxt, uptxt;
	private Alert alert, alert2;
	private static Stack stack1, stack2;
	private static District district;
	private Stage upstg;
	
	public DistrictScreen() throws IOException{
		stack1 = new Stack();
		stack2 = new Stack();
		
		dislb = new Label("District name");
		dislb.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
		numlb = new Label("The number of martyrs is ");
		numlb.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
		next = new Button("Next");
		next.setStyle("-fx-font-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold; -fx-border-width: 2.6px; -fx-border-color: #7C15D8; -fx-border-style: hidden hidden solid hidden;");
		next.setOnAction(e->{
			stack2.push(stack1.pop());
			district=(District)((SNode)stack1.peek()).getKey();
			dislb.setText(district.toString());
			numlb.setText("The number of martyrs is "+district.getLocations().countMartyrs(district.getLocations().getRoot()));
			LocationScreen.setDistrict(district);
			MartyrsScreen.setLocation(LocationScreen.getLocation());
			MartyrsScreen.setDate();
			cmb.setValue(district);
			if(stack1.getSize()==1) next.setDisable(true);
			else next.setDisable(false);
			if(stack2.isEmpty()) prev.setDisable(true);
			else prev.setDisable(false);
		});
		prev = new Button("Previous");
		if(stack1.getSize()==1) next.setDisable(true);
		else next.setDisable(false);
		if(stack2.isEmpty()) prev.setDisable(true);
		else prev.setDisable(false);
		prev.setStyle("-fx-font-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold; -fx-border-width: 2.6px; -fx-border-color: #7C15D8; -fx-border-style: hidden hidden solid hidden;");
		prev.setOnAction(e->{
			stack1.push(stack2.pop());
			district=(District)((SNode)stack1.peek()).getKey();
			dislb.setText(district.toString());
			numlb.setText("The number of martyrs is "+district.getLocations().countMartyrs(district.getLocations().getRoot()));
			LocationScreen.setDistrict(district);
			MartyrsScreen.setLocation(LocationScreen.getLocation());
			MartyrsScreen.setDate();
			cmb.setValue(district);
			if(stack1.getSize()==1) next.setDisable(true);
			else next.setDisable(false);
			if(stack2.isEmpty()) prev.setDisable(true);
			else prev.setDisable(false);
		});
		hb3 = new HBox(15);
		hb3.getChildren().addAll(prev, next);
		hb3.setAlignment(Pos.CENTER);
		vb3 = new VBox(15);
		vb3.getChildren().addAll(dislb, numlb, hb3);
		
		vb = new VBox(20);
		vb.setPadding(new Insets(10,10,10,10));
		vb.setAlignment(Pos.CENTER);
		vb.setStyle("-fx-background-radius: 10px; -fx-border-color: grey; -fx-border-radius: 10px;");
		cmb = new ComboBox<>();
		cmb.setValue(new District("Districts"));
		cmb.setStyle("-fx-pref-width: 180px; -fx-font-color: #7C15D8; -fx-font-size: 18; -fx-font-weight: bold; -fx-border-width: 2.5px; -fx-border-color: #7C15D8; -fx-border-style: hidden hidden solid hidden;");
		update = new Button("Update");
		update.setStyle("-fx-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold; -fx-background-radius: 10px;");
		enterlb = new Label("Enter the new name");
		enterlb.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
		uptxt = new TextField();
		uptxt.setStyle("-fx-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold;");
		updatestg = new Button("Update");
		updatestg.setStyle("-fx-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold; -fx-background-radius: 10px;");
		upstg = new Stage();
		Image icon = new Image(new FileInputStream("palestine.png"));
		upstg.getIcons().add(icon);
		upstg.setTitle("Update District");
		vb5 = new VBox(15);
		vb5.setPadding(new Insets(25,25,25,25));
		vb5.getChildren().addAll(enterlb, uptxt, updatestg);
		upstg.setScene(new Scene(vb5));
		alert = new Alert(AlertType.WARNING);
		alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
		alert.setTitle("Warning Dialog");
        alert.setHeaderText("This is a warning!");
        alert2 = new Alert(AlertType.ERROR);
		alert2.setTitle("Error Dialog");
        alert2.setHeaderText("An error has occurred!");
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
				District d=cmb.getValue();
				alert.setContentText("Are you sure to update "+d.getName()+"?!");

		        alert.setOnCloseRequest(event -> {
		        	if (alert.getResult() == okButton) {
						Driver.getDistricts().delete(d);
						d.setName(uptxt.getText());
						Driver.getDistricts().insert(d);
						setDistrict();
						upstg.close();
						uptxt.setText("");
		        	}
		        });

		        alert.showAndWait();
			}
		});
		update.setOnAction(e->{
			District d=cmb.getValue();
			if(d!=null && !d.getName().equals("Districts")) {
				upstg.show();
			}
			else {
				alert2.setContentText("Please choose a district.");
				alert2.showAndWait();
			}
		});
		
		delete = new Button("Delete");
		delete.setOnAction(e->{
			District d=cmb.getValue();
			if(d!=null && !d.getName().equals("Districts")) {
		        alert.setContentText("Are you sure to delete "+d.getName()+"?!");

		        alert.setOnCloseRequest(event -> {
		        	if (alert.getResult() == okButton) {
			        	Driver.getDistricts().delete(d);
						cmb.getItems().remove(d);
						setDistrict();
						if(d==LocationScreen.getDistrict()) {
							if(Driver.getDistricts().getRoot()==null) MenuFX.getLoc().setDisable(true);
							else {
								MenuFX.getLoc().setDisable(false);
								LocationScreen.setDistrict(district);
							}
						}
		        	}
		        });

		        alert.showAndWait();
			}
			else {
				alert2.setContentText("Please choose a district.");
				alert2.showAndWait();
			}
		});
		up_de = new Label("Choose a district to update or delete");
		up_de.setStyle("-fx-color: #7C15D8; -fx-font-size: 18; -fx-font-weight: bold;");
		delete.setStyle("-fx-font-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: #7C15D8;");
		insert = new Button("Insert");
		insert.setStyle("-fx-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold; -fx-background-radius: 10px;");
		inslb = new Label("Insert new district");
		inslb.setStyle("-fx-color: #7C15D8; -fx-font-size: 18; -fx-font-weight: bold;");
		instxt = new TextField();
		instxt.setStyle("-fx-color: #7C15D8; -fx-font-size: 18; -fx-font-weight: bold;");
		insert.setOnAction(e->{
			if(instxt.getText().isBlank()) {
				alert2.setContentText("Please fill the blank.");
				alert2.showAndWait();
			}
			else if(Driver.getDistricts().find(new District(instxt.getText()))!=null) {
				alert2.setContentText("The district is added before.");
				alert2.showAndWait();
			}
			else {
				District dis= new District(instxt.getText());
				Driver.getDistricts().insert(dis);
				setDistrict();
				if(MenuFX.getLoc().isDisable()) {
					MenuFX.getLoc().setDisable(false);
					LocationScreen.setDistrict(dis);
				}
				dislb.setText(district.toString());
				numlb.setText("The number of martyrs is "+district.getLocations().countMartyrs(district.getLocations().getRoot()));
				cmb.setValue(district);
				if(stack1.getSize()==1) next.setDisable(true);
				else next.setDisable(false);
				if(stack2.isEmpty()) prev.setDisable(true);
				else prev.setDisable(false);
				alert3.setContentText("Inserted successfully");
				alert3.showAndWait();
			}
		});
		vb3.setAlignment(Pos.CENTER);
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
		vb4.getChildren().addAll(hb2);
		if(Driver.getDistricts()!=null && Driver.getDistricts().getRoot()!=null) vb4.getChildren().add(vb3);
		else vb4.getChildren().remove(vb3);
		vb4.setAlignment(Pos.CENTER);
		VBox.setMargin(hb2, new Insets(150, 0, 0, 0));
	}
	
	public static VBox getVb() {
		return vb4;
	}
	public static ComboBox<District> getCombo(){
		return cmb;
	}
	public static void setDistrict() {
		District dtemp=district;
		stack1.clear();
		stack2.clear();
		cmb.getItems().clear();
		fillStack(Driver.getDistricts().getRoot());
		while(!stack1.isEmpty()  && dtemp!=null && !dtemp.getName().equals(((District)((SNode)stack1.peek()).getKey()).getName())) {
			stack2.push(stack1.pop());
		}
		if(stack1.isEmpty()) {
			while(!stack2.isEmpty()) stack1.push(stack2.pop());
			if(!stack1.isEmpty()) {
				LocationScreen.setDistrict((District)((SNode)stack1.peek()).getKey());
				MartyrsScreen.setLocation(LocationScreen.getLocation());
				MartyrsScreen.setDate();
			}
		}
		getFirstDistrict();
	}
	public static void fillStack(TNode node) {
		if(node==null) return;
		fillStack(node.getRight());
		stack1.push(node.getData());
		cmb.getItems().add((District)node.getData());
		fillStack(node.getLeft());
	}
	public static void getFirstDistrict() {
		if(stack1.getSize()==1) next.setDisable(true);
		else next.setDisable(false);
		if(stack2.isEmpty()) prev.setDisable(true);
		else prev.setDisable(false);
		if(Driver.getDistricts()!=null && Driver.getDistricts().getRoot()!=null) {
			district=(District)((SNode)stack1.peek()).getKey();
			dislb.setText(district.toString());
			numlb.setText("The number of martyrs is "+district.getLocations().countMartyrs(district.getLocations().getRoot()));
			cmb.setValue(district);
			delete.setDisable(false);
			update.setDisable(false);
			cmb.setDisable(false);
			vb4.getChildren().remove(vb3);
			vb4.getChildren().add(vb3);
			if(stack1.getSize()==1) {
				LocationScreen.setDistrict((District)((SNode)stack1.peek()).getKey());
				MartyrsScreen.setLocation(LocationScreen.getLocation());
				MartyrsScreen.setDate();
			}
			MenuFX.getLoc().setDisable(false);
		}
		else {
			MenuFX.getLoc().setDisable(true);
			delete.setDisable(true);
			update.setDisable(true);
			cmb.setDisable(true);
			vb4.getChildren().remove(vb3);
		}
	}
	public static District getDistrict() {
		return district;
	}
	public Label getDislb() {
		return dislb;
	}
}
