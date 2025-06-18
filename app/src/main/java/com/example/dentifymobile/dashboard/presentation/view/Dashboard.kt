package com.example.dentifymobile.dashboard.presentation.view

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.dentifymobile.dashboard.presentation.di.DashboardModule
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.max
import kotlin.random.Random


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard() {
    val context = LocalContext.current
    val viewModel = remember { DashboardModule.provideDashboardViewModel(context) }
    val state by viewModel.dashboardState.collectAsState()
    val shimmer = rememberShimmerAnimation()

    LaunchedEffect(Unit) {
        viewModel.loadDashboardData()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Resumen de datos",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black,
                        modifier = Modifier.offset(x = 4.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier
                    .background(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFFABE0D5), Color(0xFFABE0D5))
                        )
                    )
            )
        },
        containerColor = Color(0xFFABE0D5)
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFFD1F2EB), Color(0xFFD1F2EB))
                    )
                )
                .drawBehind { drawParticles() }
        ) {
            state?.let { it1 ->
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        LowStockSection(
                            items = state!!.lowStockItems?.map {
                                LowStockItem(
                                    name = it.name.toString(),
                                    stock = it.stockQuantity ?: 0,
                                    maxStock = 100 // Adjust based on your data
                                )
                            } ?: listOf(),
                            shimmer = shimmer
                        )
                    }
                    item {
                        PaymentsSection(
                            items = state!!.recentPayments?.map {
                                PaymentItem(
                                    amount = it.amount ?: 0.0,
                                    date = it.createdAt ?: "N/A"
                                )
                            } ?: listOf(),
                            shimmer = shimmer
                        )
                    }
                    item {
                        AppointmentsSection(
                            items = state!!.recentAppointments?.map {
                                AppointmentItem(
                                    reason = it.reason ?: "Sin motivo",
                                    date = it.appointmentDate ?: "N/A",
                                    duration = it.duration ?: "N/A"
                                )
                            } ?: listOf(),
                            shimmer = shimmer
                        )
                    }
                }
            }
        }
    }
}

data class LowStockItem(val name: String, val stock: Int, val maxStock: Int)
data class PaymentItem(val amount: Double, val date: String)
data class AppointmentItem(val reason: String, val date: String, val duration: String)

@Composable
fun SkeletonLoadingScreen(shimmer: Float) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        repeat(3) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xFFB0C4DE).copy(alpha = 0.6f),
                                Color(0xFFCED4DA).copy(alpha = 0.6f)
                            ),
                            start = Offset(shimmer * 1000f, 0f),
                            end = Offset(shimmer * 1000f + 1000f, 0f)
                        )
                    )
            ) {}
        }
    }
}

@Composable
fun LowStockSection(items: List<LowStockItem>, shimmer: Float) {
    GlassmorphicCard(title = "Items con Bajo Stock", icon = Icons.Default.Inventory, contentColor = Color(0xFFF59E0B)) {
        if (items.isEmpty()) {
            Text(
                text = "No hay datos disponibles",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF6B7280),
                modifier = Modifier.padding(16.dp)
            )
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items.take(3).forEach { item ->
                    CircularStockIndicator(
                        name = item.name,
                        stock = item.stock,
                        maxStock = item.maxStock,
                        color = Color(0xFFF59E0B)
                    )
                }
            }
        }
    }
}

@Composable
fun PaymentsSection(items: List<PaymentItem>, shimmer: Float) {
    GlassmorphicCard(title = "Últimos Pagos", icon = Icons.Default.Payments, contentColor = Color(0xFF10B981)) {
        if (items.isEmpty()) {
            Text(
                text = "No hay datos disponibles",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF6B7280),
                modifier = Modifier.padding(16.dp)
            )
        } else {
            Column {
                Text(
                    text = "Total: S/. ${items.sumOf { it.amount }.format(2)}",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF1F2937),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                SparklinePayments(
                    payments = items.take(5),
                    color = Color(0xFF10B981),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@Composable
fun AppointmentsSection(items: List<AppointmentItem>, shimmer: Float) {
    GlassmorphicCard(title = "Citas Recientes", icon = Icons.Default.CalendarToday, contentColor = Color(0xFF4A90E2)) {
        if (items.isEmpty()) {
            Text(
                text = "No hay datos disponibles",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF6B7280),
                modifier = Modifier.padding(16.dp)
            )
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items.take(3).forEachIndexed { index, item ->
                    EnhancedTimelineItem(
                        reason = item.reason,
                        date = item.date,
                        duration = item.duration,
                        color = Color(0xFF4A90E2),
                        index = index
                    )
                }
            }
        }
    }
}

@Composable
fun GlassmorphicCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    contentColor: Color,
    content: @Composable ColumnScope.() -> Unit
) {
    var scale by remember { mutableStateOf(1f) }
    val interactionSource = remember { MutableInteractionSource() }
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0x1AFFFFFF)),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { isExpanded = !isExpanded }
            .scale(scale)
            .background(
                Brush.linearGradient(
                    colors = listOf(Color(0x1AFFFFFF), Color(0x0FFFFFFF))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .animateContentSize(animationSpec = tween(400))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = contentColor,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                    fontSize = 22.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            AnimatedVisibility(
                visible = isExpanded,
                enter = fadeIn() + scaleIn(),
                exit = fadeOut() + scaleOut()
            ) {
                Column(content = content)
            }
        }
    }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> scale = 0.97f
                is PressInteraction.Release, is PressInteraction.Cancel -> scale = 1f
            }
        }
    }
}

