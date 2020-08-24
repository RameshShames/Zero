package com.example.zero;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
public class bottomnavmain_MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
  private DrawerLayout drawer;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_bottomnavmain);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    drawer = findViewById(R.id.drawer_layout);
    NavigationView navigationView = findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
              new quotesFragment()).commit();
      navigationView.setCheckedItem(R.id.navigation_home);
    }
   // Button AdminBtn = (Button)findViewById(R.id.admin_FAB);
    Intent intent = getIntent();
    String Name=intent.getStringExtra("Name");
    if(Name.equals("Admin")){
      Menu nav_menu=navigationView.getMenu();
      nav_menu.findItem(R.id.navigation_Admin).setVisible(true);
    }
    else{
      Menu nav_menu=navigationView.getMenu();
      nav_menu.findItem(R.id.navigation_Admin).setVisible(false);

    }
  /*  AdminBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(android.view.View v) {
        Intent in = new Intent(getApplicationContext(),Admin.class);
        in.putExtra("Some","Some Data");
        startActivity(in);
      }
    });*/

  }
  @Override
  public void onBackPressed() {
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }
  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    SharedPreferences pref;
    switch (item.getItemId()) {
      case R.id.navigation_home:
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new quotesFragment()).commit();
        break;
      case R.id.navigation_sms:
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new newsFragment()).addToBackStack(null).commit();
        break;
      case R.id.navigation_notifications:
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new supportFragment()).addToBackStack(null).commit();
        break;
      case R.id.navigation_logout:
        pref=getSharedPreferences("user_details",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
        break;
      case R.id.navigation_Admin:
        Intent in = new Intent(getApplicationContext(),Admin.class);
        in.putExtra("Some","Some Data");
        startActivity(in);
        break;
    }
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }
}