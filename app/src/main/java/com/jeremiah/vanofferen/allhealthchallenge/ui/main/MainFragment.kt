package com.jeremiah.vanofferen.allhealthchallenge.ui.main

import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import com.jeremiah.vanofferen.allhealthchallenge.R
import java.io.IOException

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var resultView: AppCompatImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        view.findViewById<AppCompatImageView>(R.id.color_red).setOnClickListener {
            viewModel.onRedClicked()
        }

        view.findViewById<AppCompatImageView>(R.id.color_yellow).setOnClickListener {
            viewModel.onYellowClicked()
        }

        view.findViewById<AppCompatImageView>(R.id.color_blue).setOnClickListener {
            viewModel.onBlueClicked()
        }

        view.findViewById<AppCompatImageView>(R.id.color_green).setOnClickListener {
            viewModel.onGreenClicked()
        }

        view.findViewById<AppCompatImageView>(R.id.shape_rectangle).setOnClickListener {
            viewModel.onRectangleClicked()
        }

        view.findViewById<AppCompatImageView>(R.id.shape_oval).setOnClickListener {
            viewModel.onOvalClicked()
        }

        view.findViewById<AppCompatImageView>(R.id.shape_triangle).setOnClickListener {
            viewModel.onTriangleClicked()
        }

        view.findViewById<AppCompatImageView>(R.id.shape_star).setOnClickListener {
            viewModel.onStarClicked()
        }

        resultView = view.findViewById(R.id.result)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.result.observe(viewLifecycleOwner) { result ->
            result?.let { imageUri ->
                try {
                    val imageStream = context?.assets?.open(imageUri)
                    val drawable = Drawable.createFromStream(imageStream, null)
                    resultView.setImageDrawable(drawable)
                    resultView.visibility = View.VISIBLE
                } catch (ex: IOException) {
                    resultView.visibility = View.INVISIBLE
                }
            } ?: run { resultView.visibility = View.INVISIBLE }
        }
    }

}