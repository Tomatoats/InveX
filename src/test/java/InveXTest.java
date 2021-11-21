import baseline.controllers.ManagerController;
import baseline.functions.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
    public class InveXTest extends ManagerController {
        ObservableList<Item> basicList = FXCollections.observableArrayList();
        ManagerController mc = new ManagerController();
        Item items = new Item("","","");

        @Test
        void CanitHandle1024() {
            Item item = new Item("yo yo", "s-4re-e3d-e2s", "500");
            for (int i = 0; i < 1200; i++) {
                basicList.add(item);
            }
            boolean overOneThousand;
            if (basicList.size() > 1100) {
                overOneThousand = true;
            } else {
                overOneThousand = false;
            }
            assertEquals(true, overOneThousand);
        }
        @Test
        void over0(){
            boolean flag = true;
            String worth = "-5";
            flag = (items.priceRegex(worth));
            assertEquals(false, flag);
            worth = "100.23";
            flag = (items.priceRegex(worth));
            assertEquals(true,flag);
            worth = "0";
            flag = (items.priceRegex(worth));
            assertEquals(false,flag);

        }
        @Test
        void correctSerialFormat(){
            String tester = "J-Q3r-5fQ-gU8";
            boolean flag = false;
            flag = (items.serialRegex(tester));
            assertEquals(true,flag);
            tester = "7-ewi-i39-oer4";
            flag = (items.serialRegex(tester));
            assertEquals(false, flag);
        }
        @Test
        void UniqueSerials(){
            boolean flag = true;
            Item items = new Item("string","c-raz-ybi-tch","500");
            list.add(items);
            String serial = "c-raz-ybi-tch";
            flag = uniqueSerial(serial);
            assertEquals(false,flag);
        }
        @Test
        void AddItems(){
            String name = ("Hello dawg");
            String serial = ("a-bec-dec-fec");
            String price = ("43.56");
            Item items = new Item(name, serial, price);
            list.add(items);
            assertEquals(true,list.get(0).getSerial().equals("a-bec-dec-fec"));
        }
        @Test
        void removeItems(){
            String name = ("Hello dawg");
            String serial = ("a-bec-dec-fec");
            String price = ("43.56");
            Item items = new Item(name, serial, price);
            int i = 0;
            for (i = 0; i < 10; i++){
                list.add(items);
            }
            assertEquals(i, list.size());
            list.remove(5);
            assertEquals(i-1,list.size());
        }
        @Test
        void clearAllItemsTest(){
            String name = ("Hello dawg");
            String serial = ("a-bec-dec-fec");
            String price = ("43.56");
            Item items = new Item(name, serial, price);
            int i = 0;
            for (i = 0; i < 10; i++){
                list.add(items);
            }
            assertEquals(i, list.size());
            list.remove(0,list.size());
            assertEquals(0,list.size());
        }
        @Test
        void SearchByName() {
            ObservableList<Item> names = FXCollections.observableArrayList();
            Item items = new Item("string", "c-raz-ybi-tch", "500");
            list.add(items);
            Item item2 = new Item ("bing","a-123-456-789","433.22");
            list.add(item2);
            Item item3 = new Item ("Hell", "b-4hj-ybi-yok", "25");
            list.add(item3);
            String toSearch = "ing";
            for (Item item : list){
                String lowername = item.getName().toLowerCase(Locale.ROOT);
                if (lowername.contains(toSearch)) {
                    names.add(item);
                }
            }
            assertEquals(2,names.size());
        }
        @Test
        void SearchBySerial(){
            ObservableList<Item> serials = FXCollections.observableArrayList();
            Item items = new Item("string", "c-raz-ybi-tch", "500");
            list.add(items);
            Item item2 = new Item ("bing","a-123-456-789","433.22");
            list.add(item2);
            Item item3 = new Item ("Hell", "b-4hj-ybi-yok", "25");
            list.add(item3);
            String toSearch = "ybi";
            for (Item item : list){
                String lowername = item.getSerial().toLowerCase(Locale.ROOT);
                if (lowername.contains(toSearch)) {
                    serials.add(item);
                }
            }
            assertEquals(2,serials.size());
        }
        @Test
        void SaveAndLoad(){}


    }
