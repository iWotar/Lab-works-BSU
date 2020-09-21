#include "controller.h"
Controller::Controller(Logic* log) {
  logic_ = log;
}
bool Controller::signal() {
  return logic_->step();
}
std::vector<std::vector<int>> Controller::getData() {
  std::vector<int> data_line;
  for (auto el : *(logic_->getLine())) {
    data_line.push_back(el.getNum());
  }
  std::vector<int> data_chaos;
  for (auto el : *(logic_->getChaos())) {
    data_chaos.push_back(el.getNum());
  }
  return std::vector<std::vector<int>>({data_line, data_chaos});
}
