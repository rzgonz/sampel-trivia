package rzgonz.id.guru.model

import com.google.gson.annotations.SerializedName

data class CategoryModel(

	@field:SerializedName("trivia_categories")
	val triviaCategories: ArrayList<CategoriesItemModel>? = null
)