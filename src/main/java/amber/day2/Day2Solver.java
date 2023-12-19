package amber.day2;

import amber.Solver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

/**
 * <a href="https://adventofcode.com/2023/day/2">Day 2 Prompt</a>
 */
public class Day2Solver extends Solver {
    private static final String GAME_RECORDS_FILE = "game-records.txt";
    private static final Map<String, Integer> CUBES_IN_BAG = Map.of(
            "red", 12,
            "green", 13,
            "blue", 14);

    @Override
    public void solve() {
        URL resourceURL = getURL(GAME_RECORDS_FILE);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceURL.openStream()))) {
            String line;
            int validGameIdSum = 0;
            while ((line = reader.readLine()) != null) {
                validGameIdSum += getGameIdIfValid(line);
            }
            System.out.println("Day 2 Solution: " + validGameIdSum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the id of the game as an integer if the game is valid according to the amount of cubes in the bag.
     * If the game is not valid, returns 0.
     * Note that there is no validation that the record is formatted properly. We assume that each record is formatted
     * exactly as defined below and that there are no duplicate colors per round.
     * @param gameRecord Record of a game that was played. Ex: "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
     */
    private static int getGameIdIfValid(String gameRecord) {
        String[] gameRecordSplit = gameRecord.split(": ");
        String gameInput = gameRecordSplit[1];
        String[] gameRounds = gameInput.split("; ");
        boolean validGame = true;
        for (String round : gameRounds) {
            String[] pulledColors = round.split(", ");
            for (String pulledColor : pulledColors) {
                String[] pulledColorSplit = pulledColor.split(" ");
                int cubeAmount = Integer.parseInt(pulledColorSplit[0]);
                String cubeColor = pulledColorSplit[1];
                if (!isValidCubeAmount(cubeAmount, cubeColor)) {
                    validGame = false;
                    break;
                }
            }
        }
        return validGame ? getGameId(gameRecordSplit[0]) : 0;
    }

    /**
     * @param gameTitle Title of the game within a record. Ex: "Game 1"
     * @return The id of the game as an integer. Using the example param, we would return the integer 1.
     */
    private static int getGameId(String gameTitle) {
        return Integer.parseInt(gameTitle.split(" ")[1]);
    }

    private static boolean isValidCubeAmount(int cubeAmount, String cubeColor) {
        return cubeAmount <= CUBES_IN_BAG.get(cubeColor);
    }
}
