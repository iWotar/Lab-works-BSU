#include "painter.h"
#include <ctime>
#include <cmath>

void Painter::draw(HDC hdc) {
  int size = 20;
  auto mas = control->getData();

  LOGBRUSH br;
  br.lbStyle = BS_SOLID;
  br.lbColor = RGB(60, 13, 32);
  HBRUSH brush;
  brush = CreateBrushIndirect(&br);
  SelectObject(hdc, brush);

  MoveToEx(hdc, 10, 10, NULL);
  LineTo(hdc, 10, 90);
  LineTo(hdc, 275, 90);
  LineTo(hdc, 275, 10);
  Ellipse(hdc, 100, 50, 130, 80);

  DeleteObject(brush);

  int x, y;
  srand(static_cast<unsigned int>(time(0)));

  for (int i = 0; i < mas[0].size(); i++) {
    LOGBRUSH br;
    br.lbStyle = BS_SOLID;
    br.lbColor = RGB(0, mas[0][i], 0);
    HBRUSH brush;
    brush = CreateBrushIndirect(&br);
    SelectObject(hdc, brush);
    x = 100;
    y = 100 + (size + 2) * i;
    Ellipse(hdc, x, y, x + size, y + size);
    DeleteObject(brush);
  }
  x = 130;
  y = 100;
  int deg;
  for (int i = 0; i < mas[1].size(); i++) {
    LOGBRUSH br;
    br.lbStyle = BS_SOLID;
    br.lbColor = RGB(mas[1][i], 0, 0);
    HBRUSH brush;
    brush = CreateBrushIndirect(&br);
    SelectObject(hdc, brush);
    Ellipse(hdc, x, y, x + size, y + size);
    deg = rand() % 2;
    y += size * std::cos(deg) + rand() % 7;
    x += size * std::sin(deg) + rand() % 30 - 15;
    DeleteObject(brush);
  }
}
Painter::Painter(Controller* det) {
  control = det;
}

bool Painter::sign() {
  return control->signal();
}
