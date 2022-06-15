package id.graceannafitrisinaga.projekakhirsemester

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view.view.*

//Membuat costum adapter ListView dengan membuat dua buah resource layout terlebih dahulu yang ada pada folder layout.
// satu untuk list viewnya dan satu lagi digunakan untuk menampilkan isi/item dari listview tersebut, dan
// mengambil pada class viewHolder, kemudian kita set isi pada onBindViewHolder
//dan yang terakhir adalah set adapternya pada layout view.

//class Adapter menggunakan variabel data list dalam class CardInfo
//Memperluas ItemAdapter dari class abstrak RecyclerView.Adapter.
//ItemAdapter.ItemViewHolder sebagai jenis holder tampilan.
class Adapter(var data: List<CardInfo>) : RecyclerView.Adapter<Adapter.viewHolder>() {
    //menginisiasi class viewHolder menggunakan itemView dan RecyclerView
    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //mendefinisikan properti title oleh class itemView
        var title = itemView.title
        //mendefinisikan properti priority oleh class itemView
        var priority = itemView.priority
        //mendefinisikan properti layout oleh class itemView
        var layout = itemView.mylayout
    }

    //memanggil metode ini setiap kali perlu membuat ViewHolder baru.
    //Metode ini membuat serta menginisialisasi ViewHolder dan View-nya yang diatribusikan, tetapi tidak mengisi
    //konten tampilan—ViewHolder karena belum terikat dengan data tertentu.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.view, parent, false)
        return viewHolder(itemView)
    }

    //Metode ini dipanggil untuk mengatribusikan ViewHolder dengan data.
    //Metode ini mengambil data yang sesuai dan menggunakan data tersebut untuk mengisi tata letak pemegang tampilan.
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        when (data[position].priority.toLowerCase()) {
            "high" -> holder.layout.setBackgroundColor(Color.parseColor("#F05454"))
            "medium" -> holder.layout.setBackgroundColor(Color.parseColor("#EDC988"))
            else -> holder.layout.setBackgroundColor(Color.parseColor("#00917C"))
        }

        //menginisiasi holder untuk mengambil teks dalam properti title untuk dimasukkan kedalam position data
        holder.title.text = data[position].title
        //menginisiasi holder untuk mengambil teks dalam properti priority untuk dimasukkan kedalam position data
        holder.priority.text = data[position].priority
        //menginisiasi holder menggunakan fungsi klik pada itemview
        holder.itemView.setOnClickListener{
            //menggunakan properti intent, agar ketika holder itemview di klik dapat pindah ke class UpdateCard
            val intent= Intent(holder.itemView.context,UpdateCard::class.java)
            intent.putExtra("id",position)
            //memulai intent pada holder itemView
            holder.itemView.context.startActivity(intent)
        }

    }

    //fungsi item count untuk mendapatkan ukuran set data karena recyclerview menggunakannya untuk menentukan
    //bahwa tidak ada lagi item yang akan di tampilkan.
    override fun getItemCount(): Int {
        return data.size
    }
}