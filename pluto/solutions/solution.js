process.stdin.resume();
process.stdin.setEncoding('utf8');
var chunks = [];
process.stdin.on('data', function (chunk) {
	chunks.push(chunk);
});
process.stdin.once('end', function() {
	main(chunks.join(''));
});

function main(input) {
	var lines = input.split('\n');
	for(var i = 1; i <= +lines[0]; i++) {
		console.log(arithmetic(lines[i].split(' ')));
	}
}

var arithmetic = function(words){
	var conditions = '';
	var longest = Math.max.apply(null,words.map(function(w){return w.length;}));
	for(var i=0; i<longest; i++){
		for(var j=0; j<words.length; j++){
			if(i < words[j].length && j < words.length-1){
				conditions+='+'+words[j][words[j].length-1-i];
			} else if(j == words.length-1 && i<words[j].length){
				conditions += '='+words[j][words[j].length-1-i];
			}
		}
	}

	var lowest = {};
	words.forEach(function(word){
		for(var i=0; i<word.length; i++){
			lowest[word[i]] = 0;
		}
	});
	for(var i=0; i<words.length; i++){
		lowest[words[i][0]] = 1;
	}

	var answers = solve(conditions, lowest, 0, {}, [false,false,false,false,false,false,false,false,false,false], 0);
	if(answers.length == 1){
		answer = answers[0];
		var numbers = words.map(function(word, i){
			return word.split('').map(function(letter){
				return answer[letter];
			}).join('') + (i < words.length-2 ? '+' : (i <words.length-1 ? '=': ''));
		}).join('');
		return numbers;
	}
	if(answers.length > 1){
		return answers.length + ' solutions?!!??! ' + words.join(' ');
		return answers.map(function(answer){
			return words.map(function(word, i){
				return word.split('').map(function(letter){
					return answer[letter];
				}).join('') + (i < words.length-2 ? '+' : (i <words.length-1 ? '=': ''));
			}).join('');
		}).join('\n');
	}
	return 'no solution';
}

var solve = function(conditions, lowest, index, assigned, used, error){
	if(index >= conditions.length){
		if(error == 0){
			// console.log('solved', JSON.stringify(assigned));
			return [JSON.parse(JSON.stringify(assigned))];
		}
		else
			return [];
	}

	var result = [];

	if(conditions[index] == '+'){
		if(conditions[index+1] in assigned){
			result = result.concat(solve(conditions, lowest, index+2, assigned, used, error + assigned[conditions[index+1]]));
		} else {
			for(var i=lowest[conditions[index+1]]; i<10; i++){
				if(!used[i]){
					used[i] = true;
					assigned[conditions[index+1]] = i;
					result = result.concat(solve(conditions, lowest, index+2, assigned, used, error + i));
					delete assigned[conditions[index+1]];
					used[i] = false;
				}
			}
		}
	} else if(conditions[index] == '='){
		if(conditions[index+1] in assigned && assigned[conditions[index+1]] == error%10){
			result = result.concat(solve(conditions, lowest, index+2, assigned, used, Math.floor(error/10)));
		} else if(!used[error%10] && !(conditions[index+1] in assigned) && error%10 >= lowest[conditions[index+1]]){
			used[error%10] = true;
			assigned[conditions[index+1]] = error%10;
			result = result.concat(solve(conditions, lowest, index+2, assigned, used, Math.floor(error/10)));
			used[error%10] = false;
			delete assigned[conditions[index+1]];
		}
	}
	return result;
}