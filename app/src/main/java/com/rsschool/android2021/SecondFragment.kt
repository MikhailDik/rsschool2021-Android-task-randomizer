package com.rsschool.android2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.lang.Exception

class SecondFragment : Fragment() {

    private var backButton: Button? = null
    var result: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)

        val min = arguments?.getInt(MIN_VALUE_KEY) ?: 0
        val max = arguments?.getInt(MAX_VALUE_KEY) ?: 0

        result?.text = generate(min, max).toString()

        backButton?.setOnClickListener {
            try {
                (activity as Exchange)?.firstFragmentOpenEx(result?.text.toString().toInt())
            }
            catch (e: NumberFormatException) {
                Toast.makeText(this.context,"Error!",Toast.LENGTH_SHORT).show()
            }
        // TODO: implement back

        }
    }

    private fun generate(min: Int, max: Int): Int {
        // TODO: generate random number
            return (min..max).random()
        //return 0
    }

    public fun getResult() : Int {

        return try {result?.text.toString().toInt()}   catch(e:Exception) {0}

    }

    companion object {

        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle()
            // TODO: implement adding arguments
            args.putInt(SecondFragment.MIN_VALUE_KEY, min)
            args.putInt(SecondFragment.MAX_VALUE_KEY, max)
            fragment.arguments = args
            return fragment

        }


        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }
}