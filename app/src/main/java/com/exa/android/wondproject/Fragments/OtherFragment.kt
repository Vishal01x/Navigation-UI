package com.exa.android.wondproject.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.exa.android.wondproject.R

class OtherFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_other, container, false)
    }
  /* WHY ONLY ONE FRAGMENT

  since the task screen except home screen is to just show navigation, that's why
  i have created only one fragment named Other fragment and its corresponding
  layout file.

  if we need to do different work for different screen we need to create separate
  fragment and its corresponding layout to handle efficiently

   */
}