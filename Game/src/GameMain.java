import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GameMain {

    public static void main(String[] args) {
        if (args.length < 3 || args.length % 2 == 0 || hasDuplicates(args)) {
            System.out.println("Error: You must provide an odd number of different arguments (three arguments or more, but differents).");
            System.out.println("Example: rock Spock paper lizard scissors");
            return;
        }

        //Generate the cryptographically strong key
        KeyGenerator keyGen = new KeyGenerator();
        String secretKey = keyGen.generateKey();

        //Generate random computer movement
        MoveGenerator moveGen = new MoveGenerator(args);
        String computerMove = moveGen.generateMove();

        //Calculate HMAC and show it to the player
        HMACCalculator hmacGenerator = new HMACCalculator(secretKey, computerMove, "HmacSHA256");
        try {
            byte[] hmac = hmacGenerator.generateHmac();
            String hmacHex = hmacGenerator.bytesToHex(hmac);
            System.out.println("HMAC: " + hmacHex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    

       
        //Show menu of available moves
        GameTable gameTable = new GameTable(args);
        gameTable.displayTable();

        //Loop for the user to select their movement
        Scanner scanner = new Scanner(System.in);
        int userMove;
        do {
            System.out.print("Enter your move: ");
            if (scanner.hasNextInt()) {
                userMove = scanner.nextInt();
                if (userMove >= 0 && userMove <= args.length) {
                    break;
                }
                else {
                	System.out.println("Invalid input! Enter a number from the menu or '?', 'help' for game rules.");
                    gameTable.displayTable();
                }
                
                
            } else {
                String input = scanner.next();
                if (input.equals("?") || input.equals("help")) {
                	
                    gameTable.displayGameRules();
                }
            }
            
            
        } while (true);

        //Show user selection
        if (userMove == 0) {
            System.out.println("Exiting game...");
            return;
        }
        String userMoveStr = moveGen.getMoveName(userMove);

        //Determine the outcome of the game
        Rules rules = new Rules(args);
        String result = rules.determineWinner(computerMove, userMoveStr);

        //Show result and details
        System.out.println("Your move: " + userMoveStr);
        System.out.println("Computer move: " + computerMove);
        System.out.println(result);
        System.out.println("Secret key: " + secretKey);
    }
    
	    //Method to check for duplicates in the argument array
	    private static boolean hasDuplicates(String[] args) {
	        Set<String> set = new HashSet<>();
	        for (String arg : args) {
	            if (!set.add(arg)) {
	                return true; 	//A duplicate was found
	            }
	        }
	        return false; 			//No duplicates found
	    }
    
}



