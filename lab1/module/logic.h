#ifndef LOGIC_H
#define LOGIC_H

#include "line.h"
#include "chaos.h"
#include "seller.h"

class Logic {
 public:
  Logic();
  bool step();

  Line* getLine();
  Chaos* getChaos();
  Seller* getSeller();
 private:
  Seller* sel;
  Line* ln;
  Chaos* ch;
};

#endif //LOGIC_H
