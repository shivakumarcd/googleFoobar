//package com.google.challenges;
//Google foobar coding challenge Level-4 Code-1 Attempt-1
package GoogleChallenge4Code1A1;
import java.util.*;

public class Answer {
    public static void main(String[] args) {
        //input 1
        int[] entrances = {0, 1};
        int[] exits = {4, 5};
        int[][] path = {{1, 0, 4, 6, 0, 0},
                {0, 1, 5, 2, 0, 0},
                {0, 0, 1, 0, 4, 4},
                {0, 0, 0, 1, 6, 6},
                {0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 1}};
        System.out.println("*****************************************for input 1:"+answer(entrances, exits, path));
        //input 2
        entrances = new int[]{0};
        exits = new int[]{3};
        path = new int[][]{ {0, 7, 0, 0},
                {0, 0, 6, 0},
                {0, 0, 0, 8},
                {9, 0, 0, 0} };
        System.out.println("*****************************************for input 2:"+answer(entrances, exits, path));
        //input with loop 1
        entrances = new int[]{0,2};
        exits = new int[]{4};
        path = new int[][]{ {0, 3, 0, 0, 0},
                {0, 0, 2, 0, 5},
                {0, 0, 0, 4, 0},
                {0, 6, 0, 0, 0},
                {0, 0, 0, 0, 0} };
        System.out.println("*****************************************for input 3:"+answer(entrances, exits, path));
        //input with loop 2
        entrances = new int[]{0,1};
        exits = new int[]{4,5};
        path = new int[][]{ {0, 0, 10, 0, 0, 0},
                            {0, 0, 0, 8, 0, 0},
                            {0, 0, 0, 4, 6, 0},
                            {0, 0, 4, 0, 0, 12},
                            {0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0}};
        System.out.println("*****************************************for input 4:"+answer(entrances, exits, path));

        entrances = new int[]{0};
        exits = new int[]{0};
        path = new int[][]{ {0} };
        System.out.println("*****************************************for input 5:"+answer(entrances, exits, path));
    }

    public static final int SOME_BIG_VALUE = 2146999999;

