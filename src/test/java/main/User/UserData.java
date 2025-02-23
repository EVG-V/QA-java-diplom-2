package main.User;

import praktikum.User;

public class UserData {

    // Корректный пользователь
    public static User bodyPostCreateUser() {
        return new User("volkov_2025@ya.ru", "qwerty", "Volk");
    }

    public static User bodyLoginWithCorrectData() {
        return new User("volkov_2025@ya.ru", "qwerty");
    }

    // Неполные пользовательские данные
    public static User bodyPostWithEmptyEmail() {
        return new User(null, "12345", "Belka");
    }

    public static User bodyPostWithEmptyPassword() {
        return new User("belkin_2025@ya.ru", null, "Belka");
    }

    public static User bodyPostWithEmptyName() {
        return new User("belkin_2025@ya.ru", "12345", null);
    }

    // Неправильные пользовательские данные
    public static User bodyLoginWithUnCorrectData() {
        return new User("kotikov_2025@ya.ru", "qwerty");
    }

    // Изменение данных - новый e-mail не используется другим пользователем
    public static User bodyChangeUserDataEmail() {
        return new User("volk_volkov_2025@gmail.ru", "qwerty", "Volk");
    }

    public static User bodyChangeUserDataName() {
        return new User("volkov_2025@ya.ru", "qwerty", "Seryi");
    }

    public static User bodyChangeUserDataPassword() {
        return new User("volkov_2025@ya.ru", "qwerty1", "Volk");
    }

    public static User bodyChangeUserAllData() {
        return new User("new_email@ya.ru", "new_password", "new_name");
    }
}


