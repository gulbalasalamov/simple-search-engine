import constant.Constant;
import manager.SearchEngineManager;
import model.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestToCheckNumberOfDocs {
    SearchEngineManager searchEngineManager;

    @BeforeEach
    void setUp() {
        searchEngineManager = new SearchEngineManager();
        searchEngineManager.createLibrary();

    }

    @org.junit.jupiter.api.Test
    @DisplayName("Doc list confirm including a search term")
    void testDocListConfirmForTerm() {
        //Note: defaultTerm should be inline with the documents. So don't forget to update it too if you change the text
        String defaultTerm = Constant.searchKeyWord1;
        searchEngineManager.calculateTFScore(defaultTerm);
        List<Document> sortedDocsByTFIDF = searchEngineManager.calculateTFIDF();
        assertEquals(2, sortedDocsByTFIDF.size(),
                "2 docs included " + defaultTerm);
        for (Document d : sortedDocsByTFIDF
        ) {
            System.out.println("included docs id: " + d.getId());
        }
    }
}
