package rzgonz.id.guru.interfaces

import android.content.Context
import rzgonz.core.kotlin.Interface.BaseView
import rzgonz.core.kotlin.Interface.BaseViewList
import rzgonz.core.kotlin.presenter.BasePresenter
import rzgonz.id.guru.view.custome.CardAnswer

/**
 * Created by rzgonz on 27/11/17.
 */
object FragmentQuizView {
    interface View : BaseView {
        fun checkAnswer(checkAnswer:Boolean)
    }


    interface Presenter : BasePresenter<View> {
        fun getDataQuiz()
    }
}