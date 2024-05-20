package com.exa.android.wondproject.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.CreationExtras
import com.exa.android.wondproject.R
import com.google.android.material.imageview.ShapeableImageView


class HomeFragment : Fragment() {
    private lateinit var wel_txt: TextView
    private lateinit var toolbar: Toolbar
    private lateinit var card1: CardView
    private lateinit var card2: CardView
    private lateinit var card3: CardView
    private var selectedCard: CardView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // extracting view via there id, we can use viewBinding to further optimise code

        val home_scr = view.findViewById<LinearLayout>(R.id.home_scr)
        val proceed_btn = view.findViewById<Button>(R.id.proceed_button)
        val edit_txt = view.findViewById<EditText>(R.id.name_edit_text)
        wel_txt = view.findViewById(R.id.welcome_text)
        card1 = view.findViewById(R.id.card1)
        card2 = view.findViewById(R.id.card2)
        card3 = view.findViewById(R.id.card3)
        toolbar = requireActivity().findViewById(R.id.toolbar)

       // applying on click for cards and call fun

        card1.setOnClickListener { selectCard(card1) }
        card2.setOnClickListener { selectCard(card2) }
        card3.setOnClickListener { selectCard(card3) }

         // when nothing is selected show a toast of nothing is selcted
        // when either card or no name is provided then show a toast
        // when both are selected then switch new screen and set some text

        proceed_btn.setOnClickListener {
            val name = edit_txt.text.toString()
            if (selectedCard == null && name.isEmpty()) { // when nothing is selected
                Toast.makeText(
                    requireContext(), " Enter the data" +
                            " before proceeding", Toast.LENGTH_SHORT
                ).show()
            } else if (selectedCard == null) { // when only name is given
                Toast.makeText(requireContext(), "Select any one card", Toast.LENGTH_SHORT).show()
            } else if (name.isEmpty()) { // when only card is selected
                Toast.makeText(requireContext(), "Enter your name", Toast.LENGTH_SHORT).show()
            } else {// when card and text is written then switch new screen


                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, OtherFragment())
                    .commit()

                toolbar.title = "Next Screen"
                val user_name = requireActivity().findViewById<TextView>(R.id.user_name)

                user_name.text = name

            }
        }

        // when we click on home screen welcome msg visibility is gone

        home_scr.setOnClickListener {
            wel_txt.visibility = View.GONE
        }



        return view
    }

    // creating a separate fun that responds when card is clicked

    private fun selectCard(card: CardView) {
        // Deselect previous card
        selectedCard?.setCardBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                android.R.color.white
            )
        )
        // if same card is selected again then deselect card
        if (selectedCard == card) {
            selectedCard = null
            return
        }
        // Select new card
        card.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.cardback))
        selectedCard = card

    }

}