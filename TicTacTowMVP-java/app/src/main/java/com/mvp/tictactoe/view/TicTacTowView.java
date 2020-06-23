package com.mvp.tictactoe.view;

public interface TicTacTowView {
    void showWinner(String winningPlayerDisplayLabel);
    void clearWinnerDisplay();
    void clearButtons();
    void setButtonText(int row, int col, String text);
}
