package com.aster.app.weather.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.aster.app.weather.R
import com.aster.app.weather.di.component.FragmentComponent
import com.aster.app.weather.ui.base.BaseFragment
import com.aster.app.weather.utils.common.Status
import com.aster.app.weather.utils.display.Toaster
import com.example.nested_recycler_view.HomeAdapter
import kotlinx.android.synthetic.main.fragment_forecast.*
import javax.inject.Inject

class HomeFragment : BaseFragment<HomeViewModel>() {


    companion object {

        const val TAG = "HomeFragment"

        fun newInstance(): HomeFragment {
            val args = Bundle()
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }


    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    val adapter = HomeAdapter()

    override fun provideLayoutId(): Int = R.layout.fragment_forecast

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupObservers() {
        super.setupObservers()

        //observe the data
        viewModel.getWeatherForecast().observe(this, Observer {
            Log.d(TAG, "Observer for weather forecast")
            if (it.status == Status.SUCCESS) {
                Log.d(TAG, "Data fetched successfully for weather forecast")
                val weatherList = it.data
                weatherList?.let { it1 -> adapter.updateData(it1) }
                adapter.notifyDataSetChanged()
            } else if(it.status == Status.ERROR){
                Log.d(TAG, "Data fetched failed for weather forecast ${it.status}")
               // Toast.makeText(activity,"Something went wrong! Please try again!",Toast.LENGTH_LONG).show()
                Toaster.show(activity!!.applicationContext,"Something went wrong! Please try again!")
            }

        })
    }

    override fun setupView(view: View) {

        rv_weatherForecast.layoutManager = LinearLayoutManager(activity)
        rv_weatherForecast.adapter = adapter

    }

}