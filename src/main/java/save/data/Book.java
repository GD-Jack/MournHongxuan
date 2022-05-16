package save.data;

public class Book {
    int id;
    String name;
    String author;
    String translator;
    String press;
    float price;
    int num;
    String introduction;

    public Book() {
    }

    public Book(int id, String name, String author, String translator, String press, float price, int num, String introduction) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.translator = translator;
        this.press = press;
        this.price = price;
        this.num = num;
        this.introduction = introduction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
