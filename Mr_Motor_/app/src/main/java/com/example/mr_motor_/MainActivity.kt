
package com.example.mr_motor_

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        val bottomNavigationViewation : BottomNavigationView = findViewById(R.id.nav_view)
        val radius = resources.getDimension(R.dimen.cornerSize)

        val shapeDrawable : MaterialShapeDrawable = bottomNavigationViewation.background as MaterialShapeDrawable
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
        bottomNavigationViewation.setupWithNavController(navController)

    }
}