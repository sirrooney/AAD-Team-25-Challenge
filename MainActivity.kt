package com.example.mobirent

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        val rootSet = AnimatorSet()

// Flip Animation
        val flip = ObjectAnimator.ofFloat(imageView2, "rotationX", 0.0f, 360.0f)
        flip.duration = 1000

// Child Animator Set
        val childSet = AnimatorSet()

// Scale Animations
        val scaleX = ObjectAnimator.ofFloat(imageView2, "scaleX", 0.5f, 1.0f)
        scaleX.duration = 1500
        scaleX.interpolator = OvershootInterpolator()

        val scaleY = ObjectAnimator.ofFloat(imageView2, "scaleY", 0.5f, 1.0f)
        scaleY.duration = 1500
        scaleY.interpolator = OvershootInterpolator()

        //rootSet.playSequentially(flip,childSet)
        //childSet.playTogether(scaleX, scaleY)

        rootSet.play(flip).before(childSet)
        childSet.play(scaleX).with(scaleY)

        rootSet.start()
        Toasty.success(applicationContext,"Welcome to MobiRent", Toast.LENGTH_SHORT).show()

        button.setOnClickListener(){
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
