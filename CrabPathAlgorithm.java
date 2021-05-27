// @author João Cervo
// Algorithm for finding the shortest path on a maze for the crab
// Based on Lee Algorithm - https://en.wikipedia.org/wiki/Lee_algorithm

import java.util.*;

public class CrabPathAlgorithm {
    public static Graph findShortestPath() {
        TableSize tableSize = TableValues.size;
        Position crabPosition = TableValues.crabPosition;
        Position exitPosition = TableValues.exitPosition;

        boolean[][] hasBeenDiscovered = new boolean[tableSize.getWidth()][tableSize.getHeight()];
        hasBeenDiscovered[crabPosition.getDirX()][crabPosition.getDirY()] = true;

        Queue<Graph> queue = new ArrayDeque<>();
        queue.add(new Graph(crabPosition, null));

        while (!queue.isEmpty()) {
            Graph graph = queue.poll();

            // Search in each direction once
            // In case of up-left-right-down, it makes two movements instead of one (see
            // Diego Noble's PDF)
            for (DirectionValuesEnum directionValue : DirectionValuesEnum.values()) {
                Direction dir = new Direction(directionValue);

                int nextX = graph.getPosition().getDirX() + dir.getPosition().getDirX();
                int nextY = graph.getPosition().getDirY() + dir.getPosition().getDirY();
                Position nextPosition = new Position(nextX, nextY);

                if (crabCantGoHere(nextPosition)) {
                    continue;
                }

                Graph nextGraph = new Graph(nextPosition, graph);

                if (nextX == exitPosition.getDirX() && nextY == exitPosition.getDirY()) {
                    return nextGraph;
                }

                // Next movement
                if (dir.getMovementTimes() == 2) {
                    nextX += dir.getPosition().getDirX();
                    nextY += dir.getPosition().getDirY();
                    nextPosition = new Position(nextX, nextY);

                    if (crabCantGoHere(nextPosition)) {
                        continue;
                    }

                    nextGraph = new Graph(nextPosition, graph);

                    if (nextX == exitPosition.getDirX() && nextY == exitPosition.getDirY()) {
                        return nextGraph;
                    }
                }

                // Enqueue if not discovered
                if (!hasBeenDiscovered[nextX][nextY]) {
                    hasBeenDiscovered[nextX][nextY] = true;
                    queue.add(nextGraph);
                }
            }
        }

        return null;
    }

    private static boolean crabCantGoHere(Position position) {
        return isOutOfBounds(position) || isBlockedPath(position);
    }

    private static boolean isOutOfBounds(Position position) {
        return (position.getDirX() >= TableValues.size.getWidth() || position.getDirY() >= TableValues.size.getHeight()
                || position.getDirX() < 0 || position.getDirY() < 0);
    }

    private static boolean isBlockedPath(Position position) {
        return TableValues.table[position.getDirX()][position.getDirY()];
    }
}
