def printScores(scores):
	for k, v in sorted(scores.iteritems(), key=lambda x: (-x[1],x[0])):
		print k, v/2

def getScores(board, size, players):
	grid = [board[x:x+size] for x in xrange(0, size*size, size)]
	scores = {chr(ord('a')+i):0 for i in xrange(players)}

	for x1 in xrange(size):
		for y1 in xrange(size):
			if grid[y1][x1] != '-':
				for x2 in xrange(x1+1 if y1==size-1 else x1,size):
					for y2 in xrange(0 if x2>x1 else y1+1,size):
						dx = x1-x2
						dy = y1-y2
						x3 = x1+dy
						x4 = x2+dy
						y3 = y1-dx
						y4 = y2-dx
						if 0 <= x3 < size and 0 <= y3 < size and 0 <= x4 < size and 0 <= y4 < size \
							and grid[y1][x1] == grid[y3][x3] == grid[y4][x4]:
								scores[grid[y1][x1]] += 1
	printScores(scores)

for _ in xrange(input()):
	players,size = map(int,raw_input().split())
	board = raw_input()
	getScores(board, size, players)