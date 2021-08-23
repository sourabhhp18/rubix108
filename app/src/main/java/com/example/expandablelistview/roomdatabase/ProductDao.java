package com.example.expandablelistview.roomdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.expandablelistview.model.Brand;
import com.example.expandablelistview.model.Products;

import java.util.List;
@Dao
public interface ProductDao {
    @Query("SELECT * FROM product where produc_brand = :pBrand")
    List<Products> getProductsByBrand(String pBrand);

    @Query("SELECT * FROM product group by produc_brand")
    List<Products> getAllBrands();

    @Insert
    void insert(Product product);

    @Delete
    void delete(Product product);

    @Update
    void update(Product product);
}
