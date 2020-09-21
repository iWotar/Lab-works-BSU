#include "man.h"

Man::Man() {
  data = 0;
}

Man::Man(int num) {
  data = num;
}

int Man::getNum() {
  return data;
}
