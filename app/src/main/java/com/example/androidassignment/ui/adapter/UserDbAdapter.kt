package com.example.androidassignment.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.example.androidassignment.R
import com.example.androidassignment.model.UserModel


class UserdbAdapter(context : Context,var usrList: ArrayList<UserModel> = ArrayList()) : RecyclerView.Adapter<UserdbAdapter.UserdbViweHolder>() {


//    private var usrList: ArrayList<UserModel> = ArrayList()
//    private var onClickItem: ((UserModel)->Unit)? = null

    fun addItems(items: ArrayList<UserModel>){
        this.usrList = items
        notifyDataSetChanged()
    }

//    fun setOnClick(callback: (UserModel) -> Unit){
//        this.onClickItem = callback
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserdbViweHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_items_usr_frag1, parent, false)
    )

    override fun onBindViewHolder(holder: UserdbViweHolder, position: Int) {
        var usr = usrList[position]
        holder.bindView(usr)
//        holder.itemView.setOnClickListener { onClickItem?.invoke(usr) }
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


        fun bindView(usr: UserModel) {
            id.text = usr.id.toString()
            name.text = usr.name
            email.text = usr.email
            phone.text = usr.phone
            address.text = usr.address
        }
    }

}