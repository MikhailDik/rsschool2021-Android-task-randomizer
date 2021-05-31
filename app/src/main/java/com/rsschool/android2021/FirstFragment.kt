package com.rsschool.android2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var minEdit: EditText? = null
    private var maxEdit: EditText? = null




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"
        // TODO: val min = ...
        minEdit = view.findViewById(R.id.min_value)
        // TODO: val max = ...
        maxEdit =  view.findViewById(R.id.max_value)

        generateButton?.setOnClickListener {
            try {
                val min:Int = minEdit?.text.toString().toInt()
                val max:Int = maxEdit?.text.toString().toInt()
                if ((min <= max) && min >= 0 && max >= 0) {
                        (activity as Exchange)?.secondFragmentOpenEx(min, max) }
                else {
                    Toast.makeText(this.context,"Enter correct value!",Toast.LENGTH_SHORT).show()
                }
            }
            catch (e:Exception){
                Toast.makeText(this.context,"Enter correct value!",Toast.LENGTH_SHORT).show()
            }

            // TODO: send min and max to the SecondFragment
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }


        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}