import constant.Constant;
import manager.SearchEngineManager;
import model.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestMostValuableDoc {

    SearchEngineManager searchEngineManager;

    @BeforeEach
    void setUp() {
        searchEngineManager = new SearchEngineManager();
        searchEngineManager.createLibrary();

    }

    @org.junit.jupiter.api.Test
    @DisplayName("Most valuable doc in list")
    void testFindMostValuableDocs() {
        //Note: defaultTerm should be inline with the documents. So don't forget to update it too if you change the text
        String defaultTerm = Constant.searchKeyWord2;
        searchEngineManager.calculateTFScore(defaultTerm);
        List<Document> sortedDocsByTFIDF = searchEngineManager.calculateTFIDF();
        System.out.println(sortedDocsByTFIDF.get(0).getId());
        assertEquals(3, sortedDocsByTFIDF.get(0).getId(),
                "3 is id the doc" + defaultTerm);
        System.out.println("The id of most valuable doc for the default term \""+ defaultTerm + "\" is " + sortedDocsByTFIDF.get(0).getId());
    }
}
