package rzgonz.id.guru.view.activity

import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import rzgonz.core.kotlin.activity.BaseActivity
import rzgonz.core.kotlin.adapter.BaseRVAdapter
import rzgonz.core.kotlin.view.CustomeRV
import rzgonz.id.guru.R
import rzgonz.id.guru.adapter.AdapterCategory
import rzgonz.id.guru.interfaces.HomeView
import rzgonz.id.guru.model.CategoriesItemModel
import rzgonz.id.guru.presenter.HomePresenter
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class MainActivity :BaseActivity<HomeView.view,HomeView.Presenter>(),HomeView.view,CustomeRV.RVListener{

    override fun initAdapter(): BaseRVAdapter {
        return AdapterCategory(getContext())
    }

    override fun onLoadItems(limit: Int, offset: Int) {
        mPresenter.loadCategory(rvCategory.context)
    }

    override fun initRV(): CustomeRV {
      return rvCategory
    }

    override fun onSetData(status: Boolean, message: String, items: ArrayList<*>) {
        if(status){
            Log.d("onSetData"," --> "+items?.size)

            rvCategory.getAdapter().setItems(items)

        }else{
            rvCategory.errorLoading()
        }
    }

    override fun initLayout(): Int {
     return  R.layout.activity_main
    }

    override fun initUI() {
        rvCategory.listener(this)

        var data = ArrayList<String>()
        data.add("1")
        data.add("3")
        data.add("a")
        data.add("32")

        data.sortDescending()

        for(item in data){
            Log.d("sort",item)
        }


    }

    override var mPresenter: HomeView.Presenter = HomePresenter()
}
