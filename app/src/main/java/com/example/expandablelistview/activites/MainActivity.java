package com.example.expandablelistview.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.expandablelistview.R;
import com.example.expandablelistview.roomdatabase.DatabaseClient;
import com.example.expandablelistview.roomdatabase.Product;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SaveProduct st = new SaveProduct();
        st.execute();
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


    class SaveProduct extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < 5; i++) {
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
            for (int j = 0; j < 5; j++) {
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
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            getProducts();
            Toast.makeText(getApplicationContext(), "Product Saved", Toast.LENGTH_LONG).show();
        }



        private void getProducts() {
            class GetProducts extends AsyncTask<Void, Void, List<Product>> {

                @Override
                protected List<Product> doInBackground(Void... voids) {
                    List<Product> productList = DatabaseClient
                            .getInstance(getApplicationContext())
                            .getAppDatabase()
                            .taskDao()
                            .getAll();
                    return productList;
                }

                @Override
                protected void onPostExecute(List<Product> products) {
                    super.onPostExecute(products);
                    Log.e("total list",""+products.toString());
                    Log.e(" list size",""+products.size());
                   /* TasksAdapter adapter = new TasksAdapter(MainActivity.this, tasks);
                    recyclerView.setAdapter(adapter);*/
                }
            }

            GetProducts gp = new GetProducts();
            gp.execute();
        }
    }


}
