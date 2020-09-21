#ifndef PAINTER_H
#define PAINTER_H

#include <vector>
#include <windows.h>
#include "../controller/controller.h"

class Painter {
 public:
  explicit Painter(Controller* det);
  bool sign();

  void draw(HDC hds);
 private:
  Controller* control;
};

#endif //PAINTER_H
