package swaglabs.inventory;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import swaglabs.authentication.LoginActions;
import swaglabs.authentication.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SerenityRunner.class)
public class ViewingHighlightedProducts {

    @Managed(driver = "chrome")
    WebDriver driver;

    @Steps
    LoginActions login;

    @Steps
    ViewProductDetailsActions viewProductDetails;

    ProductLists productList;

    ProductDetails productDetails;

    @Test
    public void shouldDisplayHighlightedItemsOnWelcomePage() {
        login.as(User.STANDARD_USER);

        List<String> productsOnDisplay = productList.titles();

        assertThat(productsOnDisplay).hasSize(6)
                .contains("Sauce Labs Backpack");
    }

    @Test
    public void highlightedProductsShouldDisplayCorrespondingImages() {
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

        //productList.openProductDetailsFor(firstItemName);
        viewProductDetails.forProductWithName(firstItemName);

        Serenity.reportThat("The product name should be correctly displayed",
                () -> assertThat(productDetails.productName()).isEqualTo(firstItemName)
        );
        assertThat(productDetails.productName()).isEqualTo(firstItemName);
        //Validating Img and alt value
        Serenity.reportThat("Product Image should have the correct alt text",
                () -> productDetails.productImageWithAltValueOf(firstItemName).shouldBeVisible()
        );
    }
}
