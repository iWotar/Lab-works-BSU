#include <windows.h>
#include "painter.h"

#define IDT_TIMER1 1

Logic module;
Controller control(&module);
Painter view(&control);

LRESULT CALLBACK WndProc(HWND, UINT, WPARAM, LPARAM);
char szClassName[] = "CG_WAPI_Template";

int WINAPI WinMain(HINSTANCE hInstance,
                   HINSTANCE hPrevInstance,
                   LPSTR lpCmdLine,
                   int nCmdShow) {
  HWND hWnd;
  MSG lpMsg;
  WNDCLASS wc;

  wc.style = CS_HREDRAW | CS_VREDRAW;
  wc.lpfnWndProc = WndProc;
  wc.cbClsExtra = 0;
  wc.cbWndExtra = 0;
  wc.hInstance = hInstance;
  wc.hIcon = NULL;
  wc.hCursor = LoadCursor(NULL, IDC_ARROW);
  wc.hbrBackground = (HBRUSH) GetStockObject(WHITE_BRUSH);
  wc.lpszMenuName = NULL;
  wc.lpszClassName = (LPCTSTR) szClassName;

  if (!RegisterClass(&wc)) {
    MessageBox(NULL,
               (LPCTSTR) "Не могу зарегистрировать класс окна!",
               (LPCTSTR) "Ошибка",
               MB_OK);
    return 0;
  }

  hWnd = CreateWindow(
      (LPCTSTR) szClassName,
      (LPCTSTR) "lab 1",
      WS_OVERLAPPEDWINDOW,
      50, 50,
      300, 600,
      (HWND) NULL,
      (HMENU) NULL,
      (HINSTANCE) hInstance,
      NULL);

  SetTimer(hWnd,
           IDT_TIMER1,
           500,
           (TIMERPROC) NULL);

  if (!hWnd) {
    MessageBox(NULL,
               (LPCTSTR) "Не удается создать главное окно!",
               (LPCTSTR) "Ошибка",
               MB_OK);
    return 0;
  }

  ShowWindow(hWnd, nCmdShow);
  UpdateWindow(hWnd);

  while (GetMessage(&lpMsg, NULL, 0, 0)) {
    TranslateMessage(&lpMsg);
    DispatchMessage(&lpMsg);
  }
  return (lpMsg.wParam);
}

LRESULT CALLBACK WndProc(HWND hWnd, UINT messg, WPARAM wParam, LPARAM lParam) {
  PAINTSTRUCT ps;
  RECT Rect;
  HDC hdc, hCmpDC;
  HBITMAP hBmp;

  switch (messg) {
    case WM_LBUTTONDOWN:
      view.sign();
      InvalidateRect(hWnd, NULL, TRUE);
      break;

    case WM_PAINT:
      GetClientRect(hWnd, &Rect);
      hdc = BeginPaint(hWnd, &ps);

      hCmpDC = CreateCompatibleDC(hdc);
      hBmp = CreateCompatibleBitmap(hdc, Rect.right - Rect.left,
                                    Rect.bottom - Rect.top);
      SelectObject(hCmpDC, hBmp);

      LOGBRUSH br;
      br.lbStyle = BS_SOLID;
      br.lbColor = 0xEECCCC;
      HBRUSH brush;
      brush = CreateBrushIndirect(&br);
      FillRect(hCmpDC, &Rect, brush);
      DeleteObject(brush);

      view.draw(hCmpDC);

      SetStretchBltMode(hdc, COLORONCOLOR);
      BitBlt(hdc, 0, 0, Rect.right - Rect.left, Rect.bottom - Rect.top,
             hCmpDC, 0, 0, SRCCOPY);

      DeleteDC(hCmpDC);
      DeleteObject(hBmp);
      hCmpDC = NULL;

      EndPaint(hWnd, &ps);
      break;

    case WM_TIMER:
      if (!view.sign()) {
        DestroyWindow(hWnd);
      }
      InvalidateRect(hWnd, NULL, TRUE);
      break;

    case WM_DESTROY:
      PostQuitMessage(0);
      break;

    default:return (DefWindowProc(hWnd, messg, wParam, lParam));
  }
  return (0);
}