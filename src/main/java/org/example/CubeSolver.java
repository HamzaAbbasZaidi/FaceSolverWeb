package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CubeSolver {


    static List<String> cubeCorners = null;
    static List<String> cubeEdges = null;
    static String ULSolution;
    static String UFLSolution;
    static String UFSolution;
    static String URSolution;
    static String UFRSolution;
    static String UBSolution;
    static String UBLSolution;
    static String UBRSolution;

    public static String solve(String input) {

        System.out.println("Received input: " + input);

        String solution;
        if (!StringVerifier.verifyString(input)) {
            return "Invalid cube input.";
        }

        switch (input.charAt(4)) {
            case 'w' -> {
                cubeCorners = new ArrayList<>(Arrays.asList("w", "w", "w", "w", "o", "o", "o", "o", "g", "g", "g", "g", "r", "r", "r", "r", "b", "b", "b", "b", "y", "y", "y", "y"));
                cubeEdges = new ArrayList<>(Arrays.asList("w", "w", "w", "w", "o", "o", "o", "o", "g", "g", "g", "g", "r", "r", "r", "r", "b", "b", "b", "b", "y", "y", "y", "y"));
                solution = "";
            }
            case 'g' -> {
                cubeCorners = new ArrayList<>(Arrays.asList("g", "g", "g", "g", "o", "o", "o", "o", "y", "y", "y", "y", "r", "r", "r", "r", "w", "w", "w", "w", "b", "b", "b", "b"));
                cubeEdges = new ArrayList<>(Arrays.asList("g", "g", "g", "g", "o", "o", "o", "o", "y", "y", "y", "y", "r", "r", "r", "r", "w", "w", "w", "w", "b", "b", "b", "b"));
                solution = "x ";
            }
            case 'r' -> {
                cubeCorners = new ArrayList<>(Arrays.asList("r", "r", "r", "r", "w", "w", "w", "w", "g", "g", "g", "g", "y", "y", "y", "y", "b", "b", "b", "b", "o", "o", "o", "o"));
                cubeEdges = new ArrayList<>(Arrays.asList("r", "r", "r", "r", "w", "w", "w", "w", "g", "g", "g", "g", "y", "y", "y", "y", "b", "b", "b", "b", "o", "o", "o", "o"));
                solution = "z' ";
            }
            case 'o' -> {
                cubeCorners = new ArrayList<>(Arrays.asList("o", "o", "o", "o", "y", "y", "y", "y", "g", "g", "g", "g", "w", "w", "w", "w", "b", "b", "b", "b", "r", "r", "r", "r"));
                cubeEdges = new ArrayList<>(Arrays.asList("o", "o", "o", "o", "y", "y", "y", "y", "g", "g", "g", "g", "w", "w", "w", "w", "b", "b", "b", "b", "r", "r", "r", "r"));
                solution = "z ";
            }
            case 'b' -> {
                cubeCorners = new ArrayList<>(Arrays.asList("b", "b", "b", "b", "o", "o", "o", "o", "w", "w", "w", "w", "r", "r", "r", "r", "y", "y", "y", "y", "g", "g", "g", "g"));
                cubeEdges = new ArrayList<>(Arrays.asList("b", "b", "b", "b", "o", "o", "o", "o", "w", "w", "w", "w", "r", "r", "r", "r", "y", "y", "y", "y", "g", "g", "g", "g"));
                solution = "x' ";
            }
            default -> {
                cubeCorners = new ArrayList<>(Arrays.asList("y", "y", "y", "y", "o", "o", "o", "o", "b", "b", "b", "b", "r", "r", "r", "r", "g", "g", "g", "g", "w", "w", "w", "w"));
                cubeEdges = new ArrayList<>(Arrays.asList("y", "y", "y", "y", "o", "o", "o", "o", "b", "b", "b", "b", "r", "r", "r", "r", "g", "g", "g", "g", "w", "w", "w", "w"));
                solution = "x2 ";
            }
        }

        String ULSticker = String.valueOf(input.charAt(3));

        List<String> finalCubeEdges = cubeEdges;
        List<Integer> indices = IntStream.range(0, 24)
                .filter(i -> Objects.equals(finalCubeEdges.get(i), ULSticker))
                .boxed().toList();

        List<Integer> ULMoveCount = Arrays.asList(1, 1, 1, 0, 3, 2, 3, 2, 2, 2, 2, 1, 3, 2, 3, 2, 2, 1, 2, 2, 2, 2, 2, 1);
        List<String> ULMoves = Arrays.asList("U' ", "U2 ", "U ", "", "L F U ", "F U ", "L' F U ", "B' U' ", "F' L' ", "R U2 ", "F L' ", "L' ", "R' F' U ", "B U' ", "R F' U ", "F' U ", "B L ", "L ", "B' L ", "R' U2 ", "F2 U ", "R2 U2 ", "D' L2 ", "L2 ");

        Integer lowestMoves = 10;

        for (int i = 0; i < 4; i++) {
            if (ULMoveCount.get(indices.get(i)) < lowestMoves) {
                lowestMoves = ULMoveCount.get(indices.get(i));
            }
        }


        for (Integer i : indices) {
            if (Objects.equals(ULMoveCount.get(i), lowestMoves)) {
                ULSolution = solution.concat(ULMoves.get(i));
                changeCube(ULMoves.get(i));
                break;
            }
        }

        // UL Solved

        String UFLSticker = String.valueOf(input.charAt(6));

        List<String> postULCubeCorners = cubeCorners;
        indices = IntStream.range(0, 24)
                .filter(i -> Objects.equals(postULCubeCorners.get(i), UFLSticker))
                .boxed().toList();

        List<Integer> UFLMoveCount = Arrays.asList(3,2,3,0,3,3,1,3,3,2,2,3,1,2,2,2,3,3,2,2,2,1,2,2);
        List<String> UFLMoves = Arrays.asList("B2 D' F2 ","R2 F2 ","R2 D' F2 ","","B D2 F2 ","F' D F2 ","F ","D2 R F' ","F R' F2 ","R' F2 ","D' F ","D R F' ","F' ","R' F' ","R2 F' ","R F' ","R D' F2 ","B D F ","D F ","R F2 ","D F2 ","F2 ","D' F2 ","D2 F2 ");

        lowestMoves = 10;

        for (int i = 0; i < 4; i++) {
            if (UFLMoveCount.get(indices.get(i)) < lowestMoves) {
                lowestMoves = UFLMoveCount.get(indices.get(i));
            }
        }

        for (Integer i : indices) {
            if (Objects.equals(UFLMoveCount.get(i), lowestMoves)) {
                UFLSolution = ULSolution.concat(UFLMoves.get(i));
                changeCube(UFLMoves.get(i));
                break;
            }
        }

        // UFL solved

        String UFSticker = String.valueOf(input.charAt(7));

        List<String> postUFLCubeEdges = cubeEdges;
        indices = IntStream.range(0, 24)
                .filter(i -> Objects.equals(postUFLCubeEdges.get(i), UFSticker))
                .boxed().toList();

        List<Integer> UFMoveCount = Arrays.asList(3,3,0,100,100,5,3,3,5,4,4,6,5,5,4,3,4,3,4,4,3,4,4,3);
        List<String> UFMoves = Arrays.asList("L U2 L' ","L U L' ","","nothing","nothing","U' F U' F' U ","L' F L ","L2 F L2 ","L' F' L' U' L2 ","R L U L' ","D' L' F L ","U2 L' R U R' U2 ","R2 D2 L' F L ","R D2 L' F L ","D2 L' F L ","L' F' L ","B L U' L' ","L U' L' ","D L' F L ","R' L U L' ","L' F2 L ","D' L' F2 L ","D L2 U' L2 ","L2 U' L2 ");

        lowestMoves = 10;

        for (int i = 0; i < 4; i++) {
            if (UFMoveCount.get(indices.get(i)) < lowestMoves) {
                lowestMoves = UFMoveCount.get(indices.get(i));
            }
        }

        for (Integer i : indices) {
            if (Objects.equals(UFMoveCount.get(i), lowestMoves)) {
                UFSolution = UFLSolution.concat(UFMoves.get(i));
                changeCube(UFMoves.get(i));
                break;
            }
        }

        // UF solved

        String URSticker = String.valueOf(input.charAt(5));

        List<String> postUFCubeEdges = cubeEdges;
        indices = IntStream.range(0, 24)
                .filter(i -> Objects.equals(postUFCubeEdges.get(i), URSticker))
                .boxed().toList();

        List<Integer> URMoveCount = Arrays.asList(3,0,100,100,100,3,3,3,100,1,3,3,4,3,3,3,2,2,2,1,2,1,2,2);
        List<String> URMoves = Arrays.asList("B2 D' R2 ","","nothing","nothing","nothing","U F U' ","D' B R' ","U' B' U ","nothing","R ","F' R F ","U2 L' U2 ","R' U F' U' ","U' B U ","D B R' ","U F' U' ","B' R' ","B2 R' ","B R' ","R' ","D R2 ","R2 ","D' R2 ","D2 R2 ");

        lowestMoves = 10;

        for (int i = 0; i < 4; i++) {
            if (URMoveCount.get(indices.get(i)) < lowestMoves) {
                lowestMoves = URMoveCount.get(indices.get(i));
            }
        }

        for (Integer i : indices) {
            if (Objects.equals(URMoveCount.get(i), lowestMoves)) {
                URSolution = UFSolution.concat(URMoves.get(i));
                changeCube(URMoves.get(i));
                break;
            }
        }

        // UR Solved

        String UFRSticker = String.valueOf(input.charAt(8));

        List<String> postURCubeCorners = cubeCorners;
        indices = IntStream.range(0, 24)
                .filter(i -> Objects.equals(postURCubeCorners.get(i), UFRSticker))
                .boxed().toList();

        List<Integer> UFRMoveCount = Arrays.asList(4,5,0,100,5,100,3,3,100,6,3,4,6,4,4,3,4,4,3,3,5,5,4,5);
        List<String> UFRMoves = Arrays.asList("B F D2 F' ","B' D2 R' D R ","","nothing","B2 D2 R' D R ","nothing","R' D R ","F D2 F' ","nothing","R' D R2 F' R' F ","F D F' ","D R' D' R ","F D' F2 R F R' ","B2 F D2 F' ","D2 R' D R ","R' D' R ","B2 R' D2 R ","B R' D2 R ","R' D2 R ","F D' F' ","D2 B' F D2 F' ","D B' F D2 F' ","B' F D2 F' ","B D2 R' D R ");

        lowestMoves = 10;

        for (int i = 0; i < 4; i++) {
            if (UFRMoveCount.get(indices.get(i)) < lowestMoves) {
                lowestMoves = UFRMoveCount.get(indices.get(i));
            }
        }

        for (Integer i : indices) {
            if (Objects.equals(UFRMoveCount.get(i), lowestMoves)) {
                UFRSolution = URSolution.concat(UFRMoves.get(i));
                changeCube(UFRMoves.get(i));
                break;
            }
        }

        // UFR Solved

        String UBSticker = String.valueOf(input.charAt(1));

        List<String> postUFRCubeEdges = cubeEdges;
        indices = IntStream.range(0, 24)
                .filter(i -> Objects.equals(postUFRCubeEdges.get(i), UBSticker))
                .boxed().toList();

        List<Integer> UBMoveCount = Arrays.asList(0,100,100,100,100,3,3,1,100,3,4,3,100,1,3,3,4,3,4,3,2,2,1,2);
        List<String> UBMoves = Arrays.asList("","nothing","nothing","nothing","nothing","U2 F U2 ","L B' L' ","B' ","nothing","U R U' ","D R' B R ","U' L' U ","nothing","B ","R' B R ","U2 F' U2 ","B' U R' U' ","U' L U ","D' R' B R ","U R' U' ","D2 B2 ","D B2 ","B2 ","D' B2 ");

        lowestMoves = 10;

        for (int i = 0; i < 4; i++) {
            if (UBMoveCount.get(indices.get(i)) < lowestMoves) {
                lowestMoves = UBMoveCount.get(indices.get(i));
            }
        }

        for (Integer i : indices) {
            if (Objects.equals(UBMoveCount.get(i), lowestMoves)) {
                UBSolution = UFRSolution.concat(UBMoves.get(i));
                changeCube(UBMoves.get(i));
                break;
            }
        }

        // UB finished

        String UBLSticker = String.valueOf(input.charAt(0));

        List<String> postUBCubeCorners = cubeCorners;
        indices = IntStream.range(0, 24)
                .filter(i -> Objects.equals(postUBCubeCorners.get(i), UBLSticker))
                .boxed().toList();

        List<Integer> UBLMoveCount = Arrays.asList(0,6,100,100,6,100,4,3,100,100,3,3,100,6,3,3,5,6,3,4,6,6,5,5);
        List<String> UBLMoves = Arrays.asList("","R D2 R' B D' B' ","nothing","nothing","B D' B2 L B L' ","nothing","D2 L' D L ","L' D' L ","nothing","nothing","L' D2 L ","B D' B' ","nothing","R D R' B D B' ","L' D L ","B D2 B' ","B' D2 B2 D' B' ","L' D L2 B' L' B ","B D B' ","D L' D' L ","D' L2 D' L2 D L2 ","D2 L2 D' L2 D L2 ","B' D B2 D' B' ","L2 D' L2 D L2 ");

        lowestMoves = 10;

        for (int i = 0; i < 4; i++) {
            if (UBLMoveCount.get(indices.get(i)) < lowestMoves) {
                lowestMoves = UBLMoveCount.get(indices.get(i));
            }
        }

        for (Integer i : indices) {
            if (Objects.equals(UBLMoveCount.get(i), lowestMoves)) {
                UBLSolution = UBSolution.concat(UBLMoves.get(i));
                changeCube(UBLMoves.get(i));
                break;
            }
        }

        // UBL Finished

        String UBRSticker = String.valueOf(input.charAt(2));

        List<String> postUBLCubeCorners = cubeCorners;
        indices = IntStream.range(0, 24)
                .filter(i -> Objects.equals(postUBLCubeCorners.get(i), UBRSticker))
                .boxed().toList();

        List<Integer> UBRMoveCount = Arrays.asList(100,0,100,100,100,100,3,3,100,100,3,3,100,6,3,4,6,100,4,3,6,6,5,6);
        List<String> UBRMoves = Arrays.asList("nothing","","nothing","nothing","nothing","nothing","B' D2 B ","R D' R' ","nothing","nothing","B' D B ","R D2 R' ","nothing","B' D B2 R' B' R ","R D R' ","D2 R D' R' ","R D' R2 B R B' ","nothing","D' R D R' ","B' D' B ","R D R2 B R B' ","D R2 D R2 D' R2 ","R2 D R2 D' R2 ","D' R2 D R2 D' R2 ");

        lowestMoves = 10;

        for (int i = 0; i < 4; i++) {
            if (UBRMoveCount.get(indices.get(i)) < lowestMoves) {
                lowestMoves = UBRMoveCount.get(indices.get(i));
            }
        }

        for (Integer i : indices) {
            if (Objects.equals(UBRMoveCount.get(i), lowestMoves)) {
                UBRSolution = UBLSolution.concat(UBRMoves.get(i));
                // System.out.println(UBRSolution);
                changeCube(UBRMoves.get(i));
                break;
            }
        }

        List<String> listOfMoves = cancel(Arrays.asList(UBRSolution.split(" ")));

        return String.join(" ", listOfMoves);
    }

    private static List<String> cancel(List<String> sb) {
        while(isNotSimplified(sb) && sb.size() >= 1) {
            sb = makeFirstSimplificationPossible(sb);
        }
        return sb;
    }

    private static List<String> makeFirstSimplificationPossible(List<String> sb) {

        List<String> filtered = null;

        for(int i = 0; i < sb.size() - 1; i++) {
            if(sb.get(i).charAt(0) == sb.get(i + 1).charAt(0)) {
                int rotation = 0;
                if(sb.get(i).length() == 1) {
                    rotation += 1;
                }
                else if(sb.get(i).charAt(1) == '2') {
                    rotation += 2;
                }
                else {
                    rotation -= 1;
                }

                if(sb.get(i + 1).length() == 1) {
                    rotation += 1;
                }
                else if(sb.get(i + 1).charAt(1) == '2') {
                    rotation += 2;
                }
                else {
                    rotation -= 1;
                }

                switch (rotation) {
                    case 0, 4  -> sb.set(i, "");
                    case 1     -> sb.set(i, Character.toString(sb.get(i).charAt(0)));
                    case -2, 2 -> sb.set(i, Character.toString(sb.get(i).charAt(0)).concat("2"));
                    case 3     -> sb.set(i, Character.toString(sb.get(i).charAt(0)).concat("'"));
                }
                sb.set(i+1, "");
                filtered = sb.stream().filter(k -> k != "").collect(Collectors.toList());
                break;
            }
        }
        return filtered;
    }

    private static boolean isNotSimplified(List<String> sb) {
        for(int i = 0; i < sb.size() - 1; i++) {
            if(sb.get(i).charAt(0) == sb.get(i + 1).charAt(0))
                return true;
        }
        return false;
    }

    private static void changeCube(String moves) {

        String[] moveList = moves.split(" ");

        for(String move : moveList) {
            makeMove(move);
        }

    }

    private static void makeMove(String move) {

        switch (move) {
            case "R" -> {
                cycleElements(cubeEdges, 1, 19, 21, 9);
                cycleElements(cubeEdges, 12, 13, 14, 15);
                cycleElements(cubeCorners, 12, 13, 14, 15);
                cycleElements(cubeCorners, 1, 19, 21, 9);
                cycleElements(cubeCorners, 2, 16, 22, 10);
            }
            case "R'" -> {
                cycleElements(cubeEdges, 1, 9, 21, 19);
                cycleElements(cubeEdges, 12, 15, 14, 13);
                cycleElements(cubeCorners, 12, 15, 14, 13);
                cycleElements(cubeCorners, 1, 9, 21, 19);
                cycleElements(cubeCorners, 2, 10, 22, 16);
            }
            case "R2" -> {
                makeMove("R");
                makeMove("R");
            }
            case "U" -> {
                cycleElements(cubeEdges, 0, 1, 2, 3);
                cycleElements(cubeEdges, 8, 4, 16, 12);
                cycleElements(cubeCorners, 0, 1, 2, 3);
                cycleElements(cubeCorners, 8, 4, 16, 12);
                cycleElements(cubeCorners, 9, 5, 17, 13);
            }
            case "U'" -> {
                cycleElements(cubeEdges, 0, 3, 2, 1);
                cycleElements(cubeEdges, 8, 12, 16, 4);
                cycleElements(cubeCorners, 0, 3, 2, 1);
                cycleElements(cubeCorners, 8, 12, 16, 4);
                cycleElements(cubeCorners, 9, 13, 17, 5);
            }
            case "U2" -> {
                makeMove("U");
                makeMove("U");
            }
            case "F" -> {
                cycleElements(cubeEdges, 2, 15, 20, 5);
                cycleElements(cubeEdges, 8, 9, 10, 11);
                cycleElements(cubeCorners, 2, 15, 20, 5);
                cycleElements(cubeCorners, 12, 21, 6, 3);
                cycleElements(cubeCorners, 8, 9, 10, 11);
            }
            case "F'" -> {
                cycleElements(cubeEdges, 5, 20, 15, 2);
                cycleElements(cubeEdges, 8, 11, 10, 9);
                cycleElements(cubeCorners, 5, 20, 15, 2);
                cycleElements(cubeCorners, 12, 3, 6, 21);
                cycleElements(cubeCorners, 8, 11, 10, 9);
            }
            case "F2" -> {
                makeMove("F");
                makeMove("F");
            }
            case "L" -> {
                cycleElements(cubeEdges, 3, 11, 23, 17);
                cycleElements(cubeEdges, 4, 5, 6, 7);
                cycleElements(cubeCorners, 0, 8, 20, 18);
                cycleElements(cubeCorners, 3, 11, 23, 17);
                cycleElements(cubeCorners, 4, 5, 6, 7);
            }
            case "L'" -> {
                cycleElements(cubeEdges, 3, 17, 23, 11);
                cycleElements(cubeEdges, 4, 7, 6, 5);
                cycleElements(cubeCorners, 0, 18, 20, 8);
                cycleElements(cubeCorners, 3, 17, 23, 11);
                cycleElements(cubeCorners, 4, 7, 6, 5);
            }
            case "L2" -> {
                makeMove("L");
                makeMove("L");
            }

            case "D" -> {
                cycleElements(cubeEdges, 20, 21, 22, 23);
                cycleElements(cubeEdges, 10, 14, 18, 6);
                cycleElements(cubeCorners, 20, 21, 22, 23);
                cycleElements(cubeCorners, 10, 14, 18, 6);
                cycleElements(cubeCorners, 11, 15, 19, 7);
            }

            case "D'" -> {
                cycleElements(cubeEdges, 20, 23, 22, 21);
                cycleElements(cubeEdges, 10, 6, 18, 14);
                cycleElements(cubeCorners, 20, 23, 22, 21);
                cycleElements(cubeCorners, 10, 6, 18, 14);
                cycleElements(cubeCorners, 11, 7, 19, 15);
            }

            case "D2" -> {
                makeMove("D");
                makeMove("D");
            }

            case "B" -> {
                cycleElements(cubeEdges, 16, 17, 18, 19);
                cycleElements(cubeEdges, 0, 7, 22, 13);
                cycleElements(cubeCorners, 16, 17, 18, 19);
                cycleElements(cubeCorners, 0, 7, 22, 13);
                cycleElements(cubeCorners, 1, 4, 23, 14);
            }

            case "B'" -> {
                cycleElements(cubeEdges, 16, 19, 18, 17);
                cycleElements(cubeEdges, 0, 13, 22, 7);
                cycleElements(cubeCorners, 16, 19, 18, 17);
                cycleElements(cubeCorners, 0, 13, 22, 7);
                cycleElements(cubeCorners, 1, 14, 23, 4);
            }

            case "B2" -> {
                makeMove("B");
                makeMove("B");
            }
        }
    }

    public static void cycleElements(List<String> list, int num1, int num2, int num3, int num4) {

        String temp = list.get(num1);
        list.set(num1, list.get(num4));
        list.set(num4, list.get(num3));
        list.set(num3, list.get(num2));
        list.set(num2, temp);

    }


    // Your face solving logic goes here

}
