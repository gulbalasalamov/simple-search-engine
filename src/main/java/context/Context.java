package context;


import model.Document;
import service.CalculatorAlgorithm;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * A special class for storing reference to a strategy
 * The context delegates execution to an instance of concrete strategy through its interface instead of implementing behaviour itself
 */
public class Context {
    CalculatorAlgorithm calculatorAlgorithm;

    public void setCalculatorAlgorithm(CalculatorAlgorithm calculatorAlgorithm) {
        this.calculatorAlgorithm = calculatorAlgorithm;
    }

    public List<Document> createDocuments() {
        return this.calculatorAlgorithm.createDocuments();
    }

    public HashMap<Document, TreeMap<String,Integer>> getWordsFromDocList(List<Document> documents) {
        return this.calculatorAlgorithm.getWordsFromDocList(documents);
    }

    public HashMap<Document, Double> calculateTFScore(List<Document> docList, String term) {
        return this.calculatorAlgorithm.calculateTFScore(docList,term);
    }

    public double calculateIDFScore(List<Document> docList, String term){
        return this.calculatorAlgorithm.calculateIDFScore(docList,term);
    }

    public List<Document> calculateTFIDF(HashMap<Document,Double> docListMap, double idfScore){
        return this.calculatorAlgorithm.calculateTFIDF(docListMap,idfScore);
    }

}
