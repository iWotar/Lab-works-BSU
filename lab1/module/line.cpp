#include "line.h"
#include "seller.h"

#include "iter_line.h"

Man Line::getFront() const {
  if (front == nullptr) {
    return Man();
  }
  return front->getData();
}
void Line::pop() {
  if (front != nullptr) {
    Node* buf = front;
    front = front->getNext();
    size--;
    buf->next = nullptr;
    delete buf;
  }
}

IterLine Line::begin() const {
  return IterLine(front);
}

IterLine Line::end() const {
  return IterLine(nullptr);
}

Line::Line() {
  front = nullptr;
  back = nullptr;
  size = 0;
}

void Line::push(Man el) {
  if (back == nullptr) {
    front = new Node(el);
    back = front;
    size = 1;
    return;
  }
  size++;
  back->push(el);
  back = back->next;
}
Line::Line(std::initializer_list<Man> mas) {
  size = 0;
  front = new Node();
  back = front;
  for (auto el : mas) {
    push(el);
  }
  Node* buf = front;
  front = front->getNext();
  buf->next = nullptr;
  delete buf;
}

int Line::getSize() const {
  return size;
}

Line::~Line() {
  if (front != nullptr && size != -1) {
    size = -1;
    delete front;
  }
}

void Line::visit(const Seller& seller) {
  seller.visitLine(this);
}
Line::Line(std::initializer_list<int> mas) {
  size = 0;
  front = new Node();
  back = front;
  for (auto el : mas) {
    push(Man(el));
  }
  Node* buf = front;
  front = front->getNext();
  buf->next = nullptr;
  delete buf;
}