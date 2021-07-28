package com.example.androidassignment.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.androidassignment.R
import com.example.androidassignment.SQLiteHelper
import com.example.androidassignment.model.UserModel
import com.example.androidassignment.ui.adapter.UserdbAdapter
import kotlinx.android.synthetic.main.fragment_four.*
import kotlinx.android.synthetic.main.fragment_third.*

class FourFragment : Fragment(R.layout.fragment_four) {


    private lateinit var btnUpdateDetails: Button

    private lateinit var sqLiteHelper: SQLiteHelper

    private lateinit var usrList: ArrayList<UserModel>

    private var usr: UserModel? = null


    private lateinit var etNameUpdate: EditText
    private lateinit var etEmailUpdate: EditText
    private lateinit var etPhoneUpdate: EditText
    private lateinit var etAddressUpdate: EditText


    private var userdbAdapter: UserdbAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_four, container, false)
//        var etNameUpdate = v.findViewById<TextView>(R.id.etUserNameUpdate) //1st way make this textview in layout
        etNameUpdate = v.findViewById(R.id.etUserNameUpdate) //2nd way

        etEmailUpdate = v.findViewById(R.id.etUserEmailUpdate)

        etPhoneUpdate = v.findViewById(R.id.etUserPhoneUpdate)

        etAddressUpdate = v.findViewById(R.id.etUserAddressUpdate)

        val args = this.arguments
//        val inputName = args?.get("name")  //1st way
        val inputName = args?.getString("name") //2nd way
        val inputEmail = args?.getString("email")
        val inputPhone = args?.getString("phone")
        val inputAddress = args?.getString("address")
//        etNameUpdate.text= inputName.toString() // 1st way

        etNameUpdate?.setText(inputName) //2nd way

        etEmailUpdate?.setText(inputEmail)

        etPhoneUpdate?.setText(inputPhone)

        etAddressUpdate?.setText(inputAddress)



        btnUpdateDetails = v.findViewById(R.id.btnUpdate)

//        userdbAdapter?.setOnClick {
//            etUserNameUpdate.setText(it.name)
//            etUserEmailUpdate.setText(it.email)
//            etUserPhoneUpdate.setText(it.phone)
//            etUserAddressUpdate.setText(it.address)
//            usr = it
//
//        }

        btnUpdateDetails.setOnClickListener {

            updateUser()
        }


        return v
    }

    private fun updateUser() {
        val name = etNameUpdate.text.toString()
        val email = etEmailUpdate.text.toString()
        val phone = etPhoneUpdate.text.toString()
        val address = etAddressUpdate.text.toString()
        Log.e("tesitng", "Instide Update User Function")

        if (name == usr?.name && email == usr?.email && phone == usr?.phone && address == usr?.address) {
            Toast.makeText(context, "Record not changed...", Toast.LENGTH_SHORT).show()
            return
        }

        if (usr == null) {
            Toast.makeText(context,"Nothing Happening",Toast.LENGTH_SHORT).show()
            return
        }

        val usr =
            UserModel(id = usr!!.id, name = name, email = email, phone = phone, address = address)

        val status = sqLiteHelper.updateUser(usr)

        if (status > -1) {
            Toast.makeText(context, "Record Updated...", Toast.LENGTH_SHORT).show()
            clearEditText()
            getAllUsersDetails()
        } else {
            Toast.makeText(context, "Updatation Failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearEditText() {
        etUserNameLogin.setText("")
        etUserEmailLogin.setText("")
        etUserPhoneLogin.setText("")
        etUserAddressLogin.setText("")
        etUserNameLogin.requestFocus()
    }

    private fun getAllUsersDetails() {
        val usrList = sqLiteHelper.getAllUser()
        Log.e("Testing", "${usrList.size}")
    }


}