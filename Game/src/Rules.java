public class Rules {
    private String[] moves;

    public Rules(String[] moves) {
        this.moves = moves;
    }

    public String determineWinner(String computerMove, String userMove) {
        int compIndex = getIndex(computerMove);
        int userIndex = getIndex(userMove);

        if (compIndex == userIndex) {
            return "It's a tie!";
        }

        int half = moves.length / 2;
        if ((userIndex >= compIndex + 1 && userIndex <= compIndex + half)
                || (userIndex <= compIndex - half)) {
            return "You win!";
        }

        return "Computer wins!";
    }

    private int getIndex(String move) {
        for (int i = 0; i < moves.length; i++) {
            if (moves[i].equals(move)) {
                return i + 1;
            }
        }
        return -1;
    }
}

