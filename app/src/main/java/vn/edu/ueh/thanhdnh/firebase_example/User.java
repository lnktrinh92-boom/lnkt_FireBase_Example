package vn.edu.ueh.thanhdnh.firebase_example;

public class User {
  private String id;
  private String uname;
  private String password;
  private String url_profile;
  private String short_bio;

  // Bắt buộc phải có constructor rỗng cho Firestore
  public User() {
  }

  // Constructor không có ID (dùng khi Add user mới, Firestore tự tạo ID)
  public User(String uname, String password, String url_profile, String short_bio) {
    this.uname = uname;
    this.password = password;
    this.url_profile = url_profile;
    this.short_bio = short_bio;
  }

  // Constructor đầy đủ
  public User(String id, String uname, String password, String url_profile, String short_bio) {
    this.id = id;
    this.uname = uname;
    this.password = password;
    this.url_profile = url_profile;
    this.short_bio = short_bio;
  }

  // CÁC HÀM GETTER VÀ SETTER (Đây là phần file của bạn đang thiếu dẫn đến báo lỗi đỏ)
  public String getId() { return id; }
  public void setId(String id) { this.id = id; }

  public String getUname() { return uname; }
  public void setUname(String uname) { this.uname = uname; }

  public String getPassword() { return password; }
  public void setPassword(String password) { this.password = password; }

  public String getUrl_profile() { return url_profile; }
  public void setUrl_profile(String url_profile) { this.url_profile = url_profile; }

  public String getShort_bio() { return short_bio; }
  public void setShort_bio(String short_bio) { this.short_bio = short_bio; }
}