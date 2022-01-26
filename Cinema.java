package com;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Cinema {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int rows = 0, columns = 0;
		boolean notValidSeatsNumber = true;
		do {
			try {
				System.out.println("Enter the number of rows:");
		        rows = scanner.nextInt() + 1;
				System.out.println("Enter the number of seats in each row:");
		        columns = scanner.nextInt() + 1;
		        notValidSeatsNumber = false;
			} catch(InputMismatchException err) {
				System.out.println("Please enter a valid number");
				scanner = new Scanner(System.in);
			}
		} while(notValidSeatsNumber);
        String[][] seats = new String[rows][columns];
        seats[0][0] = "";
        for(int i = 0; i < seats.length ; i++){
            for(int j = 0; j < seats[0].length ; j++) {
                if(i == 0) {
                    if(j == 0){
                        continue;
                    } else {
                        Integer column = new Integer(j);
                        seats[i][j] = column.toString();
                        continue;
                    }
                }
                if(j == 0) {
                	if(i == 0) {
                		continue;
                	} else {
                        Integer column = new Integer(i);
                        seats[i][j] = column.toString();
                        continue;
                	}
                }
                seats[i][j] = "S";
            }
        }
	    for(int i = 0; i < seats.length ; i++){
	        for(int j = 0; j < seats[0].length ; j++) {
	            if(i == 0) {
	                if(j == 0){
	                    continue;
	                } else {
	                    Integer column = new Integer(j);
	                    seats[i][j] = column.toString();
	                    continue;
	                }
	            }
	            if(j == 0) {
	            	if(i == 0) {
	            		continue;
	            	} else {
	                    Integer column = new Integer(i);
	                    seats[i][j] = column.toString();
	                    continue;
	            	}
	            }
	            seats[i][j] = "S";
	        }
	    }
	    boolean buyingBoolean = true;
	    int purchasedTickets = 0;
        int seatRows = rows - 1;
        int seatColumns = columns - 1;
        int currentIncome = 0;
	    do {
	    	try {
	    		System.out.println(
		    			"1. Show the seats\n" +
		    			"2. Buy a ticket\n" +
		    			"3. Statistics\n" +
		    			"0. Exit");
		    	int choice = scanner.nextInt();
		        switch(choice) {
		        case 0:
		        	buyingBoolean = false;
		        	break;
		        case 1:
		        	System.out.println("\nCinema:");
		            for(String[] arr: seats) {
		            	for(String element: arr) {
		            		System.out.print(element + " ");
		            	}
		            	System.out.println();
		            }
		            System.out.println();
		        	break;
		        case 2:
		        	int ticketPrice = 0;
		        	boolean purchasedBoolean = false;
		        	int seatRow = 0;
		        	int seatColumn = 0;
		        	while(purchasedBoolean == false) {
			        	System.out.println("\nEnter a row number:");
			        	seatRow = scanner.nextInt();
			        	System.out.println("\nEnter a seat number in that row:");
			        	try {
				        	seatColumn = scanner.nextInt();
			        		if(seats[seatRow][seatColumn] == "B") {
			        			System.out.println("That ticket has already been purchased!");
			        		} else if(seats[seatRow][seatColumn] == "S") {
			        			purchasedBoolean = true;
			        		}
			        	} catch(ArrayIndexOutOfBoundsException err) {
			        		System.out.println("Wrong input!");
			        	}
		        	}
		        	if((rows-1)*(columns-1) <= 60) {
		        		ticketPrice = 10;
		        	} else {
		        		if(seatRow < rows/2){
		        			ticketPrice = 10;
		        		} else {
		        			ticketPrice = 8;
		        		}
		        	}
		        	System.out.println("Ticket price: $" + ticketPrice);
		        	seats[seatRow][seatColumn] = "B";
		        	purchasedTickets ++;
		        	currentIncome += ticketPrice;
		        	break;
		        case 3:
		        	double soldSeatsPercentage = ((double)purchasedTickets/(seatRows*seatColumns))*100;
		            int totalProfit = 0;
		    		if(seatRows*seatColumns < 60) {
		    			totalProfit = seatRows*seatColumns*10;
		    		} else {
		    			if(seatRows%2==0){
		    				totalProfit = (seatRows/2)*seatColumns*10+(seatRows/2)*seatColumns*8;
		    			} else {
		    				totalProfit = (seatRows/2)*seatColumns*10+((seatRows/2)+1)*seatColumns*8;
		    			}
		    		}
		    		System.out.println("Number of purchased tickets: " + purchasedTickets);
		    		System.out.println(String.format("Percentage: %.2f", soldSeatsPercentage) + "%");
		    		System.out.println("Current income: $" + currentIncome);
		    		System.out.println("Total income: $" + totalProfit);
		        	break;
		        }
	    	} catch(InputMismatchException err) {
	    		System.out.println("Please enter a valid input");
	    		scanner = new Scanner(System.in);
	    	}
	    } while(buyingBoolean);
		scanner.close();
	}

}
