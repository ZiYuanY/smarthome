package com.example.smarthome
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun VideoPlayerScreen(navController: NavController) {
    VideoPlayerPage(navController)
}

@Composable
fun VideoPlayerPage(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 文本
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("当前状态：", fontSize = 20.sp)
                    Text("开启", color = Color.Red, fontSize = 20.sp)
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // 正方形播放区（用于视频播放）
            Surface(
                modifier = Modifier
                    .size(300.dp)
                    .border(BorderStroke(2.dp, Color.Gray)),
                color = Color.Black // 播放区域的背景颜色
            ) {
                // 这里可以放置视频播放组件，其余可以展示不用管
            }

            Spacer(modifier = Modifier.height(24.dp))
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(50.dp)
                    .border(BorderStroke(1.dp, Color.Gray))
            ) {
                Row(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable { /* TODO: 开启操作 */ }
                            .background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("开启", color = Color.Black)
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight() // 填满高度
                            .clickable { /* TODO: 关闭操作 */ }
                            .background(Color.DarkGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("关闭", color = Color.White)
                    }
                }
            }
        }
    }
}
