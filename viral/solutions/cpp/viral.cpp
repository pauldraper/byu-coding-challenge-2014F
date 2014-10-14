#include <iostream>
#include <map>
#include <string>

using namespace std;

int main() {
	string state;
	getline(cin, state);

	int numMinutes, numViruses;
	cin >> numMinutes >> numViruses;

	map<char,string> rules;
	for(int i = 0; i < numViruses; i++) {
		char c;
		cin >> c;
		cin.ignore();
		getline(cin, rules[c]);
	}

	map<char,long long> numbers;
	for(map<char,string>::const_iterator it = rules.begin(); it != rules.end(); it++) {
		numbers[it->first] = 1;
	}
	for(int i = 0; i < numMinutes; i++) {
		map<char,long long> newNumbers;
		for(map<char,string>::const_iterator it = rules.begin(); it != rules.end(); it++) {
			long long sum = 0;
			for(string::const_iterator rIt = it->second.begin(); rIt != it->second.end(); rIt++) {
				sum += numbers[*rIt];
			}
			newNumbers[it->first] = sum;
		}
		numbers = newNumbers;
	}

	long long sum = 0;
	for(string::const_iterator it = state.begin(); it != state.end(); it++) {
		sum += numbers[*it];
	}
	cout << sum << endl;
}
