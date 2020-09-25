package com.gohealth.interview;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.*;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class InterviewTest {

    /**
     * Given:
     * “The quick brown fox and the quick blue hare.”
     *
     * When:
     * fetching the histogram
     *
     * Then:
     * Histogram is the following:
     * “the quick” 2
     * “quick brown” 1
     * “brown fox” 1
     * “fox and” 1
     * “and the” 1
     * “quick blue” 1
     * “blue hare” 1
     */
    @Test
    public void test1() throws IOException {
        // setup
        final String filepath= "happy1.txt";
        final InputStreamReader reader = new InputStreamReader(ClassLoader.getSystemResourceAsStream(filepath));
        final Interview interview = new Interview(reader);

        // act
        final Map<String,Integer> histogram = interview.histogram();

        // assert
        assertThat(histogram, hasEntry("the quick",2));
        assertThat(histogram, hasEntry("quick brown", 1));
        assertThat(histogram, hasEntry("brown fox", 1));
        assertThat(histogram, hasEntry("fox and", 1));
        assertThat(histogram, hasEntry("and the", 1));
        assertThat(histogram, hasEntry("quick blue", 1));
        assertThat(histogram, hasEntry("blue hare", 1));
    }

    /*
        Given: ""

        When:
        fetching the histogram

        Then:
        Empty map
     */
    @Test
    public void test2() throws IOException {
        // setup
        final String filepath= "happy2.txt";
        final InputStreamReader reader = new InputStreamReader(ClassLoader.getSystemResourceAsStream(filepath));
        final Interview interview = new Interview(reader);

        // act
        final Map<String,Integer> histogram = interview.histogram();

        // assert
        assertThat(histogram.isEmpty(), is(true));
    }

    /*
        Given: "the quick"

        When:
        fetching the histogram

        Then:
        "The quick" => 1
     */
    @Test
    public void test3() throws IOException {
        // setup
        final String filepath= "happy3.txt";
        final InputStreamReader reader = new InputStreamReader(ClassLoader.getSystemResourceAsStream(filepath));
        final Interview interview = new Interview(reader);

        // act
        final Map<String,Integer> histogram = interview.histogram();

        // assert
        assertThat(histogram, hasEntry("the quick", 1));
    }

    /*
        Given: "the"

        When:
        fetching the histogram

        Then:
        Empty map
     */
    @Test
    public void test4() throws IOException {
        // setup
        final String filepath= "happy4.txt";
        final InputStreamReader reader = new InputStreamReader(ClassLoader.getSystemResourceAsStream(filepath));
        final Interview interview = new Interview(reader);

        // act
        final Map<String,Integer> histogram = interview.histogram();

        // assert
        assertThat(histogram.isEmpty(), is(true));
    }

    /*
        Given: "the quick "

        When:
        fetching the histogram

        Then:
        "the quick" => 1
     */
    @Test
    public void test5() throws IOException {
        // setup
        final String filepath= "happy5.txt";
        final InputStreamReader reader = new InputStreamReader(ClassLoader.getSystemResourceAsStream(filepath));
        final Interview interview = new Interview(reader);

        // act
        final Map<String,Integer> histogram = interview.histogram();

        // assert
        assertThat(histogram, hasEntry("the quick", 1));
    }
}
