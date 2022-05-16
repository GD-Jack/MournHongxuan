package save.data;

public class Administrators {
    String logname = "";
    String password;
    String backNews = "Î´µÇÂ¼";

    public Administrators() {
    }

    public Administrators(String logname, String password, String backNews) {
        this.logname = logname;
        this.password = password;
        this.backNews = backNews;
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

    public String getBackNews() {
        return backNews;
    }

    public void setBackNews(String backNews) {
        this.backNews = backNews;
    }
}