@Composable
fun CircularStockIndicator(name: String, stock: Int, maxStock: Int, color: Color) {
    val progress by animateFloatAsState(
        targetValue = stock.toFloat() / maxStock,
        animationSpec = tween(1000)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .drawBehind {
                    drawCircle(
                        color = color.copy(alpha = 0.2f),
                        radius = size.minDimension / 2
                    )
                    drawArc(
                        color = color,
                        startAngle = -90f,
                        sweepAngle = progress * 360f,
                        useCenter = false,
                        style = Stroke(width = 8.dp.toPx(), cap = StrokeCap.Round)
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "${(progress * 100).toInt()}%",
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "$name ($stock/$maxStock)",
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF1F2937),
            fontSize = 16.sp
        )
    }
}

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun SparklinePayments(payments: List<PaymentItem>, color: Color, modifier: Modifier = Modifier) {
    val maxAmount = payments.maxOfOrNull { it.amount }?.toFloat() ?: 1f
    val normalizedAmounts = payments.map { it.amount.toFloat() / maxAmount }
    val transition = updateTransition(targetState = normalizedAmounts, label = "sparkline")
    val animatedValues = normalizedAmounts.mapIndexed { index, value ->
        transition.animateFloat(
            transitionSpec = { tween(600) },
            label = "dot$index"
        ) { value }.value
    }
    var hoveredIndex by remember { mutableStateOf(-1) }
    var canvasSize by remember { mutableStateOf(Size.Zero) }

    Box(modifier = modifier) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .onSizeChanged { canvasSize = it.toSize() }
                .pointerInput(Unit) {
                    detectTapGestures { offset ->
                        val spacing = canvasSize.width / (payments.size + 1)
                        val index = (offset.x / spacing).toInt() - 1
                        hoveredIndex = if (index in payments.indices) index else -1
                    }
                }
        ) {
            val spacing = size.width / (payments.size + 1)
            animatedValues.forEachIndexed { index, value ->
                val x = (index + 1) * spacing
                val y = size.height * (1f - value)
                drawCircle(
                    color = color.copy(alpha = if (index == hoveredIndex) 1f else 0.8f),
                    radius = if (index == hoveredIndex) 10.dp.toPx() else 8.dp.toPx(),
                    center = Offset(x, y)
                )
                if (index < payments.size - 1) {
                    val nextX = (index + 2) * spacing
                    val nextY = size.height * (1f - animatedValues[index + 1])
                    drawLine(
                        color = color.copy(alpha = 0.4f),
                        start = Offset(x, y),
                        end = Offset(nextX, nextY),
                        strokeWidth = 2.dp.toPx()
                    )
                }
            }
        }
        if (hoveredIndex != -1 && canvasSize != Size.Zero) {
            val payment = payments[hoveredIndex]
            val offsetX = ((hoveredIndex + 1) * (canvasSize.width / (payments.size + 1)) - canvasSize.width / (payments.size + 1) / 2).dp
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(x = offsetX)
                    .background(Color(0xCC000000), RoundedCornerShape(8.dp))
                    .padding(8.dp)
            ) {
                Text(
                    text = "S/. ${payment.amount.format(2)}\n${payment.date.formatDate()}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            payments.forEach { payment ->
                Text(
                    text = payment.date.formatDate().substring(0, 6), // e.g., "17 Jun"
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF6B7280),
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun EnhancedTimelineItem(reason: String, date: String, duration: String, color: Color, index: Int) {
    var isHovered by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (isHovered) 1.02f else 1f, animationSpec = tween(200))
    val offsetY by animateDpAsState(
        targetValue = 0.dp,
        animationSpec = tween(400, delayMillis = index * 100)
    )

    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0x1AFFFFFF)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = offsetY)
            .scale(scale)
            .clickable { /* Navigate to appointment details */ }
            .background(
                Brush.linearGradient(
                    colors = listOf(Color(0x1AFFFFFF), Color(0x0FFFFFFF))
                )
            )
            .pointerInput(Unit) {
                detectHover { isHovered = it }
            }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(color.copy(alpha = 0.1f), CircleShape)
                    .border(1.dp, color, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = reason,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF1F2937),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "${date.formatDate()} • ${duration.formatDuration()}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF6B7280),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun rememberShimmerAnimation(): Float {
    var shimmer by remember { mutableStateOf(0f) }
    LaunchedEffect(Unit) {
        while (true) {
            shimmer = (shimmer + 0.01f) % 1f
            delay(16)
        }
    }
    return shimmer
}

fun DrawScope.drawParticles() {
    repeat(20) {
        val x = Random.nextFloat() * size.width
        val y = Random.nextFloat() * size.height
        val radius = Random.nextFloat() * 2f
        drawCircle(
            color = Color.White.copy(alpha = 0.1f),
            center = Offset(x, y),
            radius = radius
        )
    }
}

fun String.formatDate(): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale("es", "PE"))
        val date = inputFormat.parse(this)
        outputFormat.format(date)
    } catch (e: Exception) {
        this
    }
}

fun String.formatDuration(): String {
    return try {
        val minutes = toIntOrNull() ?: return this
        if (minutes < 60) "$minutes min"
        else "${minutes / 60}h ${minutes % 60}m"
    } catch (e: Exception) {
        this
    }
}

fun Double.format(decimals: Int): String {
    return "%.${decimals}f".format(this)
}

fun PointerInputScope.detectHover(onHover: (Boolean) -> Unit) {
    detectHoverGestures(
        onHoverStart = { onHover(true) },
        onHoverEnd = { onHover(false) }
    )
}

private fun PointerInputScope.detectHoverGestures(
    onHoverStart: () -> Unit,
    onHoverEnd: () -> Unit
) {
}