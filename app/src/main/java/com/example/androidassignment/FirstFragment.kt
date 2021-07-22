package com.example.androidassignment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FirstFragment : Fragment(R.layout.fragment_first) {



    companion object{
        //image pick code
        private val IMAGE_PICK_CODE = 1000;
        //permission code
        private val PERMISSION_cODE = 1001;
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v =  inflater.inflate(R.layout.fragment_first, container, false)

        //fab on user details fragment
        val fabWorkBtn = v.findViewById<FloatingActionButton>(R.id.fabWork)
        fabWorkBtn.setOnClickListener {
            val fourFragment = FourFragment();
            val transaction : FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.container_fragment, fourFragment)
            transaction.commit()

        }

        //loading image from gallery
        val btnimage = v.findViewById<Button>(R.id.btnCameraLoad);
        btnimage.setOnClickListener {
            pickImageFromGallery();
        }
        return v
    }


    private  fun pickImageFromGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type="image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }






}