package com.anythingintellect.happyshop.repo;

import com.anythingintellect.happyshop.BaseTest;
import com.anythingintellect.happyshop.db.LocalDataStore;
import com.anythingintellect.happyshop.model.CartEntry;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.realm.RealmResults;

import static org.mockito.Mockito.verify;

/**
 * Created by ishan.dhingra on 31/08/17.
 */

public class CartRepositoryTest extends BaseTest {

    @Mock
    private LocalDataStore localStore;
    private CartRepository cartRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        cartRepository = new CartRepository(localStore);
    }


    // addToCart
    // Should add to local store cart
    @Test
    public void testAddToCart_ShouldCallLocalStoreToSave() {
       long productId = 1000L;
        cartRepository.addToCart(productId);
        verify(localStore).addToCart(productId);
    }



    // getFromCart
    // Should get from local store cart
    @Test
    public void testGetFromCart_ShouldGetFromLocalCart() {
        long productId = 1000L;
        RealmResults<CartEntry> cartEntry =
                cartRepository.getCartEntry(productId);
        verify(localStore).getCartEntryByProduct(productId);

    }




}
