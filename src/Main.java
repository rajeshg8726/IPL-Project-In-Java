import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLOutput;
import java.util.*;

public class Main {
    public static final int MATCH_ID = 0;
    public static final int MATCH_SEASON = 1;
    public static final int MATCH_CITY = 2;
    public static final int TEAM_ONE = 4;
    public static final int TEAM_TWO = 5;
    public static final int MATCH_WINNER = 10;
    public static final int EXTRA_RUNS = 16;
    public static final int BATSMAN_NAME = 6;
    public static final int BATSMAN_RUNS = 15;
    public static final int MATCH_DAT = 3;
    public static final int MATCH_TOSS_WINNER = 6;
    public static final int MATCH_TOSS_DECISION = 7;
    public static final int MATCH_RESULT = 8;
    public static final int MATCH_WIN_BY_RUNS = 11;
    public static final int MATCH_WIN_BY_WICKETS = 12;
    public static final int PLAYER_OF_MATCH = 13;
    public static final int MATCH_VENUE = 14;
    public static final int MATCH_UMPIRE_ONE = 15;
    public static final int MATCH_UMPIRE_TWO = 16;
    public static final int MATCH_INNING = 1;
    public static final int BATTING_TEAM = 2;
    public static final int BOWLING_TEAM = 3;
    public static final int MATCH_OVER = 4;
    public static final int NON_STRIKER = 7;
    public static final int BOWLER = 8;
    public static final int SUPER_OVER = 9;
    public static final int WIDE_RUNS = 10;
    public static final int BYE_RUNS = 11;
    public static final int LEGBYE_RUNS = 12;
    public static final int NOBALL_RUNS = 13;
    public static final int PENALTY_RUNS = 14;
    public static final int TOTAL_RUNS = 17;

    public static void numberOfMatchesPlayedPerYearOfAllTheYearsInIPL(List<Match> match)
    {   HashMap<String , Integer> matchesPlayedInEachSeason = new HashMap<>();

        for (Match iterator : match)
        {

            if(matchesPlayedInEachSeason.containsKey(iterator.getMatchSeason()))
            {

                matchesPlayedInEachSeason.put(iterator.getMatchSeason(), matchesPlayedInEachSeason.get(iterator.getMatchSeason()) + 1);
            }
            else
            {
                matchesPlayedInEachSeason.put(iterator.getMatchSeason(), 0);
            }
        }

        for (Map.Entry<String, Integer> iterator : matchesPlayedInEachSeason.entrySet())
        {
            System.out.println("YEAR: " + iterator.getKey() + " ,Number of Matches played: " + iterator.getValue());
        }


    }

    public static void numberOfMatchesWonOfAllTeamsOverAllTheYearsOfIPL(List<Match> match)
    {      HashMap<String , Integer>  numberOfMatchesWon = new HashMap<>();

        for (Match iterator : match)
        {
            if(numberOfMatchesWon.containsKey(iterator.getMatchWinner()))
            {
                numberOfMatchesWon.put(iterator.getMatchWinner(), numberOfMatchesWon.get(iterator.getMatchWinner()) + 1);
            }
            else
            {
                numberOfMatchesWon.put(iterator.getMatchWinner() , 0);
            }
        }

        for(Map.Entry<String, Integer> it : numberOfMatchesWon.entrySet())
        {
            System.out.println("Team: " + it.getKey() + ", Matches won by Team: " + it.getValue());
        }
    }


    public static void forTheYear2016GetTheExtraRunsConcededPerTeam(List<Match> match , List<Delivery> deliveries)
    {       HashMap<String, Integer> extraRunsPerTeam = new HashMap<>();
            HashSet<String> matchIdGiveYear = new HashSet<>();

            for(Match iterator : match)
            {
                if(iterator.getMatchSeason().equals("2016"))
                {
                    matchIdGiveYear.add(iterator.getMatchId());
                }
            }

            for (Delivery iterator : deliveries)
            {
                if(matchIdGiveYear.contains(iterator.getMatchId()))
                {
                    if(extraRunsPerTeam.containsKey(iterator.getBowlingTeam()))
                    {
                        extraRunsPerTeam.put(iterator.getBowlingTeam(), extraRunsPerTeam.get(iterator.getBowlingTeam()) + Integer.valueOf(iterator.getExtraRuns()));
                    }
                    else
                    {
                        extraRunsPerTeam.put(iterator.getBowlingTeam(), Integer.valueOf(iterator.getExtraRuns()));
                    }
                }
            }


        for(Map.Entry<String,Integer> it : extraRunsPerTeam.entrySet())
        {
            System.out.println("Extra runs given by team is :" + it.getKey() + " and extra runs is : " + it.getValue());
        }


    }

