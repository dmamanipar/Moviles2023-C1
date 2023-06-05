package pe.edu.upeu.modelo

<<<<<<< HEAD
<<<<<<< HEAD
data class ComboModel(
    val code: String,
    val name: String
    ): PickerValue() {
    override fun searchFilter(query: String): Boolean {
        return this.name.startsWith(query) } }
abstract class PickerValue {
    abstract fun searchFilter(query: String): Boolean }
=======
=======
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException
>>>>>>> Dev07-1
data class MsgModelResp(
    var success:Boolean,
    var data:List<Actividad>,
    var message:String
)

data class MsgModelResp2(
    var success: Boolean,
    var data:List<Facultad>,
    var message: String
)


data class ComboModel(
    val code: String,
    val name: String
): PickerValue() {
    override fun searchFilter(query: String): Boolean {
        return this.name.startsWith(query)
    }
}
abstract class PickerValue {
    abstract fun searchFilter(query: String): Boolean
<<<<<<< HEAD
}
>>>>>>> DevDocent
=======
}
>>>>>>> Dev07-1
