
package com.example.mr_motor_.presentation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mr_motor_.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        setTheme(R.style.splashScreenTheme)
        setContentView(R.layout.activity_main)

        val bottomNavigationView : BottomNavigationView = findViewById(R.id.nav_view)
        bottomNavigationView.itemIconTintList = null
        val radius = resources.getDimension(R.dimen.cornerSize)

        val shapeDrawable : MaterialShapeDrawable = bottomNavigationView.background as MaterialShapeDrawable
        shapeDrawable.shapeAppearanceModel = shapeDrawable.shapeAppearanceModel
            .toBuilder()
            .setTopLeftCorner(CornerFamily.ROUNDED, radius)
            .setTopRightCorner(CornerFamily.ROUNDED, radius)
            .build()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        val appBarDrawerToggle = AppBarConfiguration(setOf(
            R.id.navigation_news, R.id.navigation_home, R.id.navigation_task_page
        ))
        setupActionBarWithNavController(navController, appBarDrawerToggle)
        bottomNavigationView.setupWithNavController(navController)

    }

    companion object{
        fun start(caller : Activity){
            val intent : Intent = Intent(caller, MainActivity::class.java)
            caller.startActivity(intent)
        }
    }
}