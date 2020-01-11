package com.myapplication

import android.os.Bundle
import android.os.Handler
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navArgs

import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class DetailActivity : AppCompatActivity() {

    val args: DetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate")

        setContentView(R.layout.activity_detail)

        // This is a programmatic nav fragment derived for portrait phones,
        // and requires manually setting the nav graph to forward the arguments
        val detail = supportFragmentManager.findFragmentById(R.id.detail_nav_fragment) as NavHostFragment?
        detail?.navController?.setGraph(R.navigation.detail, args.toBundle())
    }

}
