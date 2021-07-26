package com.example.androidassignment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidassignment.R
import com.example.androidassignment.model.Results
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.items.view.*

class UserAdapter(var userList: ArrayList<Results>, var onUserClickListener: OnUserClickListener) :
    RecyclerView.Adapter<UserAdapter.ContactsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)

        return ContactsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.userName.text = "${currentItem.name.first} ${currentItem.name.last}"
        holder.userEmail.text = currentItem.email
        holder.userPhoneNo.text = currentItem.phone
        Picasso.get()
            .load(currentItem.picture.large)
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.userImage)


        ViewCompat.setTransitionName(holder.userCallBtn, currentItem.phone)


        holder.userCallBtn.setOnClickListener {
            onUserClickListener.onUserClickListener(currentItem, holder.userCallBtn)
        }
    }

    override fun getItemCount(): Int = userList.size

    class ContactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.user_name
        val userEmail: TextView = itemView.user_email
        val userPhoneNo: TextView = itemView.user_phoneNo
        val userImage: ImageView = itemView.iVProfileImg
        val userCallBtn: Button = itemView.user_CallBtn

    }

    interface OnUserClickListener {
        fun onUserClickListener(results: Results, sharedButton: Button)
    }

}