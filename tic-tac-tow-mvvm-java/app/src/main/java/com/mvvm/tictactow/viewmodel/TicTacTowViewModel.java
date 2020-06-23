package com.mvvm.tictactow.viewmodel;


import androidx.databinding.ObservableArrayMap;
import androidx.databinding.ObservableField;

import com.mvvm.tictactow.model.Board;
import com.mvvm.tictactow.model.Player;

public class TicTacTowViewModel implements ViewModel {

    private Board board;

    public final ObservableArrayMap<String, String> cells = new ObservableArrayMap<>();
    public final ObservableField<String> winner = new ObservableField<>();

    public TicTacTowViewModel() {
        this.board = new Board();
    }

    @Override
    public void onCreate() {

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

    public void onResetSelected() {
        board.restart();
        winner.set(null);
        cells.clear();
    }

    public void onClickedCellAt(int row, int col) {
        Player playerMoved = board.mark(row, col);
        cells.put("" + row + col, playerMoved == null ? null : playerMoved.toString());
        winner.set(board.getWinner() == null ? null : board.getWinner().toString());
    }
}
