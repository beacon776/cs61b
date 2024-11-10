package IntList;

public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;//first 一定放一个数
        rest = r;//rest可以放一个数，也可以指向下一个list
    }

    /** Return the size of the list using... recursion! */
    public int size() {//递归版本求list长度
        if (rest == null) {
            return 1;
        }
        return 1 + this.rest.size();//在调用子节点的时候，每一个子节点都是正确递增过的，因为每一个子节点都会加一，也就是说，头上是1，第二个就是2，第三个就是3，依次递增
    }

    /** Return the size of the list using no recursion! */
    public int iterativeSize() {
        IntList p = this;//指针，用这个指针扫描list,求list长度第二个方法
        int totalSize = 0;
        while (p != null) {
            totalSize += 1;
            p = p.rest;
        }
        return totalSize;
    }

    /** Returns the ith item of this IntList. */
    public int get(int i) {//下标从0开始，调用第i个list的first值
        if (i == 0) {
            return first;
        }
        return rest.get(i - 1);
    }

    /** Method to return a string representation of an IntList */
    public String toString() {
        if (rest == null) {
            // Converts an Integer to a String!
            return String.valueOf(first);
        } else {
            return first + " -> " + rest.toString();
        }
    }

    /**
     * Method to create an IntList from an argument list.
     * You don't have to understand this code. We have it here
     * because it's convenient with testing. It's used like this:
     *
     * IntList myList = IntList.of(1, 2, 3, 4, 5);
     * will create an IntList 1 -> 2 -> 3 -> 4 -> 5 -> null.
     *
     * You can pass in any number of arguments to IntList.of and it will work:
     * IntList mySmallerList = IntList.of(1, 4, 9);
     */
    public static IntList of(int ...argList) {
        if (argList.length == 0)
            return null;
        int[] restList = new int[argList.length - 1];
        System.arraycopy(argList, 1, restList, 0, argList.length - 1);
        return new IntList(argList[0], IntList.of(restList));
    }
}
