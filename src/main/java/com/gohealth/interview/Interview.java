package com.gohealth.interview;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Interview {
    protected final InputStreamReader reader;
    protected static final char STREAM_SENTINEL='\uFFFF';
    protected final Map<String, Integer> counts = new HashMap<>();

    public Interview(final InputStreamReader reader){
        if(ObjectUtils.isEmpty(reader)) throw new IllegalArgumentException("reader is missing");

        this.reader=reader;
    }

    protected void add(final String bigram){
        if(!counts.containsKey(bigram)) counts.put(bigram, 0);

        counts.put(bigram, counts.get(bigram)+1);
    }

    public Map<String, Integer> histogram() throws IOException {
        try {
            Character aChar;
            StringBuilder bigramBuilder = new StringBuilder();
            StringBuilder wordBuilder = new StringBuilder();

            // ignore leading whitespace
            while (Character.isWhitespace(aChar = read())) ;

            if (aChar == STREAM_SENTINEL) return Collections.emptyMap();

            // initialize bigramBuilder
            do {
                if (Character.isAlphabetic(aChar)) {
                    wordBuilder.append(aChar);
                    bigramBuilder.append(aChar);
                }
            } while (Character.isAlphabetic(aChar = read()));


            wordBuilder.delete(0, wordBuilder.length());

            // repetitive operation
            do {
                if (Character.isAlphabetic(aChar) || Character.isWhitespace(aChar))
                    bigramBuilder.append(aChar);

                if (Character.isAlphabetic(aChar)) {
                    wordBuilder.append(aChar);
                } else if (Character.isWhitespace(aChar) && StringUtils.isNotBlank(wordBuilder)) {
                    add(bigramBuilder.subSequence(0, bigramBuilder.length() - 1).toString().toLowerCase());
                    bigramBuilder.delete(0, bigramBuilder.length());
                    bigramBuilder.append(wordBuilder).append(aChar);
                    wordBuilder.delete(0, wordBuilder.length());
                }
            } while ((aChar = read()) != STREAM_SENTINEL);

            if (StringUtils.isNotBlank(wordBuilder)) {
                add(bigramBuilder.subSequence(0, bigramBuilder.length()).toString().toLowerCase());
            }

            return counts;
        }
        finally {
            reader.close();
        }
    }

    protected char read() throws IOException {
        return (char)reader.read();
    }
}
