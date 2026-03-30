package com.vinted.demovinted.application

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.vinted.demovinted.features.feed.FeedFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, FeedFragment.newInstance())
                .commit()
        }
    }
}
