import java.util.Random;

public class MoveGenerator {
    private String[] moves;

    public MoveGenerator(String[] moves) {
        this.moves = moves;
    }

    public String generateMove() {
        Random random = new Random();
        int index = random.nextInt(moves.length);
               
        return moves[index];
    }

    public String getMoveName(int moveIndex) {
        if (moveIndex >= 1 && moveIndex <= moves.length) {
            return moves[moveIndex - 1];
        }
        return null;
    }
}

