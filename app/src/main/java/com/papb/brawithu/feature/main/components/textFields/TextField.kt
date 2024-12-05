package com.papb.brawithu.feature.main.components.textFields

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.papb.brawithu.ui.theme.CustBased1
import com.papb.brawithu.ui.theme.CustBased5
import com.papb.brawithu.ui.theme.CustPrimary5

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultField(
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .background(CustBased1, shape = RoundedCornerShape(20))
            .height(55.dp),

        value = value,
        shape = RoundedCornerShape(20),
        onValueChange = {
            onValueChange(it)
        },
        placeholder = { Text(placeholder, color = CustBased5, style = MaterialTheme.typography.bodySmall) },
        textStyle = TextStyle(color = Color.Black),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Black,
            focusedBorderColor = CustPrimary5,
            cursorColor = CustPrimary5
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    val visualTransformation = if (isPasswordVisible) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .background(CustBased1, shape = RoundedCornerShape(20))
            .height(55.dp),

        value = value,
        shape = RoundedCornerShape(20),
        onValueChange = {
            onValueChange(it)
        },
        placeholder = { Text(placeholder, color = CustBased5, style = MaterialTheme.typography.bodySmall) },
        textStyle = TextStyle(color = Color.Black),
        singleLine = true,
        visualTransformation = visualTransformation,
        trailingIcon = {
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                val image = if (isPasswordVisible) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }
                Icon(imageVector = image, contentDescription = "Toggle password visibility", tint = Color.Black)
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Black,
            focusedBorderColor = CustPrimary5,
            cursorColor = CustPrimary5
        )
    )
}