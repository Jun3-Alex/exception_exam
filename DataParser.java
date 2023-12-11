import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataParser {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private static boolean isValidDate(String date) {
        try {
            Date parsedDate = DATE_FORMAT.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(parsedDate);

            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            int minimumBirthYear = currentYear - 120;
            int maximumBirthYear = currentYear - 18;

            int birthYear = cal.get(Calendar.YEAR);
            return birthYear >= minimumBirthYear && birthYear <= maximumBirthYear;
        } catch (ParseException e) {
            return false;
        }
    }

    public static UserData parseUserData(String input) throws IllegalArgumentException {

        String[] parts = input.split(",");

        if (parts.length != 6) {
            throw new IllegalArgumentException("Неверное количество параметров");
        }

        String lastName = null;
        String firstName = null;
        String patronymic = null;
        String birthDate = null;
        long phoneNumber = 0;
        char gender = 0;

        for(String part : parts) {

            if (part.matches("[А-ЯЁ][а-яё]+|[А-ЯЁ][A-ЯЁa-яё]+")) {

                String normalized = part.substring(0, 1).toUpperCase() +
                        part.substring(1).toLowerCase();

                if (lastName == null) {
                    lastName = normalized;
                }
                else if (firstName == null) {
                    firstName = normalized;
                }
                else if (patronymic == null)  {
                    patronymic = normalized;
                }

            }

            if (part.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
                birthDate = part;
            }

            if (part.matches("\\d{10}")) {
                phoneNumber = Long.parseLong(part);
            }

            if (part.matches("[mf]")) {
                gender = part.charAt(0);
            }

        }

        try {

            DATE_FORMAT.setLenient(false);
            DATE_FORMAT.parse(birthDate);

            if (!isValidDate(birthDate)) {
                throw new IllegalArgumentException("Неверная дата");
            }

            return new UserData(lastName, firstName, patronymic,
                    birthDate, phoneNumber, gender);

        } catch (ParseException e) {

            throw new IllegalArgumentException("Неверный формат даты");

        } catch (Exception e) {

            throw new IllegalArgumentException("Неверный формат данных");
        }



    }

}
