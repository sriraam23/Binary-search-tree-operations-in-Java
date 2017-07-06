import java.util.*;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        //if( isEmpty( ) )
            //throw new UnderflowException( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        //if( isEmpty( ) )
            //throw new UnderflowException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree." );
        else
            printTree( root );
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    public int nodeCount()
    {
      return nodeCount(root);
    }
    
    private int nodeCount(BinaryNode<AnyType> t)
    {
       if(t == null)
         return 0;
       else
         return(nodeCount(t.left) + 1 + nodeCount(t.right));
    }
    
    public boolean isFull()
    {
      return isFull(root);
    }
       
    private boolean isFull(BinaryNode<AnyType> t)
    {
      if(t == null)
        return true;
      if((t.left != null && t.right == null) || (t.left == null && t.right != null))
        return false;
      return (isFull(t.left) && isFull(t.right));
    }
    
    public boolean compareStructure(BinarySearchTree<AnyType> t)
    {
      return compareStructure(root,t.root);     
    }
    
    private boolean compareStructure(BinaryNode<AnyType> a, BinaryNode<AnyType> b)
    {
      if(a == null && b == null)
        return true;
      if(a == null || b == null)
        return false;
      return (compareStructure(a.left,b.left) && compareStructure(a.right,b.right));
    }
    
    public boolean equals(BinarySearchTree<AnyType> t)
    {
      return equals(root,t.root);    
    }
    
    private boolean equals(BinaryNode<AnyType> a, BinaryNode<AnyType> b)
    {
      if(a == null && b == null)
        return true;
      if(a == null || b == null)
        return false;
      return (a.element == b.element && equals(a.left,b.left) && equals(a.right,b.right));
    }
    
    public BinarySearchTree<AnyType> copy(BinarySearchTree<AnyType> m)
    {
      m.makeEmpty();
      m.root = copy(root, m.root);
      return m;
    }
    
    private BinaryNode<AnyType> copy(BinaryNode<AnyType> a, BinaryNode<AnyType> b)
    {
      if(a != null)
      {
        b = new BinaryNode<>(a.element,null,null);
        if(a.left != null)
         b.left = copy(a.left, b.left);
        if(a.right != null)
         b.right = copy(a.right, b.right);
      }
      return b;  
    }
    
    public BinarySearchTree<AnyType> mirror(BinarySearchTree<AnyType> m)
    {
      m.makeEmpty();
      m.root = mirror(root, m.root);
      return m;
    }
    
    private BinaryNode<AnyType> mirror(BinaryNode<AnyType> a, BinaryNode<AnyType> b)
    {
      if(a != null)
      {
        b = new BinaryNode<>(a.element,null,null);
        if(a.left != null)
         b.right = mirror(a.left, b.right);
        if(a.right != null)
         b.left = mirror(a.right, b.left);
      }
      return b;
    }
    
    public boolean isMirror(BinarySearchTree<AnyType> m)
    {
      return isMirror(root,m.root);
    }
    
    private boolean isMirror(BinaryNode<AnyType> a, BinaryNode<AnyType> b)
    {
      if(a == null && b == null)
        return true;
      if(a == null || b == null)
        return false;
      return(a.element == b.element && isMirror(a.left,b.right) && isMirror(a.right,b.left));
    }
    
    public void printLevels()
    {
       BinaryNode<AnyType> temp;
       Queue<BinaryNode<AnyType>> q = new LinkedList<>();
       if(root == null)
         System.out.println("Empty tree.");
       else
       {
         q.add(root);
         q.add(null);
         while(!q.isEmpty())
         {
           temp = q.remove();
           if(temp == null)
           {
             System.out.println(" ");
             if(!q.isEmpty())
               q.add(null);
           }
           else
           { 
             System.out.print(temp.element + " ");
             if (temp.left != null)
               q.add(temp.left);
             if (temp.right != null)
               q.add(temp.right);
           }
         }
       }  
    }    
    
    public void rotateRight(AnyType a)
    {
      BinaryNode<AnyType> n = findNode(root, a);
      BinaryNode<AnyType> p = null;
      if (n != root && n != null)
        p = findParentNode(root,n);
      if(n != null)
      {
        rotateRight(n,p);
        printTree();
      }
      else
      {
        if (root != null)
          System.out.println("The node with given value does not exist in the tree. Tree structure remains the same.");
        printTree();
      }
    }
    
    //Custom function added to find a particular node based on an element.
    private BinaryNode<AnyType> findNode(BinaryNode<AnyType> k, AnyType a)
    {
      if(k == null)
        return null;
      int compare = a.compareTo(k.element);
      if(compare < 0)
        return(findNode(k.left,a));
      else if(compare > 0)
        return(findNode(k.right,a));
      else
        return k;
    }
    
    //Custom function added to find the parent node of a certain node.
    private BinaryNode<AnyType> findParentNode(BinaryNode<AnyType> k, BinaryNode<AnyType> r)
    {
      if(k == null)
       return null;
      if(k.left == r || k.right == r)
       return k;
      int compare = (r.element).compareTo(k.element);
      if (compare < 0)
        return findParentNode(k.left,r);
      else if (compare > 0)
        return findParentNode(k.right,r);  
      else
        return k;  
    }
    
    private void rotateRight(BinaryNode<AnyType> n, BinaryNode<AnyType> p)
    {
      BinaryNode<AnyType> g = n;
      BinaryNode<AnyType> k = n.left;
      if(k != null)
      {
        n.left = k.right;
        k.right = n;
        if (g == root) 
          root = k;
        else
        {
          if (p.left == n)
            p.left = k;
          else
            p.right = k;
        }
      }   
      else
      {
        System.out.println("Left child does not exist for the given node. So, no right rotation to make. Tree structure remains the same.");
        System.out.println(" ");
      }
    }
    
    public void rotateLeft(AnyType a)
    {
      BinaryNode<AnyType> n = findNode(root, a);
      BinaryNode<AnyType> p = null;
      if (n != root && n != null)
        p = findParentNode(root,n);
      if(n != null)
      {
        rotateLeft(n,p);
        printTree();
      }
      else
      {
        if (root != null)
          System.out.println("The node with given value does not exist in the tree. Tree structure remains the same.");
        printTree();
      }
    }
    
    private void rotateLeft(BinaryNode<AnyType> n, BinaryNode<AnyType> p)
    {
      BinaryNode<AnyType> g = n;
      BinaryNode<AnyType> k = n.right;
      if(k != null)
      {
        n.right = k.left;
        k.left = n;
        if (g == root) 
          root = k;
        else
        {
          if(p.left == n)
            p.left = k;
          else
            p.right = k;
        }
      } 
      else
      {
        System.out.println("Right child does not exist for the given node. So, no left rotation to make. Tree structure remains the same.");
        System.out.println(" ");
      }  
    }

    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }


      /** The tree root. */
    private BinaryNode<AnyType> root;


        // Test program
    public static void main( String [ ] args )
    {
        BinarySearchTree<Integer> t = new BinarySearchTree<>( );
        BinarySearchTree<Integer> m = new BinarySearchTree<>( );
        BinarySearchTree<Integer> n = new BinarySearchTree<>( );
        BinarySearchTree<Integer> l = new BinarySearchTree<>( );
        boolean t1, t2, t3, t4;
        
        t.insert(19);
        t.insert(10);
        t.insert(30);
        t.insert(12);
        t.insert(7);
        t.insert(11);
        t.insert(23);
        t.insert(14);
        t.insert(32);
        
        m.insert(19);
        m.insert(10);
        m.insert(30);
        m.insert(12);
        m.insert(7);
        m.insert(11);
        m.insert(23);
        m.insert(14);
        m.insert(32);
        
        // Printing the tree in inorder
        System.out.println("The elements of the tree T in inorder are:");
        t.printTree();
        System.out.println(" ");
        
        // Calling the nodeCount to calculate the number of nodes.
        System.out.println("There are " + t.nodeCount() + " nodes in the tree T.");
        System.out.println(" ");
        
        // Calling the isFull function to check if the binary tree is full or not.
        t1 = t.isFull();
        if(t1)
          System.out.println("The binary tree T is full.");
        else
          System.out.println("The binary tree T is not full.");
        System.out.println(" ");  
        
        System.out.println("The elements of the tree M in inorder are:");
        m.printTree();
        System.out.println(" ");
        // Calling the compareStructure function to see if the structures of the two trees match.
        t2 = t.compareStructure(m);
        if(t2)
          System.out.println("The two trees T and M have the same structure.");
        else
          System.out.println("The two trees T and M do not have the same structure.");
        System.out.println(" ");  
        
        // Calling the equals function to see if the two trees are identical.
        t3 = t.equals(m);
        if(t3)
          System.out.println("The two trees T and M are identical.");
        else
          System.out.println("The two trees T and M are not identical.");
        System.out.println(" ");
        
        // Calling the copy function to copy the given tree.
        System.out.println("Before copy, tree N is: ");
        n.printTree();
        n = t.copy(n);
        System.out.println(" ");
        System.out.println("After copying tree T, tree N in inorder reads: ");
        n.printTree();
        System.out.println(" ");
        
        // Calling the mirror function to produce the mirror of the given tree.
        System.out.println("Before mirroring, tree L is: ");
        l.printTree();
        l = t.mirror(l);
        System.out.println(" ");
        System.out.println("After mirroring tree T, tree L in inorder reads: ");
        l.printTree();
        System.out.println(" ");
        
        //Calling the isMirror function to see if the two trees are mirror images of each other.
        t4 = t.isMirror(m);
        if(t4)
          System.out.println("The two trees T and M are mirror images of each other.");
        else
          System.out.println("The two trees T and M are not mirror images of each other.");
        System.out.println(" ");
        
        //Calling the rotateRight function to perform a single right rotation on the node having the passed value.
        System.out.println("Tree T in inorder before right rotation:");
        t.printTree();
        System.out.println(" ");
        
        //Calling the printLevels function to print the elements of the tree level by level.
        System.out.println("Tree T in level order before right rotation:");
        t.printLevels();
        System.out.println(" ");
                
        System.out.println("Tree T in inorder, after right rotation at node with passed value:");
        t.rotateRight(5);
        System.out.println(" ");  
        System.out.println("The above tree T in level order:");
        t.printLevels();
        System.out.println(" ");
        
        //Calling the rotateLeft function to perform a single left rotation on the node having the passed value.        
        System.out.println("Tree T in inorder, after left rotation at node with passed value:");
        t.rotateLeft(30);
        System.out.println(" ");  
        System.out.println("The above tree T in level order:");
        t.printLevels();
   }
}