package rzgonz.id.guru.presenter

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import rzgonz.core.kotlin.helper.APIHelper
import rzgonz.core.kotlin.helper.JsonHelper
import rzgonz.core.kotlin.presenter.BasePresenterImpl
import rzgonz.id.guru.interfaces.HomeView
import rzgonz.id.guru.interfaces.QuizView
import rzgonz.id.guru.model.CategoryModel
import rzgonz.id.guru.model.QuizModel
import rzgonz.id.guru.model.TokenModel
import rzgonz.id.guru.services.RestServices

/**
 * Created by rzgonz on 27/11/17.
 */


class QuizPresenter : BasePresenterImpl<QuizView.View>(),QuizView.Presenter{


    fun getRestService(): RestServices {
        return APIHelper.getClient().create(RestServices::class.java)
    }

    override fun getQuiz(data: HashMap<String, String>) {
        getRestService().getAccess().enqueue(object : Callback<TokenModel> {
           override fun onFailure(call: Call<TokenModel>?, t: Throwable?) {
              Log.d("loadCategory",t.toString())
           }
           override fun onResponse(call: Call<TokenModel>?, response: Response<TokenModel>?) {
               Log.d("loadCategory",response?.body().toString())
               data.set("token",response?.body()?.token!!)
               Log.d("loadCategory",data.toString())
               getRestService().getQuiz(data).enqueue(object : Callback<QuizModel>{
                   override fun onResponse(call: Call<QuizModel>?, response: Response<QuizModel>?) {
                       Log.d("getQuiz",response?.body().toString())
                       if(response?.body()?.responseCode==4){
                                refreshToken(data)
                       }else{
                           mView.setDataQuiz(true, response?.body()!!)
                       }
                   }
                   override fun onFailure(call: Call<QuizModel>?, t: Throwable?) {
                       Log.d("getQuiz",t.toString())
                       mView.setDataQuiz(false)
                   }
               })
           }
       })
    }

    override fun refreshToken(data: HashMap<String, String>) {
        getRestService().getReset(data.get("token")!!).enqueue(object : Callback<TokenModel> {
            override fun onFailure(call: Call<TokenModel>?, t: Throwable?) {
                Log.d("refreshToken",t.toString())
            }
            override fun onResponse(call: Call<TokenModel>?, response: Response<TokenModel>?) {
                Log.d("refreshToken",response?.body().toString())
                data.set("token",response?.body()?.token!!)
                Log.d("refreshToken",data.toString())
                getRestService().getQuiz(data).enqueue(object : Callback<QuizModel>{
                    override fun onResponse(call: Call<QuizModel>?, response: Response<QuizModel>?) {
                        Log.d("getQuiz",response?.body().toString())
                        if(response?.body()?.responseCode==4){
                            mView.setDataQuiz(false)
                        }else{
                            mView.setDataQuiz(true, response?.body()!!)
                        }
                    }
                    override fun onFailure(call: Call<QuizModel>?, t: Throwable?) {
                        Log.d("getQuiz",t.toString())
                        mView.setDataQuiz(false)
                    }
                })
            }
        })
    }
}