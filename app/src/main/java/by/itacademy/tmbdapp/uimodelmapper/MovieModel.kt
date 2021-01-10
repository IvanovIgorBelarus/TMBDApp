package by.itacademy.tmbdapp.uimodelmapper

data class MovieModel (
    val id:Int,
    val title:String,
    val releaseDate:String,
    val overview:String,
    val rating:Float,
    val genres:String,
    val posterPath:String
)