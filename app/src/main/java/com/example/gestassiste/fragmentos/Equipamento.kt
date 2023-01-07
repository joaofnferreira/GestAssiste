package com.example.gestassiste.fragmentos

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
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


class Equipamento : Fragment() {


    lateinit var button1: Button
    lateinit var imageView: ImageView

    ///////////////////////////////////////////////////////////////

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        var view:View = inflater.inflate(R.layout.fragment_equipamento, container, false)

        button1 = view.findViewById(R.id.button1)
        imageView = view.findViewById(R.id.imageView)

        button1.setOnClickListener {
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




///////////////////////////////////////////////////////////////////////////////////



//////////////////////////////////////////////////////////////////////////////////////







   }