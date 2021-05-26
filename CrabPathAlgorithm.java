// @author João Cervo
// Algorithm for finding the shortest path on a maze for the crab
// Based on Lee Algorithm - https://en.wikipedia.org/wiki/Lee_algorithm

import java.util.*;

public class CrabPathAlgorithm {
    public static Graph findShortestPath() {
        TableSize tableSize = TableValues.size;
        boolean[][] table = TableValues.table;
        Position crabPosition = TableValues.crabPosition;
        Position exitPosition = TableValues.exitPosition;

        boolean[][] hasBeenDiscovered = new boolean[tableSize.getWidth() + 1][tableSize.getHeight() + 1];
        hasBeenDiscovered[crabPosition.getDirX()][crabPosition.getDirY()] = true;

        Queue<Graph> queue = new ArrayDeque<>();
        queue.add(new Graph(crabPosition, null, null));

        while(!queue.isEmpty()) {
            Graph graph = queue.poll();

            // Search in each direction once
            // In case of up-left-right-down two times
            for(DirectionValuesEnum directionValue: DirectionValuesEnum.values()) {                
                Direction dir = new Direction(directionValue);

                int nextX = graph.getPosition().getDirX() + dir.getPosition().getDirX();
                int nextY = graph.getPosition().getDirY() + dir.getPosition().getDirY();

                if (nextX >= tableSize.getWidth() || nextY >= tableSize.getHeight() || nextX < 0 || nextY < 0) {
                    continue;
                }

                // Crab cant go here!
                if (table[nextX][nextY]) {
                    continue;
                }

                Direction nextDirection = graph.getFirstDir() != null ? graph.getFirstDir() : dir;
                
                if(nextX == exitPosition.getDirX() && nextY == exitPosition.getDirY()) {
                    return graph;
                }

                // Next movement
                if(dir.getMovementTimes() == 2) {
                    nextX += dir.getPosition().getDirX();
                    nextY += dir.getPosition().getDirY();

                    if (nextX >= tableSize.getWidth() || nextY >= tableSize.getHeight() || nextX < 0 || nextY < 0) {
                        continue;
                    }

                    if(nextX == exitPosition.getDirX() && nextY == exitPosition.getDirY()) {
                        return graph;
                    }
                }

                // Enqueue if not discovered
                if(!hasBeenDiscovered[nextX][nextY]) {
                    hasBeenDiscovered[nextX][nextY] = true;
                    queue.add(new Graph(new Position(nextX, nextY), nextDirection, graph));
                }
            }
        }

        return null;
    }
}
