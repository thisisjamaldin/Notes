package com.joma.notes

data class NoteModel (
    var title: String = "",
    var desc: String = "",
    var date: String = "",
    var imageUri: String? = null
): java.io.Serializable