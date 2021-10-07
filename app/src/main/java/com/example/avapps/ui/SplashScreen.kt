package com.example.avapps.ui

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.avapps.DataLoader
import com.example.avapps.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException

class SplashScreen : AppCompatActivity() {
    private val delayActivity: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val txtVersionInfo = findViewById<TextView>(R.id.txt_ver_app)
        txtVersionInfo.text = getVersionApp()
        checkPermission()
    }

    private fun getVersionApp(): String {
        val packageManager = packageManager
        return packageManager.getPackageInfo(packageName, 0).versionName
    }

    override fun onResume() {
        super.onResume()
        updateSystemUIFullScreen(this.window)
        updateScreenMode(this)
    }

    private fun updateScreenMode(activity: Activity) {
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    private fun updateSystemUIFullScreen(window: Window) {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LOW_PROFILE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    private fun startMainActivity() {
        val mainIntent = Intent(this, MainActivity::class.java)
        this.startActivity(mainIntent)
        finish()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkPermission() {
        if ((ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            requestPermissions(
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_STORAGE_PERMISSION
            )
        } else {
            val txtVersionInfo = findViewById<TextView>(R.id.txt_ver_app)
            txtVersionInfo.text = getVersionApp()
            GlobalScope.launch {
                delay(delayActivity)
                startMainActivity()
            }.start()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_STORAGE_PERMISSION -> {
                if (grantResults.isNotEmpty()
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    val txtVersionInfo = findViewById<TextView>(R.id.txt_ver_app)
                    txtVersionInfo.text = getVersionApp()
                    GlobalScope.launch {
                        delay(delayActivity)
                        startMainActivity()
                    }.start()
                } else {
                    Toast.makeText(this, getString(R.string.permission_denied), Toast.LENGTH_SHORT)
                        .show()

                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object {
        private const val REQUEST_STORAGE_PERMISSION = 1
    }
}