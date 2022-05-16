package save.data;

public class User {
    String logname;
    String password;
    String address;
    String phone;
    String realname;

    public User() {
    }

    public User(String logname, String password, String address, String phone, String realname) {
        this.logname = logname;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.realname = realname;
    }

    public String getLogname() {
        return logname;
    }

    public void setLogname(String logname) {
        this.logname = logname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
