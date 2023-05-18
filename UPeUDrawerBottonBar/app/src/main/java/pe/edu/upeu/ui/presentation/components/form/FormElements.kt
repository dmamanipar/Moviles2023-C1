package pe.edu.upeu.ui.presentation.components.form

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.k0shk0sh.compose.easyforms.*
import pe.edu.upeu.modelo.ComboModel
import java.text.SimpleDateFormat
import java.util.*

enum class MyFormKeys {
    EMAIL, PASSWORD, SALUTATION, SALUTATION2,NAME, URL, CUSTOM_FOCUS,
    PHONE, CARD, CHECKBOX, LIST_CHECKBOX, TRI_CHECKBOX, RADIO_BUTTON,
    SWITCH, SLIDER, RANGE_SLIDER,DNI, APE_PAT, APE_MAT, FECHA, TIME, TIME_TOLER, PERSON, OFFLIN, TIPO, TIPO_REG, CAL
}

@Composable
fun ComboBox(
    easyForm: EasyForms,
    label:String,
    textv: String,
    list:List<ComboModel>
) {
    val state = easyForm.addAndGetCustomState(
        MyFormKeys.SALUTATION, MyEasyFormsCustomStringState(
            validData = list
        )
    )
    val text = state.state
    val isOpen = state.isOpen
    if (textv != "") {
        val seleccionado = list.find { it.code == textv }
        text.value = seleccionado!!.name
    }
    Log.i("DATAXXX",text.value )
    Box{
        Column {
            TextField(
                value = text.value,
                onValueChange = state.onValueChangedCallback,
                label = { Text(text = label) },
                placeholder = { Text(text = label) },
                modifier = Modifier.fillMaxWidth(),
            )
            pe.edu.upeu.ui.presentation.components.Spacer(size=2)
            DropDownList(
                state = state,
                requestToOpen = isOpen.value,
                list = list,
            )
        }
        Box(
            modifier = Modifier
                .matchParentSize()
                .alpha(0f)
                .clickable(onClick = state.onClick)
        )
    }
}

@Composable
fun ComboBoxTwo(
    easyForm: EasyForms,
    label:String,
    textv: String,
    list:List<ComboModel>
) {
    val state = easyForm.addAndGetCustomState(
        MyFormKeys.SALUTATION2, MyEasyFormsCustomStringState(
            validData = list
        )
    )
    val text = state.state
    val isOpen = state.isOpen
    if (textv != "") {
        val seleccionado = list.find { it.code == textv }
        text.value = seleccionado!!.name
    }
    Box{
        Column {
            TextField(
                value = text.value,
                onValueChange = state.onValueChangedCallback,
                label = { Text(text = label) },
                placeholder = { Text(text = label) },
                modifier = Modifier.fillMaxWidth(),
            )
            pe.edu.upeu.ui.presentation.components.Spacer(size=2)
            DropDownList(
                state = state,
                requestToOpen = isOpen.value,
                list = list,
            )
        }
        Box(
            modifier = Modifier
                .matchParentSize()
                .alpha(0f)
                .clickable(onClick = state.onClick)
        )
    }
}

@Composable
private fun DropDownList(
    state: MyEasyFormsCustomStringState,
    requestToOpen: Boolean = false,
    list: List<ComboModel>,
) {
    DropdownMenu(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        expanded = requestToOpen,
        onDismissRequest = state.onDismissed,
    ) {
        list.forEach {
            DropdownMenuItem(
                modifier = Modifier.fillMaxWidth(),
                onClick = { state.onValueChangedCallback(if(it.code==it.name) it.code else it.code+"-"+it.name) },
            ) {
                Text(
                    text = it.name,
                    modifier = Modifier.wrapContentWidth(),
                )
            }
        }
    }
}


