package swaglabs.authentication.actions;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import swaglabs.authentication.User;

public class LoginActions extends UIInteractionSteps {
    @Step("Login as {0}")
    public void as(User user) {
        openUrl("https://www.saucedemo.com/");

        //Login as a Standard user
        $("#user-name").sendKeys(user.getUsername());
        $("#password").sendKeys(user.getPassword());
        $("#login-button").click();

    }
}
