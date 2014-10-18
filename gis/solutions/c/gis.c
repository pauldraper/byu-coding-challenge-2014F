#include <stdio.h>

int intercept(int x, int y, int x1, int y1, int x2, int y2) {
	return x1 <= x
		&& x < x2
		&& y < (float)(y2 - y1) / (x2 - x1) * (x - x1) + y1;
}

int main() {
	int x, y, n;
	scanf("%d %d\n%d", &x, &y, &n);

	int count = 0;
	int i, xFirst, yFirst, x1, y1;
	for(i = 0; i < n; i++) {
		int x2, y2;
		scanf("%d %d", &x2, &y2);
		if(i) {
			count += intercept(x, y, x1, y1, x2, y2);
		} else {
			xFirst = x2;
			yFirst = y2;
		}
		x1 = x2;
		y1 = y2;
	}
	count += intercept(x, y, x1, y1, xFirst, yFirst);

	printf("%s\n", count % 2 ? "YES" : "NO");

	return 0;
}
