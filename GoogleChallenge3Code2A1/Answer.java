package GoogleChallenge3Code2A1;//package com.google.challenges;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Answer {
    public static int[] answer(int[][] m) {
        // Your code goes here.
        ArrayList<OreNode> nodes = new ArrayList<OreNode>();
        for (int i = 0; i < m.length; i++) {
            nodes.add(new OreNode(i, findType(i, m[i])));
        }
        OreNode head = constructGraph(m, nodes);
        Map<Integer, OreFraction> singleLoopProbability = findLoopProbability(m, nodes);
        List<OrePath> paths = findAllPaths(head);
        addLoopProbability(paths, singleLoopProbability, nodes);

        return findFinalProbabilityArray(paths, nodes);
    }
    public static void addLoopProbability(List<OrePath> paths, Map<Integer, OreFraction> singleLoopProbability, ArrayList<OreNode> nodes) {
        if (singleLoopProbability == null) {
            for (int i = 0; i < paths.size(); i++) {
                OrePath path = paths.get(i);
                path.finalProbability = path.probabilityWithoutLoop;
            }
            return;
        }
        Integer loopNodeIndex = singleLoopProbability.keySet().iterator().next();
        OreNode nodeWithLoop = nodes.get(loopNodeIndex);
        for (int i = 0; i < paths.size(); i++) {
            OrePath path = paths.get(i);
            if (path.nodeList.contains(nodeWithLoop)) {
                path.loopProbability = singleLoopProbability.get(loopNodeIndex);
                path.finalProbability = OreFraction.multiply(path.probabilityWithoutLoop, new OreFraction(path.loopProbability.dn, (path.loopProbability.dn - path.loopProbability.nm)));
            } else {
                path.finalProbability = path.probabilityWithoutLoop;
            }
        }
    }
    public static Map<Integer, OreFraction> findLoopProbability(int[][] m, ArrayList<OreNode> nodes) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (i < j && m[i][j] != 0 && m[j][i] != 0) {
                    OreNode nodeI = nodes.get(i);
                    OreNode nodeJ = nodes.get(j);
                    OreFraction pItoJ = nodeI.nextNodes.get(nodeJ);
                    OreFraction pJtoI = nodeJ.nextNodes.get(nodeI);
                    Map<Integer, OreFraction> loopProbability = new HashMap<Integer, OreFraction>();
                    loopProbability.put(i, OreFraction.multiply(pItoJ, pJtoI));
                    return loopProbability;
                }
            }
        }
        return null;
    }
    public static int[] findFinalProbabilityArray(List<OrePath> paths, ArrayList<OreNode> nodes) {
        Map<Integer, OreFraction> finalProbability = new TreeMap<Integer, OreFraction>();
        for (int i = 0; i < paths.size(); i++) {
            OrePath path = paths.get(i);
            OreNode leafNode = path.nodeList.get(path.nodeList.size()-1);
            if (!leafNode.type.equals(OreNodeType.FINAL)) {
                continue;
            }
            OreFraction probability = finalProbability.get(leafNode.index);
            finalProbability.put(leafNode.index, probability == null ? path.finalProbability : OreFraction.add(probability, path.finalProbability));
        }

        // For unreachable final nodes
        for (int i = 0; i < nodes.size(); i++) {
            OreNode node = nodes.get(i);
            int nodeIndex = node.index;
            if ((!node.type.equals(OreNodeType.FINAL)) || finalProbability.keySet().contains(nodeIndex)) {
                continue;
            }
            finalProbability.put(nodeIndex, new OreFraction(0, 1));
        }

        int nr[] = new int[finalProbability.size()];
        int dr[] = new int[finalProbability.size()];
        int ans[] = new int[finalProbability.size()+1];

        Iterator<Integer> iterator = finalProbability.keySet().iterator();
        int nodeIndex = 0;
        while (iterator.hasNext()) {
            OreFraction probability = finalProbability.get(iterator.next());
            nr[nodeIndex] = probability.nm;
            dr[nodeIndex++] = probability.dn;
        }

        int lcm = lcm(dr);

        for (int i = 0; i < nr.length; i++) {
            ans[i] = (lcm / dr[i]) * nr[i];
        }
        ans[nr.length] = lcm;
        return ans;
    }
    public static int lcm(int[] numbers)
    {
        int lcm = numbers[0];
        for (int i=1; i<numbers.length; i++)
            lcm = ( ((numbers[i]*lcm)) /
                    (gcd(numbers[i], lcm)) );
        return lcm;
    }
    public static int gcd(int num1, int num2)
    {
        if (num2==0)
            return num1;
        return gcd(num2, num1%num2);
    }
    public static List<OrePath> findAllPaths(OreNode head) {
        List<OrePath> paths = new ArrayList<OrePath>();
        paths.add(new OrePath(head));
        addChildNodes(paths, head);
        return paths;
    }
    public static void addChildNodes(List<OrePath> paths, OreNode node) {
        if (node.type.equals(OreNodeType.FINAL)) {
            return;
        }
        Map<OreNode, OreFraction> nextNodeMap = node.nextNodes;
        List<OrePath> pathsToUpdate = getPathsWithLeaf(paths, node);
        List<OrePath> clonedList = copyPath(pathsToUpdate);
        Set<OreNode> nextNodes = nextNodeMap.keySet();
        Iterator<OreNode> nodeIterator = nextNodes.iterator();
        if (nodeIterator.hasNext()) {
            OreNode nextNode = nodeIterator.next();
            OreFraction probability = nextNodeMap.get(nextNode);
            addNextNode(pathsToUpdate, nextNode, probability);
        }
        while (nodeIterator.hasNext()) {
            List<OrePath> spareClonedList = copyPath(clonedList);
            OreNode nextNode = nodeIterator.next();
            OreFraction probability = nextNodeMap.get(nextNode);
            addNextNode(clonedList, nextNode, probability);
            paths.addAll(clonedList);
            clonedList = spareClonedList;
        }
        //Remove paths with loop
        List<OrePath> pathsWithLoop = new ArrayList<OrePath>();
        List<OreNode> nodesWithLoop = new ArrayList<OreNode>();
        for (int i = 0; i < paths.size(); i++) {
            OrePath path = paths.get(i);
            int lastIndex = path.nodeList.size() - 1;
            OreNode lastNode = path.nodeList.get(lastIndex);
            int indexOfLeafNode = path.nodeList.indexOf(lastNode);
            if (indexOfLeafNode != -1 && indexOfLeafNode != lastIndex) {
                pathsWithLoop.add(path);
                nodesWithLoop.add(lastNode);
            }
        }
        paths.removeAll(pathsWithLoop);
        nodeIterator = nextNodes.iterator();
        while (nodeIterator.hasNext()) {
            OreNode next = nodeIterator.next();
            if (nodesWithLoop.contains(next)) {
                continue;
            }
            addChildNodes(paths, next);
        }
    }
    public static void addNextNode(List<OrePath> paths, OreNode nextNode, OreFraction probability) {
        for (int i = 0; i < paths.size(); i++) {
            OrePath path = paths.get(i);
            path.addNode(nextNode, probability);
        }
    }
    public static List<OrePath> copyPath(List<OrePath> pathsToUpdate) {
        List<OrePath> clone = new ArrayList<OrePath>();
        for (int i = 0; i < pathsToUpdate.size(); i++) {
            clone.add(OrePath.duplicate(pathsToUpdate.get(i)));
        }
        return clone;
    }
    public static List<OrePath> getPathsWithLeaf(List<OrePath> paths, OreNode leafNode) {
        List<OrePath> indexes = new ArrayList<OrePath>();
        for (int i = 0; i < paths.size(); i++) {
            OrePath path = paths.get(i);
            List<OreNode> nodeList = path.nodeList;
            int lastNodeIndex = nodeList.size() - 1;
            if (nodeList.get(lastNodeIndex) == leafNode) {
                indexes.add(path);
            }
        }
        return indexes;
    }
    public static OreNode constructGraph(int[][] m, List<OreNode> nodes) {
        for(int i = 0; i < m.length; i++) {
            OreNode currentNode = nodes.get(i);
            int denominator = 0;
            for(int j=0; j<m[i].length; j++) {
                denominator += m[i][j];
            }
            for(int j=0; j<m[i].length; j++) {
                OreNode possibleNextNode = nodes.get(j);
                if(m[i][j] != 0) {
                    currentNode.add(possibleNextNode, new OreFraction(m[i][j], denominator));
                }
            }
        }
        return nodes.get(0);
    }
    public static String findType(int nodeId, int[] nodeRow) {
        if (nodeId == 0) {
            return OreNodeType.INITIAL;
        }

        for (int i = 0; i < nodeRow.length; i++) {
            if (nodeRow[i] != 0) {
                return OreNodeType.MID;
            }
        }
        return OreNodeType.FINAL;
    }
}
class OreFraction {
    int nm, dn;
    public OreFraction(int nm, int dn) {
        this.nm = nm;
        this.dn = dn;
        if (nm != 0 && dn!=0) {
            this.simplify();
        }
    }
    public void simplify() {
        int nm1 = nm;
        int dn1 = dn;
        int temp1 = nm;
        int temp2 = dn;
        while (nm1 != dn1){
            if(nm1 > dn1)
                nm1 = nm1 - dn1;
            else
                dn1 = dn1 - nm1;
        }
        int nm3 = temp1 / nm1 ;
        int nm4 = temp2 / nm1 ;
        nm = nm3;
        dn = nm4;
    }
    public static OreFraction add(OreFraction f1, OreFraction f2) {
        int den = f1.dn * f2.dn;
        int num = f1.nm * f2.dn + f2.nm * f1.dn;
        return new OreFraction(num,den);
    }
    public static OreFraction multiply(OreFraction a, OreFraction b) {
        return new OreFraction(a.nm * b.nm, a.dn * b.dn);
    }
    public static OreFraction duplicate(OreFraction fraction) {
        return (fraction == null ? null : new OreFraction(fraction.nm, fraction.dn));
    }
}
class OreNodeType {
    public static final String INITIAL = "INITIAL";
    public static final String MID = "MID";
    public static final String FINAL = "FINAL";
}
class OreNode {
    int index;
    Map<OreNode, OreFraction> nextNodes;
    String type;
    public OreNode(int index, String type) {
        this.index = index;
        this.type = type;
    }
    public void add(OreNode next, OreFraction probability) {
        if (nextNodes == null) {
            nextNodes = new HashMap<OreNode, OreFraction>();
        }
        nextNodes.put(next, probability);
    }
    public int hashCode() {
        return index;
    }
    public boolean equals(Object obj) {
        if (obj instanceof OreNode) {
            return index == ((OreNode)obj).index;
        }
        return super.equals(obj);
    }
}
class OrePath {
    List<OreNode> nodeList;
    OreFraction loopProbability;
    OreFraction probabilityWithoutLoop;
    OreFraction finalProbability;
    public OrePath(OreNode head) {
        nodeList = new ArrayList<OreNode>();
        nodeList.add(head);
    }
    public OrePath(List<OreNode> nodeList, OreFraction loopProbability, OreFraction probabilityWithoutLoop, OreFraction finalProbability) {
        this.nodeList = nodeList;
        this.loopProbability = loopProbability;
        this.probabilityWithoutLoop = probabilityWithoutLoop;
        this.finalProbability = finalProbability;
    }
    public void addNode(OreNode node, OreFraction probability) {
        nodeList.add(node);
        probabilityWithoutLoop = probabilityWithoutLoop == null ? probability : OreFraction.multiply(probabilityWithoutLoop, probability);
    }
    public static OrePath duplicate(OrePath path) {
        if (path == null) {
            return null;
        }
        List<OreNode> nodeListCopy = new ArrayList<OreNode>();
        for (int i = 0; i < path.nodeList.size(); i++) {
            nodeListCopy.add(path.nodeList.get(i));
        }
        return new OrePath(nodeListCopy, OreFraction.duplicate(path.loopProbability), OreFraction.duplicate(path.probabilityWithoutLoop), OreFraction.duplicate(path.finalProbability));
    }
}