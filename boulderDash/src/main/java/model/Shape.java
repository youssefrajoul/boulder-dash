package model;

public enum Shape {
    c('c'), d('d'), e('e'), x('x'), p('p'), r('r'), w('w');

    private char shape;

    Shape (char c){
        this.shape = c;
    }

    public char getShape() {
        return shape;
    }
}
