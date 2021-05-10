package com.jpro.eevis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.lang.management.ManagementFactory;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.vaccs.eevis.driver.CExpr;
import org.vaccs.eevis.util.FileHandler;

import com.jpro.webapi.JProApplication;

import javafx.beans.value.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class UIExpressionEvaluation extends JProApplication {

  // FileChooser fileChooser, fileChooserSave;
  FileHandler fileLoadHandler;

  private ExpressionEvaluation ev;
  private Label lblEquation;
  private GridPane tblVariables = new GridPane();
  private GridPane tblEvaluation = new GridPane();
  private VBox boxBuildEquation = new VBox();
  private GridPane buildEquationContainer = new GridPane();
  private TextField txtEquation;

  public static void main(String[] args) {
    // redirect stderr
    try {
      FileOutputStream f = new FileOutputStream("errorlog.txt");
      System.setErr(new PrintStream(f));
    } catch (Exception e) {
      System.err.println(e);
    }

    launch(args);
  }

  public static void startApp(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws IOException {

    primaryStage.setTitle("Expression Evaluation Vis");

    Image image = new Image("file:assets/ConversionRules.png");
    ImageView imv = new ImageView();
    imv.setImage(image);
    imv.setPreserveRatio(true);
    imv.setSmooth(true);
    imv.setCache(true);
    // imv.setFitWidth(100);

    // uploadLabel.getStyleClass().add("file-handler");

    fileLoadHandler = new FileHandler(getWebAPI(), "Click, or Drag-and-Drop Equation", true);

    Button btnLoad = new Button("Use Equation");
    btnLoad.setDisable(true); // this is disabled until the file is completely loaded
    // btnLoad.setStyle("-fx-font-size: 16;");
    // btnLoad.setPadding(new Insets(10, 10, 10, 10));
    btnLoad.setOnAction(e -> {
      try {
        getWebAPI().downloadURL(fileLoadHandler.fileHandler.getUploadedFile().toURI().toURL());
      } catch (MalformedURLException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      File file = fileLoadHandler.fileHandler.getUploadedFile();
      if (file != null) {
        String absolutePath = file.getAbsolutePath();
        try {

          parseEvaluationFromString(CExpr.processCodeFromString("customequation.eevis", loadFile(absolutePath)));
          boxBuildEquation.getChildren().clear();
          List<HBox> newVariableRows = populateEquationEditor();
          for (HBox h : newVariableRows)
            boxBuildEquation.getChildren().add(h);
        } catch (Exception ex) {
          System.err.println(ex);
        }
      }
    });

    fileLoadHandler.fileHandler.progressProperty().addListener((obs, oldV, newV) -> {
      if (newV.doubleValue() == 1.0) {
        btnLoad.setDisable(false);
      }
    });

    VBox loadEquationBox = new VBox(1.0, fileLoadHandler, btnLoad);
    loadEquationBox.setSpacing(50);
    loadEquationBox.setAlignment(Pos.CENTER);

    Tooltip tipLoad = new Tooltip("Select an equation analysis file to load");
    Tooltip.install(loadEquationBox, tipLoad);

    // lblEquation.setPadding(new Insets(10, 10, 10, 10));

    // tblVariables.setHgap(12);
    // tblVariables.setVgap(8);
    tblVariables.setPadding(new Insets(10, 10, 10, 10));

    // tblEvaluation.setHgap(12);
    // tblEvaluation.setVgap(8);
    tblEvaluation.setPadding(new Insets(10, 10, 10, 10));

    lblEquation = new Label("-----");
    lblEquation.setStyle("-fx-font-size: 18;");
    GridPane subLayout = new GridPane();
    subLayout.add(createCell(lblEquation, "#000000", "#ffffff", ""), 0, 0, 1, 1);
    subLayout.add(loadEquationBox, 0, 1, 1, 1);
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
      vbox.getChildren()
          .addAll(new Text(
              "To create your own equation, first define the variables that will be used in" + System.lineSeparator()
                  + "the equation. To define a variable, click the Add Variable button. The text box on the"
                  + System.lineSeparator()
                  + "left is where you can give the variable any unique, valid C variable name. You can use the"
                  + System.lineSeparator()
                  + "drop-down box to set the variable's type and the text field to enter its initial value."
                  + System.lineSeparator()
                  + "All variables require an initial value, even the one being assigned to. You can click the"
                  + System.lineSeparator()
                  + "Clear button to remove all variables you have defined. Then, enter your equation in the"
                  + System.lineSeparator()
                  + "provided field at the top. The equation must be a single line of valid C that consists of"
                  + System.lineSeparator()
                  + "the variables you defined and valid arithmetic operations, such as A = B * C / D + E."
                  + System.lineSeparator()
                  + "When you are ready to evaluate your equation, click the Evaluate button. You can save"
                  + System.lineSeparator() + "the results of your evaluation to a file with the Save button."
                  + System.lineSeparator()),
              btnOk);
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
      HBox row = createBuildVariableRow(null, null, null, true);
      if (row != null)
        boxBuildEquation.getChildren().add(row);
    });
    Tooltip tipAddVariable = new Tooltip("Define a new variable to include in your custom equation");
    Tooltip.install(btnAddVariable, tipAddVariable);

    Button btnClear = new Button("Clear");
    btnClear.setStyle("-fx-font-size: 16;");
    btnClear.setOnAction(e -> {
      boxBuildEquation.getChildren().clear();
      createdVariables = 0;
      customVariables.clear();
    });
    Tooltip tipClear = new Tooltip("Remove all of the variables you have defined");
    Tooltip.install(btnClear, tipClear);

    Button btnEvaluate = new Button("Evaluate");
    btnEvaluate.setStyle("-fx-font-size: 16;");
    btnEvaluate.setOnAction(e -> {
      runCustomAnalysis("customequation.eevis", true);
    });
    Tooltip tipEvaluate = new Tooltip("Perform and display analysis of the custom equation you have set up");
    Tooltip.install(btnEvaluate, tipEvaluate);

    // fileSaveHandler = webAPI.makeFileUploadNode(uploadLabel);
    // fileSaveHandler.setSelectFileOnClick(true);

    Button btnSave = new Button("Save");
    btnSave.setStyle("-fx-font-size: 16;");
    btnSave.setOnAction(e -> {
      File file = null;// new File(fileSaveHandler.getSelectedFile());
      if (file != null) {
        String absolutePath = file.getAbsolutePath();
        saveFile(absolutePath);
      }
    });
    Tooltip tipSave = new Tooltip("Save your custom equation and analysis results to a file");
    Tooltip.install(btnSave, tipSave);

    txtEquation = new TextField("Enter Equation (e.g. A = B + C)");

    buildEquationContainer.setPadding(new Insets(10, 10, 10, 10));
    buildEquationContainer.add(lblBuild1, 0, 0, 3, 1);
    buildEquationContainer.add(btnHelp, 3, 0, 1, 1);
    buildEquationContainer.add(btnAddVariable, 0, 1, 1, 1);
    buildEquationContainer.add(btnClear, 1, 1, 1, 1);
    buildEquationContainer.add(btnEvaluate, 2, 1, 1, 1);
    buildEquationContainer.add(btnSave, 3, 1, 1, 1);
    buildEquationContainer.add(txtEquation, 0, 2, 4, 1);
    buildEquationContainer.add(boxBuildEquation, 0, 3, 4, 5);

    // parseEvaluation("examples/test1.c.vaccs.ascii");
    createdVariables = 0;
    // boxBuildEquation.getChildren().clear();
    // List<HBox> newVariableRows = populateEquationEditor();
    // for (HBox h : newVariableRows) boxBuildEquation.getChildren().add(h);

    // display
    // /////////////////////////////////////////////////////////////////////////////////////
    GridPane layout = new GridPane();

    // no effect?
    /*
     * ColumnConstraints leftCol = new ColumnConstraints();
     * leftCol.setPercentWidth(40); ColumnConstraints rightCol = new
     * ColumnConstraints(); rightCol.setPercentWidth(60);
     * layout.getColumnConstraints().addAll(leftCol, rightCol); /*
     */

    layout.add(subLayout, 0, 0, 1, 1);
    layout.add(imv, 1, 0, 1, 4);
    layout.add(tblVariables, 0, 1, 1, 1);
    layout.add(buildEquationContainer, 0, 2, 1, 1);
    layout.add(tblEvaluation, 0, 4, 2, 1);

    primaryStage.widthProperty().addListener(new ChangeListener<Number>() {
      public void changed(ObservableValue<? extends Number> obs, Number oldWidth, Number newWidth) {
        imv.setFitWidth(newWidth.doubleValue() / 1.75);
      }
    });
    // imv.fitWidthProperty().bind(primaryStage.widthProperty());

    // layout.setMinWidth(1000);
    // layout.setMinHeight(500);

    // root.minWidth(1000);
    // root.minHeight(500);

    // primaryStage.setMinWidth(1000);
    // primaryStage.setMinHeight(500);

    ScrollPane scrollPane = new ScrollPane(layout);
    scrollPane.setFitToHeight(true);

    BorderPane root = new BorderPane(scrollPane);
    root.setPadding(new Insets(1));
    root.setCenter(layout);

    Scene scene = new Scene(root, 500, 500);
    root.getStylesheets().add(getClass().getResource("/com/jpro/hellojpro/css/filehandler.css").toExternalForm());
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private String loadFile(String absolutePath) {
    String input = "";
    try {
      BufferedReader fr = new BufferedReader(new FileReader(absolutePath));
      String line;
      while ((line = fr.readLine()) != null) {
        input += line;
      }
      fr.close();
    } catch (IOException e) {
      System.err.println("Exception while attempting to load custom equation: " + e);
    }

    return input;
  }

  private void saveFile(String absolutePath) {
    try {
      String contents = "";
      for (Variable var : customVariables) {
        contents += (var.type + " " + var.name + " = " + (var.value.equals("<NOVALUE>") ? "0" : var.value) + ";"
            + System.lineSeparator());
      }
      contents += (txtEquation.getText() + ";" + System.lineSeparator());

      FileWriter fileWriter = new FileWriter(absolutePath);
      fileWriter.write(contents);
      fileWriter.close();

    } catch (Exception e) {
      System.err.println("Exception while attempting to save custom equation: " + e);
    }
  }

  void parseEvaluation(BufferedReader reader) throws IOException {
    ev = new ExpressionEvaluation();

    List<String> fileContents = new ArrayList<>();
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

    customVariables.clear();

    for (ExpressionEvaluation.VariableRecord record : ev.variableTable) {
      customVariables.add(new Variable(record.name, record.value, record.type));
    }
  }

  void parseEvaluationFromFile(String file) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    parseEvaluation(reader);
  }

  void parseEvaluationFromString(String analysis) throws IOException {
    BufferedReader reader = new BufferedReader(new StringReader(analysis));
    parseEvaluation(reader);
  }

  // add(Node child, int columnIndex, int rowIndex, int colspan, int rowspan)

  void buildVariablesTable() {
    tblVariables.getChildren().clear();

    // ColumnConstraints columnCon = new ColumnConstraints(10); // left column for
    // color-coding
    // tblVariables.getColumnConstraints().add(columnCon);

    Label colName = new Label("Name");
    colName.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
    Label colType = new Label("Type");
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

    // ColumnConstraints columnCon = new ColumnConstraints(10); // left column for
    // color-coding
    // tblEvaluation.getColumnConstraints().add(columnCon);

    Label colOperation = new Label("");
    Label colLhsType = new Label("New Type");
    colLhsType.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
    Label colRhsTypes = new Label("Initial Types");
    colRhsTypes.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
    Label colConversionRule = new Label("Rule");
    colConversionRule.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
    Label colValue = new Label("Result Value");
    colValue.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
    Label colValues = new Label("Initial Values");
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
    cell.setStyle("-fx-background-color: " + borderColor + ", " + bgColor + ";" + "-fx-background-insets: 0, 1 1 1 1;"
        + otherCss + ";");
    cell.setPadding(new Insets(5, 5, 5, 5));
    return cell;
  }

  int createdVariables = 0;
  List<Variable> customVariables = new ArrayList<>();

  class Variable {
    public String name;
    public String value;
    public String type;

    public Variable() {
    }

    public Variable(String name, String value, String type) {
      this.name = name;
      this.value = value;
      this.type = type;
    }
  }

  HBox createBuildVariableRow(String name, String type, String value, boolean isNew) {
    if (type != null && type.startsWith("signed "))
      type = type.substring(7, type.length());
    HBox row = new HBox();

    final int idx = createdVariables;
    ++createdVariables;

    TextField txtName = new TextField((name == null ? "var_name" : name));
    txtName.textProperty().addListener(new ChangeListener<String>() {
      public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        Variable var = customVariables.get(idx);
        var.name = newValue;
      }
    });

    ObservableList<String> options = FXCollections.observableArrayList("unsigned char", "char", "unsigned short",
        "short", "unsigned int", "int", "unsigned long", "long");
    ComboBox cboTypes = new ComboBox(options);
    cboTypes.getSelectionModel().select(5);
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
      var.type = (String) (cboTypes.getValue());
    });

    TextField txtValue = new TextField((value == null ? "Enter Value" : value));
    txtValue.textProperty().addListener(new ChangeListener<String>() {
      public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        String val = convertNumber(newValue);
        txtValue.setText(val);
        Variable var = customVariables.get(idx);
        var.value = val;
      }
    });

    // add a button to delete the variable
    Button btnXVariable = new Button("X");
    btnXVariable.setStyle("-fx-font-size: 14;");
    btnXVariable.setOnAction(e -> {
      // add a popup dialog to confirm yes/no
      ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
      ButtonType noButton = new ButtonType("No", ButtonData.YES);
      Dialog<ButtonType> dialog = new Dialog<>();
      dialog.setTitle("Variable Delete Dialog");
      dialog.setContentText("Are you sure you want to delete the variable " + customVariables.get(idx).name + "?");
      dialog.getDialogPane().getButtonTypes().add(yesButton);
      dialog.getDialogPane().getButtonTypes().add(noButton);
      boolean disabled = false; // computed based on content of text fields, for example
      dialog.getDialogPane().lookupButton(yesButton).setDisable(disabled);
      dialog.getDialogPane().lookupButton(noButton).setDisable(disabled);
      dialog.showAndWait().filter(response -> response.getText() == "Yes").ifPresent(response -> {
        customVariables.remove(idx);
        createdVariables--;
        row.getChildren().clear();
        boxBuildEquation.getChildren().remove(row);
      });
    });

    Tooltip tipXVariable = new Tooltip("Delete this variable");
    Tooltip.install(btnXVariable, tipXVariable);

    row.getChildren().addAll(txtName, cboTypes, txtValue, btnXVariable);

    if (isNew) {
      Variable var = new Variable();
      var.name = "var_name";
      var.value = "Enter Value";
      var.type = "int";
      customVariables.add(var);
    }

    return row;
  }

  int getIndexOfCreatedVariable(String name) {
    for (int n = 0; n < customVariables.size(); ++n) {
      if (customVariables.get(n).name.equals(name))
        return n;
    }
    return -1;
  }

  void runCustomAnalysis(String filename, boolean toRead) {
    try {
      String contents = "";
      for (Variable var : customVariables) {
        contents += (var.type + " " + var.name + " = " + (var.value.equals("<NOVALUE>") ? "0" : var.value) + ";"
            + System.lineSeparator());
      }
      contents += (txtEquation.getText() + ";" + System.lineSeparator());

      String analysis = CExpr.processCodeFromString(filename, contents);
      if (toRead)
        parseEvaluationFromString(analysis);
      // FileWriter fileWriter = new FileWriter(filename);
      // fileWriter.write(contents);
      // fileWriter.close();

      // Process proc = Runtime.getRuntime().exec("java -jar ceval.jar " + filename);
      // proc.waitFor();

      // if (toRead)
      // parseEvaluationFromFile(filename + ".vaccs.ascii");
    } catch (Exception e) {
      System.err.println("Exception while attempting to evaluate custom equation: " + e);
    }
  }

  List<HBox> populateEquationEditor() {
    createdVariables = 0;
    List<HBox> newVariableRows = new ArrayList<>();

    for (ExpressionEvaluation.VariableRecord record : ev.variableTable) {
      HBox row = new HBox();

      newVariableRows.add(createBuildVariableRow(record.name, record.type, record.value, false));
    }

    return newVariableRows;
  }

  String convertNumber(String number) {
    String check = number.toUpperCase();
    if (check.equals("CHAR_MIN"))
      return "-128";
    else if (check.equals("CHAR_MAX"))
      return "127";
    else if (check.equals("SHRT_MIN"))
      return "-32768";
    else if (check.equals("SHRT_MAX"))
      return "32767";
    else if (check.equals("USHRT_MAX"))
      return "65535";
    else if (check.equals("INT_MIN"))
      return "-2147483648";
    else if (check.equals("INT_MAX"))
      return "2147483647";
    else if (check.equals("UINT_MAX"))
      return "4294967295";
    else if (check.equals("LONG_MIN"))
      return "-9223372036854775808";
    else if (check.equals("LONG_MAX"))
      return "9223372036854775807";
    else if (check.equals("ULONG_MAX"))
      return "18446744073709551615";
    return number;
  }

  /**
   * Code modified from https://dzone.com/articles/programmatically-restart-java`
   * since that version did not work. Sun property pointing the main class and its
   * arguments. Might not be defined on non Hotspot VM implementations.
   */
  private static final String SUN_JAVA_COMMAND = "sun.java.command";

  /**
   * Restart the current Java application
   * 
   * @param runBeforeRestart some custom code to be run before restarting
   * @throws IOException
   */
  private static void restartApplication(/* Runnable runBeforeRestart */) throws IOException {
    try {
      // java binary
      String java = System.getProperty("java.home") + "/bin/java";
      // vm arguments
      List<String> vmArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
      String vmArgsOneLine = new String();
      for (String arg : vmArguments) {
        // if it's the agent argument : we ignore it otherwise the
        // address of the old application and the new one will be in conflict
        if (!arg.contains("-agentlib")) {
          vmArgsOneLine += (arg + " ");
        }
      }
      // init the command to execute, add the vm args
      String cmd = java + " " + vmArgsOneLine + " ";

      // program main and program arguments
      String[] mainCommand = System.getProperty(SUN_JAVA_COMMAND).split(" ");
      // program main is a jar
      cmd += "-jar " + new File(mainCommand[0]).getAbsolutePath();
      // finally add program arguments
      for (int i = 1; i < mainCommand.length; i++) {
        cmd += (" " + (mainCommand[i]));
      }
      // execute the command in a shutdown hook, to be sure that all the
      // resources have been disposed before restarting the application

      final String command = cmd;
      Runtime.getRuntime().addShutdownHook(new Thread() {
        @Override
        public void run() {
          try {
            Runtime.getRuntime().exec(command);
          } catch (IOException e) {
            Alert info = new Alert(AlertType.INFORMATION);
            info.setTitle("Download eevis Dialog");
            info.setHeaderText("Error restarting eevis");
            info.setContentText("Restart manually.");
            info.showAndWait();
            return;
          }
        }
      });
      // execute some custom code before restarting
      // if (runBeforeRestart != null) {
      // runBeforeRestart.run();
      // }
      // exit
      System.exit(0);
    } catch (Exception e) {
      Alert info = new Alert(AlertType.INFORMATION);
      info.setTitle("Download eevis Dialog");
      info.setHeaderText("Error restarting eevis");
      info.setContentText("Restart manually.");
      info.showAndWait();
      return;
    }
  }
}
