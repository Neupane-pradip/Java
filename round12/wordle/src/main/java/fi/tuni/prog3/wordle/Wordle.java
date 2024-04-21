package fi.tuni.prog3.wordle;

import java.util.Map;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Wordle extends Application {
    private Game gameLogic;
    private String targetWord;
    private int gameNumber = 0;
    private boolean gameActive;
    private Label[][] allLabels;
    private int lastColumn;
    private int lastRow;
    private String guessedWord;
    private GridPane grid;
    private Button newGameButton;
    private Label infoLabel;
    private Scene scene;

    @Override
    public void start(Stage stage) {
        initializeNewGame(stage);
        stage.setTitle("Wordle Game");
        stage.show();
    }

    private void initializeNewGame(Stage stage) {
        gameLogic = new Game();
        targetWord = gameLogic.getWord(gameNumber);
        gameNumber++;
        gameActive = true;
        allLabels = new Label[6][targetWord.length()];
        lastColumn = 0;
        lastRow = 0;
        guessedWord = "";

        setBackgroundAndBorder();

        grid = new GridPane();
        createLabelGrid();

        createNewGameButton();

        createInfoLabel();

        createScene(stage);

        setEventHandlers();
    }

    private void setBackgroundAndBorder() {
        BackgroundFill whiteBackgroundFill = new BackgroundFill(Color.WHITE, null, null);
        Background whiteBackground = new Background(whiteBackgroundFill);
        Border border = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, new BorderWidths(2)));
    }

    private void createLabelGrid() {
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < targetWord.length(); c++) {
                Label letterLabel = new Label();
                setLabelProperties(letterLabel);
                String id = String.format("%d_%d", r, c);
                letterLabel.setId(id);
                grid.add(letterLabel, c + 1, r + 1);
                allLabels[r][c] = letterLabel;
            }
        }
    }

    private void setLabelProperties(Label label) {
        label.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        label.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, new BorderWidths(2))));
        label.setPrefSize(100, 100);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        label.setAlignment(Pos.CENTER);
    }

  private void createNewGameButton() {
    newGameButton = new Button();
    newGameButton.setText("Start new game"); 
    newGameButton.setPrefSize(120, 40);
    newGameButton.setId("newGameBtn");
    newGameButton.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY, new BorderWidths(2))));
    grid.add(newGameButton, 0, 0);
    newGameButton.setOnAction((event) -> initializeNewGame((Stage) newGameButton.getScene().getWindow()));
}

    private void createInfoLabel() {
        infoLabel = new Label();
        infoLabel.setId("infoBox");
        infoLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        grid.add(infoLabel, 2, 0, 3, 1);
    }

    private void createScene(Stage stage) {
        int gridWidth = 100 * targetWord.length() + 200;
        int gridHeight = 700;
        scene = new Scene(grid, gridWidth, gridHeight);
        stage.setScene(scene);
    }

    private void setEventHandlers() {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, this::handleKeyPressedEvent);
        scene.addEventHandler(KeyEvent.KEY_TYPED, this::handleKeyTypedEvent);
        scene.getRoot().requestFocus();
    }

    private void handleKeyPressedEvent(KeyEvent event) {
        if (gameActive) {
            if (event.getCode() == KeyCode.ENTER) {
                if (lastColumn != targetWord.length()) {
                    infoLabel.setText("Give a complete word before pressing Enter!");
                } else {
                    updateGuessedWord();
                    changeColor();
                    if (gameOver()) {
                        gameActive = false;
                    } else {
                        lastRow++;
                        lastColumn = 0;
                        guessedWord = "";
                    }
                    scene.getRoot().requestFocus();
                }
            } else if (event.getCode() == KeyCode.BACK_SPACE) {
                if (lastColumn != 0) {
                    allLabels[lastRow][--lastColumn].setText("");
                }
            }
        }
    }

    private void handleKeyTypedEvent(KeyEvent event) {
        if (gameActive) {
            String typedCharacter = event.getCharacter();
            if (Character.isLetter(typedCharacter.charAt(0))) {
                infoLabel.setText("");
                typedCharacter = typedCharacter.toUpperCase();
                if (lastColumn != targetWord.length()) {
                    allLabels[lastRow][lastColumn].setText(typedCharacter);
                    lastColumn++;
                }
            }
        }
    }

    private void updateGuessedWord() {
        for (Label currentLabel : allLabels[lastRow]) {
            String character = currentLabel.getText();
            guessedWord += character;
        }
    }

    private void changeColor() {
        Map<Integer, String> colorMap = gameLogic.guess(guessedWord);
        for (int i = 0; i < targetWord.length(); i++) {
            Label currentLabel = allLabels[lastRow][i];
            String color = colorMap.get(i);
            switch (color) {
                case "GREEN":
                    currentLabel.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
                    break;
                case "ORANGE":
                    currentLabel.setBackground(new Background(new BackgroundFill(Color.ORANGE, null, null)));
                    break;
                case "GRAY":
                    currentLabel.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
                    break;
                default:
                    break;
            }
        }
    }

    private boolean gameOver() {
        if (gameLogic.correctGuess(guessedWord)) {
            infoLabel.setText("Congratulations, you won!");
            infoLabel.setTextFill(Color.GREEN);
            return true;
        } else if (lastRow == 5) {
            infoLabel.setText("Game over, you lost!");
            infoLabel.setTextFill(Color.RED);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        launch();
    }
}
