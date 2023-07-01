package com.annas.e_patrolpolbanapp.view.reportArea

import android.Manifest
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaScannerConnection
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.annas.e_patrolpolbanapp.R
import com.annas.e_patrolpolbanapp.view.absen.AbsenActivity
import com.annas.e_patrolpolbanapp.view.main.MainActivity
import com.google.android.gms.auth.api.signin.internal.Storage
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class ReportAreaActivity : AppCompatActivity() {
    lateinit var takeCameraImage: ImageView
    lateinit var kejadianInput: EditText
    lateinit var submitData: Button

    lateinit var rootNode: FirebaseDatabase
    lateinit var reference: DatabaseReference

    private val PIC_ID = 123
    private val REQUEST_CAMERA_CODE = 124
    private val REQUEST_IMAGE_CAPTURE = 125
    private var Uidcode = 1

    lateinit var filepath: Uri
    lateinit var firebaseStorage: FirebaseStorage
    lateinit var storageReference: StorageReference
    val GenerateUUID = UUID.randomUUID().toString()
    var fileURI: Uri? = null

    //for imagePath
    lateinit var currentPhotoPath : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_area)

        takeCameraImage = findViewById(R.id.imageKejadian)
        kejadianInput = findViewById(R.id.inputDeskripsi)
        submitData = findViewById(R.id.btnInputReport)

        // check permission
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // if permission is not gratted
            ActivityCompat.requestPermissions(
                this@ReportAreaActivity,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CAMERA_CODE
            )
        }

        takeCameraImage.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, PIC_ID)
        }

        // Button onClick intent into submit data
        submitData.setOnClickListener {
            rootNode = FirebaseDatabase.getInstance()
            reference = rootNode.getReference("UserReported")

            // get data
            Uidcode++
            val convert_Uid = Uidcode.toString()
            val photo_path = GenerateUUID
            val deskripsi = kejadianInput.text.toString()
            val isSafe = "Not Safe"

            // input into data class
            val firebaseDataClass = FireBaseDataClassNoSafe(photo_path, deskripsi, isSafe)

            // setValue in here
            reference.child(convert_Uid).setValue(firebaseDataClass)

            val intent = Intent(ReportAreaActivity@this, MainActivity::class.java)
            startActivity(intent)
            finish()

            Toast.makeText(this@ReportAreaActivity,"Upload Data berhasil",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PIC_ID && resultCode == RESULT_OK) {
            if (data != null) {
                onCaptureImage(data)
            }
        }
    }

    private fun onCaptureImage(data : Intent){
        val photo: Bitmap = data.extras?.get("data") as Bitmap
        val byteOutputStream = ByteArrayOutputStream()
        photo.compress(Bitmap.CompressFormat.JPEG,90,byteOutputStream)
        val bytedata = byteOutputStream.toByteArray()
        takeCameraImage.setImageBitmap(photo)
        uploadImageData(bytedata)
    }

    private fun uploadImageData(byteData : ByteArray){
        // generate from UUID
        val storagereference : StorageReference = storageReference.child("patroliImage/${GenerateUUID}.jpg")
        storagereference.putBytes(byteData)
            .addOnSuccessListener {
                // onSuccesfully
                Toast.makeText(this@ReportAreaActivity,"Foto Berhasil Diupload",Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                // onFailed
                Toast.makeText(this@ReportAreaActivity, "Foto Gagal DiUpload, Silahkan Ulangi Lagi", Toast.LENGTH_SHORT).show()
            }
    }

    @Throws(IOException::class)
    private fun createImageFile() : File {
        // Create image file name
        val timeStamp : String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir : File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    private fun dispachTakePictureIntent(){
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                // create file where camera active
                val photoFile : File? = try {
                    createImageFile()
                } catch (E : IOException){
                    // onException Data
                    Log.e("Create File Photo", E.printStackTrace().toString())
                    null
                }
                // continue if a file succesfull create
                photoFile?.also {
                    val photoURI : Uri = FileProvider.getUriForFile(
                        this,
                        "com.annas.e_patrolpolbanapp.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    private fun setPic(){
        val getWidth : Int = takeCameraImage.width
        val getHeight : Int = takeCameraImage.height

        val bitmapOption = BitmapFactory.Options().apply {
            inJustDecodeBounds = true

            // BitmapFactory.decodeFile(currentPhotoPath, bitmapOption)

            val photoWidth : Int = outWidth
            val photoheight : Int = outHeight

            val scaleFactor : Int = Math.max(1, Math.min(photoWidth/getWidth, photoheight/getHeight))

            inJustDecodeBounds = false
            inSampleSize = scaleFactor
            inPurgeable = true
        }

        BitmapFactory.decodeFile(currentPhotoPath, bitmapOption)?.also { bitmap ->  
            takeCameraImage.setImageBitmap(bitmap)
        }


    }

    private fun galleryPicAdd(){
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also {mediaScanIntent ->
            val file = File(currentPhotoPath)
            mediaScanIntent.data = Uri.fromFile(file)
            sendBroadcast(mediaScanIntent)
        }
    }
}
