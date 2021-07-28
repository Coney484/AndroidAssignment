package com.example.androidassignment.ui.fragment

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidassignment.R
import com.example.androidassignment.SQLiteHelper
import com.example.androidassignment.model.UserModel
import com.example.androidassignment.ui.adapter.UserdbAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.card_items_usr_frag1.view.*
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment(R.layout.fragment_first) {


    private lateinit var sqLiteHelper: SQLiteHelper
    private lateinit var recycler_view_DB: RecyclerView
    private lateinit var userdbAdapter: UserdbAdapter
    private lateinit var usrList: ArrayList<UserModel>

//    private  var usrList: UserModel? = null
    //image pick code
    private val IMAGE_PICK_CODE = 1000;

    //permission code
    private val PERMISSION_cODE = 1001;


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_first, container, false)
        sqLiteHelper = SQLiteHelper(requireActivity())
        usrList = sqLiteHelper.getAllUser()
        recycler_view_DB = v.findViewById(R.id.recycler_view_frag1)
        recycler_view_DB.setHasFixedSize(true)
        recycler_view_DB.layoutManager = LinearLayoutManager(context)
        userdbAdapter = UserdbAdapter(context!!,usrList)
        recycler_view_DB.adapter = userdbAdapter

        //fab on user details fragment
        val fabWorkBtn = v.findViewById<FloatingActionButton>(R.id.fabWork)


        userdbAdapter.setOnClick {
//            Toast.makeText(context,it.phone, Toast.LENGTH_SHORT).show()

            val fourFragment = FourFragment();
            val bundle = Bundle()
            bundle.putString("name", it.name)
            bundle.putString("email", it.email)
            bundle.putString("phone", it.phone)
            bundle.putString("address", it.address)
            fourFragment.arguments = bundle
//            usrList = it
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.container_fragment, fourFragment)
            transaction.commit()

        }

        //loading image from gallery
        loadImageFromGallery(v)
        return v
    }


    //FAB Button that takes to Fragment 4
    private fun fraOnetoFraFourFAB(fabWorkBtn: FloatingActionButton) {
        fabWorkBtn.setOnClickListener {

            val fourFragment = FourFragment();
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.container_fragment, fourFragment)
            transaction.commit()

        }
    }


    //Load Image From Gallery
    private fun loadImageFromGallery(v: View) {
        val btnimage = v.findViewById<Button>(R.id.btnCameraLoad);
        btnimage.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (context?.checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    //permission denied
                    val permission: Array<String> =
                        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    //show pop up to request runtime permission
                    requestPermissions(permission, PERMISSION_cODE)
                } else {
                    pickImageFromGallery();
                }
            } else {
                //system OS is < Marshmallow
                pickImageFromGallery();
            }

        }
    }

    //Pick Image from Gallery
    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)

    }


    //Handling Gallery Permission
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PERMISSION_cODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery()
                } else {
                    Toast.makeText(context, "PERMISSION DENIED", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    // Displaying Image in Imageview from Gallery
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            imgSelect.setImageURI(data?.data)
        }
    }
}