
/*
 * Creates a ledger that describes how much each player
 * pays out.
 */

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    
    public static Double checkIfBalance(ArrayList<NameDouble> winners, ArrayList<NameDouble> losers) {
        Double sumWinners = 0.0;
        Double sumLosers = 0.0;
        
        for (NameDouble i : winners) {
            sumWinners+= i.getVal();
        }
        
        for (NameDouble i : losers) {
            sumLosers+= i.getVal();
        }
        
        Double result = sumWinners + sumLosers;
        System.out.println("The sum of total wins and losses is  " + result);
        return result;
      
    }
    
    public static void main(String[] args) throws Exception {
        
        
        // Instantiates the excel reader that takes in data from the given excel workbook
        ExcelUtils utils = new ExcelUtils("./data/CovidPokerPayoutSheet.xlsx", "Sheet7");
        
        ArrayList<NameDouble> winners = new ArrayList<NameDouble>();        
        ArrayList<NameDouble> losers = new ArrayList<NameDouble>();
           
        for (int i = 1; i < utils.getRowCount(); i++) {
            
            String name = (String) utils.getCellDataInt(i, 0);
            String temp = (String) utils.getCellDataInt(i, 1);
            Double val = Double.parseDouble(temp);
            
            if (val == 0) {
                continue;
            } 
            NameDouble entry = new NameDouble(name, val);
            
            if (val < 0) {
                losers.add(entry);
            } else if (val > 0) {
                winners.add(entry);
            }
        }
        
        // Checks to see if the profit and losses add up to 0
        // This will let you know if there are any errors in the 
        // excel sheet data
        
        Double res = checkIfBalance(winners, losers);
        if (res == 0 || res < 0) {
            System.out.println("Perfect ledger or more losses - "
                    + "no indexOutofBounds");
        } else if (res > 0) {
            System.out.println("More wins than losses"
                    + "may result in an indexOutofBounds");
        }
        
        int numWinners = winners.size();
        int numLosers = losers.size();
        System.out.println(numWinners + " " + numLosers);
        
        int w = 0;
        int l = 0;
        
        // Iterates through the winners and losers and determines
        // which losers pay which winner 
        
        while (numWinners > 0) {
            NameDouble win = winners.get(w);
            NameDouble lose = losers.get(l);
            Double winNum = win.getVal();
            Double loseNum = lose.getVal();
            Double remainder = winNum + loseNum;
            Double loseNumPos = loseNum * -1;
            
            if (remainder > 0) {
                win.setVal(remainder);
                System.out.println(lose.getName() + " pays " + win.getName() + " " + loseNumPos);
                l++;
                numLosers--;
                
            } else if (remainder == 0) {
                System.out.println(lose.getName() + " pays " + win.getName() + " " + loseNumPos);
                w++;
                l++;
                numWinners--;
                numLosers--;
                    
            } else if (remainder < 0) {
                System.out.println(lose.getName() + " pays " + win.getName() + " " + winNum);
                lose.setVal(remainder);
                w++;
                numWinners--;  
                
            }
            
        }
   
    }

}

