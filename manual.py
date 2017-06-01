import json

features = []

with open("data/manual_coors.txt", "r") as groc_csv:
    lines = groc_csv.readlines()
    for l in lines:
        data = l.split(",")

        feature = {
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [data[5].strip(), data[4].strip()]
            },
            "properties": {
                "name": data[0].strip(),
                "type": data[1].strip(),
                "address": data[2].strip() + "," + data[3]
            }
        }
        features.append(feature);
    with open("data/man.geojson", "w") as outfile:
        json.dump(features, outfile)
