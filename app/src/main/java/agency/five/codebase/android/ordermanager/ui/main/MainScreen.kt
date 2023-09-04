package agency.five.codebase.android.ordermanager.ui.main

import agency.five.codebase.android.completedOrdermanager.ui.completedOrder.CompletedOrdersRoute
import agency.five.codebase.android.ordermanager.ROUNDED_CORNER_PERCENT_30
import agency.five.codebase.android.ordermanager.data.currentuser.UserData
import agency.five.codebase.android.ordermanager.data.currentuser.UserDataViewModel
import agency.five.codebase.android.ordermanager.enums.StaffRoles
import agency.five.codebase.android.ordermanager.navigation.CompleteOrderDestination
import agency.five.codebase.android.ordermanager.navigation.INDIVIDUAL_STAFF_KEY
import agency.five.codebase.android.ordermanager.navigation.IndividualStaffDestination
import agency.five.codebase.android.ordermanager.navigation.NavigationItem
import agency.five.codebase.android.ordermanager.navigation.ORDER_KEY
import agency.five.codebase.android.ordermanager.ui.completeorder.CompleteOrderRoute
import agency.five.codebase.android.ordermanager.ui.completeorder.CompleteOrderViewModel
import agency.five.codebase.android.ordermanager.ui.component.DrawerBody
import agency.five.codebase.android.ordermanager.ui.component.DrawerMenuItem
import agency.five.codebase.android.ordermanager.ui.confirmorder.ConfirmOrderRoute
import agency.five.codebase.android.ordermanager.ui.confirmorder.ConfirmOrderViewModel
import agency.five.codebase.android.ordermanager.ui.individualstaff.IndividualStaffRoute
import agency.five.codebase.android.ordermanager.ui.individualstaff.IndividualStaffViewModel
import agency.five.codebase.android.ordermanager.ui.login.LoginRoute
import agency.five.codebase.android.ordermanager.ui.login.LoginViewModel
import agency.five.codebase.android.ordermanager.ui.order.OrdersRoute
import agency.five.codebase.android.ordermanager.ui.order.OrdersViewModel
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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun TopBarr(
    currentUserName: String,
    onNavigationItemClick: () -> Unit,
    logoutButton: @Composable (() -> Unit)?,
) {
    Box {
        TopAppBar(
            title = {
                Text(
                    text = currentUserName,

                    )
            },
            navigationIcon = {
                IconButton(
                    onClick = onNavigationItemClick,
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Toggle drawer",
                    )
                }
            },
            backgroundColor = LightGray,
            contentColor = DarkGreen,
        )
        Box(modifier = Modifier.align(CenterEnd)) {
            logoutButton?.invoke()
        }
    }
}

