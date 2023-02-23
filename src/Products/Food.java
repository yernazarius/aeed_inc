package Products;

import java.time.LocalDate;
public abstract class Food {

    private String name;
    private int price;


    private LocalDate expiration_date;

    private String storage_place;
    private String type;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public Food() {}

    public Food(String name, int price, String type) {
        setName(name); setPrice(price); setType(type);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





}
