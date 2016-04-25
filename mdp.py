from numpy import *
import random

terminal_grid =[[0,0,0,2],
				[0,1,0,2],
				[0,0,0,0]]
value_grid = [[0,0,0,100],
			[0,1,0,-100],
			[0,0,0,0]]
action_grid = [ [[0,0]] * len(x) for x in value_grid]

# def value_iter(visited,pos,p,r):
# 	if terminal_grid[pos[0]][pos[1]] == 2:
# 		return
# 	visited.append(pos)
# 	col = len(terminal_grid)
# 	row = len(terminal_grid[0])
# 	neighbour = [[-1,0],[1,0],[0,-1],[0,1]]
# 	next_pos = [add(pos,n) for n in neighbour ]
# 	original_u = [0,0,0,0]
# 	for i in range(4):
# 		if 0<= next_pos[i][0] < col and 0<= next_pos[i][1] < row and terminal_grid[next_pos[i][0]][next_pos[i][1]] != 1:
# 			original_u[i] = value_grid[next_pos[i][0]][next_pos[i][1]]
# 		else:
# 			original_u[i] = value_grid[pos[0]][pos[1]]
# 	utilities = [0,0,0,0]
# 	utilities[0] = p * original_u[0] + 0.5 * (1-p) * original_u[2] + 0.5 * (1-p) * original_u[3]
# 	utilities[1] = p * original_u[1] + 0.5 * (1-p) * original_u[2] + 0.5 * (1-p) * original_u[3]
# 	utilities[2] = p * original_u[2] + 0.5 * (1-p) * original_u[0] + 0.5 * (1-p) * original_u[1]
# 	utilities[3] = p * original_u[3] + 0.5 * (1-p) * original_u[0] + 0.5 * (1-p) * original_u[1]
# 	u = max(utilities) * r - 4
# 	tmp_grid[pos[0]][pos[1]] = u

# 	next_pos = [[n[0],n[1]] for n in next_pos if 0<=n[0]<col and 0<=n[1]<row and terminal_grid[n[0]][n[1]] != 1]
# 	next_pos = [n for n in next_pos if n not in visited]
# 	for next_p in next_pos:
# 		value_iter(visited,next_p,p,r)


# sub = 1000
# while sub > 0.2:
# 	visited = []
# 	value_iter(visited,[2,0],0.8,1)
# 	sub = sum(abs(subtract(value_grid,tmp_grid)))
# 	print sub
# 	for i in range(len(value_grid)):
# 		for j in range(len(value_grid[0])):
# 			value_grid[i][j] = tmp_grid[i][j]
# for line in value_grid:
# 	print line

def cal_utility_with_direct(direction,pos,p):
	col = len(value_grid)
	row = len(value_grid[0])
	possible_pos = []
	possible_pos.append(add(pos,direction))
	alternative = []
	if direction[0] in [1,-1]:
		alternative = [[0,-1],[0,1]]
		alternative = [add(pos,x) for x in alternative]
	else:
		alternative = [[-1,0],[1,0]]
		alternative = [add(pos,x) for x in alternative]
	possible_pos.append(alternative[0]);possible_pos.append(alternative[1]);
	for i in range(len(possible_pos)):
		if (possible_pos[i][0] in range(col) and possible_pos[i][1] in range(row)):
			continue
		else:
			possible_pos[i] = pos
	for i in range(len(possible_pos)):
		if terminal_grid[possible_pos[i][0]][possible_pos[i][1]] == 1 :
			possible_pos[i] = pos
	n = possible_pos
	u = p * value_grid[n[0][0]][n[0][1]] + 0.5 * (1 - p) * value_grid[n[1][0]][n[1][1]] +  0.5 * (1 - p) * value_grid[n[2][0]][n[2][1]]
	return u


def policy_iter(p):
	while True:
		unchanged = True
		for z in range(10):
			tmp_u = [[0]*len(value_grid[0]) for x in value_grid]
			for i in range(len(value_grid)):
				for j in range(len(value_grid[0])):
					if terminal_grid[i][j] not in [1,2]:
						value_grid[i][j] =  policy_evaluate([i,j],p)

		directions = [[1,0],[-1,0],[0,1],[0,-1]]
		tmp_action = [ [[0,0]] * len(x) for x in value_grid]
		tmp_max_utility = [ [0] * len(value_grid[0]) for x in value_grid ]

		for i in range(len(value_grid)):
			for j in range(len(value_grid[0])):
				if terminal_grid[i][j] not in [1,2]:
					tmp = []
					for d in directions:
						if d == action_grid[i][j]:
							tmp.append(tmp_u[i][j])
						else:
							tmp.append(cal_utility_with_direct([i,j],d,p))
					tmp_action[i][j] = directions[tmp.index(max(tmp))]

		if tmp_action == action_grid:
			break
		else:
			for i in range(len(value_grid)):
				for j in range(len(value_grid[0])):
					action_grid[i][j] = tmp_action[i][j]

def policy_evaluate(pos,p): # evaluate the policy ( of going a action from position pos)
	direction = action_grid[pos[0]][pos[1]]
	u = cal_utility_with_direct(direction,pos,p)
	return u 
	

def policy_improve():
	pass

direction = [[1,0],[-1,0],[0,1],[0,-1]]
for i in range(len(value_grid)):
	for j in range(len(value_grid[0])):
		if terminal_grid[i][j] not in [1,2] :
			value_grid[i][j] = random.randint(-50,50)
			action_grid[i][j] = random.choice(direction)

print value_grid
print action_grid
policy_iter(0.8)
print value_grid
print action_grid
