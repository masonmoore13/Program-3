/**
   Program to perform basic testing of the SocApp class.  Expected output is below.
   
   The most popular one is caveman
   The top follower is daredevil
   Is Bond considered an influencer? true
   Graph reciprocity: 0.29
   Shortest path from antman to caveman has 1 edge(s): [antman|caveman]
   Shortest path from bond to daredevil has 2147483647 edge(s): [NONE]
   Users reachable from antman: [caveman, daredevil, bond]
   Users reachable from bond: [caveman]
   Users reachable from caveman: []
   Users reachable from daredevil: [caveman, antman, bond] 
*/
public class SocAppTest
{     
   // public static void main (String[] args)
   // {
   //    SocApp spynet = new SocApp("spies.txt");
   //    String[] spies = {"antman", "bond", "caveman", "daredevil" };
      
   //    System.out.println("The most popular one is " + spynet.mostPopular());
   //    System.out.println("The top follower is " + spynet.topFollower());
   //    System.out.println("Is Bond considered an influencer? " + spynet.isInfluencer("bond"));
   //    System.out.printf("Graph reciprocity: %4.2f\n", spynet.reciprocity());
   
   //    System.out.printf("Shortest path from %s to %s has %d edge(s): %s\n", 
   //                       spies[0], spies[2], spynet.distance(spies[0], spies[2]), 
   //                       spynet.path(spies[0], spies[2]));
   
   //    System.out.printf("Shortest path from %s to %s has %d edge(s): %s\n", 
   //                       spies[1], spies[3], spynet.distance(spies[1], spies[3]), 
   //                       spynet.path(spies[1], spies[3]));
   //    for (String spy: spies)
   //    {
   //       System.out.printf("Users reachable from %s: %s\n", spy, spynet.reachable(spy));
   //    }
   // }
}