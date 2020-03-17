package com.company.app;

import com.company.ammunition.Helmet;
import com.company.ammunition.Jacket;
import com.company.driver.Motorcyclist;
import com.company.xml.XMLParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class ConsoleApplication {

    private Motorcyclist motorcyclist;

    public ConsoleApplication() {
        motorcyclist = new Motorcyclist();
    }

    public void start() throws IOException, SAXException, ParserConfigurationException {
        motorcyclist.setAmmunitions(XMLParser.parseXMLFile("C:\\Users\\Lenovo\\IdeaProjects\\Second task\\src\\Ammunition.xml"));
        boolean working = true;
        Scanner in = new Scanner(System.in);
        String choice = null;
        while (working) {
            System.out.println("Motorcyclist application");
            System.out.println("Type option:");
            System.out.println("helmet - to change helmet");
            System.out.println("jacket - to change jacket");
            System.out.println("print - to print motorcyclist ammunition");
            System.out.println("price - to show price of all ammunition");
            System.out.println("sort - to sort all ammunition by weight");
            System.out.println("find - to find ammunition in required range");
            System.out.println("any other string - to exit");
            System.out.print("Enter option: ");
            choice = in.nextLine();

            switch (choice) {
                case "helmet": {
                    System.out.print("Enter manufacturer: ");
                    String manufacturer = in.nextLine();
                    System.out.print("Enter price: ");
                    double price = in.nextDouble();
                    System.out.print("Enter weight: ");
                    double weight = in.nextDouble();
                    System.out.print("Enter radius: ");
                    int radius = in.nextInt();
                    motorcyclist.getAmmunitions().set(0, new Helmet(manufacturer, price, weight, radius));
                    System.out.println("Helmet successfully changed!");
                    break;
                }
                case "jacket": {
                    System.out.print("Enter manufacturer: ");
                    String manufacturer = in.nextLine();
                    System.out.print("Enter price: ");
                    double price = in.nextDouble();
                    System.out.print("Enter weight: ");
                    double weight = in.nextDouble();
                    System.out.print("Enter leather percentage: ");
                    int leatherPercentage = in.nextInt();
                    motorcyclist.getAmmunitions().set(1, new Jacket(manufacturer, price, weight, leatherPercentage));
                    System.out.println("Jacket successfully changed!");
                    break;
                }
                case "print": {
                    if (motorcyclist.getAmmunitions().isEmpty()) {
                        System.out.println("There are no any ammunition on motorcyclist!");
                        break;
                    }
                    for (var ammunition : motorcyclist.getAmmunitions()) {
                        System.out.println(ammunition);
                    }
                    break;
                }
                case "price": {
                    System.out.println("Price of all ammunition is " + motorcyclist.getPrice());
                    break;
                }
                case "sort": {
                    motorcyclist.sortAmmunition();
                    break;
                }
                case "find": {
                    System.out.println("Enter two borders for price: ");
                    double firstPrice = in.nextDouble(), secondPrice = in.nextDouble();
                    if(firstPrice < 0 || secondPrice < 0) {
                        throw new IllegalArgumentException("Price lower than 0 are unacceptable!");
                    }
                    var arrayOfRequiredAmmunition = motorcyclist.findInRange(firstPrice, secondPrice);
                    if (arrayOfRequiredAmmunition.isEmpty()) {
                        System.out.println("There are no any ammunition in required range!");
                        break;
                    }
                    for (var ammunition : arrayOfRequiredAmmunition) {
                        System.out.println(ammunition);
                    }
                    break;
                }
                case "":
                    break;
                default: {
                    working = false;
                }
            }
            System.out.println("");
        }
    }

}
