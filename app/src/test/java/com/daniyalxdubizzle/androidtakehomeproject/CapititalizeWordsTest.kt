package com.daniyalxdubizzle.androidtakehomeproject

import com.daniyalxdubizzle.androidtakehomeproject.utilities.capitalizeWords
import org.junit.Assert
import org.junit.Test

class CaptitalizeWordsTest {

    @Test
    fun `Make First Letter into Caps Should True`(){
        Assert.assertTrue("daniyal".capitalizeWords.equals("Daniyal"))
    }

    @Test
    fun `Make Long String First Letter into Caps Should True`(){
        Assert.assertTrue("daniyal ahmed khan".capitalizeWords.equals("Daniyal Ahmed Khan"))
    }

    @Test
    fun `Make String As Same If Already Caps Should True`(){
        Assert.assertTrue("Daniyal Ahmed Khan".capitalizeWords.equals("Daniyal Ahmed Khan"))
    }

    @Test
    fun `Make String without dot Should not break False`(){
        Assert.assertFalse("daniyal.ahmed".capitalizeWords.equals("Daniyal Ahmed"))
    }
}