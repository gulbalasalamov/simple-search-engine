## Simple Search Engine

The goal of this assignment is to create a simple search engine in Java. 

The search engine implements as an inverted index (http://en.wikipedia.org/wiki/Inverted_index) that runs in memory and can return a result list that is sorted by TF-IDF (http://en.wikipedia.org/wiki/Tf*idf).

The search engine should:
  - be able to take in a list of documents
  - support searches for single terms in the document set (http://en.wikipedia.org/wiki/Tokenization)
  - return a list of matching documents sorted by TF-IDF.
    - For TF choose either (using Wikipedia terminology):  
        - term frequency adjusted for document length: ft,d ÷ (number of words in d)
        - augmented frequency
    - For IDF choose (using Wikipedia terminology):


     ![Intro Page](https://wikimedia.org/api/rest_v1/media/math/render/svg/ac67bc0f76b5b8e31e842d6b7d28f8949dab7937)
     
## Demo

The following documents are indexed: 

> Document1: Our company adds business value to organisations where information application is a priority

> Document2: We help companies find and information analyse and act on information

> Document3: We are global experts in solving information challenges

A search for "information" should now return the following documents from list:

3, 2, 1, 

A search for "challenges" should now return the following documents from list:

3, 
     

## Design and Architecture

The project is built on The Strategy Pattern that defines a family of algorithms, encapsulates each one, and makes them interchangeable. 
Strategy is based on composition: you can alter parts of the object’s behavior by supplying it with different strategies that correspond to that behavior.

That's said, strategy pattern uses composition instead of inheritance. In the strategy pattern, behaviors are defined as separate interfaces and specific classes that implement these interfaces. 
This allows better decoupling between the behavior and the class that uses the behavior. The behavior can be changed without breaking the classes that use it, and the classes can switch between behaviors by changing the specific implementation used without requiring any significant code changes. Behaviors can also be changed at run-time as well as at design-time.
