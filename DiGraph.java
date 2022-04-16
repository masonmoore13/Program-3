/******************************************************************************
 *
 *  A directed graph, implemented using a Map of sets.
 *  Self-loops allowed.  Based on the general Graph class provided by
 *  cs.princeton.edu under the GNU General Public License, version 3 (GPLv3)
 *  available at http://www.gnu.org/copyleft/gpl.html
 *
 *  The <tt>DiGraph</tt> class represents a directed graph of vertices,
 *  represented as integer values.
 *  It supports the following operations:
 *  - add a vertex to the graph,
 *  - add an edge to the graph,
 *  - obtain a set of all of the vertices adjacent to a vertex. 
 *  It also provides methods for returning the number of vertices <em>V</em>,
 *  the number of edges <em>E</em>, and a String representation of the Graph.
 * 
 */
 
import java.util.*;
 
public class DiGraph {
   private static final String NEWLINE = System.getProperty("line.separator");
   private int V;
   private int E;
   private Map<Integer, Set<Integer>> adj;
   
   /**
    * Initializes an empty graph
    */
   public DiGraph() {
      this.V = 0;
      this.E = 0;
      adj =  new TreeMap<>();
   }

    /**
    * Returns the number of vertices in this graph.
    *
    * @return the number of vertices in this graph
    */
   public int vertices() {
      return V;
   }

   /**
    * Returns the number of edges in this graph.
    *
    * @return the number of edges in this graph
    */
   public int edges() {
      return E;
   }

   /**
    * Ensures the argument is a valid vertex in the graph
    *
	 * @param  v one vertex in the graph
    * @throws IllegalArgumentException if v is not a valid vertex
    */
   private void validateVertex(int v) {
      if (!adj.containsKey(v))
         throw new IllegalArgumentException("Invalid Vertex " + v);
   }

  /**
       * Adds the vertex v to this graph
       *
       * @param  v one vertex in the graph
       * @return true if v was added, false otherwise
   */
   public boolean addVertex(int v) {
      if (adj.containsKey(v))
         return false;
      V++;
      Set<Integer> neighbors = new HashSet<>();
      adj.put(v, neighbors);
      return true;
   }

   /**
    * Adds the directed edge v-w to this graph.
    * The arguments must be valid vertices in the graph.
    * @param  v one vertex in the edge
    * @param  w the other vertex in the edge
    * @return true if edge was added, false otherwise
    * @throws IllegalArgumentException if either vertex does not exist
    */
   public boolean addEdge(int v, int w) {
      validateVertex(v);
      validateVertex(w);
      if (adj.get(v).contains(w))
         return false;
      E++;
      adj.get(v).add(w);
      return true;
   }

   /**
    * Returns the vertices adjacent to vertex <tt>v</tt>.
    *
    * @param  v the vertex
    * @return a set containing the vertices adjacent to vertex <tt>v</tt>
    * @throws IllegalArgumentException if v is not a valid vertex
    */
   public Set<Integer> getAdjacent(int v) {
      validateVertex(v);
      return new HashSet<Integer>(adj.get(v));
   }

   /**
    * Returns a string representation of this graph.
    *
    * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
    *         followed by the <em>V</em> adjacency lists
    */
   public String toString() {
      StringBuilder s = new StringBuilder();
      s.append(V + " vertices, " + E + " edges " + NEWLINE);
      for (int v: adj.keySet()) {
         s.append(v + ": ");
         for (int w : adj.get(v)) {
            s.append(w + " ");
         }
         s.append(NEWLINE);
      }
      return s.toString();
   }

/*
   Creates a sample graph with 3 vertices and 2 edges.
*/   
   public static void main(String[] args)
   {
      DiGraph g = new DiGraph();
      g.addVertex(25);
      g.addVertex(10);
      g.addVertex(50);
      g.addEdge(25, 50);
      g.addEdge(25, 10);
      System.out.println(g); 
   }
}
