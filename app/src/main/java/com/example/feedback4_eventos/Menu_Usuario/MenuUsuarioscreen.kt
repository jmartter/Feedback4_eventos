package com.example.feedback4_eventos

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.feedback4_eventos.Base_datos.Novela
import com.example.feedback4_eventos.Inicio.LoginActivity

@Composable
fun MenuUsuarioScreen(userName: String, onBack: () -> Unit, onAddNovela: () -> Unit, onViewUserNovelas: () -> Unit, onConfiguracion: () -> Unit, novelas: List<Novela>, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Scaffold { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            IconButton(
                onClick = {
                    val intent = Intent(context, LoginActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 64.dp), // Adjust padding to place the text below the back button
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Bienvenido $userName", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = onAddNovela,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Añadir Novela")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = onViewUserNovelas,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ver Novelas")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = onConfiguracion,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Filled.Settings, contentDescription = "Configuración")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Configuración")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Tus Novelas", fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 200.dp) // Adjust the height as needed
                ) {
                    items(novelas) { novela ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { /* Handle novela click */ }
                                .padding(16.dp)
                        ) {
                            Text(text = novela.titulo, modifier = Modifier.weight(1f))
                            if (novela.isFavorite) {
                                Spacer(modifier = Modifier.width(8.dp))
                                Image(
                                    painter = painterResource(id = R.drawable.estrella),
                                    contentDescription = "Favorite",
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuUsuarioScreenPreview() {
    val sampleNovelas = listOf(
        Novela(titulo = "Novela 1", autor = "Autor 1", anoPublicacion = 2021, sinopsis = "Sinopsis 1", isFavorite = true),
        Novela(titulo = "Novela 2", autor = "Autor 2", anoPublicacion = 2022, sinopsis = "Sinopsis 2", isFavorite = false)
    )
    MenuUsuarioScreen(
        userName = "User",
        onBack = {},
        onAddNovela = {},
        onViewUserNovelas = {},
        onConfiguracion = {},
        novelas = sampleNovelas
    )
}