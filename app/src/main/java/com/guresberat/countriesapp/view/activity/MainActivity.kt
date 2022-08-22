package com.guresberat.countriesapp.view.activity

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.guresberat.countriesapp.R
import com.guresberat.countriesapp.data.model.Country
import com.guresberat.countriesapp.databinding.ActivityMainBinding
import com.guresberat.countriesapp.utils.Resource
import com.guresberat.countriesapp.utils.hide
import com.guresberat.countriesapp.utils.show
import com.guresberat.countriesapp.view.adapter.HomeAdapter
import com.guresberat.countriesapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.internal.Contexts


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val homeAdapter = HomeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
        subscribeObservers()
    }

    private fun initialize() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter
            homeAdapter.itemClickListener = { item ->
                showDialog(item)
            }
        }

        val sharedPreference = Contexts.getApplication(applicationContext).getSharedPreferences(
            "time",
            Context.MODE_PRIVATE
        )

        viewModel.fetchData(sharedPreference)
    }

    private fun showDialog(item: Country) {
        val customDialog = Dialog(this)
        customDialog.setContentView(R.layout.dialog_layout)
        customDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        Glide.with(this).load(item.url).into(customDialog.findViewById(R.id.flagImageView))
        customDialog.findViewById<ImageButton>(R.id.closeButton).setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.findViewById<TextView>(R.id.countryName).text = item.name
        customDialog.findViewById<TextView>(R.id.countryDesc).text = item.desc

        customDialog.show()
    }

    private fun subscribeObservers() {
        lifecycleScope.launchWhenCreated {
            viewModel.countriesSharedFlow.collect {
                when (it) {
                    is Resource.Failure -> {
                        binding.progressBar.hide()
                        Log.e("initaldata", "Inital data failure")
                    }
                    is Resource.Loading -> binding.progressBar.show()
                    is Resource.Success -> {
                        binding.progressBar.hide()
                        homeAdapter.items = it.value
                    }
                }
            }
        }
    }
}