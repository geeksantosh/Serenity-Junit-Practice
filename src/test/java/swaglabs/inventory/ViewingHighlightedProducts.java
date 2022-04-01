package swaglabs.inventory;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import swaglabs.authentication.User;
import swaglabs.authentication.actions.LoginActions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SerenityRunner.class)
public class ViewingHighlightedProducts {

    @Managed(driver = "chrome")
    WebDriver driver;

    @Steps
    LoginActions login;

    ProductListsPageObject productList;

    ProductDetailsPageObject productDetails;

    @Test
    public void shouldDisplayHighlightedItemsOnWelcomePage() {
        login.as(User.STANDARD_USER);

        List<String> productsOnDisplay = productList.titles();

        assertThat(productsOnDisplay).hasSize(6)
                .contains("Sauce Labs Backpack");
    }

    @Test
    public void highlightedProductsShouldDisplayCorrespondingImages(){
        login.as(User.STANDARD_USER);
        List<String> productsOnDisplay = productList.titles();

        SoftAssertions softAssertions = new SoftAssertions();
        productsOnDisplay.forEach(
                productName -> softAssertions.assertThat(productList.imageTextForProduct(productName)).isEqualTo(productName)
        );


    }

    @Test
    public void shouldDisplayCorrectProductDetails() {
        login.as(User.STANDARD_USER);

        String firstItemName = productList.titles().get(0);

        productList.openProductDetailsFor(firstItemName);

        assertThat(productDetails.productName()).isEqualTo(firstItemName);
        //Validating Img and alt value
        productDetails.productImageWithAltValueOf(firstItemName).shouldBeVisible();

    }
}
