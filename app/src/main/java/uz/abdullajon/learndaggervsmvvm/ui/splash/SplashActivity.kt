package uz.abdullajon.learndaggervsmvvm.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import uz.abdullajon.learndaggervsmvvm.R
import uz.abdullajon.learndaggervsmvvm.ui.main.MainActivity
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private var timer: CountDownTimer? = null
    private var login = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        AndroidInjection.inject(this)
        getTimer()
    }

    private fun getTimer() {
        timer = object : CountDownTimer(3_000, 1000) {
            override fun onFinish() {
                onMainActivity()
            }

            override fun onTick(p0: Long) {}
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }

    fun onMainActivity() {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }
}