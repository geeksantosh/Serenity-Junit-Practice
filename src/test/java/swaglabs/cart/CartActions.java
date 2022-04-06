package swaglabs.cart;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import swaglabs.inventory.ProductLists;

import java.util.List;

public class CartActions extends UIInteractionSteps {

    @Step("Add {0} to the cart")
    public void addItem(String itemName) {
        $(ProductLists.addToCartButtonFor(itemName)).click();

    }

    @Step("Add {0} to the cart")
    public void addItems(List<String> items) {
        items.forEach(this::addItem);
    }

    @Step("Open shopping cart")
    public void openCart() {
        $(ShoppingCartIcon.link()).click();

    }

    public List<String> displayedItems() {
        return findAll(".inventory_item_name").texts();
    }
}
