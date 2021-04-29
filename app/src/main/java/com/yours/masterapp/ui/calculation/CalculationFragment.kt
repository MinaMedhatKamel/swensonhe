package com.yours.masterapp.ui.calculation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.yours.masterapp.R
import com.yours.masterapp.databinding.FragmentCalculationBinding
import com.yours.masterapp.databinding.FragmentListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CalculationFragment : Fragment() {
    private val viewModel: CalculationViewModel by viewModel()
    private var rate: Float? = null
    private var currency: String? = null
    private val safeArgs: CalculationFragmentArgs by navArgs()
    private var _binding: FragmentCalculationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCalculationBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rate = safeArgs.rate
        currency = safeArgs.currency
        binding.txtCurrencyName.text = currency
        binding.txtCurrencyAmount.text = rate.toString()
        binding.edtCurrencyChange.doAfterTextChanged {
            var value = it.toString()
            if (!value.isNullOrEmpty() && rate != null) {
                binding.txtCurrencyAmount.text =
                    viewModel.getCurrentAmount(rate!!, it.toString().toInt()).toString()
            }
        }

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}