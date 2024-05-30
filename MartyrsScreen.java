package Phase2;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.function.UnaryOperator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MartyrsScreen {
	private static VBox vb, vb2, vb3, vb4, vb5, vb6, vb7;
	//private static ComboBox<Date> cmb;
	private static Button update, delete, insert, next, prev, search, insertstg, delstg, upstg, updatestg;
	private static HBox hb, hb2, hb3, hb4, hb5;
	private static Label up_de, inslb, dislb, avglb, younglb, oldlb, searchlb, stglb, namestg, datestg, agestg, genderstg, loclb, stglabel, upgender, upname, upage;
	private TextField deltxt, searchtxt, nametxt, agetxt, agetxtstg, nametxtstg;
	private Alert alert, alert2;
	private static Location location;
	private static Date date;
	private static Stack stack1, stack2;
	private static TableView<Martyr> tv, stv;
	private ComboBox<String> cmb, cmb2;
	private DatePicker dp;
	private Stage stg, sestg, upstage;
	private GridPane gp, gp2;
	private static ObservableList<Martyr> data, sedata;
	
	public MartyrsScreen() throws IOException{
		stack1 = new Stack();
		stack2 = new Stack();
		vb = new VBox(20);
		vb.setPadding(new Insets(10,10,10,10));
		vb.setAlignment(Pos.CENTER);
		vb.setStyle("-fx-background-radius: 10px; -fx-border-color: grey; -fx-border-radius: 10px;");
		loclb = new Label();
		loclb.setStyle("-fx-font-size: 30; -fx-font-weight: bold;");
		deltxt = new  TextField();
		deltxt.setStyle("-fx-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold;");
		update = new Button("Update");
		update.setStyle("-fx-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold; -fx-background-radius: 10px;");
		delete = new Button("Delete");
		alert = new Alert(AlertType.WARNING);
		alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
		alert.setTitle("Warning Dialog");
        alert.setHeaderText("This is a warning!");
        alert2 = new Alert(AlertType.ERROR);
		alert2.setTitle("Error Dialog");
        alert2.setHeaderText("An error has occurred!");

		up_de = new Label("Enter the name you want to delete or update");
		up_de.setStyle("-fx-color: #7C15D8; -fx-font-size: 17; -fx-font-weight: bold;");
		delete.setStyle("-fx-font-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: #7C15D8;");
		
		stg = new Stage();
		Image icon = new Image(new FileInputStream("palestine.png"));
		stg.getIcons().add(icon);
		stg.setTitle("Insert New Martyr");
		stglb = new Label("Fill the martyrs data");
		stglb.setStyle("-fx-color: #7C15D8; -fx-font-size: 22; -fx-font-weight: bold;");
		namestg = new Label("Name: ");
		namestg.setStyle("-fx-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold;");
		agestg = new Label("Age: ");
		agestg.setStyle("-fx-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold;");
		genderstg = new Label("Gender: ");
		genderstg.setStyle("-fx-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold;");
		datestg = new Label("Date: ");
		datestg.setStyle("-fx-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold;");
		Locale.setDefault(Locale.ENGLISH);
		dp = new DatePicker();
		dp.getEditor().setDisable(true);
		dp.setStyle("-fx-font-size: 15; -fx-font-weight: bold;");
		cmb = new ComboBox<>();
		cmb.getItems().addAll("M","F");
		cmb.setStyle("-fx-font-size: 15; -fx-font-weight: bold;");
		nametxt = new TextField();
		nametxt.setStyle("-fx-font-size: 15; -fx-font-weight: bold;");
		agetxt = new TextField();
		agetxt.setStyle("-fx-font-size: 15; -fx-font-weight: bold;");
		UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        TextFormatter<String> textFormatter2 = new TextFormatter<>(filter);
        agetxt.setTextFormatter(textFormatter);
		insertstg = new Button("Insert");
		insertstg.setStyle("-fx-color: #7C15D8; -fx-font-size: 15; -fx-font-weight: bold; -fx-background-radius: 10px;");
		gp = new GridPane();
		gp.setVgap(10);
		gp.setHgap(10);
		gp.add(namestg, 0, 0);
		gp.add(nametxt, 1, 0);
		gp.add(agestg, 0, 1);
		gp.add(agetxt, 1, 1);
		gp.add(datestg, 0, 2);
		gp.add(dp, 1, 2);
		gp.add(genderstg, 0, 3);
		gp.add(cmb, 1, 3);
		gp.add(insertstg, 0, 4);
		gp.setAlignment(Pos.CENTER);
		gp.setPadding(new Insets(10,10,10,10));
		VBox vbb = new VBox(10);
		vbb.setPadding(new Insets(10,10,10,10));
		vbb.getChildren().addAll(stglb, gp);
		vbb.setAlignment(Pos.CENTER);
		stg.setScene(new Scene(vbb, 400, 300));
		insert = new Button("Insert");
		insert.setStyle("-fx-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold; -fx-background-radius: 10px;");
		inslb = new Label("Insert new martyr");
		inslb.setStyle("-fx-color: #7C15D8; -fx-font-size: 17; -fx-font-weight: bold;");
		insert.setOnAction(e->{
			nametxt.setText("");
			agetxt.setText("");
			dp.setValue(null);
			cmb.setValue("Gender");
			stg.show();
		});
		Alert alert3 = new Alert(AlertType.INFORMATION);
        alert3.setTitle("Success Dialog");
        alert3.setHeaderText("This is a congratulation!");
		insertstg.setOnAction(e->{
			if(nametxt.getText().isBlank() || agetxt.getText().isBlank() || dp.getValue()==null || cmb.getValue().equals("Gender")) {
				alert2.setContentText("Please fill the blanks.");
				alert2.showAndWait();
			}
			else if(dp.getValue().isAfter(LocalDate.now())){
				alert2.setContentText("Invalid date");
				alert2.showAndWait();
			}
			else {
				try {
					if(Integer.parseInt(agetxt.getText())<0 || Integer.parseInt(agetxt.getText())>130) throw new NumberFormatException();
					Martyr m = new Martyr(nametxt.getText(), cmb.getValue(), Integer.parseInt(agetxt.getText()));
					String[] date = dp.getValue().toString().split("-");
					Date d = new Date(date[1]+"/"+date[2]+"/"+date[0]);
					Date dd = (Date)location.getDates().insert(d);
					if(dd.getMartyrs().find(m)) {
						alert2.setContentText("The martyr is added before");
						alert2.showAndWait();
					}
					else {
						dd.getMartyrs().add(m);
						setDate();
						MenuFX.setSaved(false);
						alert3.setContentText("Inserted successfully");
						alert3.showAndWait();
					}
					//((Date)location.getDates().insert(d)).getMartyrs().add(m);
				} catch(NumberFormatException ex) {
					alert2.setContentText("Invalid age: Integer 0-130 required");
					alert2.showAndWait();
				}
			}
		});

		search = new Button("Search");
		search.setStyle("-fx-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold; -fx-background-radius: 10px;");
		searchlb = new Label("Search about martyrs then delete or update them");
		searchlb.setStyle("-fx-font-size: 17; -fx-font-weight: bold;");
		searchtxt = new TextField();
		searchtxt.setStyle("-fx-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold;");
		vb5 = new VBox(10);
		vb5.getChildren().addAll(searchlb, searchtxt, search);
		stv = new TableView<Martyr>();
		sedata = FXCollections.observableArrayList();
	    TableColumn<Martyr, String> nameColumn1 = new TableColumn<>("Name");
	    TableColumn<Martyr, Integer> ageColumn1 = new TableColumn<>("Age");
	    TableColumn<Martyr, String> genderColumn1 = new TableColumn<>("Gender");

	    stv.getColumns().addAll(nameColumn1, ageColumn1, genderColumn1);
        nameColumn1.setCellValueFactory(new PropertyValueFactory<>("Name"));
	    ageColumn1.setCellValueFactory(new PropertyValueFactory<>("Age"));
	    genderColumn1.setCellValueFactory(new PropertyValueFactory<>("Gender"));
	    
	    nameColumn1.setStyle( "-fx-alignment: CENTER;");
	    ageColumn1.setStyle( "-fx-alignment: CENTER;");
	    genderColumn1.setStyle( "-fx-alignment: CENTER;");

	    stv.setItems(sedata);
	    stv.setMinWidth(400);
	    stv.setMaxHeight(240);
	    stv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	    stv.setStyle("-fx-font-size: 14.5px; -fx-border-color: #7C15D8; -fx-border-width: 2px;");
	    
	    delstg = new Button("Delete");
	    delstg.setStyle("-fx-font-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: #7C15D8;");
	    upstg = new Button("Update");
	    upstg.setStyle("-fx-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold; -fx-background-radius: 10px;");
	    sestg = new Stage();
	    Image icon2 = new Image(new FileInputStream("palestine.png"));
		sestg.getIcons().add(icon2);
	    vb6 = new VBox(15);
	    hb5 = new HBox(10);
	    hb5.getChildren().addAll(upstg, delstg);
	    vb6.getChildren().addAll(stv, hb5);
	    vb6.setAlignment(Pos.CENTER);
	    hb5.setAlignment(Pos.CENTER);
	    vb6.setPadding(new Insets(10, 10, 10, 10));
	    sestg.setScene(new Scene(vb6));
	    stv.setMaxWidth(430);
	    alert = new Alert(AlertType.WARNING);
		alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
		alert.setTitle("Warning Dialog");
        alert.setHeaderText("This is a warning!");
        
        upstage = new Stage();
        gp2 = new GridPane();
        gp2.setVgap(10);
        gp2.setHgap(10);
        vb7 = new VBox(15);
        vb7.setAlignment(Pos.CENTER);
        vb7.setPadding(new Insets(15, 15, 15, 15));
        updatestg = new Button("Update");
        updatestg.setStyle("-fx-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold; -fx-background-radius: 10px;");
        stglabel = new Label("Enter the new data");
        stglabel.setStyle("-fx-color: #7C15D8; -fx-font-size: 22; -fx-font-weight: bold;");
        upgender = new Label("Gender");
        upgender.setStyle("-fx-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold;");
        upname = new Label("Name");
        upname.setStyle("-fx-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold;");
        upage = new Label("Age");
        upage.setStyle("-fx-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold;");
        agetxtstg = new TextField();
        agetxtstg.setStyle("-fx-font-size: 15; -fx-font-weight: bold;");
        agetxtstg.setTextFormatter(textFormatter2);
        nametxtstg = new TextField();
        nametxtstg.setStyle("-fx-font-size: 15; -fx-font-weight: bold;");
        cmb2 = new ComboBox<>();
        cmb2.getItems().addAll("M", "F");
        cmb2.setStyle("-fx-font-size: 15; -fx-font-weight: bold;");
		gp2.add(upname, 0, 0);
		gp2.add(nametxtstg, 1, 0);
		gp2.add(upage, 0, 1);
		gp2.add(agetxtstg, 1, 1);
		gp2.add(upgender, 0, 2);
		gp2.add(cmb2, 1, 2);
		gp2.add(updatestg, 0, 3);
		gp2.setAlignment(Pos.CENTER);
		gp2.setPadding(new Insets(10,10,10,10));
		vb7.getChildren().addAll(stglabel, gp2);
		upstage.setScene(new Scene(vb7));
		ButtonType okButton = alert.getButtonTypes().get(0);
	    delstg.setOnAction(e->{
	    	Martyr martyr = stv.getSelectionModel().getSelectedItem();
	    	if(martyr!=null) {
	    		alert.setContentText("Are you sure to delete "+martyr.getName()+"?!");

		        alert.setOnCloseRequest(event -> {
		        	if (alert.getResult() == okButton) {
		        		deleteFromLocation(location.getDates().getRoot(), martyr);
		                sedata.remove(martyr);
		                data.remove(martyr);
		                MenuFX.setSaved(false);
		        	}
		        });
		        alert.showAndWait();
	    	}
	    	else {
	    		alert2.setContentText("Please select a martyr");
				alert2.showAndWait();
	    	}

        });
	    upstg.setOnAction(e->{
	    	Martyr martyr = stv.getSelectionModel().getSelectedItem();
	    	if(martyr!=null) {
	    		nametxtstg.setText(martyr.getName());
	    		agetxtstg.setText(martyr.getAge()+"");
	    		cmb2.setValue(martyr.getGender());
	    		upstage.show();
	    	}
	    	else {
	    		alert2.setContentText("Please select a martyr");
				alert2.showAndWait();
	    	}
	    });
	    updatestg.setOnAction(e->{
	    	Martyr martyr = stv.getSelectionModel().getSelectedItem();
	    	if(nametxtstg.getText().isBlank() || agetxtstg.getText().isBlank()) {
	    		alert2.setContentText("Fill the blanks");
				alert2.showAndWait();
	    	}
	    	else {
	    		if(!findMartyr(nametxtstg.getText()) || nametxtstg.getText().equals(martyr.getName())) {
	    			alert.setContentText("Are you sure to update "+martyr.getName()+"?!");
			        alert.setOnCloseRequest(event -> {
			        	if (alert.getResult() == okButton) {
			        		try{
				    			int age=Integer.parseInt(agetxtstg.getText());
					    		if(age<=0 || age>130) throw new NumberFormatException();
					    		martyr.setAge(age);
					    		martyr.setName(nametxtstg.getText());
					    		martyr.setGender(cmb2.getValue());
					    		sedata.clear();
					    		avglb.setText("The average of ages is: " + date.averageAges());
								younglb.setText("The youngest martyr is: " + date.getYoungest());
								oldlb.setText("The oldest martyr is: " + date.getOldest());
								fillTv();
					    		MenuFX.setSaved(false);
					    		searchInLocation(location.getDates().getRoot(), searchtxt.getText());
					    		upstage.close();
				    		} catch(NumberFormatException ex) {
				    			alert2.setContentText("Invalid age, integer from 0 to 130 required");
								alert2.showAndWait();
				    		}
			        	}
			        });
			        alert.showAndWait();
	    		}
	    		else {
		    		alert2.setContentText("The new name is exist before");
					alert2.showAndWait();
		    	}
	    	}
	    });
	    search.setOnAction(e->{
	    	if(searchtxt.getText().isBlank()) {
	    		alert2.setContentText("Please fill the blank");
				alert2.showAndWait();
	    	}
	    	else {
	    		sedata.clear();
	    		searchInLocation(location.getDates().getRoot(), searchtxt.getText());
	    		if(sedata.size()==0) {
	    			alert2.setContentText("No martyrs found");
					alert2.showAndWait();
	    		}
	    		else {
	    			sestg.setTitle("Martyrs who contains \'"+searchtxt.getText()+"\' On Their Names");
	    			sestg.show();
	    		}
	    	}
	    });
		
		dislb = new Label("District name");
		dislb.setStyle("-fx-font-size: 26; -fx-font-weight: bold;");
		avglb = new Label("The ages average is 25.5");
		avglb.setStyle("-fx-font-size: 17; -fx-font-weight: bold;");
		younglb = new Label("The youngest martyr is ayman");
		younglb.setStyle("-fx-font-size: 17; -fx-font-weight: bold;");
		oldlb = new Label("The oldest martyr is ahmad");
		oldlb.setStyle("-fx-font-size: 17; -fx-font-weight: bold;");
		next = new Button("Next");
		next.setStyle("-fx-font-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold; -fx-border-width: 2.6px; -fx-border-color: #7C15D8; -fx-border-style: hidden hidden solid hidden;");
		next.setOnAction(e->{
			stack2.push(stack1.pop());
			date=(Date)((SNode)stack1.peek()).getKey();
			dislb.setText(date.toString());
			avglb.setText("The average of ages is: " + date.averageAges());
			younglb.setText("The youngest martyr is: " + date.getYoungest());
			oldlb.setText("The oldest martyr is: " + date.getOldest());
			fillTv();
			if(stack1.getSize()==1) next.setDisable(true);
			else next.setDisable(false);
			if(stack2.isEmpty()) prev.setDisable(true);
			else prev.setDisable(false);
		});
		prev = new Button("Previous");

		prev.setStyle("-fx-font-color: #7C15D8; -fx-font-size: 16; -fx-font-weight: bold; -fx-border-width: 2.6px; -fx-border-color: #7C15D8; -fx-border-style: hidden hidden solid hidden;");
		prev.setOnAction(e->{
			stack1.push(stack2.pop());
			date=(Date)((SNode)stack1.peek()).getKey();
			dislb.setText(date.toString());
			avglb.setText("The average of ages is: " + date.averageAges());
			younglb.setText("The youngest martyr is: " + date.getYoungest());
			oldlb.setText("The oldest martyr is: " + date.getOldest());
			fillTv();
			if(stack1.getSize()==1) next.setDisable(true);
			else next.setDisable(false);
			if(stack2.isEmpty()) prev.setDisable(true);
			else prev.setDisable(false);
		});
		
		tv = new TableView<Martyr>();
		data = FXCollections.observableArrayList();
	    TableColumn<Martyr, String> nameColumn = new TableColumn<>("Name");
	    TableColumn<Martyr, Integer> ageColumn = new TableColumn<>("Age");
	    TableColumn<Martyr, String> genderColumn = new TableColumn<>("Gender");

	    tv.getColumns().addAll(nameColumn, ageColumn, genderColumn);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
	    ageColumn.setCellValueFactory(new PropertyValueFactory<>("Age"));
	    genderColumn.setCellValueFactory(new PropertyValueFactory<>("Gender"));
	    
	    nameColumn.setStyle( "-fx-alignment: CENTER;");
	    ageColumn.setStyle( "-fx-alignment: CENTER;");
	    genderColumn.setStyle( "-fx-alignment: CENTER;");

	    tv.setItems(data);
	    tv.setMinWidth(400);
	    tv.setMaxHeight(200);
	    tv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	    tv.setStyle("-fx-font-size: 14.5px; -fx-border-color: #7C15D8; -fx-border-width: 2px;");
		
		hb3 = new HBox(15);
		hb3.getChildren().addAll(prev, next);
		hb3.setAlignment(Pos.CENTER);
		vb3 = new VBox(15);
		vb3.getChildren().addAll(dislb, avglb, younglb, oldlb, tv, hb3);
		vb3.setAlignment(Pos.CENTER);
		vb2 = new VBox(20);
		vb2.setPadding(new Insets(10,10,10,10));
		vb2.setAlignment(Pos.CENTER);
		vb2.setStyle("-fx-background-radius: 10px; -fx-border-color: grey; -fx-border-radius: 10px;");
		vb5.setPadding(new Insets(10,10,10,10));
		vb5.setAlignment(Pos.CENTER);
		vb5.setStyle("-fx-background-radius: 10px; -fx-border-color: grey; -fx-border-radius: 10px;");
		vb2.getChildren().addAll(inslb, insert);
		hb = new HBox(15);
		hb.setAlignment(Pos.CENTER);
		hb.getChildren().addAll(update, delete);
		vb.getChildren().addAll(up_de, deltxt, hb);
		hb2 = new HBox(15);
		hb2.setAlignment(Pos.CENTER);
		hb2.getChildren().addAll(vb2, vb5);
		hb4 = new HBox(30);
	    hb4.getChildren().addAll(vb3);
		vb4 = new VBox(25);
		vb4.getChildren().addAll(loclb, hb2, hb4);
		hb4.setAlignment(Pos.CENTER);
		vb4.setAlignment(Pos.CENTER);
		VBox.setMargin(loclb, new Insets(30, 0, 0, 0));
	}
	public static void setDate() {
		Date dtemp=date;
		stack1.clear();
		stack2.clear();
		//cmb.getItems().clear();
		fillStack(location.getDates().getRoot());
		while(!stack1.isEmpty()  && dtemp!=null && !dtemp.equals(((Date)((SNode)stack1.peek()).getKey()))) {
			stack2.push(stack1.pop());
		}
		if(stack1.isEmpty()) {
			while(!stack2.isEmpty()) {
				stack1.push(stack2.pop());
			}
		}
		getFirstDate();
	}
	public static void fillStack(TNode node) {
		if(node==null) return;
		fillStack(node.getRight());
		stack1.push(node.getData());
		//cmb.getItems().add((Date)node.getData());
		fillStack(node.getLeft());
	}
	public static void getFirstDate() {
		if(stack1.getSize()==1) next.setDisable(true);
		else next.setDisable(false);
		if(stack2.isEmpty()) prev.setDisable(true);
		else prev.setDisable(false);
		if(stack1.peek()!=null) {
			date=(Date)((SNode)stack1.peek()).getKey();
			loclb.setText(location.getName());
			dislb.setText(date.toString());
			avglb.setText("The average of ages is: " + date.averageAges());
			younglb.setText("The youngest martyr is: " + date.getYoungest());
			oldlb.setText("The oldest martyr is: " + date.getOldest());
			fillTv();
			vb4.getChildren().remove(hb4);
			vb4.getChildren().add(hb4);
			MenuFX.getMar().setDisable(false);
		}
		else {
			//MenuFX.getMar().setDisable(true);
			delete.setDisable(true);
			update.setDisable(true);
			//cmb.setDisable(true);
			vb4.getChildren().remove(hb4);
		}
	}
	private static void fillTv(){
		SNode node = date.getMartyrs().getFirst();
		BinarySearchTree martyrsbst = new BinarySearchTree();
		data.clear();
		tv.getItems().clear();
		while(node!=null) {
			martyrsbst.insert((Martyr)node.getKey());
			node=node.getNext();
		}
		fillData(martyrsbst.getRoot());
	}
	private static void fillData(TNode node) {
		if(node==null) return;
		fillData(node.getLeft());
		data.add((Martyr)node.getData());
		fillData(node.getRight());
	}
	
	private void searchInLocation(TNode node, String name) {
		if(node==null) return;
		searchInLocation(node.getLeft(), name);
		searchMartyr(((Date)node.getData()).getMartyrs().getFirst(), name);
		searchInLocation(node.getRight(), name);
	}
	
	private void searchMartyr(SNode node, String name) {
		while(node!=null) {
			if(((Martyr)node.getKey()).getName().contains(name)) sedata.add((Martyr)node.getKey());
			node=node.getNext();
		}
	}
	
	private void deleteFromLocation(TNode node, Martyr m) {
		if(node==null) return;
		if(deleteMartyr((Date)node.getData(), m)) return;
		deleteFromLocation(node.getLeft(), m);
		deleteFromLocation(node.getRight(), m);
	}
	
	private boolean deleteMartyr(Date date, Martyr m) {
		SNode node = date.getMartyrs().getFirst();
		while(node!=null) {
			if(((Martyr)node.getKey()).getName().contains(m.getName())) {
				date.getMartyrs().remove(m);
				return true;
			}
			node=node.getNext();
		}
		return false;
	}
	private boolean findMartyr(String name) {
		for(int i=0; i<sedata.size(); i++) {
			if(sedata.get(i).getName().equals(name)) return true;
		}
		return false;
	}
	
	public static VBox getVb() {
		return vb4;
	}
	public Label getDislb() {
		return dislb;
	}

	public static Location getLocation() {
		return location;
	}

	public static void setLocation(Location location) {
		MartyrsScreen.location = location;
	}

	public static Date getDate() {
		return date;
	}

	public static void setDate(Date date) {
		MartyrsScreen.date = date;
	}
	
}
