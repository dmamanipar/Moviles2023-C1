package pe.edu.upeu.modelo

data class MsgModelResp(
    var success:Boolean,
    var data:List<Actividad>,
    var message:String
)
data class MsgModelPerResp(
    var success:Boolean,
    var data:List<Periodo>,
    var message:String
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
}