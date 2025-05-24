package com.example.barflowapp.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.barflowapp.MainActivity
import com.example.barflowapp.R
import com.example.barflowapp.domain.model.CargoItem
import com.example.barflowapp.domain.usecase.FakeGetCargoUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class HomeScreenTest {
  /*  @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var fakeGetCargoUseCase: FakeGetCargoUseCase

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun homeScreen_displaysCargoItems_whenSuccess() {
        // Arrange
        val testCargos =
            listOf(
                CargoItem(
                    "1",
                    "Tehran",
                    "Tehran P",
                    "Kerman",
                    "Kerman P",
                    12.0,
                    2000000,
                    "Cement",
                    "Sack",
                    "20 Shahrivar",
                ),
                CargoItem(
                    "2",
                    "Mashhad",
                    "Khorasan P",
                    "Ahvaz",
                    "Khuzestan P",
                    9.0,
                    3000000,
                    "Steel",
                    "Pallet",
                    "21 Shahrivar",
                ),
            )
        (fakeGetCargoUseCase as FakeGetCargoUseCase).cargosToReturn = testCargos
        (fakeGetCargoUseCase as FakeGetCargoUseCase).shouldThrowError = false

        // Act: ComposeTestRule will set content to MainActivity, which should show HomeScreen
        // No explicit setContent needed if MainActivity's default content is what you want to test.

        // Assert
        composeTestRule
            .onNodeWithText("Tehran (Tehran P)")
            .assertIsDisplayed() // From CargoCardItem
        composeTestRule.onNodeWithText("Kerman (Kerman P)").assertIsDisplayed()
        composeTestRule
            .onNodeWithText("12.0 تن")
            .assertIsDisplayed() // Assuming your CargoCardItem formats it this way

        composeTestRule.onNodeWithText("Mashhad (Khorasan P)").assertIsDisplayed()
    }

    @Test
    fun homeScreen_displaysError_whenUseCaseFails() {
        // Arrange
        (fakeGetCargoUseCase as FakeGetCargoUseCase).shouldThrowError = true
        (fakeGetCargoUseCase as FakeGetCargoUseCase).errorMessage = "Custom Fake Error"

        // Act - Recomposition will happen as ViewModel state changes

        // Assert
        composeTestRule
            .onNodeWithText("Something went wrong!")
            .assertIsDisplayed() // From your HomeScreen error state
    }

    @Test
    fun clickOnCargoItem_opensDetailModal_andDisplaysCorrectData() {
        // Arrange
        val cargoToClick =
            CargoItem(
                "1",
                "Tehran",
                "Tehran P",
                "Kerman",
                "Kerman P",
                12.0,
                2000000,
                "Cement",
                "Sack",
                "20 Shahrivar",
            )
        (fakeGetCargoUseCase as FakeGetCargoUseCase).cargosToReturn = listOf(cargoToClick)
        (fakeGetCargoUseCase as FakeGetCargoUseCase).shouldThrowError = false

        // Act
        // Find the card item (you might need more specific finders if text is not unique)
        composeTestRule.onNodeWithText("Tehran (Tehran P)").performClick()

        // Assert: Modal is displayed with correct data
        val cargoDetailsTitle = composeTestRule.activity.getString(R.string.cargo_details_title)
        composeTestRule.onNodeWithText(cargoDetailsTitle).assertIsDisplayed() // Modal title

        composeTestRule.onNodeWithText("Tehran").assertIsDisplayed() // Origin in modal
        composeTestRule.onNodeWithText("Kerman").assertIsDisplayed() // Destination in modal
        composeTestRule
            .onNodeWithText("12.0 ${composeTestRule.activity.getString(R.string.ton_unit)}")
            .assertIsDisplayed() // Weight in modal
        composeTestRule.onNodeWithText("Cement").assertIsDisplayed() // Type in modal

        // Example: Check the confirm button text (uses formatted price)
        val expectedButtonText =
            composeTestRule.activity.getString(R.string.confirm_action_button, "2 میلیون")
        composeTestRule.onNodeWithText(expectedButtonText).assertIsDisplayed()
    }*/
}