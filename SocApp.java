import java.util.*;
import java.io.*;

public class SocApp {

    DiGraph myGraph = new DiGraph();
    ArrayList<String> relationships = new ArrayList<>();
    Map<String, Integer> namesIdMap = new HashMap<String, Integer>();

    HashSet<String> namesSet = new HashSet<String>();
    ArrayList<String> namesArray = new ArrayList<>();

    public SocApp(String filename) {
        String names = "";
        ArrayList<String> tempArray = new ArrayList<>();
        File file = new File(filename);
        try {
            Scanner sc = new Scanner(file);
            // Read all names into a string
            while (sc.hasNextLine()) {
                names += (sc.nextLine() + " ");
            }
            // Add the string of names to a temp array
            for (String name : names.split(" ")) {
                tempArray.add(name);
            }
            // Turn the list of names into a set to remove duplicates
            namesSet = new HashSet<String>(tempArray);
            for (String x : namesSet) {
                if (!x.isEmpty()) {
                    namesArray.add(x);
                }
            }
            // Initialize the graph with the ID's
            for (int i = 0; i < namesArray.size(); i++) {
                myGraph.addVertex(i);
            }
            // Add each pair of relationships to the graph
            for (int i = 0; i < tempArray.size(); i += 2) {
                myGraph.addEdge(namesArray.indexOf(tempArray.get(i)), namesArray.indexOf(tempArray.get(i + 1)));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // System.out.println(myGraph.toString());
    }

    public String mostPopular() {
        int mostPopular = 0;
        for (int i = 0; i < namesArray.size(); i++) {
            if (getNumFollowers(i) > getNumFollowers(mostPopular)) {
                mostPopular = i;
            }
        }
        return namesArray.get(mostPopular);
    }

    public int getNumFollowers(int person) {
        int numFollowers = 0;
        for (int i = 0; i < namesArray.size(); i++) {
            if (myGraph.getAdjacent(i).contains(person) == true) {
                numFollowers++;
            }
        }
        return numFollowers;
    }

    public String topFollower() {
        // Initialize top follower as the first follower
        int topFollower = 0;
        for (int i = 0; i < namesArray.size(); i++) {
            if (myGraph.getAdjacent(i).size() > myGraph.getAdjacent(topFollower).size()) {
                topFollower = i;
            }
        }
        return namesArray.get(topFollower);
    }

    boolean isInfluencer(String user) {
        boolean check = false;
        double numUsers = namesArray.size();
        double numFollowers = getNumFollowers(namesArray.indexOf(user));
        double followPercentage = (numFollowers / numUsers);
        if (followPercentage >= .40)
            check = true;
        return check;
    }

    public double reciprocity() {
        double numSymmetric = 0.0;
        for (int i = 0; i < namesArray.size(); i++) {
            for (int j = 0; j < namesArray.size(); j++) {
                if (myGraph.getAdjacent(i).contains(j) && myGraph.getAdjacent(j).contains(i)) {
                    numSymmetric++;
                }
            }
        }
        return numSymmetric / (myGraph.edges());
    }

    public Set<String> reachable(String user) {
        Set<Integer> reachableInts = new HashSet<Integer>();
        HashSet<String> reachable = new HashSet<String>();
        ArrayList<Integer> temp = new ArrayList<>();

        for (int i : getFollowed(user)) {
            reachableInts.add(i);
        }

        for (int i = 0; i < namesArray.size(); i++) {
            for (int j : reachableInts) {
                for (int k : getFollowed(namesArray.get(j))) {
                    temp.add(k);
                }
            }
            for (int j : temp)
                reachableInts.add(j);
        }

        // Converts the set of integers to user names
        for (int f : reachableInts) {
            if (!(f == namesArray.indexOf(user)))
                reachable.add(namesArray.get(f));
        }
        return reachable;
    }

    /*
     * Checks if user1 follows user2.
     */
    public boolean follows(String user1, String user2) {
        if (myGraph.getAdjacent(namesArray.indexOf(user1)).contains(namesArray.indexOf(user2))) {
            return true;
        }
        return false;
    }

    // Gets the numbers of users that user1 follows
    public int numUsersFollowed(String user1) {
        return myGraph.getAdjacent(namesArray.indexOf(user1)).size();
    }

    public int[] getFollowed(String user) {
        Set<Integer> tempFollows = myGraph.getAdjacent(namesArray.indexOf(user));
        int[] followArray = new int[tempFollows.size()];
        int j = 0;
        for (int thisInt : tempFollows) {
            followArray[j++] = thisInt;
        }
        return followArray;
    }

    int distance(String user1, String user2) {
        // Base case: If user1 follows user2, the shortest path is 1
        int distance = Integer.MAX_VALUE;
        if (follows(user1, user2)) {
            distance = 1;
        } 
        
        else {
            for (int i = 0; i < numUsersFollowed(user1); i++) {
                if (follows(namesArray.get(getFollowed(user1)[i]), user2)) {
                    distance = 2;
                } else {
                    distance = 3;
                }
            }
        }
        return distance;
    }

    public String path(String user1, String user2) {
        String path = "[NONE}";
        if (follows(user1, user2)) {
            path = "[" + user1 + "|" + user2 + "]";
        } else {
            for (int i = 0; i < numUsersFollowed(user1); i++) {
                Set<Integer> tempFollows = myGraph.getAdjacent(namesArray.indexOf(user1));
                Integer[] followArray = new Integer[tempFollows.size()];
                int j = 0;
                for (int thisInt : tempFollows) {
                    followArray[j++] = thisInt;
                }
                if (follows(namesArray.get(followArray[i]), user2)) {
                    path += (namesArray.get(followArray[i]) + " ");
                } else {
                    path = "fish";
                }
            }
        }
        return path;
    }

    public static void main(String[] args) {
        SocApp spynet = new SocApp("spies.txt");
        // String[] spies = { "bond", "caveman", "daredevil", "antman" };

        // System.out.println("The most popular one is " + spynet.mostPopular());
        // System.out.println("The top follower is " + spynet.topFollower());
        // System.out.println("Is Bond considered an influencer? " +
        // spynet.isInfluencer("bond"));
        // System.out.printf("Graph reciprocity: %4.2f\n", spynet.reciprocity());

        // System.out.println(spynet.distance(spies[0], spies[2]));

        // System.out.printf("Shortest path from %s to %s has %d edge(s): %s\n",
        // spies[0], spies[2], spynet.distance(spies[0], spies[2]),
        // spynet.path(spies[0], spies[2]));

        // System.out.printf("Shortest path from %s to %s has %d edge(s): %s\n",
        // spies[1], spies[3], spynet.distance(spies[1], spies[3]),
        // spynet.path(spies[1], spies[3]));

        // for (String spy : spies) {
        // System.out.printf("Users reachable from %s: %s\n", spy,
        // spynet.reachable(spy));
        // }

        String[] spies2 = { "Microsoft", "Inventec", "Kodak", "Sony", "Qualcomm", "B&N", "Apple", "HTC", "Samsung",
                "LG", "Amazon", "Foxconn", "Motorola", "Nokia" };
                System.out.println("Distance from: " + spies2[0] + " to " + spies2[1] + ": " + spynet.distance(spies2[0],
                        spies2[1]));
        System.out.println("Expected: " + 1);

        // System.out.println(spynet.reachable(spies2[3]));
        // for (String spy : spies2) {
        // System.out.printf("Users reachable from %s: %s\n", spy,
        // spynet.reachable(spy));
        // }
    }
}