package rzgonz.id.guru.services

import retrofit2.Call
import retrofit2.http.*
import rzgonz.id.guru.model.CategoryModel
import rzgonz.id.guru.model.QuizModel
import rzgonz.id.guru.model.TokenModel

/**
 * Created by rzgonz on 11/1/17.
 */
interface RestServices {

    @GET("api_category.php")
    fun getCategory(): Call<CategoryModel>

    @GET("api_token.php?command=request")
    fun getAccess(): Call<TokenModel>
    //https://opentdb.com/api.php?amount=20&category=9&difficulty=medium&type=multiple
    @GET("api.php/")
    fun getQuiz(@QueryMap query:Map<String, String>): Call<QuizModel>

    @GET("api_token.php?command=reset")
    fun getReset(@Query("token") token:String): Call<TokenModel>

}