package baseline.functions;

import java.math.BigDecimal;

public class Item {
    //set up  what each item has
    String name;
    String serial;
    double price;
    //create a new item
    public Item(String product, String sNumber, double worth){
        this.name = product;
        this.serial = sNumber;
        this.price = worth;
    }
    //make standard getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(String worth) {
        double number = Double.parseDouble(worth);
        this.price = number;
    }

    //double check all strings to make sure they're in proper format

    public boolean nameRegex(String product){
        // if name is  within 2 and 256 characters, set this to true and setname to this
        if (product.length() > 2 && product.length() < 257){
            return true;
        }
        //else, send as false
        else {
            return false;
        }
    }

    public boolean serialRegex (String sNumber){
        //if serial is in regex "A-XXX-XXX-XXX", where A is a letter and x is a letter or number,
        if (sNumber.matches("([a-zA-Z])-([a-zA-Z|0-9]{3})-([a-zA-Z|0-9]{3})-([a-zA-Z|0-9]{3})")){
            //set to true and set Serial to this. otherwise, send as false
            return true;
        }
        else
        {
            return  false;
        }
        //Note: i'll have a function to check if the serial matches another serial in the controller function
    }
    public boolean priceRegex(String worth){
        //try parsing it, if it doesn't work return false
        double tester = 1;
        try{
            double number = Double.parseDouble(worth);
            System.out.println(number);
            if (number %tester == 0){
                System.out.println(number);
                return true;
            }
            else
            {
                return  false;
            }
        }
        catch (NumberFormatException numberFormatException){
            //otherwise, set to false
            return false;
        }

    }
}
