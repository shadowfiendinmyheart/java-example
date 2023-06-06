package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import application.Logic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller implements Initializable {
  private boolean isMatrixLoad = false;
  private boolean isAnswerLoad = false;

  @FXML
  private Button loadMatrixButton;

  @FXML
  private Button maxColumnButton;

  @FXML
  private Button loadAnswerButton;

  @FXML
  private TextArea matrixField;

  @FXML
  private TextField maxColumnField;

  @FXML
  private TextField loadFileField;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {}

  public void loadMatrix(ActionEvent event) {
    System.out.println("Matrix is load");
    try {
      matrixField.setText(Logic.getMatrix());
      isMatrixLoad = true;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public void findMax(ActionEvent event) {
    if (isMatrixLoad) {
      maxColumnField.setText(Logic.getMaxElementInEveryColumn());
      isAnswerLoad = true;
      System.out.println("Max elements is load");
    } else {
      maxColumnField.setText("Please, load matrix");
    }
  }

  public void loadInFile(ActionEvent event) {
    if (isAnswerLoad) {
      try {
        Logic.writeAnswerToFile();
      } catch (IOException e) {
        System.out.println("!!! File not found 404 error !!!");
        e.printStackTrace();
      }
      loadFileField.setText("Done! Answer loaded in file");
      System.out.println("Answer loaded in file");
    } else {
      loadFileField.setText("Please, find max elements");
    }
  }
}