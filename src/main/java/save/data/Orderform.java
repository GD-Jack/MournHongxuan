package save.data;

public class Orderform {
    int id;
    String title;
    String logname;
    String message;

    public Orderform() {
    }

    public Orderform(int id, String title, String logname, String message, String ids) {
        this.id = id;
        this.title = title;
        this.logname = logname;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogname() {
        return logname;
    }

    public void setLogname(String logname) {
        this.logname = logname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