@Composable
fun MainScreen(userDataViewModel: UserDataViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val orderCreation by remember {
        derivedStateOf {
            navBackStackEntry?.destination?.route == NavigationItem.SelectionDestination.route
                    || navBackStackEntry?.destination?.route == NavigationItem.ConfirmOrderDestination.route
        }
    }
    val orderViewAndEdit by remember {
        derivedStateOf {
            navBackStackEntry?.destination?.route == NavigationItem.OrdersDestination.route
                    || navBackStackEntry?.destination?.route == NavigationItem.CompletedOrdersDestination.route
        }
    }
    val staffManagement by remember {
        derivedStateOf {
            navBackStackEntry?.destination?.route == NavigationItem.StaffDestination.route
        }
    }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val userData by userDataViewModel.userDataFlow.collectAsState(initial = UserData())
    val topBarLoggedIn =
        navBackStackEntry?.destination?.route != NavigationItem.LoginDestination.route &&
                navBackStackEntry?.destination?.route != NavigationItem.RegisterStaffDestination.route &&
                userData.role != StaffRoles.NONE
    val scaffoldState = rememberScaffoldState()
    val notLoggedInDrawerMenuItem = listOf(
        DrawerMenuItem(
            path = NavigationItem.LoginDestination.route,
            text = "Login"
        ),
        DrawerMenuItem(
            path = NavigationItem.RegisterStaffDestination.route,
            text = "Register"
        ),
    )
    val waiterDrawerMenuItem = listOf(
        DrawerMenuItem(
            path = NavigationItem.SelectionDestination.route,
            text = "Order creation"
        ),
        DrawerMenuItem(
            path = NavigationItem.OrdersDestination.route,
            text = "Order view and edit"
        ),
    )
    val adminDrawerMenuItem = listOf(
        DrawerMenuItem(
            path = NavigationItem.StaffDestination.route,
            text = "Staff management"
        ),
        DrawerMenuItem(
            path = NavigationItem.OrdersDestination.route,
            text = "Order view and edit"
        ),
    )
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            DrawerBody(
                menuItems = if (!topBarLoggedIn) {
                    notLoggedInDrawerMenuItem
                } else {
                    when (userData.role) {
                        StaffRoles.WAITER ->
                            waiterDrawerMenuItem

                        StaffRoles.ADMIN ->
                            adminDrawerMenuItem

                        StaffRoles.NONE ->
                            notLoggedInDrawerMenuItem
                    }
                },
                onItemClick = {
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                    navController.navigate(
                        it.path
                    )
                },
                modifier = Modifier
                    .background(LightGray)
            )
        },
        drawerBackgroundColor = LightGray,
        drawerContentColor = DarkGreen,
        topBar = {
            TopBarr(
                currentUserName = if (topBarLoggedIn) {
                    userData.name
                } else {
                    ""
                },
                onNavigationItemClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                },
                logoutButton = {
                    if (topBarLoggedIn) {
                        LogoutButton(
                            onClick = {
                                scope.launch {
                                    userDataViewModel.clearUserData()
                                    navController.navigate(
                                        NavigationItem.LoginDestination.route
                                    )
                                }
                            },
                        )
                    }
                },
            )
        },
        bottomBar = {
            if (orderCreation) {
                BottomNavigationBar(
                    destinations = listOf(
                        NavigationItem.SelectionDestination,
                        NavigationItem.ConfirmOrderDestination,
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
            } else if (orderViewAndEdit) {
                BottomNavigationBar(
                    destinations = listOf(
                        NavigationItem.OrdersDestination,
                        NavigationItem.CompletedOrdersDestination,
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
            } else if(staffManagement) {
                BottomNavigationBar(
                    destinations = listOf(
                        NavigationItem.StaffDestination,
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
        },
        backgroundColor = LightGray
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = NavigationItem.LoginDestination.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(
                NavigationItem.LoginDestination.route,
            ) {
                val clickedButton = remember { mutableStateOf(false) }
                val viewModel: LoginViewModel = getViewModel()
                val isLoading = viewModel.isLoading.value

                LaunchedEffect(key1 = isLoading) {
                    if (!isLoading && clickedButton.value) {
                        withContext(Dispatchers.Main) {
                            navigateRoles(
                                role = viewModel.staffRole.value,
                                navController = navController,
                                snackbarHostState = snackbarHostState,
                            ).run {
                                userDataViewModel.setUserData(viewModel.staff.value)
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
            ) {
                val viewModel: SelectionViewModel = getViewModel(
                    parameters = { parametersOf(userData.establishmentId) }
                )
                SelectionRoute(
                    viewModel = viewModel,
                )
            }
            composable(
                NavigationItem.ConfirmOrderDestination.route,
            ) {
                val viewModel: ConfirmOrderViewModel = getViewModel()
                ConfirmOrderRoute(
                    viewModel = viewModel,
                    onClickConfirmOrder = { tableNumber ->
                        viewModel.confirmOrder(userData, tableNumber)
                        navController.navigateUp()
                    }
                )
            }
            composable(
                route = CompleteOrderDestination.route,
                arguments = listOf(navArgument(ORDER_KEY) { type = NavType.StringType }),
            ) {
                val orderId = it.arguments?.getString(ORDER_KEY)
                val viewModel: CompleteOrderViewModel =
                    getViewModel(parameters = { parametersOf(orderId) })
                CompleteOrderRoute(
                    viewModel = viewModel,
                    onCompleteOrder = { navController.navigateUp() },
                    currentUser = userData,
                )
            }
            composable(
                route = NavigationItem.OrdersDestination.route,
            ) {
                val viewModel: OrdersViewModel =
                    getViewModel(parameters = { parametersOf(userData.establishmentId) })
                OrdersRoute(
                    viewModel = viewModel,
                    openOrder = {
                        navController.navigate(
                            CompleteOrderDestination.createNavigationRoute(it)
                        )
                    }
                )
            }
            composable(
                route = NavigationItem.CompletedOrdersDestination.route,
            ) {
                val viewModel: OrdersViewModel =
                    getViewModel(parameters = { parametersOf(userData.establishmentId) })
                CompletedOrdersRoute(
                    viewModel = viewModel,
                    openCompletedOrder = {
                        navController.navigate(
                            CompleteOrderDestination.createNavigationRoute(it)
                        )
                    }
                )
            }
            composable(
                route = NavigationItem.StaffDestination.route,
            ) {
                val viewModel: StaffViewModel = getViewModel()
                viewModel.updateEstablishmentId(userDataViewModel)
                StaffRoute(
                    viewModel = viewModel,
                    onClickStaff = { staffId ->
                        navController.navigate(
                            IndividualStaffDestination.createNavigationRoute(staffId)
                        )
                        println(
                            "CLICKED MAIN SCREEN" + " " + IndividualStaffDestination.createNavigationRoute(
                                staffId
                            )
                        )
                    }
                )
            }
            composable(
                route = IndividualStaffDestination.route,
                arguments = listOf(navArgument(INDIVIDUAL_STAFF_KEY) { type = NavType.StringType }),
            ) {
                val staffId = it.arguments?.getString(INDIVIDUAL_STAFF_KEY)
                val viewModel: IndividualStaffViewModel =
                    getViewModel(parameters = { parametersOf(snackbarHostState, staffId) })
                IndividualStaffRoute(
                    viewModel = viewModel,
                    snackbarHostState = snackbarHostState,
                )
            }
            composable(
                route = NavigationItem.RegisterStaffDestination.route,
            ) {
                val clickedButton = remember { mutableStateOf(false) }
                val viewModel: RegisterStaffViewModel = getViewModel()
                val isLoading = viewModel.isLoading.value
                val validationResult = viewModel.validationResult.value

                LaunchedEffect(isLoading) {
                    println("ISLOADING TRIGGERED")
                    println("$isLoading - ${clickedButton.value}")
                    if (!isLoading && clickedButton.value) {
                        if (validationResult.isSuccess) {
                            navController.navigate(
                                NavigationItem.LoginDestination.route
                            )
                        }
                        snackbarHostState.currentSnackbarData?.dismiss()
                        snackbarHostState.showSnackbar(
                            validationResult.getOrNull()
                                .toString() + validationResult.exceptionOrNull()?.message + "TEEST"
                        )
                        println("TEST " + validationResult.getOrNull() + validationResult.exceptionOrNull()?.message + " TEST")
                        clickedButton.value = false
                    }
                }
                RegisterStaffRoute(
                    snackbarHostState = snackbarHostState,
                    onClickRegisterButton = { name, username, password, establishmentId ->
                        println("CLICK REGISTRATION")
                        clickedButton.value = true
                        viewModel.addStaff(
                            name, username, password, establishmentId, snackbarHostState
                        ) {
                            navController.navigate(
                                NavigationItem.LoginDestination.route
                            )
                        }
                        println("TEST2 " + validationResult.getOrNull() + validationResult.exceptionOrNull()?.message + " TEST2")
                    },
                    registerStaffViewModel = viewModel,
                    onClickNavigateLoginButton = {
                        navController.navigate(
                            NavigationItem.LoginDestination.route
                        )
                    }
                )
            }
        }
    }
}

private suspend fun navigateRoles(
    role: StaffRoles,
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
        colors = ButtonDefaults.buttonColors(
            contentColor = LightGray,
            backgroundColor = LightGray
        ),
        modifier = modifier
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
