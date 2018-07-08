package leyan.hwma.com.ebook;
public class User {
    public String account;
    public String password;

    public User() { //Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
