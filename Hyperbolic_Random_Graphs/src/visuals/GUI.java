package visuals;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import hyperbolicGraphConstruction.HyperbolicMath;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.scene.control.ComboBox;

/** This application creates a GUI to visualise a Geometric Random Hyperbolic graph on the Poincare disk. The graph
 * contains three main parameters given by textfields, and drop down for type of projection.
 * 
 * These parameters are alpha, maxRadius and numberOfNodes, theses are described in HyperbolicMath.java documentation. The
 * projection type is discussed in EuclideanConversion.java. 
 * 
 * 
 * @author Jordan
 * @data 30/12/2020
 *
 */

public class GUI extends Application {
	
	public static void main(String[] args) {
		
		launch(args);
	}
	
	private TextField alphaInput = new TextField("" + HyperbolicMath.alpha);
	private TextField maxRadiusInput = new TextField("" + HyperbolicMath.MaxRadius);
	private TextField nodesInput = new TextField("" + HyperbolicMath.numberOfNodes);
	
	private Button reDrawButton = new Button("Re-Draw Graph");
	
	private Label alphaLabel = new Label("Alpha =");
	private Label radiusLabel = new Label("Max Radius =");
	private Label numberOfNodesLabel = new Label("Number of Nodes =");
	private Label conversionTypeLabel = new Label("Type of projection:");
	
	private ComboBox<String> dropdown = new ComboBox<String>(); 		

	private BorderPane root = new BorderPane();
	
	public void start(Stage stage) {
	    
		dropdown.getItems().addAll("Euclidean", "Linear Scale Factor");
		dropdown.getSelectionModel().select(1);
		
		reDrawButton.setDefaultButton(true);
		reDrawButton.setOnAction(e -> {
			
			setValues();
			HyperbolicCanvas stackedCanvas = new HyperbolicCanvas(HyperbolicMath.numberOfNodes);
		    root.setCenter(stackedCanvas);
		    stackedCanvas.reDraw();
			
		});
		
		
		HBox buttonbar = new HBox(20, alphaLabel, alphaInput, radiusLabel, maxRadiusInput,  numberOfNodesLabel, nodesInput, conversionTypeLabel, dropdown, reDrawButton);
		
		
		root.setBottom(buttonbar);
		
		Scene scene = new Scene(root, Screen.getPrimary().getVisualBounds().getWidth()-90, Screen.getPrimary().getVisualBounds().getHeight()-90);
		
		stage.setScene(scene);
		stage.show();
		stage.setResizable(true);
		stage.setTitle("Hyperbolic Random Graph");
		}
	
 
public void setValues() {
	
	HyperbolicMath.setAlpha(alphaInput.getText());
	HyperbolicMath.setMaxRadius(maxRadiusInput.getText());
	HyperbolicMath.setNumerOfNodes(nodesInput.getText());
	
	if(dropdown.getValue() == "Euclidean") { EuclideanConversion.setuseEuclideanProjection(true);}
	else {EuclideanConversion.setuseEuclideanProjection(false);}
	
	}	
	

	
}
