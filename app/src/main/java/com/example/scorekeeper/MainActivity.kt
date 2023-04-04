package com.example.scorekeeper



import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.scorekeeper.R

class MainActivity : AppCompatActivity() {
    //member variable for holding the score
    var mScore1 = 0
    var mScore2 = 0

    val STATE_SCORE_1 = "Team 1 Score"
    val STATE_SCORE_2 = "Team 2 Score"


    // Member variables for holding the score
    lateinit var mScoreText1: TextView
    lateinit var mScoreText2: TextView

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mScoreText1 = findViewById(com.example.scorekeeper.R.id.score_1)
        mScoreText2 = findViewById(com.example.scorekeeper.R.id.score_2)


        if (savedInstanceState != null) {
            mScore1 = savedInstanceState.getInt(STATE_SCORE_1)
            mScore2 = savedInstanceState.getInt(STATE_SCORE_2)

// Set the score text views.
            mScoreText1.text = mScore1.toString()
            mScoreText2.text = mScore2.toString()
        }


    }


    fun decreaseScore(v: View) {
        var viewID = v.id
        //switch like strucutre
        when (viewID) {
            R.id.decreaseTeam1 -> {
                --mScore1
                mScoreText1.text=mScore1.toString()

            }
            R.id.decreaseTeam2 -> {
                --mScore2
                mScoreText2.text=mScore2.toString()
            }

        }

    }

    fun increaseScore(v: View) {
        var viewID = v.id
        when (viewID) {
            R.id.increaseTeam1 -> {
                ++mScore1
                mScoreText1.text = mScore1.toString()
            }
            R.id.increaseTeam2 -> {
                ++mScore2
                mScoreText2.text = mScore2.toString()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        return try {

            val nightMode = AppCompatDelegate.getDefaultNightMode()
            if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            val resetMenuItem: MenuItem = menu.findItem(R.id.night_mode)
            resetMenuItem.setTitle(R.string.day_mode)}
            else{
                val resetMenuItem: MenuItem = menu.findItem(R.id.night_mode)
                resetMenuItem.setTitle(R.string.night_mode)
            }
            true
        } catch (e: NullPointerException) {
            false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == com.example.scorekeeper.R.id.night_mode) {
            val nightMode = AppCompatDelegate.getDefaultNightMode()
            //Set the theme mode for the restarted activity.
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            // Recreate the activity for the theme change to take effect.
            recreate()
        }
        return true
    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_SCORE_1, mScore1);
        outState.putInt(STATE_SCORE_2, mScore2);
        super.onSaveInstanceState(outState)
    }
}