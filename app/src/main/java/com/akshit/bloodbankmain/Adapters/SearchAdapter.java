package com.akshit.bloodbankmain.Adapters;

import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.PermissionChecker;
import androidx.recyclerview.widget.RecyclerView;

import com.akshit.bloodbankmain.Models.Donor;
import com.akshit.bloodbankmain.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

  private List<Donor> dataSet;
  private Context context;

  public SearchAdapter(
      List<Donor> dataSet, Context context) {
    this.dataSet = dataSet;
    this.context = context;
  }

  public interface OnItemClickListener{
    void onClickItem(int positon);
  }

  public OnItemClickListener onItemClickListener;
  public void setOnItemClickListener(OnItemClickListener onItemClickListener){
    this.onItemClickListener = onItemClickListener;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.donor_item_main, parent, false);
    return new ViewHolder(view, onItemClickListener);
  }


  @Override
  public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
    holder.name.setText(dataSet.get(position).getName());
    holder.mobile_number.setText(dataSet.get(position).getPhone_number());
    holder.city_pin.setText(dataSet.get(position).getPin_code());
//
//        if (PermissionChecker.checkSelfPermission(context, CALL_PHONE)
//            == PermissionChecker.PERMISSION_GRANTED) {
//          Intent intent = new Intent(Intent.ACTION_CALL);
//          intent.setData(Uri.parse("Mob Number :" + dataSet.get(position).getPhone_number()));
//          context.startActivity(intent);
//        } else {
//          ((Activity) context).requestPermissions(new String[]{CALL_PHONE}, 401);
//        }
  }


  @Override
  public int getItemCount() {
    return dataSet.size();
  }


  class ViewHolder extends RecyclerView.ViewHolder {

    TextView name, mobile_number, city_pin;
    ImageView callButton;

    ViewHolder(final View itemView, OnItemClickListener listener) {
      super(itemView);
      name = itemView.findViewById(R.id.name);
      mobile_number = itemView.findViewById(R.id.mobile_number);
      city_pin = itemView.findViewById(R.id.city_pin);
      callButton = itemView.findViewById(R.id.call_button);

      itemView.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
          int position = getAdapterPosition();
          if(position!=RecyclerView.NO_POSITION){
            listener.onClickItem(position);
          }
        }
      });
    }

  }

}
