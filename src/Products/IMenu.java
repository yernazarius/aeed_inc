package Products;

import java.util.ArrayList;

public interface IMenu {
    void displayMenu(String option);
    Integer getPriceOfOrder(int index, String option);
    void outputChosenOrder(int index, String option, ArrayList<String> items);
}
