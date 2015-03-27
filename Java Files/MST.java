import java.io.File;
import java.util.Scanner;


public class MST<T> {
    static final int Infinity = Integer.MAX_VALUE;
/**
 * 
 * @param g : graph(set of vertices and edges)
 * @return value of minimum spanning tree
 */
    static  <E> int PrimMST(Graph g)
    {
	MST mst = new MST();

	PriorityQueueIndexed pq = new PriorityQueueIndexed(g.V); // create priority queue.
	int wmst = 0;
	Graph.Vertex src = g.V[1];


src.weight=0;
 while(!pq.isEmpty())	 
{ 
//Graph.Vertex u = pq.deleteMin();
	 Graph.Vertex u = (Graph.Vertex) pq.deleteMin();		// removes minimum key (weight)vertex
	 wmst+=u.weight;			
	 u.seen=true;
	 
	for(Graph.Edge e : u.Adj)
	{
		Graph.Vertex v = e.otherEnd(u);
		if(v.seen!= true && e.Weight<v.weight)
		{
			v.parent=u;
			v.weight = e.Weight;
			pq.decreaseKey(v);		// to maintain the heap property.
			
		}
	
	}
}
	
	return wmst;

   }

    public static void main(String[] args) 
    {
    	try
    	{
	Scanner in = new Scanner(new File(args[0]));
	Graph g = Graph.readGraph(in);
	g.initialize();	// initialize the graph  
	long startTime = System.currentTimeMillis();
	System.out.println(PrimMST(g));
	
	System.out.println("Time taken : " + (System.currentTimeMillis()-startTime) + "mSec");
    	}
    	catch (Exception e)
    	{ e.printStackTrace();}
    }
}

