package org.cj.alec.maintainableCode2;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class GetTargetFromStringTest {

    @Test
    public void singleTargetFound(){
        //given
        final String queryString = "target=cat";
        final Optional<String> expected = Optional.of("cat");

        //when
        final Optional<String> actual = GetTargetFromString.getSingleStringParameterFromQuery(queryString);

        //then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void emptyWhenMultipleTargets(){
        //given
        final String queryString = "target=cat&target=dog";
        final Optional<String> expected = Optional.empty();

        //when
        final Optional<String> actual = GetTargetFromString.getSingleStringParameterFromQuery(queryString);

        //then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void emptyWhenEmptyString(){
        //given
        final String queryString = "";
        final Optional<String> expected = Optional.empty();

        //when
        final Optional<String> actual = GetTargetFromString.getSingleStringParameterFromQuery(queryString);

        //then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void emptyWhenNull(){
        //given
        final String queryString = null;
        final Optional<String> expected = Optional.empty();

        //when
        final Optional<String> actual = GetTargetFromString.getSingleStringParameterFromQuery(queryString);

        //then
        assertThat(actual, equalTo(expected));
    }


}
