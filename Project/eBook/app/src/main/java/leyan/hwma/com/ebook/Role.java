package leyan.hwma.com.ebook;
public class Role {   /**日後以自定義JSON從網路下載資料格式為主*/
    String account;
    String date;
    int amount;
    int type;
    public Role(){} //JackSon解析需使用預設建構子
    public Role(String account, String date, int amount, int type) {
        this.account = account;
        this.date = date;
        this.amount = amount;
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
