#ifndef LINE_H
#define LINE_H

#include <initializer_list>

#include "queue.h"
#include "Node.h"
#include "man.h"
#include "iter_line.h"

class IterLine;

class Line : public Queue {
 public:
  Line();
  Line(std::initializer_list<Man> mas);
  Line(std::initializer_list<int> mas);

  IterLine begin() const;
  IterLine end() const;

  void pop();
  Man getFront() const;

  void  visit(const Seller& seller) override;

  void push(Man el);
  int getSize() const;

  virtual ~Line();
 protected:
  Node* front;
  Node* back;

  int size;
};

#endif //LINE_H