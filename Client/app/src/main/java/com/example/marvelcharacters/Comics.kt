package com.example.marvelcharacters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.marvelcharacters.list.*
import com.example.marvelcharacters.net.NetHelper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_comics_list.*
import kotlinx.android.synthetic.main.activity_reg.*
import kotlinx.android.synthetic.main.view_itemc.*

class Comics : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comics_list)

        if(isReallyOnline()){
            NetHelper.instance.getComics()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(

                    onNext = {
                        Log.d("ComicsT", it.toString())

                        val dataComicsItem = ArrayList<ComicsItem>()

                        for (comics in it.data.results) {
                            val imageUrl = "${comics.thumbnail.path}/standard_large.${comics.thumbnail.extension}".replace("http", "https")
                            val imageLargeUrl = "${comics.thumbnail.path}/standard_fantastic.${comics.thumbnail.extension}".replace("http", "https")

                            dataComicsItem.add(ComicsItem(imageUrl, imageLargeUrl, comics.title, comics.description))
                        }

                        comicsListView.adapter = ComicsAdapter(this, dataComicsItem)

                        comicsListView.setOnItemClickListener{parent, view, position, id ->
                            val listItem = dataComicsItem[position]

                            val intent = Intent(this, CharacterActivity::class.java)
                            intent.putExtra("comicsImageLarge", listItem.comicsImageLarge)
                            intent.putExtra("comicsName", listItem.comicsTitle)
                            intent.putExtra("comicsDesc", listItem.comicsDesc)

                            startActivity(intent)
                        }
                    },

                    onError = {
                        Log.d("ComicsT", it.toString())
                    }

                )
        }
        else {
            Toast.makeText(getApplicationContext(), "Интернет соединение отсутствует!!!", Toast.LENGTH_SHORT).show();}
    }

    @SuppressLint("CheckResult")
    fun FavoriteC(view: View) {

        //Создаем массив данных
        val favoritesC = FavoritesTable(
            "2",
            comicsName.text.toString()
           // comicsImage
        )


        Observable.fromCallable {

            val db = TestDatabase.getAppDataBase(context = this)
            val userDao = db?.readoutDAO()
            userDao!!.addFavorite(favoritesC)

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
