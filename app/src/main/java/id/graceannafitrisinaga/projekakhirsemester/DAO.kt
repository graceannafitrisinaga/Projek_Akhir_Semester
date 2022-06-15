package id.graceannafitrisinaga.projekakhirsemester

import androidx.room.*

//Anotasi @Dao mengidentifikasikan sebagai class DAO untuk Room
@Dao
interface DAO {
    //Anotasi yang menandakan bahwa methode yang ada dibawahnya berfungsi untuk menginsert data
    //Setiap parameter untuk metode @Insert harus berupa instance class entity data Room yang dianotasikan dengan @Entity atau kumpulan instance class entity data.
    //Saat metode @Insert dipanggil, Room akan menyisipkan setiap instance entity yang diteruskan ke tabel database.
    @Insert
    suspend fun insertTask(entity: Entity)

    //Anotasi @update untuk memperbarui baris dalam tabel task database hampir mirip dengan @insert,
    // metode @update menerima instance dari entty sebagai parameter untuk memperbarui data task
    @Update
    suspend fun updateTask(entity: Entity)

    //anotasi yang menandakan bahwa methode yang berada dibawahnya berfungsi untuk mendelete data dalam tabel database
    @Delete
    suspend fun deleteTask(entity: Entity)

    //metode @Query dibawah ini untuk menggunakan query sql dalam menghapus data dari tabel to_do di database
    @Query("Delete from to_do")
    //Mendeklarasikan fungsi penangguhan untuk menghapus semua kata
    suspend fun deleteAll()

    //Metode @Query ("Select * from to_do") memungkinkan kita untuk menggunakan Query sql dalam menampilkannya sebagai DAO
    @Query("Select * from to_do")
    // menggunakan suspend function karena kita akan melakukan semua proses pada database secara asynchronous.
    //hal ini diwajibkan untuk room, karena jika tidak menggunakan suspend function akan muncul error saat di run.
    suspend fun getTasks():List<CardInfo>

}