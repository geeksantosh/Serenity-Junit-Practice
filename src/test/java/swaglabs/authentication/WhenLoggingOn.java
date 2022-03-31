package swaglabs.authentication;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import swaglabs.authentication.actions.LoginActions;
import swaglabs.inventory.InventoryPage;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SerenityRunner.class)
public class WhenLoggingOn {

    @Managed
    WebDriver driver;

    @Steps
    LoginActions login;

    InventoryPage inventoryPage;

    @Test
    public void usersCanLogOnViaTheHomePage() {
        login.as(User.STANDARD_USER);
//        login.as(User.LOCKED_OUT_USER);
//        login.as(User.PROBLEM_USER);
//        login.as(User.PERFORMANCE_GLITCH_USER);

//      Should see product catalog
        Serenity.reportThat("The inventory should be displayed with the correct title",
                () -> assertThat(inventoryPage.getHeading()).isEqualToIgnoringCase("Products")
        );
    }

}
