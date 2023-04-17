import java.util.*;  
import java.text.SimpleDateFormat;    

class Product {  
    // properties  
    private String id;  
    private String pname;  
    private int qty;  
    private double price;  
    private double totalPrice;  
       
    // constructor  
    Product(String id, String pname, int qty, double price, double totalPrice) {  
        this.id=id;  
        this.pname = pname;  
        this.qty = qty;  
        this.price = price;  
        this.totalPrice = totalPrice;  
    }  
    
    // getter methods  
    public String getId() {  
        return id;  
    }  
    public String getPname() {  
        return pname;  
    }  
    public int getQty() {  
        return qty;  
    }  
    public double getPrice() {  
        return price;  
    }  
    public double getTotalPrice() {  
        return totalPrice;  
    }  
    
    //displayFormat  
    public static void displayFormat() {  
        System.out.print("-----------------------------------------------------------------------------------------------------------------------------------");  
        System.out.print("\nProduct ID \t\tName\t\tQuantity\t\tRate \t\t\t\tTotal Price\n");  
        System.out.print("-----------------------------------------------------------------------------------------------------------------------------------\n");  
    }  
       
    // display  
    public void display() {  
        System.out.format("   %-9s             %-9s      %5d               %9.2f                       %14.2f\n" ,id, pname, qty, price, totalPrice);  
    }  
}

class Customer {
    private String name;
    private int points;

    public Customer(String name) {
        this.name = name;
        this.points = 0;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int newPoints) {
        points += newPoints;
    }

}

public class ShoppingBill {
    public static void main(String args[]) {
        // variables  
        String id = null;  
        String productName = null;  
        int quantity = 0;  
        double price = 0.0;  
        double totalPrice = 0.0;  
        double overAllPrice = 0.0;  
        double cgst, sgst, subtotal=0.0, discount=0.0,ldiscount=0.0;  
        char choice = '\0';  
        
        System.out.println("\t\t\t\t--------------------Invoice-----------------");  
        System.out.println("\t\t\t\t\t\t    "+"SRP Mart");  
        System.out.println("\t\t\t\t\tModel Town,Jalandhar,Punjab\n");   
        System.out.println("GSTIN: 03AWBPP8756K592"+"\t\t\t\t\t\t\tContact: (+91) 9988776655");  

        //format of date and time  
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");    
        Date date = new Date();    
        System.out.println("Date: "+formatter.format(date)+"\t\t\t\t\t\t\t (+91) 9998887770\n");  
  
    System.out.print("Enter Customer Name: ");  
    Scanner scanner = new Scanner(System.in);  
    String customerName = scanner.nextLine();  
    Customer customer = new Customer(customerName);
    System.out.println("Do you have Loyalty points? (y/n)");
    char has=scanner.next().charAt(0);
    if(has == 'y' || has == 'Y'){
        System.out.print("How much you have : ");
        int lpoints=scanner.nextInt();
        ldiscount = lpoints*5;
    }
    List<Product> productsList = new ArrayList<Product>();  

    System.out.println("Please enter product details ----------");
    do{  
        System.out.print("Enter Product ID: ");  
        id = scanner.next();
        System.out.print("Enter Product Name: ");  
        productName = scanner.next();  
        System.out.print("Enter Quantity: ");  
        quantity = scanner.nextInt();  
        System.out.print("Enter Price: ");  
        price = scanner.nextDouble();  
        totalPrice = price * quantity;  
        Product product = new Product(id, productName, quantity, price, totalPrice);  
        productsList.add(product);  
        System.out.println("Do you want to add more products? (y/n)");  
        choice = scanner.next().charAt(0);  
    }while(choice == 'y' || choice == 'Y');  
    
    Product.displayFormat();  
    for(Product product : productsList) {  
        product.display();  
        subtotal += product.getTotalPrice();  
    }  
    
    if(subtotal >= 1000 && subtotal < 5000) {
        discount = 0.05 * subtotal;
        customer.addPoints((int)subtotal/100);
    } else if(subtotal >= 5000 && subtotal < 10000) {
        discount = 0.1 * subtotal;
        customer.addPoints((int)subtotal/50);
    } else if(subtotal >= 10000) {
        discount = 0.15 * subtotal;
        customer.addPoints((int)subtotal/20);
    }
    
    System.out.format("\nSub Total : \t\t\t\t\t\t\t\t\t\t%.2f\n", subtotal);  
    cgst = subtotal * 0.025;  
    sgst = subtotal * 0.025;  
    overAllPrice = subtotal + cgst + sgst - discount - ldiscount;  
    System.out.format("CGST : \t\t\t\t\t\t\t\t\t\t\t%.2f\n",cgst);  
    System.out.format("SGST : \t\t\t\t\t\t\t\t\t\t\t%.2f\n",sgst);  
    System.out.format("Discount : \t\t\t\t\t\t\t\t\t\t%.2f\n",discount);  
    System.out.format("Total : \t\t\t\t\t\t\t\t\t\t%.2f\n",overAllPrice);  
    System.out.println("\nEarned Loyalty Points: " + customer.getPoints());
    System.out.println("\t\t\t\t----------------Thank You for the Shopping!!-----------------");  
    System.out.println("\t\t\t\t                     Visit Again"); 
}  
}
