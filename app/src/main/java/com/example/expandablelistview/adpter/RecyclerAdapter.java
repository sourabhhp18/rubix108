package com.example.expandablelistview.adpter;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expandablelistview.R;
import com.example.expandablelistview.roomdatabase.Product;
import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private final List<Product> productList;
    private Context context;
    String pBrand;
    //SparseBooleanArrays map integers to booleans.
    private SparseBooleanArray expandStates = new SparseBooleanArray();


    /**
     * Our Adapter's constructor
     *
     * @param products - list of products objects
     */
    public RecyclerAdapter(final List<Product> products) {
        this.productList = products;
        for (int i = 0; i < products.size(); i++) {
            expandStates.append(i, false);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.recycler_view_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Product product = productList.get(position);
        if(pBrand==null){
            pBrand=product.getProductBrand();
            holder.txtTitle.setText(product.getProductBrand());
        }
        holder.setIsRecyclable(false);

        holder.txtDescription.setText(product.getProductName());
        holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.material_teal_500));
        holder.expandableLayout.setInRecyclerView(true);
        holder.expandableLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.gray_light));
        holder.expandableLayout.setInterpolator(Utils.createInterpolator(Utils.BOUNCE_INTERPOLATOR));
        holder.expandableLayout.setExpanded(expandStates.get(position));
        holder.expandableLayout.setListener(new ExpandableLayoutListenerAdapter() {
            @Override
            public void onPreOpen() {
                //pass target view, and two foating points
                createRotateAnimator(holder.buttonLayout, 0f, 180f).start();
                expandStates.put(position, true);
            }

            @Override
            public void onPreClose() {
                createRotateAnimator(holder.buttonLayout, 180f, 0f).start();
                expandStates.put(position, false);
            }
        });

        holder.buttonLayout.setRotation(expandStates.get(position) ? 180f : 0f);
        holder.buttonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onClickButton(holder.expandableLayout);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    /**
     * Toggle our ExpandableLayout state when clicked.
     *
     * @param expandableLayout
     */
    private void onClickButton(final ExpandableLayout expandableLayout) {
        expandableLayout.toggle();
    }

    /**
     * Create Animation for our ExpandableLayout.
     * We use ObjectAnimator,a subclass of ValueAnimator that will provide us support for
     * animating properties on target objects.
     *
     * @param target
     * @param from
     * @param to
     * @return
     */
    public ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }

    /**
     * Our ViewHolder class extends RecyclerView.ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle, txtDescription;
        public RelativeLayout buttonLayout;
        /**
         * You must use the ExpandableLinearLayout in the recycler view.
         * The ExpandableRelativeLayout doesn't work.
         */
        public ExpandableLinearLayout expandableLayout;

        public ViewHolder(View v) {
            super(v);
            txtTitle = v.findViewById(R.id.txt_title);
            txtDescription = v.findViewById(R.id.txt_description);
            buttonLayout = v.findViewById(R.id.button);
            expandableLayout = v.findViewById(R.id.expandableLayout);
        }
    }
}
