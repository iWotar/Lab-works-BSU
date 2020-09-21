#ifndef ITER_LINE_H
#define ITER_LINE_H

#include <iterator>

#include "line.h"
#include "man.h"

class IterLine {
 public:
  IterLine(Node* mas);

  Man operator*() const;
  IterLine& operator++();

  Node* getNode() const;

  bool operator!=(IterLine other) const;
  bool operator==(IterLine other) const;

  IterLine& operator=(const IterLine& other);
 private:
  Node* data;
};

namespace std {
template<>
struct iterator_traits<IterLine> {
  using iterator_category = std::forward_iterator_tag;
  using value_type = Man;
};
}

#endif //ITER_LINE_H