    public static void howManyRunsMadeByEachBatsmanInAllTheIPLSeason(List<Delivery> deliveries){
            HashMap<String, Integer> batsmanRuns = new HashMap<>();

            for (Delivery iterator : deliveries)
            {

                if(batsmanRuns.containsKey(iterator.getBatsman()))
                {
                    batsmanRuns.put(iterator.getBatsman(), batsmanRuns.get(iterator.getBatsman()) + Integer.parseInt(iterator.getBatsmanRuns()));
                }
                else
                {
                    try {
                        batsmanRuns.put(iterator.getBatsman() , Integer.parseInt(iterator.getBatsmanRuns()));
                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }

                }
            }

        for(Map.Entry<String , Integer> it : batsmanRuns.entrySet())
        {
            System.out.println("Name of the Player: " + it.getKey() + ", and runs made by him in all IPL season: " + it.getValue());
        }

    }


    public static void forTheYear2015GetTheTopEconomicalBowlers(List<Match> match , List<Delivery> deliveries){
        HashSet<String> matchIdOfGivenYear = new HashSet<>();

        for(Match iterator : match)
        {
            if(iterator.getMatchSeason().equals("2015"))
            {
                matchIdOfGivenYear.add(iterator.getMatchId());
            }
        }

        HashMap<String, Float> bowlerThrownBalls = new HashMap<>();
        HashMap<String , Float> bowlerTotalGivenRun = new HashMap<>();

        for(Delivery iterator : deliveries)
        {
            if(matchIdOfGivenYear.contains(iterator.getMatchId()))
            {
                    if(bowlerThrownBalls.containsKey(iterator.getMatchBowler())){
                        bowlerThrownBalls.put(iterator.getMatchBowler() , bowlerThrownBalls.get(iterator.getMatchBowler()) + 1.0f - Integer.parseInt(iterator.getMatchWideRuns()) - Integer.parseInt(iterator.getMatchNoBallsRuns()));
                    }
                    else
                    {
                        bowlerThrownBalls.put(iterator.getMatchBowler(), 1.0f - Float.parseFloat(iterator.getMatchWideRuns()) - Float.parseFloat(iterator.getMatchNoBallsRuns()));
                    }
            }

            if(matchIdOfGivenYear.contains(iterator.getMatchId()))
            {

                if(bowlerTotalGivenRun.containsKey(iterator.getMatchBowler()))
                {
                    bowlerTotalGivenRun.put(iterator.getMatchBowler(), bowlerThrownBalls.get(iterator.getMatchBowler()) + Float.parseFloat(iterator.getTotalRuns())  - Float.parseFloat(iterator.getMatchByeRuns()) - Float.parseFloat(iterator.getMatchLegByRuns()) - Float.parseFloat(iterator.getMatchPenaltyRuns()));

                }
                else
                {
                    bowlerTotalGivenRun.put(iterator.getMatchBowler(), Float.parseFloat(iterator.getTotalRuns())  - Float.parseFloat(iterator.getMatchByeRuns()) - Float.parseFloat(iterator.getMatchLegByRuns()) - Float.parseFloat(iterator.getMatchPenaltyRuns()));
                }


            }


        }

        HashMap<String, Float> bowlerThrowsTotalOvers = new HashMap<>();

        for(Map.Entry<String, Float> iterator : bowlerThrownBalls.entrySet())
        {
            bowlerThrowsTotalOvers.put( iterator.getKey(), (iterator.getValue())/6.0f);
        }

        TreeMap<Float , String> topEconomicalBowlers = new TreeMap<>();

        for(Map.Entry<String , Float> iterator : bowlerThrowsTotalOvers.entrySet())
        {
            if(bowlerTotalGivenRun.containsKey(iterator.getKey()))
            {
                topEconomicalBowlers.put(bowlerTotalGivenRun.get(iterator.getKey())/iterator.getValue() , iterator.getKey());

            }
        }

        for (Map.Entry<Float , String> it : topEconomicalBowlers.entrySet())
        {
            System.out.println("Name of the Bowler: " + it.getValue() + " and economy of this bowlers is: " + it.getKey());
        }

    }

