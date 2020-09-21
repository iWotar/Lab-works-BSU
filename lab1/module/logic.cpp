#include "logic.h"
#include <ctime>

Logic::Logic() {
  srand(static_cast<unsigned int>(time(0)));
  ch = new Chaos();
  ln = new Line();
  for (int i = 0; i < 30; i++) {
    ch->push(Man(100 + rand() % 100));
  }
  for (int i = 0; i < 30; i++) {
    ln->push(Man(100 + rand() % 100));
  }
  sel = new Seller();
}
bool Logic::step() {
  srand(static_cast<unsigned int>(time(0)));
  if (ch->getSize() > 0 && (rand() % 2 == 0 || ln->getSize() == 0)) {
    ch->visit(*sel);
  } else {
    ln->visit(*sel);
  }
  return ch->getSize() + ln->getSize() != 0;
}
Line* Logic::getLine() {
  return ln;
}
Chaos* Logic::getChaos() {
  return ch;
}
Seller* Logic::getSeller() {
  return sel;
}
