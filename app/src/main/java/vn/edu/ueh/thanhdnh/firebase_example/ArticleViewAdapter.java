package vn.edu.ueh.thanhdnh.firebase_example;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ArticleViewAdapter extends RecyclerView.Adapter<ArticleViewHolder> {
  private Context context;
  private LayoutInflater mInflater;
  private List<Article> articles;

  public ArticleViewAdapter(Context context, List<Article> articles) {
    this.context = context;
    this.mInflater = LayoutInflater.from(context);
    this.articles = articles;
  }

  public void update(List<Article> articles){
    this.articles = articles;
  }

  @NonNull
  @Override
  public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View customView = mInflater.inflate(R.layout.article_list, parent, false);
    return new ArticleViewHolder(customView, this);
  }

  @Override
  public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
    Article currentArticle = articles.get(position);

    holder.getTxtTitle().setText(currentArticle.getTitle());
    holder.getTxtContent().setText(currentArticle.getContent());

    if (currentArticle.getImage_url() != null && !currentArticle.getImage_url().isEmpty()) {
      Glide.with(context)
              .load(currentArticle.getImage_url())
              .placeholder(R.mipmap.ic_launcher)
              .into(holder.getImgArticle());
    }

    // THÊM SỰ KIỆN CLICK VÀO ĐÂY
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(context, DetailActivity.class);
        // Gói object currentArticle vào Intent
        intent.putExtra("ARTICLE_DATA", currentArticle);
        // Bắt buộc thêm Flag này vì biến context truyền vào Adapter là BaseContext
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
      }
    });
  }

  @Override
  public int getItemCount() {
    return articles.size();
  }
}