package com.example.projectfinder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class RecommenderSystem {

    /* function that reverses array and stores it
           in another array*/
    public static int[] reverse(int[] a, int n) {
        int[] b = new int[n];
        int j = n;
        for (int i = 0; i < n; i++) {
            b[j - 1] = a[i];
            j = j - 1;
        }
        return b;
    }


    //Example
    //           String[] userInterests = {"ML", "FP"};
    //        String[][] projects = {{"ML", "AI"}, {"ML", "FP"}, {"OOP"}};

    public static void recommendations(String[] projects, String[] userInterests) {
        int[] projectIndex = new int[projects.length];

        //top option index to bottom option index
        int[] sortedOrder = new int[projects.length];

        //number of times a key word appears
        int[] score = new int[projects.length];

        //pair score with index
        int[][] pairs = new int[projects.length][2];

        //assign values 0-length of projects-1 to array eg 0,1,2..., these are indexes corresponding to project
        for (int i = 0; i < projectIndex.length; i++) {
            projectIndex[i] = i;
        }

        //get the score for each project - how many key words there are per project
        for (int i = 0; i < projects.length; i++) {
            for (String userInterest : userInterests) {
                for (String e : projects) {
                    if (userInterest.equals(e)) {
                        score[i] += 1;
                    }
                }
            }
        }


        //link the project index and its score together in a 2d array [score, index]
        for (int i = 0; i < projects.length; i++) {
            pairs[i] = new int[]{score[i], projectIndex[i]};
        }


        System.out.println("Scores" + Arrays.toString(score));
        System.out.println("Indexed" + Arrays.toString(projectIndex));
        for (int[] p : pairs) {
            System.out.print(Arrays.toString(p));
        }

        //sort the pairs 2d array according to first element
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);

        //obtains project indexes of the sorted 2d array
        for (int i = 0; i < pairs.length; i++) {
            sortedOrder[i] = pairs[i][1];
        }
        //since the sorting array sorts in increasing order, have to reverse the array to get in decreasing order
        sortedOrder = reverse(sortedOrder, sortedOrder.length);

        for (int[] p : pairs) {
            System.out.print(Arrays.toString(p));
        }

        System.out.println("Sorted: " + Arrays.toString(sortedOrder));


    }



        //sample code on how it works

            /*ArrayList<String> userInterests = new ArrayList<String>();
        userInterests.add("ML");
        userInterests.add("FP");*/


            /*ArrayList<String> projects = new ArrayList<String>();
            projects.add("ML");
            projects.add("FP");*/

        //project 1-0, project 2-1 et

}
