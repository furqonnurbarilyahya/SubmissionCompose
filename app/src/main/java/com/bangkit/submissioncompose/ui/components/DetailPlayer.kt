package com.bangkit.submissioncompose.ui.components

import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.submissioncompose.R
import com.bangkit.submissioncompose.ui.theme.SubmissionComposeTheme

@Composable
fun detailPlayer(
    photoUrl: Int,
    name: String,
    desc: String
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding()
    ){
        Box (modifier = Modifier.fillMaxWidth()) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.back),
                modifier = Modifier
                    .padding(16.dp, 16.dp, 16.dp, 0.dp)
                    .align(Alignment.CenterStart)
                    .clickable {  }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            textAlign = TextAlign.Center,
            text = name,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(170.dp)
            )
        Spacer(modifier = Modifier.height(15.dp))
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 0.dp)) {
            Text(text = desc)
        }
    }
}

@Composable
@Preview (showBackground = true, showSystemUi = true, device = Devices.PIXEL_3A)
fun preview() {
    SubmissionComposeTheme {
        detailPlayer(photoUrl = R.drawable.furqon_bgbiru, name = "Wayne Rooney", desc = "Wayne Mark Rooney /ˈruːni/; lahir 24 Oktober 1985) adalah seorang pelatih dan mantan pemain sepak bola profesional yang kini menjadi kepala pelatih klub Kejuaraan EFL Birmingham City F.C.. Sebagai pemain profesional, Rooney dikenal saat bermain sebagai penyerang untuk klub-klub seperti Everton, Manchester United, D.C. United, dan Derby County, serta tim nasional Inggris.\n" +
                "\n" +
                "Pada saat berumur sembilan tahun, Rooney bergabung dengan tim muda dari Everton, dan ia membuat debut profesional pada tahun 2002 pada usia 16 tahun. Ia menghabiskan dua musim di klub Merseyside tersebut, sebelum pindah ke Manchester United seharga £ 25.600.000 di jendela transfer musim panas 2004. Pada tahun yang sama, Rooney memperoleh julukan \"Wazza\".[3] Sejak itu, dengan Rooney berada dalam tim, United telah memenangkan Liga Premier lima kali, Liga Champions sekali, Piala Dunia Antarklub FIFA sekali dan Piala Liga dua kali. Pada Maret 2014, Rooney mencetak gol ke-212 untuk United, membuatnya menjadi pencetak gol tertinggi ketiga sepanjang masa bagi klub.[4]\n" +
                "\n" +
                "Rooney menjalani debut internasional senior pada tahun 2003, menjadi pemain termuda yang mewakili Inggris (rekor dipatahkan oleh Theo Walcott). Dia pernah menjadi pencetak gol termuda Inggris.[5] Ia bermain di UEFA Euro 2004 dan mencetak empat gol, menjadi pencetak gol termuda dalam sejarah Piala Eropa. Rooney tampil di Piala Dunia 2006 dan 2010 dan secara luas dianggap sebagai pemain terbaik untuk negaranya.[6][7][8][9][10] Dia telah memenangkan Inggris Player of the Year dua kali, tahun 2008 dan 2009. Pada Juni 2014, ia telah mengemas 95 caps internasional dan mencetak 40 gol, membuatnya menjadi pencetak gol kelima tertinggi Inggris dalam sejarah.[11] Seiring dengan David Beckham, Rooney adalah pemain paling sering dikartu merah untuk Inggris, yang telah diterima sebanyak dua kali.[12]\n" +
                "\n" +
                "Pada 2009-10, Rooney dianugerahi PFA Players 'Player of the Year dan FWA Footballer of the Year. Dia telah memenangkan penghargaan Premier League Player of the Month lima kali, rekornya berbagi dengan Steven Gerrard. Dia masuk nominasi kelima dalam pemungutan suara untuk 2011 FIFA Ballon d'Or dan masuk di FIFPro Dunia 11 untuk 2011. Rooney telah memenangkan 'Gol of the Season' penghargaan oleh BBC Match of the Day dari jajak pendapat pada tiga kesempatan, dengan tendangan sepeda melawan rival Manchester City dan memenangkan penghargaan 'Gol Premier League of the 20 Seasons'.[13] Sebuah laporan pada Maret 2011 tercatat Rooney sebagai pemain sepak bola dengan bayaran tertinggi ketiga di dunia setelah Lionel Messi dan Cristiano Ronaldo, dengan pendapatan tahunan € 20.700.000 (£ 18 juta) termasuk penawaran dari sponsorship.[14]" )
    }
}