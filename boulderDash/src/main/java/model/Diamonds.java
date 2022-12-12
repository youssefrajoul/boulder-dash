package model;

public class Diamonds extends Item{
    private Shape shape;
    private static int numberOfDiamonds;

    public Diamonds(Position position) {
        super(position);
        this.shape = Shape.d;
        numberOfDiamonds ++;
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    public static int diamondsCounter(){
        return numberOfDiamonds;
    }
}
