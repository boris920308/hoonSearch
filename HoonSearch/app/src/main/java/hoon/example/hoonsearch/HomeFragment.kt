package hoon.example.hoonsearch

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import hoon.example.hoonsearch.databinding.FragmentHomeBinding
import hoon.example.hoonsearch.network.NaverSearchResponse
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeItem : NaverSearchResponse
    private val viewModel: HomeViewModel by viewModels()
    private val listAdapter = HomeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btn.setOnClickListener {
            Log.d("hoon92", "click btn ")
            viewModel.getSearchResult("book", "kotlin")
        }

        lifecycleScope.launch {
            viewModel.searchFlow.collect {
                Log.d("hoon92", "searchResult = ${it.toString()}")
                listAdapter.submitList(it.items)
            }
        }

        binding.rv.apply {
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
                adapter = listAdapter
            }
        }
    }

}