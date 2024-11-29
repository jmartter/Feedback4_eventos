import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.feedback4_eventos.Base_datos.Novela
import com.example.feedback4_eventos.R
import com.example.feedback4_eventos.loadBitmapFromResource

@Composable
fun NovelList(novelas: List<Novela>, onSelectNovela: (Novela) -> Unit, modifier: Modifier = Modifier) {
    val listState = rememberLazyListState()
    val context = LocalContext.current

    LazyColumn(state = listState, modifier = modifier.fillMaxSize()) {
        items(novelas) { novela ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelectNovela(novela) }
                    .padding(16.dp)
            ) {
                Text(text = novela.titulo, modifier = Modifier.weight(1f))
                if (novela.isFavorite) {
                    Spacer(modifier = Modifier.width(8.dp))
                    val bitmap: Bitmap = loadBitmapFromResource(context, R.drawable.estrella)
                    Image(bitmap = bitmap.asImageBitmap(), contentDescription = "Favorite", modifier = Modifier.size(24.dp))
                }
            }
        }
    }
}