    public static void findThePlayerWhoBowledMostNumberOfWidesInEachTeamIn2016(List<Match> match , List<Delivery> deliveries)
    {
        HashSet<String> matchIdOfGivenYear = new HashSet<>();

        for(Match iterator : match)
        {
            if(iterator.getMatchSeason().equals("2016"))
            {
                matchIdOfGivenYear.add(iterator.getMatchId());
            }
        }

        HashMap<String , Integer> bowlersWideRuns = new HashMap<>();
        HashMap<String, ArrayList<String>> bowlingTeamWithBowlers = new HashMap<>();

        for(Delivery iterator : deliveries)
        {
            if(matchIdOfGivenYear.contains(iterator.getMatchId()))
            {
                if (bowlersWideRuns.containsKey(iterator.getMatchBowler()))
                {
                    bowlersWideRuns.put(iterator.getMatchBowler() , bowlersWideRuns.get(iterator.getMatchBowler()) + Integer.parseInt(iterator.getMatchWideRuns()));
                }
                else
                {
                    bowlersWideRuns.put(iterator.getMatchBowler() , Integer.parseInt(iterator.getMatchWideRuns()));
                }

            }
            ArrayList<String> bowlers = new ArrayList<>();
            if(matchIdOfGivenYear.contains(iterator.getMatchId()))
            {
                if(bowlingTeamWithBowlers.containsKey(iterator.getBowlingTeam()))
                {
                    ArrayList<String> checkBowlers = bowlingTeamWithBowlers.get(iterator.getBowlingTeam());
                    if(checkBowlers.contains(iterator.getMatchBowler()) == false)
                    {
                        checkBowlers.add(iterator.getMatchBowler());

                        bowlingTeamWithBowlers.put(iterator.getBowlingTeam(), checkBowlers);
                    }

                }
                else
                {
                    String bowlingTeam = iterator.getBowlingTeam();
                    bowlers.add(iterator.getMatchBowler());
                    bowlingTeamWithBowlers.put(bowlingTeam, bowlers);

                }
            }

        }

        TreeMap<String , TreeMap<Integer, String>> bowlerWithWide = new TreeMap<>();

        for (Map.Entry<String , ArrayList<String>> it : bowlingTeamWithBowlers.entrySet())
        {
            String teamName = it.getKey();
            ArrayList<String> bowlers = it.getValue();
            TreeMap<Integer, String> bowlerRuns = new TreeMap<>(Collections.reverseOrder());

            for (String name : bowlers) {
                if (bowlersWideRuns.containsKey(name))
                {
                    Integer wideRuns = bowlersWideRuns.get(name);

                    bowlerRuns.put(wideRuns , name);

                }
            }

            bowlerWithWide.put(teamName, bowlerRuns);


        }

        for (Map.Entry<String, TreeMap<Integer, String>> it : bowlerWithWide.entrySet()) {
            String teamNameOfBowler = it.getKey();
            TreeMap<Integer, String> bowlersWithWide = it.getValue();
            System.out.println("Name of The Bowler Team: " + teamNameOfBowler);
            for (Map.Entry<Integer, String> iterator : bowlersWithWide.entrySet()) {
                System.out.println("Name of the Bowler: " + iterator.getValue() + " and wide runs of this bowler is: " + iterator.getKey());
                break;
            }
        }


    }


