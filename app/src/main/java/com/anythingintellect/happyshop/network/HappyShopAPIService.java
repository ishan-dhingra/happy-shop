package com.anythingintellect.happyshop.network;

import com.anythingintellect.happyshop.model.ProductListResponse;
import com.anythingintellect.happyshop.model.ProductResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ishan.dhingra on 30/08/17.
 */

public interface HappyShopAPIService {

    @GET("api/v1/products.json")
    Observable<ProductListResponse> getProductList(@Query("page") int page, @Query("category") String category);

    @GET("api/v1/products/{id}.json")
    Observable<ProductResponse> getProduct(@Path("id") long id);



}
