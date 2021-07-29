package br.com.fiap.easycoachapp.domain.entities

class SpecialtyEntity (
    val uid: String,
    val description: String
) {
    companion object {
        fun fromJson(json: MutableMap<String, Any>) : SpecialtyEntity {
            return SpecialtyEntity(
                uid = json["uid"].toString(),
                description = json["description"].toString()
            )
        }
    }

    override fun toString(): String {
        return description
    }
}