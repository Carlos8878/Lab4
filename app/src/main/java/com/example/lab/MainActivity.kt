@file:OptIn(ExperimentalLayoutApi::class)

package com.example.bienvenidoalcurso

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Box {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ComponentesExploracion(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@ExperimentalLayoutApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentesExploracion(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Exploración de Componentes", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        // ==================== CONTENEDORES (10) ====================
        Text("CONTENEDORES", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)

        // 1. LazyColumn
        CardExample("1. LazyColumn - Lista vertical con scroll")
        LazyColumn(modifier = Modifier.height(120.dp)) {
            items(listOf("Item 1", "Item 2", "Item 3")) { item ->
                Text(item, modifier = Modifier.padding(8.dp))
            }
        }

        // 2. LazyRow
        CardExample("2. LazyRow - Lista horizontal")
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(listOf("A", "B", "C", "D")) { item ->
                Surface(modifier = Modifier.size(50.dp), shape = RoundedCornerShape(8.dp)) {
                    Box(contentAlignment = Alignment.Center) { Text(item) }
                }
            }
        }

        // 3. Grid (LazyVerticalGrid)
        CardExample("3. LazyVerticalGrid - Grid")
        LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.height(150.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            items((1..9).toList()) { item ->
                Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(4.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer) {
                    Box(modifier = Modifier.aspectRatio(1f), contentAlignment = Alignment.Center) { Text("$item") }
                }
            }
        }

        // 4. ConstraintLayout
        CardExample("4. ConstraintLayout - Posicionamiento relativo")
        Box(modifier = Modifier.fillMaxWidth().height(80.dp)) {
            Text("Arriba-Izq", modifier = Modifier.align(Alignment.TopStart))
            Text("Centro", modifier = Modifier.align(Alignment.Center))
            Text("Abajo-Der", modifier = Modifier.align(Alignment.BottomEnd))
        }

        // 5. Scaffold
        CardExample("5. Scaffold - Estructura Material")
        Text("TopBar, FAB, Snackbar", fontSize = 12.sp)

        // 6. Surface
        CardExample("6. Surface - Contenedor con elevación")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Surface(modifier = Modifier.size(40.dp), shadowElevation = 4.dp, shape = RoundedCornerShape(8.dp), color = Color.Blue) { }
            Surface(modifier = Modifier.size(40.dp), shadowElevation = 8.dp, shape = RoundedCornerShape(8.dp), color = Color.Green) { }
        }

        // 7. Chip
        CardExample("7. Chip - Etiquetas")
        var selectedChip by remember { mutableStateOf("Chip 1") }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("Chip 1", "Chip 2", "Chip 3").forEach { chip ->
                FilterChip(selected = selectedChip == chip, onClick = { selectedChip = chip }, label = { Text(chip) })
            }
        }

        // 8. BackdropScaffold
        CardExample("8. BackdropScaffold - Capas superpuestas")
        Text("Front/back layers", fontSize = 12.sp)

        // 9. FlowRow
        CardExample("9. FlowRow - Flujo horizontal")
        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("Tag1", "Tag2", "Tag3", "Tag4").forEach { tag ->
                Surface(shape = RoundedCornerShape(16.dp), color = MaterialTheme.colorScheme.primaryContainer) {
                    Text(tag, modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp))
                }
            }
        }

        // 10. FlowColumn
        CardExample("10. FlowColumn - Flujo vertical")
        Box(modifier = Modifier.height(80.dp)) {
            FlowColumn(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                repeat(4) { i ->
                    Surface(color = MaterialTheme.colorScheme.tertiaryContainer) { Text("Item $i", modifier = Modifier.padding(8.dp)) }
                }
            }
        }

        // ==================== CONTROLES - PARTE 1 (11) ====================
        Text("CONTROLES - Parte 1", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)

        // 11. AlertDialog
        CardExample("11. AlertDialog - Diálogo alerta")
        var showAlert by remember { mutableStateOf(false) }
        Button(onClick = { showAlert = true }) { Text("Mostrar Alert") }
        if (showAlert) {
            AlertDialog(onDismissRequest = { showAlert = false }, title = { Text("Alerta") },
                text = { Text("Mensaje") }, confirmButton = { Button(onClick = { showAlert = false }) { Text("OK") } })
        }

        // 12. Card
        CardExample("12. Card - Tarjeta")
        Card(modifier = Modifier.fillMaxWidth().height(60.dp), elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)) {
            Box(contentAlignment = Alignment.Center) { Text("Card") }
        }

        // 13. Checkbox
        CardExample("13. Checkbox - Casilla")
        var checked by remember { mutableStateOf(true) }
        Row { Text("Checkbox:"); Checkbox(checked = checked, onCheckedChange = { checked = it }) }

        // 14. FloatingActionButton
        CardExample("14. FloatingActionButton")
        FloatingActionButton(onClick = { }) { Icon(Icons.Default.Add, "Add") }

        // 15. Icon
        CardExample("15. Icon - Iconos")
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Icon(Icons.Default.Favorite, "Favorite", tint = Color.Red)
            Icon(Icons.Default.Star, "Star", tint = Color.Yellow)
            Icon(Icons.Default.Home, "Home")
        }

        // 16. Image
        CardExample("16. Image - Imagen")
        Box(modifier = Modifier.fillMaxWidth().height(80.dp), contentAlignment = Alignment.Center) {
            Text("🖼️ Imagen", color = Color.Gray)
        }

        // 17. ProgressBar
        CardExample("17. ProgressBar - Progreso")
        var progress by remember { mutableFloatStateOf(0.5f) }
        LinearProgressIndicator(progress = { progress }, modifier = Modifier.fillMaxWidth())
        Slider(value = progress, onValueChange = { progress = it }, valueRange = 0f..1f)

        // 18. RadioButton
        CardExample("18. RadioButton - Selección única")
        var radioSelected by remember { mutableStateOf("A") }
        listOf("A", "B").forEach { opt ->
            Row { RadioButton(selected = radioSelected == opt, onClick = { radioSelected = opt }); Text(opt) }
        }

        // 19. Slider
        CardExample("19. Slider - Deslizador")
        var sliderVal by remember { mutableFloatStateOf(50f) }
        Slider(value = sliderVal, onValueChange = { sliderVal = it }, valueRange = 0f..100f)
        Text("Valor: ${sliderVal.toInt()}")

        // 20. Spacer
        CardExample("20. Spacer - Espaciador")
        Text("Arriba"); Spacer(Modifier.height(16.dp)); Text("Abajo")

        // 21. Switch
        CardExample("21. Switch - Interruptor")
        var switchState by remember { mutableStateOf(true) }
        Row { Text("Switch:"); Switch(checked = switchState, onCheckedChange = { switchState = it }) }

        // 22. TopAppBar
        CardExample("22. TopAppBar - Barra superior")
        TopAppBar(title = { Text("Mi App") })

        // ==================== CONTROLES - PARTE 2 (11) ====================
        Text("CONTROLES - Parte 2", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)

        // 23. BottomNavigation
        CardExample("23. BottomNavigation")
        var navSelected by remember { mutableIntStateOf(0) }
        NavigationBar {
            listOf(Pair("Home", Icons.Default.Home), Pair("Search", Icons.Default.Search)).forEachIndexed { idx, item ->
                NavigationBarItem(icon = { Icon(item.second, item.first) }, label = { Text(item.first) },
                    selected = navSelected == idx, onClick = { navSelected = idx })
            }
        }

        // 24. Dialog
        CardExample("24. Dialog - Diálogo")
        var showDialog by remember { mutableStateOf(false) }
        Button(onClick = { showDialog = true }) { Text("Mostrar Dialog") }
        if (showDialog) {
            Dialog(onDismissRequest = { showDialog = false }) {
                Surface(shape = RoundedCornerShape(16.dp)) {
                    Column(modifier = Modifier.padding(24.dp)) { Text("Dialog"); Button(onClick = { showDialog = false }) { Text("Cerrar") } }
                }
            }
        }

        // 25. Divider
        CardExample("25. Divider - Divisor")
        Text("Arriba"); HorizontalDivider(); Text("Abajo")

        // 26. DropDownMenu
        CardExample("26. DropDownMenu")
        var expanded by remember { mutableStateOf(false) }
        var selectedMenu by remember { mutableStateOf("Seleccione") }
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            OutlinedTextField(value = selectedMenu, onValueChange = {}, readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }, modifier = Modifier.menuAnchor())
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                listOf("Opción 1", "Opción 2").forEach { opt ->
                    DropdownMenuItem(text = { Text(opt) }, onClick = { selectedMenu = opt; expanded = false })
                }
            }
        }

        // 27. NavigationRail
        CardExample("27. NavigationRail - Navegación lateral")
        Row(modifier = Modifier.height(100.dp)) {
            NavigationRail {
                NavigationRailItem(icon = { Icon(Icons.Default.Home, "Home") }, label = { Text("Home") },
                    selected = true, onClick = { })
            }
            Divider(modifier = Modifier.height(100.dp))
            Text("Contenido", modifier = Modifier.padding(16.dp))
        }

        // 28. OutlinedTextField
        CardExample("28. OutlinedTextField")
        var textVal by remember { mutableStateOf("") }
        OutlinedTextField(value = textVal, onValueChange = { textVal = it }, label = { Text("Texto") }, modifier = Modifier.fillMaxWidth())

        // 29. Pager
        CardExample("29. Pager - Desplazamiento")
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Surface(shape = RoundedCornerShape(8.dp), color = MaterialTheme.colorScheme.primary) { Text("Pág 1", modifier = Modifier.padding(16.dp)) }
            Surface(shape = RoundedCornerShape(8.dp), color = Color.Gray) { Text("Pág 2", modifier = Modifier.padding(16.dp)) }
        }

        // 30. Snackbar
        CardExample("30. Snackbar - Notificación")
        Text("Notificación temporal", fontSize = 12.sp)

        // 31. TabRow
        CardExample("31. TabRow - Pestañas")
        var tabSelected by remember { mutableIntStateOf(0) }
        TabRow(selectedTabIndex = tabSelected) {
            listOf("Tab 1", "Tab 2").forEachIndexed { idx, title ->
                Tab(selected = tabSelected == idx, onClick = { tabSelected = idx }, text = { Text(title) })
            }
        }

        // 32. Tooltip
        CardExample("32. Tooltip - Información")
        IconButton(onClick = { }) {
            Icon(Icons.Default.Info, "Info")
        }

        Spacer(Modifier.height(32.dp))
    }
}

@Composable
fun CardExample(title: String) {
    Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
        Text(text = title, modifier = Modifier.padding(12.dp), fontWeight = FontWeight.Bold, fontSize = 13.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewComponentes() {
    Box { ComponentesExploracion() }
    @Composable
    fun ViewHolaCurso() {
        Column(
            modifier = Modifier.run {
                fillMaxWith()
                        .padding(16.dp)
            },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to the Course!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Hello, Student!",
                fontSize = 20.sp
            )
        }
    }
}