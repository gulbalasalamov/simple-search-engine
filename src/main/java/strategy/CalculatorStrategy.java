package strategy;

import constant.Constant;
import model.Document;
import service.CalculatorAlgorithm;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class CalculatorStrategy implements CalculatorAlgorithm {

    /**
     * Generates list of document object holding words. Words are String constants and predefined.     *
     *
     * @return List<Document>
     */
    public List<Document> createDocuments() {
        Document doc1 = new Document(1, Constant.Doc1);
        Document doc2 = new Document(2, Constant.Doc2);
        Document doc3 = new Document(3, Constant.Doc3);
        List<Document> documents = Arrays.asList(doc1, doc2, doc3);
        return documents;
    }

    /**
     * The words in documents is validated against set of conditions. Regex can be applied.
     * These words are stored in case-insensitive order in a tree, along with counter information how many times they occur.
     *
     * @param documents -> List of Document objects.
     * @return HashMap -> Key - Documents object, Value -> Tree of validated words and their occurrence number
     */
    public HashMap<Document, TreeMap<String, Integer>> getWordsFromDocList(List<Document> documents) {
        TreeSet<String> wordList = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        AtomicReference<HashMap<Document, TreeMap<String, Integer>>> finalMap = new AtomicReference<>(new HashMap<>());
        documents.forEach(
                doc -> {
                    TreeMap<String, Integer> wordCount = new TreeMap<>();
                    String[] words = doc.getText().toLowerCase().split(" ");
                    for (String term : words) {
                        term = StringOperations.cleanseInput(term);
                        if (StringOperations.isDigit((term))) {
                            continue;
                        }
                        if (term.length() == 0) {
                            continue;
                        }
                        wordList.add(term);
                        if (wordCount.containsKey(term)) {
                            wordCount.put(term, wordCount.get(term) + 1);
                        } else {
                            wordCount.put(term, 1);
                        }
                    }
                    finalMap.get().put(doc, wordCount);
                }
        );
        return finalMap.get();
    }

    /**
     * This method first calls getWordsFromDocList() which returns a hashmap holding document objects, all the words from documents & number of times each word occur per document
     * It creates new HashMap tfScoreMap, holding Document object and tfScore of term.
     * If the search term is included in any document, it calculates TF score and puts this in new tfScoreMap
     *
     * @param documents List of document object containing words. That is to pass it to getWordsMapFromDocList as an argument.
     * @param term      String search term
     * @return HashMap -> Frequency of term in documents
     */
    @Override
    public HashMap<Document, Double> calculateTFScore(List<Document> documents, String term) {
        HashMap<Document, TreeMap<String, Integer>> words = getWordsFromDocList(documents);
        HashMap<Document, Double> tfScoreMap = new HashMap<>();
        words.forEach((doc, treeMap) -> {
            double tfScore = Double.NaN;
            int wordsSum = treeMap.values().stream().mapToInt(Integer::intValue).sum();
            if (treeMap.containsKey(term)) {
                tfScore = (double) treeMap.get(term) / wordsSum;
            }
            tfScoreMap.put(doc, tfScore);
        });
        return tfScoreMap;
    }

    /**
     * @param docList List of documents
     * @param term    String search term
     * @return The Inverse Document Frequency of term in documents
     */
    @Override
    public double calculateIDFScore(List<Document> docList, String term) {
        HashMap<Document, TreeMap<String, Integer>> documentTreeMapHashMap = getWordsFromDocList(docList);
        AtomicInteger counter = new AtomicInteger();
        documentTreeMapHashMap.forEach((doc, treeMap) -> {
            if (treeMap.containsKey(term)) {
                counter.getAndIncrement();
            }
        });
        return Math.log((double) documentTreeMapHashMap.size() / counter.get());
    }

    /**
     * This method requires Frequency of Term (TF) in documents and Inverse Document Frequency (IDF) to calculate TF-IDF rate
     *
     * @param docListTFMap -> return value of calculateTFScore() method
     * @param idfScore     -> return value of calculateIDFScore() method
     * @return List of sorted Documents based on higher terms' TF-IDF value
     */
    @Override
    public List<Document> calculateTFIDF(HashMap<Document, Double> docListTFMap, double idfScore) {

        List<Document> documentList =
                docListTFMap.entrySet()
                        .stream()
                        .filter(entry -> !Double.isNaN(entry.getValue()))
                        .peek(entry -> entry.setValue(entry.getValue() * idfScore))
                        .sorted(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());
        return documentList;
    }
}