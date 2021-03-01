package uz.abdullajon.learndaggervsmvvm.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import uz.abdullajon.learndaggervsmvvm.R
import uz.abdullajon.learndaggervsmvvm.data.model.NewsArticle
import uz.abdullajon.learndaggervsmvvm.ui.base.BaseFragment
import uz.abdullajon.learndaggervsmvvm.ui.news.NewsListFragment
import uz.abdullajon.learndaggervsmvvm.ui.news.OnClickListener
import uz.abdullajon.learndaggervsmvvm.ui.news_detail.DetailFragment
import javax.inject.Inject


class MainActivity : AppCompatActivity(), HasAndroidInjector, OnClickListener {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        if (savedInstanceState == null) {
            addFragment(NewsListFragment())
        }
    }

    private fun addFragment(newFragment: BaseFragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.slide_left,
                R.anim.slide_right
            )
            .add(container_layout.id, newFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onClick(item: NewsArticle) {
        addFragment(DetailFragment.instance(item))
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 1) {
            supportFragmentManager.popBackStack()
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}
