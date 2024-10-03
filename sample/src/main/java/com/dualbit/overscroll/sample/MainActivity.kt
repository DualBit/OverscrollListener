package com.dualbit.overscroll.sample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.dualbit.overscroll.addOverScrollEdge
import com.dualbit.overscroll.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        ViewCompat.setOnApplyWindowInsetsListener(viewBinding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewBinding.rclMain.addOverScrollEdge(lifecycleScope = lifecycleScope,
            onTop = {
                Toast.makeText(this, "⬆\uFE0F Overscroll Top!", Toast.LENGTH_SHORT).show()
            },
            onBottom = {
                Toast.makeText(this, "⬇\uFE0F Overscroll Bottom!", Toast.LENGTH_SHORT).show()
            })

        viewBinding.rclMain.adapter = MainAdapter(
            listOf(
                "Rome",
                "New York",
                "Dubai",
                "Moscow",
                "Paris",
                "Tokyo",
                "Barcelona",
                "Bali",
                "Sydney",
                "Casablanca",
                "Vancouver",
                "Lisbon",
                "Amsterdam",
                "London",
                "Shangai",
                "Mordor",
                "Genosha",
                "Berlin"
            )
        )

    }
}