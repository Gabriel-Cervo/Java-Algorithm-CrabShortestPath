public class Direction {
    private Position position;
    private DirectionValuesEnum direction;
    private int movementTimes;

    public Direction(DirectionValuesEnum direction) {
        this.direction = direction;

        switch (direction) {
            case up:
                this.position = new Position(0, -1); // Go back a value in the matrix
                this.movementTimes = 2;
                break;
            case upLeft:
                this.position = new Position(-1, -1);
                this.movementTimes = 1;
                break;
            case upRight:
                this.position = new Position(1, -1);
                this.movementTimes = 1;
                break;
            case left:
                this.position = new Position(-1, 0);
                this.movementTimes = 2;
                break;
            case right:
                this.position = new Position(1, 0);
                this.movementTimes = 2;
                break;
            case down:
                this.position = new Position(0, 1);
                this.movementTimes = 2;
                break;
            case downLeft:
                this.position = new Position(-1, 1);
                this.movementTimes = 1;
                break;
            case downRight:
                this.position = new Position(1, 1);
                this.movementTimes = 1;
                break;
            default:
                break;
        }
    }

    public Position getPosition() {
        return position;
    }

    public DirectionValuesEnum getDirection() {
        return direction;
    }

    public int getMovementTimes() {
        return movementTimes;
    }

}
