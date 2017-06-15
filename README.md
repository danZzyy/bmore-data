# bmore-data
Using Baltimore City datasets from https://data.baltimorecity.gov/

Process
-------
I accquired the following datasets:
* [Grocery Stores](https://data.baltimorecity.gov/Health/Grocery-Stores/uuwk-975y)
* [Bike Facilities](https://data.baltimorecity.gov/Transportation/On-Street-Bike-Facilities/2fxj-ph4e)
* [Multe-Use Trails](https://data.baltimorecity.gov/Transportation/Multi-Use-Trails/ca8m-ir6r)

Bike Facilities and Multi-Use Trails are used together to create a unified set of Baltimore's bike-accessible paths
Each point in Grocery Store data contains NAME, TYPE(Full, Small, Limited), and ADDRESS, but no Lat/Lng coordinates
* __parse.py__ - I wrote this script to pick out NAME, TYPE, and concat the ADDRESS fields of 'address', "city", and "state" into a single string and then write these out to a JSON file
* __convert.html__ - I would use Google Maps' Geocoding function to get the Lat/Lng from the address create a geoJSON Feature for each grocery store.

_Only 12 out of the 32 store addresses successfully geocoded to Lat/Lng coordinates. I had to enter the rest manually by searching the address in Google Maps and copying down the coordinates. The issue for some of them was that streets in Baltimore have N/S and E/W sides and the dataset addresses did not specify. Some others did not have this issue so it is strange that they were not found, especially since both use Google services_
* __manual.py__ - Takes the text file I entered coordinates into and formats it to geoJSON
* __accessible.js__ - A Node script that uses Turf.js (a geoJSON processing library) to determine the accessibility of grocery stores by their proximity to bike-accessible paths
