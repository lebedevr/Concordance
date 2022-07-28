package com.lebedevrs9.testtask;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

class Concordance {

    private final static String example = "Given an arbitrary text document written in English, write a program that will generate a concordance, i.e. an alphabetical list of all word occurrences, labeled with word frequencies. Bonus: label each word with the sentence numbers in which each occurrence appeared.";

    public static void main(String[] args) {
        String text = getText(args);
        String result = new Concordance().processText(text);
        System.out.println("Result:");
        System.out.println(result);
    }

    public String processText(String text) {
        Map<String, List<Integer>> sentencesMap = createSentencesMap(text);
        return sentenceMapToOutputString(sentencesMap);
    }

    private Map<String, List<Integer>> createSentencesMap(String text) {
        Map<String, List<Integer>> sentencesMap = new TreeMap<>();
        String sentenceSpliterator = "(?<=[.?!])\\s(?=[A-Z0-9])";
        String[] sentences = text.split(sentenceSpliterator);
        int sentenceIndex = 1;
        for (String sentence : sentences) {
            String[] words = sentence.split(" ");
            for (String word : words) {
                word = dictionaryForm(word);
                if (!sentencesMap.containsKey(word)) {
                    sentencesMap.put(word, new ArrayList<>());
                }
                sentencesMap.get(word).add(sentenceIndex);
            }
            sentenceIndex++;
        }
        return sentencesMap;
    }

    private String sentenceMapToOutputString(Map<String, List<Integer>> sentencesMap) {
        StringBuilder result = new StringBuilder();
        int row = 0;
        for (Map.Entry<String, List<Integer>> kv : sentencesMap.entrySet()) {
            String rowLetterIndex = rowLetterIndex(row++);
            String word = kv.getKey();
            String sentencesPart = formatSentenceIndexes(kv.getValue());
            String outputRowFormat = "%-10s%-20s%-20s\n";
            result.append(String.format(outputRowFormat, rowLetterIndex, word, sentencesPart));
        }
        return result.toString();
    }

    private String formatSentenceIndexes(List<Integer> sentenceIndexes) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(sentenceIndexes.size());
        sb.append(":");
        for (int j = 0; j < sentenceIndexes.size() - 1; j++) {
            sb.append(sentenceIndexes.get(j));
            sb.append(",");
        }
        sb.append(sentenceIndexes.get(sentenceIndexes.size() - 1));
        sb.append("}");
        return sb.toString();
    }

    private String dictionaryForm(String word) {
        String punctuation = "[,:.]";
        return word.replaceAll(punctuation, "").toLowerCase();
    }

    private String rowLetterIndex(int index) {
        StringBuilder sb = new StringBuilder();
        while (index >= 0) {
            sb.append((char)('a' + index % 26));
            index -= 26;
        }
        sb.append('.');
        return sb.toString();
    }

    private static String getText(String[] args) {
        String text;
        if (args.length > 0) {
            String fileName = args[0];
            try {
                text = Files.readString(Path.of(fileName));
                System.out.println("File with path: " + fileName + " successfully loaded");
            } catch (IOException e) {
                System.out.println("Could not find file with path: " + fileName);
                System.out.println("Will run program with test example\n");
                text = example;
            }
        } else {
            System.out.println("Run program with test example\n");
            text = example;
        }
        return text;
    }
}