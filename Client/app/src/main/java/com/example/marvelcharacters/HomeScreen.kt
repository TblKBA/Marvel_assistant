package com.example.marvelcharacters

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelcharacters.list.CharacterAdapter
import com.example.marvelcharacters.list.CharacterItem
import com.example.marvelcharacters.list.isReallyOnline
import com.example.marvelcharacters.net.NetHelper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlinx.android.synthetic.main.view_itemh.*

class HomeScreen : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        if(isReallyOnline()){
        NetHelper.instance.getCharacters()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(

                onNext = {
                    Log.d("HomeScreen", it.toString())

                    val dataCharacterItem = ArrayList<CharacterItem>()

                    for (character in it.data.results) {
                        val imageUrl = "${character.thumbnail.path}/standard_large.${character.thumbnail.extension}".replace("http", "https")
                        val imageLargeUrl = "${character.thumbnail.path}/standard_fantastic.${character.thumbnail.extension}".replace("http", "https")

                        dataCharacterItem.add(CharacterItem(imageUrl, imageLargeUrl, character.name, character.description))
                    }

                    charactersListView.adapter = CharacterAdapter(this, dataCharacterItem)
                },

                onError = {
                    Log.d("HomeScreen", it.toString())
                }

            )}
        else {
            Toast.makeText(getApplicationContext(), "Интернет соединение отсутствует!!!", Toast.LENGTH_SHORT).show();}
    }

    @SuppressLint("CheckResult")
    fun FavoriteH(view: View) {
        //Создаем массив данных
       val favoritesH = FavoritesTable(
            "1",
           characterName.text.toString()
            // characterImage
        )

        Observable.fromCallable {

            val db = TestDatabase.getAppDataBase(context = this)
            val userDao = db?.readoutDAO()
            userDao!!.addFavorite(favoritesH)

        }.subscribeOn(Schedulers.io())

            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {  Toast.makeText(applicationContext, "Добавил в избранное!!!", Toast.LENGTH_SHORT).show(); }
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
