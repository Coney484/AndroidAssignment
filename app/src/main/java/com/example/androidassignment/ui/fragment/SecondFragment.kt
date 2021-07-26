package com.example.androidassignment.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.androidassignment.R
import com.example.androidassignment.data.ApiClient
import com.example.androidassignment.data.ApiService
import com.example.androidassignment.gone
import com.example.androidassignment.model.Results
import com.example.androidassignment.model.UserResponse
import com.example.androidassignment.ui.adapter.UserAdapter
import com.example.androidassignment.visible
import kotlinx.android.synthetic.main.fragment_seond.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondFragment : Fragment(R.layout.fragment_seond), UserAdapter.OnUserClickListener {

    private val apiClient: ApiService by lazy { ApiClient.getApiClient() }
    private lateinit var usersadapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_seond, container, false)

        val recycler_view_P = v.findViewById<RecyclerView>(R.id.recycler_view)
        val swiperefresh_P = v.findViewById<SwipeRefreshLayout>(R.id.swiperefresh)
        val progressBar_P = v.findViewById<ProgressBar>(R.id.progressBar)
        recycler_view_P.layoutManager = LinearLayoutManager(context)
        recycler_view_P.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        progressBar_P.visible()
        recycler_view_P.gone()
        getUsers()

        swiperefresh_P.setOnRefreshListener { getUsers() }

        return v
    }


    private fun getUsers() {
        apiClient.getUsers(20).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    usersadapter = UserAdapter(response.body()?.userList!!, this@SecondFragment)
                    recycler_view.adapter = usersadapter
                    progressBar.gone()
                    recycler_view.visible()
                    swiperefresh.isRefreshing = false
//                    Log.e("Success", response.body().toString())
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("failure", t.localizedMessage)
            }

        })
    }

    override fun onUserClickListener(results: Results, sharedButton: Button) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.setData(android.net.Uri.parse("tel: $sharedButton"))
        startActivity(intent)    }


}