package id.graceannafitrisinaga.projekakhirsemester

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_update_card.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//kita menggunakan GLobalScope untuk menjalankan suspend function. dan mengupdate data ke dalam database
class UpdateCard : AppCompatActivity() {
    //menginisiasi variabel database agar dapat menggunakan class myDatabase
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //mengatur class activity_update_card kedalam layout oleh R menggunakan fungsi setContentView
        setContentView(R.layout.activity_update_card)
        //variabel database menggunakan room agar dapat menampilkan data tabel to_do dalam class myDatabase
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()
        //variabel pos diinisiasi menggunakan intent dengan id -1
        val pos = intent.getIntExtra("id", -1)
        //kemudian dilakukan seleksi jika value variabel pos tidak sama dengan -1
        if (pos != -1) {
            //maka variabel title  akan menggunakan class DataObject untuk mengambil data kolom title oleh pos
            val title = DataObject.getData(pos).title
            //maka variabel priority  akan menggunakan class DataObject untuk mengambil data kolom priority oleh pos
            val priority = DataObject.getData(pos).priority
            //properti create_title dan create_priority akan menset teks kedalamm kolom title dan kolom priority
            create_title.setText(title)
            create_priority.setText(priority)

            //button delete diberikan fungsi klik
            delete_button.setOnClickListener {
                //agar class DataObject dapat menjalankan fungsi deleteData pada variabel pos
                DataObject.deleteData(pos)
                //menggunakan GLobalScope untuk menjalankan suspend function dan mengupdate data ke dalam database
                GlobalScope.launch {
                    //class dao akan menjalankan fungsi deleteTask untuk menghapus data create_title
                    //dan create_priority dengan id pos ditambah 1 yang ada dalam database
                    database.dao().deleteTask(
                        Entity(
                            pos + 1,
                            create_title.text.toString(),
                            create_priority.text.toString()
                        )
                    )
                }
                myIntent()
            }

            //button update diberikan fungsi klik
            update_button.setOnClickListener {
                //agar class DataObject dapat menjalankan fungsi updateData pada variabel pos, create_title, dan create_priority
                DataObject.updateData(
                    pos,
                    create_title.text.toString(),
                    create_priority.text.toString()
                )
                //menggunakan GLobalScope untuk menjalankan suspend function dan mengupdate data ke dalam database
                GlobalScope.launch {
                    //class dao akan menjalankan fungsi updateTask untuk mengupdate data create_title
                    //dan create_priority dengan id pos ditambah 1 yang ada dalam database
                    database.dao().updateTask(
                        Entity(
                            pos + 1, create_title.text.toString(),
                            create_priority.text.toString()
                        )
                    )
                }
                myIntent()
            }

        }
    }

    //fungsi myIntent dibuat agar intent dapat diterapkan pada variabel intent untuk berpindah ke halaman class MainActivity
    fun myIntent() {
        val intent = Intent(this, MainActivity::class.java)
        //mulai intent
        startActivity(intent)
    }
}