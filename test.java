/**
* The <code>Heap</code> class implements a heap of <code>HeapItem</code>
* objects.
*    
*
* @author M. D. W. 
*    e-mail: 
*    Stony Brook ID:
**/

public class Heap
{
    HeapItem[] heapStore; // An array holding the items in the heap.
    int numItems; // The number of items currently in the heap.
    int maxItems;
    
    // Invariants: 
    // numItems always represents the number of items currently in the heap.
    // heapStore[i] is less than or equal to heapStore[(i-1)/2] according to  
    // the definition of "less than" provided by the HeapItem's 
    // compareTo() method.
    
    /**
    * Returns an instance of <code>Heap</code>.
    *
    * @param capacity
    *    The maximum number of items in the heap.
    *
    * <dt>Precondition:
    *    <dd><code>capacity</code> must be greater than or equal to 0.
    * 
    * @exception IllegalArgumentException
    *    Indicates that <code>capacity</code> is less than zero.
    **/
    public Heap(int capacity)
    {
        heapStore = new HeapItem[capacity];
        numItems = 0;
        maxItems = capacity;
    }
    
    /**
    * Inserts a new HeapItem into the heap.
    *
    * @param item
    *    An object implementing the HeapItem interface
    *
    * <dt>Postcondition:
    *    <dd><code>item</code> has been added to the heap. If it is the greatest
    *    element of the heap it will be the first returned by the 
    *    <code>dequeue()</code> method.
    **/
    public void enqueue( HeapItem item ) 
        throws HeapFullException, IncomparableObjException
    {
        // The Heap is a complete binary tree 
        // with a depth of at most log n, where n is
        // the number of items in the heap. Enqueue 
        // is simply a series of swaps along a
        // single path from a new leaf at the bottom of 
        // the tree to the root of the tree. So the order of complexity will
        // the maximum depth of a leaf--i.e., the maximumem length of any
        // single path from a leaf to the root. The order of complexity is
        // therefore O(log n).
        
        int parentPosition, newPosition;
        if (isFull()) throw new HeapFullException(
          "Item cannot be added to full Heap");
        newPosition = numItems;
        parentPosition = (newPosition - 1)/2;
        numItems++;
        heapStore[newPosition] = item;
        
        while(newPosition > 0 &&
          heapStore[newPosition].compareTo(heapStore[parentPosition]) >= 0)
        {
            swap(newPosition, parentPosition);
            newPosition = parentPosition;
            parentPosition = (newPosition - 1)/2;
        }
    }
    
    /**
    * Returns the greatest item in the heap.
    *
    * <dt>Precondition:
    *   <dd>The heap is not empty.
    *   <dd>All items in the heap are objects of the same type.
    *
    * @return 
    *   Returns the greatest item in the heap, according to  
    *   the definition of "greater than" provided by the <code>HeapItem</code>'s
    *   <code>compareTo()</code> method.
    *
    * @exception HeapEmptyException
    *   Indicates that the heap is empty.
    * @exception IncomparableObjectException
    *   Indicates that two objects of different types have been found in the
    *   Heap.
    **/
    public HeapItem dequeue() 
      throws HeapEmptyException, IncomparableObjException
    {
        // The Heap is a complete binary tree 
        // with a depth of at most log n, where n is
        // the number of items in the heap. Dequeue 
        // is simply a series of swaps along a
        // single path from the root of the tree to a  
        // new leaf at the bottom of the tree. So the order of complexity will
        // the maximum depth of a leaf--i.e., the maximumem length of any
        // single path from a leaf to the root. The order of complexity is
        // therefore O(log n).
        int demoteeIndex, child1Index, child2Index, biggestChildIndex;
        boolean done;
        if (isEmpty()) throw new HeapEmptyException();
        HeapItem result = heapStore[0];
        heapStore[0] = heapStore[numItems - 1];
        numItems--;
        
        demoteeIndex = 0;
        child1Index = demoteeIndex * 2 + 1;
        child2Index = demoteeIndex * 2 + 2;
        done = ((child1Index >= numItems) && (child2Index >= numItems));
        
        while(!done)
        {
            biggestChildIndex = -1;
            
            if (child1Index >= numItems)
                biggestChildIndex = child2Index;
            else if (child2Index >= numItems)
                biggestChildIndex = child1Index;
                
            if (biggestChildIndex < 0)
            {
                switch
                 (heapStore[child1Index].compareTo(heapStore[child2Index]))
                {
                    case -1:
                        biggestChildIndex = child2Index;
                        break;
                    case 0:
                        biggestChildIndex = child2Index;
                        break;
                    case 1:
                        biggestChildIndex = child1Index;
                        break;
                }
            }
            switch( 
              heapStore[demoteeIndex].compareTo(heapStore[biggestChildIndex]) )
            {
                case -1:
                    swap( demoteeIndex, biggestChildIndex );
                    demoteeIndex = biggestChildIndex;
                    child1Index = demoteeIndex * 2 + 1;
                    child2Index = demoteeIndex * 2 + 2;
                    break;
                case 0:
                    done = true;
                    break;
                case 1:
                    done = true;
                    break;
            }
            done = (done || ((child1Index >= numItems) && 
              (child2Index >= numItems)));
        }
        return result;
    }
    
    /**
    * Swaps the positions of two items in the heap.
    *
    * @param position1 
    *    The position of the first item to be swapped.
    * @param position2
    *    The position of the second item to be swapped.
    *
    * <dt>Preconditions:
    *    <dd> position1 and position2 are both greater than or equal to 0 
    *    and less than the current number of items in the Heap.
    *
    * <dt>Postconditions:
    *    <dd>The positions of the two items indicated in the parameters have
    *    been exchanged.
    *
    * @exception IllegalArgumentException
    **/
    private void swap( int position1, int position2 )
    {
        if (position1<0 || position2<0 || 
          position1>=maxItems || position2>=maxItems)
            throw new IllegalArgumentException("Parameter out of range.");
        HeapItem temp = heapStore[position1];
        heapStore[position1] = heapStore[position2];
        heapStore[position2] = temp;
    }
    
    /**
    * Indicates whether the Heap has room for a new element.
    *
    * @return
    *   True if the Heap is full, false if the Heap has room for a new element.
    **/
    public boolean isFull()
    {
        return (numItems == maxItems);
    }
    
    /**
    * Indicates whether the Heap contains any elements.
    *
    * @return
    *   True if the Heap is empty, false if the Heap has any elements.
    **/
    public boolean isEmpty()
    {
        return (numItems == 0);
    }
    
    /**
    * Returns the current number of items in the Heap.
    *
    * @return
    *   Returns the number of items in the Heap.
    **/
    public int getSize()
    {
        return numItems;
    }
}