package save.data;

public class Login {
    String logname="";
    String backNews="Î´µÇÂ¼";

    public Login() {
    }

    public Login(String logname, String backNews) {
        this.logname = logname;
        this.backNews = backNews;
    }

    public void setLogname(String logname){
       this.logname = logname;
    }
    public String getLogname(){
       return logname;
    }
    public void setBackNews(String s) {
       backNews = s;
    } 
    public String getBackNews(){
       return backNews;
    }
}