    public static int answer(int[] entrances, int[] exits, int[][] path) {
        if (path == null || entrances == null || exits == null){
            return 0;
        }
        if(path.length<2 || entrances.length<1 || exits.length<1){
            return 0;
        }
        //below makes difference with one test case
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j <path[i].length ; j++) {
                if(i==j)
                    path[i][j]=0;
            }
        }
        //creating all nodes
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < path.length; i++) {
            nodes.add(new Node(i));
        }
        Node.constructGraph(path, nodes);

        int total = 0;
        for(int src:entrances) {
            //System.out.println("for src: "+ src);
            Node start = nodes.get(src);
            int pathCapacity = 0;
            do {
                if(start.discard)
                    break;
                pathCapacity = findCapacityOfLoopLessPath(src, exits, nodes);
                total = total + pathCapacity;
            } while (pathCapacity != 0);
        }
        return total;
    }

    /**
     *Returns >0 if valid path is found between src and one of the exits
     * Returns  0 if valid path is not found between src and any of exits
     * Apart, below function   *overcomes the loop while finding the path
     *alters graph as new paths are  discovered
     *removes dead-end path frm src to non-exit
     */
    public static int findCapacityOfLoopLessPath(int src, int[] exits, ArrayList<Node> nodes) {
        ArrayList<Node> path = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node start = nodes.get(src);
        stack.push(start);

        boolean reachedExit = modifiedDFS(path, stack, exits);
        int smallestCorridorSizeInPath = 0;
        if(!reachedExit){
            return smallestCorridorSizeInPath;
        }
        else{
            smallestCorridorSizeInPath = findSmallestCorridorSizeInPath(path);
            if(smallestCorridorSizeInPath != SOME_BIG_VALUE) {
                reduceCorridorSizeInPath(path, smallestCorridorSizeInPath, exits);
                return smallestCorridorSizeInPath;
            }
        }
        return smallestCorridorSizeInPath;
    }

    /**
     * Does dfs until one of the exit is reached
     * Parallelly putting nodes into path as they get discovered to reach the one of exits
     */
    private static boolean modifiedDFS(ArrayList<Node> path, Stack<Node> stack, int[] exits) {
        while(!stack.empty()) {
            Node current = stack.pop();
            if(Node.isNodeInPath(current, path)) {
                return modifiedDFS(path,stack,exits);
            }else {
                path.add(current);
            }
            if(isNodeOneOfExits(current,exits)) {
                return true;
            }
            HashMap<Node, Integer> corridorWeightToReachNextNode = current.getCorridorWeightToReachNextNode();
            for(Node node:corridorWeightToReachNextNode.keySet()) {
                if(!stack.contains(node) && !node.discard)
                    stack.push(node);
            }
        }
        return false;
    }

    public static int findSmallestCorridorSizeInPath(ArrayList<Node> path) {
        if(path.size() < 2){
            return 0;//may be if exception is thrown then we can debug more easily
        }
        int smallestCorridorSizeInPath = SOME_BIG_VALUE;

        //System.out.print("path : ");
        for (int j = 0; j <path.size() ; j++) {
            //System.out.print(path.get(j).toString()+", ");
        }

        int i;
        for (i = 0; i < path.size()-1; i++) {
            Node currentNode = path.get(i);
            Node nextNode = path.get(i+1);
            HashMap<Node, Integer> corridorWeightToReachNextNode = currentNode.getCorridorWeightToReachNextNode();
            if(corridorWeightToReachNextNode.get(nextNode)<smallestCorridorSizeInPath) {
                smallestCorridorSizeInPath = corridorWeightToReachNextNode.get(nextNode);
            }
        }
        //System.out.println("shortest corridor size in the path:" + smallestCorridorSizeInPath);
        return smallestCorridorSizeInPath;
    }

    /**
     * reduce corridor size of each in path by smallestCorridorSizeInPath
     * Removes the corresponding path with that smallest size from the graph
     * by removing respective node with smallestCorridorSizeInPath from  corridorWeightToReachNextNode
     * Also, makes node.discard = true if node's nextNode list is empty
     */
    public static void reduceCorridorSizeInPath(ArrayList<Node> path, int smallestCorridorSizeInPath, int[] exits) {
        if(path == null || exits == null){
            return;
        }
        if(path.size()<2 && exits.length==0)
            return;
        for (int i = 0; i < path.size()-1 ; i++) {
            Node currentNode = path.get(i);
            Node nextNode = path.get(i+1);
            if(currentNode==null || nextNode==null){
                return;
            }
            HashMap<Node, Integer> corridorWeightToReachNextNode = currentNode.getCorridorWeightToReachNextNode();
            if(corridorWeightToReachNextNode==null || corridorWeightToReachNextNode.size()==0) {
                return;
            }
            if(corridorWeightToReachNextNode.get(nextNode)==null) {
                return;
            }
            int currentCorridorSize = 0;
            currentCorridorSize = corridorWeightToReachNextNode.get(nextNode);
            if(currentCorridorSize==0 || currentCorridorSize == SOME_BIG_VALUE){
                return;
            }

            corridorWeightToReachNextNode.put(nextNode, (currentCorridorSize-smallestCorridorSizeInPath));
            if(currentCorridorSize == smallestCorridorSizeInPath) {
                corridorWeightToReachNextNode.remove(nextNode);
                if(corridorWeightToReachNextNode.size()==0 && !isNodeOneOfExits(currentNode,exits)) {
                    currentNode.discard = true;
                    //System.out.println("discarded node:"+ currentNode.toString());
                }
            }
        }
    }

    public static boolean isNodeOneOfExits(Node node, int[] exits) {
        for (int i = 0; i < exits.length; i++) {
            if(node.getIndex() == exits[i])
                return true;
        }
        return false;
    }
}
    class Node {
        int index;
        HashMap<Node, Integer> corridorWeightToReachNextNode = null;
        Boolean discard = false;
        public Node(int index) {
            this.index = index;
            corridorWeightToReachNextNode = new HashMap<>();
        }
        public int getIndex() {
            return index;
        }

        public HashMap<Node, Integer> getCorridorWeightToReachNextNode() {
            return corridorWeightToReachNextNode;
        }

        public static Node constructGraph(int[][] matrix, List<Node> nodes) {
            for(int i = 0; i < matrix.length; i++) {
                Node currentNode = nodes.get(i);
                for(int j=0; j<matrix[i].length; j++) {
                    if(matrix[i][j] != 0) {
                        Node nextNode = nodes.get(j);
                        currentNode.corridorWeightToReachNextNode.put(nextNode,matrix[i][j]);
                    }
                }
            }
            return nodes.get(0);
        }

        @Override
        public boolean equals(Object obj) {
            Node node = (Node)obj;
            if(node.index == this.index)
                return true;
            return false;
        }
        @Override
        public int hashCode() {
            return index % 2;
        }

        @Override
        public String toString() {
            return Integer.toString(this.index);
        }

        public static boolean isNodeInPath(Node n, ArrayList<Node> path) {
            if(path == null || n == null) {
                return false;
            }
            boolean alreadyInPath = false;
            for( Node nodeInPath : path) {
                if(nodeInPath.equals(n))
                    return true;
            }
            return false;
        }
    }