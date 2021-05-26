import java.io.*;

public class Reader {
    public static void readTxt(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName + ".txt"));

        String[] nameCut = fileName.trim().toLowerCase().split("_"); // get the table size based on the name (ex: 50_50.txt = 50x50)
        TableSize size = new TableSize(Integer.parseInt(nameCut[0]), Integer.parseInt(nameCut[1]));
        TableValues.size = size;
        TableValues.table = new boolean[size.getWidth()][size.getHeight()];

        String line = "";

        int count = 0;

        while ((line = reader.readLine()) != null) {
            for(int i = 0; i < line.length(); i++) {
                char actualC = line.toLowerCase().charAt(i);

                TableValues.table[i][count] = actualC == 'x' ? true : false;

                if (actualC == 's') {
                    TableValues.exitPosition = new Position(i, count);
                }
                 if (actualC == 'c') {
                    TableValues.crabPosition = new Position(i, count);
                }
            }

            count++;
        }

        reader.close();
    }
}
