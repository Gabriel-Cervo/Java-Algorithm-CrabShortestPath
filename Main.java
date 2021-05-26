import java.io.IOException;

public class Main {
    public static void main(String args[]) {
        final String[] files = { "50_50", "75_75", "75_75_1", "75_75_2", "75_75_3", "75_75_4", "75_75_5", "100_50",
                "100_100", "250_250", "640_480", "1000_1000" };

        for (String filename : files) {
            System.out.println("----" + filename + "----");
            try {
                Reader.readTxt(filename);

                Graph shortestPath = CrabPathAlgorithm.findShortestPath();
                if (shortestPath == null) {
                    System.out.println("No path has been found for this table.");
                    return;
                }

                System.out.println("Path Found!");
                int count = 0;
                while (shortestPath.getPrevious() != null) {
                    count++;
                    shortestPath = shortestPath.getPrevious();
                }
                count++; // Count the last movement
                System.out.println("It took '" + count + "' moves!");
                System.out.println("\n");
            } catch (IOException e) {
                System.out.println("file not found.");
            }
        }
    }
}