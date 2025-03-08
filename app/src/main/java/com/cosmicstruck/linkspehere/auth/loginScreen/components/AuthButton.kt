package com.cosmicstruck.linkspehere.auth.loginScreen.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cosmicstruck.linkspehere.R

@Composable
fun AuthButton(
    @DrawableRes logo: Int,
    name: String,
    onClick: ()-> Unit,
    modifier: Modifier = Modifier) {
    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        onClick = {onClick()}
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                tint = Color.Unspecified,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(20.dp),
                painter = painterResource(logo),
                contentDescription = "Google Login"
            )
            Text(
                text = name,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}