/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car_validation;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @date 20.10.2021
 * @version 1.0
 * @lecturer Ken Healy
 * @module Programming: Object-Oriented Approach.
 * @author Wandwilson Almeida Da Silva
 * @Student Number: 2021230
 * @Assessment: File Access CA
 * @Date of Submission: 06/11/2021 
 */
public class Car_validation {

    /**
     * @param args the command line arguments
     * 
     *
     * Strategy:
     *
     * 1) create a variable to get the name of file. 
     * 2) Start the variables. 
     * 3) create a variables that will read the file. 
     * 4) start the loop to check all the data on the file. 
     * 5) separete all the data by the variable car, licence_plate an Type_of_car. 
     * 6) check if there is a space between the car brand and car model. 
     * 7) create a variable that will get the information if the there is a space between the car brand and car model.
     * 8) check if the car brand is text only and the car model is text and number. 
     * 9) convert the string to Integer and create a variable that will get this Integer. 
     * 10) check if the limit of passenger is more or equal 1 or less or equal 8. 
     * 11) if the limit is correct check which type of car is the car. 
     * 12) define a type of licence plate must be like( 201DL3456 or 221D2308). 
     * 13) check if the 5th character is metcher with the letter or number. 
     * 14) write all data that is valid in a new file called status.txt.
     *
     */
    public static void main(String[] args) {

        String file = "vehicle.txt"; // this is the file name.
        String new_file = "status.txt"; //this is the file name where the data will be save if the car is valid.
        String car_brand, car, car_model = "", licence_plate, type_of_car = ""; //All String was used to separete car_brand from car_model, plate and type_of_car.

        // here will try to do all code 
        try {

            Scanner myfile = new Scanner(new FileReader(file)); //this will read the data from the vehicle.txt.

            BufferedWriter myWriter = new BufferedWriter(new FileWriter(new_file, true)); //this will write the valid data in a new file called status.txt.

            while (myfile.hasNextLine()) { //here starts the looping to check if there any information on the myfile.

                car = myfile.nextLine(); //this valible get the first information on the myfile.

                int locate_space = car.indexOf(" ");  //here start to check if have space between car_brand and the car_model.

                if (locate_space == -1) { //this part check if there one space, if dont have any spaces they will show an error.

                    //If the code not find any space the code will show a Error menssenger.
                    System.out.println("ERROR -- I can't find any space \ncar_brand and car_model must be one space between than.\n ");

                } else {
                    //if the code find a space the information will separate the car_model from the car_brand.
                    car_brand = car.substring(0, locate_space); //car_brand get the car brand like Volkswagen.
                    car_model = car.substring(locate_space + 1); //car_model get the car model like Passat.

                    if (!car_brand.matches("[A-Za-z]+")) {
                        //This part the code will check if the car brand is text only.
                        //The code can't accept the brand with numbers.
                        System.out.println("ERROR -- this " + car_brand + " the Car brand must be text only\n.");
                    } else if (!car_model.trim().matches("[A-Za-z0-9]+")) {
                        //This part the code will check if the car model is text with number or just text.
                        System.out.println("ERROR -- this " + car_model + " the Car model can be text with number\n.");
                    }

                }
                //Here start to check number of passengers in each car information.
                //the number passengers must be a int number.
                //the max of passenger is 8 and the minumum is 1.
                int passengers = Integer.parseInt(myfile.nextLine());
                //System.out.println(passengers);
                if (passengers < 1 || passengers > 8) {
                    //if the passenger on the file is bellow the 1 or higger than 8 the system will show an Error..
                    System.out.println("Error... The minimun of passengers is 1! \nAnd The limit of passenger is 8 max!\n");

                    //if the passenger is valid the code will check what type of car they are. 
                    //in each section will save a type of the car in the valible type_of_car.
                } else if (passengers == 1) {
                    type_of_car = "Racing Car";

                } else if (passengers == 2) {
                    type_of_car = "Sports car";

                } else if (passengers >= 3 && passengers <= 5) {
                    type_of_car = "Saloon";

                } else {
                    type_of_car = "SUV";
                }

                licence_plate = myfile.nextLine(); //this valible will get the licence plate from the file.

                //here will start to check the licence plate.
                if (!licence_plate.matches("[2-9]{1}[0-9]{2}[a-zA-Z0-9]{2}[0-9]{1,4}")) {

                    //in this part will check if the plate is the same of this model (201DL2378) or (221D23678) if the plate is not metche the code will show an Error.
                    System.out.println(licence_plate + " Sorry this licence plate is invalid.");
                    System.out.println("The licence plate must start with the year 20 or higger.\nThe licence plate must be 6 or 9 characters, 3 numbers in the first part. \n1 or 2 letters in the middle and followed by numbers in the end.\nexample(202DL3454) or (221D23456).\n");

                } else {

                    //if the licence plate is valid.
                    //Here the code will check if the 5th character is letter or number.
                    if (licence_plate.substring(4, 5).matches("[a-zA-Z]+")) { // this part will check if the 5th character is a letter.

                        //if the 5th character is letter.
                        //the code will check if the 5th character is bigger than 100 and check if the licence plate is smaller than 30000..
                        //but in this parte the code will check again if all information is correct like passenger and the space on the date.
                        if (Integer.parseInt(licence_plate.substring(5)) >= 100 && Integer.parseInt(licence_plate.substring(5)) <= 30000 && passengers >= 1 && passengers <= 8 && locate_space != -1) {
                            // if all information is correct the data will be save in another file called status.txt.
                            // if the licence plate is bigger than 100 and the lower than 30000 the code start to writer the date in a new file called status.txt.
                            myWriter.write(licence_plate + "--" + car_model.trim() + "\n" + type_of_car);
                            myWriter.newLine(); //this code is to give a space to a data from another data.
                            myWriter.newLine();

                        }

                    } else if (licence_plate.substring(4, 5).matches("[0-9]+")) {  // this part will check if the 5 character is a number.

                        //if the 5th character is a number.
                        //the code will check if the 5th character is bigger than 100 and check if the licence plate is smaller than 30000.
                        //but in this parte the code will check again if all information is correct like passenger and the space on the date.
                        if (Integer.parseInt(licence_plate.substring(4)) >= 100 && Integer.parseInt(licence_plate.substring(4)) <= 30000 && passengers >= 1 && passengers <= 8 && locate_space != -1) {
                            // if all information is correct the data will be save in another file called status.txt.
                            //if the licence plate is bigger than 100 and the lower than 30000 the code start to writer the data in a new file called status.txt
                            myWriter.write(licence_plate + "--" + car_model.trim() + "\n" + type_of_car);
                            myWriter.newLine(); //this code is to give a space to a data from another data.
                            myWriter.newLine();

                        } else {

                            // if the licence plate is invalid the code will show an Error.
                            System.out.println("This licence plate " + licence_plate + " is invalid. \nThe final numbers of plate must be between 100 to 30000.\n");
                        }
                    }
                }
            }
            myWriter.close(); //this code will close the myWriter and save the data on the status.txt

            // when the code finish will output a mensager to user inform that the data was save in another file. 
            System.out.println("All valid data is saved in status.txt");
        } catch (Exception e) {

            //if the code can't find any file called vehicle.txt will output this Error.
            System.out.println("Sorry I didn't find any file called 'Vehicle.txt'");

        }

    }
}
