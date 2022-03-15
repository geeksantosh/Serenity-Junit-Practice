package swaglabs.authentication.actions;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import swaglabs.authentication.User;

public class LoginActions extends UIInteractionSteps {
    @Step("Login as {0}")
    public void as(User user) {
        openUrl("https://www.saucedemo.com/");

        //Login as a Standard user
        $("[data-test='username']").sendKeys(user.getUsername());
        $("[data-test = 'password']").sendKeys(user.getPassword());
        $("[data-test='login-button']").click();

    }
}
