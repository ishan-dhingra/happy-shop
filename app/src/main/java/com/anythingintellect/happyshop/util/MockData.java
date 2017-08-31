package com.anythingintellect.happyshop.util;

import com.anythingintellect.happyshop.model.CartEntry;
import com.anythingintellect.happyshop.model.Product;
import com.anythingintellect.happyshop.model.ProductListResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ishan.dhingra on 30/08/17.
 */

public class MockData {

    private static final String PRODUCT_LIST_JSON = "{\"products\":[{\"id\":10,\"name\":\"Deep Sleep Night Oil 120ml\",\"category\":\"Skincare\",\"price\":5600.0,\"img_url\":\"http://luxola-assets-staging-nemesis.s3.amazonaws.com/images/pictures/6391/default_195450f4c178065b72ca3a13e8e0ba36a0276d31_1409799777_TW_DeepSleepNightOil.jpg\",\"under_sale\":false},{\"id\":12,\"name\":\"Elbow \\u0026 Heel Smoothing Cream 59ml\",\"category\":\"Bath \\u0026 Body\",\"price\":1700.0,\"img_url\":\"http://luxola-assets-staging-nemesis.s3.amazonaws.com/images/pictures/6804/default_698894a6f41fa32fa8ddb78133b4316b1bb58d45_1411538629_elbow-heel-smoothing-cream_closed.jpg\",\"under_sale\":false},{\"id\":13,\"name\":\"No. 1 Belle Frais Cleansing Milk 200ml\",\"category\":\"Skincare\",\"price\":4370.0,\"img_url\":\"http://luxola-assets-staging-nemesis.s3.amazonaws.com/images/pictures/11478/default_0c3ad1a0b47aab584c23b3b0dfdca6947ead2a7e_1436254156_No1_SC_Sephora.jpg\",\"under_sale\":false},{\"id\":14,\"name\":\"Big Kabuki Brush\",\"category\":\"Tools\",\"price\":3210.0,\"img_url\":\"http://luxola-assets-staging-nemesis.s3.amazonaws.com/images/pictures/9588/default_f10243dc3db92b065b1c4da2f29cad1b77810650_1423037089_THEBALM_big_kabuki_brush_20.jpg\",\"under_sale\":false},{\"id\":16,\"name\":\"Black Diamond 15ml\",\"category\":\"Nails\",\"price\":2300.0,\"img_url\":\"http://luxola-assets-staging-nemesis.s3.amazonaws.com/images/pictures/1400/default_2d30f67992e8baf23dcdde9c36eecf0713f5bfde_NCLA_blackdiamond.png\",\"under_sale\":false},{\"id\":20,\"name\":\"Silk Touché Luxurious Foundation SPF 15 11g\",\"category\":\"Makeup\",\"price\":6300.0,\"img_url\":\"http://luxola-assets-staging-nemesis.s3.amazonaws.com/images/pictures/6642/default_da326737a8b499d7e5bd98295ed4c55ba4614933_1410921469_ENV_Silk_20Touche_20Luxurious.jpg\",\"under_sale\":false},{\"id\":21,\"name\":\"Retractable Foundation Brush \",\"category\":\"Tools\",\"price\":1990.0,\"img_url\":\"http://luxola-assets-staging-nemesis.s3.amazonaws.com/images/pictures/8091/default_5c6f5b9b34bfd52cf866b760a9828d917d8668d1_1416552344_RetractableFoundationBrush.jpg\",\"under_sale\":false},{\"id\":22,\"name\":\"Lip Glaze 15ml\",\"category\":\"Makeup\",\"price\":4100.0,\"img_url\":\"http://luxola-assets-staging-nemesis.s3.amazonaws.com/images/pictures/4509/default_dadd9518417af7c9f48ff060ee887030a71bbb29_faceatelier_new_lipglaze_cameo.jpg\",\"under_sale\":false},{\"id\":25,\"name\":\"Precision Shave Gel 150ml\",\"category\":\"Men\",\"price\":2800.0,\"img_url\":\"http://luxola-assets-staging-nemesis.s3.amazonaws.com/images/pictures/5572/default_404c7e757a530e41b880857c3e8378419517b1c4_1406796515_AC_PercisionShavegel.jpg\",\"under_sale\":false},{\"id\":28,\"name\":\"TimeBalm Primer 30ml\",\"category\":\"Makeup\",\"price\":4000.0,\"img_url\":\"http://luxola-assets-staging-nemesis.s3.amazonaws.com/images/pictures/9274/default_92e59c0b6dfca1ddf7902258c2c38ef0dbddb750_1421252192_primer_300dpi.jpg\",\"under_sale\":false}]}";
    private static final String category = "Makeup";
    private static List<Product> productList;
    private static Product product;
    private static CartEntry cartEntry;
    private static ProductListResponse productListResponse;

    public static void init() {
        productList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(PRODUCT_LIST_JSON);
            JSONArray jsonArray = jsonObject.optJSONArray("products");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject prodObj = jsonArray.optJSONObject(i);
                Product prod = new Product();
                prod.setName(prodObj.optString("name"));
                prod.setId(prodObj.optLong("id"));
                prod.setCategory(prodObj.optString("category"));
                prod.setImgUrl(prodObj.optString("img_url"));
                prod.setPrice(prodObj.optDouble("price"));
                prod.setUnderSale(prodObj.optBoolean("under_sale"));
                productList.add(prod);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        product = productList.get(0);
        cartEntry = new CartEntry();
        cartEntry.setProductId(100);
        productListResponse = new ProductListResponse();
        productListResponse.setProducts(productList);
    }

    public static List<Product> getProductList() {
        return productList;
    }

    public static Product getProduct() {
        return product;
    }

    public static String getCategory() {
        return category;
    }

    public static CartEntry getCartEntry() {
        return cartEntry;
    }

    public static ProductListResponse getProductListResponse() {
        return productListResponse;
    }
}
