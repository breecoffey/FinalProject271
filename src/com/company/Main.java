package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here

        //note: choice of user input or upload files
        CargoListGenerator testGenerator = new CargoListGenerator();

        int[] a = {1,3,4,5};
        int[] v = {1,4,5,7};

        int returnValue = testGenerator.calculateMaxValue(7, a, v, 4);
        System.out.println(returnValue);

        int val[] = new int[]{60, 100, 120};
        int wt[] = new int[]{10, 20, 30};
        int  W = 50;
        int n = val.length;
        System.out.println(testGenerator.calculateMaxValue(W, wt, val, n));

        //int maxWeight, int weightArray[], int valueArray[], int numItems
    }
    public static int calculateMaxValue(int maxWeight, int weightArray[], int valueArray[], int numItems){ //todo change to arraylist? /// change to private??

        int indexOfAnItem, incrementWeight;
        int K[][] = new int[numItems+1][maxWeight+1]; //[row][column]

        // Build table K[][] in bottom up manner
        //finding solutions to different combinations of items and max weights. then storing them
        for (indexOfAnItem = 0; indexOfAnItem <= numItems; indexOfAnItem++)
        {//considering one by one all items
            for (incrementWeight = 0; incrementWeight <= maxWeight; incrementWeight++)
            {// trying different poss weights scenarios
                if (indexOfAnItem==0 || incrementWeight==0) //if items or weight is zero, solution must be zero
                    K[indexOfAnItem][incrementWeight] = 0;
                else if (weightArray[indexOfAnItem-1] <= incrementWeight) // if the weight of a given item is less than the incrementWeight, enter this branch
                    K[indexOfAnItem][incrementWeight] = max(valueArray[indexOfAnItem-1] + K[indexOfAnItem-1][incrementWeight-weightArray[indexOfAnItem-1]],  K[indexOfAnItem-1][incrementWeight]);
                    //compare the value of including the item verses not including it our optimal set (comparison is done with the max() method.
                    // then stores the max value in the MDarray.
                else //if weight goes over, don't include.
                    K[indexOfAnItem][incrementWeight] = K[indexOfAnItem-1][incrementWeight];
                //
            }
        }
        //todo  know for sure if the is all possible solutions bellow the weight limit or not.

        return K[numItems][maxWeight];
    }

    public static int max(int first, int second){ //rename parameters to reflect what we intend the values to represent

        return (first > second)? first : second; //todo more representitive names
    }

}
