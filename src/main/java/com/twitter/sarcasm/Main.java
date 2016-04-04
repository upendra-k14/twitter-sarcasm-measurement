package com.twitter.sarcasm;

import com.twitter.sarcasm.core.DataQueryRetriever;
import com.twitter.sarcasm.enums.AttitudeType;
import org.joda.time.DateTime;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.stream.Stream;


/**
 * Created by Dmitry on 2016-03-05.
 */
public class Main {
    public static void main(String[] args) throws TwitterException {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("QDf0ToLxu56xoi5oJV3kvL7qm")
                .setOAuthConsumerSecret("i9jGKY84U2WLICzMdoNsEBjO1Ua4iNGG4m804U3YRXZrVyYXCo")
                .setOAuthAccessToken("855764401-8vKzShNx1RrPbMtt8ZLhSYlqUeDKAurCfhbayqCe")
                .setOAuthAccessTokenSecret("UXkcZpio9pbucgYpn4aANo5h34EahV2rZEGMexFuP5Fmp");

//        cb.setDebugEnabled(true)
//                .setOAuthConsumerKey("ntBVZjmHBskP0n5m2t52O4j5g")
//                .setOAuthConsumerSecret("zAuuX5s9wKq25KOFphJaUxqyXrNH0Z1G5JAE7QwqVRzStDtnPq")
//                .setOAuthAccessToken("716890251415826432-EB0TMvRHrA2yWOJpxSwffZRnbswpsls")
//                .setOAuthAccessTokenSecret("2E9n8RrkTFU7Dvu9Om6PS8wwYFDd8H3Rk6EU2uPYYreCb");

        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        final DataQueryRetriever negativeDataRetriever2 =
                new DataQueryRetriever(AttitudeType.NEGATIVE,AttitudeType.SARCASM);
        negativeDataRetriever2.queryData(twitter,
                "2015-01-01",
                "2015-10-01");

//        Stream.of(AttitudeType.values())
//                .forEach(attitudeType -> {
//                    final DataQueryRetriever negativeDataRetriever =
//                            new DataQueryRetriever(attitudeType);
//                    negativeDataRetriever.queryData(twitter,
//                            "2015-01-01",
//                            "2015-10-01");
//                });
//        final DataQueryRetriever negativ
//        final DataQueryRetriever negativeDataRetriever =
//                new DataQueryRetriever(AttitudeType.POSITIVE,AttitudeType.SARCASM);
//        negativeDataRetriever.queryData(twitter,
//                "2015-01-01",
//                "2015-10-01");
//        negativeDataRetriever.getListOfData();

//
////        Twitter twitter = TwitterFactory.getSingleton();
////        Query query = new Query("source:twitter4j yusukey");
////        Query query = new Query("#sarcasm");
//        Query query = new Query("#weather");
//
//
//
//
//
//


//
//        List<Tweet> goodWeather = new ArrayList<Tweet>();
//        List<Tweet> badWeather = new ArrayList<Tweet>();
//
//        Twitter twitter = tf.getInstance();
//        System.out.println("Fetching Weather Data...");
//
//        // get the 1000 most recent tweets tagged #weather
//        for (int page = 1; page <= 10; page++) {
//            Query query = new Query("#weather");
//
//            QueryResult qr = twitter.search(query);
//            List<Tweet> qrTweets = qr.getTweets();
//
//            // break out if there are no more tweets
//            if(qrTweets.size() == 0) break;
//
//            // separate tweets into good and bad bins
//            for(Tweet t : qrTweets) {
//                if (t.getText().toLowerCase().contains("happy") ||
//                        t.getText().toLowerCase().contains("love")) {
//                    goodWeather.add(t);
//                }
//
//                if (t.getText().toLowerCase().contains("sad") ||
//                        t.getText().toLowerCase().contains("hate")) {
//                    badWeather.add(t);
//                }
//            }
//        }
//
//        System.out.println("Good Weather: " + goodWeather.size());
//        for (Tweet good : goodWeather) {
//            System.out.println(good.getCreatedAt() + ": " + good.getText());
//        }
//
//        System.out.println("\nBad Weather: " + badWeather.size());
//        for (Tweet bad : badWeather) {
//            System.out.println(bad.getCreatedAt() + ": " + bad.getText());
//        }
    }
}
