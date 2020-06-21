/*
Dominic Lutz
10/28/2019
Prof. Hira Herrington
ISYS 316 - 001

    This program uses the Account class to make accounts which are read in from
a file. The user can use the interface to enter their account number and perform
operations on it.

Inputs and Outputs
inputs - various button presses from the user
inputs - number amounts from the user
outputs - confirmation messages based on calculations on account balance
output - balance of user's account

Variables:
inputList - ArrayList<String> - holds raw input from file
accounts - ArrayList<Account> - holds accounts created from file
currentAccount - Account - holds the current user's account
operation - String - dictates which screen is showing at the moment

handleWithdrawButton - displays the withdraw screen
handleDepositButton - displays the deposit screen
handleBalanceButton - displays the balance screen, showing how much money is in
    the current user's account
handleBackButton - returns to the previous screen
handleNumberButton - writes numbers into the bottom label
handleClearButton - clears the bottom label
handleBackspaceButton - backspaces the bottom label
handleEnterButton - performs actions based on which screen is showing
handleExitButton - returns to the beginning screen, no matter where the user is
 */
package dominiclutzassignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author d_lut
 */
public class FXMLDocumentController implements Initializable {
    //Initialize essential variables for processing
    ArrayList<String> inputList = new ArrayList<>();
    ArrayList<Account> accounts = new ArrayList<>();
    Account currentAccount;
    String operation = "account";

    private Label label;
    @FXML
    private Button btWithdraw;
    @FXML
    private Button btDeposit;
    @FXML
    private Button btBack;
    @FXML
    private Button btSeven;
    @FXML
    private Button btEight;
    @FXML
    private Button btNine;
    @FXML
    private Button btFour;
    @FXML
    private Button btFive;
    @FXML
    private Button btSix;
    @FXML
    private Button btOne;
    @FXML
    private Button btTwo;
    @FXML
    private Button btThree;
    @FXML
    private Button btZero;
    @FXML
    private Button btClear;
    @FXML
    private Button btBackSpace;
    @FXML
    private Button btEnter;
    @FXML
    private Label lblTop;
    @FXML
    private Label lblMid;
    @FXML
    private Label lblBot;
    @FXML
    private Button btBalance;
    @FXML
    private Button btExit;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Create File objects to point towards input and output files
        File inputFile = new File("AccountDataIn.txt");

        //try-catch block for file input
        try {
            Scanner input = new Scanner(inputFile);

            while (input.hasNext()) {
                inputList.add(input.next());
            }
            
            //Create String array to hold each line of input as it is processed
            String[] temp;
            
            //For loop to process each line of input
            for (int i = 0; i < inputList.size(); i++) {
                //Split the current input line into process array
                temp = inputList.get(i).split(",");
                Account account = new Account(Integer.parseInt(temp[1]), Double.parseDouble(temp[2]));
                accounts.add(account);
            }
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Something went wrong");
        }
        catch (FileNotFoundException e) {
            System.out.println("Input file not found");
            System.exit(1);
        }
    }   

    @FXML
    private void handleWithdrawButton(ActionEvent event) {
        //withdraw button disabled at beginning screen
        if (!(operation.equals("account"))) {
            lblTop.setText("Welcome to Lutz Bank!");
            lblMid.setText("How much would you like to withdraw?");
            lblBot.setText("");
            operation = "withdraw";
        }
    }

    @FXML
    private void handleDepositButton(ActionEvent event) {
        //deposit button disabled at beginning screen
        if (!(operation.equals("account"))) {
            lblTop.setText("Welcome to Lutz Bank!");
            lblMid.setText("How much would you like to deposit?");
            lblBot.setText("");
            operation = "deposit";
        }
    }

    @FXML
    private void handleBalanceButton(ActionEvent event) {
        //balance button disabled at beginning screen
        if (!(operation.equals("account"))) {
            lblTop.setText("Welcome to Lutz Bank!");
            lblMid.setText("Your current balance is:");
            lblBot.setText(currentAccount.getBalance() + "");
            operation = "balance";
        }
        
    }

    @FXML
    private void handleBackButton(ActionEvent event) {
        //logic block to set the labels according to which screen you're going back to
        if (operation.equals("withdraw") || operation.equals("deposit") || operation.equals("balance")) {
            lblMid.setText("What would you like to do today?");
            lblBot.setText("");
            operation = "select";
        }
        else if (operation.equals("select")) {
            lblTop.setText("Welcome to Lutz Bank!");
            lblMid.setText("Please enter your account id:");
            lblBot.setText("");
            currentAccount = null;
            operation = "account";
        }
    }

    @FXML
    private void handleNumberButton(ActionEvent event) {
        //Number buttons disabled if screen is balance screen or select screen
        if (!(operation.equals("balance")) && !(operation.equals("select"))) {
            String[] temp;
            //splits the event source toString in order to grab the button text
            temp = event.getSource().toString().split("'");
            lblBot.setText(lblBot.getText() + temp[1]);  
        }
         
    }

    @FXML
    private void handleClearButton(ActionEvent event) {
        //clears numbers from bottom label
        lblBot.setText("");
    }

    @FXML
    private void handleBackspaceButton(ActionEvent event) {
        //splits bottom label into array of chars, then displays the same array minus the last char
        String[] temp = lblBot.getText().split("");
        String output = "";
        for (int i = 0; i < temp.length - 1; i++) {
            output = (output + temp[i] + "");
        }
        lblBot.setText(output);
    }

    @FXML
    private void handleEnterButton(ActionEvent event) {
        
        //switch statement to tell the enter button what to do depending on which screen is showing
        switch (operation) {
            case "account":
                Boolean found = false;
                //search accounts to find entered account number
                for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i).getId() == Double.parseDouble(lblBot.getText())) {
                        currentAccount = accounts.get(i);
                        found = true;
                        break;
                    }
                }
                //if found, go to next screen, holding the current account
                if (found) {
                    lblMid.setText("What would you like to do today?");
                    operation = "select";
                }
                //if not found, stay on account screen
                else {
                    lblMid.setText(lblMid.getText() + " Sorry, that account was not found");
                }
                //either way, clear bottom label
                lblBot.setText("");
                break;
            case "withdraw":
                //if enough money, complete transaction
                if (currentAccount.getBalance() >= Double.parseDouble(lblBot.getText())) {
                    currentAccount.withdraw(Double.parseDouble(lblBot.getText()));
                    lblTop.setText("Transaction successful");
                    lblMid.setText("What would you like to do today?");
                }
                else {
                    lblTop.setText("Insufficient funds");
                    lblMid.setText("Sorry, the transaction could not be completed");
                }
                //return to previous screen
                operation = "select";
                lblBot.setText("");
                break;
            case "deposit":
                //deposit entered amount, then return to select screen
                currentAccount.deposit(Double.parseDouble(lblBot.getText()));
                lblTop.setText("Transaction successful");
                lblMid.setText("What would you like to do today?");
                operation = "select";
                lblBot.setText("");
                break;
            default:
                break;
        }
    }

    @FXML
    private void handleExitButton(ActionEvent event) {
        //no matter where you are, returns you to beginning screen
        lblTop.setText("Welcome to Lutz Bank!");
        lblMid.setText("Please enter your account id:");
        lblBot.setText("");
        currentAccount = null;
        operation = "account";
    }
}