@Composable
fun DatePickerCustom(
    easyForm: EasyForms,
    label:String,
    texts: String,
    key:MyFormKeys,
    formDP:String
) {
    val state = easyForm.addAndGetCustomState(
        key, MyEasyFormsCustomDateState(
            defaultValue=texts,
            easyFormsValidationType = NameValidationType
        )
    )
    val text = state.state
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val year: Int
    val month: Int
    val day: Int
    val calendar = Calendar.getInstance()
    calendar.time = Date()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)

    val date = remember { mutableStateOf("") }
    val themeResId: Int = 0
    val datePickerDialog = DatePickerDialog(
        context,
        themeResId,
        { _: DatePicker, yyyy: Int, mm: Int, dd: Int ->
            val c = Calendar.getInstance()
            c.set(yyyy, mm, dd, 0, 0);
            val d = c.time
            date.value = dateShort(formDP,d)
            text.value= date.value
        },
        year,
        month,
        day
    )
    //datePickerDialog.datePicker.locale = Locale("es")
    datePickerDialog.setOnDismissListener {
        focusManager.clearFocus()
        Log.i("VERRRD", date.value.toString())
        state.errorState.value = when (date.value.toString()!="") {
            true -> EasyFormsErrorState.VALID
            false -> EasyFormsErrorState.INVALID
        }
    }

    OutlinedTextField(
        value =  text.value,
        onValueChange = {
            Log.i("VERRR", "quiza: ")
        },
        label = { Text(text = label) },
        placeholder = { Text(text = label) },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {
                if (it.isFocused) {
                    datePickerDialog.show()
                    Log.i("VERRR", "LLEGO AQUI: ")
                }
            },
        isError = (state.errorState.value == EasyFormsErrorState.INVALID),
    )
}

@Composable
fun TimePickerCustom(
    easyForm: EasyForms,
    label:String,
    texts: String,
    key:MyFormKeys,
    formDP:String
) {
    val state = easyForm.addAndGetCustomState(
        key, MyEasyFormsCustomDateState(
            defaultValue=texts,
            easyFormsValidationType = NameValidationType
        )
    )
    val text = state.state
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val hora: Int
    val minuto: Int
    val segundo: Int
    val calendar = Calendar.getInstance()
    calendar.time = Date()
    hora = calendar.get(Calendar.HOUR)
    minuto = calendar.get(Calendar.MINUTE)
    segundo=calendar.get(Calendar.SECOND)

    val date = remember { mutableStateOf("") }
    val themeResId: Int = 0

    val timePickerDialog = TimePickerDialog(
        context,
        themeResId,
        { _: TimePicker, hh: Int, mm: Int->
            val c = Calendar.getInstance()
            c.set(0,0,0,hh, mm,0);
            val d = c.time
            date.value = timeShort(formDP, d)
            text.value = date.value
        },
        hora,
        minuto,
        true
    )
    //datePickerDialog.datePicker.locale = Locale("es")
    timePickerDialog.setOnDismissListener {
        focusManager.clearFocus()
        Log.i("VERRRD", date.value.toString())
        state.errorState.value = when (date.value.toString()!="") {
            true -> EasyFormsErrorState.VALID
            false -> EasyFormsErrorState.INVALID
        }
    }

    OutlinedTextField(
        value =  text.value,
        onValueChange = {
            Log.i("VERRR", "quiza: ")
        },
        label = { Text(text = label) },
        placeholder = { Text(text = label) },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {
                if (it.isFocused) {
                    timePickerDialog.show()
                    Log.i("VERRR", "LLEGO AQUI: ")
                }
            },
        isError = (state.errorState.value == EasyFormsErrorState.INVALID),
    )
    pe.edu.upeu.ui.presentation.components.Spacer(size=2)
}

