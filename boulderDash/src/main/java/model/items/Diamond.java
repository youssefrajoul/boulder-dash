package model.items;

import model.Position;

public class Diamond extends Item {
    private Shape shape;
    private boolean claimed;

    public Diamond(Position position) {
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
