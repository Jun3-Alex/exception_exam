import java.io.IOException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (scanner) {
            System.out.println("Введите данные: ");
            String input = scanner.nextLine();

            UserData userData = DataParser.parseUserData(input);

            String fileName = userData.lastName();
            String content = userData.toString();

            CsvWriter.writeToCsvFile(fileName, content);

            System.out.println("Данные записаны в файл");

        } catch (IOException e) {

            System.out.println("Произошла ошибка: " + e.getMessage());
            e.printStackTrace();

        }

    }
}
