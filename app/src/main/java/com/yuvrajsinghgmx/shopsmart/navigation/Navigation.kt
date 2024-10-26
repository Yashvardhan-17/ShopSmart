package com.yuvrajsinghgmx.shopsmart.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.yuvrajsinghgmx.shopsmart.screens.*
import com.yuvrajsinghgmx.shopsmart.viewmodel.ShoppingListViewModel

@Composable
fun Navigation(viewModel: ShoppingListViewModel, navController: NavHostController) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route

    // Add all settings screens to bottom bar visible screens
    val showBottomBar = currentDestination in listOf(
        "Home", "List", "UpComing", "Profile", "MyOrders", "Help",
        "settings", "personal_info", "address_book", "payment_methods", "security",
        "language", "theme", "notifications", "privacy", "currency",
        "shipping_preferences", "order_notifications", "app_version",
        "terms", "privacy_policy", "contact"
    )

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                ShopSmartNavBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "signUpScreen",
            modifier = Modifier.padding(innerPadding)
        ) {
            // Existing routes
            composable("signUpScreen") {
                SignUpScreen(
                    onSignUpComplete = {
                        navController.navigate("Home") {
                            popUpTo("signUpScreen") { inclusive = true }
                        }
                    },
                    onContinueWithEmail = {
                        navController.navigate("emailSignUpScreen")
                    },
                    onTermsAndConditionsClick = {
                        navController.navigate("TermsAndConditions")
                    }
                )
            }

            composable("TermsAndConditions") {
                TermsAndConditionsScreen(
                    onBackClick = {
                        navController.navigate("signUpScreen"){
                            popUpTo("signUpScreen") { inclusive = true }
                        }
                    }
                )
            }

            composable("emailSignUpScreen") {
                EmailSignUpScreen(
                    onSignUpComplete = {
                        navController.navigate("Home") {
                            popUpTo("signUpScreen") { inclusive = true }
                        }
                    },
                    onBackButtonClicked = {
                        navController.navigate("signUpScreen"){
                            popUpTo("signUpScreen") { inclusive = true }
                        }
                    },
                    onTermsOfUseClicked = {
                        navController.navigate("TermsAndConditions"){
                            popUpTo("signUpScreen") { inclusive = true }
                        }
                    }
                )
            }

            composable("Home") {
                HomeScreen(navController = navController)
            }

            composable("List") {
                ListScreen(viewModel = viewModel, navController = navController)
            }

            composable("UpComing") {
                Upcoming(modifier = Modifier.padding(innerPadding))
            }

            composable("Profile") {
                Profile(navController = navController)
            }

            composable("MyOrders?selectedItems={selectedItems}") { backStackEntry ->
                val selectedItemsJson = backStackEntry.arguments?.getString("selectedItems")
                MyOrders(navController = navController, selectedItemsJson = selectedItemsJson ?: "[]")
            }

            composable("Help") {
                HelpS(navController = navController)
            }

            // New Settings Routes
            // Main Settings
            composable("settings") {
                SettingsScreen(navController = navController)
            }

            // Account Settings
            composable("personal_info") {
                PersonalInformationScreen(navController = navController)
            }

            composable("address_book") {
                AddressBookScreen(navController = navController)
            }

            composable("payment_methods") {
                PaymentMethodsScreen(navController = navController)
            }

            composable("security") {
                SecurityScreen(navController = navController)
            }

            // App Settings
            composable("language") {
                LanguageScreen(navController = navController)
            }

            composable("theme") {
                ThemeScreen(navController = navController)
            }

            composable("notifications") {
                NotificationSettingsScreen(navController = navController)
            }

            composable("privacy") {
                PrivacySettingsScreen(navController = navController)
            }

            // Shopping Settings
            composable("currency") {
                CurrencyScreen(navController = navController)
            }

            composable("shipping_preferences") {
                ShippingPreferencesScreen(navController = navController)
            }

            composable("order_notifications") {
                OrderNotificationsScreen(navController = navController)
            }

            // About Section
            composable("app_version") {
                AppVersionScreen(navController = navController)
            }

            composable("terms") {
                TermsScreen(navController = navController)
            }

            composable("privacy_policy") {
                PrivacyPolicyScreen(navController = navController)
            }

            composable("contact") {
                ContactScreen(navController = navController)
            }
        }
    }
}