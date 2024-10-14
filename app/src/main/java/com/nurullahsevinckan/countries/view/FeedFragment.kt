package com.nurullahsevinckan.countries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.nurullahsevinckan.countries.R
import com.nurullahsevinckan.countries.adapter.CountryAdapter
import com.nurullahsevinckan.countries.viewmodel.FeedViewModel


class FeedFragment : Fragment() {

    private lateinit var viewModel : FeedViewModel
    private lateinit var countryAdapter : CountryAdapter
    private lateinit var recyclerView : RecyclerView
    private lateinit var errorView : TextView
    private lateinit var loadingBar : ProgressBar
    private lateinit var refreshLayout : SwipeRefreshLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.countryList)
        errorView = view.findViewById(R.id.countryError)
        loadingBar = view.findViewById(R.id.countryLoading)
        refreshLayout = view.findViewById(R.id.swipeRefreshLayout)

        countryAdapter = CountryAdapter(arrayListOf())
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = countryAdapter
        viewModel = ViewModelProvider(this)[FeedViewModel::class.java]
        viewModel.refreshData()

        observeListData()

        refreshLayout.setOnRefreshListener {
            recyclerView.visibility = View.GONE
            errorView.visibility = View.GONE
            loadingBar.visibility = View.VISIBLE
            viewModel.refreshData()
            refreshLayout.isRefreshing = false
        }




    }

    private fun observeListData(){
        // here we use viewLifecycleOwner instead of "this" keyword. Because Google recommend it
        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->

            countries?.let {
                recyclerView.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }

        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer {error ->
            error?.let{
                if(it){
                errorView.visibility = View.VISIBLE
                }else{
                    errorView.visibility = View.GONE
                }
            }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer {loading->
            loading?.let{
                if(it){
                    loadingBar.visibility = View.VISIBLE
                    errorView.visibility = View.GONE
                    recyclerView.visibility = View.GONE

                }else
                {
                    loadingBar.visibility = View.GONE
                }
            }
        })
    }
}
