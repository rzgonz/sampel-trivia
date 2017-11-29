package rzgonz.id.guru.model

import com.google.gson.annotations.SerializedName

data class QuizModel(

	@field:SerializedName("response_code")
	val responseCode: Int? = null,

	@field:SerializedName("results")
	val results: List<QuizItemModel?>? = null
)