package model;

public class Diamonds extends Item{
    private static int numberOfDiamonds;

    public Diamonds(Shape shape, Position position) {
        super(shape, position);
        numberOfDiamonds ++;
    }

    public static int diamondsCounter(){
        return numberOfDiamonds;
    }
}
