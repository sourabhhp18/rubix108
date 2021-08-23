package com.example.expandablelistview.model;



public class Products {


    private int id;
    private String productBrand;
    private String productName;
    private String productQuantity;
    private String productDate;
    private String productRating;

    private byte[] productImage;

    public Products(String productName) {

        this.productName = productName;



    }

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
}
