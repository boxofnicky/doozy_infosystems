package com.doozyinfosystem.sample.doozyinfosystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.doozyinfosystem.sample.doozyinfosystem.db.DBSource;
import com.doozyinfosystem.sample.doozyinfosystem.db.Tables;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String PRODUCT_ID = "PRODUCT_ID";
    public static final String RESULT_DATA = "RESULT_DATA";
    private static final int REQUEST_CODE = 1212;
    private static final int LOGIN = 112233;
    private static final int FILES = 212121;
    public static User currentUser;
    private CoordinatorLayout coordinator;
    private static final int ON_WEB = 1111;
    private static final int ABOUT = 1112;
    private static final String topDiaplayImage = "logoAbout.png";
    private static final String webAdd = "https://m.facebook.com/Doozy-Infosystems-1627013727572229";
    private static final String[] emailAdd = {"doozyinfosystems@gmail.com", "box.of.nicky@gmail.com"};
    private static final int SHOPPING_CART = 2200;
    final private List<Product> products = DataProvider.getProducts();
    private Toolbar toolbar;
    private DBSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        coordinator = (CoordinatorLayout) findViewById(R.id.coordinator);

        createLayout();

            try {
                if (dataSource.countRows(Tables.TABLE_USERS) == 0) {
                for (User u : DataProvider.getUserList()) {
                    dataSource.addUser(u);
                }
                } else {
                    Toast.makeText(this, "User Database already exists", Toast.LENGTH_LONG).show();
                }
            } catch (SQLiteException e) {
                e.printStackTrace();

                Toast.makeText(this, "Database error loading user details.", Toast.LENGTH_LONG).show();
            }
            Toast.makeText(this, "User Database updated.", Toast.LENGTH_LONG).show();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       Intent mailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));
//                mailIntent.setData(Uri.parse("mailto:"));
                                       mailIntent.putExtra(Intent.EXTRA_EMAIL, emailAdd);
                                       mailIntent.putExtra(Intent.EXTRA_SUBJECT, "Information Request");
                                       mailIntent.putExtra(Intent.EXTRA_TEXT, "Enter the requested information here.");
                                       if (mailIntent.resolveActivity(getPackageManager()) != null)
                                           startActivity(mailIntent);
                                   }
                               }
        );
    }

    public void createLayout() {
        dataSource = new DBSource(this);
        dataSource.open();
        if (dataSource.countRows(Tables.TABLE_ITEMS) == 0) {
            try {
                for (Product p :
                        products) {
                    dataSource.addItem(p);

                }
            } catch (SQLiteException e) {
                e.printStackTrace();
                Toast.makeText(this, "Database error.", Toast.LENGTH_LONG).show();

            }
            Toast.makeText(this, "Database updated.", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "Database allready exists", Toast.LENGTH_LONG).show();
        }

        try {
            if (dataSource.countRows(Tables.TABLE_CART) == 0) {
                for (Product u : DataProvider.getCartItems(this)) {
                    dataSource.addItem(u);
                }
            } else {
                Toast.makeText(this, "User Database already exists", Toast.LENGTH_LONG).show();
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            Toast.makeText(this, "Database error loading user details.", Toast.LENGTH_LONG).show();
        }
        dataSource.close();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);

        boolean grid = settings.getBoolean(getString(R.string.grid_view_pref), false);

        //user name check

        SharedPreferences preferences = getSharedPreferences(LoginActivity.GLOBAL_SHARED_PREFS, MODE_PRIVATE);
        String username = preferences.getString(LoginActivity.USER_NAME, null);

        List<Product> productsFromDB = dataSource.getAllItems();
        ProductDataAdapter adapter = new ProductDataAdapter(this, productsFromDB);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        if (grid)
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        else
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Snackbar.make(coordinator, "One item added to your cart. Proceed to cart?", Snackbar.LENGTH_LONG).setAction("Show CartItem", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Opens cart here.", Toast.LENGTH_LONG).show();
                    }
                }).show();
            }
        } else {
            if (requestCode == LoginActivity.LOGIN_KEY) {
                if (resultCode == RESULT_OK) {
                    currentUser = data.getParcelableExtra(LoginActivity.USER_NAME);
                    Snackbar.make(coordinator, "Logged in as : " + currentUser.getCustomerName(), Snackbar.LENGTH_LONG).show();
                    toolbar.setTitle(currentUser.getCustomerName());
                }
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        dataSource.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        createLayout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.add(0, ON_WEB, 101, R.string.onWeb);
        menu.add(0, ABOUT, 102, R.string.about);
        menu.add(0, LOGIN, 103, "Login");
        menu.add(0, FILES, 104, "File Manager");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                Intent settingIntent = new Intent(this, SettingActivity.class);
                startActivity(settingIntent);
                return true;
            case ON_WEB:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webAdd));
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivity(intent);
                else
                    Toast.makeText(this, "No supported application selected", Toast.LENGTH_LONG).show();
                return true;
            case ABOUT:
                Intent aboutInt = new Intent(this, AboutActivity.class);
                startActivity(aboutInt);
                return true;
            case LOGIN:
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivityForResult(loginIntent, LoginActivity.LOGIN_KEY);
                return true;
            case R.id.shopping_cart:
                Intent cartIntent = new Intent(this, CartActivity.class);
                startActivity(cartIntent);
                return true;
            case FILES:
                Intent filesIntent = new Intent(this, FilesActivity.class);
                startActivity(filesIntent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
