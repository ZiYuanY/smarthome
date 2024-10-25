package com.example.smarthome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

//登录界面，写到这里时已经不想写了，全部赛到了一个函数内（

@Composable
fun LoginScreen(navController: NavController){
    LogScreen(navController)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogScreen(navController: NavController) {
    var isExpanded by remember { mutableStateOf(false) }
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    // 登录页高度动画
    val loginHeight by animateDpAsState(
        targetValue = if (isExpanded) screenHeight * 3 / 4 else screenHeight * 1 / 2,
        animationSpec = tween(durationMillis = 300), label = ""
    )

    // 大字顶部间距动画
    val paddingTop by animateDpAsState(
        targetValue = if (isExpanded) 0.dp else 16.dp,
        animationSpec = tween(durationMillis = 300), label = ""
    )

    //外置Box
    Box(
        modifier = Modifier
            .fillMaxSize()
            //背景颜色
            .background(
                brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFabbaab),
                            Color(0xFFFFFFFF),
                            Color(0xFFabbaab)),
                )
            )
            .clickable { isExpanded = true }
            .animateContentSize(animationSpec = tween(durationMillis = 300))
    ) {
        // 上半部分
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxHeight(if (isExpanded) 0.25f else 0.5f)
                .padding(top = paddingTop),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 登录界面的介绍字（大）
            Text(
                text = "智能家居",
                fontSize = 48.sp,
                modifier = Modifier.padding(top = paddingTop)
            )

            // 登录界面的介绍字（小）
            AnimatedVisibility(visible = !isExpanded) {
                Text(
                    text = "帮助您管理家庭",
                    fontSize = 28.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(loginHeight)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(Color.Transparent)
        ) {
            Column(
                modifier = Modifier.fillMaxSize() // 使 Column 占据整个 Box
            ) {
                //密码输入框上的“登录”二字
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "登录",
                        fontSize = 28.sp,
                        color = Color.Black,
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp, end = 40.dp, top = 28.dp), // 此处调整登录框与屏幕左右的边距
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // 账号输入框
                    var account by remember { mutableStateOf("") }
                    TextField(
                        value = account,
                        onValueChange = { account = it },
                        label = { Text("账号") },
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.White,
                            unfocusedIndicatorColor = Color.White,
                            containerColor = Color.White
                        ),
                        modifier = Modifier.onFocusChanged {
                            if (it.isFocused) {
                                isExpanded = true
                            }
                        }
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // 密码输入框（没有做密码掩盖）
                    var password by remember { mutableStateOf("") }
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("密码") },
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.White,
                            unfocusedIndicatorColor = Color.White,
                            containerColor = Color.White
                        ),
                        modifier = Modifier.onFocusChanged {
                            if (it.isFocused) {
                                isExpanded = true
                            }
                        }
                            .fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp)) // 为按钮和输入框之间添加间距
                    //此处为写登录逻辑处，目前设置的为直接转入主页面
                    Button(
                        onClick = {
                            /* TODO: 登录逻辑 */
                            navController.navigate("home"){
                                // 清空返回栈，移除原页面
                                popUpTo(navController.graph.startDestinationId) { inclusive = true }
                            }
                                  },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent // 设置按钮背景颜色为透明
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFFabbaab),
                                        Color(0xFFCEE0CE),
                                        Color(0xFFCEE0CE),
                                        Color(0xFFabbaab)
                                    )
                                ),
                                shape = RoundedCornerShape(24.dp) // 设置圆角形状
                            ),
                    ) {
                        Text(text = "登录", color = Color.Black) // 设置按钮文本颜色
                    }
                    // 只有在登录页上升后才会出现的内容（qq登录与微信登录），剩下的都不用管
                    AnimatedVisibility(
                        visible = isExpanded,
                        enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                        exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
                    ) {
                        Column {
                            Spacer(modifier = Modifier.height(48.dp)) // 添加间距

                            Text(
                                text = "其他登录方式",
                                fontSize = 16.sp,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp)) // 添加间距
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Button(
                                    onClick = { /* TODO: 按钮1逻辑 */ },
                                    modifier = Modifier.weight(1f),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Transparent // 设置按钮背景颜色为透明
                                    )
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        // 使用 Image 显示自定义图片
                                        Image(
                                            painter = painterResource(id = R.drawable.qq), // 替换为你的图片资源
                                            contentDescription = null,
                                            modifier = Modifier.size(24.dp), // 设置图片大小
                                            contentScale = ContentScale.Crop // 设置图片缩放方式
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "QQ登录",
                                            color = Color.Black
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.width(10.dp)) // 按钮之间的间距

                                Button(
                                    onClick = { /* TODO: 按钮2逻辑 */ },
                                    modifier = Modifier.weight(1f),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Transparent // 设置按钮背景颜色为透明
                                    )
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        // 使用 Image 显示自定义图片
                                        Image(
                                            painter = painterResource(id = R.drawable.wechat), // 替换为你的图片资源
                                            contentDescription = null,
                                            modifier = Modifier.size(24.dp), // 设置图片大小
                                            contentScale = ContentScale.Crop // 设置图片缩放方式
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "微信登录",
                                            color = Color.Black
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}
