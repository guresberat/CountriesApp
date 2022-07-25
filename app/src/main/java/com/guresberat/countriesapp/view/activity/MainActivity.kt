package com.guresberat.countriesapp.view.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.guresberat.countriesapp.databinding.ActivityMainBinding
import com.guresberat.countriesapp.utils.Resource
import com.guresberat.countriesapp.utils.hide
import com.guresberat.countriesapp.utils.show
import com.guresberat.countriesapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
        subscribeObservers()
    }

    private fun initialize() {
        /*binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = homeRecyclerViewAdapter
            homeRecyclerViewAdapter.itemClickListener = { item, position ->
                val newIntent = Intent(this@MainActivity, ProductDetail::class.java).apply {
                    putExtra("item", item)
                    putExtra("position", position)
                }
                startActivity(newIntent)
            }
        }*/
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
                        //homeRecyclerViewAdapter.items =
                        //  it.value.widgets
                    }
                }
            }
        }
    }
}