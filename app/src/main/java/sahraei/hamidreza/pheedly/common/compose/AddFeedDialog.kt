package sahraei.hamidreza.pheedly.common.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import sahraei.hamidreza.pheedly.ui.theme.LightGreys90
import sahraei.hamidreza.pheedly.ui.theme.LightPheedly
import sahraei.hamidreza.pheedly.ui.theme.Typography

const val AddFeedDialogTag = "AddFeedDialog"
const val AddFeedTextFieldTag = "AddFeedTextField"

@Composable
fun AddFeedDialog(
    onDismissRequest: (() -> Unit)? = null,
    onConfirmClicked: (String) -> Unit
) {
    var textState by rememberSaveable { mutableStateOf(TextFieldValue().text) }

    Dialog(
        onDismissRequest = {
            onDismissRequest?.invoke()
        },
        content = {
            Card(
                modifier = Modifier
                    .testTag(AddFeedDialogTag)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    TextField(
                        value = textState,
                        onValueChange = {
                            textState = it
                        },
                        label = {
                            Text(text = "Url")
                        },
                        placeholder = {
                            Text(text = "Enter the RSS URL here...")
                        },
                        textStyle = Typography.subtitle2,
                        modifier = Modifier
                            .testTag(AddFeedTextFieldTag)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Button(
                        onClick = {
                            onConfirmClicked.invoke(textState)
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = LightPheedly),
                    ) {
                        Text(
                            text = "Add",
                            color = LightGreys90
                        )
                    }
                }
            }
        }
    )
}