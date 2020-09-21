#ifndef SELLER_H
#define SELLER_H

#include <iostream>

class Line;
class Chaos;

class Seller {
 public:
  Seller() = default;

  void visitLine(Line* line) const;

  void visitChaos(Chaos* chaos) const;
};


#endif //SELLER_H
