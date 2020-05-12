package sample;

import java.util.ArrayList;

public class FoundText {

    public static ArrayList<String> findDateInText(String text) {
        ArrayList<String> foundDates = new ArrayList<>();
        for (int i = 0; i < text.length() - 10; ++i) {
            if (ValidDate.isValidDate(text.substring(i, i + 10))) {
                foundDates.add(text.substring(i, i + 10));
            }
        }
        return foundDates;
    }

}
