package com.techelevator.tenmo;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.services.*;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);
    private final UserServiceDto userService = new UserServiceDto(API_BASE_URL);
    //added on 3/8
    private AccountService accountService= new AccountServiceDto(API_BASE_URL);;
    private AuthenticatedUser currentUser;


    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

	private void viewCurrentBalance() {
		// TODO Auto-generated method stub
        Account balance = accountService.getBalance(currentUser);
        System.out.println("Your current account balance is:  $" + balance.getBalance());

    }

	private void viewTransferHistory() {
        // TODO Auto-generated method stub
        Transfer[] transfers = null;

        transfers = accountService.list_transaction(currentUser);
        System.out.println("------------------------------------------ ");
        System.out.println("Transfers ");
        System.out.println("ID           From/To                Amount ");
        System.out.println("------------------------------------------ ");
        for (int i = 0; i < transfers.length ; i++) {
                    //get from and to names and refactor the string output to have consistent spacing
                    System.out.println(transfers[i].getTransfer_id() + " ACC from..... " + transfers[i].getAccount_from() +
                    " Account to..... " + transfers[i].getAccount_to() + " Amount ..." + transfers[i].getAmount());
        }
        System.out.println("Please enter transfer ID to view details (0 to cancel): ");
        //get user input and if user input == 0, quit
    }

	private void viewPendingRequests() {
		// TODO Auto-generated method stub
		
	}

	private void sendBucks() {
		// TODO Auto-generated method stub
        //not complete ?????????????
        User[] users= null;
        System.out.println("_______________________");
        System.out.println("USERS");
        System.out.println("ID"+"                "+"Name");
        System.out.println("________________________");
        users=userService.getAllUsers(currentUser);
        for(User user:users){
            System.out.println(user.getId()+"              "+user.getUsername());
        }


        System.out.println("________________________");

        Transfer transfer= new Transfer( 5,1,2001,2003,230.00);
//        int x= consoleService.promptForMenuSelection("Enter the amount to transfer: ");
//        transfer.setAmount((double)x);
        Account account;
	     account= accountService.approveTransferFunds(currentUser,transfer);
        System.out.println(account.getAccount_id() +"   "+ account.getBalance()+">>>>>>>>>");
	}

	private void requestBucks() {
		// TODO Auto-generated method stub
		
	}

}
