package com.example.fudelo.ui.page.Fragment

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.fudelo.R
import com.example.fudelo.RecipeStep

class StepFragment : Fragment() {
    private lateinit var step: RecipeStep

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        step = requireArguments().getParcelable("step")!!!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.page_step, container, false)
        val text = view.findViewById<TextView>(R.id.stepText)
        val image = view.findViewById<ImageView>(R.id.stepImage)
        text.text = step.text
        if (!step.imageUrl.isNullOrEmpty()) {
            Glide.with(this).load(step.imageUrl).into(image)
        } else image.visibility = View.GONE
        return view
    }

    companion object {
        fun newInstance(step: RecipeStep) = StepFragment().apply {
            arguments = Bundle().apply { putParcelable("step", step) }
        }
    }
}
