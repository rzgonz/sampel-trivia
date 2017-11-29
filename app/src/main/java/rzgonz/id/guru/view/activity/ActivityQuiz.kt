package rzgonz.id.guru.view.activity

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.support.v4.app.Fragment
import android.util.Log
import kotlinx.android.synthetic.main.activity_quiz.*
import rzgonz.core.kotlin.activity.BaseActivity
import rzgonz.core.kotlin.view.CustomeViewPager
import rzgonz.id.guru.R
import rzgonz.id.guru.config.Constant
import rzgonz.id.guru.interfaces.QuizView
import rzgonz.id.guru.model.CategoriesItemModel
import rzgonz.id.guru.model.QuizModel
import rzgonz.id.guru.presenter.QuizPresenter
import rzgonz.id.guru.view.fragments.FragmentQuiz
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Toast
import com.eftimoff.viewpagertransformers.StackTransformer

class ActivityQuiz : BaseActivity<QuizView.View,QuizView.Presenter>(),QuizView.View,CustomeViewPager.PagerListener, FragmentQuiz.OnFragmentInteractionListener {
    var items: ArrayList<Fragment> = ArrayList()
    var rightAnswer  = 0
    override fun initLayout(): Int {
       return  R.layout.activity_quiz
    }

    lateinit var progress: ProgressDialog

    override fun initUI() {
        val level = intent.getStringExtra(Constant.EXTRA_LEVEL)
        val data = intent.getParcelableExtra<CategoriesItemModel>(Constant.EXTRA_DATA)

        tvBar.setText(data.name)

        Log.d("ActivityQuiz"," -> $level = ${data.toString()}")
        val params = HashMap<String,String>()
        params.set("difficulty",level)
        params.set("amount","20")
        params.set("category",data.id.toString())
        params.set("type","multiple")
        cpQuiz.setOnTouchListener(object : OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                cpQuiz.setCurrentItem(cpQuiz.currentItem,false)
                return true
            }

        })

        mPresenter.getQuiz(params)
        progress = ProgressDialog(this)
        progress.setMessage("Please waiting")
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progress.setIndeterminate(true)
        progress.setProgress(0)
        progress.setCanceledOnTouchOutside(false)
        progress.show()

        cpQuiz.setPageTransformer(true,  StackTransformer())
    }

    override var mPresenter: QuizView.Presenter = QuizPresenter()

    override fun initViewPager(): Int {
       return R.id.cpQuiz
    }

    override fun initViewTabLayout() {

    }

    override fun addFragment(): ArrayList<Fragment> {

        return items
    }

    override fun onFragmentInteraction(boolean: Boolean,position:Int) {
        cpQuiz.setCurrentItem(cpQuiz.currentItem+1,true)
        tvQuestion.setText("Question no : ${cpQuiz.currentItem+1}")
        if(boolean){
            rightAnswer++
            tvAnswer.setText(" ${rightAnswer} Right Answers")
        }
        if(position>=items.size){
            makeDialog("Congratulation","You Have $rightAnswer Right Answers",true)
        }
    }

    private fun makeDialog(title:String,message :String,finish : Boolean = false) {
        // setup the alert builder
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)

        // add a button
        builder.setPositiveButton("Ok",{ dialogInterface, i ->
            finish()
        })
        if(!finish){
            builder.setNegativeButton("No",{ dialogInterface, i ->
                dialogInterface.dismiss()
            })
        }
        // create and show the alert dialog
        val dialog = builder.create()
        dialog.show()
    }

    override fun setDataQuiz(status: Boolean, data: QuizModel) {
        progress.dismiss()
        if(status){
            for ( item in data.results!!){
                items.add(FragmentQuiz.newInstance(item!!,items.size))
            }
            tvQuestion.setText("Question no : 1")
            tvAnswer.setText("$rightAnswer Right Answers")
            cpQuiz.listener = this
            cpQuiz.setAdapter(this)
        }else{
            Toast.makeText(getContext(),"OPPS!! Some things wrongs \nPlease Try Again other Category",Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    public fun onClose( v : View){
        makeDialog("Warning !!","Are you sure finish this session ?")
    }

    override fun onBackPressed() {
        makeDialog("Warning !!","Are you sure finish this session ?")
    }
}
