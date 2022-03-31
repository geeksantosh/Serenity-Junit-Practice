package swaglabs.inventory;

import net.serenitybdd.core.pages.PageObject;

import java.util.List;

public class ProductListsPageObject extends PageObject {
    public List<String> titles() {
        return findAll(".inventory_item_name").textContents();
    }
}
