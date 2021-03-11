package com.example.realworldconduitkotlin.ui.feed

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.api.models.entities.User
import com.example.realworldconduitkotlin.adapters.ArticleFeedRVAdapter
import com.example.realworldconduitkotlin.adapters.FeedViewPagerAdapter
import com.example.realworldconduitkotlin.databinding.FragmentFeedBinding
import com.example.realworldconduitkotlin.extensions.loadImage
import com.example.realworldconduitkotlin.ui.auth.AuthViewModel
import com.example.realworldconduitkotlin.ui.auth.SignIn


class Feed: Fragment() {

    companion object {
        const val PREFS_AUTH = "prefs_auth"
        const val PREFS_KEY_TOKEN = "prefs_key_token"
    }

    private var binding: FragmentFeedBinding? = null
    lateinit var viewModel: FeedViewModel
    lateinit var feedAdapter: ArticleFeedRVAdapter
    private val authViewModel: AuthViewModel by activityViewModels()
    private var isLoggedIn: Boolean = false
    lateinit var sharedPreferences: SharedPreferences
    private var currentUser: User? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        sharedPreferences = requireContext().getSharedPreferences(PREFS_AUTH, Context.MODE_PRIVATE)

        // Inflate the layout for this fragment
        binding = FragmentFeedBinding.inflate(inflater, container, false)

        sharedPreferences.getString(PREFS_KEY_TOKEN, null)?.let {
            authViewModel.getCurrentUser(it)
            isLoggedIn = true
        }

        setUpTabLayout()

        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)

        authViewModel.user.observe({lifecycle}) {
            //setting currentUser object
            currentUser = it
            //if we get user i.e user is logged in
            it?.token?.let { token ->
                //saving token in sharedPreferences
                sharedPreferences.edit {
                    putString(PREFS_KEY_TOKEN, token)
                }
            } ?:  run {
                sharedPreferences.edit {
                    remove(PREFS_KEY_TOKEN)
                }
            }
        }


        binding?.ivUserAvatar?.setOnClickListener {
            if (!isLoggedIn) {
                val loginIntent = Intent(context, SignIn::class.java)
                startActivity(loginIntent)
            }else {
                binding?.ivUserAvatar!!.loadImage(currentUser?.image!!)
            }
        }


        return binding!!.root
    }


    private fun setUpTabLayout() {
        val viewPagerAdapter = FeedViewPagerAdapter(childFragmentManager)
        viewPagerAdapter.addFragment(GlobalFeed(), "Global Feed")
        viewPagerAdapter.addFragment(YourFeed(), "Your Feed")

        binding?.viewPagerFeed?.adapter = viewPagerAdapter

        binding?.tabsFeed?.setupWithViewPager(binding?.viewPagerFeed)
    }


    override fun onDestroy() {
        super.onDestroy()
        //to avoid memory leak when view is destroyed
        binding = null
    }

}