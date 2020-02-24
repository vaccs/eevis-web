import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.beans.value.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.*;
import javafx.stage.*;
 
 
public class UIExpressionEvaluation extends Application {

  FileChooser fileChooser, fileChooserSave;

  ExpressionEvaluation ev;
  Label lblEquation;
  GridPane tblVariables = new GridPane();
  GridPane tblEvaluation = new GridPane();
  VBox boxBuildEquation = new VBox();
  GridPane buildEquationContainer = new GridPane();
  TextField txtEquation;
 
  public static void main(String[] args) {
  
    // redirect stderr
    try {
      FileOutputStream f = new FileOutputStream("errorlog.txt"); 
      System.setErr(new PrintStream(f));
    }
    catch (Exception e) {
      System.err.println(e);
    }
  
    launch(args);
  }
  
  @Override
  public void start(Stage primaryStage) throws IOException {
  
    fileChooser = new FileChooser();
    FileChooser.ExtensionFilter extFilter =
      new FileChooser.ExtensionFilter("Equation files (*.vaccs.ascii)", "*.vaccs.ascii");
    fileChooser.getExtensionFilters().add(extFilter);
    fileChooser.setTitle("Load Equation");
    
    fileChooserSave = new FileChooser();
    FileChooser.ExtensionFilter extFilterSave =
      new FileChooser.ExtensionFilter("C source files (*.c)", "*.c");
    fileChooserSave.getExtensionFilters().add(extFilterSave);
    fileChooserSave.setTitle("Save Equation");
  
    primaryStage.setTitle("Expression Evaluation Vis");
    Group root = new Group();

    Image image = new Image("assets/ConversionRules.png");
    ImageView imv = new ImageView();
    imv.setImage(image);
    imv.setPreserveRatio(true);
    imv.setSmooth(true);
    imv.setCache(true);
//    imv.setFitWidth(100);
    
    Button btnLoad = new Button("Load Equation");
    btnLoad.setStyle("-fx-font-size: 16;");
    btnLoad.setPadding(new Insets(10, 10, 10, 10));
    btnLoad.setOnAction(e -> {
      File file = fileChooser.showOpenDialog(primaryStage);
      if (file != null) {
        String absolutePath = file.getAbsolutePath();
        try {
          parseEvaluation(absolutePath);
          createdVariables = 0;
          boxBuildEquation.getChildren().clear();
          List<HBox> newVariableRows = populateEquationEditor();
          for (HBox h : newVariableRows) boxBuildEquation.getChildren().add(h);
        }
        catch (Exception ex) {
          System.err.println(ex);
        }
      }
    });
    Tooltip tipLoad = new Tooltip("Select an equation analysis file to load");
    Tooltip.install(btnLoad, tipLoad);
    
    lblEquation = new Label("-----");
    lblEquation.setStyle("-fx-font-size: 16;");
//    lblEquation.setPadding(new Insets(10, 10, 10, 10));
    
//    tblVariables.setHgap(12);
//    tblVariables.setVgap(8);
    tblVariables.setPadding(new Insets(10, 10, 10, 10));

//    tblEvaluation.setHgap(12);
//    tblEvaluation.setVgap(8);
    tblEvaluation.setPadding(new Insets(10, 10, 10, 10));
    
    GridPane subLayout = new GridPane();
    subLayout.add(createCell(lblEquation, "#000000", "#ffffff", ""), 0, 0, 1, 1);
    subLayout.add(btnLoad, 1, 0, 1, 1);
    subLayout.setPadding(new Insets(10, 10, 10, 10));
    
    boxBuildEquation = new VBox();
    Label lblBuild1 = new Label("Create Custom Equation");
    lblBuild1.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");
    
    Button btnHelp = new Button("Help");
    btnHelp.setStyle("-fx-font-size: 16;");
    btnHelp.setOnAction(e -> {
      final Stage helpWindow = new Stage();
      helpWindow.initModality(Modality.WINDOW_MODAL);
      Button btnOk = new Button("Close");
      btnOk.setOnAction(ev -> {
        helpWindow.close();
      });
      VBox vbox = new VBox();
      vbox.getChildren().addAll(
          new Text("To create your own equation, first define the variables that will be used in" + System.lineSeparator() +
          "the equation. To define a variable, click the Add Variable button. The text box on the" + System.lineSeparator() +
          "left is where you can give the variable any unique, valid C variable name. You can use the" + System.lineSeparator() +
          "drop-down box to set the variable's type and the text field to enter its initial value." + System.lineSeparator() +
          "All variables require an initial value, even the one being assigned to. You can click the" + System.lineSeparator() +
          "Clear button to remove all variables you have defined. Then, enter your equation in the" + System.lineSeparator() +
          "provided field at the top. The equation must be a single line of valid C that consists of" + System.lineSeparator() +
          "the variables you defined and valid arithmetic operations, such as A = B * C / D + E." + System.lineSeparator() +
          "When you are ready to evaluate your equation, click the Evaluate button. You can save" + System.lineSeparator() +
          "the results of your evaluation to a file with the Save button." + System.lineSeparator()), btnOk);
      vbox.setAlignment(Pos.CENTER);
      vbox.setPadding(new Insets(10));
      Scene helpScene = new Scene(vbox);
      helpWindow.setScene(helpScene);
      helpWindow.show();
    });
    Tooltip tipHelp = new Tooltip("Open an explanation for how to create a custom equation");
    Tooltip.install(btnHelp, tipHelp);
    
    Button btnAddVariable = new Button("Add Variable");
    btnAddVariable.setStyle("-fx-font-size: 16;");
    btnAddVariable.setOnAction(e -> {
      HBox row = createBuildVariableRow(null, null, null);
      if (row != null) boxBuildEquation.getChildren().add(row);
    });
    Tooltip tipAddVariable = new Tooltip("Define a new variable to include in your custom equation");
    Tooltip.install(btnAddVariable, tipAddVariable);
    
    Button btnClear = new Button("Clear");
    btnClear.setStyle("-fx-font-size: 16;");
    btnClear.setOnAction(e -> {
      boxBuildEquation.getChildren().clear();
      customVariables.clear();
    });
    Tooltip tipClear = new Tooltip("Remove all of the variables you have defined");
    Tooltip.install(btnClear, tipClear);
    
    Button btnEvaluate = new Button("Evaluate");
    btnEvaluate.setStyle("-fx-font-size: 16;");
    btnEvaluate.setOnAction(e -> {
      runCustomAnalysis("customequation.c", true);
    });
    Tooltip tipEvaluate = new Tooltip("Perform and display analysis of the custom equation you have set up");
    Tooltip.install(btnEvaluate, tipEvaluate);
    
    Button btnSave = new Button("Save");
    btnSave.setStyle("-fx-font-size: 16;");
    btnSave.setOnAction(e -> {
      File file = fileChooserSave.showSaveDialog(primaryStage);
      if (file != null) {
        String absolutePath = file.getAbsolutePath();
        runCustomAnalysis(absolutePath, false);
      }
    });
    Tooltip tipSave = new Tooltip("Save your custom equation and analysis results to a file");
    Tooltip.install(btnSave, tipSave);
    
    txtEquation = new TextField("Enter Equation (e.g. A = B + C)");
    
    buildEquationContainer.setPadding(new Insets(10, 10, 10, 10));
    buildEquationContainer.add(lblBuild1,        0, 0, 3, 1);
    buildEquationContainer.add(btnHelp,          3, 0, 1, 1);
    buildEquationContainer.add(btnAddVariable,   0, 1, 1, 1);
    buildEquationContainer.add(btnClear,         1, 1, 1, 1);
    buildEquationContainer.add(btnEvaluate,      2, 1, 1, 1);
    buildEquationContainer.add(btnSave,          3, 1, 1, 1);
    buildEquationContainer.add(txtEquation,      0, 2, 4, 1);
    buildEquationContainer.add(boxBuildEquation, 0, 3, 4, 5);
    
    
    parseEvaluation("examples/test1.c.vaccs.ascii");
    createdVariables = 0;
    boxBuildEquation.getChildren().clear();
    List<HBox> newVariableRows = populateEquationEditor();
    for (HBox h : newVariableRows) boxBuildEquation.getChildren().add(h);
    
    // display /////////////////////////////////////////////////////////////////////////////////////
    GridPane layout = new GridPane();

    // no effect?
/*    ColumnConstraints leftCol = new ColumnConstraints();
    leftCol.setPercentWidth(40);
    ColumnConstraints rightCol = new ColumnConstraints();
    rightCol.setPercentWidth(60);
    layout.getColumnConstraints().addAll(leftCol, rightCol); /* */

    layout.add(subLayout,               0, 0, 1, 1);
    layout.add(imv,                     1, 0, 1, 4);
    layout.add(tblVariables,            0, 1, 1, 1);
    layout.add(buildEquationContainer,  0, 2, 1, 1);
    layout.add(tblEvaluation,           0, 4, 2, 1);
    
    primaryStage.widthProperty().addListener(new ChangeListener<Number>() {
      public void changed(ObservableValue<? extends Number> obs, Number oldWidth, Number newWidth) {
        imv.setFitWidth(newWidth.doubleValue() / 1.75);
      }
    });
//    imv.fitWidthProperty().bind(primaryStage.widthProperty());
    
//    layout.setMinWidth(1000);
//    layout.setMinHeight(500);
    
//    root.minWidth(1000);
//    root.minHeight(500);
    
//    primaryStage.setMinWidth(1000);
//    primaryStage.setMinHeight(500);
    
    root.getChildren().add(layout);
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }
  
  
  
