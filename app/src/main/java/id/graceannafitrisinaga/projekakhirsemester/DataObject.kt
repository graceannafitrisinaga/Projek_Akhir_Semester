package id.graceannafitrisinaga.projekakhirsemester

object DataObject {
    //mendefinisikan properti listdata pada class CardInfo
    var listdata = mutableListOf<CardInfo>()

    //menset semua data title dan priority bertipe string lalu menambahkan nya kedalam properti listdata pada class CardInfo di kolom title dan kolom priority
    fun setData(title: String, priority: String) {
        listdata.add(CardInfo(title, priority))
    }

    //mengambil semua data yang ada dalam list class CardInfo dan mengembalikannya kedalam properti lisdata
    fun getAllData(): List<CardInfo> {
        return listdata
    }

    //memanggil function deleteAll yang terdapat dalam class DAO untuk membersihkan isi value properti listdata
    fun deleteAll(){
        listdata.clear()
    }

    //menjalankan fungsi getData untuk mengambil data pos bertipe int oleh properti listdata pada class CardInfo
    fun getData(pos:Int): CardInfo {
        return listdata[pos]
    }

    //menghapus data pos bertipe int oleh properti listdata
    fun deleteData(pos:Int){
        listdata.removeAt(pos)
    }

    //mengupdate data pos bertipe int, data title bertipe string, dan data priority bertipe string oleh properti listdata
    fun updateData(pos:Int,title:String,priority:String)
    {
        listdata[pos].title=title
        listdata[pos].priority=priority
    }

}