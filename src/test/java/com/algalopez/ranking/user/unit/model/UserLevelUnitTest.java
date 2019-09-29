package com.algalopez.ranking.user.unit.model;

import com.algalopez.ranking.user.model.UserLevel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class UserLevelUnitTest {

    private static final int LEVEL_UNDEFINED = 0;
    private static final int LEVEL_BEGINNER = 1;
    private static final int LEVEL_EXPERT = 8;

    @Test
    public void testParseUserLevel() {

        assertEquals(UserLevel.UNDEFINED, UserLevel.parseUserLevel(LEVEL_UNDEFINED));
        assertEquals(UserLevel.BEGINNER, UserLevel.parseUserLevel(LEVEL_BEGINNER));
        assertEquals(UserLevel.EXPERT, UserLevel.parseUserLevel(LEVEL_EXPERT));
        assertNull(UserLevel.parseUserLevel(null));
    }

    @Test
    public void testFormatUserLevel() {
        assertEquals(Integer.valueOf(LEVEL_UNDEFINED), UserLevel.UNDEFINED.getLevelValue());
        assertEquals(Integer.valueOf(LEVEL_BEGINNER), UserLevel.BEGINNER.getLevelValue());
        assertEquals(Integer.valueOf(LEVEL_EXPERT), UserLevel.EXPERT.getLevelValue());
    }
}
