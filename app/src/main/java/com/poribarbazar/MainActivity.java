package com.poribarbazar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.poribarbazar.Fragment.HomeFragment;
import com.poribarbazar.MyPreferance.MysharedPreferance;
import com.poribarbazar.UI.CartActivity;
import com.poribarbazar.UI.LoginActivity;
import com.poribarbazar.UI.MyOrders;
import com.poribarbazar.UI.ProductsActivity;
import com.poribarbazar.UI.Profile;
import com.poribarbazar.UI.SignUpActivity;
import com.poribarbazar.databinding.ActivityMainBinding;
import com.poribarbazar.model.ModelCartRoom;

import java.util.List;

import static androidx.core.view.GravityCompat.*;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private ActivityMainBinding binding;
    HomeFragment homeFragment;
    int valu = 0;
    Toolbar toolbarr;
    TextView toolbarTitle, cartQuantity;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    MysharedPreferance sharedPreferences;
    CartRepository repository;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = MysharedPreferance.getPreferences(getApplicationContext());

        toolbarr = findViewById(R.id.toolbar);
        setSupportActionBar(toolbarr);









        //   cartQuantity = findViewById(R.id.cart_quantity_id);
        cartQuantity = findViewById(R.id.cart_quantity_id);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText(R.string.app_name);



        if (sharedPreferences.getPhone().equals("none")){
            binding.navLogout.setVisibility(View.GONE);
            binding.logoutText.setVisibility(View.GONE);
        }else{
            binding.navLogout.setVisibility(View.VISIBLE);
            binding.logoutText.setVisibility(View.VISIBLE);
        }


        setSupportActionBar(binding.include.toolbar);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                binding.drawerLayout, binding.include.toolbar, R.string.open, R.string.close);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getColor(R.color.white));



        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();





        binding.navigationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tools.hideKeyboard(MainActivity.this);
            }
        });

        binding.navigationView.setNavigationItemSelectedListener(this);


        binding.navBaby.setOnClickListener(this);
        binding.navElectronic.setOnClickListener(this);
        binding.navHealth.setOnClickListener(this);
        binding.navOrders.setOnClickListener(this);
        binding.navProfile.setOnClickListener(this);
        binding.navLogout.setOnClickListener(this);
        binding.navWoman.setOnClickListener(this);
        binding.navWatches.setOnClickListener(this);
        binding.navMan.setOnClickListener(this);
        binding.navGrocharies.setOnClickListener(this);
        binding.navHome.setOnClickListener(this);

        repository = new CartRepository(this);

        repository.getAllData().observe(this, new Observer<List<ModelCartRoom>>() {
            @Override
            public void onChanged(List<ModelCartRoom> modelCartRooms) {

                if (modelCartRooms.size() == 0) {
                    cartQuantity.setVisibility(View.GONE);
                } else {
                    cartQuantity.setVisibility(View.VISIBLE);
                    if (modelCartRooms.size() > 99) {
                        cartQuantity.setText("99+");
                    } else {
                        cartQuantity.setText("" + modelCartRooms.size());
                    }


                }
            }
        });

        initFragmentHome();


    }

    private void initFragmentHome() {
        homeFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(binding.include.contentMain.fragmentContainer.getId(), homeFragment, "HomeFragment").commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.cartmenuid) {

            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);


          /*  valu++;
            if (valu>0){
                cartQuantity.setVisibility(View.VISIBLE);
            }

            cartQuantity.setText(""+valu);

            if (valu>10){
                cartQuantity.setText("10+");
            }*/

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {


            case R.id.nav_profile:


                if (sharedPreferences.getPhone().equals("none")) {

                    Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                    intent.putExtra("method", "" + "MainActivity");
                    intent.putExtra("invoiceid", "");
                    intent.putExtra("subtotal", "");
                    intent.putExtra("total", "");
                    startActivity(intent);
                } else {

                    startActivity(new Intent(MainActivity.this, Profile.class));
                }
                binding.drawerLayout.closeDrawer(START);

                break;


            case R.id.nav_orders:
                if (sharedPreferences.getPhone().equals("none")) {
                    Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                    intent.putExtra("method", "" + "MainActivity");
                    intent.putExtra("invoiceid", "");
                    intent.putExtra("subtotal", "");
                    intent.putExtra("total", "");
                    startActivity(intent);
                } else {

                    startActivity(new Intent(MainActivity.this, MyOrders.class));
                }

                binding.drawerLayout.closeDrawer(START);

                break;


            case R.id.nav_man:

                Intent intent = new Intent(MainActivity.this, ProductsActivity.class);
                intent.putExtra("category", "Man");
                intent.putExtra("type", "none");
                startActivity(intent);

                binding.drawerLayout.closeDrawer(START);

                break;


            case R.id.nav_electronic:

                intent = new Intent(MainActivity.this, ProductsActivity.class);
                intent.putExtra("category", "Electronics");
                intent.putExtra("type", "none");
                startActivity(intent);

                binding.drawerLayout.closeDrawer(START);


                break;

            case R.id.nav_Watches:

                intent = new Intent(MainActivity.this, ProductsActivity.class);
                intent.putExtra("category", "Watches & Accessories");
                intent.putExtra("type", "none");
                startActivity(intent);
                binding.drawerLayout.closeDrawer(START);


                break;

            case R.id.nav_health:
                intent = new Intent(MainActivity.this, ProductsActivity.class);
                intent.putExtra("category", "Health & Beauty");
                intent.putExtra("type", "none");
                startActivity(intent);
                binding.drawerLayout.closeDrawer(START);
                break;


            case R.id.nav_grocharies:
                intent = new Intent(MainActivity.this, ProductsActivity.class);
                intent.putExtra("category", "Grocery");
                intent.putExtra("type", "none");
                startActivity(intent);
                binding.drawerLayout.closeDrawer(START);
                break;


            case R.id.nav_home:
                intent = new Intent(MainActivity.this, ProductsActivity.class);
                intent.putExtra("category", "Home & Lifestyle");
                intent.putExtra("type", "none");
                startActivity(intent);
                binding.drawerLayout.closeDrawer(START);
                break;



            case R.id.nav_woman:

                intent = new Intent(MainActivity.this, ProductsActivity.class);
                intent.putExtra("category", "Woman");
                intent.putExtra("type", "none");
                startActivity(intent);

                binding.drawerLayout.closeDrawer(START);

                break;

            case R.id.nav_baby:

                intent = new Intent(MainActivity.this, ProductsActivity.class);
                intent.putExtra("category", "Baby");
                intent.putExtra("type", "none");
                startActivity(intent);

                binding.drawerLayout.closeDrawer(START);

                break;
            case R.id.nav_logout:

                sharedPreferences.setName(null);
                sharedPreferences.setAddress(null);
                sharedPreferences.setPhone("none");
                sharedPreferences.setEmail(null);

                binding.drawerLayout.closeDrawer(START);
                Tools.snackInfo(MainActivity.this, "Logout");

                intent = new Intent(MainActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                break;


            default:

        }
    }

}