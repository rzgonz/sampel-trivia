package rzgonz.id.guru.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class QuizItemModel(

	@field:SerializedName("difficulty")
	val difficulty: String? = null,

	@field:SerializedName("question")
	val question: String? = null,

	@field:SerializedName("correct_answer")
	val correctAnswer: String? = null,

	@field:SerializedName("incorrect_answers")
	val incorrectAnswers: List<String?>? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("type")
	val type: String? = null
):Parcelable{
	constructor(parcel: Parcel) : this(
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.createStringArrayList(),
			parcel.readString(),
			parcel.readString()) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(difficulty)
		parcel.writeString(question)
		parcel.writeString(correctAnswer)
		parcel.writeStringList(incorrectAnswers)
		parcel.writeString(category)
		parcel.writeString(type)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<QuizItemModel> {
		override fun createFromParcel(parcel: Parcel): QuizItemModel {
			return QuizItemModel(parcel)
		}

		override fun newArray(size: Int): Array<QuizItemModel?> {
			return arrayOfNulls(size)
		}
	}

}