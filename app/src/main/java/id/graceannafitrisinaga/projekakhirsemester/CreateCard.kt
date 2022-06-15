package id.graceannafitrisinaga.projekakhirsemester


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_create_card.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//Untuk membuat card baru dengan dengan menambahkan button save untuk menyimpan data yang sudah di isi.
// mengisi form dengan menggunakan method trim untuk tetap menyimpan dengan spasi
//dan hanya akan di simpan jika tidak kosong dengan mengguankan isNotEmpty spasi.
class CreateCard : AppCompatActivity() {
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //mengatur class activity_create_card kedalam layout oleh R menggunakan fungsi setContentView
        setContentView(R.layout.activity_create_card)
        //variabel database menggunakan room agar dapat menampilkan data tabel to_do dalam class myDatabase
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()
        //memberikan fungsi klik pada button save
        save_button.setOnClickListener {
            //memberikan seleksi jika terdapat teks dengan spasi yang ditambahkan dalam kolom title dan kolom priority menggunakan create_title dan create_priority
            if (create_title.text.toString().trim { it <= ' ' }.isNotEmpty()
                && create_priority.text.toString().trim { it <= ' ' }.isNotEmpty()
            ) {
                //maka variabel title akan mengambil teks yang ditambahkan user dalam create_title
                var title = create_title.getText().toString()
                //dan variabel priority akan mengambil teks yang ditambahkan user dalam create_priority
                var priority = create_priority.getText().toString()
                //lalu data object akan menset data yang ditambahkan user kedalam kolom title dan kolom priority
                DataObject.setData(title, priority)
                //menggunakan GLobalScope untuk menjalankan suspend function dan mengupdate data ke dalam database
                GlobalScope.launch {
                    //class dao akan menggunakan fungsi insertTask untuk memasukkan data kedalam entity di database sesuai dengan id, kolom title, dan kolom priority
                    database.dao().insertTask(Entity(0, title, priority))

                }

                //menggunakan variabel intent untuk berpindah ke class MainActivity ketika button save di klik
                val intent = Intent(this, MainActivity::class.java)
                //memulai intent
                startActivity(intent)
            }
        }
    }
}

