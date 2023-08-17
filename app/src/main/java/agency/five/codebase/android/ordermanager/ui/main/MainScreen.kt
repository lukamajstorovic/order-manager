package agency.five.codebase.android.ordermanager.ui.main

import agency.five.codebase.android.ordermanager.R
import agency.five.codebase.android.ordermanager.ROUNDED_CORNER_PERCENT_30
import agency.five.codebase.android.ordermanager.WEIGHT_1
import agency.five.codebase.android.ordermanager.data.currentuser.UserData
import agency.five.codebase.android.ordermanager.data.currentuser.UserDataViewModel
import agency.five.codebase.android.ordermanager.enums.StaffRoles
import agency.five.codebase.android.ordermanager.navigation.CompleteOrderDestination
import agency.five.codebase.android.ordermanager.navigation.NavigationItem
import agency.five.codebase.android.ordermanager.navigation.ORDER_KEY
import agency.five.codebase.android.ordermanager.ui.activeorders.ActiveOrdersRoute
import agency.five.codebase.android.ordermanager.ui.activeorders.ActiveOrdersViewModel
import agency.five.codebase.android.ordermanager.ui.completeorder.CompleteOrderRoute
import agency.five.codebase.android.ordermanager.ui.completeorder.CompleteOrderViewModel
import agency.five.codebase.android.ordermanager.ui.confirmorder.ConfirmOrderRoute
import agency.five.codebase.android.ordermanager.ui.confirmorder.ConfirmOrderViewModel
import agency.five.codebase.android.ordermanager.ui.login.LoginRoute
import agency.five.codebase.android.ordermanager.ui.login.LoginViewModel
import agency.five.codebase.android.ordermanager.ui.registerstaff.RegisterStaffRoute
import agency.five.codebase.android.ordermanager.ui.registerstaff.RegisterStaffViewModel
import agency.five.codebase.android.ordermanager.ui.selection.SelectionRoute
import agency.five.codebase.android.ordermanager.ui.selection.SelectionViewModel
import agency.five.codebase.android.ordermanager.ui.staff.StaffRoute
import agency.five.codebase.android.ordermanager.ui.staff.StaffViewModel
import agency.five.codebase.android.ordermanager.ui.theme.DarkGreen
import agency.five.codebase.android.ordermanager.ui.theme.DarkerGray
import agency.five.codebase.android.ordermanager.ui.theme.LightGray
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MainScreen(userDataViewModel: UserDataViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val showBottomBar by remember {
        derivedStateOf {
            navBackStackEntry?.destination?.route == NavigationItem.SelectionDestination.route
                    || navBackStackEntry?.destination?.route == NavigationItem.ConfirmOrderDestination.route
                    || navBackStackEntry?.destination?.route == NavigationItem.ActiveOrdersDestination.route
        }
    }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val userData by userDataViewModel.userDataFlow.collectAsState(initial = UserData())
    val topBarVisible = navBackStackEntry?.destination?.route != NavigationItem.LoginDestination.route
    Box {
        Scaffold(
            topBar = {
                if(topBarVisible) {
                    TopBar(
                        logoutButton = {
                            LogoutButton(onClick = {
                                scope.launch {
                                    userDataViewModel.clearUserData()
                                    navController.navigate(
                                        NavigationItem.LoginDestination.route
                                    )
                                }
                            })
                        },
                        userData = userData,
                    )

                }
            },
            bottomBar = {
                if (showBottomBar)
                    BottomNavigationBar(
                        destinations = listOf(
                            NavigationItem.SelectionDestination,
                            NavigationItem.ConfirmOrderDestination,
                            NavigationItem.ActiveOrdersDestination
                        ),
                        onNavigateToDestination = {
                            navController.navigate(it.route) {
                                popUpTo(it.route) {
                                    inclusive = true
                                }
                            }
                        },
                        currentDestination = navBackStackEntry?.destination
                    )
            }
        ) { padding ->
            Surface {
                NavHost(
                    navController = navController,
                    startDestination = NavigationItem.LoginDestination.route,
                    modifier = Modifier.padding(padding)
                ) {
                    composable(
                        NavigationItem.LoginDestination.route,
                        enterTransition = null,
                        exitTransition = null,
                    ) {
                        val clickedButton = remember { mutableStateOf(false) }
                        val viewModel: LoginViewModel = getViewModel()
                        val isLoading = viewModel.isLoading.value

                        LaunchedEffect(key1 = isLoading) {
                            if (!isLoading && clickedButton.value) {
                                scope.launch {
                                    userDataViewModel.setUserData(viewModel.staff.value).run {
                                        navigateRoles(
                                            role = viewModel.staffRole.value,
                                            navController = navController,
                                            snackbarHostState = snackbarHostState,
                                        )
                                    }
                                    clickedButton.value = false
                                }
                                println("User data: $userData")
                            }
                        }
                        LoginRoute(
                            snackbarHostState = snackbarHostState,
                            onClickLoginButton = { username, password ->
                                clickedButton.value = true
                                viewModel.authenticateStaff(username, password)
                            },
                            onClickNavigateRegisterButton = {
                                navController.navigate(
                                    NavigationItem.RegisterStaffDestination.route
                                )
                            }
                        )
                    }
                    composable(
                        NavigationItem.SelectionDestination.route,
                        enterTransition = null,
                        exitTransition = null,
                    ) {
                        val viewModel: SelectionViewModel = getViewModel()
                        SelectionRoute(
                            viewModel = viewModel,
                        )
                    }
                    composable(
                        NavigationItem.ConfirmOrderDestination.route,
                        enterTransition = null,
                        exitTransition = null,
                    ) {
                        val viewModel: ConfirmOrderViewModel = getViewModel()
                        ConfirmOrderRoute(
                            viewModel = viewModel,
                            onNavigateToSelectionScreen = {
                                navController.navigateUp()
                            }
                        )
                    }
                    composable(
                        route = CompleteOrderDestination.route,
                        enterTransition = null,
                        exitTransition = null,
                        arguments = listOf(navArgument(ORDER_KEY) { type = NavType.IntType }),
                    ) {
                        val orderId = it.arguments?.getInt(ORDER_KEY)
                        val viewModel: CompleteOrderViewModel =
                            getViewModel(parameters = { parametersOf(orderId) })
                        CompleteOrderRoute(
                            viewModel = viewModel,
                            onCompleteOrder = { navController.navigateUp() }
                        )
                    }
                    composable(
                        route = NavigationItem.ActiveOrdersDestination.route,
                        enterTransition = null,
                        exitTransition = null,
                    ) {
                        val viewModel: ActiveOrdersViewModel = getViewModel()
                        ActiveOrdersRoute(
                            viewModel = viewModel,
                            openOrder = {
                                navController.navigate(
                                    CompleteOrderDestination.createNavigationRoute(it)
                                )
                            }
                        )
                    }
                    composable(
                        route = NavigationItem.StaffDestination.route,
                        enterTransition = null,
                        exitTransition = null,
                    ) {
                        val viewModel: StaffViewModel = getViewModel()
                        StaffRoute(
                            viewModel = viewModel,
                        )
                    }
                    composable(
                        route = NavigationItem.RegisterStaffDestination.route,
                        enterTransition = null,
                        exitTransition = null,
                    ) {
                        val clickedButton = remember { mutableStateOf(false) }
                        val viewModel: RegisterStaffViewModel = getViewModel()
                        val isLoading = viewModel.isLoading.value

                        LaunchedEffect(isLoading) {
                            println("ISLOADING TRIGGERED")
                            println("$isLoading - ${clickedButton.value}")
                            if (!isLoading && clickedButton.value) {
                                println("NAVIGATE LOGIN")
                                navController.navigate(
                                    NavigationItem.LoginDestination.route
                                )
                                clickedButton.value = false
                                println("CLICKEDBUTTON FALSE")
                                /*snackbarHostState.currentSnackbarData?.dismiss()*/
                                snackbarHostState.showSnackbar("Registration successful")
                            }
                        }
                        RegisterStaffRoute(
                            snackbarHostState = snackbarHostState,
                            onClickRegisterButton = { name, username, password ->
                                println("CLICK REGISTRATION")
                                clickedButton.value = true
                                viewModel.addStaff(name, username, password)
                                println("ACTIVATED VIEWMODEL")
                            },
                        )
                    }
                }
            }
        }
    }
}

