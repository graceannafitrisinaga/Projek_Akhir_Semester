package id.graceannafitrisinaga.projekakhirsemester

import androidx.room.Database
import androidx.room.RoomDatabase

//Menentukan class database untuk menyimpan class myDatabase ini yang digunakan untuk melakukan koneksi dari device ke database room dengan mengguankan amotasi @Database
//untuk pembuatannya class harus di anotasi dengan @Database dan kemudian menentukan array entities yang akan mencantumkan semua entity data yang terkait dengan database,
@Database(entities = [Entity::class],version=1)
abstract class myDatabase : RoomDatabase() {
    //kemudian kita akan menentukan metode abstrak oleh fungsi dao dan akan menampilkan instance class DAO
    abstract fun dao():DAO
}
