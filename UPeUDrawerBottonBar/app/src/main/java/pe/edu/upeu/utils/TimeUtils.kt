package pe.edu.upeu.utils

import java.util.*
fun isNight():Boolean{
    val
            hora= Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return (hora<=6 || hora>=18)
}
