package id.graceannafitrisinaga.projekakhirsemester


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


//Untuk mengases data dari database dapat di lihat getAllData mengakses database
//dengan menggunakan RxJava, dan
// dengan menggunakan RecyclerView untuk menampilkan semua data dari database pada halaman utama di daftar task layout aplikasi

class MainActivity : AppCompatActivity() {
    // menginisiasi variabel database agar class myDatabase dapat digunakan
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //mengatur class activity_main kedalam layout oleh R menggunakan fungsi setContentView
        setContentView(R.layout.activity_main)
        //variabel database menggunakan room agar dapat menampilkan data tabel to_do dalam class myDatabase
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()
        //lalu ditambahkan fungsi klik agar intent dapat dijalankan untuk berpindah ke halaman class CreateCard
        add.setOnClickListener {
            val intent = Intent(this, CreateCard::class.java)
            //memulai intent
            startActivity(intent)
        }
        //pada button deleteAll ditambahkan fungsi klik
        deleteAll.setOnClickListener {
            //agar class DataObject dapat menjalankan fungsi deleteAll
            DataObject.deleteAll()
            //menggunakan globalscope untuk mengupdate data dalam database
            GlobalScope.launch {
                //kemudian class dao akan menjalankan fungsi deleteAll untuk menghapus data yang ada dalam database
                database.dao().deleteAll()
            }
            //menset scroll
            setRecycler()
        }

        setRecycler()

    }

    //fungsi setRecycler dibuat agar class adapter dapat menggunakan recycler_view untuk scroll tampilan data yang diambil dari class DataObject
    fun setRecycler() {
        recycler_view.adapter = Adapter(DataObject.getAllData())
        recycler_view.layoutManager = LinearLayoutManager(this)
    }
}