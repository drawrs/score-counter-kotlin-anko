package com.example.root.scorecounterkotlin

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.Gravity
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set Title
        supportActionBar?.title = "Score Counter App"
        // Anko External Layout Class
        MainActivityUI(this).setContentView(this)


    }

    class MainActivityUI(context: MainActivity) : AnkoComponent<MainActivity> {
        // Score view
        private var scoreTeamA: TextView ? = null
        private var scoreTeamB: TextView ? = null

        private var nameTeamA: TextView ? = null
        private var nameTeamB: TextView ? = null

        var ctx : Context = context
        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        override fun createView(ui: AnkoContext<MainActivity>) = ui.apply {

            // RootView / Main View
            verticalLayout {
                // View Group
                linearLayout {
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER
                    verticalPadding = 10
                    nameTeamA = textView {
                        text = "Team A"
                        textSize = sp(8).toFloat()
                        textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                        // Event
                        onClick {
                            // Change Team Name
                            changeTeamName(nameTeamA)
                        }
                    }.lparams {
                        width = matchParent
                        weight = 1F
                    }
                    nameTeamB = textView {
                        text = "Team B"
                        textSize = sp(8).toFloat()
                        textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                        // Event
                        onClick {
                            // Change Team Name
                            changeTeamName(nameTeamB)
                        }

                    }.lparams {
                        width = matchParent
                        weight = 1F
                    }
                }.lparams {
                    width = matchParent
                    height = matchParent
                    weight = 1f
                }
                // Score View
                linearLayout {
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER
                    verticalPadding = 10
                    // SCORE A
                    scoreTeamA = textView {
                        text = "0"
                        textSize = sp(80).toFloat()
                        typeface = Typeface.DEFAULT_BOLD
                        textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                        // Event
                        onClick {
                            // Subctact score
                            subtractScore(scoreTeamA)
                        }
                    }.lparams {
                        width = matchParent
                        weight = 1F
                    }
                    // SCORE B
                    scoreTeamB = textView {
                        text = "0"
                        textSize = sp(80).toFloat()
                        typeface = Typeface.DEFAULT_BOLD
                        textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                        gravity  = Gravity.CENTER_VERTICAL
                        onClick {
                            // Subctact score
                            subtractScore(scoreTeamB)
                        }
                    }.lparams {
                        width = matchParent
                        gravity  = Gravity.CENTER_VERTICAL
                        weight = 1F
                    }
                }.lparams {
                    width = matchParent
                    height = matchParent
                    weight = 0.5f
                }

                // Button Input
                linearLayout {
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER
                    verticalPadding = 10
                    button {
                        text = "Point +1"
                        // Event
                        onClick {
                            addPointTo(scoreTeamA)
                        }
                    }.lparams {
                        width = matchParent
                        weight = 1F
                    }
                    button {
                        text = "Point +1"
                        // Event
                        onClick {
                            addPointTo(scoreTeamB)
                        }
                    }.lparams {
                        width = matchParent
                        weight = 1F
                    }
                }.lparams {
                    width = matchParent
                    height = matchParent
                    weight = 1f
                }
                // Complete Input
                linearLayout {
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER
                    verticalPadding = 10
                    button {
                        text = "Finish Match"
                        onClick {
                            startActivity<ResultActivity>("SC_A" to scoreTeamA?.text.toString(),
                                    "SC_B" to scoreTeamB?.text.toString(),
                                    "NM_A" to nameTeamA?.text.toString(),
                                    "NM_B" to nameTeamB?.text.toString());
                        }
                    }.lparams {
                        width = matchParent

                    }
                }.lparams {
                    width = matchParent
                    height = matchParent
                    weight = 1f
                }
            }

        }.view

        private fun changeTeamName(teamName: TextView?) {
            val currentName = teamName?.text
            // Alert dialog
            ctx.alert {
                // Custom View
                var newTeamName : EditText ? = null
                customView {
                    title = "Change Team Name"
                    verticalLayout {
                        padding = 10
                        newTeamName = editText(currentName) {
                            hint = "Enter team name"
                        }
                    }
                }
                yesButton {
                    // prevent null name
                    if(newTeamName?.text.toString().isNotEmpty() && newTeamName?.text.toString().count() > 0){
                        // Set team name with new name
                        teamName?.text = newTeamName?.text.toString()
                    }
                }
                noButton {

                }
            }.show()
        }

        private fun subtractScore(scoreView: TextView?) {
            // Get current Score
            val currentScore = scoreView?.text.toString()?.toInt()
            // Set Maximum score
            val minScore = 0
            // Check if score less than maxScore
            if (currentScore > minScore){
                // Subtract current score with -1
                val newScore = currentScore - 1
                // set new score into TextView
                scoreView?.text = newScore.toString()
            }
        }

        private fun addPointTo(scoreView: TextView?) {
            // Get current Score
            val currentScore = scoreView?.text.toString()?.toInt()
            // Set Maximum score
            val maxScore = 99
            // Check if score less than maxScore
            if (currentScore < maxScore){
                // Add current score with +1
                val newScore = currentScore + 1
                // set new score into TextView
                scoreView?.text = newScore.toString()
            }
        }

    }
}
