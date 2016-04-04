package com.twitter.sarcasm.core;

import com.twitter.sarcasm.base.TwitterData;
import com.twitter.sarcasm.enums.AttitudeType;
import com.twitter.sarcasm.enums.QueryOperator;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import twitter4j.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.IntStream;

import static com.twitter.sarcasm.enums.QueryOperator.*;

/**
 * Created by Dmitry on 2016-04-03.
 */
public class DataQueryRetriever {

    private final static int DEFAULT_QUERY_COUNT = 100;
    private final static String DEFAULT_QUERY_LANGUAGE = "en";
    private final static DateTimeFormatter dateTimeFormatter =
            DateTimeFormat.forPattern("yyyy-MM-dd hh_mm_ss");
    private final static String NEW_LINE_SEPARATOR = "\n";
    private final static String[] FILE_HEADER =  {"Tweet"};

    private final String folder = "data/";
    private List<Status> listOfData;

    private final AttitudeType primaryAttitudeMarker;
    private AttitudeType secondaryAttitudeMarker;
    private AttitudeType tertiaryAttitudeMarker;

    private  FileWriter fileWriter;
    private  CSVPrinter csvPrinter;

    public DataQueryRetriever(final AttitudeType primaryAttitudeMarker){

        listOfData = new ArrayList<>();
        this.primaryAttitudeMarker = primaryAttitudeMarker;
    }

    public DataQueryRetriever(final AttitudeType primaryAttitudeMarker,
                              final AttitudeType secondaryAttitudeMarker){
        this(primaryAttitudeMarker);
        this.secondaryAttitudeMarker = secondaryAttitudeMarker;
    }
    public DataQueryRetriever(final AttitudeType primaryAttitudeMarker,
                              final AttitudeType secondaryAttitudeMarker,
                              final AttitudeType tertiaryAttitudeMarker){
        this(primaryAttitudeMarker,secondaryAttitudeMarker);
        this.tertiaryAttitudeMarker = tertiaryAttitudeMarker;
    }

    //:( OR #sad AND #sarcasm OR #sarcastic
    public void queryData(final Twitter twitter,
                          final String startData,
                          final String endDate)   {

        final StringJoiner fileName = new StringJoiner(" ");

        fileName.add(primaryAttitudeMarker.name());

        //Create the CSVFormat object with "\n" as a record delimiter
        final CSVFormat csvFileFormat =
                CSVFormat.DEFAULT;
        try {




            final StringBuilder queryBuilder
                    = new StringBuilder();
            createQuery(queryBuilder, primaryAttitudeMarker, OR);
            if (secondaryAttitudeMarker != null) {
                fileName.add(secondaryAttitudeMarker.name());
                queryBuilder.append(AND.getOperatorValue());
                createQuery(queryBuilder, secondaryAttitudeMarker, OR);
            }
            if (tertiaryAttitudeMarker != null) {
                fileName.add(tertiaryAttitudeMarker.name());
                queryBuilder.append(AND.getOperatorValue());
                createQuery(queryBuilder, tertiaryAttitudeMarker, OR);
            }
            fileName.add(dateTimeFormatter.print(DateTime.now().minusDays(12)));

            fileWriter = new FileWriter(folder + fileName.toString() +".csv");

            //initialize CSVPrinter object
            csvPrinter = new CSVPrinter(fileWriter, csvFileFormat);

            //Create CSV file header
            csvPrinter.printRecord(FILE_HEADER);



//            final Query query = new Query(queryBuilder.toString());
//            final Query query = new Query("#sarcasm OR #sarcastic # OR #happy OR #joy OR #lmfo OR #lol OR :) #sadness OR #angry OR #frustrated OR #hate OR :(");

            final Query query = new Query("her");

            query.setCount(DEFAULT_QUERY_COUNT);
            query.setLang(DEFAULT_QUERY_LANGUAGE);



            runQuery(twitter, query);
        }
        catch (final Exception e) {
            System.out.println(e.getMessage());
        }finally {
        try {
            fileWriter.flush();
            fileWriter.close();
            csvPrinter.close();
        } catch (IOException e) {
            System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
            e.printStackTrace();
        }
    }
    }


    private void runQuery(final Twitter twitter,
                          final Query query) throws TwitterException, IOException {
        int counter = 0;
        QueryResult result = twitter.search(query);
        if (result.getTweets().size() != 0) {

            final List<Status> tweets = result.getTweets();
//            System.out.print("# Tweets:\t" + tweets.size()+"\n");
            Long minId = Long.MAX_VALUE;
            listOfData.addAll(tweets);
//            final List<TwitterData> listOfTwitterData =
//                    new ArrayList<>();
            for (Status tweet : tweets) {
//                TwitterData twitterData =
//                        new TwitterData(tweet.getText(),primaryAttitudeMarker,secondaryAttitudeMarker);
                if (tweet.getId() < minId)
                    minId = tweet.getId();

                csvPrinter.print(tweet.getText());
//                csvPrinter.print(primaryAttitudeMarker);
//                csvPrinter.print(secondaryAttitudeMarker);
                csvPrinter.printRecord();
            }
            query.setMaxId(minId - 1);
            System.out.print("# Tweets:\t" + listOfData.size()+"\n");
            runQuery(twitter,query);
//            if(listOfData.size() == 3 0000000)
//                break;

        }
    }

    private void createQuery(final StringBuilder queryBuilder,
                             final AttitudeType attitudeType,
                             final QueryOperator queryOperator){
        final List<String> listOfAttitudeMarkers = attitudeType.getListOfAttitudeMarkers();
        IntStream.range(0,listOfAttitudeMarkers.size()-1)
                .forEach(indexOfMarker ->
                {
                    queryBuilder.append(listOfAttitudeMarkers.get(indexOfMarker))
                            .append(queryOperator.getOperatorValue());
                });
        queryBuilder.append(listOfAttitudeMarkers.get(listOfAttitudeMarkers.size()-1));
    }

    public List<Status> getListOfData() {
        return listOfData;
    }
}
