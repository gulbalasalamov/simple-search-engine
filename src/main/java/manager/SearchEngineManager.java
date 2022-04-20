package manager;


import context.Context;
import model.Document;
import strategy.CalculatorStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class SearchEngineManager {
    Context context;
    List<Document> documents;
    //HashMap<Document, TreeMap<String, Integer>> wordsFromDocuments;
    HashMap<Document, Double> tfScoreMap;
    double idfScore;

    public SearchEngineManager() {
        this.context = new Context();
        context.setCalculatorAlgorithm(new CalculatorStrategy());
    }

    /**
     * Creates the list of documents containing words
     */
    public void createLibrary() {
        context.setCalculatorAlgorithm(new CalculatorStrategy());
        documents = context.createDocuments();
        //return documents;
    }

//    public void getWordsFromDocList() {
//        wordsFromDocuments = context.getWordsFromDocList(documents);
//    }

    public void calculateTFScore(String term) {
        tfScoreMap = context.calculateTFScore(documents, term);
    }

    public void calculateIDFScore(String term) {
        idfScore = context.calculateIDFScore(documents, term);
    }

    public List<Document> calculateTFIDF() {
        return context.calculateTFIDF(tfScoreMap, idfScore);
    }

}
