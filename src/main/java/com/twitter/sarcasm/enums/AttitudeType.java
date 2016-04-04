package com.twitter.sarcasm.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Dmitry on 2016-04-03.
 */
public enum AttitudeType {
    SARCASM("#sarcasm","#sarcastic"),
    POSITIVE("#happy", "#joy", "#lmfo", "#lol",":)"),
    NEGATIVE("#sadness", "#angry", "#frustrated","#hate",":(");


    public List<String> getListOfAttitudeMarkers() {
        return listOfAttitudeMarkers;
    }

    private final List<String> listOfAttitudeMarkers;
    AttitudeType(final String firstHashtag,
                 final String... remainingHashtags){
        listOfAttitudeMarkers = new ArrayList<String>();
        listOfAttitudeMarkers.add(firstHashtag);
        if(remainingHashtags != null){
            listOfAttitudeMarkers.addAll(Stream.of(remainingHashtags)
            .collect(Collectors.toList()));
        }
    }
}

//    SARCASM("#sarcasm","#sarcastic"),
//    POSITIVE_1("#happy", "#joy"),
//    POSITIVE_2("#lmfo", "#lol"),
//    POSITIVE_3(":)"),
//
//    NEGATIVE_1("#sad", "#sadness"),
//    NEGATIVE_2("#angry","#hate"),
//    NEGATIVE_3(":(");
