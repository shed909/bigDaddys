
/**
 * Class menuItem
 * 
 * A custom class to demonstrate an object oriented menu driver,
 * demonstrated in "trialMenu.java".
 * 
 */

import java.text.DecimalFormat;

public class menuItem
{
   // Define instance variables
   private String itemNumber, itemName, itemDescription;
   private double itemPrice, orderPrice, itemDiscount;
   private int itemStock;
   private boolean setDiscount;

   // Define constructor
   public menuItem(String itemNumber, String itemName,
                   String itemDescription, double itemPrice)
   {
      this.itemNumber = itemNumber;
      this.itemName = itemName;
      this.itemDescription = itemDescription;
      this.itemPrice = itemPrice;
      this.itemStock = 20;
      this.itemDiscount = 0;
   }

   // Define accessors
   public String getItemNumber()
   {
      return itemNumber;
   }

   public String getItemName()
   {
      return itemName;
   }

   public String getItemDescription()
   {
      return itemDescription;
   }

   public double getItemPrice()
   {
      if (this.itemDiscount != 0)
      {
         itemPrice -= (itemDiscount * itemPrice);
      }
      return itemPrice;
   }

   // Define mutators
   public boolean setDiscount(double itemDiscount)
   {
      if (itemDiscount <= ((double)9 / 100) ||
          itemDiscount >= ((double)51 / 100))
      {
         return false;
      }
      else
      {
         this.itemDiscount = itemDiscount;
         return true;
      }
   }

   public double orderItem(int quantity)
   {
      if (itemStock >= quantity)
      {
         itemStock -= quantity;
         orderPrice = itemPrice * quantity;

      }
      else
      {
         orderPrice = Double.NaN;
      }
      return orderPrice;
   }

   public boolean restock(int quantity)
   {
      if (quantity <= 0)
      {
         return false;

      }
      else
      {
         this.itemStock = (itemStock + quantity);
         return true;
      }
   }


   // Define a public void method to print a line in a menu format
   public void printMenuLine()
   {
      System.out.printf("%s.  %-20s        $%-20.2f \n              " +
                        "(%s)\n\n",
                        itemNumber,
                        itemName,
                        itemPrice,
                        itemDescription);
   }
   
   // Define a public void method to print details of each variable
   public void printDetails()
   {
      System.out.println("Item Number: " + itemNumber);
      System.out.println("Item Name: " + itemName);
      System.out.println("Item Description: " + itemDescription);

      if (setDiscount)
      {
         System.out.println("Item Price: " + (itemPrice - itemDiscount));
      }
      else
         System.out.println("Item Price: " + (itemPrice));

      System.out.println("Stock Level: " + itemStock);

      if (itemDiscount == 0)
      {
         System.out.println("Current Discount: None\n");
      }
      else
      {
         DecimalFormat df = new DecimalFormat("#%");
         System.out.println("Current Discount: " + df.format(itemDiscount) +
                            "\n");
      }

   }
}