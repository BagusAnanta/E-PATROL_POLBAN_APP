package com.annas.e_patrolpolbanapp.view.qrgenerate

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.annas.e_patrolpolbanapp.R
import com.annas.e_patrolpolbanapp.view.login.PagePimpinan
import com.annas.e_patrolpolbanapp.view.main.MainActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder

class QrGenerate : AppCompatActivity() {
    lateinit var ImageQr : ImageView
    lateinit var InputLokasi : TextInputEditText
    lateinit var InputArea : TextInputEditText
    lateinit var ButtonQr : Button
    lateinit var ButtonPrint : Button
    val multi = MultiFormatWriter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_generate)

        ImageQr = findViewById(R.id.imageQr)
        InputLokasi = findViewById(R.id.inputNamaLokasi)
        InputArea = findViewById(R.id.inputArea)
        ButtonQr = findViewById(R.id.btnQrCode)
        ButtonPrint = findViewById(R.id.btnCetak)

        ButtonQr.setOnClickListener { generateQrCode() }
        ButtonPrint.setOnClickListener {
            Toast.makeText(applicationContext,"Print QR Code",Toast.LENGTH_SHORT).show()
            val intent = Intent(this@QrGenerate,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun generateQrCode(){
        var data : String?
        try{
            var locationdata = InputLokasi.text.toString()
            var areadata = InputArea.text.toString()
            data = locationdata +""+ areadata

            val bitmatrix : BitMatrix = multi.encode(data,BarcodeFormat.QR_CODE,300,300)
            val barcodeEncoder = BarcodeEncoder()
            val bitmap : Bitmap = barcodeEncoder.createBitmap(bitmatrix)
            ImageQr.setImageBitmap(bitmap)

        } catch (E : WriterException){
            Toast.makeText(applicationContext,E.localizedMessage,Toast.LENGTH_SHORT).show()
        }
    }
}