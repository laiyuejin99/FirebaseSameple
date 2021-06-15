package com.jin.firebasetest

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        // Obtain the FirebaseAnalytics instance.
        firebaseAnalytics = Firebase.analytics
        val parameters = Bundle()
        parameters.putString("level_name", "Caverns01")
        parameters.putInt("level_difficulty", 4)
        firebaseAnalytics.setDefaultEventParameters(parameters)

        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID,"testid")
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME,"first_item_name")
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE,"this is the content type")
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT,bundle)
        println("put the firebase logs.")
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}