package swaglabs.cart;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import swaglabs.authentication.LoginActions;
import swaglabs.authentication.User;
import swaglabs.cart.CartActions;
import swaglabs.cart.ShoppingCartIcon;
import swaglabs.inventory.ProductLists;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SerenityRunner.class)
public class WhenAddingAnItemToTheCart {
    @Managed(driver = "chrome")
    WebDriver driver;

    LoginActions login;

    @Before
    public void login() {
        login.as(User.STANDARD_USER);
    }

    ShoppingCartIcon shoppingCartBadge;

    @Steps
    CartActions cart;
    @Test
    public void theCorrectItemCountShouldBeShown() {
        // Check that the shopping cart badge is empty
        Serenity.reportThat("The shopping badge should be empty",
                () -> assertThat(shoppingCartBadge.badgeCount()).isEmpty()
                );
        assertThat(shoppingCartBadge.badgeCount()).isEmpty();

        // Add 1 item to the cart
        cart.addItem("Sauce Labs Backpack");

        //Then shopping cart badge should be 1
        Serenity.reportThat("The Shopping cart badge should be now '1'",
                () -> assertThat(shoppingCartBadge.badgeCount()).isEqualTo("1")
                );
    }

    ProductLists productLists;

    @Test
    public void allTheItemsShouldAppearInTheCart() {
        // Add several Items to the cart
        List<String> selectedItems = firstThreeProductTitlesDisplayed();

        //Open the cart page
        cart.addItems(selectedItems);

        String cartBadgeCount = Integer.toString(selectedItems.size());
        Serenity.reportThat("The Shopping cart badge should be now"+ cartBadgeCount,
                () -> assertThat(shoppingCartBadge.badgeCount()).isEqualTo(cartBadgeCount)
        );

        cart.openCart();

        //Should see all the items listed
        Serenity.reportThat("Should see all the items listed in cart",
                () -> assertThat(cart.displayedItems()).containsExactlyElementsOf(selectedItems)
        );
    }

    private List<String> firstThreeProductTitlesDisplayed() {
        return productLists.titles().subList(0, 3);
    }

}
