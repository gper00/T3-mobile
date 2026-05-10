package com.example.tugas3

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. INISIALISASI VARIABEL (Nangkep ID dari XML)
        val etNama = findViewById<EditText>(R.id.etNama)
        val rgJenisKelamin = findViewById<RadioGroup>(R.id.rgJenisKelamin)
        val cbMembaca = findViewById<CheckBox>(R.id.cbHobiMembaca)
        val cbKoding = findViewById<CheckBox>(R.id.cbHobiKoding)
        val cbOlahraga = findViewById<CheckBox>(R.id.cbHobiOlahraga)
        val cbTidur = findViewById<CheckBox>(R.id.cbHobiTidur)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val tvHasil = findViewById<TextView>(R.id.tvHasil)

        // 2. EVENT LISTENER (Pas tombol diklik)
        btnSubmit.setOnClickListener {
            // Ambil teks dari EditText
            val nama = etNama.text.toString().trim()

            // ==== VALIDASI 1: NAMA KOSONG ====
            // Sesuai instruksi tugas, kalau kosong kasih peringatan
            if (nama.isEmpty()) {
                etNama.error = "Nama tidak boleh kosong"
                Toast.makeText(this, "Nama tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // Berhenti di sini, jangan lanjut ke bawah
            }

            // ==== VALIDASI 2: JENIS KELAMIN ====
            // Ngecek ID RadioButton mana yang lagi kepilih di dalam RadioGroup
            val selectedGenderId = rgJenisKelamin.checkedRadioButtonId

            if (selectedGenderId == -1) {
                // -1 artinya belum ada yang dipilih sama sekali
                Toast.makeText(this, "Pilih jenis kelamin dulu, bro!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // Berhenti di sini
            }

            // Kalau ada yang dipilih, kita cari tahu teksnya (Laki-laki / Perempuan)
            val rbTerpilih = findViewById<RadioButton>(selectedGenderId)
            val gender = rbTerpilih.text.toString()

            // ==== AMBIL DATA CHECKBOX (HOBI) ====
            // Karena hobi bisa banyak, kita tampung di List (Array)
            val hobiList = mutableListOf<String>()

            if (cbMembaca.isChecked) hobiList.add("Membaca")
            if (cbKoding.isChecked) hobiList.add("Koding")
            if (cbOlahraga.isChecked) hobiList.add("Olahraga")
            if (cbTidur.isChecked) hobiList.add("Tidur")

            // Gabungin list hobi jadi satu kalimat dipisah koma (misal: "Koding, Tidur")
            val hobi = if (hobiList.isNotEmpty()) {
                hobiList.joinToString(", ")
            } else {
                "Tidak ada hobi" // Kalau user nggak nyentang satupun
            }

            // ==== TAMPILKAN HASIL ====
            // Cetak ke TextView dengan format yang rapi
            val hasilAkhir = """
                Nama     : $nama
                Kelamin  : $gender
                Hobi     : $hobi
            """.trimIndent()

            tvHasil.text = hasilAkhir
        }
    }
}
