package by.itacademy.tmbdapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import by.itacademy.tmbdapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.guest.setOnClickListener { startActivity(Intent(this,DashBoardActivity::class.java)) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater=menuInflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}