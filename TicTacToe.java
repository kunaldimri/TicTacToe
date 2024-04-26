import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame {
    private JPanel boardPanel;
    private JButton[][] buttons;
    private JLabel statusLabel;
    private boolean isXTurn;
    private boolean gameEnded;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        boardPanel = new JPanel(new GridLayout(3, 3));
        buttons = new JButton[3][3];
        isXTurn = true;
        gameEnded = false;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                JButton button = new JButton();
                button.setFont(new Font("Arial", Font.PLAIN, 40));
                button.addActionListener(new ButtonClickListener(row, col));
                buttons[row][col] = button;
                boardPanel.add(button);
            }
        }

        statusLabel = new JLabel("X's turn");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(boardPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }

    private void checkWinner() {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (buttons[row][0].getText().equals(buttons[row][1].getText())
                    && buttons[row][0].getText().equals(buttons[row][2].getText())
                    && !buttons[row][0].getText().isEmpty()) {
                endGame(buttons[row][0].getText() + " wins!");
                return;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (buttons[0][col].getText().equals(buttons[1][col].getText())
                    && buttons[0][col].getText().equals(buttons[2][col].getText())
                    && !buttons[0][col].getText().isEmpty()) {
                endGame(buttons[0][col].getText() + " wins!");
                return;
            }
        }

        // Check diagonals
        if (buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[0][0].getText().equals(buttons[2][2].getText())
                && !buttons[0][0].getText().isEmpty()) {
            endGame(buttons[0][0].getText() + " wins!");
            return;
        }

        if (buttons[0][2].getText().equals(buttons[1][1].getText())
                && buttons[0][2].getText().equals(buttons[2][0].getText())
                && !buttons[0][2].getText().isEmpty()) {
            endGame(buttons[0][2].getText() + " wins!");
            return;
        }

        // Check for draw
        boolean isBoardFull = true;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    isBoardFull = false;
                    break;
                }
            }
        }
        if (isBoardFull) {
            endGame("It's a draw!");
        }
    }

    private void endGame(String message) {
        statusLabel.setText(message);
        gameEnded = true;
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!buttons[row][col].getText().isEmpty() || gameEnded) {
                return;
            }

            if (isXTurn) {
                buttons[row][col].setText("X");
                statusLabel.setText("O's turn");
            } else {
                buttons[row][col].setText("O");
                statusLabel.setText("X's turn");
            }

            isXTurn = !isXTurn;
            checkWinner();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TicTacToe().setVisible(true);
        });
    }
}
