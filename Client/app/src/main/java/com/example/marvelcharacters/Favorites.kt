package com.example.marvelcharacters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class Favorites : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu2, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.heroes -> {
                heroes()
                return true
            }
            R.id.comics -> {
                comics()
                return true
            }
            R.id.creators -> {
                creators()
                return true
            }
            R.id.favorites -> {
                favorites()
                return true
            }
            R.id.about -> {
                about()
                return true
            }
            R.id.logout -> {
                logout()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    fun heroes() {
        val intent = Intent(this, HomeScreen::class.java)
        startActivity(intent)
    }

    fun comics() {
        val intent = Intent(this, Comics::class.java)
        startActivity(intent)
    }

    fun creators() {
        val intent = Intent(this, Creators::class.java)
        startActivity(intent)
    }

    fun favorites() {
        val intent = Intent(this, Favorites::class.java)
        startActivity(intent)
    }

    fun about() {
        val intent = Intent(this, About::class.java)
        startActivity(intent)
    }

    fun logout() {
        finish()
    }

}
