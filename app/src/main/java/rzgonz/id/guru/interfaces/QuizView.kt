package rzgonz.id.guru.interfaces

import android.content.Context
import rzgonz.core.kotlin.Interface.BaseView
import rzgonz.core.kotlin.Interface.BaseViewList
import rzgonz.core.kotlin.presenter.BasePresenter
import rzgonz.id.guru.model.QuizModel

/**
 * Created by rzgonz on 27/11/17.
 */
object QuizView  {
    interface View : BaseView {
        fun setDataQuiz(status:Boolean,data:QuizModel = QuizModel());
    }


    interface Presenter : BasePresenter<View> {
        fun getQuiz(data:HashMap<String,String>)
        fun refreshToken(data:HashMap<String,String>)
    }
}