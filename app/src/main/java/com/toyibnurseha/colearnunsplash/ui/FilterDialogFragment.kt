package com.toyibnurseha.colearnunsplash.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.DialogFragment
import com.toyibnurseha.colearnunsplash.R
import com.toyibnurseha.colearnunsplash.databinding.FragmentFilterDialogBinding
import com.toyibnurseha.colearnunsplash.interfaces.FilterListener
import com.toyibnurseha.colearnunsplash.ui.search.SearchActivity


class FilterDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentFilterDialogBinding

    private var filterColor: String? = null
    private var filterOrientation: String? = null

    companion object {
        const val COLOR_FILTER = "color_filter_data"
        const val ORIENTATION_FILTER = "orientation_filter_data"
        fun newInstance(colorFilter: String?, orientationFilter: String?): FilterDialogFragment {
            val frag = FilterDialogFragment()
            val args = Bundle()
            args.putString(COLOR_FILTER, colorFilter)
            args.putString(ORIENTATION_FILTER, orientationFilter)
            frag.arguments = args
            return frag
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listener: FilterListener = (activity as SearchActivity)

        val args : Bundle? = arguments
        val currentColorFilter = args?.getString(COLOR_FILTER)
        val currentOrientationFilter = args?.getString(ORIENTATION_FILTER)
        if(currentColorFilter == resources.getString(R.string.black_and_white).replace(" ", "_").lowercase()) {
            binding.rgColor.check(R.id.rbBlackAndWhite)
        }
        if(currentOrientationFilter == resources.getString(R.string.landscape)) {
            binding.rgOrientation.check(R.id.rbLandscape)
        }else if(currentOrientationFilter == resources.getString(R.string.portrait)){
            binding.rgOrientation.check(R.id.rbPortrait)
        }

        binding.btnApply.setOnClickListener {
            val colorId = binding.rgColor.checkedRadioButtonId
            filterColor = binding.root.findViewById<RadioButton>(colorId).text.toString()
            val orientationId = binding.rgOrientation.checkedRadioButtonId
            filterOrientation = binding.root.findViewById<RadioButton>(orientationId).text.toString()
            if(filterOrientation == resources.getString(R.string.any)) {
                filterOrientation = null
            }else if(filterColor == resources.getString(R.string.any_color)) {
                filterColor = null
            }
            val formattedColor = filterColor?.replace(" ", "_")?.lowercase()
            listener.finishFilter(formattedColor, filterOrientation)
            dismiss()
        }

        binding.btnClear.setOnClickListener {
            binding.rgColor.check(R.id.rbAnyColor)
            binding.rgOrientation.check(R.id.rbAnyLandscape)
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }

    }

}