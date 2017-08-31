package com.anythingintellect.happyshop.util;

/**
 * Created by ishan.dhingra on 31/08/17.
 */

public interface Navigator {

    void openCategoryList();

    void openProductList(String category);

    void openProductDetails(long productId);

}
