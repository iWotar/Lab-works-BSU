#ifndef NODE_H
#define NODE_H

#include "man.h"
class Line;
class Chaos;

class Node {
 public:
  Node* getNext() const;
  Man getData() const;
  ~Node();
 private:
  Node();
  Node(Man& d);
  void push(Man dt);
  Node* next;
  Man data;
  friend Line;
  friend Chaos;
};

#endif //NODE_H
