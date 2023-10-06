package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {

    private static Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    public static AuthInfo getAuthInfoFromTestData() {
        return new AuthInfo("vasya", "qwerty123");
    }

    private static String generateLogin() {
        return faker.name().username();
    }

    private static String generatePassword() {
        return faker.internet().password();
    }

    public static AuthInfo generateUser() {
        return new AuthInfo(generateLogin(), generatePassword());
    }

    public static VerificationCode getRandomVerificationCode() {
        return new VerificationCode(faker.numerify("######"));
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    @Value
    public static class VerificationCode {
        private String code;
    }
}