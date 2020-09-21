#include "seller.h"
#include "line.h"
#include "chaos.h"

void Seller::visitLine(Line* line) const {
  line->pop();
}
void Seller::visitChaos(Chaos* chaos) const {
  if (chaos->getSize() == 0) {
    return;
  }
  int pow = chaos->getFront().getNum();
  IterLine buf(nullptr);
  for (IterLine it = chaos->begin(); it != chaos->end(); ++it) {
    if (it.getNode()->getNext() != nullptr
        && it.getNode()->getNext()->getData().getNum() > pow) {
      pow = it.getNode()->getNext()->getData().getNum();
      buf = it;
    }
  }
  if (buf.getNode() == nullptr) {
    chaos->pop();
  } else {
    chaos->delNext(buf);
  }
}


