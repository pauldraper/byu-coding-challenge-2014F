import math

class Combatant:
	def __init__(self, hp, strength, dexterity, attack, armor):
		self.hp = hp
		self.strength = strength
		self.dexterity = dexterity
		self.attack = attack
		self.armor = armor
		self.cover = False

me = Combatant(*map(int, raw_input().split()))
me.weapons = map(int, raw_input().split())
death = Combatant(*map(int, raw_input().split()))
death.weapons = map(int, raw_input().split())

for _ in xrange(input()):
	params = raw_input().split()
	if params[0] == 'I':
		attacker = me
		defender = death
	else:
		attacker = death
		defender = me
	if params[1] == '+':
		attacker.cover = True
	elif params[1] == '-':
		attacker.cover = False
	else:
		die = int(params[2])
		weapons = [ord(w) - ord('A') for w in params[1]]
		for i, w in enumerate(weapons):
			if die == 0:
				attack = float('-inf')
			elif die == 20:
				attack = float('inf')
			else:
				attack = die + attacker.attack + (attacker.dexterity if w == 3 else attacker.strength)
				if len(weapons) > 1:
					attack -= (10 if i else 6)
			if attack > defender.armor + (4 if defender.cover else 0):
				damage = attacker.weapons[w]
				if w == 0:
					damage += int(math.ceil(attacker.strength * 1.5))
				elif w != 3:
					damage += attacker.strength
				defender.hp -= damage

	print me.hp, death.hp
