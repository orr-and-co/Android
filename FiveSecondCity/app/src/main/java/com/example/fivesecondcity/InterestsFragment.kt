package com.example.fivesecondcity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.fragment_interests.*

class InterestsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val preferences = activity?.getPreferences(Context.MODE_PRIVATE)
        val view = inflater.inflate(R.layout.fragment_interests, container, false)
        if (preferences != null) {
            view.findViewById<Button>(R.id.buttonEvents).setOnClickListener() {
                negateButton(preferences, "InterestEvents", cardEvents)
            }
            view.findViewById<Button>(R.id.buttonCrime).setOnClickListener() {
                negateButton(preferences, "InterestCrime", cardCrime)
            }
            view.findViewById<Button>(R.id.buttonEducation).setOnClickListener() {
                negateButton(preferences, "InterestEducation", cardEducation)
            }
            view.findViewById<Button>(R.id.buttonEnvironment).setOnClickListener() {
                negateButton(preferences, "InterestEnvironment", cardEnvironment)
            }
            view.findViewById<Button>(R.id.buttonHealth).setOnClickListener() {
                negateButton(preferences, "InterestHealth", cardHealth)
            }
            view.findViewById<Button>(R.id.buttonHomelessness).setOnClickListener() {
                negateButton(preferences, "InterestHomelessness", cardHomelessness)
            }
            view.findViewById<Button>(R.id.buttonRights).setOnClickListener() {
                negateButton(preferences, "InterestRights", cardRights)
            }
            view.findViewById<Button>(R.id.buttonCampaigns).setOnClickListener() {
                negateButton(preferences, "InterestCampaigns", cardCampaigns)
            }
            view.findViewById<Button>(R.id.buttonMentalHealth).setOnClickListener() {
                negateButton(preferences, "InterestMentalHealth", cardMentalHealth)
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val preferences = activity?.getPreferences(Context.MODE_PRIVATE)
        if (preferences != null) {
            // Set the colors of the cards to match the preferences
            setButton(preferences, "InterestEvents", cardEvents)
            setButton(preferences, "InterestCrime", cardCrime)
            setButton(preferences, "InterestEducation", cardEducation)
            setButton(preferences, "InterestEnvironment", cardEnvironment)
            setButton(preferences, "InterestHealth", cardHealth)
            setButton(preferences, "InterestHomelessness", cardHomelessness)
            setButton(preferences, "InterestRights", cardRights)
            setButton(preferences, "InterestCampaigns", cardCampaigns)
            setButton(preferences, "InterestMentalHealth", cardMentalHealth)
        }
    }

    private fun negateButton(preferences: SharedPreferences, key: String, card: CardView) {
        val currVal = preferences.getBoolean(key, false)
        preferences.edit().putBoolean(key, !currVal).apply()
        if (currVal)
        {
            interestStrings.remove(interestMap[key])
            card.setCardBackgroundColor(resources.getColor(R.color.blue_light))
        }
        else
        {
            interestMap[key]?.let { interestStrings.add(it) }
            card.setCardBackgroundColor(resources.getColor(R.color.blue_dark))
        }
    }

    private fun setButton(preferences: SharedPreferences, key: String, card: CardView) {
        val currVal = preferences.getBoolean(key, false)
        if (currVal)
            card.setCardBackgroundColor(resources.getColor(R.color.blue_dark))
        else
            card.setCardBackgroundColor(resources.getColor(R.color.blue_light))
    }
}