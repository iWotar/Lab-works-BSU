#include "iter_line.h"
#include "Node.h"

IterLine::IterLine(Node* mas) {
  data = mas;
}

IterLine& IterLine::operator++() {
  data = data->getNext();
  return *this;
}

Man IterLine::operator*() const {
  return data->getData();
}

bool IterLine::operator!=(IterLine other) const {
  return data != other.data;
}

bool IterLine::operator==(IterLine other) const {
  return data == other.data;
}
Node* IterLine::getNode() const {
  return data;
}
IterLine& IterLine::operator=(const IterLine& other) = default;
