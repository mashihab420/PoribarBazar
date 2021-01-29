package com.poribarbazar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.poribarbazar.Fragment.HomeFragment;
import com.poribarbazar.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    private ActivityMainBinding binding;
    HomeFragment homeFragment;
    int valu =0;
    Toolbar toolbarr;
    TextView toolbarTitle,cartQuantity;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toolbarr=findViewById(R.id.toolbar);
        setSupportActionBar(toolbarr);

     //   cartQuantity = findViewById(R.id.cart_quantity_id);
        toolbarTitle=findViewById(R.id.toolbarTitle);
        toolbarTitle.setText(R.string.app_name);


        setSupportActionBar(binding.include.toolbar);

        actionBarDrawerToggle=new ActionBarDrawerToggle(this,
                binding.drawerLayout,binding.include.toolbar,R.string.open, R.string.close);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getColor(R.color.white));


        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        binding.navigationView.setNavigationItemSelectedListener(this);

        

   
        initFragmentHome();
        
    }

    private void initFragmentHome(){
        homeFragment=new HomeFragment();
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(binding.include.contentMain.fragmentContainer.getId(),homeFragment,"HomeFragment").commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.cartmenuid){

            /*Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);*/
            Toast.makeText(this, "Add to Cart", Toast.LENGTH_SHORT).show();

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
        switch (view.getId()){
      

            case R.id.cart:
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                
                break;


            case R.id.loginid:
                binding.drawerLayout.closeDrawer(GravityCompat.START);
          

                break;

            case R.id.createaccount:
                binding.drawerLayout.closeDrawer(GravityCompat.START);
               

                break;

            case R.id.orderid:
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                

                break;



            case R.id.logout:
                Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
               
                binding.userName.setText("");
                binding.createaccount.setVisibility(View.VISIBLE);
                binding.loginid.setVisibility(View.VISIBLE);
                binding.logout.setVisibility(View.GONE);
                binding.orderid.setVisibility(View.GONE);
                break;


            default:


        }
    }
    
}