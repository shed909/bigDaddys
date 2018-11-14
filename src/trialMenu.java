
/**
 * Class trialMenu
 * 
 * A trial menu driver class to demonstrate the use of 
 * objects within the custom class "menuItem.java".
 * 
 */

import java.util.Scanner;

public class trialMenu
{
   // Define a method to get item selection for the ordering system
   public static int getMenuItemSelection(String itemNumber,
                                          menuItem menuArray[])
   {
      int itemSelection = -1;

      // Iterate through list checking if it contains the user input
      for (int i = 0; i < menuArray.length; i++)
      {
         // If itemNumber is valid, itemSelection = i
         // (the index of selected element in array)
         if (menuArray[i].getItemNumber().contains(itemNumber))
         {
            itemSelection = i;
         }
      }
      // If itemSelection is invalid, print error message and continue
      if (itemSelection < 0 || itemSelection > menuArray.length)
      {
         System.out.println("\nError - invalid item number!");
      }
      return itemSelection;
   }

   public static void main(String[] args)
   {

      // Create an array of 8 items and populate with menu items as per spec
      menuItem menuArray[] = new menuItem[8];

      menuArray[0] = new menuItem("1", "Hamburger", "A delicious flame-" +
                                  "grilled mystery-meat patty in a bun " +
                                  "w/ our special sauce",
                                  1.95);

      menuArray[1] = new menuItem("2", "Cheeseburger", "A flame-grilled " +
                                  "mystery-meat patty in a bun w/ a cheese" +
                                  "-like substance and onions",
                                  2.95);

      menuArray[2] = new menuItem("3", "Double Cheeseburger", "The " +
                                  "cheeseburger v2.0 – two mystery-" +
                                  "meat patties and double cheese",
                                  3.95);

      menuArray[3] = new menuItem("4", "Big Daddy Burger", "Two flame-" +
                                  "grilled 1/4 lb mystery meat patties " +
                                  "w/ all the trimmings in a big bun",
                                  10);

      menuArray[4] = new menuItem("5", "Mega Veggie Burger", "Premium " +
                                  "mystery-veggie patty w/ salad in a " +
                                  "wholegrain bun (vegetarian)",
                                  5.95);

      menuArray[5] = new menuItem("6", "French Fries", "Crispy golden " +
                                  "french fries – large serve",
                                  2.45);

      menuArray[6] = new menuItem("7", "Pepsi Max", "Maximum taste, zero " +
                                  "calories – large size only",
                                  2.95);

      menuArray[7] = new menuItem("8", "Ice Cream Sundae", "Soft-serve" +
                                  " ice cream smothered in hot chocolate " +
                                  "or caramel fudge sauce",
                                  1.95);

      // Print welcome message
      System.out.println("Welcome to Big Daddy's Burger Shoppe!");
      System.out.println("-------------------------------------\n");

      // Create scanner object
      Scanner inputAnswer = new Scanner(System.in);

      // Ask user to input today's discount for the Big Daddy Burger
      System.out.println("Enter a today's discount for the " +
                         "Big Daddy Burger: ");
      // If valid, apply discount
      if (menuArray[3].setDiscount((inputAnswer.nextDouble() / 100)))
      {
         System.out.printf("Manager's special - Big Daddy Burger is $%.2f " +
                  "for today only!\n\n", menuArray[3].getItemPrice());
      }
      // If not valid, output an error and continue at normal price
      else
      {
         System.out.println("\nError - invalid discount percentage! " +
                            "Big Daddy Burger is normal price today!\n");
      }

      // Prompt user to hit enter for print of menu
      System.out.println("Hit enter to view Big Daddy's Burger Shoppe " +
                         "menu: ");
      inputAnswer.nextLine();
      inputAnswer.nextLine(); // Handle scanner bug

      // Print menu header
      System.out.println("Item #    Name                Price");
      System.out.println("------    ----                -----\n");
      
      // Call printMenuLine on each element of array to construct menu
      for (int i = 0; i < menuArray.length; i++)
      {
         menuArray[i].printMenuLine();
      }
      
      // Indicate beginning of order process to user
      System.out.println("Placing new order:\n");

      // Initialize variables for order system
      String itemNumber = null;
      int restockQuantity;
      int itemSelection;
      int desiredQuantity = 0;
      double price = 0;
      String orderSummary = "";
      double totalPrice = 0;

      // Begin order system loop
      do
      {
         System.out.println("\nEnter an item number to add to order " +
                            "[hit enter to finish]: ");
         itemNumber = inputAnswer.nextLine();
         itemSelection = getMenuItemSelection(itemNumber, menuArray);

         if (itemSelection >= 0 && !itemNumber.isEmpty())
         {
            // If itemSelection is valid and itemNumber is not empty
            // call orderItem and get price
            System.out.println("\nEnter quantity to add to order: ");
            desiredQuantity = inputAnswer.nextInt();
            inputAnswer.nextLine(); // Handle scanner bug
            price = menuArray[itemSelection].orderItem(desiredQuantity);

            if (Double.isNaN(price))
            {
               // If price doesn't exist, this indicates no stock
               // Call "restock" on this object before continuing order
               System.out.println("\nInsufficient stock on hand!");
               System.out.println("\nEnter amount of stock retrieved " +
                                  "from cold storage: ");
               restockQuantity = inputAnswer.nextInt();
               inputAnswer.nextLine(); // Handle scanner bug
               menuArray[itemSelection].restock(restockQuantity);
               System.out.println("\nRestocking successful!");

            }
            else if (!Double.isNaN(price))
            {
               // If a price exists, display confirmation of order
               System.out.println("\n" + menuArray[itemSelection].getItemName() +
                                  " x " + desiredQuantity + " added to " +
                                  "order.\n");
               // Store new line for this order for use in order summary
               orderSummary += "*  " + menuArray[itemSelection].getItemName() +
                               " x " + Integer.toString(desiredQuantity) + 
                               "\n";

               totalPrice = totalPrice + price;

            }
         }

      } while (!itemNumber.isEmpty());

      // Print receipt
      System.out.println("Order Summary: \n");
      System.out.println(orderSummary + "\n");
      System.out.printf("Total order price: $%.2f\n\n", totalPrice);

      // Ask user to hit enter if they wish to print Stock summary
      System.out.println("Hit enter to view stock level summary: ");
      inputAnswer.nextLine();

      // Iterate through array and call printDetails on each item
      for (int i = 0; i < menuArray.length; i++)
      {
         menuArray[i].printDetails();
      }

      // Close scanner
      inputAnswer.close();

   }
}