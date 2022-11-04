package ar.edu.ort.tp3parcialrickandmorty.data

import android.os.Parcelable
import android.os.Parcel


data class CharacterDto(
    val id: Int,
    val image: String,
    val name: String,
    val origin: String,
    val species: String,
    val status: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(image)
        parcel.writeString(name)
        parcel.writeString(origin)
        parcel.writeString(species)
        parcel.writeString(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CharacterDto> {
        override fun createFromParcel(parcel: Parcel): CharacterDto {
            return CharacterDto(parcel)
        }

        override fun newArray(size: Int): Array<CharacterDto?> {
            return arrayOfNulls(size)
        }
    }
}
