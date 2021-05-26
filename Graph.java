public class Graph {
    private Position position;
    private Direction firstDir;
    private Graph previous;

    public Graph(Position position, Direction firstDir, Graph previous) {
        this.position = position;
        this.firstDir = firstDir;
        this.previous = previous;
    }

    public Position getPosition() {
        return position;
    }

    public Direction getFirstDir() {
        return firstDir;
    }

    public Graph getPrevious() {
        return previous;
    }
}
