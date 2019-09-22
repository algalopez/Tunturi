package com.algalopez.ranking.user.unit;

import com.algalopez.ranking.user.data.UserInfoLevel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class UserInfoLevelUnitTest {

    private static final int LEVEL_UNDEFINED = 0;
    private static final int LEVEL_BEGINNER = 1;
    private static final int LEVEL_EXPERT = 8;

    @Test
    public void testParseUserLevel() {

        assertEquals(UserInfoLevel.UNDEFINED, UserInfoLevel.parseUserLevel(LEVEL_UNDEFINED));
        assertEquals(UserInfoLevel.BEGINNER, UserInfoLevel.parseUserLevel(LEVEL_BEGINNER));
        assertEquals(UserInfoLevel.EXPERT, UserInfoLevel.parseUserLevel(LEVEL_EXPERT));
        assertNull(UserInfoLevel.parseUserLevel(null));
    }
}
