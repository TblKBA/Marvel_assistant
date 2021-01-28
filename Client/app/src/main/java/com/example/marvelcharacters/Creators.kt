package com.example.marvelcharacters

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.marvelcharacters.list.*
import com.example.marvelcharacters.net.NetHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_creators.*

class Creators : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creators)

        if(isReallyOnline()){
            NetHelper.instance.getCreators()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(

                    onNext = {
                        Log.d("CreatorsT", it.toString())

                        val dataCreatorsItem= ArrayList<CreatorsItem>()

                        for (comics in it.data.results) {
                            val imageUrl = "${comics.thumbnail.path}/standard_large.${comics.thumbnail.extension}".replace("http", "https")
                            val imageLargeUrl = "${comics.thumbnail.path}/standard_fantastic.${comics.thumbnail.extension}".replace("http", "https")

                            dataCreatorsItem.add(CreatorsItem(imageUrl, imageLargeUrl, comics.fullname))
                        }

                        creatorsListView.adapter = CreatorsAdapter(this, dataCreatorsItem)

                    },

                    onError = {
                        Log.d("CreatorsT", it.toString())
                    }

                )
        }
        else {
            Toast.makeText(getApplicationContext(), "Интернет соединение отсутствует!!!", Toast.LENGTH_SHORT).show();}
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.action_update -> {
                recreate()
                return true
            }
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
