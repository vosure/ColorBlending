package model;

import javafx.scene.paint.Color;

public class Palette {
    private double red;
    private double green;
    private double blue;

    private Color color;

    public Palette(double red, double green, double blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public Palette() {
        this.red = 1;
        this.green = 1;
        this.blue = 1;
    }

    public double getRed() {
        return red;
    }

    public void setRed(double red) {
        this.red = red;
    }

    public double getGreen() {
        return green;
    }

    public void setGreen(double green) {
        this.green = green;
    }

    public double getBlue() {

        return blue;
    }

    public void setBlue(double blue) {
        this.blue = blue;

    }

    public Color getColor() {
        return color;
    }

    public Color generateAndReturnColor() {
        color = new Color(red, green, blue, 1);
        return color;
    }

    public Color blend(Color color1, Color color2, float blendingRation) {
        if (blendingRation > 1f) blendingRation = 1f;
        else if (blendingRation < 0f) blendingRation = 0f;
        float color2BlendingRatio = 1.0f - blendingRation;

        double a = (color1.getOpacity() * color2BlendingRatio) + (color2.getOpacity() * blendingRation);
        double r = (color1.getRed() * color2BlendingRatio) + (color2.getRed() * blendingRation);
        double g = (color1.getGreen() * color2BlendingRatio) + (color2.getGreen() * blendingRation);
        double b = (color1.getBlue() * color2BlendingRatio) + (color2.getBlue() * blendingRation);

        color = new Color(r, g, b, 1.0f);
        return color;
    }

    public boolean compareColors(Color targetColor) {
        double targetRed = targetColor.getRed();
        double targetGreen = targetColor.getGreen();
        double targetBlue = targetColor.getBlue();

        return Math.abs(targetRed - red) <= 0.1 && Math.abs(targetGreen - green) <= 0.1 && Math.abs(targetBlue - blue) <= 0.1;
    }
}
