package dataStructure.tree;

public class traversal {
    //         A
    //        /\
    //       B  C
    //      /\
    //     D E
    //

    //前序  A,B,D,E,C
    //
    //  visit node
    //  traverse(left child)
    //  traverse(right child)
    //
    //中序  D,B,E,A,C
    //
    //  traverse(left child)
    //  visit node
    //  traverse(right child)
    //
    //后序  D,E,B,C,A
    //
    //  traverse(left child)
    //  traverse(right child)
    //  visit  node
    //
    //层序 A,B,C,D,E
    //
    // create a queue called nodes
    // create an unordered list called results
    // enqueue the root onto the nodes queue
    // while the nodes queue is not empty {
    //
    //   dequeue the first element form the queue
    //   If that element is not null
    //      Add that element to the rear of the result list
    //      Enqueue the children of the element to the nodes queue
    //   Else
    //      Add null on the result list
    // }
    // Return an Iterator for the result list
}
