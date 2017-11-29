package rzgonz.id.guru.view.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Html
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_quiz.*
import rzgonz.core.kotlin.fragment.BaseFragment

import rzgonz.id.guru.R
import rzgonz.id.guru.interfaces.FragmentQuizView
import rzgonz.id.guru.model.QuizItemModel
import rzgonz.id.guru.presenter.FragmentQuizPresenter

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FragmentQuiz.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FragmentQuiz.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentQuiz : BaseFragment<FragmentQuizView.View,FragmentQuizView.Presenter>(),FragmentQuizView.View {

    private var mListener: OnFragmentInteractionListener? = null

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(data: Boolean, position:Int) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(data,position)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(select: Boolean,position: Int)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentQuiz.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: QuizItemModel,param2:Int): FragmentQuiz {
            val fragment = FragmentQuiz()
            val args = Bundle()
            args.putParcelable(ARG_PARAM1, param1)
            args.putInt(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

    override fun initLayout(): Int {
       return R.layout.fragment_quiz
    }

    override fun initUI() {
       val data =  arguments.getParcelable<QuizItemModel>(ARG_PARAM1)
        tvQuestion.setText(Html.fromHtml(data.question))

        var answers = ArrayList<String>(data.incorrectAnswers)
        answers.add(data.correctAnswer!!)

        if(data.question?.length?.rem(2) ==0){
            answers.sortDescending()
        }else{
            answers.sort()
        }
        cardA.listener = this
        cardB.listener = this
        cardC.listener = this
        cardD.listener = this
        for (a in 0..3){
            when(a){
                0 -> cardA.setData(answers[a],data.correctAnswer)
                1 -> cardB.setData(answers[a],data.correctAnswer)
                2 -> cardC.setData(answers[a],data.correctAnswer)
                3 -> cardD.setData(answers[a],data.correctAnswer)
            }
        }
        btnNext.setOnClickListener({
          Toast.makeText(context,"Please Answer Before Next",Toast.LENGTH_LONG).show()
        })

        if(arguments.getInt(ARG_PARAM2)>=19){
            btnNext.setText("Finish Game")
        }
    }

    override fun checkAnswer(checkAnswer: Boolean) {
        cardA.lock()
        cardB.lock()
        cardC.lock()
        cardD.lock()
        btnNext.setOnClickListener({
            if(checkAnswer){
                onButtonPressed(true,arguments.getInt(ARG_PARAM2)+1)
            }else{
                onButtonPressed(false,arguments.getInt(ARG_PARAM2)+1)
            }
        })
    }

    override var mPresenter: FragmentQuizView.Presenter = FragmentQuizPresenter()
}// Required empty public constructor
