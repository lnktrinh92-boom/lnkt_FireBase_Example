package vn.edu.ueh.thanhdnh.firebase_example;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
  private TextView txtUname, txtBio;
  private ImageView imgProfile;
  private UserViewAdapter adapter;

  public UserViewHolder(@NonNull View itemView, UserViewAdapter adapter) {
    super(itemView);
    txtUname = itemView.findViewById(R.id.txt_uname);
    txtBio = itemView.findViewById(R.id.txt_bio);
    imgProfile = itemView.findViewById(R.id.img_profile);
    this.adapter = adapter;
  }

  public TextView getTxtUname() { return txtUname; }
  public TextView getTxtBio() { return txtBio; }
  public ImageView getImgProfile() { return imgProfile; }
}