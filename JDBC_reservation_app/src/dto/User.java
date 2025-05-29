package dto;

public class User {
	private int userId;
	private String userName;
    private String phone;
    private String email;

    public User(int userId, String userName, String phone, String email) {
        this.userId = userId;
        this.userName = userName;
        this.phone = phone;
        this.email = email;
    }

    public User(String userName, String phone, String email) {
        this.userName = userName;
        this.phone = phone;
        this.email = email;
    }


    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }


}
