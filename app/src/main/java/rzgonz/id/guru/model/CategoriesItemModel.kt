package rzgonz.id.guru.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class CategoriesItemModel(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("image")
	val image: String? = null
):Parcelable {
	constructor(parcel: Parcel) : this(
			parcel.readString(),
			parcel.readValue(Int::class.java.classLoader) as? Int,
			parcel.readString(),
			parcel.readString()) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(name)
		parcel.writeValue(id)
		parcel.writeString(icon)
		parcel.writeString(image)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<CategoriesItemModel> {
		override fun createFromParcel(parcel: Parcel): CategoriesItemModel {
			return CategoriesItemModel(parcel)
		}

		override fun newArray(size: Int): Array<CategoriesItemModel?> {
			return arrayOfNulls(size)
		}
	}
}
