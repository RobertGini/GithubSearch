package com.example.githubsearch.presentation.utils

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Context.toast(resId: String) =
    Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()

fun Fragment.toast(resId: String) =
    requireContext().toast(resId)
