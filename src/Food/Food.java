package Food;

import java.time.LocalDate;

public abstract class Food {
    private static int id_gen = 1;
    private int id = 0;
    private String name;
    private LocalDate deliveredAt;
    private LocalDate expiration_date;

    private String storage_place;
    private Boolean is_eatable;

    public Food() {
        id = id_gen++;
    }
    public Food(String n, LocalDate dAt, LocalDate eDate, String sPlace) {
        this();
        setName(n); setDeliveredAt(dAt); setExpiration_date(eDate);
        setStorage_place(sPlace);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(LocalDate deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public LocalDate getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(LocalDate expiration_date) {
        this.expiration_date = expiration_date;
    }

    public String getStorage_place() {
        return storage_place;
    }

    public void setStorage_place(String storage_place) {
        this.storage_place = storage_place;
    }

    public Boolean getIs_eatable() {
        return is_eatable;
    }

    public void setIs_eatable(Boolean is_eatable) {
        this.is_eatable = is_eatable;
    }
}
