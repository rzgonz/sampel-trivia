package rzgonz.id.guru.presenter

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import rzgonz.core.kotlin.helper.APIHelper
import rzgonz.core.kotlin.helper.JsonHelper
import rzgonz.core.kotlin.presenter.BasePresenterImpl
import rzgonz.id.guru.interfaces.HomeView
import rzgonz.id.guru.model.CategoryModel
import rzgonz.id.guru.services.RestServices

/**
 * Created by rzgonz on 27/11/17.
 */


class HomePresenter : BasePresenterImpl<HomeView.view>(),HomeView.Presenter{


    fun getRestService(): RestServices {
        return APIHelper.getClient().create(RestServices::class.java)
    }

    override fun loadCategory(context: Context) {
//       getRestService().getCategory().enqueue(object : Callback<CategoryModel>{
//           override fun onFailure(call: Call<CategoryModel>?, t: Throwable?) {
//              Log.d("loadCategory",t.toString())
//           }
//
//           override fun onResponse(call: Call<CategoryModel>?, response: Response<CategoryModel>?) {
//               Log.d("loadCategory",response?.body().toString())
//           }
//       })
        val data = JsonHelper.loadJSONFromAsset(context,"json/category.json")
        Log.d("loadCategory",data)
        val json = Gson().fromJson(data,CategoryModel::class.java)
        mView.onSetData(true,"succes", json.triviaCategories!!)
    }
}