    public static void findThePlayerWhoBowledMostNumberOf4sInEachTeamIn2016(List<Match> match , List<Delivery> deliveries)
    {
        HashSet<String> matchIdOfGivenYear = new HashSet<>();

        for(Match iterator : match)
        {
            if(iterator.getMatchSeason().equals("2016"))
            {
                matchIdOfGivenYear.add(iterator.getMatchId());
            }
        }


        HashMap<String , Integer> bowlersWith4sRuns = new HashMap<>();
        HashMap<String, ArrayList<String>> bowlingTeamWithBowlers = new HashMap<>();

        for(Delivery iterator : deliveries)
        {
            if(matchIdOfGivenYear.contains(iterator.getMatchId()))
            {
                if (bowlersWith4sRuns.containsKey(iterator.getMatchBowler()))
                {
                    if(iterator.getBatsmanRuns().equals("4"))
                    {
                        bowlersWith4sRuns.put(iterator.getMatchBowler() , bowlersWith4sRuns.get(iterator.getMatchBowler()) + 1);
                    }

                    bowlersWith4sRuns.put(iterator.getMatchBowler() , bowlersWith4sRuns.get(iterator.getMatchBowler()) + Integer.parseInt(iterator.getMatchWideRuns()));
                }
                else
                {
                    if(iterator.getBatsmanRuns().equals("4"))
                    {
                        bowlersWith4sRuns.put(iterator.getMatchBowler() , 1);
                    }
                    else
                    {
                        bowlersWith4sRuns.put(iterator.getMatchBowler(), 0);
                    }

                }

            }
            ArrayList<String> bowlers = new ArrayList<>();
            if(matchIdOfGivenYear.contains(iterator.getMatchId()))
            {
                if(bowlingTeamWithBowlers.containsKey(iterator.getBowlingTeam()))
                {
                    ArrayList<String> checkBowlers = bowlingTeamWithBowlers.get(iterator.getBowlingTeam());
                    if(checkBowlers.contains(iterator.getMatchBowler()) == false)
                    {
                        checkBowlers.add(iterator.getMatchBowler());

                        bowlingTeamWithBowlers.put(iterator.getBowlingTeam(), checkBowlers);
                    }

                }
                else
                {
                    String bowlingTeam = iterator.getBowlingTeam();
                    bowlers.add(iterator.getMatchBowler());
                    bowlingTeamWithBowlers.put(bowlingTeam, bowlers);

                }
            }

        }

        TreeMap<String , TreeMap<Integer, String>> bowlerWith4s = new TreeMap<>();

        for (Map.Entry<String , ArrayList<String>> it : bowlingTeamWithBowlers.entrySet())
        {
            String teamName = it.getKey();
            ArrayList<String> bowlers = it.getValue();
            TreeMap<Integer, String> bowlerRuns = new TreeMap<>(Collections.reverseOrder());

            for (String name : bowlers) {
                if (bowlersWith4sRuns.containsKey(name))
                {
                    Integer wideRuns = bowlersWith4sRuns.get(name);

                    bowlerRuns.put(wideRuns , name);

                }
            }

            bowlerWith4s.put(teamName, bowlerRuns);

        }

        for (Map.Entry<String, TreeMap<Integer, String>> it : bowlerWith4s.entrySet()) {
            String teamNameOfBowler = it.getKey();
            TreeMap<Integer, String> bowlersWith4 = it.getValue();
            System.out.println("Name of The Bowler Team: " + teamNameOfBowler);
            for (Map.Entry<Integer, String> iterator : bowlersWith4.entrySet()) {
                System.out.println("Name of the Bowler: " + iterator.getValue() + " and most 4s runs of this bowler is: " + iterator.getKey());
                break;
            }
        }


    }

    public static void main(String[] args) {

            List<Match> match = getMatchesData();
            List<Delivery> deliveries = getDeliveriesData();


//          numberOfMatchesPlayedPerYearOfAllTheYearsInIPL(match);
//          numberOfMatchesWonOfAllTeamsOverAllTheYearsOfIPL(match);
//          forTheYear2016GetTheExtraRunsConcededPerTeam(match,deliveries);
//          howManyRunsMadeByEachBatsmanInAllTheIPLSeason(deliveries);
//          forTheYear2015GetTheTopEconomicalBowlers(match , deliveries);

//          findThePlayerWhoBowledMostNumberOfWidesInEachTeamIn2016(match , deliveries);

        findThePlayerWhoBowledMostNumberOf4sInEachTeamIn2016(match , deliveries);
    }

