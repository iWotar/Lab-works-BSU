#include "chaos.h"
#include "seller.h"

Chaos::Chaos() : Line() {}

Chaos::Chaos(std::initializer_list<Man> mas) : Line(mas) {}

void Chaos::visit(const Seller& seller) {
  seller.visitChaos(this);
}
void Chaos::delNext(IterLine it) {
  Node* dt = it.getNode();
  if (dt != nullptr && dt->next != nullptr) {
    Node* buf = dt->next;
    dt->next = dt->next->next;
    buf->next = nullptr;
    delete buf;
  }
  size--;
}
Chaos::~Chaos() {

}
Chaos::Chaos(std::initializer_list<int> mas) : Line(mas) {}
