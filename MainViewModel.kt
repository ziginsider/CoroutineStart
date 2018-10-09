package com.example.aliaksei_kisel.coroutinestart

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.example.aliaksei_kisel.coroutinestart.util.BACKGROUND
import com.example.aliaksei_kisel.coroutinestart.util.ConsumableValue

class MainViewModel : ViewModel() {

    /**
     * Request a snackbar to display a string.
     *
     * This variable is private because we don't want to expose MutableLiveData
     */
    private val _snackBar = MutableLiveData<String>()

    /**
     * Request a snackbar to display a string.
     *
     * Use Transformations.map to wrap each string sent to _snackbar in a ConsumableValue.
     */
    var snackBar = Transformations.map(_snackBar) { ConsumableValue(it) }

    /**
     * Wait a second then display a snackbar.
     */
    fun onMainViewClicked() {

        BACKGROUND.submit {
            Thread.sleep(1_000)
            _snackBar.postValue("Hello, from threads!")
        }
    }
}