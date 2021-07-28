package com.example.androidassignment.ui.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.example.androidassignment.R
import com.example.androidassignment.model.UserModel
import com.example.androidassignment.ui.fragment.FourFragment
import kotlinx.android.synthetic.main.card_items_usr_frag1.view.*
import kotlinx.android.synthetic.main.fragment_third.view.*
import kotlinx.android.synthetic.main.items.view.*



class UserdbAdapter(context: Context, var usrList: ArrayList<UserModel> = ArrayList()) :
    RecyclerView.Adapter<UserdbAdapter.UserdbViweHolder>() {


    //    private var usrList: ArrayList<UserModel> = ArrayList()
    private var onClickItem: ((UserModel) -> Unit)? = null

    fun addItems(items: ArrayList<UserModel>) {
        this.usrList = items
        notifyDataSetChanged()
    }

    fun setOnClick(callback: (UserModel) -> Unit) {
        this.onClickItem = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserdbViweHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_items_usr_frag1, parent, false)
    )

    override fun onBindViewHolder(holder: UserdbViweHolder, position: Int) {
        var usr = usrList[position]
        holder.bindView(usr)
        holder.itemView.setOnClickListener {
            onClickItem?.invoke(usr)

            val bundle = Bundle()
            bundle.putString("name", it.tvUserName.toString())
            bundle.putString("email", it.tvUsrEmail.toString())
            bundle.putString("phone", it.tvUsrPhone.toString())
            bundle.putString("name", it.tvUsrAddress.toString())
            val fragment  = FourFragment()
            fragment.arguments = bundle
        }
    }

    override fun getItemCount(): Int {
        return usrList.size
    }


    class UserdbViweHolder(var view: View) : RecyclerView.ViewHolder(view) {
        private var id = view.findViewById<TextView>(R.id.tvID)
        private var name = view.findViewById<TextView>(R.id.tvUserName)
        private var email = view.findViewById<TextView>(R.id.tvUsrEmail)

        private var phone = view.findViewById<TextView>(R.id.tvUsrPhone)
        private var address = view.findViewById<TextView>(R.id.tvUsrAddress)
        private var btnDelete = view.findViewById<Button>(R.id.btnUsrDelete)
        private var layoutrv = view.findViewById<LinearLayout>(R.id.layout_rv)


        fun bindView(usr: UserModel) {
            id.text = usr.id.toString()
            name.text = usr.name
            email.text = usr.email
            phone.text = usr.phone
            address.text = usr.address
        }
    }

    interface FragmentCommunication {
        fun respond(userModel: UserModel)
    }
}

