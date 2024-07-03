package com.example.calculator_compose

import android.provider.CalendarContract
import android.provider.CalendarContract.Colors
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val buttonList = listOf(
    "C", "(", ")", "/",
    "9", "8", "7", "*",
    "6", "5", "4", "-",
    "3", "2", "1", "+",
    "AC", "0", ".", "="

)

@Composable
fun Calculator(modifier: Modifier = Modifier,viewModel: CalculatorViewModel) {

    val equationText = viewModel.equationText.observeAsState()
    val resultText = viewModel.resultText.observeAsState()

    Box(modifier = modifier.background(Color(0xFF71BDAD))) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.End) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = equationText.value?:"",
                style = TextStyle(fontSize = 32.sp, textAlign = TextAlign.End),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding( end = 16.dp)
            )

            Text(
                text = resultText.value?:"",
                style = TextStyle(fontSize = 64.sp, textAlign = TextAlign.End),
                maxLines = 2,
                modifier = Modifier.padding( end = 16.dp)

                )
            Spacer(modifier = Modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),) {
                items(buttonList) {
CalculatorButton(btn = it, ctn = it, onClick = {viewModel.onButtonClick(it)})
                }
            }

        }
    }
}
@Composable
fun CalculatorButton(btn:String,onClick : ()->Unit,ctn: String){
    Column (modifier = Modifier){
        Box(modifier = Modifier.clip(RoundedCornerShape(32.dp))){
            Box(modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 16.dp, top = 16.dp)){
                FloatingActionButton(onClick = onClick,
                    modifier = Modifier.size(80.dp),
                    shape = CircleShape,
                    contentColor = contentColor(ctn ),
                    containerColor = getColor(btn)

                ) {
                    Text(text = btn, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

    }

}

fun getColor(btn : String): Color{
    if (btn == "(" || btn == "C" || btn == ")" )
        return  Color(0xFF1B1B1B)

    if (btn == "/" || btn == "*"|| btn == "+" || btn == "-" || btn == "=")
        return Color (0xFF2A7162)
    return Color(0xFFFFFFFF)
}

fun contentColor(ctn :String):Color{
    if (ctn == "1" || ctn == "2" || ctn == "3" || ctn == "4" || ctn == "5" || ctn == "6" || ctn == "7" ||
        ctn == "8" ||ctn == "9" || ctn == "." || ctn == "0" || ctn == "AC"   )
        return Color.Black
    return Color.White
}
