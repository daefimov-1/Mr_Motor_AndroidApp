package com.example.mr_motor_.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.example.mr_motor_.R
import com.example.mr_motor_.login.SessionManager
import com.example.mr_motor_.ui.AccountPage
import com.example.mr_motor_.ui.LoginActivity

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private var accountButton : ImageButton? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View? = inflater.inflate(R.layout.home_fragment, container, false)
        accountButton = view?.findViewById<ImageButton>(R.id.ib_profile)
        accountButton?.setOnClickListener {

            val sessionManager : SessionManager = SessionManager(requireContext())
            Log.d("CHECK_HAS_TOKEN", sessionManager.fetchAuthToken().toString())
            if(sessionManager.fetchAuthToken() == null) {
                LoginActivity.start(activity)
            }
            else {
                AccountPage.start(activity)
            }

        }
        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}