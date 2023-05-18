package pe.edu.upeu.modelo

data class MsgModelRespEs(
    var success:Boolean,
    var data:List<Escuela>,
    var message:String
)
data class ComboModels(
    val code: String,
    val name: String
): PickerValue() {
    override fun searchFilter(query: String): Boolean {
        return this.name.startsWith(query)
    }
}
abstract class PickerValues {
    abstract fun searchFilter(query: String): Boolean
}
