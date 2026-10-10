package vn.edu.ueh.thanhdnh.firebase_example;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class DetailActivity extends AppCompatActivity {
    ImageView imgDetail;
    TextView tvTitle, tvContent;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Ánh xạ View
        imgDetail = findViewById(R.id.detail_img);
        tvTitle = findViewById(R.id.detail_title);
        tvContent = findViewById(R.id.detail_content);

        // Khởi tạo Firestore
        db = FirebaseFirestore.getInstance();

        // Lấy object Article được truyền sang từ Intent
        if (getIntent() != null && getIntent().hasExtra("ARTICLE_DATA")) {
            Article article = (Article) getIntent().getSerializableExtra("ARTICLE_DATA");

            // Hiển thị tạm dữ liệu tĩnh ban đầu cho nhanh (đỡ bị giật màn hình chờ mạng)
            if (article != null) {
                updateUI(article);

                // Gắn bộ lắng nghe Realtime (Snapshot Listener) vào document cụ thể này
                if (article.getId() != null) {
                    DocumentReference docRef = db.collection("articles").document(article.getId());

                    docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException error) {
                            if (error != null) {
                                Log.e("FirestoreError", "Lỗi lắng nghe dữ liệu", error);
                                return;
                            }

                            if (snapshot != null && snapshot.exists()) {
                                // Ép kiểu dữ liệu mới nhất từ Firebase về Article
                                Article updatedArticle = snapshot.toObject(Article.class);
                                if (updatedArticle != null) {
                                    // Cập nhật lại giao diện ngay lập tức
                                    updateUI(updatedArticle);
                                }
                            }
                        }
                    });
                }
            }
        }
    }

    // Hàm phụ trợ để cập nhật giao diện đỡ phải viết lại nhiều lần
    private void updateUI(Article currentArticle) {
        tvTitle.setText(currentArticle.getTitle());
        tvContent.setText(currentArticle.getContent());

        if (currentArticle.getImage_url() != null && !currentArticle.getImage_url().isEmpty()) {
            Glide.with(DetailActivity.this)
                    .load(currentArticle.getImage_url())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(imgDetail);
        }
    }
}