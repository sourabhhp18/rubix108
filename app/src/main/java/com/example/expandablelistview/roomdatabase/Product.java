package com.example.expandablelistview.roomdatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Arrays;

@Entity
public class Product implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "produc_brand")
    private String productBrand;

    @ColumnInfo(name = "product_name")
    private String productName;

    @ColumnInfo(name = "product_quantity")
    private String productQuantity;

    @ColumnInfo(name = "product_date")
    private String productDate;

    @ColumnInfo(name = "product_rating")
    private String productRating;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] productImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public String getProductRating() {
        return productRating;
    }

    public void setProductRating(String productRating) {
        this.productRating = productRating;
    }

    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productBrand='" + productBrand + '\'' +
                ", productName='" + productName + '\'' +
                ", productQuantity='" + productQuantity + '\'' +
                ", productDate='" + productDate + '\'' +
                ", productRating='" + productRating + '\'' +
                ", productImage=" + Arrays.toString(productImage) +
                '}';
    }
}
