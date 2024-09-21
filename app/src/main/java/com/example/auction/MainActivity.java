package com.example.auction;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.PopupMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ImageView imageView,home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        home = findViewById(R.id.account);

        home.setOnClickListener(view -> getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new Home())
                .commit());

        imageView.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(MainActivity.this, imageView);
            popupMenu.getMenuInflater().inflate(R.menu.menup, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                // Handle menu item clicks here
                if (item.getItemId() == R.id.menu_item_account) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new AccountFragment())
                            .commit();
                    return true;
                } else if (item.getItemId() == R.id.menu_item_wallet) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new WalletFragment())
                            .commit();
                    return true;
                } else if (item.getItemId() == R.id.menu_item_your_bids) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new BidsFragment())
                            .commit();
                    return true;
                } else if (item.getItemId() == R.id.menu_item_sell_item) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new SellFragment())
                            .commit();
                    return true;
                } else if (item.getItemId() == R.id.menu_item_about) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new AboutFragment())
                            .commit();
                    return true;
                } else if (item.getItemId() == R.id.menu_item_log_out) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new ProductInfoFragment())
                            .commit();
                    return true;
                }
                return false;
            });
            popupMenu.show();
        });

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.inflateMenu(R.menu.bottom_nav_menu);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new Home())
                    .commit();
        }

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.item1) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new Home())
                        .commit();
                return true;
            } else if (item.getItemId() == R.id.item2) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new BidsFragment())
                        .commit();
                return true;
            } else if (item.getItemId() == R.id.item3) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new WalletFragment())
                        .commit();
                return true;
            } else if (item.getItemId() == R.id.item4) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new AccountFragment())
                        .commit();
                return true;
            }
            return false;
        });
    }
}