package org.cj.alec.maintainableCode2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class GetTargetFromString {

    private GetTargetFromString(){

    }

    static Optional<String> getSingleStringParameterFromQuery(String queryString) {
        if(queryString == null) return Optional.empty();
        List<String> matches = getStrings(queryString);
        int numTargets = matches.toArray().length;
        if(numTargets == 1){
            return Optional.of(getParameter(matches));
        }else{
            return Optional.empty();
        }
    }

    private static String getParameter(List<String> matches) {
        String [] words = matches.get(0).split("=");
        return words[1];
    }

    private static List<String> getStrings(String queryString) {
        List<String> matches = new ArrayList<>();
        Matcher m = Pattern.compile("target=\\w+")
                .matcher(queryString);
        while (m.find()) {
            matches.add(m.group());
        }
        return matches;
    }
}
