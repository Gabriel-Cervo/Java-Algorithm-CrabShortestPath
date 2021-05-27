public class Graph {
    private Position position;
    private Graph previous;

    public Graph(Position position, Graph previous) {
        this.position = position;
        this.previous = previous;
    }

    public Position getPosition() {
        return position;
    }

    public Graph getPrevious() {
        return previous;
    }
}
