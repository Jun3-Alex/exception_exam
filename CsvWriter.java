import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class CsvWriter {
    /**
     * Записывает заданное содержимое в CSV-файл.
     *
     * @param  fileName  имя файла CSV, в который нужно записать
     * @param  content   содержимое, которое нужно записать в файл
     * @throws IOException  если произошла ошибка ввода-вывода при записи файла
     */
    public static void writeToCsvFile(String fileName, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".csv", StandardCharsets.UTF_8, true))) {
            writer.write(content);
            writer.newLine();
        }
    }
}
