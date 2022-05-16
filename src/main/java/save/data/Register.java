package save.data;
public class Register{  
    String  logname="" , phone="",
            address="",realname="",
            backNews="«Î ‰»Î–≈œ¢";
    public Register() {}
    public Register(String logname, String phone, String address, String realname, String backNews) {
        this.logname = logname;
        this.phone = phone;
        this.address = address;
        this.realname = realname;
        this.backNews = backNews;
    }
    public void setLogname(String logname){
       this.logname=logname;
    }
    public String getLogname(){  
       return logname;
    }
    public void setPhone(String phone){  
       this.phone=phone;
    }
    public String getPhone(){  
       return phone;
    }
    public void setAddress(String address){  
       this.address=address;
    }
    public String getAddress(){  
       return address;
    }
    public void setRealname(String realname){  
       this.realname=realname;
    }
    public String getRealname(){  
       return realname;
    }
    public void setBackNews(String backNews){  
       this.backNews=backNews;
    }
    public String getBackNews(){  
       return backNews;
    }
}
