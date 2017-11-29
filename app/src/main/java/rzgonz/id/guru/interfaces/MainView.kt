package rzgonz.id.guru.interfaces

import android.content.Context
import rzgonz.core.kotlin.Interface.BaseViewList
import rzgonz.core.kotlin.presenter.BasePresenter

/**
 * Created by rzgonz on 27/11/17.
 */
object HomeView  {
    interface view : BaseViewList {

    }


    interface Presenter : BasePresenter<view> {
        fun loadCategory(context: Context)
    }
}