  void parseEvaluation(String file) throws IOException {
    ev = new ExpressionEvaluation();
    
    List<String> fileContents = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader(file));
    String line = reader.readLine();
    while (line != null) {
      fileContents.add(line);
      line = reader.readLine();
    }
    ev.parseEvaluation(fileContents);
    
    lblEquation.setText(ev.equation);
    txtEquation.setText(ev.equation);
    buildVariablesTable();
    buildEvaluationTable();
  }
  
  
  // 	add(Node child, int columnIndex, int rowIndex, int colspan, int rowspan)
  
  void buildVariablesTable() {
    tblVariables.getChildren().clear();
    
//    ColumnConstraints columnCon = new ColumnConstraints(10); // left column for color-coding
//    tblVariables.getColumnConstraints().add(columnCon);
    
    Label colName =  new Label("Name");
    colName.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
    Label colType =  new Label("Type");
    colType.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
    Label colValue = new Label("Value");
    colValue.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
    tblVariables.add(createCell(colName, "#556688", "#aabbdd", ""), 0, 0, 1, 1);
    tblVariables.add(createCell(colType, "#556688", "#aabbdd", ""), 1, 0, 1, 1);
    tblVariables.add(createCell(colValue, "#556688", "#aabbdd", ""), 2, 0, 1, 1);
    
    int row = 1;
    for (ExpressionEvaluation.VariableRecord record : ev.variableTable) {
      Label rowName = new Label(record.name);
      rowName.setStyle("-fx-font-size: 16;");
      Label rowType = new Label(record.type);
      rowType.setStyle("-fx-font-size: 16;");
      Label rowValue = new Label(record.value);
      rowValue.setStyle("-fx-font-size: 16;");
      tblVariables.add(createCell(rowName, "#556688", "#ccddff", ""), 0, row, 1, 1);
      tblVariables.add(createCell(rowType, "#556688", "#ccddff", ""), 1, row, 1, 1);
      tblVariables.add(createCell(rowValue, "#556688", "#ccddff", ""), 2, row, 1, 1);
      ++row;
    }

  }
  
  
  void buildEvaluationTable() {
    tblEvaluation.getChildren().clear();
    
//    ColumnConstraints columnCon = new ColumnConstraints(10); // left column for color-coding
//    tblEvaluation.getColumnConstraints().add(columnCon);
    
    Label colOperation =  new Label("");
    Label colLhsType =  new Label("New Type");
    colLhsType.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
    Label colRhsTypes =  new Label("Initial Types");
    colRhsTypes.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
    Label colConversionRule =  new Label("Rule");
    colConversionRule.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
    Label colValue =  new Label("Result Value");
    colValue.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
    Label colValues =  new Label("Initial Values");
    colValues.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
    tblEvaluation.add(createCell(colOperation, "#558866", "#aaddbb", ""), 0, 0, 1, 1);
    tblEvaluation.add(createCell(colRhsTypes, "#558866", "#aaddbb", ""), 1, 0, 1, 1);
    tblEvaluation.add(createCell(colLhsType, "#558866", "#aaddbb", ""), 2, 0, 1, 1);
    tblEvaluation.add(createCell(colConversionRule, "#558866", "#aaddbb", ""), 3, 0, 1, 1);
    tblEvaluation.add(createCell(colValues, "#558866", "#aaddbb", ""), 4, 0, 1, 1);
    tblEvaluation.add(createCell(colValue, "#558866", "#aaddbb", ""), 5, 0, 1, 1);
    
    int row = 1;
    for (ExpressionEvaluation.EvaluationRow record : ev.evaluation) {
      Label rowOperation = new Label(record.operation);
      rowOperation.setStyle("-fx-font-size: 16;");
      Label rowLhsType = new Label(record.lhsType);
      rowLhsType.setStyle("-fx-font-size: 16;");
      Label rowRhsTypes = new Label(record.rhsTypes);
      rowRhsTypes.setStyle("-fx-font-size: 16;");
      Label rowConversionRule = new Label("" + record.conversionRule);
      rowConversionRule.setStyle("-fx-font-size: 16;");
      Label rowValue = new Label(record.values);
      rowValue.setStyle("-fx-font-size: 16;");
      Label rowValues = new Label(record.value);
      rowValues.setStyle("-fx-font-size: 16;");
      tblEvaluation.add(createCell(rowOperation, "#558866", "#ccffdd", ""), 0, row, 1, 1);
      tblEvaluation.add(createCell(rowRhsTypes, "#558866", "#ccffdd", ""), 1, row, 1, 1);
      tblEvaluation.add(createCell(rowLhsType, "#558866", "#ccffdd", ""), 2, row, 1, 1);
      tblEvaluation.add(createCell(rowConversionRule, "#558866", "#ccffdd", ""), 3, row, 1, 1);
      tblEvaluation.add(createCell(rowValue, "#558866", "#ccffdd", ""), 4, row, 1, 1);
      tblEvaluation.add(createCell(rowValues, "#558866", "#ccffdd", ""), 5, row, 1, 1);
      ++row;
    }
  }
  
  
  StackPane createCell(Label label, String borderColor, String bgColor, String otherCss) {
    StackPane cell = new StackPane();
    cell.getChildren().add(label);
    cell.setStyle("-fx-background-color: " + borderColor + ", " + bgColor + ";" +
                  "-fx-background-insets: 0, 1 1 1 1;" +
                  otherCss + ";");
    cell.setPadding(new Insets(5, 5, 5, 5));
    return cell;
  }
  
  
  
  
  int createdVariables = 0;
  List<Variable> customVariables = new ArrayList<>();
  
  class Variable {
    public String name;
    public String value;
    public String type;
  }
  
  
  
  HBox createBuildVariableRow(String name, String type, String value) {
    if (type != null && type.startsWith("signed ")) type = type.substring(7, type.length());
    HBox row = new HBox();
    
    ++createdVariables;
    final int idx = createdVariables-1;
    
    TextField txtName = new TextField((name == null ? "var_name" : name));
    txtName.textProperty().addListener(new ChangeListener<String>() {
      public void changed(ObservableValue<? extends String> observable,
                          String oldValue, String newValue)
      {
        Variable var = customVariables.get(idx);
        var.name = newValue;
      }
    });
    
    ObservableList<String> options = FXCollections.observableArrayList(
      "char", "unsigned short", "short", "unsigned int", "int", "unsigned long", "long"
    );
    ComboBox cboTypes = new ComboBox(options);
    cboTypes.getSelectionModel().select(4);
    if (type != null) {
      for (int n = 0; n < options.size(); ++n) {
        if (options.get(n).equals(type)) {
          cboTypes.getSelectionModel().select(n);
          break;
        }
      }
    }
    cboTypes.setOnAction(e -> {
      Variable var = customVariables.get(idx);
      var.type = (String)(cboTypes.getValue());
    });
    
    TextField txtValue = new TextField((value == null ? "Enter Value" : value));
    txtValue.textProperty().addListener(new ChangeListener<String>() {
      public void changed(ObservableValue<? extends String> observable,
                          String oldValue, String newValue)
      {
        String val = convertNumber(newValue);
        txtValue.setText(val);
        Variable var = customVariables.get(idx);
        var.value = val;
      }
    });
    
    row.getChildren().addAll(txtName, cboTypes, txtValue);
    
    Variable var = new Variable();
    var.name = "var_name";
    var.value = "Enter Value";
    var.type = "int";
    customVariables.add(var);
    
    return row;
  }
  
  
  int getIndexOfCreatedVariable(String name) {
    for (int n = 0; n < customVariables.size(); ++n) {
      if (customVariables.get(n).name.equals(name)) return n;
    }
    return -1;
  }
  
  
  void runCustomAnalysis(String filename, boolean toRead) {
    try {
      String contents = "";
      for (Variable var : customVariables) {
        contents += (var.type + " " + var.name + " = " + var.value + ";" + System.lineSeparator());
      }
      contents += (txtEquation.getText() + ";" + System.lineSeparator());
      
      FileWriter fileWriter = new FileWriter(filename);
      fileWriter.write(contents);
      fileWriter.close();
      
      Process proc = Runtime.getRuntime().exec("java -jar ceval.jar " + filename);
      proc.waitFor();
      
      if (toRead) parseEvaluation(filename + ".vaccs.ascii");
    }
    catch (Exception e) {
      System.err.println("Exception while attempting to evaluate custom equation: " + e);
    }
  }
  
  
  List<HBox> populateEquationEditor() {
    createdVariables = 0;
    List<HBox> newVariableRows = new ArrayList<>();
  
    for (ExpressionEvaluation.VariableRecord record : ev.variableTable) {
    
      ++createdVariables;
      HBox row = new HBox();
      
      newVariableRows.add(createBuildVariableRow(record.name, record.type, record.value));
    }
    
    return newVariableRows;
  }

  
  String convertNumber(String number) {
    String check = number.toUpperCase();
    if (check.equals("CHAR_MIN")) return "-128";
    else if (check.equals("CHAR_MAX")) return "127";
    else if (check.equals("SHRT_MIN")) return "-32768";
    else if (check.equals("SHRT_MAX")) return "32767";
    else if (check.equals("USHRT_MAX")) return "65535";
    else if (check.equals("INT_MIN")) return "-2147483648";
    else if (check.equals("INT_MAX")) return "2147483647";
    else if (check.equals("UINT_MAX")) return "4294967295";
    else if (check.equals("LONG_MIN")) return "-9223372036854775808";
    else if (check.equals("LONG_MAX")) return "9223372036854775807";
    else if (check.equals("ULONG_MAX")) return "18446744073709551615";
    return number;
  }
  
}
