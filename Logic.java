package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class Logic {
  public static boolean isFileOpen = false;
  public static File matrixFile = openFile();

  public static boolean scanFile() {
    try (FileReader reader = new FileReader(matrixFile)) {
      BufferedReader br = new BufferedReader(reader);
      int symbol = br.read();
      String matrix = "";

      while (symbol != -1) {
        char c = (char) symbol;
        matrix = matrix + c;
        symbol = br.read();
      }
      
      String answer = matrix.replaceAll("\\s+", "");
      return answer.matches("-?\\d+(\\.\\d+)?")
    } catch (IOException ex) {
      System.out.println(ex);
      return false;
    }
  }

  public static File openFile() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose file with matrix");
    fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
    File file = fileChooser.showOpenDialog(null);
    isFileOpen = true;
    return file;
  }

  public static String getMaxElementInEveryColumn() {
    String answer = "";
    try {
      int[][] matrix = getMatrixFromFile(matrixFile);

      System.out.println(matrixFile.getName() + " exist");
      System.out.print("MATRIX LENGTH = " + matrix[0].length + "\n");
      System.out.print("MATRIX HEIGHT = " + matrix.length + "\n");

      for (int i = 0; i < matrix[0].length; i++) {
        int maxm = matrix[0][i];
        for (int j = 1; j < matrix.length; j++) {
          if (matrix[j][i] > maxm) {
            maxm = matrix[j][i];
          }
        }

        answer = answer + maxm + "   ";
      }
    } catch (FileNotFoundException e) {
      System.out.println("!!! File not found 404 ERROR !!!");
      e.printStackTrace();
    }
    return answer;
  }

  public static String getMatrix() throws FileNotFoundException {
    if (!isFileOpen) {
      matrixFile = openFile();
    }
    if (scanFile() == true) {
      Scanner scanner = new Scanner(matrixFile);
      int n = scanner.nextInt();
      int m = scanner.nextInt();
      int[][] array = new int[n][m];
      String answer = "";

      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          array[i][j] = scanner.nextInt();
          answer = answer + array[i][j] + "   ";
        }
        answer = answer + System.lineSeparator();
      }

      isFileOpen = false;
      return answer;
    } else {
      isFileOpen = false;
      return "Please choose right file, thx!";
    }
  }

  public static int[][] getMatrixFromFile(File fl) throws FileNotFoundException {
    Scanner scanner = new Scanner(fl);
    int n = scanner.nextInt();
    int m = scanner.nextInt();

    int[][] array = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        array[i][j] = scanner.nextInt();
      }
    }
    return array;
  }

  public static void writeAnswerToFile() throws IOException {
    File outputFile = new File("outputFile.txt");
    try {
      if (outputFile.createNewFile()) {
        System.out.println("File " + outputFile.getName() + " created");
      }
      String answer = getMaxElementInEveryColumn();
      FileWriter fw = new FileWriter(outputFile);
      fw.write(answer);
      fw.close();
    } catch (IOException e) {
      System.err.println(e);
    }
  }
}