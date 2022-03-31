package swaglabs.inventory;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import swaglabs.authentication.User;
import swaglabs.authentication.actions.LoginActions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SerenityRunner.class)
public class ViewingHighlightedProducts {
    @Managed
    WebDriver driver;

    @Steps
    LoginActions login;

    ProductListsPageObject productList;

    @Test
    public void shouldDisplayHighlightedItemsOnWelcomePage() {
        login.as(User.STANDARD_USER);

        List<String> productsOnDisplay = productList.titles();

        assertThat(productsOnDisplay).hasSize(6)
                .contains("Sauce Labs Backpack");

    }

}
