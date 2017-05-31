import json

addr_list = []
with open("data/grocery.json", "r") as grocery_json:
    data = json.load(grocery_json)
    for g in data['data']:
        name = g[8]
        store_type = g[9]
        addr = json.loads(g[14][0])
        addr_text = addr["address"] + " " + addr["city"] + ", " + addr["state"]
        addr_list.append({"name": name, "type": store_type, "address": addr_text})
    with open("data/groc_addr.json", "w") as outfile:
        json.dump(addr_list, outfile)
