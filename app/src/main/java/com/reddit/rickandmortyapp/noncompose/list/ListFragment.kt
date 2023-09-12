package com.reddit.rickandmortyapp.noncompose.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.reddit.rickandmortyapp.R
import com.reddit.rickandmortyapp.noncompose.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {
    private val listViewModel by viewModels<ListViewModel>()

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        swipeRefreshLayout = view.findViewById<SwipeRefreshLayout?>(R.id.swipeRefreshLayout).apply {
            setOnRefreshListener { listViewModel.fetchCharacters() }
        }

        recyclerView = view.findViewById<RecyclerView?>(R.id.recyclerView).apply {
            adapter = CharacterAdapter()
            layoutManager = LinearLayoutManager(requireContext())
            addOnScrollListener(createLoadMoreListener())
        }

        listViewModel.fetchCharacters()
        initObservers()
    }

    private fun initObservers() {
        listViewModel.characters.observe(viewLifecycleOwner) {
            (recyclerView.adapter as CharacterAdapter).setItems(it)
        }

        listViewModel.moreCharacters.observe(viewLifecycleOwner) {
            (recyclerView.adapter as CharacterAdapter).addItems(it)
        }

        listViewModel.error.observe(viewLifecycleOwner) {
            println(it)
        }

        listViewModel.loading.observe(viewLifecycleOwner) {
            swipeRefreshLayout.isRefreshing = it
        }
    }

    private fun createLoadMoreListener() = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager: LinearLayoutManager =
                recyclerView.layoutManager as LinearLayoutManager
            val visibleItemCount: Int = layoutManager.childCount
            val totalItemCount: Int = layoutManager.itemCount
            val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()

            if (!listViewModel.isPageLoading) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= listViewModel.pageSize
                ) {
                    listViewModel.nextPage()
                }
            }
        }
    }
}