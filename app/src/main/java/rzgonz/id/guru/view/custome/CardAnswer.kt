package rzgonz.id.guru.view.custome

import android.content.Context
import android.support.v7.widget.CardView
import android.text.Html
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.cell_answer.view.*
import rzgonz.id.guru.R
import rzgonz.id.guru.interfaces.FragmentQuizView

/**
 * Created by rzgonz on 28/11/17.
 */
open class CardAnswer : LinearLayout {
    lateinit var listener : FragmentQuizView.View
    var flagAnswer : Boolean = false

    constructor(context: Context?) : super(context) {
        makeView(context!!)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        makeView(context!!)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        makeView(context!!)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes){
        makeView(context!!)
    }

    private fun makeView(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.cell_answer, this)

    }

    fun Listener(listener: FragmentQuizView.View) {
       this.listener = listener
    }

     fun setData(data : String, correct : String){
        tvAnswer.setText(Html.fromHtml(data))
         if(data.equals(correct)){
             flagAnswer = true
         }
        cardAnswer.setOnClickListener({
            if(data.equals(correct)){
                listener.checkAnswer(true)
                cardFrame.setCardBackgroundColor(resources.getColor(R.color.aqua_marien))
                cardAnswer.setCardBackgroundColor(resources.getColor(R.color.aqua_marien))
            }else{
                listener.checkAnswer(false)
                cardFrame.setCardBackgroundColor(resources.getColor(R.color.coral))
                cardAnswer.setCardBackgroundColor(resources.getColor(R.color.coral))
                tvStatus.setTextColor(resources.getColor(R.color.white_two))
                tvAnswer.setTextColor(resources.getColor(R.color.white_two))
                tvAnswer.gravity = Gravity.BOTTOM
                tvStatus.setText("Wrong Answer !!")
                tvStatus.visibility = View.VISIBLE
            }
        })
    }
    fun lock(){
        if(flagAnswer){
            cardAnswer.setCardBackgroundColor(resources.getColor(R.color.aqua_marien))
            cardFrame.setCardBackgroundColor(resources.getColor(R.color.aqua_marien))
            tvStatus.setTextColor(resources.getColor(R.color.white_two))
            tvAnswer.setTextColor(resources.getColor(R.color.white_two))
            tvAnswer.gravity = Gravity.BOTTOM
            tvStatus.setText("Correct Answer")
            tvStatus.visibility = View.VISIBLE
        }
        cardAnswer.setOnClickListener({

        })
    }

}

