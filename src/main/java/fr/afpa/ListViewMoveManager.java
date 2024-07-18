package fr.afpa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class ListViewMoveManager {
    public static <T> void moveSelectedItemUp(ListView<T> listView) {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex > 0) {
            ObservableList<T> items = FXCollections.observableArrayList(listView.getItems());
            T item = items.remove(selectedIndex);
            items.add(selectedIndex - 1, item);
            listView.setItems(items);
            listView.getSelectionModel().select(selectedIndex - 1);
        }
    }

    public static <T> void moveSelectedItemDown(ListView<T> listView) {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex < listView.getItems().size() - 1) {
            ObservableList<T> items = FXCollections.observableArrayList(listView.getItems());
            T item = items.remove(selectedIndex);
            items.add(selectedIndex + 1, item);
            listView.setItems(items);
            listView.getSelectionModel().select(selectedIndex + 1);
        }
    }
}

