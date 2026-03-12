package com.example.hotelapp.presentation.components.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotelapp.presentation.components.search.SearchRow
import com.example.hotelapp.presentation.theme.AdditionalColor
import com.example.hotelapp.presentation.theme.MainColor

@Composable
fun HotelSearchCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column {
            Column(modifier = Modifier.padding(16.dp)) {
                SearchRow(
                    icon = Icons.Default.LocationOn,
                    label = "Місто",
                    value = "Київ, Україна"
                )
                SearchRow(
                    icon = Icons.Default.DateRange,
                    label = "Дати",
                    value = "24 лют - 27 лют"
                )
                SearchRow(
                    icon = Icons.Default.Person,
                    label = "Гості",
                    value = "2 дорослих, 1 номер",
                    showDivider = true
                )
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MainColor)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier.size(28.dp),
                        tint = AdditionalColor
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Знайти готелі",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = AdditionalColor
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HotelSearchCardPreview() {
    HotelSearchCard()
}