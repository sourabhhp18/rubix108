package com.example.expandablelistview.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.expandablelistview.R;
import com.example.expandablelistview.adpter.BrandExpandableLayoutHelper;
import com.example.expandablelistview.interfaces.ItemClickListener;
import com.example.expandablelistview.model.Brand;
import com.example.expandablelistview.model.Products;
import com.example.expandablelistview.roomdatabase.DatabaseClient;
import com.example.expandablelistview.roomdatabase.Product;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener {
    RecyclerView mRecylerview;

    private ArrayList<Products> products_l;
    BrandExpandableLayoutHelper brandExpandableLayoutHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecylerview = findViewById(R.id.recycler_view);

       /* mRecylerview.addItemDecoration(new DividerItemDecoration(this,0));
        mRecylerview.setLayoutManager(new LinearLayoutManager(this));*/
        brandExpandableLayoutHelper = new BrandExpandableLayoutHelper(this,
                mRecylerview,this, 3);


        ArrayList<Products> arrayList = new ArrayList<>();
        arrayList.add(new Products("iPhone",0));
        arrayList.add(new Products("iPad",1));
        arrayList.add(new Products("iPod",2));
        arrayList.add(new Products("iMac",3));
        brandExpandableLayoutHelper.addBrand("Apple",arrayList);

        arrayList = new ArrayList<>();
        arrayList.add(new Products("LG", 0));
        arrayList.add(new Products("Apple", 1));
        arrayList.add(new Products("Samsung", 2));
        arrayList.add(new Products("Motorola", 3));
        arrayList.add(new Products("Sony", 4));
        arrayList.add(new Products("Nokia", 5));
        brandExpandableLayoutHelper.addBrand("Companies", arrayList);
        arrayList = new ArrayList<>();
        arrayList.add(new Products("Chocolate", 0));
        arrayList.add(new Products("Strawberry", 1));
        arrayList.add(new Products("Vanilla", 2));
        arrayList.add(new Products("Butterscotch", 3));
        arrayList.add(new Products("Grape", 4));
        brandExpandableLayoutHelper.addBrand("Ice cream", arrayList);

        brandExpandableLayoutHelper.notifyDataSetChanged();
       // saveProducts();
    }

    private byte[] byteImage() {
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.samsung);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitMapData = stream.toByteArray();
        return bitMapData;
    }

    private void saveProducts() {

        class SaveProduct extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                for (int i = 1; i < 5; i++) {
                    //creating a Product
                    Product product = new Product();
                    product.setProductBrand("Samsung");
                    product.setProductName("Samsung S" + i);
                    product.setProductRating("" + i);
                    product.setProductQuantity("55" + i + 3);
                    product.setProductImage(byteImage());

                    //adding to database
                    DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                            .taskDao()
                            .insert(product);
                }

               /* for (int j = 0; j < 5; j++) {
                    //creating a Product
                    Product product = new Product();
                    product.setProductBrand("Apple");
                    product.setProductName("Apple A" + j);
                    product.setProductRating("" + j);
                    product.setProductQuantity("25" + j + 6);
                    product.setProductImage(byteImage());

                    //adding to database
                    DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                            .taskDao()
                            .insert(product);
                }*/

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                getBrands();
                Toast.makeText(getApplicationContext(), "Product Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveProduct st = new SaveProduct();
        st.execute();
    }

    private ArrayList<Products> getProducts(String pBrand) {
        class GetProducts extends AsyncTask<Void, Void, List<Products>> {

            @Override
            protected List<Products> doInBackground(Void... voids) {
                List<Products> productList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .taskDao()
                        .getProductsByBrand(pBrand);
                return productList;
            }

            @Override
            protected void onPostExecute(List<Products> products) {
                super.onPostExecute(products);
                Log.e("total list", "" + products.toString());
                Log.e(" list size", "" + products.size());
products_l= new ArrayList<Products>(products);
            }
        }

        GetProducts gp = new GetProducts();
        gp.execute();
        return products_l;
    }
    private void getBrands() {
        class GetBrands extends AsyncTask<Void, Void, List<Products>> {

            @Override
            protected List<Products> doInBackground(Void... voids) {
                List<Products> brandList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .taskDao()
                        .getAllBrands();
                return brandList;
            }

            @Override
            protected void onPostExecute(List<Products > brands) {
                super.onPostExecute(brands);
                Log.e("total list", "" + brands.toString());
                Log.e(" list size", "" + brands.size());


            }
        }

        GetBrands gb = new GetBrands();
        gb.execute();
    }




    @Override
    public void itemClicked(Products products) {

    }

    @Override
    public void itemClicked(Brand brand) {

    }
}
