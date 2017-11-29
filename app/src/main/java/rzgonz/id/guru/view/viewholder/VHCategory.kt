package rzgonz.id.guru.view.viewholder

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import rzgonz.core.kotlin.holder.BaseItemHolder
import com.bumptech.glide.request.target.Target
import rzgonz.id.guru.R
import rzgonz.id.guru.config.Constant

import rzgonz.id.guru.model.CategoriesItemModel
import rzgonz.id.guru.view.activity.ActivityQuiz

/**
 * Created by rzgonz on 11/5/17.
 */
class VHCategory(itemView: View) : BaseItemHolder(itemView) {
    var tvJudul = itemView.findViewById<TextView>(R.id.tvLabel)
    var img = itemView.findViewById<ImageView>(R.id.img)
    var imgIcon = itemView.findViewById<ImageView>(R.id.imgIcon)

    fun getImage(context: Context, imageName: String): Int {

        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName())
    }

    fun onBindData(item: Any) {
        val data = item as CategoriesItemModel
        tvJudul.text = data.name
        Glide.with(img.context)
                .load(getImage(img.context, data.image!!))
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        return false
                    }

                    override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, isFirstResource: Boolean): Boolean {
                            img.setImageResource(R.mipmap.ic_launcher)
                        return true
                    }
                }).into(img)

        Glide.with(imgIcon.context)
                .load(getImage(imgIcon.context, data.icon!!))
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        return false
                    }

                    override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        imgIcon.setImageResource(R.mipmap.ic_launcher)
                        return true
                    }
                }).into(imgIcon)
//
//        imgAnime.setOnClickListener({
//            val intent = ACT_Detial.newIntent(imgAnime.context,data)
//            imgAnime.context.startActivity(intent)
//        })

        img.rootView.setOnClickListener({
            showLevel(img.context,data)
        })

    }

    fun showLevel(context: Context, data: CategoriesItemModel){
        // Account picker
       var mAlertDialog = AlertDialog.Builder(context).setTitle("Select Levels").setAdapter(ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, context.resources.getStringArray(R.array.arrayLevel))) { dialog, which ->
            Log.d("showLevel"," --> "+which)
           var inten = Intent(context,ActivityQuiz::class.java)
           inten.putExtra(Constant.EXTRA_DATA,data)
           inten.putExtra(Constant.EXTRA_LEVEL,context.resources.getStringArray(R.array.arrayLevel)[which])
           context.startActivity(inten)
       }.create()
        mAlertDialog.show()
    }
}