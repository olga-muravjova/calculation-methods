package model;

/**
 * Created by Ольга on 22.10.2016.
 */
public class Node {

    private Double x;
    private Double y;
    private Double firstDerivation;
    private Double secondDerivation;
    private Double firstDerivationReal;
    private Double secondDerivationReal;

    public Node(Double x, Double y, Double firstDerivationReal, Double secondDerivationReal) {
        this.x = x;
        this.y = y;
        this.firstDerivationReal = firstDerivationReal;
        this.secondDerivationReal = secondDerivationReal;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getFirstDerivation() {
        return firstDerivation;
    }

    public void setFirstDerivation(Double firstDerivation) {
        this.firstDerivation = firstDerivation;
    }

    public Double getSecondDerivation() {
        return secondDerivation;
    }

    public void setSecondDerivation(Double secondDerivation) {
        this.secondDerivation = secondDerivation;
    }

    public Double getFirstDerivationReal() {
        return firstDerivationReal;
    }

    public void setFirstDerivationReal(Double firstDerivationReal) {
        this.firstDerivationReal = firstDerivationReal;
    }

    public Double getSecondDerivationReal() {
        return secondDerivationReal;
    }

    public void setSecondDerivationReal(Double secondDerivationReal) {
        this.secondDerivationReal = secondDerivationReal;
    }
}
