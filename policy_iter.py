import numpy as np
import random
import matplotlib.pyplot as plot

def policy_iter(learning_rate,max_episodes,max_steps):
	records = []
	w = []
	for i in range(4):
		w.append([0.0]*4)

	sum_reward = 0.0
	theta = []
	posib = []
	for i in range(3):  # init theta and posibilities 
		theta.append(w)
		posib.append(w)
	terminals = [{'cordinate':[0,3],'reward':1},{'cordinate:':[1,3],'reward':-1}]

	for episod in range(max_episodes):
		cur_cor = [0,0]
		if_ter = False
		tracks = []

		for x in range(3):
			for y in range(4):
				for p in range(4):
					posib[x][y][p] = soft_max(p,[x,y],theta)

		for step in range(max_steps):
			# randomly choose a action
			# 0 1 2 3 stands for west north east south
			action_chose = random.randint(0,3) 

			# for 80% it'll go right direction, 20% right angle
			rnd = random.random()
			if rnd>0.9:
				action_chose = (action_chose + 1) % 4
			elif rnd>0.8:
				action_chose = (action_chose - 1) % 4

			last_cor = cur_cor[:]
            
            # decide next cordinate
			if action_chose == 0:
				cur_cor[1] = cur_cor[1]-1 if cur_cor[1]>0 else 0
			elif action_chose == 2:
				cur_cor[1] = cur_cor[1]+1 if cur_cor[1]<3 else 3
			elif action_chose == 1:
				cur_cor[0] = cur_cor[0]+1 if cur_cor[0]<2 else 2
			elif action_chose == 3:
				cur_cor[0] = cur_cor[0]-1 if cur_cor[0]>0 else 0

			reward = -0.04
            # if reaches the terminals
			for terminal in terminals:
				if terminal['cordinate'] == cur_cor:
					if_ter = True
					reward = terminal['reward']
			tracks.append({'cordinate':cur_cor[:],'last_cor':last_cor[:],
				'reward':reward,'action':action_chose})
			if if_ter:
				break
        w = [0.0]*4
        rewards = []
        for i in range(3):
        	rewards.append(w)
        counter = [[0,0,0,0],[0,0,0,0],[0,0,0,0]]

        reward = 0.0
        for i in reversed(xrange(step+1)):
            reward += tracks[i]['reward']
            state = track[i]['last_cor'][:]
            rewards[state[0]][state[1]] += reward
            counter[state[0]][state[1]] += 1
        for i in range(3):
        	for j in range(4):
        		rewards[i][j] = rewards[i][j]/counter[i][j] if counter[i][j]!= 0 else rewards[i][j]

        for i in reversed(xrange(step+1)):
            state = tracks[i]['last_cor'][:]
            for k in range(4):
                if k == tracks[i]['action']:
                    x = 1.0 - posib[state[0]][state[1]][k]
                else:
                    x = -posib[state[0]][state[1]][k]
                    
                theta[state[0]][state[1]][k] += learning_rate * x * rewards[state[0]][state[1]]

        sum_reward += rewards[0][0]
        records.append(sum_reward/(episod+1))
	return records

def soft_max(a, s, theta):
    e = np.exp(theta[s[0]][s[1]][a])
    sum = np.sum(np.exp(theta[s[0]][s[1]]))
    dist = e / sum
    return dist

if __name__ == "__main__":
	rates = [1,0.1,0.01]
	records = [[],[],[]]
	standard = [0.705]*1000
	for i in range(3):
		records[i].append(policy_iter(rates[i],1000,100))

	plts = [0,0,0,0]
	for i in range(3):
		plts[i], = plot.plot(records[i],range(1000))
	plts[3] = plot.plot(standard,range(1000))
	plt.legend(plots, legends, loc=4)
	plt.show()
