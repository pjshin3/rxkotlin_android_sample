package com.example.mygooglebook.bind

import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.databinding.adapters.TextViewBindingAdapter
import com.arlib.floatingsearchview.FloatingSearchView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.mygooglebook.Seach.*
import com.example.mygooglebook.util.toHumanResponse
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view:ImageView, imageUrl: String?){
    if (!imageUrl.isNullOrEmpty()){
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("isGone")
fun bindIsGone(view: View,isGone: Boolean){
    view.visibility = if (isGone){
        View.GONE
    }else{
        View.VISIBLE
    }
}

@BindingAdapter("on_change")
fun FloatingSearchView.listnerTextOnChange(
        listner : FloatingSearchView.OnQueryChangeListener
): Unit = setOnQueryChangeListener(listner)

@BindingAdapter("state_change")
fun FloatingSearchView.bindSuggestion(data: QueryViewState<String>?): Unit? = data?.let { state ->
    toggleProgress(state.Loading)
    state.error.fold({
        state.Result.map { QuerySearchSuggestion.ResultSuggestion(it) }
    },{
        listOf(QuerySearchSuggestion.ErrorSuggestion(it.toHumanResponse()))
    }).let { swapSuggestions(it) }
}

private fun FloatingSearchView.toggleProgress(show: Boolean): Unit = when(show){
    true -> showProgress()
    false -> hideProgress()
}