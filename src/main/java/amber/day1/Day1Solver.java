package amber.day1;

import amber.Solver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * <a href="https://adventofcode.com/2023/day/1">Day 1 Prompt</a>
 */
public class Day1Solver extends Solver {
    private static final String BROKEN_CALIBRATION_FILE = "broken-calibration-document.txt";
    public void solve() {
        URL resourceURL = getURL(BROKEN_CALIBRATION_FILE);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceURL.openStream()))) {
            String line;
            int calibrationValueSum = 0;
            while ((line = reader.readLine()) != null) {
                calibrationValueSum += getCalibrationValue(line);
            }
            System.out.println("Solution: " + calibrationValueSum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param line Text that we want to determine a calibration value from.
     * @return The integer found by combining the first digit and the last digit (in that order) in the line.
     *     Will always be a single two-digit number unless the line contains no digits in which case 0 will be returned.
     */
    private static int getCalibrationValue(String line) {
        int lineLength = line.length();
        StringBuilder calibrationValueStringBuilder = new StringBuilder();
        // Find the first digit by starting at the beginning of the string
        for(int i = 0; i < lineLength; i++) {
            if (appendCharacterIfDigit(line.charAt(i), calibrationValueStringBuilder)) {
                break;
            }
        }
        // Find the last digit by starting from the end of the string
        for (int i = lineLength - 1; i >= 0; i--) {
            if (appendCharacterIfDigit(line.charAt(i), calibrationValueStringBuilder)) {
                break;
            }
        }
        if (!calibrationValueStringBuilder.isEmpty()) {
            String calibrationValueString = calibrationValueStringBuilder.toString();
            return Integer.parseInt(calibrationValueString);
        }
        return 0;
    }

    private static boolean appendCharacterIfDigit(char character, StringBuilder calibrationValueStringBuilder) {
        if (Character.isDigit(character)) {
            calibrationValueStringBuilder.append(character);
            return true;
        }
        return false;
    }
}
