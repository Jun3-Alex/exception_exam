public record UserData(String lastName, String firstName, String patronymic, String birthDate, long phoneNumber,
                       char gender) {

    @Override
    public String toString() {
        return "Данные о пользователе: \n" + "{"
                + "Фамилия: " + lastName + ", \n"
                + "Имя: " + firstName + ", \n"
                + "Отчество: " + patronymic + ", \n"
                + "Дата рождения: " + birthDate + ", \n"
                + "Номер телефона: " + "+7" + phoneNumber + ", \n"
                + "Пол: " + gender + '}';
    }
}
