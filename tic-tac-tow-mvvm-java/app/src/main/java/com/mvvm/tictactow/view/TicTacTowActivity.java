package com.mvvm.tictactow.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mvvm.tictactow.R;
import com.mvvm.tictactow.databinding.ActivityTictactowBinding;
import com.mvvm.tictactow.viewmodel.TicTacTowViewModel;

public class TicTacTowActivity extends AppCompatActivity {

    TicTacTowViewModel viewModel = new TicTacTowViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTictactowBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_tictactow);
        binding.setViewModel(viewModel);
        viewModel.onCreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tictactow, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (R.id.action_reset == item.getItemId()) {
            viewModel.onResetSelected();
            return true;
        } else return false;
    }
}
