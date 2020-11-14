package edu;

public class CustomList {
    private int [] ints;

    public CustomList() {
        ints = new int[0];
    }

    public CustomList(int[] ints) {
        this.ints = ints;
    }

    public boolean any() {
        return ints.length > 0;
        // don't do this:
        // return !(ints.length < 1)
    }

    public int[] getAll() {
        return ints;
    }

    public void add(int number){
        int [] newArray = new int [ints.length+1];
        for (int i=0; i<ints.length;i++){
            newArray[i] = ints[i];
        }
        newArray[ints.length] = number;
        ints = newArray;
    }

    public void removeAT(int index){
        int [] smallerArray = new int[ints.length-1];
        for (int i = 0; i < ints.length; i++){
            if (i == index) {
                continue;
            }
            smallerArray[i] = ints[i];
        }
        ints = smallerArray;
    }
}
//- add(int number) - adds a number at the end of a list;
//- removeAt(int index)- removes number at specified index of a list;
//- getAll()- gets all elements inside the list
//- any()- returns true if list has any elements