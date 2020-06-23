package com.mvp.tictactoe.presenter;

import com.mvp.tictactoe.model.Board;
import com.mvp.tictactoe.model.Player;
import com.mvp.tictactoe.view.TicTacTowView;

public class TicTacTowPresenter implements Presenter {

    private TicTacTowView view;
    private Board board;

    public TicTacTowPresenter(TicTacTowView view) {
        this.view = view;
        this.board = new Board();
    }


    @Override
    public void onCreate() {
        board = new Board();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    public void onButtonSelected(int row, int col) {
        Player playerMoved = board.mark(row, col);

        if (playerMoved != null) {
            view.setButtonText(row, col, playerMoved.toString());
            if (board.getWinner() != null) {
                view.showWinner(playerMoved.toString());
            }
        }
    }

    public void onResetSelected() {
        view.clearWinnerDisplay();
        view.clearButtons();
        board.restart();
    }
}
