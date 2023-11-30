package com.rdt.sepatuku.ui.screen.profile

import android.provider.ContactsContract.Profile
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rdt.sepatuku.R
import com.rdt.sepatuku.ui.theme.JetItemTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier.background(color = Color.LightGray)
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        item{
            Row(modifier = modifier.fillMaxWidth()){
                Text(
                    text = "Profil Saya",
                    fontWeight = FontWeight.Bold,
                    fontSize = 19.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))
            Image(
                painter = painterResource(R.drawable.profile_pict),
                contentDescription = "profile",
                modifier = modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp),
            )
            Text(
                text = "Raditya Dimas Libriawan",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(1f)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "radityadvz@gmail.com",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){
    JetItemTheme {
        ProfileScreen()
    }
}