import sys
def printScores(scores):
	for k,v in sorted(scores.items(), key=lambda x: (-x[1],x[0])):
		print k,v

def getScores(board, size, players):
	grid = [];
	for x in range(size,size*size+1,size):
		grid.append(board[x-size:x])

	empty = '-';

	scores = {}
	for i in range(players):
		scores[chr(ord('a')+i)] = 0

	for x1 in range(size):
		for y1 in range(size):
			if(grid[y1][x1] != empty):
				for x2 in range(x1+1 if y1==size-1 else x1,size):
					for y2 in range(0 if x2>x1 else y1+1,size):
						dx = x1-x2
						dy = y1-y2
						x3 = x1+dy
						x4 = x2+dy
						y3 = y1-dx
						y4 = y2-dx
						if x3 < 0 or x3 >= size or y3 < 0 or y3 >= size or x4 < 0 or x4 >= size or y4 < 0 or y4 >= size:
							continue
						if grid[y3][x3]==grid[y1][x1] and grid[y4][x4]==grid[y1][x1]:
							scores[grid[y1][x1]]+=1
	printScores({k:v/2 for k, v in scores.items()})

for _ in xrange(int(sys.stdin.readline())):
	players,size = map(int,sys.stdin.readline().split())
	board = sys.stdin.readline()
	getScores(board, size, players)