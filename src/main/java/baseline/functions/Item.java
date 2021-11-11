package baseline.functions;

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

    public void setPrice(double worth) {
        this.price = worth;
    }

    //double check all strings to make sure they're in proper format

    public boolean nameRegex(String product){
        // if name is  within 2 and 256 characters, set this to true and setname to this
        //else, send as false
        return true;
    }

    public boolean serialRegex (String sNumber){
        //if serial is in regex "A-XXX-XXX-XXX", where A is a letter and x is a letter or number,
        //set to true and set Serial to this. otherwise, send as false
        //Note: i'll have a function to check if the serial matches another serial in the controller function
        return true;
    }
    public boolean priceRegex(String worth){
        //if price is greater or equal to 0, send as true and set price to this
        //set to true and send price to this
        //otherwise, set to false
        return  true;
    }
}
