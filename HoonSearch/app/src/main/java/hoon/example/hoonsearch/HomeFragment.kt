package hoon.example.hoonsearch

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import hoon.example.hoonsearch.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment(){
    private var _binding: FragmentHomeBinding? = null
    val binding get() = _binding!!

    val viewModel: HomeViewModel by viewModels()
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
            }
        }
    }

}