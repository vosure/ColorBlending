package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.Palette;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Canvas canvas;
    public Circle circle;

    public Rectangle rect;
    public Label counter;
    public ColorPicker colorPicker2;
    public ColorPicker colorPicker1;
    public Slider blueSlider;
    public Slider greenSlider;
    public Slider redSlider;

    private int count = 0;

    private Random randomizer = new Random();

    private Palette palette;

    private Color targetColor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        palette = new Palette();
        redSlider.valueProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                generateAndFill();
            }
        });
        greenSlider.valueProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                generateAndFill();
            }
        });
        blueSlider.valueProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                generateAndFill();
            }
        });
        generateTargetColor();
    }

    private void generateTargetColor() {
        double red = 1.0f * randomizer.nextDouble();
        double green = 1.0f * randomizer.nextDouble();
        double blue = 1.0f * randomizer.nextDouble();

        targetColor = new Color(red, green, blue, 1.0f);
        rect.setFill(targetColor);
    }

    private boolean checkColors() {
        return palette.compareColors(targetColor);
    }

    private void generateNewTargetColor() {
        generateTargetColor();
        counter.setText("Успешно :" + ++count + " раз");
    }

    public void changeTargetColor(ActionEvent actionEvent) {
        generateTargetColor();
    }

    private void generateAndFill() {
        palette.setRed(redSlider.getValue());
        palette.setGreen(greenSlider.getValue());
        palette.setBlue(blueSlider.getValue());

        circle.setFill(palette.generateAndReturnColor());
        if (checkColors()){
            generateNewTargetColor();
        }
    }

    public void mixColors(ActionEvent actionEvent) {
        Color color1 = colorPicker1.getValue();
        Color color2 = colorPicker2.getValue();

        Color mixedColor = palette.blend(color1, color2, 0.5f);

        redSlider.setValue(mixedColor.getRed());
        greenSlider.setValue(mixedColor.getGreen());
        blueSlider.setValue(mixedColor.getBlue());

        palette.setRed(mixedColor.getRed());
        palette.setGreen(mixedColor.getGreen());
        palette.setBlue(mixedColor.getBlue());

        circle.setFill(palette.generateAndReturnColor());
        if (checkColors()){
            generateNewTargetColor();
        }
    }

    public void onTargetColorClick(MouseEvent mouseEvent) {
        Color color = (Color) rect.getFill();
        redSlider.setValue(color.getRed());
        greenSlider.setValue(color.getGreen());
        blueSlider.setValue(color.getBlue());
    }
}
