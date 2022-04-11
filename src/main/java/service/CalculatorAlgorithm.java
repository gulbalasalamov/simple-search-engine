package service;

import model.Document;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public interface CalculatorAlgorithm {
    HashMap<Document, Double> calculateTFScore(List<Document> documents, String term);

    double calculateIDFScore(List<Document> docList, String term);

    List<Document> calculateTFIDF(HashMap<Document,Double> docListMap, double idfScore);

    HashMap<Document, TreeMap<String,Integer>> getWordsFromDocList(List<Document> documents);

    List<Document> createDocuments();
}
