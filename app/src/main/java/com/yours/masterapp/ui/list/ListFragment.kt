package com.yours.masterapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yours.masterapp.R
import com.yours.masterapp.data.CurrencyItem
import com.yours.masterapp.databinding.FragmentListBinding
import com.yours.masterapp.ui.calculation.CalculationFragment
import com.yours.masterapp.utils.serializeToMap
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListFragment : Fragment(), ItemClickable {
    private val listViewModel: ListViewModel by viewModel()
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ListAdapter
    private lateinit var curencyList: List<CurrencyItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //(activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        binding.imgError.setOnClickListener {

        }
        observeViewStatusChanges()
        listViewModel.getList()

    }

    private fun observeViewStatusChanges() {
        listViewModel.viewState.currencyListDataState.observe(
            this.viewLifecycleOwner, Observer {
                it.currencyRates

                binding.mainProgress.visibility = if (it.showProgress) View.VISIBLE else View.GONE
                if (it.error != null && !it.error.consumed)
                    it.error.consume()?.let { errorResource ->
                        binding.lnError.visibility = View.VISIBLE
                        binding.txtError.text = getString(errorResource)
                    }

                if (it.currencyRates != null && !it.currencyRates.consumed) {
                    it.currencyRates.consume()?.let {
                        linearLayoutManager = LinearLayoutManager(requireContext())
                        binding.recyclerView.layoutManager = linearLayoutManager
                        val map = it.rates.serializeToMap()
                        curencyList = map.map {
                            CurrencyItem(it.key, it.value.toString())
                        }
                        adapter = ListAdapter(curencyList, this)
                        binding.recyclerView.adapter = adapter
                    }

                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(item: CurrencyItem) {
        val action = ListFragmentDirections.actionFirstFragmentToSecondFragment(
            item.value.toFloat(),
            item.name
        )
        findNavController().navigate(action)
    }
}