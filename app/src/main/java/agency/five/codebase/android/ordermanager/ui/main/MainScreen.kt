package agency.five.codebase.android.ordermanager.ui.main

import agency.five.codebase.android.ordermanager.mock.UserMock
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
import agency.five.codebase.android.ordermanager.ui.selection.SelectionRoute
import agency.five.codebase.android.ordermanager.ui.selection.SelectionViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val showBottomBar by remember {
        derivedStateOf {
            navBackStackEntry?.destination?.route == NavigationItem.SelectionDestination.route ||
                    navBackStackEntry?.destination?.route == NavigationItem.ConfirmOrderDestination.route
        }
    }
    //val showBackIcon = !showBottomBar
    Scaffold(
        bottomBar = {
            if (showBottomBar)
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
        }
    ) { padding ->
        Surface(
            //color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize()
        ) {
            NavHost(
                navController = navController,
                startDestination = NavigationItem.LoginDestination.route,
                modifier = Modifier.padding(padding)
            ) {
                composable(
                    NavigationItem.LoginDestination.route
                ) {
                    LoginRoute(
                        onClickLoginButton = {
                            if (UserMock.getUserRole(it) == "cook") {
                                navController.navigate(
                                    NavigationItem.SelectionDestination.route
                                )
                            } else if(UserMock.getUserRole(it) == "waiter") {
                                navController.navigate(
                                    NavigationItem.ActiveOrdersDestination.route
                                )
                            }
                        },
                    )
                }
                composable(
                    NavigationItem.SelectionDestination.route,
                ) {
                    val viewModel: SelectionViewModel = getViewModel()
                    SelectionRoute(
                        viewModel = viewModel,
                    )
                }
                composable(
                    NavigationItem.ConfirmOrderDestination.route
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
                    arguments = listOf(navArgument(ORDER_KEY) { type = NavType.IntType }),
                ) {
                    val orderId = it.arguments?.getInt(ORDER_KEY)
                    val viewModel: CompleteOrderViewModel = getViewModel(parameters = { parametersOf(orderId) })
                    CompleteOrderRoute(
                        viewModel = viewModel,
                        onCompleteOrder = { navController.navigateUp() }
                    )
                }
                composable(
                    route = NavigationItem.ActiveOrdersDestination.route
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
            }
        }
    }
}

@Composable
private fun BottomNavigationBar(
    destinations: List<NavigationItem>,
    onNavigateToDestination: (NavigationItem) -> Unit,
    currentDestination: NavDestination?,
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background
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
        modifier = Modifier,
        label = {
            Text(
                text = stringResource(id = destination.labelId),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(CenterVertically)
            )
        },
        icon = {},
        selected = currentDestination?.route == destination.route,
        onClick = { onNavigateToDestination(destination) }
    )
}

@Preview
@Composable
private fun MainScreenViewState() {
    MainScreen()
}
