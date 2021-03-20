package com.daniyalxdubizzle.androidtakehomeproject

import com.daniyalxdubizzle.androidtakehomeproject.utilities.capitalizeWords
import org.junit.Assert
import org.junit.Test

class CapititalizeWordsTest {

    /* Check CapitalizeWords Function By Passing Single Word*/
    @Test
    fun `Make First Letter into Caps Should True`() {
        Assert.assertTrue("daniyal".capitalizeWords.equals("Daniyal"))
    }

    /* Check CapitalizeWords Function By Passing Multiple Words*/
    @Test
    fun `Make Long String First Letter into Caps Should True`() {
        Assert.assertTrue("daniyal ahmed khan".capitalizeWords.equals("Daniyal Ahmed Khan"))
    }

    /* Check CapitalizeWords Function By Passing Multiple Words Already in Caps*/
    @Test
    fun `Make String As Same If Already Caps Should True`() {
        Assert.assertTrue("Daniyal Ahmed Khan".capitalizeWords.equals("Daniyal Ahmed Khan"))
    }

    /* Check CapitalizeWords Function By Passing Multiple Words with Dot*/
    @Test
    fun `Make String without dot Should not break False`() {
        Assert.assertFalse("daniyal.ahmed".capitalizeWords.equals("Daniyal Ahmed"))
    }
}