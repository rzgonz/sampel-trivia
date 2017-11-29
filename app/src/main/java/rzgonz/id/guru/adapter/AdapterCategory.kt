package rzgonz.id.guru.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import rzgonz.core.kotlin.adapter.BaseRVAdapter
import rzgonz.core.kotlin.holder.BaseItemHolder
import rzgonz.core.kotlin.model.RvPropertise
import rzgonz.id.guru.R
import rzgonz.id.guru.view.viewholder.VHCategory
import java.util.ArrayList

/**
 * Created by rzgonz on 11/5/17.
 */
class AdapterCategory(c: Context, items: ArrayList<Any> = java.util.ArrayList<Any>()) : BaseRVAdapter(c, items) {

    override fun onBindViewHolderItem(holder: BaseItemHolder?, position: Int, positionData: Int) {
        val view = holder as VHCategory;
        view.onBindData(getItem(positionData))
        //val layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 300)
        //view.itemView.setLayoutParams(layoutParams)
        Log.d("viewholderAnime", " --> " + holder.itemView.viewTreeObserver)

    }

    override fun onCreateViewHolderItem(viewGroup: ViewGroup, viewType: Int): BaseItemHolder {
        val v = LayoutInflater.from(c).inflate(R.layout.cell_category, viewGroup, false)
        return VHCategory(v)
    }

    override fun setRv(): RvPropertise {
        rvPropertise.hasFooter = false
        rvPropertise.hasHeadear = false
        rvPropertise.colomCount = 2
        rvPropertise.hasRefresh = false
        rvPropertise.hasLoadmore = false
        return rvPropertise
    }
}