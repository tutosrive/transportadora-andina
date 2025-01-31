import json

global cities
cities = {}

with open('ciudades.json', 'r', encoding='utf-8') as f:
	readed = f.read()
	dict_readed = json.loads(readed)
	for i, read in enumerate(dict_readed):
		cities[f'citiy{i}'] = read['nombre']
	f.close()


with open('nombres_ciudades.json', 'w', encoding='utf-8') as file:
	json.dump(cities, file)
	# file.write(cities)
	file.close()