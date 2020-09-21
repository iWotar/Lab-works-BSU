#ifndef DETECTOR_H
#define DETECTOR_H

#include "vector"
#include "../model/logic.h"

class Controller {
 public:
  explicit Controller(Logic* log);
  bool signal();
  std::vector<std::vector<int>> getData();
 private:
  Logic* logic_;
};

#endif //DETECTOR_H
