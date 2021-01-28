package com.example.marvelcharacters

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.*
import androidx.room.RoomDatabase.Callback
import androidx.sqlite.db.SupportSQLiteDatabase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_reg.*
import java.sql.Blob

//Обьявление дата класов для таблиц БД
@Entity
data class UsersTable(
    @ColumnInfo var name: String?,
    @ColumnInfo var surname: String?,
    @ColumnInfo var pat: String?,
    @ColumnInfo var login: String?,
    @ColumnInfo var pass: String?,
    @ColumnInfo var role: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

@Entity
data class FavoritesTable(
    @ColumnInfo var type: String?,
    @ColumnInfo var name: String?
    //@ColumnInfo var image: Blob?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

@Dao
interface ReadoutModelDao {
    // методы для юзеров
    @get:Query("select * from UsersTable")       // "get:" означает применение аннотации "Query" к геттеру (функцию геттера для переменной allReadoutItems вручную не пишем)
    val allReadoutItems: List<UsersTable>             // Обёртываем возвращаемое значение LiveData<...> чтобы отслеживать изменения в базе. При изменении данных будут рассылаться уведомления

    @Query("select * from UsersTable where id = :id")
    fun getReadoutById(id: Long): UsersTable

    @Query("select * from UsersTable where login = :login")
    fun getReadoutByLogin(login: String): List<UsersTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(readoutModel: UsersTable)

    @Update
    fun updateReadout(readoutModel: UsersTable)

    @Delete
    fun deleteReadout(readoutModel: UsersTable)

    //методы для избранного
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(readoutModel: FavoritesTable)

    @get:Query("select * from FavoritesTable")
    val getFavorites: List<FavoritesTable>

    @Query("select * from FavoritesTable where id = :id")
    fun getFavoritesbyid(id: Long): FavoritesTable

    @Query("DELETE FROM FavoritesTable")
    fun cleantable()

}

/*
 * Описание базы данных.
 */
@Database(entities = [UsersTable::class, FavoritesTable::class], version = 1)
abstract class TestDatabase : RoomDatabase() {

    abstract fun readoutDAO(): ReadoutModelDao           // Описываем абстрактные методы для получения объектов интерфейса BorrowModelDao

    companion object {
        var INSTANCE: TestDatabase? = null

        fun getAppDataBase(context: Context): TestDatabase? {


            if (INSTANCE == null) {
                synchronized(TestDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, TestDatabase::class.java, "TestDB")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}
