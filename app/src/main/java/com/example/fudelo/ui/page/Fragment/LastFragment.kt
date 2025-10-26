package com.example.fudelo.ui.page.Fragment

import android.os.Bundle
import android.view.*
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fudelo.R
import com.example.fudelo.ui.Recipe

class LastFragment : Fragment() {
    private lateinit var recipe: Recipe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipe = requireArguments().getParcelable("recipe")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.page_finish, container, false).apply {

        findViewById<TextView>(R.id.finishText).text =
            "Рецепт завершён!\nСложность: ${"★".repeat(recipe.difficulty)}"

        val webView = findViewById<WebView>(R.id.videoView)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        recipe.videoUrl?.let { url -> webView.loadUrl(url) }

        findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.closeButton)
            .setOnClickListener { requireActivity().finish() }
    }

    companion object {
        fun newInstance(recipe: Recipe) = LastFragment().apply {
            arguments = Bundle().apply { putParcelable("recipe", recipe) }
        }
    }
}
