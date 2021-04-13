package com.example.ninja.house;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ninja.Armor;
import com.example.ninja.House;
import com.example.ninja.R;
import com.example.ninja.weapons.Item;
import android.view.MotionEvent;

import java.util.ArrayList;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.RecyclerViewViewHolder>
{
    Context context;

    private ArrayList<com.example.ninja.house.Item> arrayList;
    private ArrayList<Item> weapons;
    private ArrayList<Armor> armors;
    private SparseBooleanArray selectedItems;
    private final ArrayList<Integer> selected = new ArrayList<>();


    byte touchCounter = 0;
    int pos;
    int prev;

    public recyclerViewAdapter(ArrayList<Item> arrayList, Context context) {

        this.context = context;
        this.weapons = arrayList;
    }




    class RecyclerViewViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener

    {



        public ImageView imageView;
        public TextView textView1;
        public TextView textView2;
        public TextView textView;
        public TextView weaponDescription;
        public RelativeLayout layout;

        public RecyclerViewViewHolder(@NonNull View itemView)  {
            super(itemView);

            itemView.setOnTouchListener(this);

            imageView = itemView.findViewById(R.id.imageView);
            //textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            //textView = itemView.findViewById(R.id.textView);
            layout = itemView.findViewById(R.id.weaponDescriptionCard);



            //weaponDescription = itemView.findViewById(R.id.weaponDescriptionTextView);

        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            int position = getAdapterPosition();

            //imageView.setSelected(true);

            //Item recipeItem = arrayList.get(position);
            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    //House.jj();

                    House.weaponId = position;
                    Item weapon = weapons.get(position);

                    // Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
                    House.weaponId = position;
                    House.jj();
/*
                    if(pos == position)
                    {
                        touchCounter++;
                    } else{
                        pos = position;
                        touchCounter = 0;
                    }

                    if(touchCounter == 1)
                    {
                        House.jj();
                        touchCounter = 0;
                    }
*/
                    break;
            }

            //ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(200, 200);
            //weaponDescription.setBackground(R.drawable.shop_back);
            //weaponDescription.setLayoutParams(params);
            //weaponDescription.setText(weapons.get(position).getWeaponDescription());

            //Intent intent = new Intent(context, recipe.class);
            //intent.putExtra("img", recipeItem.getImageResource());
            //intent.putExtra("fullRecipe",recipeItem.getFullRecipe());
            //intent.putExtra("ff",""+recipeItem.getFullRecipe());
            //context.startActivity(intent);

            return false;
        }


        public void o(View v) {

            int position = getAdapterPosition();
            //Item recipeItem = arrayList.get(position);
            Item weapon = weapons.get(position);

            //Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
            House.weaponId = position;

            //ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(200, 200);
            //weaponDescription.setBackground(R.drawable.shop_back);
            //weaponDescription.setLayoutParams(params);
            //weaponDescription.setText(weapons.get(position).getWeaponDescription());

            //Intent intent = new Intent(context, recipe.class);
            //intent.putExtra("img", recipeItem.getImageResource());
            //intent.putExtra("fullRecipe",recipeItem.getFullRecipe());
            //intent.putExtra("ff",""+recipeItem.getFullRecipe());
            //context.startActivity(intent);
        }

        public boolean onTouchEvent(MotionEvent event) {



        return false;
        }

    }

    @NonNull
    @Override
    public RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)  {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,
                parent, false); // передаём разметку нашего CardView
        RecyclerViewViewHolder recyclerViewViewHolder = new RecyclerViewViewHolder(view);
        return recyclerViewViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewViewHolder holder, int position) {
        //Item recyclerViewItem = arrayList.get(position);

        Item recyclerViewWeapon = weapons.get(position);
        //holder.imageView.setSelected(selectedItems.get(position, false));
       // holder.imageView.setSelected(selectedItems.get(position, false));
        holder.imageView.setImageResource(recyclerViewWeapon.getImg());
        //holder.textView1.setText(recyclerViewWeapon.getWeaponDescription());
        //holder.textView.setText(recyclerViewWeapon.getWeaponName());
        holder.textView2.setText(recyclerViewWeapon.getQuantity()+1+"x");
        //holder.weaponDescription.setText(recyclerViewWeapon.getWeaponDescription()+"");

    }






    @Override
    public int getItemCount() {
        return weapons.size();
    }

}

