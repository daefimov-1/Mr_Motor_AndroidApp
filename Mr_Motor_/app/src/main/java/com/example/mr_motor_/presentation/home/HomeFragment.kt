package com.example.mr_motor_.presentation.home

import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.example.mr_motor_.R
import com.example.mr_motor_.data.sharedPref.SessionManager
import com.example.mr_motor_.domain.models.UserResponse
import com.example.mr_motor_.presentation.AccountPage
import com.example.mr_motor_.presentation.LoginActivity

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

        val sessionManager : SessionManager = SessionManager(requireContext())
        val user : UserResponse? = sessionManager.fetchUser()

        if(user != null){

            if(user.avatar.isNotEmpty()){
                var encoded : String = user.avatar.substring(user.avatar.indexOf(',')+1)

                val decodedString: ByteArray = Base64.decode(encoded, Base64.DEFAULT)
                val bitmap =
                    BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                accountButton?.setImageBitmap(bitmap)
            }

        }

        return view

    }

    override fun onResume() {
        super.onResume()

        val sessionManager : SessionManager = SessionManager(requireContext())
        val user : UserResponse? = sessionManager.fetchUser()

        if(user != null){

            if(user.avatar.isNotEmpty()){
                var encoded : String = user.avatar.substring(user.avatar.indexOf(',')+1)

                val decodedString: ByteArray = Base64.decode(encoded, Base64.DEFAULT)
                val bitmap =
                    BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                accountButton?.setImageBitmap(bitmap)
            }

        }
        else{
            accountButton?.setImageResource(R.drawable.ic_person_circle)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}