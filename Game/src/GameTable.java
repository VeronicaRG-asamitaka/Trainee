import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;

public class GameTable {
    private String[] moves;

    public GameTable(String[] moves) {
        this.moves = moves;
    }

    public void displayTable() {
        System.out.println("Available moves:");
        for (int i = 0; i < moves.length; i++) {
            System.out.println((i + 1) + " - " + moves[i]);
        }
        System.out.println("0 - exit");
        System.out.println("? - help");
    }

    public void displayGameRules() {
        //Set up the game rules table
        JFrame frame = new JFrame("Game Rules");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Data for the table
        String[] columnNames = new String[moves.length + 1];
        columnNames[0] = "v PC\\Usuario >";
        System.arraycopy(moves, 0, columnNames, 1, moves.length);

        Object[][] data = new Object[moves.length][moves.length + 1];
        for (int i = 0; i < moves.length; i++) {
            data[i][0] = moves[i];
        }

        // Calculate results to fill the table
        for (int i = 0; i < moves.length; i++) {
            for (int j = 0; j < moves.length; j++) {
                String result = determineResult(moves[i], moves[j]);
                data[i][j + 1] = result;
            }
        }

        //Create the table model
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);

        //Align cells to the center
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        //Set background color and font for header
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.YELLOW);
        header.setForeground(Color.BLACK);
        header.setFont(header.getFont().deriveFont(Font.BOLD));

        //Add the table to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        //Show window
        frame.setSize(600, 400);
        frame.setVisible(true);
    }

    private String determineResult(String move1, String move2) {
        Rules rules = new Rules(moves);
        String result = rules.determineWinner(move1, move2);
        return result;
    }
}