private suspend fun navigateRoles(
    role : StaffRoles,
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
) {
    when (role) {
        StaffRoles.ADMIN -> {
            println("NAVIGATE ADMIN: $role")
            navController.navigate(
                NavigationItem.StaffDestination.route
            )
        }

        StaffRoles.WAITER -> {
            println("NAVIGATE WAITER: $role")
            navController.navigate(
                NavigationItem.SelectionDestination.route
            )
        }

        StaffRoles.NONE -> {
            println("NAVIGATE NONE: $role")
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar("Invalid username or password")
        }
    }
}

@Composable
private fun TopBar(
    logoutButton: @Composable (() -> Unit),
    userData: UserData,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = LightGray),
        contentAlignment = Alignment.CenterStart,
    ) {
        logoutButton.invoke()
        Text(
            text = userData.name,
            color = DarkGreen,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(10.dp, end = 20.dp)
                .align(Alignment.CenterEnd)
        )
    }
}

@Composable
private fun LogoutButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        shape = RoundedCornerShape(ROUNDED_CORNER_PERCENT_30),
        onClick = { onClick() },
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = LightGray,
            backgroundColor = LightGray
        ),
        modifier = Modifier
            .padding(10.dp)
    ) {
        Text(
            text = "Logout",
            color = DarkGreen,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(5.dp)
        )
    }
}

@Composable
private fun BottomNavigationBar(
    destinations: List<NavigationItem>,
    onNavigateToDestination: (NavigationItem) -> Unit,
    currentDestination: NavDestination?,
) {
    BottomNavigation(
        backgroundColor = LightGray
    ) {
        destinations.forEach { destination ->
            AddItem(
                destination = destination,
                onNavigateToDestination = { onNavigateToDestination(destination) },
                currentDestination = currentDestination
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    destination: NavigationItem,
    onNavigateToDestination: (NavigationItem) -> Unit,
    currentDestination: NavDestination?,
) {
    BottomNavigationItem(
        modifier = Modifier
            .align(CenterVertically),
        label = {
            Text(
                text = stringResource(id = destination.labelId),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(CenterVertically),
                //color = DarkGreen
            )
        },
        icon = {},
        selected = currentDestination?.route == destination.route,
        selectedContentColor = DarkGreen,
        unselectedContentColor = DarkerGray,
        onClick = { onNavigateToDestination(destination) }
    )
}

@Preview
@Composable
private fun MainScreenViewState() {
//    MainScreen()
}
