package org.cj.alec.maintainableCode2;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GetTargetFromStringTest {
    @Test
    public void getTypicalParameterTest() {
        //given
        final String query = "target=world";
        final String expected = "world";
        final Optional<String> actual;

        //when
        actual = GetTargetFromString.getSingleStringParameterFromQuery(query);

        //then
        assertThat(actual.isPresent(), is(true));
        assertThat(actual.get(), is(expected));
    }
}
