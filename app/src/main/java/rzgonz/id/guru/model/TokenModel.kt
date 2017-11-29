package rzgonz.id.guru.model

import com.google.gson.annotations.SerializedName

data class TokenModel(

	@field:SerializedName("response_code")
	val responseCode: Int? = null,

	@field:SerializedName("response_message")
	val responseMessage: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)