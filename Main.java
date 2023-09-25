import java.io.*;

public class Main {
    public static void copyFileSequential(String sourceFile, String destinationFile) throws IOException {
        File fileToCopy = new File(sourceFile);
        File copiedFile = new File(destinationFile);

        try (BufferedReader reader = new BufferedReader(new FileReader(fileToCopy));
             BufferedWriter writer = new BufferedWriter(new FileWriter(copiedFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public static void copyFileParallel(String sourceFile, String destinationFile) throws IOException {
        File fileToCopy = new File(sourceFile);
        File copiedFile = new File(destinationFile);

        try (InputStream inputStream = new FileInputStream(fileToCopy);
             OutputStream outputStream = new FileOutputStream(copiedFile)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String sourceFile = "Copytxt.txt";
        String destinationFile = "Copiedtext.txt";

        // Последовательное копирование
        long sequentialStartTime = System.currentTimeMillis();
        copyFileSequential(sourceFile, destinationFile);
        long sequentialEndTime = System.currentTimeMillis();
        long sequentialTimeElapsed = sequentialEndTime - sequentialStartTime;
        System.out.println("Время последовательного копирования: " + sequentialTimeElapsed + " милсек");

        // Параллельное копирование
        long parallelStartTime = System.currentTimeMillis();
        copyFileParallel(sourceFile, destinationFile);
        long parallelEndTime = System.currentTimeMillis();
        long parallelTimeElapsed = parallelEndTime - parallelStartTime;
        System.out.println("Время паралелльного копирования: " + parallelTimeElapsed + " милсек");
    }
}