package com.mvp.tictactoe.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

import com.mvp.tictactoe.R;
import com.mvp.tictactoe.presenter.TicTacTowPresenter;

public class TicTacToeActivity extends AppCompatActivity implements TicTacTowView {

    private static String TAG = TicTacToeActivity.class.getName();

    private ViewGroup buttonGrid;
    private View winnerPlayerViewGroup;
    private TextView winnerPlayerLabel;

    private TicTacTowPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initPresenter();
    }

    private void initView() {
        winnerPlayerLabel = findViewById(R.id.winnerPlayerLabel);
        winnerPlayerViewGroup = findViewById(R.id.winnerPlayerViewGroup);
        buttonGrid = findViewById(R.id.buttonGrid);
    }

    private void initPresenter() {
        presenter = new TicTacTowPresenter(this);
        presenter.onCreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_tictactoe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_reset) {
            presenter.onResetSelected();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onCellClicked(View v) {

        Button button = (Button) v;
        String tag = button.getTag().toString();
        int row = Integer.parseInt(tag.substring(0, 1));
        int col = Integer.parseInt(tag.substring(1, 2));
        Log.i(TAG, "Click row [" + row + "]" + "[" + col + "]");
        presenter.onButtonSelected(row, col);
    }

    @Override
    public void showWinner(String winningPlayerDisplayLabel) {
        winnerPlayerLabel.setText(winningPlayerDisplayLabel);
        winnerPlayerViewGroup.setVisibility(View.VISIBLE);
    }

    @Override
    public void clearWinnerDisplay() {
        winnerPlayerViewGroup.setVisibility(View.GONE);
        winnerPlayerLabel.setText("");
    }

    @Override
    public void clearButtons() {
        for (int i = 0; i < buttonGrid.getChildCount(); i++) {
            ((Button) buttonGrid.getChildAt(i)).setText("");
        }
    }

    @Override
    public void setButtonText(int row, int col, String text) {
        Button button = (Button) buttonGrid.findViewWithTag("" + row + col);
        if (button != null) {
            button.setText(text);
        }
    }
}
