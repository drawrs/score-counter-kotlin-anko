package com.example.root.scorecounterkotlin

import android.graphics.Typeface
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class ResultActivity : AppCompatActivity() {
    var SC_A : String ? = null
    var SC_B : String ? = null
    var NM_A : String ? = null
    var NM_B : String ? = null
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_result)
        // Hide Action bar
        supportActionBar?.hide()
        // Get data from Intent

        SC_A = intent.getStringExtra("SC_A")
        SC_B = intent.getStringExtra("SC_B")
        NM_A = intent.getStringExtra("NM_A")
        NM_B = intent.getStringExtra("NM_B")


        // Root View
        verticalLayout {
            // View Group
            relativeLayout {
                gravity = Gravity.CENTER_VERTICAL
                // LINEAR #1
                linearLayout {
                    orientation = LinearLayout.HORIZONTAL
                    setVerticalGravity(Gravity.CENTER_VERTICAL)
                    // For display Score Result and Team Name
                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        // Score Result
                        textView {
                            text = SC_A
                            textSize = sp(50).toFloat()
                            typeface = Typeface.DEFAULT_BOLD
                            textAlignment = TextView.TEXT_ALIGNMENT_CENTER

                        }.lparams {
                            width = matchParent
                        }
                        // Team Name
                        textView("$NM_A") {
                            textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                        }
                    }.lparams {
                        width = matchParent
                        weight = 1F
                    }
                    // Display match Result Draw / Win
                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        textView {
                            topPadding = dip(20)
                            // Simple logic, is Draw ?
                            text = if(SC_A.equals(SC_B)) "DRAW" else "Congratulations !"
                            textSize = sp(10).toFloat()
                            textAlignment = TextView.TEXT_ALIGNMENT_CENTER

                        }.lparams {
                            width = matchParent
                            weight = 1F
                        }
                        textView {
                            topPadding = dip(20)
                            text = "vs"
                            textSize = sp(30).toFloat()
                            textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                        }.lparams {
                            width = matchParent
                            weight = 2F
                        }
                    }.lparams {
                        width = matchParent
                        weight = 1F
                    }

                    // For display Score Result and Team Name
                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        // Score Result
                        textView {
                            text = "$SC_B"
                            textSize = sp(50).toFloat()
                            typeface = Typeface.DEFAULT_BOLD
                            textAlignment = TextView.TEXT_ALIGNMENT_CENTER

                        }.lparams {
                            width = matchParent
                        }
                        // Team Name
                        textView("$NM_B") {
                            textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                        }
                    }.lparams {
                        width = matchParent
                        weight = 1F
                    }
                }.lparams {
                    width = matchParent
                    centerVertically()
                }

                // LINEAR #2
                // Button Reset
                button("Reset") {
                    onClick {
                        startActivity(intentFor<MainActivity>())
                    }
                }.lparams {
                    width = matchParent
                    alignParentBottom()
                }
            }
        }
    }
}