    private static List<Delivery> getDeliveriesData() {
        List<Delivery> delivery = new ArrayList<>();

        String pathOfDeliveryFile = "deliveries.csv";

        try {
            File deliveryDataset = new File(pathOfDeliveryFile);
            FileReader readDeliveryDataset = new FileReader(deliveryDataset);
            BufferedReader readDeliveryFile = new BufferedReader(readDeliveryDataset);

            String readEachLine;

            while ((readEachLine = readDeliveryFile.readLine()) != null)
            {   String[] fields = readEachLine.split(",");

                Delivery deliveries = new Delivery();

                deliveries.setMatchId(fields[MATCH_ID]);
                deliveries.setMatchInning(fields[MATCH_INNING]);
                deliveries.setBattingTeam(fields[BATTING_TEAM]);
                deliveries.setBowlingTeam(fields[BOWLING_TEAM]);
                deliveries.setMatchOver(fields[MATCH_OVER]);
                deliveries.setBatsman(fields[BATSMAN_NAME]);
                deliveries.setNonStriker(fields[NON_STRIKER]);
                deliveries.setMatchBowler(fields[BOWLER]);
                deliveries.setMatchSuperOver(fields[SUPER_OVER]);
                deliveries.setMatchWideRuns(fields[WIDE_RUNS]);
                deliveries.setMatchByeRuns(fields[BYE_RUNS]);
                deliveries.setMatchLegByRuns(fields[LEGBYE_RUNS]);
                deliveries.setMatchNoBallsRuns(fields[NOBALL_RUNS]);
                deliveries.setMatchPenaltyRuns(fields[PENALTY_RUNS]);
                try {
                    deliveries.setBatsmanRuns(fields[BATSMAN_RUNS]);
                    deliveries.setExtraRuns(fields[EXTRA_RUNS]);
                    deliveries.setTotalRuns(fields[TOTAL_RUNS]);
                }
                catch (Exception e)
                {
                    System.out.println("There is exception occurred.." + e);
                }

                finally {
                    delivery.add(deliveries);
                }

            }

            readDeliveryDataset.close();
            readDeliveryFile.close();

        }

        catch (Exception e)
        {
            System.out.println(e);
        }

        return delivery;
    }

    private static List<Match> getMatchesData()  {
        List<Match> matches = new ArrayList<>();

        String pathOfMatchesFile = "matches.csv";

        try {
            File matchesDataset = new File(pathOfMatchesFile);
            FileReader readmatchesDataset = new FileReader(matchesDataset);
            BufferedReader readmatchesFile = new BufferedReader(readmatchesDataset);

            String readEachLine;

            while ((readEachLine = readmatchesFile.readLine()) != null)
            {  String[] fields = readEachLine.split(",");

                Match match = new Match();

                match.setMatchId(fields[MATCH_ID]);
                match.setMatchSeason(fields[MATCH_SEASON]);
                match.setMatchCity(fields[MATCH_CITY]);
                match.setMatchDate(fields[MATCH_DAT]);
                match.setTeamOne(fields[TEAM_ONE]);
                match.setTeamTwo(fields[TEAM_TWO]);
                match.setTossWinner(fields[MATCH_TOSS_WINNER]);
                match.setTossDecision(fields[MATCH_TOSS_DECISION]);
                match.setMatchResult(fields[MATCH_RESULT]);
                match.setMatchWinner(fields[MATCH_WINNER]);
                match.setMatchWinByRuns(fields[MATCH_WIN_BY_RUNS]);
                match.setGetMatchWinByWickets(fields[MATCH_WIN_BY_WICKETS]);
                match.setPlayerOfMatch(fields[PLAYER_OF_MATCH]);
                try {
                    match.setMatchVenue(fields[MATCH_VENUE]);
                    match.setMatchUmpireOne(fields[MATCH_UMPIRE_ONE]);
                    match.setGetMatchUmpireTwo(fields[MATCH_UMPIRE_TWO]);
                }
                catch(Exception e){

                    System.out.println("There is exception occurred." + e);

                }
                finally{
                    matches.add(match);
                }

            }

            readmatchesFile.close();
            readmatchesFile.close();
        }

        catch (Exception e)
        {
            System.out.println(e);
        }

        return matches;
    }
}
