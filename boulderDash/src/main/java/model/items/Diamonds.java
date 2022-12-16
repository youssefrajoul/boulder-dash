package model.items;

import model.Position;

public class Diamonds extends Item {
    private Shape shape;
    private boolean claimed;

    public Diamonds(Position position) {
        super(position);
        this.shape = Shape.d;
        this.claimed = false;
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    public boolean isClaimed() {
        return claimed;
    }

    public void setClaimed() {
        this.claimed = true;
    }


}
