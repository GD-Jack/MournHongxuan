package save.data;

public class Shoppingform {
    int id;
    String logname;
    String name;
    float price;
    int amount;
    int book_id;

    public Shoppingform() {
    }

    public Shoppingform(int id, String logname, String name, float price, int amount, int book_id) {
        this.id = id;
        this.logname = logname;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.book_id = book_id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

}
