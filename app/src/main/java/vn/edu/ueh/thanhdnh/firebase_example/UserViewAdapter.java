package vn.edu.ueh.thanhdnh.firebase_example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class UserViewAdapter extends RecyclerView.Adapter<UserViewHolder> {
  private Context context;
  private LayoutInflater mInflater;
  private List<User> users;

  public UserViewAdapter(Context context, List<User> users) {
    this.context = context;
    this.mInflater = LayoutInflater.from(context);
    this.users = users;
  }

  public void update(List<User> users){
    this.users = users;
  }

  @NonNull
  @Override
  public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View customView = mInflater.inflate(R.layout.contact_list, parent, false);
    return new UserViewHolder(customView, this);
  }

  @Override
  public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
    User currentuser = users.get(position);
    holder.getTxtUname().setText(currentuser.getUname());
    holder.getTxtBio().setText(currentuser.getShort_bio());

    if (currentuser.getUrl_profile() != null && !currentuser.getUrl_profile().isEmpty()) {
      Glide.with(context)
              .load(currentuser.getUrl_profile())
              .placeholder(R.mipmap.ic_launcher)
              .into(holder.getImgProfile());
    }
  }

  @Override
  public int getItemCount() {
    return users.size();
  }
}