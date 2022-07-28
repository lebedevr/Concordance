package com.lebedevrs9.testtask;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ConcordanceTest {

    Concordance concordance = new Concordance();

    @ParameterizedTest
    @MethodSource("provideTexts")
    public void concordanceTest(String text, String expected) {
        String result = concordance.processText(text);
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> provideTexts() {
        return Stream.of(
                Arguments.of("a", "a.        a                   {1:1}               \n"),
                Arguments.of("Qadasdada z asd. Rf 123312. Dsadaqw zxc z.",
                        "a.        123312              {1:2}               \n" +
                        "b.        asd                 {1:1}               \n" +
                        "c.        dsadaqw             {1:3}               \n" +
                        "d.        qadasdada           {1:1}               \n" +
                        "e.        rf                  {1:2}               \n" +
                        "f.        z                   {2:1,3}             \n" +
                        "g.        zxc                 {1:3}               \n"),
                Arguments.of(
                        "Given an arbitrary text document written in English, write a program that will generate a concordance, i.e. an alphabetical list of all word occurrences, labeled with word frequencies. Bonus: label each word with the sentence numbers in which each occurrence appeared.",
        "a.        a                   {2:1,1}             \n" +
        "b.        all                 {1:1}               \n" +
        "c.        alphabetical        {1:1}               \n" +
        "d.        an                  {2:1,1}             \n" +
        "e.        appeared            {1:2}               \n" +
        "f.        arbitrary           {1:1}               \n" +
        "g.        bonus               {1:2}               \n" +
        "h.        concordance         {1:1}               \n" +
        "i.        document            {1:1}               \n" +
        "j.        each                {2:2,2}             \n" +
        "k.        english             {1:1}               \n" +
        "l.        frequencies         {1:1}               \n" +
        "m.        generate            {1:1}               \n" +
        "n.        given               {1:1}               \n" +
        "o.        ie                  {1:1}               \n" +
        "p.        in                  {2:1,2}             \n" +
        "q.        label               {1:2}               \n" +
        "r.        labeled             {1:1}               \n" +
        "s.        list                {1:1}               \n" +
        "t.        numbers             {1:2}               \n" +
        "u.        occurrence          {1:2}               \n" +
        "v.        occurrences         {1:1}               \n" +
        "w.        of                  {1:1}               \n" +
        "x.        program             {1:1}               \n" +
        "y.        sentence            {1:2}               \n" +
        "z.        text                {1:1}               \n" +
        "aa.       that                {1:1}               \n" +
        "bb.       the                 {1:2}               \n" +
        "cc.       which               {1:2}               \n" +
        "dd.       will                {1:1}               \n" +
        "ee.       with                {2:1,2}             \n" +
        "ff.       word                {3:1,1,2}           \n" +
        "gg.       write               {1:1}               \n" +
        "hh.       written             {1:1}               \n")
        );
    }
}
