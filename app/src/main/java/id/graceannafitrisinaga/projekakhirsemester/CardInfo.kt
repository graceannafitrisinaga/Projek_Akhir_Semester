package id.graceannafitrisinaga.projekakhirsemester

//Untuk menampung data dari cardInfo pada kotlin data class
//semua constructornya harus di tandai dengan var.
data class CardInfo(
    //variabel title sebagai kolom pertama dalam tabel CardInfo bertipe data string
    var title:String,
    //variabel priority sebagai kolom kedua dalam tabel CardInfo bertipe data string
    var priority:String
)