@Composable
fun NameTextField(easyForms: EasyForms, text: String, label:String, key:MyFormKeys) {
    val textFieldState = easyForms.getTextFieldState(
        key = key,
        easyFormsValidationType = NameValidationType,
        defaultValue = text,
    )
    val state = textFieldState.state
    OutlinedTextField(
        value = state.value,
        onValueChange = textFieldState.onValueChangedCallback,
        label = { Text(text = label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        isError = textFieldState.errorState.value == EasyFormsErrorState.INVALID,
    )
    pe.edu.upeu.ui.presentation.components.Spacer(size=2)
}


@Composable
fun EmailTextField(easyForms: EasyForms, text: String, label:String, tipo:String) {
    val textFieldState = easyForms.getTextFieldState(
        key = MyFormKeys.EMAIL,
        easyFormsValidationType = EmailValidationType,
        defaultValue = text,
    )
    val state = textFieldState.state
    OutlinedTextField(
        value = state.value,
        onValueChange = textFieldState.onValueChangedCallback,
        label = { Text(text = label) },
        modifier = if(tipo=="U") Modifier.wrapContentWidth() else Modifier.fillMaxWidth(),
        isError = textFieldState.errorState.value == EasyFormsErrorState.INVALID,
    )
}

@Composable
fun PasswordTextField(easyForms: EasyForms,text: String, label:String,
                      passwordVisibility: MutableState<Boolean> = remember { mutableStateOf(false) }
                      ) {
    val textFieldState = easyForms.getTextFieldState(
        key = MyFormKeys.PASSWORD,
        easyFormsValidationType = PasswordValidationType,
        defaultValue = text,
    )
    val state = textFieldState.state
    //val passwordVisibility: Boolean by remember { mutableStateOf(false) }
        OutlinedTextField(
        value = state.value,
        label = { Text(text = label) },
        onValueChange = textFieldState.onValueChangedCallback,
        isError = textFieldState.errorState.value == EasyFormsErrorState.INVALID,
        visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisibility.value)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // Please provide localized description for accessibility services
                val description = if (passwordVisibility.value) "Hide password" else "Show password"

                IconButton(
                    onClick = {passwordVisibility.value=!passwordVisibility.value }){
                    Icon(imageVector  = image, description)
                }
            }
    )
}

object MyCustomLengthValidationType : EasyFormsValidationType(
    minLength = 8,
    maxLength = 8,
)

@Composable
fun DNITextField(easyForms: EasyForms, text: String, label:String) {
    val textFieldState = easyForms.getTextFieldState(MyFormKeys.DNI,
        MyCustomLengthValidationType,
        defaultValue = text,
    )
    val state = textFieldState.state
    TextField(
        value = state.value,
        onValueChange = textFieldState.onValueChangedCallback,
        label = { Text(text = label) },
        modifier = Modifier.fillMaxWidth(),
        isError = textFieldState.errorState.value == EasyFormsErrorState.INVALID,
    )
}

@Composable
fun PhoneTextField(easyForms: EasyForms, text: String, label:String) {
    val textFieldState = easyForms.getTextFieldState(
        key = MyFormKeys.PHONE,
        easyFormsValidationType = PhoneNumberValidationType,
        defaultValue = text,
    )
    val state = textFieldState.state
    TextField(
        value = state.value,
        onValueChange = textFieldState.onValueChangedCallback,
        label = { Text(text = label) },
        modifier = Modifier.fillMaxWidth(),
        isError = textFieldState.errorState.value == EasyFormsErrorState.INVALID,
    )
}





@Composable
fun AccionButtonSuccess(
    easyForms: EasyForms,
    label:String,
    id:Int,
    onClick: () -> Unit,

) {
    val errorStates = easyForms.observeFormStates()
    Log.i("DATOCC", id.toString())
    if(id==0){
    Button(
        onClick = onClick,
        modifier = Modifier.wrapContentWidth(),
        enabled = errorStates.value.all {it.value == EasyFormsErrorState.VALID }
    ) {Text(label)}
    }else{
        Button(
            onClick = onClick,
            modifier = Modifier.wrapContentWidth(),
            //enabled = errorStates.value.all {it.value == EasyFormsErrorState.VALID }
        ) {Text(label)}
    }
}

@Composable
fun AccionButtonCancel(
    easyForms: EasyForms,
    label:String,
    onClick: () -> Unit,

) {
    val errorStates = easyForms.observeFormStates()
    Button(
        onClick = onClick,
        modifier = Modifier.wrapContentWidth(),
    ) {
        Text(label)
    }
}

@Composable
fun LoginButton(
    easyForms: EasyForms,
    onClick: () -> Unit,
    label:String
) {
    val errorStates = easyForms.observeFormStates()
    Spacer(modifier = Modifier.height(5.dp))
    Button(
        onClick = onClick,
        modifier = Modifier.size(width = 200.dp, height = 70.dp),
        enabled = errorStates.value.all {it.value == EasyFormsErrorState.VALID}
    ) {
        Text(label, fontSize = 40.sp)
    }
}

fun dateShort(formF:String,r: Date?): String {
    return if (r != null) SimpleDateFormat(formF).format(r) else ""
}
fun timeShort(formF:String, r: Date?): String { //"HH:mm:ss"
    return if (r != null) SimpleDateFormat(formF).format(r) else ""
}