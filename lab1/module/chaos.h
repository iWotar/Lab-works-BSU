#ifndef CHAOS_H
#define CHAOS_H
#include <initializer_list>

#include "line.h"

class IterLine;
class Node;
class Seller;

class Chaos : public Line {
 public:
  Chaos();
  Chaos(std::initializer_list<Man> mas);
  Chaos(std::initializer_list<int> mas);

  void delNext(IterLine it);

  void visit(const Seller& seller) override;

  ~Chaos();
};

#endif //Chaos_H
