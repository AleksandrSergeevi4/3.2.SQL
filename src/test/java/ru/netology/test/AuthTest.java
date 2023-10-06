package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.SQLHelper.cleanDatabase;

public class AuthTest {

    @AfterAll
    static void teardown() {
        cleanDatabase();
    }

    @Test
    void shouldLoginRegisteredUser() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoFromTestData();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyVerificationPageVisiblity();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }

    @Test
    void shouldRegisterUserWhitInvalidPassword() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var invalidPassword = loginPage.getInvalidPassForRegisterUser();
    }

    @Test
    void shouldBlockedRegisterUserWhitInvalidPassword() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var invalidPassword = loginPage.getThreeInvalidPassForRegisterUser();
        var status = SQLHelper.getBlockedUserStatus();
        Assertions.assertEquals("blocked", status);
    }
}
