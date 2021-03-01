package uz.abdullajon.learndaggervsmvvm.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(
    private val videoMapModel: Map<Class<out ViewModel>
            , @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {


    @Throws(IllegalArgumentException::class)
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        var viewModel = videoMapModel[modelClass]

        if (viewModel == null) {
            for (entry in videoMapModel) {
                if (modelClass.isAssignableFrom(entry.key)) {
                    viewModel = entry.value
                    break
                }
            }
        }
        if (viewModel == null) throw IllegalArgumentException("unknown model class :$modelClass")
        return viewModel.get() as T
    }
}