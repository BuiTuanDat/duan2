package com.teamnaqq.androidbaberbooking.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CartDAO {
    @Query("SELECT * FROM Cart WHERE userPhone = :userPhone")
    List<CartItem> getAllItemFromCart(String userPhone);

    @Query("SELECT COUNT(*) from Cart where userPhone = :userPhone")
    int CountItemInCart(String userPhone);

    @Query("SELECT * from Cart where productId =:productId AND userPhone = :userPhone")
    CartItem getProductInCart(String productId, String userPhone);

    @Insert(onConflict = OnConflictStrategy.FAIL)
    void insert(CartItem... carts);

    @Update(onConflict = OnConflictStrategy.FAIL)
    void update(CartItem cart);

    @Delete
    void delete(CartItem cartItem);

    @Query("DELETE FROM Cart WHERE userPhone=:userPhone")
    void clearCart(String userPhone);

}
