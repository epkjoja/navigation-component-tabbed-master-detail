package com.myapplication.activity

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import com.myapplication.R
import com.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(R.id.tabs_fragment), binding.drawerLayout)
        setupActionBarWithNavController(findNavHostController(), appBarConfiguration)
        binding.navView.setupWithNavController(findNavHostController())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.options, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavHostController()
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}

fun FragmentActivity.findNavHostController(): NavController {
    val navHostFragment = supportFragmentManager
        .findFragmentById(R.id.content_main) as NavHostFragment
    return navHostFragment.navController
}