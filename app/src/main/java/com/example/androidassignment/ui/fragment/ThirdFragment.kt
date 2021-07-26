package com.example.androidassignment.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.androidassignment.R
import com.example.androidassignment.SQLiteHelper
import com.example.androidassignment.model.UserModel
import kotlinx.android.synthetic.main.fragment_third.*

class ThirdFragment : Fragment(R.layout.fragment_third) {

    private lateinit var btnAddDetails: Button

    private lateinit var btnViewDetails: Button

    private lateinit var sqLiteHelper: SQLiteHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_third, container, false)
        btnAddDetails = v.findViewById<Button>(R.id.btnAdd)
        btnViewDetails = v.findViewById<Button>(R.id.btnView)
        sqLiteHelper = SQLiteHelper(requireActivity())
        btnAddDetails.setOnClickListener {
            addUser()
            getAllUsersDetails()
        }

        btnViewDetails.setOnClickListener {
            getAllUsersDetails()
            val firstFragment = FirstFragment();
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.container_fragment, firstFragment)
            transaction.commit()
        }


        return v
    }

    private fun getAllUsersDetails() {
        val usrList = sqLiteHelper.getAllUser()
        Log.e("Testing", "${usrList.size}")
    }


    private fun addUser() {
        val name = etUserNameLogin.text.toString()
        val email = etUserEmailLogin.text.toString()
        val phone = etUserPhoneLogin.text.toString()
        val address = etUserAddressLogin.text.toString()

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            Toast.makeText(context, "Please Enter Required Fields", Toast.LENGTH_SHORT).show()
            clearEditText()
        } else {
            val usr = UserModel(name = name, email = email, phone = phone, address = address)
            val status = sqLiteHelper.insertUser(usr)

            //Check insert success or not success
            if (status > -1) {
                Toast.makeText(context, "User Added...", Toast.LENGTH_SHORT).show()
                clearEditText()
                getAllUsersDetails()
            } else {
                Toast.makeText(context, "Record Not Saved", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun clearEditText() {
        etUserNameLogin.setText("")
        etUserEmailLogin.setText("")
        etUserPhoneLogin.setText("")
        etUserAddressLogin.setText("")
        etUserNameLogin.requestFocus()
    }



}