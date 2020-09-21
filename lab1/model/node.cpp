#include "node.h"

Node::~Node() {
  delete next;
}

Node::Node(Man& d) : data(d), next(nullptr) {}

Node::Node() : next(nullptr) {}

Node* Node::getNext() const {
  return next;
}

void Node::push(Man dt) {
  next = new Node(dt);
}
Man Node::getData() const {
  return data;
}

