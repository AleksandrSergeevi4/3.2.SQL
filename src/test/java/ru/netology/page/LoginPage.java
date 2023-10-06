package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement login = $("[data-test-id=login] input");
    private SelenideElement password = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement errorNotification = $("[data-test-id=error-notification]");

    public void verifyErrorNotificationVisiblity() {
        errorNotification.shouldBe(visible);
    }

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        login.setValue(info.getLogin());
        password.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public LoginPage getInvalidPassForRegisterUser() {
        login.setValue(DataHelper.getAuthInfoFromTestData().getLogin());
        password.setValue(DataHelper.generateUser().getPassword());
        loginButton.click();
        verifyErrorNotificationVisiblity();
        return new LoginPage();
    }

    public LoginPage getThreeInvalidPassForRegisterUser() {
        login.setValue(DataHelper.getAuthInfoFromTestData().getLogin());
        password.setValue(DataHelper.generateUser().getPassword());
        loginButton.click();
        verifyErrorNotificationVisiblity();
        loginButton.click();
        verifyErrorNotificationVisiblity();
        loginButton.click();
        getBlockedUser();
        return new LoginPage();
    }

    public void getBlockedUser() {
        errorNotification.shouldBe(visible).shouldHave(text("Превышено количество попыток авторизации." +
                " Пользователь заблокирован."));
    }
}