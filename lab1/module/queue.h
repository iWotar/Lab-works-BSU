#ifndef QUEUE_H
#define QUEUE_H

class Seller;

class Queue {
 public:
  Queue() = default;
  virtual void visit(const Seller& sel) = 0;

  virtual ~Queue() = default;
};

#endif //QUEUE_H
