package com.example.smarthome

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.* // 使用 Material3
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

//主界面，主界面分成Top，Middle，Bottom三部分，可以不管
@Composable
fun HomeScreen(navController: NavController) {
    HomeScreenPage(navController)
}


@Composable
fun HomeScreenPage(navController: NavController) {
    var selectedTab by remember { mutableIntStateOf(0) }
    var searchText by remember { mutableStateOf("") }
    val weatherImageRes by remember { mutableIntStateOf(R.drawable.qq) }
    val weatherInfo = "晴 25°C"
    val itemList = listOf(
        ItemData(
            name = "智能门锁",
            imageRes1 = R.drawable.house,
            onClick = { /* 点击事件 */
                navController.navigate("video")},
            backgroundBrush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFFB7D1FF),
                    Color(0xFFC6F4F8)
                )
            ),
            textColor = Color.White
        ),
        ItemData(
            name = "灯控开关",
            imageRes1 = R.drawable.light,
            onClick = { /* 点击事件 */ },
            backgroundBrush = Brush.horizontalGradient(
                colors = listOf(
                    Color(0xFFB7D1FF),
                    Color(0xFFC6F4F8)
                )
            ),
            textColor = Color.Black
        ),

        ItemData(
        name = "数码设备",
        imageRes1 = R.drawable.tech,
        onClick = { /* 点击事件 */ },
        backgroundBrush = Brush.horizontalGradient(
            colors = listOf(
                Color(0xFFB7D1FF),
                Color(0xFFC6F4F8)
            )
        ),
        textColor = Color.Black
    ),
    ItemData(
        name = "更多",
        imageRes1 = R.drawable.more,
        onClick = { /* 点击事件 */ },
        backgroundBrush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFB7D1FF),
                Color(0xFFC6F4F8)
            )
        ),
        textColor = Color.Black
    )
    )

    Column(
        modifier = Modifier.fillMaxSize()

    ) {
        // 第一部分：搜索框和天气
        HomeTopSection(
            searchText = searchText,
            onSearchTextChange = { searchText = it },
            weatherInfo = weatherInfo,
            weatherImageRes = weatherImageRes, // 传递图片资源
            modifier = Modifier.weight(2f)
        )

        //第二部分：多级列表（如果有项列表可以取消注释）
         HomeMiddleSection(items = itemList, modifier = Modifier.weight(2.5f))

        // 第三部分：底部导航栏
        HomeBottomSection(
            navController = navController,
            selectedTab = selectedTab,
            onTabSelected = { selectedTab = it },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun HomeTopSection(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    weatherInfo: String,
    weatherImageRes: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically // 垂直居中对齐
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            // 搜索框，设置宽度为70%并固定高度
            SearchBar(
                searchText = searchText,
                onSearchTextChange = onSearchTextChange,
                modifier = Modifier
                    .weight(0.7f) // 使用weight来占据70%的宽度
                    .height(56.dp), // 设置固定高度
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search, // 使用系统图标
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            )
            // 天气卡片，设置固定宽度和高度
            WeatherCard(
                weatherImage = painterResource(id = weatherImageRes),
                weatherInfo = weatherInfo,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .size(100.dp, 100.dp) // 设置宽度和固定高度
                    .padding(start = 8.dp) // 添加一些间隔
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .height(180.dp) // 设置高度
                .clip(RoundedCornerShape(16.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFD33838),
                            Color(0xFFF1B489),
                            Color(0xFFFF9054),
                        )
                    )
                )
                .padding(start = 16.dp) // 内边距
        ) {
            Row(
                modifier = modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically // 垂直居中对齐
            ) {
                Column(
                    modifier = modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center // 垂直居中
                ) {
                    // 文本
                    Text(
                        text = "智能家居",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black,
                        modifier = Modifier.padding(8.dp)
                    )
                    // 文本
                    Text(
                        text = "智能家居",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                // 圆形图片
                Box(
                    modifier = Modifier
                        .padding(end = 12.dp) // 移动 Box 的右边界
                        .size(150.dp) // 设置外层 Box 的大小
                        .clip(CircleShape) // 设置为圆形
                        .border(2.dp, Color.Gray, CircleShape) // 设置边框

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.programmer),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize() // 使图片填满 Box
                            .clip(CircleShape) // 确保图片也是圆形
                    )
                }
            }
        }
    }
}


@Composable
fun SearchBar(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null
) {
    TextField(
        value = searchText,
        onValueChange = onSearchTextChange,
        modifier = modifier
            .border(1.dp,Color.Black, RoundedCornerShape(16.dp))
            .background(Color.Transparent, shape = RoundedCornerShape(8.dp)),
        placeholder = { Text(text = "搜索...") },
        singleLine = true,
        leadingIcon = leadingIcon?.let {
            @Composable {
                Box(modifier = Modifier.padding(end = 0.dp)) { // 添加右内边距
                    it()
                }
            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun WeatherCard(weatherImage: Painter, weatherInfo: String, modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, Color.Transparent, RoundedCornerShape(16.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFFA8400),
                        Color(0xFFFFC107),
                    )
            )
        )
    ){
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // 垂直居中
        ) {
            `Anima-tableSun`(Modifier.size(50.dp))
            Text(
                text = weatherInfo,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun HomeMiddleSection(
    items: List<ItemData>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(start = 24.dp)
                .padding(end = 24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "家居列表",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(0.dp)
            )

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward, // 向右的箭头图标
                contentDescription = null,
                tint = Color.Black
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 138.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 0.dp)
        ) {
            items(items) { item ->
                ListItemUI(
                    item = item,
                    onClick = item.onClick
                )
            }
        }

    }
}

@Composable
fun ListItemUI(
    item: ItemData,
    onClick: () -> Unit,
    textColor: Color = Color.Black,
    textStyle: TextStyle = MaterialTheme.typography.titleSmall
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(item.backgroundBrush) // 使用自定义渐变背景
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { onClick() }
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = item.name,
            style = textStyle.copy(color = textColor),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = item.imageRes1),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Transparent)
        )
    }
}

data class ItemData(
    val name: String,
    val imageRes1: Int,
    val onClick: () -> Unit,
    val backgroundBrush: Brush = Brush.verticalGradient(colors = listOf(Color.LightGray, Color.LightGray)),
    val textColor: Color = Color.Black
)

@Composable
fun HomeBottomSection(
    navController: NavController,
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = Color.White,
        contentColor = Color.Black
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "主页") },
            label = { Text("主页") },
            selected = selectedTab == 0,
            onClick = {
                onTabSelected(0)
                navController.navigate("home") {
                    // 移除所有之前的页面，保持只导航到 "home"
                    popUpTo("admin") { inclusive = false }
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "个人页面") },
            label = { Text("个人页面") },
            selected = selectedTab == 1,
            onClick = {
                onTabSelected(1)
                navController.navigate("admin") {
                    // 清空返回栈，移除 "home" 页面
                    popUpTo("home") { inclusive = true }
                }
            }
        )
    }
}
