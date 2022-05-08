package com.example.mr_motor_.presentation.home

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.example.mr_motor_.R
import com.example.mr_motor_.domain.models.UserResponse
import com.example.mr_motor_.domain.repository.UserRepository
import com.example.mr_motor_.presentation.AccountPage
import com.example.mr_motor_.presentation.LoginActivity
import com.example.mr_motor_.presentation.MainActivity
import org.koin.android.ext.android.inject


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private var accountButton : ImageButton? = null
    private var carButton : View? = null
    private var racerButton : View? = null
    private var competitionButton : View? = null

    private lateinit var user : UserResponse

    private val userRepository by inject<UserRepository>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View? = inflater.inflate(R.layout.home_fragment, container, false)
        accountButton = view?.findViewById<ImageButton>(R.id.ib_profile)
        accountButton?.setOnClickListener {
            if(userRepository.getAuthToken() == "null") {
                LoginActivity.start(activity)
            }
            else {
                AccountPage.start(activity)
            }

        }

        competitionButton = view?.findViewById<View>(R.id.v_competitions)
        competitionButton?.setOnClickListener {
            (activity as MainActivity?)!!.onNavigationItemSelected2(1)
        }

        racerButton = view?.findViewById<View>(R.id.v_racers)
        racerButton?.setOnClickListener {
            (activity as MainActivity?)!!.onNavigationItemSelected2(2)
        }

        carButton = view?.findViewById<View>(R.id.v_cars)
        carButton?.setOnClickListener {
            (activity as MainActivity?)!!.onNavigationItemSelected2(3)
        }

        if(userRepository.getUserData() != null){

            user = userRepository.getUserData()!!

            if(user.avatar.isNotEmpty()){
                val encoded : String = user.avatar.substring(user.avatar.indexOf(',')+1)

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

        if(userRepository.getUserData() != null){

            user = userRepository.getUserData()!!

            if(user.avatar.isNotEmpty()){
                val encoded : String = user.avatar.substring(user.avatar.indexOf(',')+1)

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

}