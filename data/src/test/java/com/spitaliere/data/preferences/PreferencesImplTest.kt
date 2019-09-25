package com.spitaliere.data.preferences

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 */
@RunWith(RobolectricTestRunner::class)
class PreferencesImplTest {

    private lateinit var preferences: Preferences

    private val TEST_BOOLEAN = "TEST_BOOLEAN"
    private val TEST_STRING = "TEST_STRING"
    private val TEST_INT = "TEST_INT"
    private val TEST_LONG = "TEST_LONG"

    @Before
    fun setUp() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val sharedPreferences = appContext.getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE)
        preferences = PreferencesImpl(sharedPreferences)
    }

    @Test fun `save and get boolean preference`() {
        preferences.saveBoolean(TEST_BOOLEAN, true)

        assertEquals(true, preferences.getBoolean(TEST_BOOLEAN, false))
        assertEquals(false, preferences.getBoolean(TEST_BOOLEAN + "error", false))
    }

    @Test fun `save and get string preference`() {
        preferences.saveString(TEST_STRING, "test")

        assertEquals("test", preferences.getString(TEST_STRING, "test_"))
        assertEquals("test_", preferences.getString(TEST_STRING + "error", "test_"))
    }

    @Test
    fun `save and get int preference`() {
        preferences.saveInt(TEST_INT, 10)

        assertEquals(10, preferences.getInt(TEST_INT, 100))
        assertEquals(100, preferences.getInt(TEST_INT + "error", 100))
    }

    @Test
    fun `save and get long preference`() {
        preferences.saveLong(TEST_LONG, 1000)

        assertEquals(1000, preferences.getLong(TEST_LONG, 10000))
        assertEquals(10000, preferences.getLong(TEST_LONG + "error", 10000))
    }
}