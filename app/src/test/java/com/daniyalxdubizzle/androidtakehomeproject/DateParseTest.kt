package com.daniyalxdubizzle.androidtakehomeproject

import com.daniyalxdubizzle.androidtakehomeproject.utilities.GeneralHelper
import com.daniyalxdubizzle.androidtakehomeproject.utilities.capitalizeWords
import org.junit.Test
import org.junit.Assert.*

class DateParseTest {

    /* Check Date With Expected Format From API */
    @Test
    fun `Date Parse Expected Format Should True`(){
        assertTrue(GeneralHelper.isDateParse("2019-02-24 04:04:17.566515"))
    }

    /* Check Date Parse With Different Format From API */
    @Test
    fun `Date Parse UnExpected Format Should False`(){
        assertFalse(GeneralHelper.isDateParse("2019-02-24 04:04:17"))
    }

    /* Check Date Parse With Empty Format From API */
    @Test
    fun `Date Parse Empty Should False`(){
        assertFalse(GeneralHelper.isDateParse(""))
    }


}