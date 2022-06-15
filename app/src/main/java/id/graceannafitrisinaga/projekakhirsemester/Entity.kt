package id.graceannafitrisinaga.projekakhirsemester

//Menentukan data menggunakan Entity Room untuk menggunakan library persentasi room
//untuk menyimpan data aplikasi dimana objek yang ingin kita simpan sesuai dengan keinginan
import androidx.room.Entity
//menentukan data primaryKey pada entity room
import androidx.room.PrimaryKey

//entity adalah struktur tabel pada database room
//mendefinisikan entitas atau tabel To_Do
//karna secara default Room akan menggunakan nama class sebagai nama tabel di database. begitupun
//dengan Room akan menggunakan nama kolom dalam database secara default.
@Entity(tableName = "To_Do")
//Class ini menjelaskan Entity (yang mewakili tabel SQLite)
data class Entity(
    //menentukan kunci utama dalam entity
    @PrimaryKey(autoGenerate = true)
    //properti id menggunakan kolom id dengan tipe data int
    var id:Int,
    //properti title menggunakan kolom title dengan tipe data int
    var title:String,
    //properti priority menggunakan kolom priority dengan tipe data int
    var priority:String
)
