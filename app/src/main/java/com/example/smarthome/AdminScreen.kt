package com.example.smarthome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

//个人账号界面，分为了TOP，Middle，Bottom三个部分，可以不管
@Composable
    fun AdminScreen(navController: NavController) {
        AdminScreenPage(navController)
    }


@Composable
fun AdminScreenPage(
    navController: NavController
) {
    var selectedTab by remember { mutableIntStateOf(1) }
    val itemList = listOf(
        AdminItemData(R.drawable.file, "视频管理", onClick = { /* Item 1 点击事件逻辑 */ }),
        AdminItemData(R.drawable.file, "音频管理", onClick = { /* Item 2 点击事件逻辑 */ }),
        AdminItemData(R.drawable.file, "更多文件", onClick = { /* Item 3 点击事件逻辑 */ })
    )
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // 顶部部分
                AdminTopSection(modifier = Modifier
                    .weight(1f)
                    .padding(24.dp)
                    .clip(RoundedCornerShape(16.dp)
                    )
                )

                // 中间部分
                AdminMiddleSection(
                    items = itemList,
                    modifier = Modifier.weight(1f)
                )

                // 底部导航栏
                AdminBottomSection(
                    navController = navController,
                    selectedTab = selectedTab,
                    onTabSelected = { selectedTab = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
}
@Composable
fun AdminTopSection(
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .background(
                Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF89A6DA),
                        Color(0xFFBED6FF),

                    ),
                )
            )
            .fillMaxWidth()
    ) {
        // 第一个 Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "向左箭头",
                modifier = Modifier.size(35.dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = "设置图标",
                modifier = Modifier.size(35.dp),
                tint = Color.Black
            )

        }

        // 用户头像和用户名横向居中
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, // 水平居中
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .align(Alignment.Center) // 将 Column 横向居中
                .padding(top = 32.dp) // 可根据需要调整底边距
        ) {
            Image(
                painter = painterResource(id = R.drawable.qq), // 用户头像
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(text = "用户名",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black
                    )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "用户签名或简介",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            val iList = listOf(
                AdminListItemData("账号信息",R.drawable.account),
                AdminListItemData("设备管理",R.drawable.device),
                AdminListItemData("更多",R.drawable.more2)
            )
            // 上方的多级列表
            LazyRow(
                modifier = Modifier
            ) {
                items(iList) { item -> // 使用 itemList
                    AdminListItemView(item = item)
                }
            }

        }

    }
}

@Composable
fun AdminMiddleSection(
    items: List<AdminItemData>,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier) {

        // 原有的 Box 代码
        Box(
            modifier = Modifier
                .background(color = Color.Transparent)
        ) {
            Column {
                // 个性设置的 Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, end = 32.dp, top = 8.dp) ,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "文件管理",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "查看更多",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Black
                    )
                }
                // 多级列表
                LazyColumn(
                    modifier = Modifier.fillMaxSize() // 确保 LazyColumn 占据剩余空间
                ) {
                    items(items) { item ->
                        AdminListItem(item = item) // 这里可以根据需要添加其他内容
                    }
                }
            }
        }
    }
}

@Composable
fun AdminListItemView(item: AdminListItemData) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(100.dp) // 设定为正方形
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF6684C7),
                        Color(0xFF85A8FD)
                    )
                )
            ) // 子元素背景颜色
            .padding(8.dp), // 内部填充
        contentAlignment = Alignment.Center // 让内容居中
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 使用传入的图片资源
            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = null,
                modifier = Modifier.size(40.dp) // 图片大小
            )
            Spacer(modifier = Modifier.height(4.dp)) // 图片和文本间隔
            Text(
                text = item.title, // 使用新的数据类属性
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

data class AdminListItemData(
    val title: String,
    val imageResId: Int // 图片资源 ID
)

@Composable
fun AdminListItem(item: AdminItemData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                24.dp
            )
            .clickable { item.onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(24.dp))
        Image(
            painter = painterResource(item.imageRes), // 使用传入的图片资源
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.width(24.dp))
        Text(item.description,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.titleMedium
        ) // 使用传入的介绍内容
        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "向右箭头")
        Spacer(modifier = Modifier.width(16.dp))
    }
}

data class AdminItemData(
    val imageRes: Int,
    val description: String,
    val onClick: () -> Unit
)

@Composable
fun AdminBottomSection(
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
                    // 清空返回栈，移除 "admin" 页面
                    popUpTo("admin") { inclusive = true }
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
                }
            }
        )
    }
}



