package com.example.gestassiste.fragmentos

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.gestassiste.R
import com.google.firebase.database.DatabaseReference
import android.widget.EditText
import com.google.firebase.database.FirebaseDatabase


class Equipamento : Fragment() {


    lateinit var photo_button: Button
    lateinit var imageView: ImageView

    private lateinit var cequipamento: EditText
    private lateinit var cmodelo: EditText
    private lateinit var cserial: EditText

    private lateinit var dbRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?



    ): View? {
        // Inflate the layout for this fragment
        var view:View = inflater.inflate(R.layout.fragment_equipamento, container, false)

        cequipamento = view.findViewById(R.id.cequipamento)
        cmodelo = view.findViewById(R.id.cmodelo)
        cserial = view.findViewById(R.id.cserial)

        dbRef = FirebaseDatabase.getInstance().getReference("Assist")

        photo_button = view.findViewById(R.id.photo_button)
        imageView = view.findViewById(R.id.imageView)

        photo_button.setOnClickListener {
            capturePhoto()

        }
        return view

    }



    ///////////////////////////////////////////////////////
    fun capturePhoto() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncher.launch(cameraIntent)
    }
        var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                imageView.setImageBitmap(data?.extras?.get("data") as Bitmap)



            }

        }

    val contentValues = ContentValues().apply {
        //put(MediaStore.MediaColumns.DISPLAY_NAME)
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
        }
    }

                companion object {
                    private const val TAG = "CameraXApp"
                    private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
                    private const val REQUEST_CODE_PERMISSIONS = 10
                    private val REQUIRED_PERMISSIONS =
                        mutableListOf (
                            Manifest.permission.CAMERA).apply {
                            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                                add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            }
                        }.toTypedArray()
                }


}


